package cn.orgtec.hix.broadcast.entity;

import cn.orgtec.hix.broadcast.constant.BroadcastCommentAttachmentType;
import cn.orgtec.hix.broadcast.converter.BroadcastCommentAttachmentTypeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 广播评论实体
 *
 * @author Baiyang Peng
 * @date 2019/03/20
 */
@Entity
@Table(name = "broadcast_comment")
@SQLDelete(sql = "UPDATE broadcast_comment SET is_deleted = 1 where id = ?")
@Where(clause = "is_deleted != 1")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BroadcastCommentEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
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
    private Integer uid;

    /**
     * 评论附件类型
     */
    /**
     * 评论附件类型
     */
    @Column(
            name = "attachment_type",
            columnDefinition = "TINYINT(2) UNSIGNED NOT NULL COMMENT '评论附件类型' "
    )
    @Convert(converter = BroadcastCommentAttachmentTypeConverter.class)
    private BroadcastCommentAttachmentType attachmentType;

    /**
     * 评论内容
     */
    @Column(
            name = "content",
            columnDefinition = "TEXT  COMMENT '评论内容'"
    )
    private String content;

    /**
     * 评论附件
     */
    @Column(
            name = "attachment",
            columnDefinition = " TEXT COMMENT '评论附件'"
    )
    private String attachment;

    /**
     * 是否回复
     */
    @Column(
            name = "is_reply",
            columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '是否回复'"
    )
    private Boolean reply = false;

    /**
     * 被回复的评论ID
     */
    @Column(
            name = "replied_comment_id",
            columnDefinition = "INT(11) UNSIGNED  COMMENT '被回复的评论ID'"
    )
    private Integer repliedCommentId;

    /**
     * 被回复的用户ID
     */
    @Column(
            name = "replied_user_id",
            columnDefinition = "INT(11) UNSIGNED NOT NULL COMMENT '被回复的用户ID'"
    )
    private Integer repliedUserId;

    /**
     * 是否删除
     */
    @Column(
            name = "is_deleted",
            columnDefinition = "TINYINT(1) UNSIGNED DEFAULT 0 COMMENT '是否删除'"
    )
    private Boolean deleted = Boolean.FALSE;
}
