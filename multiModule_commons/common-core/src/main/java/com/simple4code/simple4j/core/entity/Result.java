package com.simple4code.simple4j.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 * {
 * success ：是否成功
 * code    ：返回码
 * message ：返回信息
 * //返回数据
 * data：  ：{
 * <p>
 * }
 * }
 */
@Data
@NoArgsConstructor
@ApiModel("api通用返回数据")
public class Result {
    @ApiModelProperty("是否成功")
    private boolean success;
    @ApiModelProperty("返回码")
    private Integer code;
    @ApiModelProperty("提示信息,供报错时使用")
    private String message;
    @ApiModelProperty("返回的数据")
    private Object data;

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR() {
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }
}
