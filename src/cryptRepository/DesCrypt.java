package cryptRepository;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;


public class DesCrypt {

    private static final String VECTOR  = "WhatIsYo";
    private static final String key = "BWZYATBA";

    //1 - mod(CBC) and menu(file)
    //2 - mod(ECB) and menu(text)

    public String encrypt(String orgtext) throws Exception{


        IvParameterSpec ivParameterSpec = new IvParameterSpec(VECTOR.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),"DES");

        String s = null;
            //CBC mode
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
            s = new String(Hex.encodeHex(cipher.doFinal(orgtext.getBytes(StandardCharsets.UTF_8))));

        return s;

    }

    public String decrypt(String cipherText) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(VECTOR.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),"DES");

        String s;
            //CBC mode
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
            s = new String(cipher.doFinal(Hex.decodeHex(cipherText.toCharArray())));

        return s;
    }

    private void processFile(boolean encrypt, File inputFile, File outputFile, String url) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(VECTOR.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8),"DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        if(encrypt) {
            cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivParameterSpec);
        }
        else {
            cipher.init(Cipher.DECRYPT_MODE,secretKey,ivParameterSpec);
        }

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int)inputFile.length()];
        fileInputStream.read(inputBytes);

        byte[] outputBytes = cipher.doFinal(inputBytes);

        FileOutputStream fileOutputStream = new FileOutputStream(url + outputFile);
        fileOutputStream.write(outputBytes);

        fileInputStream.close();
        fileOutputStream.close();

    }

    public void encryptFile(File inputFile, File outputFile,String url) throws Exception {
        processFile(true,inputFile,outputFile,url);
    }

    public void decryptFile(File inputFile, File outputFile,String url) throws Exception {
        processFile(false,inputFile,outputFile,url);
    }
}
