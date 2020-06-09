package cn.fd.encrypt;

import java.util.Base64;

/**
 * 1 base64 是一种编码方式，不属于加密
 * 2 base64要求把每三个8Bit的字节转换为四个6Bit的字节（3*8 = 4*6 = 24），然后把6Bit再添两位高位0，组成四个8Bit的字节，
 *   也就是说，转换后的字符串理论上将要比原来的长1/3。
 * 3 进行字节转换的过程中可能存在位数不足的情况下，此时需要用0进行不充，两个0用"="表示
 * 4 6Bit共有2^6=64Z种结果，大小写字母+数字共62个字符，加上"+"和"/"共64个字符，
 *   也就是说，转换后的字符串理论上只会出现大小写字母，数字，"+"，"/"和"="。
 */
public class Base64Utils {
    private static final String DEFAULTA_CHARSET = "UTF-8";

    public static String encode(String str) {
        try {
            byte[] src = str.getBytes(DEFAULTA_CHARSET);
            byte[] encode = encode(src);
            return new String(encode, DEFAULTA_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decode(String base64) {
        try {
            byte[] src = base64.getBytes(DEFAULTA_CHARSET);
            byte[] decode = decode(src);
            return new String(decode, DEFAULTA_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] encode(byte[] src) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encode(src);
    }

    private static byte[] decode(byte[] src) {
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(src);
    }
}
