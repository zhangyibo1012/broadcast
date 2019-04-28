package cn.orgtec.hix.broadcast.constant.enums;

import cn.hutool.core.util.EnumUtil;
import cn.orgtec.hix.broadcast.constant.BroadcastCommentAttachmentType;

import java.util.LinkedHashMap;

/**
 * <p>Test.java此类用于</p>
 * <p>@author zyb <p>
 * <p>@date 2019/03/29 <p>
 * <p>@remark:</p>
 */
public class Test {
    public static void main(String[] args) {

        LinkedHashMap<String, BroadcastCommentAttachmentType> enumMap = EnumUtil.getEnumMap(BroadcastCommentAttachmentType.class);
//        enumMap.forEach((k, v) -> System.out.println("key:value = " + k + ":" + v.getCode()));

//        enumMap.forEach((k ,v) ->{
//            if (v.getCode() == 1){
//                System.out.println(k);
//            }
//        });

    }
}
