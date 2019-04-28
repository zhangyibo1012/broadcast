package cn.orgtec.hix.broadcast.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.orgtec.hix.broadcast.constant.BroadcastAttachmentType;
import cn.orgtec.hix.broadcast.data.BroadcastSavingRequestData;
import cn.orgtec.hix.broadcast.data.Location;
import cn.orgtec.hix.broadcast.dto.BroadcastDetail;
import cn.orgtec.hix.broadcast.entity.BroadcastEntity;
import cn.orgtec.hix.broadcast.entity.SystemRecommendEntity;
import cn.orgtec.hix.broadcast.repository.*;
import cn.orgtec.hix.broadcast.service.BroadcastService;
import cn.orgtec.hix.broadcast.util.DateConverter;
import cn.orgtec.hix.broadcast.util.PointUtils;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yibo Zhang
 * @date 2019/04/22
 */
@Service
@Slf4j
@AllArgsConstructor
public class BroadcastServiceImpl implements BroadcastService {


    private final BroadcastRepository broadcastRepository;
    private final BroadcastBehaviorRepository behaviorRepository;
    private final BroadcastCommentRepository commentRepository;
    private final BroadcastRewardRepository rewardRepository;
    private final EntityManager entityManager;
    private final SystemRecommendRepository recommendRepository;


    @Override
    public Boolean publishBroadcast(BroadcastSavingRequestData form) {

        // 根据用户 id 查询该用户最后一次发布广播的时间
        BroadcastEntity lastBroadcast = broadcastRepository.findByLastBroadcast(form.getUid());

        // 用户
        List<Integer> userId = getUserIds();
        if (null == lastBroadcast){
            final BroadcastEntity saveBroadcast = saveBroadcast(form);
            ss(form, userId, saveBroadcast);
            return true;
        }

        LocalDateTime createTime = lastBroadcast.getCreateTime();
        Date createDate = DateConverter.localDateTime2Date(createTime);
        Date nowDate = DateConverter.localDateTime2Date(LocalDateTime.now());
        if (DateUtil.between(createDate, nowDate, DateUnit.MINUTE) >= 3){
            final BroadcastEntity saveBroadcast = saveBroadcast(form);
            ss(form, userId, saveBroadcast);
            return  true;
        }
        return false;
    }

    private void ss(BroadcastSavingRequestData form, List<Integer> userId, BroadcastEntity saveBroadcast) {
        for (int i = 0; i < userId.size(); i++) {
            if (!form.getUid().equals(userId.get(i))) {
                SystemRecommendEntity recommendEntity = new SystemRecommendEntity();
                recommendEntity.setBroadcastId(saveBroadcast.getId());
                recommendEntity.setPublishUserId(form.getUid());
                recommendEntity.setHandlerUserId(userId.get(i));
                /**
                 * 类型权重
                 * 类型A    50
                 * 类型B    40
                 * 类型C    30
                 * 类型D    20
                 */
                recommendEntity.setTypeWeight(50);
//            广播权重100  80  60  40 20
                recommendEntity.setBroadcastWeight(100);
                recommendEntity.setTimestamp(System.currentTimeMillis());
                recommendEntity.setSumIntimacy(150);
                recommendRepository.saveAndFlush(recommendEntity);
            }
        }
    }


    @Override
    public Boolean deleteBroadcastById(Long bid) {
        return null;
    }

    @Override
    public BroadcastDetail getBroadcastDetail(Integer bid) {
        return null;
    }

    @Override
    public List<BroadcastDetail> getUserBroadcast(Integer uid, Pageable pageable, Long timestamp) {
        BroadcastEntity entity = new BroadcastEntity();
        entity.setUid(uid);
        entity.setAttachmentType(null);
        entity.setAddress(null);
        entity.setState(null);
        Example<BroadcastEntity> example =Example.of(entity);
        Page<BroadcastEntity> all = broadcastRepository.findAll(example, pageable);
        List<BroadcastEntity> content = all.getContent();
        System.out.println(content.size());


        return null;
    }

    @Override
    public List<BroadcastDetail> getNearBroadcast(String nearby, Pageable pageable, Long timestamp) {
        return null;
    }

    private BroadcastEntity saveBroadcast(BroadcastSavingRequestData form) {
        cn.orgtec.hix.broadcast.data.Coordinate coordinate = form.getLocation().getCoordinate();
        String coordinates = coordinate.getLongitude() + "," + coordinate.getLatitude();
        final Point point = PointUtils.getPoint(coordinates);
        Location location = form.getLocation();
        form.setCoordinate(point);
        BroadcastEntity entity = new BroadcastEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setAttachmentType(BroadcastAttachmentType.forValue(form.getAttachmentType()));
        entity.setAddress(location.getAddress());
        entity.setArea(location.getArea());
        BroadcastEntity saveBroadcastEntity = broadcastRepository.save(entity);
        return saveBroadcastEntity;
    }

    public static List<Integer> getUserIds() {
        List<Integer> userId = new ArrayList<>();
        userId.add(60000);
        userId.add(60001);
        userId.add(60002);
        userId.add(60003);
        userId.add(60004);
        userId.add(60005);
        userId.add(60006);
        userId.add(60007);
        userId.add(60008);
        userId.add(60009);
        return userId;
    }
}
