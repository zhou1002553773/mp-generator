package cn.seeyoui.mp.generator.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class UUIDUtils {
    private static final String DICT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random random = new Random();
    
    /**
     * UUID生成器
     * 
     * @return
     */
    public synchronized static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 6位数字验证码生成器
     * 
     * @return
     */
    public synchronized static String getInvidID() {
    	Random rand=new Random();//生成随机数
        String cardNnumer="";
        for(int a=0;a<6;a++){
        cardNnumer+=rand.nextInt(10);//生成6位数字
        }
       return cardNnumer;
    }

    /**
     * 不定长随机字符串生成器（区分大小写，数字英文混排）
     * 
     * @return
     */
    public static String createCode(int maxLength, int minLength) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (minLength > 1 && maxLength > minLength) {
            for (int bit = random.nextInt(maxLength - minLength + 1) + minLength - 1 ; bit >= 0 ; bit--) {
                sb.append(DICT.charAt(random.nextInt(62)));
            }
        } else {
            throw new Exception(String.format("Create Code ERROR with wrong params(Max: %d, Min: %d)", maxLength, minLength));
        }
        return sb.toString();
    }
    
    /**
     * 不定长随机字符串生成器（区分大小写，数字英文混排）
     * 
     * @return
     */
    public static String createCode(int length) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (length > 1) {
            for (int bit = length ; bit >= 0 ; bit--) {
                sb.append(DICT.charAt(random.nextInt(62)));
            }
        } else {
            throw new Exception(String.format("Create Code ERROR with wrong params(Length: %d)", length));
        }
        return sb.toString();
    }
    
    /**
     * 生成单据编码
     * 
     * @return
     */
    public static String createReceiptNo(String prefix) {
    	String date = new SimpleDateFormat("YYMMddHHmmss").format(new Date());
    	StringBuilder sb = StringUtils.isNotBlank(prefix) ? new StringBuilder(prefix) : new StringBuilder();
        for (int bit = 1 ; bit <= 4 ; bit++) {
            sb.append(random.nextInt(9));
        }
        for (int pointer = 0 ; pointer < date.length() ; pointer++) {
            sb.insert(random.nextInt(sb.length()), date.charAt(pointer));
        }
        return sb.toString();
    }
}
