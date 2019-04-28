package cn.orgtec.hix.broadcast.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 广播打赏实体
 * 5.7.25
 * https://blog.csdn.net/qq_30162219/article/details/87768612
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
@Entity
@Table(name = "broadcast_reward")
@Data
@EqualsAndHashCode(callSuper = true)
public class BroadcastRewardEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 广播ID
     */
    @Column(
            name = "broadcast_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '广播ID'"
    )
    private Integer broadcastId;

    /**
     * 用户ID
     */
    @Column(
            name = "user_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '用户ID'"
    )
    private Integer userId;

    /**
     * 打赏者ID
     */
    @Column(
            name = "giver_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '用户ID'"
    )
    private Integer giverId;

    /**
     * 打赏类型
     */
    @Column(
            name = "type",
            columnDefinition = "TINYINT(2) UNSIGNED NOT NULL COMMENT '打赏方式'"
    )
    private Integer type;

    /**
     * 打赏金额
     */
    @Column(
            name = "amount",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '打赏金额'"
    )
    private Integer amount = 0;

    /**
     * 附加内容
     */
    @Column(
            name = "addition",
            columnDefinition = "JSON COMMENT '附加内容'"
    )
    private String addition;
}
