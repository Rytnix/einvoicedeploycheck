package com.proeffico.einvoiceService.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class PublicKeyEncryptionExample {
    public static String encryptWithPublicKey(byte[] plaintext) throws Exception {
        // Sample byte array to be encrypted

        // Load public key from PEM file
        PublicKey publicKey = loadPublicKeyFromPEM("/home/utkarshy/Documents/einvoiceService/src/main/resources/static/keys/einv_prod_old_pubkey.pem");
        SignAuthToken signAuthToken = new SignAuthToken();
//        PublicKey publicKey = signAuthToken.readPemPublicKey("/home/utkarshy/Documents/einvoiceService/src/main/resources/static/keys/sand_public_key1.pem");
        // Create the cipher object and initialize it with the public key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Encrypt the plaintext byte array
        byte[] ciphertext = cipher.doFinal(plaintext);

        // Print the encrypted data (encoded in Base64)
        String encryptedText = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println("Encrypted Text: " + encryptedText);
        return  encryptedText;
    }

    private static PublicKey loadPublicKeyFromPEM(String pemFilePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // Read the PEM file content
        byte[] pemBytes = Files.readAllBytes(Paths.get(pemFilePath));
        String pemContent = new String(pemBytes, StandardCharsets.UTF_8);

        // Extract the key content between "BEGIN PUBLIC KEY" and "END PUBLIC KEY"
        String publicKeyContent = pemContent.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", ""); // Remove whitespace characters

        // Decode the Base64-encoded key content
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyContent);

        // Create the PublicKey object from the decoded bytes
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(publicKeySpec);
    }
}
