package ${cfg.packageRootPath}.common.model.constant;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ConstantContainer<T> implements Serializable {
    private Map<T, ConstantValue<T>> container = new LinkedHashMap<T, ConstantValue<T>>();

    public void setValue(ConstantValue<T> constant) {
        if (null != constant) {
            container.put(constant.value(), constant);
        }
    }

    public String getName(T key) {
        if (null != key) {
            ConstantValue<T> constantValue = container.get(key);
            if (null != constantValue) {
                return constantValue.name();
            }
        }
        return null;
    }

    public ConstantValue<T> get(T key) {
        return container.get(key);
    }

    @SuppressWarnings("unchecked")
    public <R extends ConstantValue<T>> R get(T key, Class<R> clazz) {
        return (R) container.get(key);
    }

    public Map<T, String> getNameValuePairs() {
        Map<T, String> result = new LinkedHashMap<T, String>();
        if (container.size() > 0) {
            for (Entry<T, ConstantValue<T>> en : container.entrySet()) {
                result.put(en.getKey(), en.getValue().name());
            }
        }
        return result;
    }
}
