package cn.orgtec.hix.broadcast.controller;

import cn.orgtec.hix.broadcast.constant.Result;
import cn.orgtec.hix.broadcast.data.BroadcastCommentRequestData;
import cn.orgtec.hix.broadcast.entity.BroadcastCommentEntity;
import cn.orgtec.hix.broadcast.service.BroadcastCommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广播评论控制器
 *
 * 6XXXX用户发布广播
 * 7XXXX用户评论广播
 *
 * @author Baiyang Peng
 * @date 2019/03/25
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/brd")
public class BroadcastCommentController {

    private final BroadcastCommentService broadcastCommentService;

    @PostMapping(value = "/broadcasts/{bid}/comments")
    public Result publishComment(@PathVariable Long bid, @RequestBody BroadcastCommentRequestData data) {
        final Boolean result = broadcastCommentService.publishComment(bid, data);
        return result ? Result.success("发布成功") : Result.fail("发布失败");
    }

    @PostMapping(value = "/broadcasts/{bid}/comments/{cid}/replies/{userId}")
    public Result publishCommentReply(@PathVariable(value = "bid") Long bid,
                                      @PathVariable(value = "cid") Long cid,
                                      @PathVariable(value = "cid") Integer userId,
                                      @RequestBody BroadcastCommentRequestData data) {
        Boolean result = broadcastCommentService.publishCommentReply(bid, cid, userId ,data);
        return result ? Result.success("发布成功") : Result.fail("发布失败");
    }
//
//
//    @GetMapping(value = "/broadcasts/{bid}/comments", params = {"page", "size"})
//    public Result<List<BroadcastCommentEntity>> getCommentsByBidInPage(Pageable pageable,
//                                                                       @PathVariable Long bid) {
//        return new Result<>(broadcastCommentService.getCommentsByBidInPage(bid, pageable));
//    }
}
