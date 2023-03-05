package ${cfg.packageRootPath}.common.constant;

import ${cfg.packageRootPath}.common.model.base.ConstantContainer;
import ${cfg.packageRootPath}.common.model.base.ConstantValue;

public class CommonConstant {

    public static final ConstantContainer<Integer> SYSTEM_STATUS = new ConstantContainer<Integer>();
    public static final ConstantValue<Integer> SYSTEM_STATUS_LOSE_EFFECT = new ConstantValue<Integer>(0, "失效", SYSTEM_STATUS);
    public static final ConstantValue<Integer> SYSTEM_STATUS_EFFECT = new ConstantValue<Integer>(1, "有效", SYSTEM_STATUS);

}
