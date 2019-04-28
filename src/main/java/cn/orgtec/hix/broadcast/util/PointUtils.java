package cn.orgtec.hix.broadcast.util;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * <p>PointUtils.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/29 <p>
 * <p>@remark: api 包下</p>
 */
@UtilityClass
public class PointUtils {

    /**
     * 获取POINT
     *
     * @param coordinates "20.000,108.000"
     * @return POINT(109.013388 32.715519)
     */
    public Point getPoint(String coordinates) {
        String[] split = coordinates.split(",");
        Coordinate coordinate = new Coordinate(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        return new GeometryFactory().createPoint(coordinate);
    }

    public String getPointStr(String coordinates) {
        String[] split = coordinates.split(",");
        return "POINT(" + Double.parseDouble(split[0]) + "," + Double.parseDouble(split[1]) + ")";
    }


    public static void main(String[] args) {
//        System.out.println(getPoint("20.000,100.23"));
//        int i = RandomUtil.randomInt(1, 7);
//        System.out.println(i);
//        // 2063597568
//        System.out.println(toLittleEndian(123));
        String str = "108.9498710632";
        String str2 = "34.2588125935";
        double aDouble = ByteBuffer.wrap(str.getBytes()).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        double aDouble2 = ByteBuffer.wrap(str2.getBytes()).order(ByteOrder.LITTLE_ENDIAN).getDouble();
        System.out.println(aDouble);
        System.out.println(aDouble2);

    }
}
