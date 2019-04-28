package cn.orgtec.hix.broadcast.repository;

import cn.orgtec.hix.broadcast.entity.BroadcastRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 广播
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastRewardRepository extends JpaRepository<BroadcastRewardEntity, Long>,
        JpaSpecificationExecutor<BroadcastRewardEntity> {

    /**
     * 查询该广播的打赏总额
     *
     * @param bid 广播id
     * @return amount
     */
    @Query("SELECT SUM(p.amount) FROM BroadcastRewardEntity p where p.broadcastId = :bid ")
    Long getAmountSum(@Param("bid") Integer bid);

    /**
     * 查询该广播的打赏总人数
     *
     * @param bid 广播id
     * @return 打赏的总人数
     */
    @Query("select distinct  (t.giverId) from BroadcastRewardEntity t where t.broadcastId = :bid ")
    List<BroadcastRewardEntity> getNop(@Param("bid") Integer bid);


}
