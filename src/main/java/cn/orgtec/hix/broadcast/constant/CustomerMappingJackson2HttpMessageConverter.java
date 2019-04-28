package cn.orgtec.hix.broadcast.constant;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

/**
 * 处理@RequestBody注解为String的情况，只支持接收单个参数的情况
 * Created by test
 * Date:2017/1/4
 * Time:17:33
 * @author  xxx
 */
@Component
public class CustomerMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Class<?> deserializationClazz = getClazz(clazz);
        Object param = super.readInternal(deserializationClazz, inputMessage);
        return getTrueObject(clazz, param);

    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        Type deserializationType = getType(type);
        Object param = super.read(deserializationType, contextClass, inputMessage);
        return getTrueObject(type, param);
    }

    /**
     * 通过返回参数类型决定是否处理参数，如果是String类型的参数，将解析后的HashMap里的值返回（只支持单个参数）
     *
     * @param type  返回参数类型
     * @param param 参数值
     * @return 实际参数值
     */
    private Object getTrueObject(Type type, Object param) {
        if (type == Integer.class) {
            Object backParam = null;
            if (param instanceof LinkedHashMap) {
                LinkedHashMap paramMap = (LinkedHashMap) param;
                if (paramMap.size() == 1) {
                    backParam = paramMap.get(paramMap.keySet().iterator().next());
                }
            }
            param = backParam;
        }
        return param;
    }

    /**
     * 获取解析参数用的Type
     *
     * @param type 参数类型
     * @return Type
     */
    private Type getType(Type type) {
        Type deserializationClazz;
        if (type == Integer.class) {
            //jackson不支持String默认用LinkedHashMap
            deserializationClazz = LinkedHashMap.class;
        } else {
            deserializationClazz = type;
        }
        return deserializationClazz;
    }

    /**
     * 获取解析参数用的Type
     *
     * @param clazz 参数类型
     * @return      Class
     */
    private Class<?> getClazz(Class<?> clazz) {
        Class<?> deserializationClazz;
        if (clazz == Integer.class) {
            //jackson不支持String默认用LinkedHashMap
            deserializationClazz = LinkedHashMap.class;
        } else {
            deserializationClazz = clazz;
        }
        return deserializationClazz;
    }
}