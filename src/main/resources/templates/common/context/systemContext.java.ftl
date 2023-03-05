package ${cfg.packageRootPath}.common.context;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SystemContext {
	
    public static final String SYSTEM_CONTEXT_KEY_USER_PRIMARY_KEY = "SystemContext-UserPrimaryKey";
    public static final String SYSTEM_CONTEXT_KEY_NICKNAME = "SystemContext-NickName";
    public static final String SYSTEM_CONTEXT_KEY_PHONE = "SystemContext-Phone";
    public static final String SYSTEM_CONTEXT_KEY_SESSION_ID = "SystemContext-SessionId";
    public static final String SYSTEM_CONTEXT_KEY_PUBLIC_KEYS = "SystemContext-PublicKey";
    public static final String SYSTEM_CONTEXT_KEY_PRIVATE_KEYS = "SystemContext-PrivateKey";
    public static final String SYSTEM_CONTEXT_WECHAT_OPENID = "System-Context-Wechat-OpenId";
    public static final String SYSTEM_CONTEXT_WECHAT_UNIONID = "System-Context-Wechat-UnionId";

    private static final ThreadLocal<Map<String, String>> threadContext = new ThreadLocal<Map<String, String>>();

    /**
     * 新增系统上下文项
     * 
     * @param key
     * @param value
     */
    public static final void add(String key, String value) {
        if (threadContext.get() == null) {
            threadContext.set(new ConcurrentHashMap<String, String>());
        }
        threadContext.get().put(key, value);
    }
    
    /**
     * 获取系统上下文项
     * 
     * @param key
     * @return
     */
    public static final String get(String key) {
        if (null == threadContext.get()) {
            return null;
        }
        return threadContext.get().get(key);
    }
    
    /**
     * 清空系统上下文
     * 
     */
    public static final void clear() {
        if (null != threadContext.get()) {
            threadContext.get().clear();
        }
    }
    
    /**
     * 新增系统上下文（对象方式）
     * 
     * @param key
     * @param value
     */
    public static final void addValue(String key, Object value) {
        add(key, JSON.toJSONString(value));
    }
    
    /**
     * 获取系统上下文（对象方式）
     * 
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    public static final <T> T getValue(String key, Class<T> clazz) throws Exception{
        String jsonValue = get(key);
        if (StringUtils.isNotBlank(jsonValue)) {
            return JSON.parseObject(jsonValue, clazz);
        }
        return null;
    }

    /**
     * 添加加密PublicKey
     * 
     * @param publicKey
     */
    public static final void setPublicKey(String publicKey) {
    	if(StringUtils.isNotEmpty(publicKey)) {
    		add(SYSTEM_CONTEXT_KEY_PUBLIC_KEYS, publicKey);
    	}
    }
    
    /**
     * 获取加密publicKey
     * 
     */
    public static String getPublicKey() {
    	String publicKey = get(SYSTEM_CONTEXT_KEY_PUBLIC_KEYS);
    	return null != publicKey ? publicKey : null;
    }
    
    /**
     * 添加加密PublicKey
     * 
     * @param privateKey
     */
    public static final void setPrivateKey(String privateKey) {
    	if(StringUtils.isNotEmpty(privateKey)) {
    		add(SYSTEM_CONTEXT_KEY_PRIVATE_KEYS, privateKey);
    	}
    }
    
    /**
     * 获取加密publicKey
     * 
     */
    public static String getPrivateKey() {
    	String privateKey = get(SYSTEM_CONTEXT_KEY_PRIVATE_KEYS);
    	return null != privateKey ? privateKey : null;
    }
    
    /**
     * 保存用户候选键
     */
    public static final void setUserPrimaryKey(String userPrimaryKey) {
        if (StringUtils.isNotBlank(userPrimaryKey)) {
            add(SYSTEM_CONTEXT_KEY_USER_PRIMARY_KEY, userPrimaryKey);
        }
    }
    
    /**
     * 获取用户候选键
     * 
     * @return
     */
    public static final String getUserPrimaryKey() {
        return get(SYSTEM_CONTEXT_KEY_USER_PRIMARY_KEY);
    }
    
    /**
     * 保存用户昵称
     * 
     * @param nickname
     */
    public static final void setNickName(String nickname) {
        if (StringUtils.isNotBlank(nickname)) {
            add(SYSTEM_CONTEXT_KEY_NICKNAME, nickname);
        }
    }
    
    /**
     * 获取用户昵称
     * 
     * @return
     */
    public static final String getNickName() {
        return get(SYSTEM_CONTEXT_KEY_NICKNAME);
    }
    
    /**
     * 保存用户电话
     * 
     */
    public static final void setPhone(String phone) {
        if (StringUtils.isNotBlank(phone)) {
            add(SYSTEM_CONTEXT_KEY_PHONE, phone);
        }
    }
    
    /**
     * 获取用户电话
     * 
     * @return
     */
    public static final String getPhone() {
        return get(SYSTEM_CONTEXT_KEY_PHONE);
    }
    
    /**
     * 保存微信小程序OPENID
     * 
     * @param openId
     */
    public static final void setWechatOpenId(String openId) {
        if (StringUtils.isNotBlank(openId)) {
            add(SYSTEM_CONTEXT_WECHAT_OPENID, openId);
        }
    }
    
    /**
     * 获取微信小程序OPENID
     * 
     * @return
     */
    public static final String getWechatOpenId() {
        return get(SYSTEM_CONTEXT_WECHAT_OPENID);
    }
    
    /**
     * 保存微信UNIONID
     * 
     * @param unionId
     */
    public static final void setWechatUnionId(String unionId) {
        if (StringUtils.isNotBlank(unionId)) {
            add(SYSTEM_CONTEXT_WECHAT_UNIONID, unionId);
        }
    }
    
    /**
     * 获取微信UNIONID
     * 
     * @return
     */
    public static final String getWechatUnionId() {
        return get(SYSTEM_CONTEXT_WECHAT_UNIONID);
    }
    
    /**
     * 设置上下文ID
     * 
     * @param sessionId
     */
    public static final void setSessionId(String sessionId) {
        if (StringUtils.isNotBlank(sessionId)) {
            add(SYSTEM_CONTEXT_KEY_SESSION_ID, sessionId);
        }
    }
    
    /**
     * 获取上下文ID
     * 
     * @return
     */
    public static final String getSessionId() {
        return get(SYSTEM_CONTEXT_KEY_SESSION_ID);
    }
    
    public static final Map<String, String> getMap() {
        return threadContext.get();
    }

    public static final void setMap(Map<String, String> map) {
        if (null != map && map.size() > 0) {
            if (threadContext.get() == null) {
                threadContext.set(new ConcurrentHashMap());
            }

            ((Map)threadContext.get()).putAll(map);
        }
    }

}
