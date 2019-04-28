package cn.orgtec.hix.broadcast.repository;

import cn.orgtec.hix.broadcast.entity.BroadcastEntity;
import cn.orgtec.hix.broadcast.util.jtsexample.technique.LineStringSelfIntersections;
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
public interface BroadcastRepository extends JpaRepository<BroadcastEntity, Long>,
        JpaSpecificationExecutor<BroadcastEntity> {

    /**
     * 查询用户最后一次发布广播的时间
     *
     * @param uid
     * @return
     */
    @Query(value = "SELECT *  FROM broadcast where uid = :uid ORDER BY gmt_create DESC LIMIT 1", nativeQuery = true)
    BroadcastEntity findByLastBroadcast(@Param("uid") Integer uid);

    /**
     * 根据用户 id 查询用户发布的广播 id
     *
     * @param uid
     * @return
     */
    List<BroadcastEntity> findByUid(Integer uid);

//
//    /**
//     * select
//     * *,
//     * ST_Distance_Sphere((POINT(108.9498710632,34.2588125935)),
//     * (POINT(109.013388,32.715519)))
//     * from
//     * broadcast
//     * WHERE
//     * ST_Distance_Sphere ((POINT(108.9498710632,34.2588125935)), (POINT(109.013388,32.715519))) < 1000000 LIMIT 1
//     *
//     * @param nearby
//     * @param targetPoint
//     * @return
//     */
//    @Query(value = "SELECT *, ST_Distance_Sphere (POINT (108.9498710632,34.2588125935),(POINT (109.013388, 32.715519)))FROM broadcast WHERE\n" +
//            "ST_Distance_Sphere (POINT (108.9498710632,34.2588125935),(POINT (109.013388, 32.715519)) )< 175000 LIMIT 10 ", nativeQuery = true)
//
////    @Query(value = "SELECT *, ST_Distance_Sphere (?1,?2) FROM broadcast WHERE\n" +
////            "ST_Distance_Sphere (POINT (108.9498710632,34.2588125935),POINT (109.013388, 32.715519)) < 175000 LIMIT 10 ", nativeQuery = true)
//    List<BroadcastEntity> findNearyBroadcast(String nearby, String targetPoint);


}
