package cn.seeyoui.mp.generator.common.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CopyUtils {

    /**
     * the beanCopierMap
     */
    private static final ConcurrentMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();

    /**
     * @param source
     * @param target
     * @return T
     * @description 两个类对象之间转换
     */
    public static <T> T convert(Object source, Class<T> target) {
        T ret = null;
        if (source != null) {
            try {
                ret = target.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("create class[" + target.getName()
                        + "] instance error", e);
            }
            BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
            beanCopier.copy(source, ret, new DeepCopyConverter(target));
        }
        return ret;
    }

    public static void copy(Object source, Object target) {
        if (source != null) {
            BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
            beanCopier.copy(source, target, new DeepCopyConverter(target.getClass()));
        }
    }

    public static <T, K> List<K> convertList(List<T> source, Class<T> clazz, Class<K> target) {
        K ret = null;
        List<K> list = null;
        if (source != null) {
            list = new ArrayList<K>();
            for (T t : source) {
                try {
                    ret = target.newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException("create class[" + target.getName()
                            + "] instance error", e);
                }
                BeanCopier beanCopier = getBeanCopier(clazz, target);
                beanCopier.copy(t, ret, new DeepCopyConverter(target));
                list.add(ret);
            }
        }
        return list;
    }
    

    public static <T> void copyBatchCreateParam(List<T> list, String userKey) {
        if (null != list && !list.isEmpty()) {
            for (Object obj : list) {
                copyCreateParam(obj, userKey);
            }
        }
    }
    
    public static <T> void copyBatchCreateParamNullUpd(List<T> list, String userKey) {
        if (null != list && !list.isEmpty()) {
            for (Object obj : list) {
                copyCreateParamNullUpd(obj, userKey);
            }
        }
    }

    public static <T> void copyBatchUpdateParam(List<T> list, String userKey) {
        if (null != list && !list.isEmpty()) {
            for (Object obj : list) {
                copyUpdateParam(obj, userKey);
            }
        }
    }

    public static void copyCreateParam(Object obj, String userKey) {
        Date now = new Date();
        Method method = null;
        if (obj != null) {
            Class<? extends Object> clazz = obj.getClass().getSuperclass();
            try {
                method = clazz.getMethod("setPrimaryKey", String.class);
                method.invoke(obj, UUIDUtils.getUuid());
            } catch (Exception e) {
            }
            try {
            	method = clazz.getMethod("setCreateUser", String.class);
                method.invoke(obj, userKey);
            } catch (Exception e) {
            }
            try {
            	method = clazz.getMethod("setCreateTime", Date.class);
                method.invoke(obj, now);
            } catch (Exception e) {
            }
            try {
            	method = clazz.getMethod("setStatus", Integer.class);
                method.invoke(obj, 1);
            } catch (Exception e) {
            }
            copyUpdateParam(obj, userKey);
        }
    }
    
    public static void copyCreateParamNullUpd(Object obj, String userKey) {
        Date now = new Date();
        Method method = null;
        if (obj != null) {
            Class<? extends Object> clazz = obj.getClass().getSuperclass();
            try {
            	try {
                    method = clazz.getMethod("setPrimaryKey", String.class);
                    method.invoke(obj, UUIDUtils.getUuid());
                } catch (Exception e) {
                }
                try {
                	method = clazz.getMethod("setCreateUser", String.class);
                    method.invoke(obj, userKey);
                } catch (Exception e) {
                }
                try {
                	method = clazz.getMethod("setCreateTime", Date.class);
                    method.invoke(obj, now);
                } catch (Exception e) {
                }
                try {
                	method = clazz.getMethod("setStatus", Integer.class);
                    method.invoke(obj, 1);
                } catch (Exception e) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyUpdateParam(Object obj, String userKey) {
        Date now = new Date();
        Method method = null;
        if (obj != null) {
            Class<? extends Object> clazz = obj.getClass().getSuperclass();
            try {
            	method = clazz.getMethod("setUpdateUser", String.class);
                method.invoke(obj, userKey);
            } catch (Exception e) {
            }
            try {
            	method = clazz.getMethod("setUpdateTime", Date.class);
                method.invoke(obj, now);
            } catch (Exception e) {
            }
        }
    }

    public static class DeepCopyConverter implements Converter {

        /**
         * The Target.
         */
        private Class<?> target;

        /**
         * Instantiates a new Deep copy converter.
         *
         * @param target the target
         */
        public DeepCopyConverter(Class<?> target) {
            this.target = target;
        }

        @Override
        public Object convert(Object value, Class targetClazz, Object methodName) {
            if (value instanceof List) {
                List values = (List) value;
                List retList = new ArrayList<>(values.size());
                for (final Object source : values) {
                    String tempFieldName = methodName.toString().replace("set",
                            "");
                    String fieldName = tempFieldName.substring(0, 1)
                            .toLowerCase() + tempFieldName.substring(1);
                    Class clazz = ClassUtils.getElementType(target, fieldName);
                    if (ClassUtils.primitiveMap.containsKey(clazz)) {
                        retList.add(source);
                    } else {
                        retList.add(CopyUtils.convert(source, clazz));
                    }
                }
                return retList;
            } else if (value instanceof Map) {
                // TODO 暂时用不到，后续有需要再补充
            } else if (!ClassUtils.isPrimitive(targetClazz)) {
                return CopyUtils.convert(value, targetClazz);
            } else if (null != value) {
                String simpleName = targetClazz.getSimpleName();
                if (value instanceof Date) {
                    if ("Long".equals(simpleName)) {
                        return ((Date) value).getTime();
                    }
                } else if (value instanceof Long) {
                    if ("Date".equals(simpleName)) {
                        return new Date((Long) value);
                    }
                }
            }
            return value;
        }
    }

    /**
     * @param source
     * @param target
     * @return BeanCopier
     * @description 获取BeanCopier
     */
    public static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String beanCopierKey = generateBeanKey(source, target);
        if (beanCopierMap.containsKey(beanCopierKey)) {
            return beanCopierMap.get(beanCopierKey);
        } else {
            BeanCopier beanCopier = BeanCopier.create(source, target, true);
            beanCopierMap.putIfAbsent(beanCopierKey, beanCopier);
        }
        return beanCopierMap.get(beanCopierKey);
    }

    /**
     * @param source
     * @param target
     * @return String
     * @description 生成两个类的key
     */
    public static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }
}
