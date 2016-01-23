package com.example.aesencrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {

	private static final String IV = "qws871bz73msl9x8";// �����������16λ�ַ���
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        byte[] arrB = new byte[16]; // ����һ���յ�16λ�ֽ����飨Ĭ��ֵΪ0��

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    /**
     * ����
     * 
     * @param data
     * @return
     */
    public static String encrypt(String data, String KEY) {
        try {
            String encryptKey = KEY;
            SecretKeySpec skeySpec = getKey(encryptKey);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes());

            return parseByte2HexStr(encrypted);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * ����
     * 
     * @param data
     * @return
     */
    public static String decrypt(String data, String KEY) {
        try {
            String decryptKey = KEY;
            SecretKeySpec skeySpec = getKey(decryptKey);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = parseHexStr2Byte(data);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);

            return originalString;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * ��������ת����16����
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * ��16����ת��Ϊ������
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
