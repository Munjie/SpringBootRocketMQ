package tk.mybatis.springboot.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @Author: 母哥 @Date: 2019-01-23 9:59 @Version 1.0
 */
public class AESUtil {

    protected static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"; // 默认的加密算法

    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        logger.info("start encrypt...");
        Long start = System.currentTimeMillis();
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM); // 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password)); // 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent); // 加密
            logger.info(
                    String.format("encrypt completed, spend time:%s", (System.currentTimeMillis() - start)));
            return Base64.encodeBase64String(result); // 通过Base64转码返回
        } catch (Exception ex) {
            logger.error("encrypt exception", ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {
        logger.info("start decrypt...");
        Long start = System.currentTimeMillis();
        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            logger.info(
                    String.format("decrypt completed, spend time:%s", (System.currentTimeMillis() - start)));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            logger.error("decrypt exception", ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        logger.info("start buid secret key");
        Long start = System.currentTimeMillis();
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // AES 要求密钥长度为 128
            kg.init(128, new SecureRandom(password.getBytes()));

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();
            logger.info("secretKey：" + secretKey.getEncoded());
            logger.info(
                    String.format(
                            "build secret key completed, spend time:%s", (System.currentTimeMillis() - start)));
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM); // 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            logger.error("build secretkey exception", ex);
        }

        return null;
    }

    public static void main(String[] args) {
        String s = "hello java";

        System.out.println("s:" + s);

        String s1 = AESUtil.encrypt(s, "mwj12/.54888888888");
        System.out.println("s1:" + s1);

        System.out.println("s2:" + AESUtil.decrypt(s1, "mwj12/.54888888888"));
    }
}
