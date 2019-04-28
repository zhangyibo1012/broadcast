package cn.orgtec.hix.broadcast.data;

import cn.orgtec.hix.broadcast.dto.UserDynamic;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>BroadcastSavingRequestData.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/29 <p>
 * <p>@remark:</p>
 */
@Data
@ToString
public class BroadcastSavingRequestData extends UserDynamic implements Serializable {


    private static final long serialVersionUID = 811209796864643567L;

    /**
     * 广播内容
     */
    private String content;

    /**
     * 广播附件
     */
    private String attachment;

    /**
     * 广播附件类型  1 图片  2 短视频
     */
    private Integer attachmentType;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 坐标区域地址
     */
    private Location location;

}
