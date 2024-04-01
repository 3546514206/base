import com.tencent.kona.crypto.KonaCryptoProvider;
import com.tencent.kona.crypto.provider.SM2KeyPairGenerator;
import com.tencent.kona.crypto.spec.SM2PrivateKeySpec;
import com.tencent.kona.crypto.spec.SM2PublicKeySpec;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 *
 */
public class MainTest {

    @Test
    public void testNormal() throws InvalidKeySpecException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SignatureException {
        // 在使用KonaCrypto中的任何特性之前，必须要加载KonaCryptoProvider，
        Security.addProvider(new KonaCryptoProvider());
        KeyFactory keyFactory = KeyFactory.getInstance("SM2");
        // 创建KeyPairGenerator实例,
        SM2KeyPairGenerator sm2KeyPairGenerator = new SM2KeyPairGenerator();
        // 生成随机密钥对。
        KeyPair keyPair = sm2KeyPairGenerator.generateKeyPair();
        // SM2的密钥对本质还是EC密钥对，所以其中的公钥与私钥也分别符合ECPublicKey与ECPrivateKey的属性。
        //SM2公钥的编码长度固定为65字节，其格式为04|x|y，其中04表示非压缩格式，x和y分别为该公钥点在椭圆曲线上的仿射横坐标和纵坐标的值。
        ECPublicKey publicKey1 = (ECPublicKey) keyPair.getPublic();
//            // SM2私钥的编码长度固定为32字节，无编码格式。
        ECPrivateKey privateKey1 = (ECPrivateKey) keyPair.getPrivate();

        byte[] encodedPublicKey = publicKey1.getEncoded();
        byte[] encodedPrivateKey = privateKey1.getEncoded();
        // 将公钥私钥转换输出
        String PrivateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);
        String PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
        System.out.println("Public:" + PublicKey);
        System.out.println("Private:" + PrivateKey);
        // 公钥
        SM2PublicKeySpec publicKeyKeySpec = new SM2PublicKeySpec(encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeyKeySpec);
        // 私钥
        SM2PrivateKeySpec privateKeySpec = new SM2PrivateKeySpec(encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 要加密的数据2
        byte[] data = ("这里是要加密的内容").getBytes("UTF-8");
        // 创建Cipher实例,
        Cipher cipher = Cipher.getInstance("SM2");
        // 使用公钥对Cipher进行初始化，指定其使用加密模式。
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 传入密文生成明文。
        byte[] ciphertext = cipher.doFinal(data);


        // 使用私钥对Cipher进行初始化，指定其使用解密模式。
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String jiamijieguo = Base64.getEncoder().encodeToString(ciphertext);
        System.out.println("加密结果：" + Base64.getEncoder().encodeToString(ciphertext));
        // 传入密文生成明文。
        byte[] cleartext = cipher.doFinal(Base64.getDecoder().decode(jiamijieguo));
        System.out.println("解密结果：" + new String(cleartext, StandardCharsets.UTF_8));


        // 签名
        Signature signature = Signature.getInstance("SM2");
        signature.initSign(privateKey);
        signature.update(cleartext);
        byte[] sign = signature.sign();
        System.out.println("sign:" + Base64.getEncoder().encodeToString(sign));

        // 验签
        signature.initVerify(publicKey);
        signature.update(cleartext);
        boolean verified = signature.verify(sign);
        System.out.println("验签结果：" + verified);

    }
}
