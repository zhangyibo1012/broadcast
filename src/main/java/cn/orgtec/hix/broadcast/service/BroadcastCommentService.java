package cn.orgtec.hix.broadcast.service;

import cn.orgtec.hix.broadcast.data.BroadcastCommentRequestData;
import cn.orgtec.hix.broadcast.entity.BroadcastCommentEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 广播评论服务
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastCommentService {

    /**
     * 发布pinglun
     *
     * @param form 广播信息
     * @return Boolean
     */
    Boolean publishComment(Long bid, BroadcastCommentRequestData form);

    /**
     * @param bid
     * @param cid
     * @param data
     * @return
     */
    Boolean publishCommentReply(Long bid, Long cid, Integer userId, BroadcastCommentRequestData data);

    /**
     * 获取指定广播评论列表
     *
     * @param bid
     * @param pageable
     * @return
     */
    List<BroadcastCommentEntity> getCommentsByBidInPage(Long bid, Pageable pageable);
}
