package cn.orgtec.hix.broadcast.constant;//package cn.orgtec.hix.broadcast.constant;
//
//import javax.persistence.AttributeConverter;
//
//public class StatusAttributeConverter implements AttributeConverter<String, Integer> {
//
//
//
//    @Override
//
//    public Integer convertToDatabaseColumn(String status) {
//
//        try {
//
//            return Integer.parseInt(status);    //如果是数字，则直接返回（这里可以遍历StatusEnum的value来进一步验证）
//
//        } catch (NumberFormatException e) {
//
//            for (BroadcastCommentAttachmentType type : BroadcastCommentAttachmentType.values()) {    //如果不是数字，则通过StatusEnum来找到描述对应的数字
//
//                if (status.equals(type.getDescription())) {
//
//                    return type.getValue();
//
//                }
//
//            }
//
//        }
//
//        throw new RuntimeException("Unknown StatusEnum: " + status);    //如果StatusEnum里不存在代表数字或描述，则抛出异常
//
//    }
//
//
//
//    @Override
//
//    public String convertToEntityAttribute(Integer value) {
//
//        for (BroadcastCommentAttachmentType type : BroadcastCommentAttachmentType.values()) {    //将数字转换为描述
//
//            if (value.equals(type.getValue())) {
//
//                return type.getDescription();
//
//            }
//
//        }
//
//        throw new RuntimeException("Unknown database value: " + value);
//
//    }
//
//}