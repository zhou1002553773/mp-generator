package ${cfg.packageRootPath}.common.model.constant;

import java.io.Serializable;
import ${cfg.packageRootPath}.common.model.constant.ConstantContainer;
import ${cfg.packageRootPath}.common.model.constant.ConstantValue;

public class ConstantValue<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T value;
    private String name;

    public ConstantValue(T value, String name) {
        this.value = value;
        this.name = name;
    }

    public ConstantValue(T value, String name, ConstantContainer<T> container) {
        this.value = value;
        this.name = name;
        if (null != container && null != value) {
            container.setValue(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    public final T value() {
        return value;
    }

    public final String name() {
        return name;
    }
}
