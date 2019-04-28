package cn.orgtec.hix.broadcast.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>SystemRecommendEntity.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/04/21 <p>
 * <p>@remark:</p>
 */
@Entity
@Table(name = "system_recommend")
@Data
@ToString(callSuper = true)
public class SystemRecommendEntity implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户ID
     */
    @Column(
            name = "handler_user_id",
            columnDefinition = "INT(11) DEFAULT NULL COMMENT '操作者id'"
    )
    private Integer handlerUserId;

    /**
     * 发布者用户ID
     */
    @Column(
            name = "publish_user_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '操作者id'"
    )
    private Integer publishUserId;

    @Column(
            name = "broadcast_id",
            columnDefinition = "BIGINT(22) UNSIGNED NOT NULL COMMENT '广播ID'"
    )
    private Long broadcastId;

    /**
     * 亲密度
     */
    @Column(
            name = "intimacy",
            columnDefinition = "INT(11) DEFAULT 0 COMMENT '亲密度'"
    )
    private Integer intimacy;

    /**
     * 类型权重
     * 类型A    50
     * 类型B    40
     * 类型C    30
     * 类型D    20
     */
    @Column(
            name = "type_weight",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '类型权重'"
    )
    private Integer typeWeight;

    /**
     *  广播权重
     *  100  80  60  40 20
     */
    @Column(
            name = "broadcast_weight",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '广播权重'"
    )
    private Integer broadcastWeight;


    /**
     *  时间戳
     */
    @Column(
            name = "timestamp",
            columnDefinition = "BIGINT(30) UNSIGNED NOT NULL COMMENT '类型权重'"
    )
    private Long  timestamp;

    /**
     * 亲密度
     */
    @Column(
            name = "sum_intimacy",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '总亲密度'"
    )
    private Integer sumIntimacy;
}
