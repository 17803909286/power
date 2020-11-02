package com.power.home.common.util;

import android.util.Base64;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

//import java.util.Base64;

public class RsaUtils {
    PublicKey publicKey;


    public RsaUtils() {
        String pem = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCn9gfPZ89YJ+XasYXcGK8Hw52zrFZC8GNkt+EW5tlT38t29JJlVI440JqTCTVM1kC9+r4dixpl6+5KxZhMnqYWjYZMR/28KBeDBHu/DOBmaYt21YeMait3cFL8wKEmVuYry8b2+PV1ksrt9gGGckygsJK6ygGDfNapf2Qo4O9QIQIDAQAB";
        String publicExponent = "10001";
        String modulus = "a7f607cf67cf5827e5dab185dc18af07c39db3ac5642f06364b7e116e6d953dfcb76f49265548e38d09a9309354cd640bdfabe1d8b1a65ebee4ac5984c9ea6168d864c47fdbc281783047bbf0ce066698b76d5878c6a2b777052fcc0a12656e62bcbc6f6f8f57592caedf60186724ca0b092baca01837cd6a97f6428e0ef5021";
        this.publicKey = getPublicKey(modulus, publicExponent);
    }

    public RsaUtils(String pem, String publicExponent, String modulus) {
        this.publicKey = getPublicKey(modulus, publicExponent);
    }

    public static PublicKey getPublicKey(String modulus, String exponent) {
        try {
            BigInteger b1 = new BigInteger(modulus, 16);
            BigInteger b2 = new BigInteger(exponent, 16);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(b1, b2);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Cipher initCipherEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipherEncrypt = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipherEncrypt;
    }

    public String encrypt(String msg) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        Cipher cipherEncrypt = null;
        try {
            cipherEncrypt = initCipherEncrypt();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(cipherEncrypt.doFinal(msg.getBytes()), Base64.NO_WRAP);
    }
}
