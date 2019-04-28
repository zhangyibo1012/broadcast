package cn.orgtec.hix.broadcast.dto;

import cn.orgtec.hix.broadcast.data.Location;
import lombok.Data;

/**
 * <p>BroadcastDTO.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/01 <p>
 * <p>@remark:</p>
 */
@Data
public class BroadcastDTO {

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

    private Location location;

    /**
     * 用户ID
     */
    private Integer uid;


}
