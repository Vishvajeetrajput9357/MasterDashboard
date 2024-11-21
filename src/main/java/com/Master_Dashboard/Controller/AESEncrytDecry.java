package  com.Master_Dashboard.Controller;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESEncrytDecry {

    public static String key_Val = "k2hLr4X0ozNyZByj5DT66edtCEee1x+6";

    public static String DecryptAES(String encryptedValue) {
        if (encryptedValue == null || encryptedValue.isEmpty()) {
            return encryptedValue;
        }

        if (encryptedValue.startsWith("\\x")) {
            encryptedValue = encryptedValue.substring(2);
        }

        byte[] key = key_Val.getBytes(StandardCharsets.UTF_8);
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(HexToByteArray(encryptedValue));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    public static String Encrypt(String plainText) {
        if (plainText == null || plainText.isEmpty()) {
        	
        	System.out.println("null");
            return plainText;
        }
//    	System.out.println(plainText);


        byte[] key = key_Val.getBytes(StandardCharsets.UTF_8);
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return "\\x" + ByteArrayToString(encryptedBytes);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    public static String ByteArrayToString(byte[] ba) {
        StringBuilder hex = new StringBuilder(ba.length * 2);
        for (byte b : ba) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    private static byte[] HexToByteArray(String hex) {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return bytes;
    }
        
    public static void main(String[] args) {
    	
    	String h="7649855283";
        String encrypted = AESEncrytDecry.Encrypt(h);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = AESEncrytDecry.DecryptAES("\\x255f1883ff812a8603d39695f1cd9592");
        System.out.println("Decrypted: " + decrypted);
        
        System.out.println("email -> "+AESEncrytDecry.Encrypt("9826359752v@gmail.com"));
        System.out.println("mobile -> "+AESEncrytDecry.Encrypt("7649855283"));
        System.out.println("name -> "+AESEncrytDecry.Encrypt("Vishvajeet rajput"));
        System.out.println("account -> "+AESEncrytDecry.Encrypt("50100000835738"));
        System.out.println("short -> "+AESEncrytDecry.Encrypt("KJSST"));
        System.out.println("Customer_Reference1 -> "+AESEncrytDecry.Encrypt("KJ847287374"));
        System.out.println("Customer_Reference2 -> "+AESEncrytDecry.Encrypt("KJ1887348747"));
        System.out.println("UtilCode -> "+AESEncrytDecry.Encrypt("NACH00000000000020"));
    }
 
}	