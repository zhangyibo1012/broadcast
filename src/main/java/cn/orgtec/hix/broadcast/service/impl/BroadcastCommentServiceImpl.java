package cn.orgtec.hix.broadcast.service.impl;

import cn.hutool.core.convert.Convert;
import cn.orgtec.hix.broadcast.constant.BroadcastCommentAttachmentType;
import cn.orgtec.hix.broadcast.data.BroadcastCommentRequestData;
import cn.orgtec.hix.broadcast.entity.BroadcastBehaviorEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastCommentEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastEntity;
import cn.orgtec.hix.broadcast.entity.SystemRecommendEntity;
import cn.orgtec.hix.broadcast.repository.BroadcastBehaviorRepository;
import cn.orgtec.hix.broadcast.repository.BroadcastCommentRepository;
import cn.orgtec.hix.broadcast.repository.BroadcastRepository;
import cn.orgtec.hix.broadcast.repository.SystemRecommendRepository;
import cn.orgtec.hix.broadcast.service.BroadcastCommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Yibo Zhang
 * @date 2019/04/22
 */
@Service
@Slf4j
@AllArgsConstructor
public class BroadcastCommentServiceImpl implements BroadcastCommentService {


    private final BroadcastCommentRepository broadcastCommentRepository;
    private final BroadcastRepository broadcastRepository;
    private final BroadcastBehaviorRepository broadcastBehaviorRepository;
    private final SystemRecommendRepository systemRecommendRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Boolean publishComment(Long bid, BroadcastCommentRequestData form) {

        // 查询广播 id 是否存在
        Optional<BroadcastEntity> broadcastEntity = broadcastRepository.findById(bid);
        if (!broadcastEntity.isPresent()) {
            // 查询不到广播
            return Boolean.FALSE;
        }
        // 保存评论实体
        BroadcastCommentEntity saveBroadcastComment = new BroadcastCommentEntity();
        saveBroadcastComment.setUid(form.getUid());
        saveBroadcastComment.setBroadcastId(bid.intValue());
        saveBroadcastComment.setAttachmentType(BroadcastCommentAttachmentType.forValue(form.getAttachmentType()));
        saveBroadcastComment.setRepliedUserId(broadcastRepository.findById(bid).get().getUid());
        saveBroadcastComment.setContent(form.getContent());
        saveBroadcastComment.setAttachment(form.getAttachment());
        try {
            broadcastCommentRepository.save(saveBroadcastComment);
            // 保存广播行为评论数量
            BroadcastBehaviorEntity behaviorEntity = broadcastBehaviorRepository.findByUserIdAndBroadcastId(form.getUid(), Convert.toInt(bid));
            if (null == behaviorEntity){
                BroadcastBehaviorEntity saveBehaviorEntity = new BroadcastBehaviorEntity();
                Integer publishUserId = broadcastRepository.findById(bid).get().getUid();
                saveBehaviorEntity.setPublishUserId(publishUserId);
                saveBehaviorEntity.setComment(1);
                saveBehaviorEntity.setBroadcastId(Convert.toInt(bid));
                saveBehaviorEntity.setUserId(form.getUid());
                broadcastBehaviorRepository.save(saveBehaviorEntity);
            } else {
                // 更新广播行为评论数量
                assert behaviorEntity != null;
                behaviorEntity.setComment(behaviorEntity.getComment() + 1);
                broadcastBehaviorRepository.saveAndFlush(behaviorEntity);
            }


            // 保存亲密度
            // 根据当前用户id查找推荐表
            SystemRecommendEntity recommendEntity = systemRecommendRepository.findByBroadcastIdAndHandlerUserId(bid, form.getUid());
            Integer intimacy = recommendEntity.getIntimacy();
            if (null == intimacy){
                recommendEntity.setIntimacy(5);
                recommendEntity.setSumIntimacy(recommendEntity.getSumIntimacy() + 5);
                systemRecommendRepository.saveAndFlush(recommendEntity);
                return Boolean.TRUE;
            }else {
                recommendEntity.setIntimacy(recommendEntity.getIntimacy() + 5);
                recommendEntity.setSumIntimacy(recommendEntity.getSumIntimacy() + 5);
                systemRecommendRepository.saveAndFlush(recommendEntity);
                return Boolean.TRUE;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean publishCommentReply(Long bid, Long cid, Integer userId, BroadcastCommentRequestData form) {
        Optional<BroadcastCommentEntity> byId = broadcastCommentRepository.findById(cid);
        if (!byId.isPresent()) {
            return false;
        }
        BroadcastCommentEntity entity = new BroadcastCommentEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setReply(true);
        entity.setBroadcastId(bid.intValue());
        entity.setRepliedCommentId(cid.intValue());
        entity.setRepliedUserId(broadcastRepository.findById(bid.longValue()).get().getUid());
        entity.setAttachmentType(BroadcastCommentAttachmentType.forValue(form.getAttachmentType()));
        entity.setUid(userId);
        try {
            broadcastCommentRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<BroadcastCommentEntity> getCommentsByBidInPage(Long bid, Pageable pageable) {
        return null;
    }
}
