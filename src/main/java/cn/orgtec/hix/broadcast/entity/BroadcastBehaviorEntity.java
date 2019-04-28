package cn.orgtec.hix.broadcast.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 广播行为实体
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
@Entity
@Table(name = "broadcast_behavior")
@IdClass(value = BroadcastBehaviorId.class)
@Data
@EqualsAndHashCode(callSuper = true)
public class BroadcastBehaviorEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 广播ID
     */
    @Id
    @Column(
            name = "broadcast_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '广播ID'"
    )
    private Integer broadcastId;

    /**
     * 执行者ID
     */
    @Id
    @Column(
            name = "user_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '执行者ID'"
    )
    private Integer userId;

    /**
     * 赞
     */
    @Column(
            name = "favor",
            columnDefinition = "TINYINT(2) DEFAULT NULL COMMENT '赞'"
    )
    private Integer favor;

    /**
     * 屎
     */
    @Column(
            name = "shit",
            columnDefinition = "TINYINT(2) DEFAULT NULL COMMENT '赞'"
    )
    private Integer shit;

    /**
     * 评论
     */
    @Column(
            name = "comment",
            columnDefinition = "TINYINT(2) DEFAULT NULL COMMENT '评论'"
    )
    private Integer comment;

    /**
     * 打赏
     */
    @Column(
            name = "reward",
            columnDefinition = "TINYINT(2) DEFAULT NULL COMMENT '打赏'"
    )
    private Integer reward;

    /**
     * 发布者用户ID
     */
    @Column(
            name = "publish_user_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '操作者id'"
    )
    private Integer publishUserId;
}
