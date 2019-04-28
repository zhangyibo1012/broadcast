package cn.orgtec.hix.broadcast.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Baiyang Peng
 * @date 2019/01/23
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(
            name = "gmt_create",
            columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'",
            updatable = false
    )
    public LocalDateTime createTime;

    @LastModifiedDate
    @Column(
            name = "gmt_modified",
            columnDefinition = "datetime DEFAULT NULL COMMENT '修改时间'"
    )
    public LocalDateTime updateTime;
}
