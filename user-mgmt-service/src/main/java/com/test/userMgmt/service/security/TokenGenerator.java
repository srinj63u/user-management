package com.test.userMgmt.service.security;

import com.test.userMgmt.service.config.Appconfig;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author sk11
 */
@Component
public class TokenGenerator {
	
	private static final String AES_ALGORITHM_NAME = "AES";
	
	private static final byte[] keyValue =
            new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
	
	public String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(AES_ALGORITHM_NAME);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(AES_ALGORITHM_NAME);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    /**
     * Generate a new encryption key.
     */
    private Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, AES_ALGORITHM_NAME);
    }
	
}
