package cn.orgtec.hix.broadcast.dto;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

/**
 * 用户动态信息
 *
 * @author Baiyang Peng
 * @date 2019/02/26
 */
@Data
public class UserDynamic {

    /**
     * 坐标
     */
    private Point coordinate;

    /**
     * 所在区域
     */
    private String area;

    /**
     * 所在详细地址
     */
    private String address;

    /**
     * 设备
     */
    private String device;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * IP信息
     */
    private String ip;
}
