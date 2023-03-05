package ${cfg.packageRootPath}.common.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsUtils {

	public static <K, V> Map<K, V> getMapProperty(List<V> list, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, V> map = list.stream().collect(Collectors.toMap(function, Function.identity(), (oldValue, newValue) -> newValue));
		return map;
	}

	/**
	 * <p>
	 * 过滤空,key,value都可为空
	 * </p >
	 */
	public static <T, K, V> Map<K, V> getMapPropertyWithNull(Collection<T> collection, Function<T, K> kFun, Function<T, V> vFun) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(collection)) {
			return Maps.newHashMap();
		}
		return collection.stream().filter(Objects::nonNull).collect(HashMap::new, (m, v) -> m.put(kFun.apply(v), vFun.apply(v)), HashMap::putAll);
	}

	/**
	 * <p>
	 * 过滤空,key,value都可为空
	 * </p >
	 */
	public static <K, T> Map<K, T> getMapPropertyWithNull(Collection<T> collection, Function<T, K> kFun) {
		return getMapPropertyWithNull(collection, kFun, Function.identity());
	}

	public static <K, V> Map<K, List<V>> getGroupProperty(List<V> list, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, List<V>> map = list.stream().collect(Collectors.groupingBy(function));
		return map;
	}

	/**
	 * <p>
	 * 过滤空,key,value都可为空
	 * </p >
	 */
	public static <K, T> Map<K, List<T>> getGroupPropertyWithNull(Collection<T> collection, Function<T, K> kFun) {
		return getGroupPropertyWithNull(collection, kFun, Function.identity());
	}

	/**
	 * <p>
	 * 过滤空,key,value都可为空
	 * </p >
	 */
	public static <T, K, V> Map<K, List<V>> getGroupPropertyWithNull(Collection<T> collection, Function<T, K> kFun, Function<T, V> vFun) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(collection)) {
			return Maps.newHashMap();
		}
		Map<K, List<V>> listMap = new HashMap<>();
		collection.stream().filter(Objects::nonNull).forEach(bean -> {
			K applyKey = kFun.apply(bean);
			List<V> vList = listMap.getOrDefault(applyKey, Lists.newArrayList());
			vList.add(vFun.apply(bean));
			listMap.put(applyKey, vList);
		});
		return listMap;
	}

	public static <K, V> List<K> getListProperty(List<V> list, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		return list.stream().map(function).collect(Collectors.toList());
	}

	public static <K, V> Set<K> getSetProperty(Collection<V> collection, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(collection)) {
			return Collections.emptySet();
		}
		return collection.stream().map(function).collect(Collectors.toSet());
	}

	public static <K, V> Set<K> getSetPropertyExcludeNull(Collection<V> collection, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(collection)) {
			return Collections.emptySet();
		}
		return collection.stream().map(function).filter(Objects::nonNull).collect(Collectors.toSet());
	}

	public static <K, V> List<K> getListPropertyExcludeNull(List<V> list, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		return list.stream().map(function).filter(e -> (null != e)).collect(Collectors.toList());
	}

	public static <K, V> List<K> getListPropertyExcludeNullDistinct(List<V> list, Function<V, K> function) {
		if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		return list.stream().map(function).filter(Objects::nonNull).distinct().collect(Collectors.toList());
	}

	public static void removeNull(List<String> list) {
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			String x = it.next();
			if (x == null || x.equals("null") || x.equals("NULL") || x.length() == 0) {
				it.remove();
			}
		}
	}

}
