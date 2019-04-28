package cn.orgtec.hix.broadcast.dto;

import cn.orgtec.hix.broadcast.data.Location;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * <p>BroadcastDetail.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/01 <p>
 * <p>@remark:</p>
 */
@Data
public class BroadcastDetail {

    /**
     * 广播ID
     */
    private Long id;

    /**
     * 广播内容
     */
    private String content;

    /**
     * 广播附件
     */
    private String attachment;

    /**
     * 广播附件类型
     */
    private Integer attachmentType;

    /**
     * 位置
     */
    private Location location;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 行为操作
     */
    private Behavior behavior;

}










