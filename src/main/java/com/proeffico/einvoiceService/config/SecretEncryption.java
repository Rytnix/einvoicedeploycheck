package com.proeffico.einvoiceService.config;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SecretEncryption
{
    public static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    public static final String AES_ALGORITHM = "AES";
    public static final int ENC_BITS = 256;
    public static final String CHARACTER_ENCODING = "UTF-8";

    private static Cipher ENCRYPT_CIPHER;
    private static Cipher DECRYPT_CIPHER;
    private static KeyGenerator KEYGEN;

    static
    {
        try
        {
            ENCRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
            DECRYPT_CIPHER = Cipher.getInstance(AES_TRANSFORMATION);
            KEYGEN = KeyGenerator.getInstance(AES_ALGORITHM);
            KEYGEN.init(ENC_BITS);
        }
        catch(NoSuchAlgorithmException | NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to encode bytes[] to base64 string.
     *
     * @param bytes
     *            : Bytes to encode
     * @return : Encoded Base64 String
     */

    private static String encodeBase64String(byte[] bytes)
    {
        return new String(java.util.Base64.getEncoder().encode(bytes));
    }

    /**
     * This method is used to decode the base64 encoded string to byte[]
     *
     * @param stringData
     *            : String to decode
     * @return : decoded String
     * @throws UnsupportedEncodingException
     */

    private static byte[] decodeBase64StringTOByte(String stringData) throws Exception
    {
        return java.util.Base64.getDecoder().decode(stringData.getBytes(CHARACTER_ENCODING));
    }

    /**
     * This method is used to encrypt the string which is passed to it as byte[] and return base64 encoded
     * encrypted String
     * @param plainText
     *            : byte[]
     * @param secret
     *            : Key using for encrypt
     * @return : base64 encoded of encrypted string.
     *
     */

    public static String encryptEK(byte[] plainText, byte[] secret)
    {
        try
        {
            SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
            ENCRYPT_CIPHER.init(Cipher.ENCRYPT_MODE, sk);
            return Base64.encodeBase64String(ENCRYPT_CIPHER.doFinal(plainText));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * This method is used to decrypt base64 encoded string using an AES 256 bit key.
     *
     * @param plainText
     *            : plain text to decrypt
     * @param secret
     *            : key to decrypt
     * @return : Decrypted String
     * @throws IOException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(String plainText, byte[] secret)
            throws InvalidKeyException, IOException, IllegalBlockSizeException,
            BadPaddingException,Exception
    {
        SecretKeySpec sk = new SecretKeySpec(secret, AES_ALGORITHM);
        DECRYPT_CIPHER.init(Cipher.DECRYPT_MODE, sk);
        return DECRYPT_CIPHER.doFinal(Base64.decodeBase64(plainText));
    }

    public static String getResults(String encKey,String asp_secret ) throws Exception {
        //response of getkey API call
        //asp_secret provided by NSDL


        byte[] enc_key = decrypt(encKey, asp_secret.getBytes());

        String enc_asp_secret=encryptEK(asp_secret.getBytes(), decodeBase64StringTOByte(encodeBase64String(enc_key)));

        System.out.println("asp secret encrypted:");
        System.out.println(enc_asp_secret);
        return enc_asp_secret;
    }

    public static void main(String[] args) throws Exception {
//        getResults("cpiNiLNOJZcqZeJ5zk+y/fQ+ELIzOc8ierasv2lQ1fiQy1epHVLp/scneoDEOdnC",
//                "228678e60IBf59A3S60e345VHJq190zW");

        System.out.println(Base64.encodeBase64String(decrypt("WolsE6AvVVdzCrHDtglwPGZBAJhAKIlkGYLmX9X+TFgDoITut06xNL5b6vIVgblR",Base64.decodeBase64("HqUA5bLy/d7xA6Y2g1n49AFKbUZ3NDKJvNtt8T/ph0c="))));
    }
}
