package cn.orgtec.hix.broadcast.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.orgtec.hix.broadcast.dto.BroadcastDetail;
import cn.orgtec.hix.broadcast.entity.BroadcastEntity;
import cn.orgtec.hix.broadcast.entity.SystemRecommendEntity;
import cn.orgtec.hix.broadcast.repository.BroadcastRepository;
import cn.orgtec.hix.broadcast.repository.SystemRecommendRepository;
import cn.orgtec.hix.broadcast.service.SystemRecommendedService;
import cn.orgtec.hix.broadcast.util.CreateBroadcastDetail;
import cn.orgtec.hix.broadcast.util.SpringContextHolder;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yibo Zhang
 * @date 2019/04/22
 */
@Service
@Slf4j
@AllArgsConstructor
public class SystemRecommendedServiceImpl implements SystemRecommendedService {

    private final BroadcastRepository broadcastRepository;
    private final SystemRecommendRepository systemRecommendRepository;
    private final CreateBroadcastDetail createBroadcastDetail;

    @Override
    public List<BroadcastDetail> getRecommendedBroadcasts(Integer userId) {

//        推荐最新广播  按照 TimestampDesc 排序
        List<SystemRecommendEntity> newBroadcast = systemRecommendRepository.findNewBroadcast(userId);

        int newSize = newBroadcast.size();
        List<Long> recommendBroadcastIds = new ArrayList<>();
        if (! CollUtil.isEmpty(newBroadcast)){
//            新广播大于 10  按照时间倒序
            if (newSize >= 10){
                for (int i = 0 ; i < 10 ; i ++){
                    Long broadcastId = newBroadcast.get(i).getBroadcastId();
                    recommendBroadcastIds.add(broadcastId);
//                    更新时间戳 0
                    systemRecommendRepository.updateTimestamp(broadcastId , userId);
                }
                // 构建广播详情
                List<BroadcastDetail> broadcastDetails = new ArrayList<>();
                buildBroadcastDetails(recommendBroadcastIds, broadcastDetails);
                return broadcastDetails;
            } else {
//                新广播不够 10  需要添加推荐广播
                for (SystemRecommendEntity aNewBroadcast : newBroadcast) {
                    Long broadcastId = aNewBroadcast.getBroadcastId();
                    recommendBroadcastIds.add(broadcastId);
//                    更新时间戳 0
                    systemRecommendRepository.updateTimestamp(broadcastId, userId);
                }
                try {
//                    推荐老广播
                    return getSystemOldBroadcast(userId, recommendBroadcastIds);
                }catch (Exception ex){
                    ex.printStackTrace();
                    return null;
                }
            }
        }else {
            try {
//               推荐老广播
                return getSystemOldBroadcast(userId, recommendBroadcastIds);
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
    }

    /**
     *  推荐老广播
     * @param userId                   用户id
     * @param recommendBroadcastIds    广播ids
     * @return                         List<BroadcastDetail>
     * @throws TasteException          TasteException
     */
    private List<BroadcastDetail> getSystemOldBroadcast(Integer userId, List<Long> recommendBroadcastIds) throws TasteException {
        JDBCDataModel dataModel = getJdbcDataModel();
        ItemSimilarity itemSimilarity = new UncenteredCosineSimilarity(dataModel);
//        定义系统推荐引擎  可能会是空数据
        GenericItemBasedRecommender recommended = new GenericItemBasedRecommender(dataModel, itemSimilarity);
        List<RecommendedItem> itemList = recommended.recommend(userId, 10);
        if (null != itemList) {
            // 查询当前用户 发布广播的 id
            List<BroadcastEntity> broadcastUid = broadcastRepository.findByUid(userId);
            List<Long> handlerBroadcastId = new ArrayList<>();
            for (int i = 0; i < broadcastUid.size(); i++) {
                // 2  11  12 用户自己的广播
                handlerBroadcastId.add(broadcastUid.get(i).getId());
            }

            for (int i = 0 ; i < handlerBroadcastId.size() ; i ++){
                System.out.println("======================================");
                System.out.println("用户自己发布的广播" + handlerBroadcastId.get(i));
            }

            for (int k = 0; k < itemList.size(); k++) {
                long itemID = itemList.get(k).getItemID();
                //                    广播 id
                if (!handlerBroadcastId.contains(itemID)) {
                    recommendBroadcastIds.add(itemList.get(k).getItemID());
                }
            }
        }


        for (int i  = 0 ; i < recommendBroadcastIds.size() ; i ++){
            System.err.println("======================================");
            System.out.println("推荐的广播" + recommendBroadcastIds.get(i));
        }

        // 根据亲密度推荐 查询当前用户和其它用户亲密度 排名前三的
        List<SystemRecommendEntity> top3SumIntimacyDesc = systemRecommendRepository.findTop3ByHandlerUserIdOrderBySumIntimacyDesc(userId);

        Set<Integer> publishUserIs = new HashSet<>();

        for (int i = 0; i < top3SumIntimacyDesc.size(); i++) {
            System.err.println("用户亲密度前三的用户:" + top3SumIntimacyDesc.get(i));
            Integer publishUserId = top3SumIntimacyDesc.get(i).getPublishUserId();
//            根据发布者 id 查询发布的广播
            publishUserIs.add(publishUserId);
        }

        publishUserIs.forEach((s) -> System.out.println("用户亲密度前三的用户:" + s));
//            需要被推荐的用户 id
        //                根据用户 id 查询 SystemRecommend 表该用户的广播
        //                根据广播 id 去重
        publishUserIs.stream().map(systemRecommendRepository::findByPublishUserIdOrderBySumIntimacyDesc).map(publishUserBroadcast -> publishUserBroadcast.stream()
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SystemRecommendEntity::getBroadcastId))), ArrayList::new))).flatMap(Collection::stream).map(SystemRecommendEntity::getBroadcastId).filter(broadcastId -> recommendBroadcastIds.size() != 10).forEach(broadcastId -> {
            recommendBroadcastIds.add(broadcastId);
            SystemRecommendEntity updateWeight = systemRecommendRepository.findDistinctByBroadcastIdAndHandlerUserId(broadcastId, userId);
            updateWeight.setBroadcastWeight(updateWeight.getBroadcastWeight() - 5);
            updateWeight.setSumIntimacy(updateWeight.getSumIntimacy() - 5);
            systemRecommendRepository.saveAndFlush(updateWeight);
        });

        // 构建广播详情
        List<BroadcastDetail> broadcastDetails = new ArrayList<>();
        buildBroadcastDetails(recommendBroadcastIds, broadcastDetails);
        return broadcastDetails;
    }

    /**
     *  构建广播详情
     * @param recommendBroadcastIds   广播 id 集合
     * @param broadcastDetails        存放详情的集合
     */
    private void buildBroadcastDetails(List<Long> recommendBroadcastIds, List<BroadcastDetail> broadcastDetails) {
        recommendBroadcastIds.forEach(newId -> {
            BroadcastEntity byId = broadcastRepository.findById(newId).get();
            BroadcastDetail broadcastDetail = createBroadcastDetail.getBroadcastDetail(newId.intValue(), byId);
            broadcastDetails.add(broadcastDetail);
        });
    }

    /**
     * JDBCDataModel
     *
     * @return 当前环境 HikariDataSource JDBCDataModel
     */
    private static JDBCDataModel getJdbcDataModel() {
        HikariDataSource bean = SpringContextHolder.getBean(HikariDataSource.class);
        return new MySQLJDBCDataModel(bean,"system_recommend","handler_user_id","broadcast_id","sum_intimacy",null);
    }
}
