package com.proeffico.einvoiceService.config;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class EncryptionUtil {

    public static String encryptBySymmetricKey(String json, String decryptedSek) {
        try {
            byte[] sekByte = Base64.decodeBase64(decryptedSek);
            Key aesKey = new SecretKeySpec(sekByte, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            byte[] encryptedJsonBytes = cipher.doFinal(json.getBytes());
            String encryptedJson = Base64.encodeBase64String(encryptedJsonBytes);
            return encryptedJson;
        } catch (Exception e) {
            return "Exception " + e;
        }
    }

    public static void main(String[] args) {
        // Example usage
        String json = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        String decryptedSek = "YourDecryptedSymmetricKey"; // Replace this with your actual decrypted symmetric key
        String encryptedJson = encryptBySymmetricKey(json, decryptedSek);
        System.out.println("Encrypted JSON: " + encryptedJson);
    }
}
