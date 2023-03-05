package ${cfg.packageRootPath}.common.exception;

import ${cfg.packageRootPath}.common.model.base.ConstantValue;

public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    protected String code;

    private static final long serialVersionUID = 6610083281801529147L;

    public BusinessException() {
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }

    public BusinessException(ConstantValue<String> errorCode, Object... args) {
        super(String.format(errorCode.name(), args));
        this.code = errorCode.value();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
