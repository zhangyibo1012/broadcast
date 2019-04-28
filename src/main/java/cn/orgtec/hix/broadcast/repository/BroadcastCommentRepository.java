package cn.orgtec.hix.broadcast.repository;


import cn.orgtec.hix.broadcast.entity.BroadcastCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 广播评论
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastCommentRepository extends JpaRepository<BroadcastCommentEntity, Long> {


//    BroadcastCommentEntity getByBroadcastIdAndAndReply(Integer broadcastId, Integer reply);
}
