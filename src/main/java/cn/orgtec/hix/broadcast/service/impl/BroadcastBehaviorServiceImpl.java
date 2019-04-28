package cn.orgtec.hix.broadcast.service.impl;

import cn.hutool.core.convert.Convert;
import cn.orgtec.hix.broadcast.entity.BroadcastBehaviorEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastCommentEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastEntity;
import cn.orgtec.hix.broadcast.entity.SystemRecommendEntity;
import cn.orgtec.hix.broadcast.repository.BroadcastBehaviorRepository;
import cn.orgtec.hix.broadcast.repository.BroadcastCommentRepository;
import cn.orgtec.hix.broadcast.repository.BroadcastRepository;
import cn.orgtec.hix.broadcast.repository.SystemRecommendRepository;
import cn.orgtec.hix.broadcast.service.BroadcastBehaviorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 广播行为服务实现
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
@Service
@Slf4j
@AllArgsConstructor
public class BroadcastBehaviorServiceImpl implements BroadcastBehaviorService {

    private final BroadcastBehaviorRepository behaviorRepository;
    private final BroadcastCommentRepository commentRepository;
    private final SystemRecommendRepository systemRecommendRepository;
    private final BroadcastRepository broadcastRepository;

    @Override
    public Boolean createFavor(Integer bid , Integer userId) {
        BroadcastBehaviorEntity behaviorEntity1 = getBehaviorEntity(bid ,userId);
        // 根据广播 id 查询出 用户 id
        Optional<BroadcastEntity> broadcastEntity = broadcastRepository.findById(Convert.toLong(bid));
        final Integer publishUserId = broadcastEntity.get().getUid();
        if (behaviorEntity1 == null) {
            BroadcastCommentEntity commentEntity = new BroadcastCommentEntity();
            commentEntity.setBroadcastId(bid);
            commentEntity.setReply(false);
            long countComment = commentRepository.count(Example.of(commentEntity));
            BroadcastBehaviorEntity behaviorEntity = new BroadcastBehaviorEntity();
//            1  已赞
            behaviorEntity.setFavor(1);
            behaviorEntity.setUserId(userId);
            behaviorEntity.setBroadcastId(bid);
            behaviorEntity.setComment(Convert.toInt(countComment));
            behaviorEntity.setPublishUserId(publishUserId);

            try {
//                保存点赞实体
                behaviorRepository.save(behaviorEntity);

              // 根据用户 id 和广播 id 查询
                SystemRecommendEntity updateSystemRecommend = systemRecommendRepository.findByBroadcastIdAndHandlerUserId(Convert.toLong(bid), userId);
                Integer intimacy = updateSystemRecommend.getIntimacy();
                if (null == intimacy){
                    updateSystemRecommend.setIntimacy( 3);
                    SystemRecommendEntity systemRecommendEntity = systemRecommendRepository.findFirstByHandlerUserIdAndPublishUserIdOrderBySumIntimacyDesc(userId, publishUserId);
                    Integer sumIntimacy = systemRecommendEntity.getSumIntimacy();
                    updateSystemRecommend.setSumIntimacy(sumIntimacy + 3);
                    systemRecommendRepository.saveAndFlush(updateSystemRecommend);

                    // 查询操作者点赞的广播 id
                    List<BroadcastBehaviorEntity> broadcastBehaviorEntities = behaviorRepository.findByUserIdAndPublishUserId(userId ,publishUserId);
                    SystemRecommendEntity updateRecommendEntity = systemRecommendRepository.findFirstByHandlerUserIdAndPublishUserIdOrderBySumIntimacyDesc(userId, publishUserId);
                    // 根据广播 id 和 操作者 id 更新总亲密度
                    for (int i = 0 ; i < broadcastBehaviorEntities.size() ; i ++){
                        if (!bid.equals(broadcastBehaviorEntities.get(i).getBroadcastId())){
                            SystemRecommendEntity updateSumRecommend = systemRecommendRepository.findByBroadcastIdAndHandlerUserId(Convert.toLong(broadcastBehaviorEntities.get(i).getBroadcastId()), userId);
                            updateSumRecommend.setSumIntimacy(updateRecommendEntity.getSumIntimacy());
                            systemRecommendRepository.saveAndFlush(updateSumRecommend);
                        }
                    }

                    return Boolean.TRUE;
                }else {
                    updateSystemRecommend.setIntimacy(updateSystemRecommend.getIntimacy() + 3);
                    updateSystemRecommend.setSumIntimacy(updateSystemRecommend.getSumIntimacy() + 3);
                    systemRecommendRepository.saveAndFlush(updateSystemRecommend);
                    return Boolean.TRUE;
                }
            } catch (Exception e) {
                return false;
            }
        } else {
            behaviorEntity1.setFavor(1);
            behaviorRepository.save(behaviorEntity1);
            return Boolean.TRUE;
        }
    }

    private BroadcastBehaviorEntity getBehaviorEntity(Integer bid , Integer userId) {
        BroadcastBehaviorEntity behaviorEntity = behaviorRepository.findByUserIdAndBroadcastId(userId, bid);
        return Optional.ofNullable(behaviorEntity).orElse(null);
    }

    @Override
    public Boolean createShit(Integer bid , Integer userId) {
//        BroadcastBehaviorEntity behaviorEntity1 = getBehaviorEntity(bid , userId);
//
//        if (behaviorEntity1 == null) {
//            BroadcastBehaviorEntity behaviorEntity = new BroadcastBehaviorEntity();
//            behaviorEntity.setShit(1);
//            behaviorEntity.setUserId(userId);
//            behaviorEntity.setBroadcastId(bid);
//            try {
//                behaviorRepository.save(behaviorEntity);
//                return true;
//            } catch (Exception e) {
//                return false;
//            }
//        } else {
//            System.out.println(behaviorEntity1.toString());
//        }
        return false;
    }
}
