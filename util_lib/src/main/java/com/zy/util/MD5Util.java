package com.zy.util;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * @author zhuyue
 */
public class MD5Util {

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * MD5加密文本
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public static String encrypt(String plaintext) {
        String md5 = null;
        try {
            byte[] btInput = plaintext.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");//获得MD5摘要算法的 MessageDigest 对象
            mdInst.update(btInput);// 使用指定的字节更新摘要
            byte[] md = mdInst.digest();//获得密文
            md5 = byteArrayToHexString(md);//把密文转换成十六进制的字符串形式
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * MD5加密文件
     *
     * @param file 文件
     * @return
     */
    public static String encrypt(File file) {
        String md5 = null;
        FileInputStream fis = null;
        FileChannel fileChannel = null;
        try {
            fis = new FileInputStream(file);
            fileChannel = fis.getChannel();
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(byteBuffer);
                md5 = byteArrayToHexString(md.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return md5;
    }

    /**
     * MD5加密对象
     *
     * @param object 对象
     * @return
     */
    private static String encrypt(Object object) {
        String md5 = null;
        byte[] bytes;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(object);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.digest(bytes);
            md5 = byteArrayToHexString(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 字节数组转十六进制字符串
     *
     * @param digest
     * @return
     */
    private static String byteArrayToHexString(byte[] digest) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            buffer.append(byteToHexString(digest[i]));
        }
        return buffer.toString();
    }

    /**
     * 字节转十六进制字符串
     *
     * @param b 字节
     * @return
     */
    private static int byteToHexString(byte b) {
        int d1 = (b & 0xf0) >> 4;
        int d2 = b & 0xf;
        return hexDigits[d1] + hexDigits[d2];
    }

}
