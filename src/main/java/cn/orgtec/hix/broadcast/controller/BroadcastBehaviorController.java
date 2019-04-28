package cn.orgtec.hix.broadcast.controller;

import cn.orgtec.hix.broadcast.constant.Result;
import cn.orgtec.hix.broadcast.service.BroadcastBehaviorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>BroadcastBehaviorController.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/01 <p>
 * <p>@remark:</p>
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/brd")
public class BroadcastBehaviorController {

    private final BroadcastBehaviorService broadcastBehaviorService;

    @PutMapping(value = "/broadcasts/{bid}/favor/{userId}")
    public Result createFavor(@PathVariable Integer bid , @PathVariable Integer userId) {
        Boolean result = broadcastBehaviorService.createFavor(bid , userId);
        return result ? Result.success("已赞") : Result.fail("赞失败");
    }

    @PutMapping(value = "/broadcasts/{bid}/shit/{userId}")
    public Result createShit(@PathVariable Integer bid , @PathVariable Integer userId) {
        Boolean result = broadcastBehaviorService.createShit(bid ,userId);
        return result ? Result.success("已屎") : Result.fail("屎失败");
    }
}
