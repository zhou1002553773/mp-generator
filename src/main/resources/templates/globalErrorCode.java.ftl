package ${cfg.packageRootPath}.common.constant;

import ${cfg.packageRootPath}.common.model.base.ConstantContainer;
import ${cfg.packageRootPath}.common.model.base.ConstantValue;

public class GlobalErrorCode {

    public static final ConstantContainer<String> GLOBAL_ERROR_CODE = new ConstantContainer<String>();
    public static final ConstantValue<String> GL9990400 = new ConstantValue<String>("400", "参数异常", GLOBAL_ERROR_CODE);
    public static final ConstantValue<String> GL9990403 = new ConstantValue<String>("403", "无权访问", GLOBAL_ERROR_CODE);
    public static final ConstantValue<String> GL9990404 = new ConstantValue<String>("404", "找不到指定资源", GLOBAL_ERROR_CODE);
    public static final ConstantValue<String> GL9990405 = new ConstantValue<String>("405", "不支持当前请求方法", GLOBAL_ERROR_CODE);
    public static final ConstantValue<String> GL9990415 = new ConstantValue<String>("415", "不支持当前媒体类型", GLOBAL_ERROR_CODE);
    public static final ConstantValue<String> GL9990500 = new ConstantValue<String>("500", "服务器繁忙，请稍后重试", GLOBAL_ERROR_CODE);
    public static final ConstantValue<String> GL9990666 = new ConstantValue<String>("666", "登陆失效", GLOBAL_ERROR_CODE);

}
