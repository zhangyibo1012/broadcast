package cn.orgtec.hix.broadcast.converter;


import cn.orgtec.hix.broadcast.constant.BroadcastAttachmentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * BroadcastAttachmentType 转换器
 *
 * @author Baiyang Peng
 * @date 2019/03/18
 */
@Converter(autoApply = true)
public class BroadcastAttachmentTypeConverter implements AttributeConverter<BroadcastAttachmentType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BroadcastAttachmentType attribute) {
        return attribute.toValue();
    }

    @Override
    public BroadcastAttachmentType convertToEntityAttribute(Integer dbData) {
        return BroadcastAttachmentType.forValue(dbData);
    }
}
