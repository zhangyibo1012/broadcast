package cn.orgtec.hix.broadcast.repository;


import cn.orgtec.hix.broadcast.entity.SystemRecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 广播行为
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface SystemRecommendRepository extends JpaRepository<SystemRecommendEntity, Long> {


    /**
     *  查询最新发布的广播
     *
     * @param uid  当前操作用户id
     * @return      List<BroadcastWeightEntity>
     */
    @Query(value = "SELECT *  FROM system_recommend where handler_user_id = :uid " +
            "and timestamp > 1 ORDER BY timestamp DESC ", nativeQuery = true)
    List<SystemRecommendEntity> findNewBroadcast( @Param("uid") Integer uid );

    /**
     *   根据广播 id 和 操作者用户 id 更新时间戳
     *
     * @param broadcastId       广播 id
     * @param handlerUserId     当前用户 id
     * @return                  int
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update SystemRecommendEntity  sys set sys.timestamp = 0  where  sys.broadcastId = :broadcastId and sys.handlerUserId = :handlerUserId")
    int updateTimestamp(@Param("broadcastId") Long broadcastId ,@Param("handlerUserId") Integer handlerUserId) ;

    /**
     *  根据操作者 id 和发布者 id 按照总亲密度降序
     *
     * @param handlerUserId     操作者 id
     * @param publishUserId     发布者 id
     * @return                  SystemRecommendEntity
     */
    SystemRecommendEntity findFirstByHandlerUserIdAndPublishUserIdOrderBySumIntimacyDesc(
            Integer handlerUserId , Integer publishUserId
    );


    /**
     *
     *
     * @param handlerUserId     操作者 id
     * @param publishUserId     发布者 id
     * @return                   List<SystemRecommendEntity>
     */

    /**
     *  根据操作者 id 查询总亲密度降序排列
     *
     * @param handlerUserId     操作者 id
     * @return                  List<SystemRecommendEntity>
     */
    List<SystemRecommendEntity> findTop3ByHandlerUserIdOrderBySumIntimacyDesc( Integer handlerUserId );


    /**
     *  根据广播id 和用户id 查询推荐表
     *
     * @param broadcastId  广播 id
     * @param userId       用户 id
     * @return             SystemRecommendEntity
     */
    SystemRecommendEntity findByBroadcastIdAndHandlerUserId(Long broadcastId, Integer userId);


    /**
     * 根据广播 id 查询信息
     *
     * @param broadcastId
     * @return findByBroadcastId
     */
    SystemRecommendEntity findDistinctByBroadcastIdAndHandlerUserId(Long broadcastId ,Integer userId);

    /**
     *  根据发布者 id 查询该用户的广播
     *
     * @return    List<SystemRecommendRepository>
     */
    List<SystemRecommendEntity> findByPublishUserIdOrderBySumIntimacyDesc( Integer publishUserId);


}
