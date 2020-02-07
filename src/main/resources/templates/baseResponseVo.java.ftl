package ${cfg.packageRootPath}.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@ApiModel(value = "BaseResponseVo对象")
public class BaseResponseVo<T> implements Serializable{

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 4893280118017319089L;

    /**
     * 成功码.
     */
    public static final String SUCCESS_CODE = "200";

    /**
     * 成功信息.
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 错误码.
     */
    public static final String ERROR_CODE = "500";

    /**
     * 错误信息.
     */
    public static final String ERROR_MESSAGE = "内部异常";

    /**
     * 执行是否成功
     */
    @ApiModelProperty(value = "执行是否成功")
    private boolean success;
    /**
     * 执行结果码
     */
    @ApiModelProperty(value = "执行结果码")
    private String code;
    /**
     * 响应携带消息
     */
    @ApiModelProperty(value = "响应携带消息")
    private String message;
    /**
     * 响应携带数据
     */
    @ApiModelProperty(value = "响应携带数据")
    private T data;
    /**
     * 响应绑定参数
     */
    @ApiModelProperty(value = "响应绑定参数")
    private Map<String, Object> bundle;

    public BaseResponseVo() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public BaseResponseVo(String code, String message) {
        this(code, message, null);
    }

    public BaseResponseVo(String code, String message, T data) {
        super();
        this.code(code).message(message).data(data);
        this.success = BaseResponseVo.SUCCESS_CODE.equals(this.code);
    }

    private BaseResponseVo code(String code) {
        this.setCode(code);
        return this;
    }

    private BaseResponseVo message(String message) {
        this.setMessage(message);
        return this;
    }

    public BaseResponseVo data(T data) {
        this.setData(data);
        return this;
    }
}
