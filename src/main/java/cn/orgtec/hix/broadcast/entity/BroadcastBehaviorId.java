package cn.orgtec.hix.broadcast.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 广播行为联合主键
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
@Data
public class BroadcastBehaviorId implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 广播ID
     **/
    @Column(
            name = "broadcast_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '广播ID'"
    )
    @Id
    private Integer broadcastId;

    /**
     * 赞成者ID
     */
    @Column(
            name = "user_id",
            columnDefinition = "INT(2) UNSIGNED NOT NULL COMMENT '赞成者ID'"
    )
    @Id
    private Integer userId;
}
