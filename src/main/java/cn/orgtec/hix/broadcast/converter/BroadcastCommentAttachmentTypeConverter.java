package cn.orgtec.hix.broadcast.converter;


import cn.orgtec.hix.broadcast.constant.BroadcastCommentAttachmentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * BroadcastCommentAttachmentType 转换器
 *
 * @author Baiyang Peng
 * @date 2019/03/18
 */
@Converter(autoApply = true)
public class BroadcastCommentAttachmentTypeConverter implements AttributeConverter<BroadcastCommentAttachmentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BroadcastCommentAttachmentType attribute) {
        return attribute.toValue();
    }

    @Override
    public BroadcastCommentAttachmentType convertToEntityAttribute(Integer dbData) {
        return BroadcastCommentAttachmentType.forValue(dbData);
    }

}
