package com.ethjava.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class CertificateUtils {
	public  static final String KEY_STORE = "JKS";
    public  static final String X509 = "X.509";
    private static final int CACHE_SIZE = 2048;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;
     
    private static final String SHA1WithRSA = "SHA1WithRSA";
    private static final String RSA = "RSA";
    private static final String ECB = "ECB";
     
    public static final Map<String, Object> signData =  new HashMap<String, Object>();
    public static final String CRRECEPAY_SIGN_KEYSTORE="YUNBO_SIGN_KEYSTORE";
    public static final String CRRECEPAY_SIGN_X509CERTIFICATE="YUNBO_SIGN_X509CERTIFICATE";
     
     
    public  static final String keyPass   = "123456";
    public  static final String storePass = "abc@2018";
    public  static final String alias     = "yunbo2";
    public  static final String jksFile   = "D:/jkstest/merKey.jks";
    public  static final String pubFile   = "D:/jkstest/yunbo2.cer";
    
    public static void initX509Certificate(String cerFilePath) throws Exception {
        
        InputStream inputStream = null;
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            inputStream = new FileInputStream(cerFilePath);
            X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);
            signData.put(CRRECEPAY_SIGN_X509CERTIFICATE, x509Certificate);
        } finally {
            inputStream.close();
            inputStream =null;
        }
    }
    
    public static PrivateKey getPrivateKey(String jksFilePath, String keyAlias, String keyPass, String storePass) throws Exception {
        
        File jksFile = new File(jksFilePath);
        InputStream in = new FileInputStream(jksFile);
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(in, storePass.toCharArray());
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, keyPass.toCharArray());
        if (in != null) {
        	 in.close();
             in =null;
        }
        return privateKey;
    }
    
    public static PublicKey getPublicKey(String cerFilePath)  {
        PublicKey publicKey = null;
        try {
             
            X509Certificate x509Certificate = (X509Certificate)signData.get(CRRECEPAY_SIGN_X509CERTIFICATE);
            if (x509Certificate == null) {
                initX509Certificate(cerFilePath);
                x509Certificate = (X509Certificate)signData.get(CRRECEPAY_SIGN_X509CERTIFICATE);
            }
            publicKey = x509Certificate.getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    /**
     * 
     * @param requestStr
     * @return
     * @throws Exception
     */
    public static byte[] encryptContentBytes(String requestStr) throws Exception {
        
        try {
            PublicKey publicKey = getPublicKey(pubFile);
            String pubKey  = Base64.encodeBase64String(publicKey.getEncoded());
            byte[] content = encryptByPublicKey(requestStr.getBytes(), pubKey);
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        
        byte[] keyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    /**
     * 
     * @param encryptedData
     * @param privateKey
     * @return
     * @throws Exception
     */
    
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
    
    public static String decryptContentBytes(byte[] responseDataBytes)
            throws Exception {
         
        try {
            PrivateKey privateKey = getPrivateKey(jksFile, alias, keyPass, storePass);
            String priKey = Base64.encodeBase64String(privateKey.getEncoded());
            
            byte[] decryptContentBytes = decryptByPrivateKey(responseDataBytes, priKey);
            return new String(decryptContentBytes, CharEncoding.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String sign(String signData) throws Exception {
        
        InputStream in = new FileInputStream(new File(jksFile));
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(in, storePass.toCharArray());
         
        // 获取jks证书别名
        Enumeration en = keyStore.aliases();
        String pName = null;
        while (en.hasMoreElements()) {
            String n = (String) en.nextElement();
            if (keyStore.isKeyEntry(n)) {
                pName = n;
            }
        }
        PrivateKey key = getPrivateKey(jksFile,pName,keyPass,storePass);
        Signature signature = Signature.getInstance(SHA1WithRSA);
        signature.initSign(key);
        signature.update(signData.getBytes("UTF-8"));
        byte[] signedData = signature.sign();
        String signDate = new BASE64Encoder().encode(signedData);
        signDate = signDate.replaceAll("\r\n", "").replaceAll("\n", "");
        return signDate;
    }
    
    public static boolean verifySign2(String originData,String returnSignData) throws Exception {
        
        PublicKey publicKey = getPublicKey(pubFile);
        Signature sign3 = Signature.getInstance(SHA1WithRSA);
        sign3.initVerify(publicKey);
        sign3.update(originData.getBytes("UTF-8"));
        boolean isVerifySign = sign3.verify(new BASE64Decoder().decodeBuffer(returnSignData));
        return isVerifySign;
    }
    
    public static void main(String[] args) throws Exception{
        
        String originData = "hello波波";
        System.out.println("========> 加密开始");
        byte[] enData = encryptContentBytes(originData);
        String signData = sign(originData);
        System.out.println("========> 加签 signData:"+signData);
        String deData = decryptContentBytes(enData);
        System.out.println("========> 解密 deData:"+deData);
        boolean verifySign = verifySign2(originData,signData);
        System.out.println("========> 解密 verifySign:"+verifySign);
    }

}
