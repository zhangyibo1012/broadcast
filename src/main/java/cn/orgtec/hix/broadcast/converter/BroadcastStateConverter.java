package cn.orgtec.hix.broadcast.converter;


import cn.orgtec.hix.broadcast.constant.BroadcastState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * BroadcastState 转换器
 *
 * @author Baiyang Peng
 * @date 2019/03/18
 */
@Converter(autoApply = true)
public class BroadcastStateConverter implements AttributeConverter<BroadcastState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BroadcastState attribute) {
        return attribute.toValue();
    }

    @Override
    public BroadcastState convertToEntityAttribute(Integer dbData) {
        return BroadcastState.forValue(dbData);
    }
}
