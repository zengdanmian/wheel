package cn.fd.encrypt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * 密码散列函数
 * 1. 已被破解
 * 2. 无法避免碰撞，不适合于做安全性校验
 * 3. 结果是32位16进制数
 */
public class MD5Utils {
    private static final String MD5_ALGORITHM_NAME = "MD5";
    private static final String SALT = "salt";// 彩虹表只能破解没加盐的MD5
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String encode(String src) {
        try {
            return DigestUtils.md5DigestAsHex(src.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeWithSalt(String src, String salt) {
        StringBuilder sb = new StringBuilder(src.length() + salt.length());
        sb.append(src).append(salt);
        try {
            return DigestUtils.md5DigestAsHex(sb.toString().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 不需要依赖 Spring
    public static String encode(String input, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = SALT;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.append(salt);

        String result = null;
        try {
            byte[] btInput = sb.toString().getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance(MD5_ALGORITHM_NAME);
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();

            char[] res = new char[md.length * 2];
            int k = 0;

            for (byte byteItem : md) {
                res[k++] = HEX_DIGITS[byteItem >>> 4 & 0xf];
                res[k++] = HEX_DIGITS[byteItem & 0xf];
            }
            result = new String(res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
