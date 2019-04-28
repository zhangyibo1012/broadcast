package cn.orgtec.hix.broadcast.service;


import cn.orgtec.hix.broadcast.data.BroadcastSavingRequestData;
import cn.orgtec.hix.broadcast.dto.BroadcastDetail;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 广播服务
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastService {

    /**
     * 发布广播
     *
     * @param form 广播信息
     * @return Boolean
     */
    Boolean publishBroadcast(BroadcastSavingRequestData form);


    /**
     * 删除广播
     *
     * @param bid 广播id
     * @return Boolean
     */
    Boolean deleteBroadcastById(Long bid);

    /**
     * 查询广播详情
     *
     * @param bid 广播id
     * @return 广播详情
     */
    BroadcastDetail getBroadcastDetail(Integer bid);

    /**
     * 查询指定用户的广播
     *
     * @param uid       用户id
     * @param pageable  分页参数
     * @param timestamp 时间
     * @return List<BroadcastDetail>
     */
    List<BroadcastDetail> getUserBroadcast(Integer uid, Pageable pageable, Long timestamp);


    List<BroadcastDetail> getNearBroadcast(String nearby, Pageable pageable, Long timestamp);
}
