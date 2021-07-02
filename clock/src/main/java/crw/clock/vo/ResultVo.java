package crw.clock.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResultVo<T> {

    @ApiModelProperty(position = 1, value = "状态码")
    private int statusCode;
    @ApiModelProperty(position = 2, value = "信息描述")
    private String desc;
    @ApiModelProperty(position = 3, value = "数据")
    private T data;

    public ResultVo(){}

    public ResultVo(String desc) {
        this.desc = desc;
    }

    public ResultVo(int statusCode, String desc) {
        this.statusCode = statusCode;
        this.desc = desc;
    }

    public ResultVo(int statusCode, String desc, T data) {
        this.statusCode = statusCode;
        this.desc = desc;
        this.data = data;
    }
}
