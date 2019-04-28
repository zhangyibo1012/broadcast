package cn.orgtec.hix.broadcast.service;

/**
 * 广播服务
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastRewardService {

    /**
     * 礼物打赏
     *
     * @param authentication 当前操作的用户
     * @param bid            广播id
     * @param giftId         礼物id
     * @return Boolean
     */
    Boolean giftReward(Long bid, Integer giftId, Integer userId);
}
