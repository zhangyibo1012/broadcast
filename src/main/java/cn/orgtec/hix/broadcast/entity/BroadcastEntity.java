package cn.orgtec.hix.broadcast.entity;

import cn.orgtec.hix.broadcast.constant.BroadcastAttachmentType;
import cn.orgtec.hix.broadcast.constant.BroadcastState;
import cn.orgtec.hix.broadcast.converter.BroadcastAttachmentTypeConverter;
import cn.orgtec.hix.broadcast.converter.BroadcastStateConverter;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 广播实体
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
@Entity
@Table(name = "broadcast")
@SQLDelete(sql = "UPDATE broadcast SET is_deleted = 1 where id = ?")
@Where(clause = "is_deleted != 1")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BroadcastEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 广播ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(
            name = "uid",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '用户ID'"
    )
    private Integer uid;

    /**
     * 广播附件类型
     */
    @Column(
            name = "attachment_type",
            columnDefinition = "TINYINT(2) UNSIGNED NOT NULL "
    )
    @Convert(converter = BroadcastAttachmentTypeConverter.class)
    private BroadcastAttachmentType attachmentType = BroadcastAttachmentType.IMAGE;

    /**
     * 广播内容
     */
    @Column(
            name = "content",
            columnDefinition = "TEXT  COMMENT '广播内容'"
    )
    private String content;

    /**
     * 广播附件
     */
    @Column(
            name = "attachment",
            columnDefinition = "TEXT  COMMENT '广播附件'"
    )
    private String attachment;

    /**
     * 定位坐标
     */
    @Column(
            name = "coordinate",
            columnDefinition = "GEOMETRY DEFAULT NULL COMMENT '定位坐标'"
    )
    private Point coordinate;

    /**
     * 定位区域
     */
    @Column(
            name = "area",
            columnDefinition = "VARCHAR(128) DEFAULT NULL COMMENT '定位区域'"
    )
    private String area;

    /**
     * 定位地址
     */
    @Column(
            name = "address",
            columnDefinition = "VARCHAR(255) DEFAULT NULL COMMENT '定位地址'"
    )
    private String address = "";

    /**
     * 广播状态
     */
    @Column(
            name = "state",
            columnDefinition = "TINYINT(2) UNSIGNED NOT NULL COMMENT '广播状态'"
    )
    @Convert(converter = BroadcastStateConverter.class)
    private BroadcastState state = BroadcastState.PENDING;

    /**
     * 是否删除  0未删除  1已删除
     */
    @Column(
            name = "is_deleted",
            columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '是否删除'"
    )
    private Boolean deleted = Boolean.FALSE;
}
