package com.proeffico.einvoiceService.config;


import org.springframework.core.io.ClassPathResource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignAuthToken {

    private PrivateKey readPemPrivateKey(String privateKeyPath) throws
            CertificateException, IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        File file = new File(privateKeyPath);
        DataInputStream is = new DataInputStream(new FileInputStream(file));
        byte[] keyBytes = new byte[(int) file.length()];
        is.readFully(keyBytes);
        is.close();
        String temp = new String(keyBytes);
        String privKeyPEM = temp.replace("-----BEGIN PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replace("-----END PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replace("\n", "");
        Base64.Decoder b64=Base64.getDecoder();
        byte[] decoded = b64.decode(privKeyPEM);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);
        return privateKey;
    }
    public  PublicKey readPemPublicKey(String publicKeyPath)
            throws
            CertificateException,
            IOException,
            NoSuchAlgorithmException,
            InvalidKeySpecException {
        File file = new File(publicKeyPath);
        DataInputStream is = new DataInputStream(new FileInputStream(file));
        byte[] keyBytes = new byte[(int) file.length()];
        is.readFully(keyBytes);
        is.close();
        String temp = new String(keyBytes);
        String pubKeyPEM = temp.replace("-----BEGIN PUBLIC KEY-----", "");
        pubKeyPEM = pubKeyPEM.replace("-----END PUBLIC KEY-----", "");
        pubKeyPEM = pubKeyPEM.replace("\n", "");
        Base64.Decoder b64 = Base64.getDecoder();
        byte[] decoded = b64.decode(pubKeyPEM);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(spec);
        return publicKey;
    }
    private String sign(String message, PrivateKey privateKey) throws
            NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(privateKey);
        sig.update(message.getBytes());
        byte[] signatureBytes = sig.sign();
        String signature = Base64.getEncoder().encodeToString(signatureBytes);
        System.out.println("Signature:" + signature);
        return signature;
    }

      public String signToken(String msg) throws CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        ClassPathResource privateKeyResource = new ClassPathResource("static/keys/privatekey.txt");
        PrivateKey privateKey = readPemPrivateKey(privateKeyResource.getFile().getAbsolutePath());
        return sign(msg,privateKey);
    }

    public String encodeByteArray(byte[] byteArrayToEncode) throws Exception {
//        ClassPathResource publicKeyFilePath = new ClassPathResource("static/keys/sand_public_key1.pem");
//        byte [] encodedBytes = CertificateEncryptionExample.encryptpayloadbyte(byteArrayToEncode,publicKeyFilePath.getFile().getPath());
        String publicKeyPath = "/home/utkarshy/Documents/einvoiceService/src/main/resources/static/keys/sand_public_key1.pem";
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
        String publicKeyPEM = new String(publicKeyBytes);
        publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] publicKeyData = Base64.getDecoder().decode(publicKeyPEM);

        // Create a PublicKey object from the encoded key data
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyData);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // Encrypt the byte array using the public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(byteArrayToEncode);
        return Base64.getEncoder().encodeToString(encryptedData);
     }


}
