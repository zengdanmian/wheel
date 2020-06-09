package cn.fd.encrypt;

import org.springframework.util.DigestUtils;

/**
 * 密码散列函数
 * 1. 已被破解
 * 2. 无法避免碰撞，不适合于做安全性校验
 * 3. 结果是32位16进制数
 */
public class MD5Utils {
    private static final String salt = "salt";// 彩虹表只能破解没加盐的MD5

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

    public static void main(String[] args) {
        String encode = MD5Utils.encode("87ki3nd7287ki3nd7287ki3nd7287ki3nd72");
        System.out.println(encode);
    }
}
