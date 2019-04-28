package cn.orgtec.hix.broadcast.service;

import cn.orgtec.hix.broadcast.dto.BroadcastDetail;

import java.util.List;
import java.util.Set;

/**
 * @author Yibo Zhang
 * @date 2019/04/22
 * 系统推荐
 */
public interface SystemRecommendedService {

    /**
     *  根据用户 id 进行推荐
     * @param userId  用户 id
     * @return        List<BroadcastDetail>
     */
    List<BroadcastDetail> getRecommendedBroadcasts(Integer userId);
}
