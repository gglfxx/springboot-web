package com.shiro.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
/**
 * 生成token
 */
public class TokenProccessor {
    private TokenProccessor(){ }
    private static final TokenProccessor instance = new TokenProccessor();
    public static TokenProccessor getInstance() {
        return instance;
    }
    /**
     * 生成Token
     * @return
     */
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
            return EncryptUtil.base64ByteEncrypt(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
