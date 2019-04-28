package cn.orgtec.hix.broadcast.controller;

import cn.orgtec.hix.broadcast.constant.Result;
import cn.orgtec.hix.broadcast.data.BroadcastSavingRequestData;
import cn.orgtec.hix.broadcast.dto.BroadcastDetail;
import cn.orgtec.hix.broadcast.service.BroadcastService;
import lombok.AllArgsConstructor;
import org.apache.xmlbeans.impl.schema.SoapEncSchemaTypeSystem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广播控制器
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/brd")
public class BroadcastController {

    private final BroadcastService broadcastService;

    @PostMapping(value="/test")
    public String hashCode1(@RequestBody Test test) {
        return test.getName();
    }

    @PostMapping(value = "/user/broadcasts")
    public Result publishBroadcast(@RequestBody BroadcastSavingRequestData data) {
        Boolean result = broadcastService.publishBroadcast(data);
        return result ? Result.success("发布成功") : Result.fail("发布失败");
    }

//    @DeleteMapping(value = "/broadcasts/{bid}")
//    public Result deleteBroadcastById(@PathVariable Long bid) {
//        boolean result = broadcastService.deleteBroadcastById(bid);
//        return result ? Result.success("删除成功") : Result.fail("删除失败");
//    }
//
//    @DeleteMapping(value = "/user/broadcasts/{bid}")
//    public Result removeUserBroadcast(@PathVariable Long bid) {
//        boolean result = broadcastService.deleteBroadcastById(bid);
//        return result ? Result.success("删除成功") : Result.fail("删除失败");
//    }
//
//    @GetMapping(value = "/broadcasts/{bid}")
//    public Result<BroadcastDetail> getBroadcastDetail(@PathVariable Integer bid) {
//        BroadcastDetail broadcastDetail = broadcastService.getBroadcastDetail(bid);
//
//        return broadcastDetail == null ? new Result<>(new Throwable("查询失败")) :new Result<>(broadcastDetail);
//    }
//

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    @GetMapping(value = "/users/{uid}/broadcasts", params = {"page", "size"})
    public Result<List<BroadcastDetail>> getUserBroadcast(@PathVariable Integer uid,
                                                          @PageableDefault Pageable pageable,
                                                          Long timestamp) {
        return new Result<List<BroadcastDetail>>(broadcastService.getUserBroadcast(uid, pageable, timestamp));
//    }
//
//    @GetMapping(value = "/broadcasts", params = {"page", "size"})
//    public Result<List<BroadcastDetail>> getNearBroadcast(String nearby,
//                                                          @PageableDefault Pageable pageable,
//                                                          Long timestamp) {
//        return new Result<List<BroadcastDetail>>(broadcastService.getNearBroadcast(nearby, pageable, timestamp));
//    }

    }
}
