package cn.orgtec.hix.broadcast.controller;

import cn.orgtec.hix.broadcast.constant.Result;
import cn.orgtec.hix.broadcast.dto.BroadcastDetail;
import cn.orgtec.hix.broadcast.service.SystemRecommendedService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 推荐广播控制器
 *
 * @author Yibo Zhang
 * @date 2019/04/15
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/brd")
public class RecommendedBroadcastController {

    private SystemRecommendedService broadcastRecommendedService;

    /**
     *
     * @param userId 用户 id
     * @return       广播详情
     */
    @GetMapping(value = "/user/recommended/{userId}")
    public Result<List<BroadcastDetail>> getRecommendedBroadcast(@PathVariable("userId") Integer userId){
        List<BroadcastDetail> recommendedBroadcasts = broadcastRecommendedService.getRecommendedBroadcasts(userId);
        return new Result<>(recommendedBroadcasts);
    }
}
