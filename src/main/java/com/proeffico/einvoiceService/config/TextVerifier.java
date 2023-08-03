package com.proeffico.einvoiceService.config;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class TextVerifier {
    public static void textVerify(String original,String receivedSignature) {
        try {

            // Load the public key from the .pem file
            String publicKeyPath = "/home/utkarshy/Documents/einvoiceService/src/main/resources/static/keys/publickey.txt";
            byte[] publicKeyBytes = readPublicKeyFile(publicKeyPath);
            PublicKey publicKey = getPublicKey(publicKeyBytes);

            // Specify the original text
            String originalText = original;

            // Specify the received digital signature

            // Create a signature object and initialize it with the public key
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);

            // Update the signature object with the original text
            signature.update(originalText.getBytes());

            // Verify the digital signature
            byte[] signatureBytes = Base64.getDecoder().decode(receivedSignature);
            boolean isSignatureValid = signature.verify(signatureBytes);

            // Print the verification result
            System.out.println("Signature Verification Result: " + isSignatureValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read the contents of the public key .pem file
    private static byte[] readPublicKeyFile(String publicKeyPath) throws Exception {
        FileInputStream fis = new FileInputStream(publicKeyPath);
        byte[] publicKeyBytes = new byte[fis.available()];
        fis.read(publicKeyBytes);
        fis.close();
        return publicKeyBytes;
    }

    // Convert the public key bytes into a PublicKey object
    private static PublicKey getPublicKey(byte[] publicKeyBytes) throws Exception {
        String publicKeyContent = new String(publicKeyBytes);
        publicKeyContent = publicKeyContent
                .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decodedBytes = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
}
