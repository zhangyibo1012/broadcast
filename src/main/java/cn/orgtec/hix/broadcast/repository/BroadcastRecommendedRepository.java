//package cn.orgtec.hix.broadcast.repository;
//
//import cn.orgtec.hix.broadcast.entity.BroadcastBehaviorId;
//import org.springframework.data.jpa.repository.JpaRepository;
//
///**
// * @author Yibo Zhang
// * @date 2019/04/16
// */
//public interface BroadcastRecommendedRepository extends JpaRepository<BroadcastRecommendEntity, BroadcastBehaviorId> {
//
//    /**
//     *  根据广播 id 和 用户 id 查询是否已经存在操作行为
//     * @param broadcastId  广播 id
//     * @param userId       用户 id
//     * @return             BroadcastRecommendEntity
//     */
//    BroadcastRecommendEntity findByBroadcastIdAndAndUserId(Integer broadcastId, Integer userId);
//}
