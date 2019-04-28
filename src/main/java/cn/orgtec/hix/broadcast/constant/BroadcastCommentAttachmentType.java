package cn.orgtec.hix.broadcast.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 广播评论附件类型
 *
 * @author Baiyang Peng
 * @date 2019/03/15
 */
public enum BroadcastCommentAttachmentType {
    /**
     * 文字
     */
    TEXT(0),
    /**
     * 音频
     */
    AUDIO(1),
    /**
     * 图片
     */
    IMAGE(2),
    /**
     * 表情
     */
    FACE(3);

    private final int value;

    BroadcastCommentAttachmentType(int value) {
        this.value = value;
    }

    @JsonCreator
    public static BroadcastCommentAttachmentType forValue(int value) {
        switch (value) {
            case 0:
                return TEXT;
            case 1:
                return AUDIO;
            case 2:
                return IMAGE;
            case 3:
                return FACE;
            default:
                throw new IllegalArgumentException("未知的BroadcastAttachmentType类型：" + value);
        }
    }

    @JsonValue
    public int toValue() {
        return this.value;
    }
}
