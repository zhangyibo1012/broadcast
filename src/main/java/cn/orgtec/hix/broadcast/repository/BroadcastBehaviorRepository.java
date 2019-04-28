package cn.orgtec.hix.broadcast.repository;


import cn.orgtec.hix.broadcast.entity.BroadcastBehaviorEntity;
import cn.orgtec.hix.broadcast.entity.BroadcastBehaviorId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 广播行为
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastBehaviorRepository extends JpaRepository<BroadcastBehaviorEntity, BroadcastBehaviorId> {

    /**
     * 根据操作者 id 和发布者 id 查询点赞的广播 id
     *
     * @param userId            操作者 id
     * @param publishUserId     发布者 id
     * @return                  List<BroadcastBehaviorEntity>
     */
    List<BroadcastBehaviorEntity> findByUserIdAndPublishUserId(Integer userId ,Integer publishUserId);

    /**
     * 根据用户 id 和广播 id 查询 BroadcastBehaviorEntity
     *
     * @param userId 用户 id
     * @param bid    广播 id
     * @return BroadcastBehaviorEntity
     */
    BroadcastBehaviorEntity findByUserIdAndBroadcastId(Integer userId, Integer bid);
}
