package ${cfg.packageRootPath}.common.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value = "结果Vo")
public class ResultVo {

    @ApiModelProperty(value = "状态码", dataType = "java.lang.String", example = "200")
    private String code = "200";
	private Boolean success = false;
	private String key;
	private String message = "未知错误";
	@ApiModelProperty(value = "扩展类",dataType = "java.util.Map")
	private Map<String, Object> extend = new HashMap<>();

	public ResultVo() {
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultVo(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultVo(String code, Boolean success, String key, String message) {
        this.code = code;
        this.success = success;
        this.key = key;
        this.message = message;
    }

    public ResultVo(String code, Boolean success, String key, String message, Map<String, Object> extend) {
        this.code = code;
        this.success = success;
        this.key = key;
        this.message = message;
        this.extend = extend;
    }

    public ResultVo(Boolean success, String key, String message) {
        this.success = success;
        this.key = key;
        this.message = message;
    }

    public void setErrorMessage(String message) {
        this.success = false;
        this.message = message;
    }

    public void setSuccessMessage(String message) {
        this.success = true;
        this.message = message;
    }

    public void setSuccessMessage(String message, String key) {
        this.setSuccessMessage(message);
        this.key = key;
    }

}
