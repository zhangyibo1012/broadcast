package cn.orgtec.hix.broadcast.service;

/**
 * 广播行为服务
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
public interface BroadcastBehaviorService {


    Boolean createFavor(Integer bid, Integer userId);


    Boolean createShit(Integer bid, Integer userId);
}
