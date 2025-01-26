import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;


/**
 * BouncyCastleExample
 *
 * @Date 2025-01-26 09:50
 * @Author 杨海波
 **/
public class BouncyCastleExample {

    public static void main(String[] args) throws Exception {
        while (true) {
            // 执行业务
            doEncrypt();

            // 打印  安全提供者的注册情况
            // showsProvidersInfo();

            // 统计当前安全提供者的数量
            showsProvidersLength();
        }
    }

    private static void doEncrypt() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 注册 BouncyCastle 安全提供者
        Security.addProvider(new BouncyCastleProvider());

        // 创建AES密钥生成器，并指定使用BC作为提供者
        KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");
        keyGen.init(128); // 设置密钥长度

        // 或者你可以直接创建一个密钥
        byte[] keyBytes = new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f}; // 示例密钥数据
        SecretKey fixedKey = new SecretKeySpec(keyBytes, "AES");

        // 初始化加密器
        // 使用BouncyCastle提供的填充方式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, fixedKey);

        // 要加密的数据
        byte[] dataToEncrypt = "Hello, World!".getBytes();

        // 执行加密
        byte[] encryptedData = cipher.doFinal(dataToEncrypt);
        System.out.println("Encrypted: " + new String(encryptedData));

        // 解密
        cipher.init(Cipher.DECRYPT_MODE, fixedKey);
        byte[] decryptedData = cipher.doFinal(encryptedData);
        System.out.println("Decrypted: " + new String(decryptedData));

    }

    private static void showsProvidersInfo() {
        Provider[] providers = Security.getProviders();
        System.out.println("Number of providers: " + providers.length);
        for (Provider provider : providers) {
            System.out.println("Provider: " + provider.getName() + " version " + provider.getVersion());
            System.out.println("Info: " + provider.getInfo());

        }
        System.out.println();
    }

    private static void showsProvidersLength() {
        int providerCount = Security.getProviders().length;
        System.out.println("Number of providers: " + providerCount);
        System.out.println();
    }

}