package ${cfg.packageRootPath}.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置数据库索引
     *
     * @param dbIndex
     */
    private void setDbIndex(Integer dbIndex) {
        if (dbIndex == null || dbIndex > 15 || dbIndex < 0) {
            dbIndex = 0;
        }
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate
                .getConnectionFactory();
        connectionFactory.setDatabase(dbIndex);
        connectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(connectionFactory);
    }

    public boolean addString(Integer dbIndex, String key, String value, int cacheSeconds){
        setDbIndex(dbIndex);
        try {
            stringRedisTemplate.opsForValue().set(key,value,cacheSeconds, TimeUnit.SECONDS);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean addString(Integer dbIndex, String key, String value){
        setDbIndex(dbIndex);
        try {
            stringRedisTemplate.opsForValue().set(key,value);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public String getString(Integer dbIndex, String key){
        setDbIndex(dbIndex);
        return stringRedisTemplate.opsForValue().get(key);
    }

}
