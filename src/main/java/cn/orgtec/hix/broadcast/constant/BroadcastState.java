package cn.orgtec.hix.broadcast.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 广播状态
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
public enum BroadcastState {
    /**
     * 待审核
     */
    PENDING(0),
    /**
     * 已发布
     */
    PUBLISHED(1),
    /**
     * 已关闭
     */
    CLOSED(2);

    private final int value;

    BroadcastState(int value) {
        this.value = value;
    }

    @JsonCreator
    public static BroadcastState forValue(int value) {
        switch (value) {
            case 0:
                return PENDING;
            case 1:
                return PUBLISHED;
            case 2:
                return CLOSED;
            default:
                throw new IllegalArgumentException("未知的BroadcastAttachmentType类型：" + value);
        }
    }

    @JsonValue
    public int toValue() {
        return this.value;
    }
}
