package cn.orgtec.hix.broadcast.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 广播附件类型
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
public enum BroadcastAttachmentType {
    /**
     * 图片
     */
    IMAGE(1),
    /**
     * 短视频
     */
    VIDEO(2);

    private final int value;

    BroadcastAttachmentType(int value) {
        this.value = value;
    }

    @JsonCreator
    public static BroadcastAttachmentType forValue(int value) {
        switch (value) {
            case 1:
                return IMAGE;
            case 2:
                return VIDEO;
            default:
                throw new IllegalArgumentException("未知的BroadcastAttachmentType类型：" + value);
        }
    }

    @JsonValue
    public int toValue() {
        return this.value;
    }
}
