package cn.seeyoui.mp.generator.common.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionsUtils {

	public static <K,V> Map<K,V> getMapProperty(List<V> list, Function<V,K> function) {
		if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
        Map<K, V> map = list.stream().collect(Collectors.toMap(function, Function.identity(), (key1, key2) -> key2));
        return map;
    }

	/**
	 * <p>
	 * 过滤空,key,value都可为空
	 * </p >
	 */
	public static <T, K, V> Map<K, V> getMapPropertyWithNull(Collection<T> collection, Function<T, K> kFun, Function<T, V> vFun) {
		if (CollectionUtils.isEmpty(collection)) {
			return Maps.newHashMap();
		}
		return collection.stream().filter(Objects::nonNull).collect(HashMap::new, (m, v) -> m.put(kFun.apply(v), vFun.apply(v)), HashMap::putAll);
	}
	
	public static <K,V> Map<K,List<V>> getGroupProperty(List<V> list, Function<V,K> function) {
		if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }
		Map<K, List<V>> map = list.stream().collect(Collectors.groupingBy(function));
        return map;
    }
	
	public static <K,V> List<K> getListProperty(List<V> list, Function<V,K> function) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        return list.stream().map(function).collect(Collectors.toList());
    }
	
	public static void removeNull(List<String> list){
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
		    String x = it.next();
		    if(x==null || x.equals("null") || x.equals("NULL")||x.length()==0){
		        it.remove();
		    }
		}
	}
	
}
