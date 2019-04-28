package cn.orgtec.hix.broadcast.util;

import cn.hutool.core.convert.Convert;
import cn.orgtec.hix.broadcast.data.BroadcastRewardConstant;
import cn.orgtec.hix.broadcast.data.Coordinate;
import cn.orgtec.hix.broadcast.data.Location;
import cn.orgtec.hix.broadcast.dto.*;
import cn.orgtec.hix.broadcast.entity.BroadcastBehaviorEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastCommentEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastRewardEntity;
import cn.orgtec.hix.broadcast.repository.BroadcastBehaviorRepository;
import cn.orgtec.hix.broadcast.repository.BroadcastCommentRepository;
import cn.orgtec.hix.broadcast.repository.BroadcastRewardRepository;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yibo Zhang
 * @date 2019/04/17
 */
@Component
@AllArgsConstructor
public class CreateBroadcastDetail {

    private final BroadcastBehaviorRepository behaviorRepository;
    private final BroadcastCommentRepository commentRepository;
    private final BroadcastRewardRepository rewardRepository;

    /**
     * 构建广播详情实体
     *
     * @param bid             广播id
     * @param broadcastEntity 实体
     * @return BroadcastDetail
     */
    public BroadcastDetail getBroadcastDetail(Integer bid, BroadcastEntity broadcastEntity) {
      BroadcastDetail broadcastDetail = new BroadcastDetail();
        broadcastDetail.setId(broadcastEntity.getId());
        broadcastDetail.setAttachment(broadcastEntity.getAttachment());
        broadcastDetail.setAttachmentType(broadcastEntity.getAttachmentType().toValue());
        broadcastDetail.setContent(broadcastEntity.getContent());
        broadcastDetail.setUid(broadcastEntity.getUid());
        Location location = new Location();
        Point coordinate = broadcastEntity.getCoordinate();
        double x = coordinate.getX();
        double y = coordinate.getY();
        Coordinate coordinate1 = new Coordinate( Convert.toStr(x), Convert.toStr(y) );
        location.setCoordinate(coordinate1);
        location.setArea(broadcastEntity.getArea());
        location.setAddress(broadcastEntity.getAddress());
        broadcastDetail.setLocation(location);

        BroadcastBehaviorEntity behaviorEntity = new BroadcastBehaviorEntity();
        behaviorEntity.setFavor(BroadcastRewardConstant.FAVOR);
        behaviorEntity.setBroadcastId(bid);
        long countFavor = behaviorRepository.count(Example.of(behaviorEntity));
        Favor favor = new Favor();
        favor.setCount(countFavor);
        favor.setDid(bid);

        behaviorEntity = new BroadcastBehaviorEntity();
        behaviorEntity.setShit(BroadcastRewardConstant.SHIT);
        behaviorEntity.setBroadcastId(bid);
        long countShit = behaviorRepository.count(Example.of(behaviorEntity));
        Hate hate = new Hate();
        hate.setCount(countShit);
        hate.setDid(bid);


        BroadcastCommentEntity commentEntity = new BroadcastCommentEntity();
        commentEntity.setBroadcastId(bid);
        commentEntity.setReply(false);
        long countComment = commentRepository.count(Example.of(commentEntity));
//        BroadcastBehaviorEntity behaviorEntity = new BroadcastBehaviorEntity();
//        behaviorEntity = new BroadcastBehaviorEntity();
//        behaviorEntity.setComment(BroadcastRewardConstant.SHIT);
//        behaviorEntity.setBroadcastId(bid);
//        long countComment = behaviorRepository.count(Example.of(behaviorEntity));
        Comment comment = new Comment();
        comment.setCount(countComment);
        comment.setDid(bid);

        Reward reward = new Reward();
        Long amountSum = rewardRepository.getAmountSum(bid);
        reward.setAmount(Convert.toInt(amountSum));
        reward.setDid(bid);
        List<BroadcastRewardEntity> nop = rewardRepository.getNop(bid);
        reward.setNop(nop.size());

        Behavior behavior = new Behavior();
        behavior.setFavor(favor);
        behavior.setHate(hate);
        behavior.setComment(comment);
        behavior.setReward(reward);
        broadcastDetail.setBehavior(behavior);
        return broadcastDetail;
    }
}
