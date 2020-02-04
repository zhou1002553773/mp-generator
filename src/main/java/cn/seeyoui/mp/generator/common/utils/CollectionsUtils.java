package cn.seeyoui.mp.generator.common.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
