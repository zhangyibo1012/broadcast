package cn.orgtec.hix.broadcast.data;

import lombok.Data;

/**
 * <p>BroadcastCommentRequestData.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/29 <p>
 * <p>@remark:</p>
 */
@Data
public class BroadcastCommentRequestData {


    private Integer uid;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论附件
     */
    private String attachment;

    /**
     * 评论附件类型  0 text 1音频 2图片 3表情
     */
    private Integer attachmentType;


}
