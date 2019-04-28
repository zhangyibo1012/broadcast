package cn.orgtec.hix.broadcast.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息结果
 *
 * @param <T>
 * @author Baiyang Peng
 * @date 2019/01/03
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    public static final Result SUCCESS = new Result();
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    @Builder.Default
    private int code = CommonConstants.SUCCESS;

    @Getter
    @Setter
    @Builder.Default
    private String message = "SUCCESS";


    @Getter
    @Setter
    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
    }

    public Result(T data, String msg) {
        super();
        this.data = data;
        this.message = msg;
    }

    public Result(Throwable e) {
        super();
        this.message = e.getMessage();
        this.code = CommonConstants.FAIL;
    }

    public static Result success(String message) {
        return new ResultBuilder().code(CommonConstants.SUCCESS).message(message).build();
    }

    public static Result fail(String message) {
        return new ResultBuilder().code(CommonConstants.FAIL).message(message).build();
    }

    @JsonIgnore
    public Boolean isSuccess() {
        return code == CommonConstants.SUCCESS;
    }
}
