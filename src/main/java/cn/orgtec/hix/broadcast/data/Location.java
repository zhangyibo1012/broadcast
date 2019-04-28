package cn.orgtec.hix.broadcast.data;

import lombok.Data;


/**
 * <p>Location.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/29 <p>
 * <p>@remark:</p>
 */
@Data
public class Location {
    /**
     * 坐标
     */
    private Coordinate coordinate;


    /**
     * 所在区域
     */
    private String area;

    /**
     * 所在详细地址
     */
    private String address;

}
