/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:SM2Test.java</p>
 * <p>创建时间	:2021-10-19 15:56:35 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test.java.cn.com.yitong.util.sm;

import java.nio.charset.StandardCharsets;

import edu.zjnu.arithmetic.sm.ares.sm.KeyPair;
import edu.zjnu.arithmetic.sm.ares.sm.SM2;
import org.junit.jupiter.api.Test;

/**
 * The type Sm2 test.
 */
public class SM2Test {

    /**
     * 测试生成公私钥对.
     */
    @Test
    public void testGenerateKeyPair() {
        KeyPair keyPair = SM2.generateKeyPair();
        System.out.println(keyPair);
    }

    /**
     * 测试根据私钥生成公钥.
     */
    @Test
    public void testGenerateKeyPairByPrivateKey() {
        String privateKeyHex = "a1256ad0541ecbc5ac588c99e790a15de07e8ff17e70379aed005b8322dd7b0d";
        System.out.println(SM2.generateKeyPairByPrivateKey(privateKeyHex));
    }

    /**
     * 使用默认公私钥加密数据
     */
    @Test
    public void testEncrypt() {
        // 使用默认公私钥对创建实例
        SM2 sm2 = SM2.build();
        // 加密原文
        String source = "我是中国人abc123";
        // 加密数据
        String cipherData = sm2.encrypt(source);
        System.out.println(cipherData);
    }

    /**
     * 使用自定义公钥加密数据
     */
    @Test
    public void testEncryptCustomPublicKey() {
        // 自定义公私钥对
        // 私钥： 0d5209059bd4de15bc16f10b14e3c649c38a339ac7e6a397af65e3ffd8dacaaa
        // 公钥X: c009c50aa23bff7db96098c33f737d6b9ad7d28c983814d3f6deab11c9ae3051
        // 公钥Y: e5c3e997bedd3e5318fd49d7cfa196b3d334885cc148b6eb3a759a8114ceb9e4

        // 使用自定义公钥创建实例
        String publicKeyX = "c009c50aa23bff7db96098c33f737d6b9ad7d28c983814d3f6deab11c9ae3051";
        String publicKeyY = "e5c3e997bedd3e5318fd49d7cfa196b3d334885cc148b6eb3a759a8114ceb9e4";
        SM2 sm2 = SM2.build(publicKeyX, publicKeyY);
        // 加密原文
        String source = "我是中国人abc123";
        // 加密数据
        String cipherData = sm2.encrypt(source);
        System.out.println(cipherData);
    }

    /**
     * 使用默认私钥解密
     */
    @Test
    public void testDecrypt() {
        // 使用默认公私钥对解密数据
        SM2 sm2 = SM2.build();
        // 加密数据
        String cipherData = "96F798BB20275B7A59A9EE97A7BA3DF96B7205EBE7083715B72FA23EEA1C85B3ADD60B8E49B6422A870EA35C22B20E3FBD4E3C81EE97F3809F313863FD70FE2F21226FEBE451C7C2DBBCABA5FE7FB931B76112013A2FB4C1E70F9761B6C7C18C26F27E921BC6E890502C3B36E016F096CBFD2E3CB3";
        // 解密数据
        byte[] plainData = sm2.decrypt(cipherData);
        String plain = new String(plainData, StandardCharsets.UTF_8);
        System.out.println(plain);
        // output: 我是中国人abc123
    }

    /**
     * 使用自定义私钥解密
     */
    @Test
    public void testDecryptCustomPrivateKey() {
        // 自定义公私钥对
        // 私钥： 0d5209059bd4de15bc16f10b14e3c649c38a339ac7e6a397af65e3ffd8dacaaa
        // 公钥X: c009c50aa23bff7db96098c33f737d6b9ad7d28c983814d3f6deab11c9ae3051
        // 公钥Y: e5c3e997bedd3e5318fd49d7cfa196b3d334885cc148b6eb3a759a8114ceb9e4

        // 使用自定义私钥创建实例
        String privateKey = "0d5209059bd4de15bc16f10b14e3c649c38a339ac7e6a397af65e3ffd8dacaaa";
        SM2 sm2 = SM2.build(privateKey);
        // 加密数据
        String cipherData = "EA11B8B824C8CFF4D0A20C7EA28E27D348060758299420D80E30259FF25BA4B078FAF9D4E92C6BF0CFF25CAB194F1D1EBF92DB1C9D8C1A5EEFCE8C66D2F68E9EE5D3E6068592247877A0A8E6DCB049F92D1CA712631E0D50EE2CB91B92B47490D4B96DC66B79F2614C652048EA45FC1FE7B3E3CF65";
        // 解密数据，并转换为明文字符串，默认使用UTF-8编码
        String plain = sm2.decryptToString(cipherData);
        System.out.println(plain);
        // output: 我是中国人abc123
    }

    /**
     * 使用自定义公钥加密数据
     */
    @Test
    public void testEncryptCustomPublicKey2() {
        // 自定义公私钥对
        // 私钥： 849f592733edcac574855c25c01ea2ee71c173bc773647c23d4d195a78ea5a61
        // 公钥X: 0d5fd700cce099087ea9e8b0887b0a5c3e08ec36a532cdc7ec4270c7d2f50ca1
        // 公钥Y: 62f3c6577eac0baaad2fb65f09029d6e2c067bdf5f266c6de4c9bd1c16d5d035

        // 使用自定义公钥创建实例
        String publicKeyX = "0d5fd700cce099087ea9e8b0887b0a5c3e08ec36a532cdc7ec4270c7d2f50ca1";
        String publicKeyY = "62f3c6577eac0baaad2fb65f09029d6e2c067bdf5f266c6de4c9bd1c16d5d035";
        SM2 sm2 = SM2.build(publicKeyX, publicKeyY);
        // 加密原文
        String source = "L2uUIQwGwDmpEU2j";
        // 加密数据
        String cipherData = sm2.encrypt(source);
        System.out.println(cipherData);
    }

    /**
     * 使用自定义私钥解密
     */
    @Test
    public void testDecryptCustomPrivateKey2() {
        // 自定义公私钥对
        // 私钥： 849f592733edcac574855c25c01ea2ee71c173bc773647c23d4d195a78ea5a61
        // 公钥X: 0d5fd700cce099087ea9e8b0887b0a5c3e08ec36a532cdc7ec4270c7d2f50ca1
        // 公钥Y: 62f3c6577eac0baaad2fb65f09029d6e2c067bdf5f266c6de4c9bd1c16d5d035

        // 使用自定义私钥创建实例
        String privateKey = "849f592733edcac574855c25c01ea2ee71c173bc773647c23d4d195a78ea5a61";
        SM2 sm2 = SM2.build(privateKey);
        // 加密数据
        String cipherData = "F6C4302584967C8176EA2968D7182BEB88BEB5F2C8B38CE29A7809E8A9513C8883AED3A0107653A8FCCBBA7C493736A402525C27467F786735CBC3179A691ED3AD7D80593F7EA460249231C21FC20F93CF87F46B6EDA1EE53573CE9EEDF87B3CD59D65068E5B750247A65747525C9961";
        // 解密数据，并转换为明文字符串，默认使用UTF-8编码
        String plain = sm2.decryptToString(cipherData);
        System.out.println(plain);
        // output: 我是中国人abc123
    }

    /**
     * 使用默认私钥签名数据
     */
    @Test
    public void testSign() {
        // 使用默认私钥签名数据
        SM2 sm2 = SM2.build();
        // 待签名数据
        String source = "我是中国人abc123";
        // 数据签名
        String sign = sm2.sign(source);
        System.out.println(sign);
        // output:875F54BA4591B87C02DECAB7ABF876EABA43C0DEA40CC3777D067658F8693E0F28A836838032365F155B5C6D17C5C54D15847C0AA1E08B1459614FC2CEE63FE7
    }

    /**
     * 使用自定义私钥签名
     */
    @Test
    public void testSignCustomPrivateKey() {
        // 自定义公私钥对
        // 私钥： 0d5209059bd4de15bc16f10b14e3c649c38a339ac7e6a397af65e3ffd8dacaaa
        // 公钥X: c009c50aa23bff7db96098c33f737d6b9ad7d28c983814d3f6deab11c9ae3051
        // 公钥Y: e5c3e997bedd3e5318fd49d7cfa196b3d334885cc148b6eb3a759a8114ceb9e4

        // 使用自定义私钥创建实例
        String privateKey = "0d5209059bd4de15bc16f10b14e3c649c38a339ac7e6a397af65e3ffd8dacaaa";
        SM2 sm2 = SM2.build(privateKey);
        // 待签名数据
        String source = "我是中国人abc123";
        // 数据签名
        String sign = sm2.sign(source);
        System.out.println(sign);
        // output:031A6D9CBC4F07E5DEE909E6A4367A9968FDFB97EABE45DF5D090F7BDB80F0780229A6CE97C59634BBBCA43F29F17DCB4BE19B5B4623A66FF8D5632F04937207
    }

    /**
     * 使用默认公钥验证签名
     */
    @Test
    public void testVerify() {
        // 使用默认公钥验证签名
        SM2 sm2 = SM2.build();
        // 原文数据
        String source = "我是中国人abc123";
        // 签名数据
        String sign = "875F54BA4591B87C02DECAB7ABF876EABA43C0DEA40CC3777D067658F8693E0F28A836838032365F155B5C6D17C5C54D15847C0AA1E08B1459614FC2CEE63FE7";
        // 验证结果
        boolean result = sm2.verify(source, sign);
        System.out.println(result);
        // output: true
    }

    /**
     * 使用自定义公钥验证签名.
     */
    @Test
    public void testVerifyCustomPublicKey() {
        // 自定义公私钥对
        // 私钥： 0d5209059bd4de15bc16f10b14e3c649c38a339ac7e6a397af65e3ffd8dacaaa
        // 公钥X: c009c50aa23bff7db96098c33f737d6b9ad7d28c983814d3f6deab11c9ae3051
        // 公钥Y: e5c3e997bedd3e5318fd49d7cfa196b3d334885cc148b6eb3a759a8114ceb9e4

        // 使用自定义公钥创建实例
        String publicKeyX = "c009c50aa23bff7db96098c33f737d6b9ad7d28c983814d3f6deab11c9ae3051";
        String publicKeyY = "e5c3e997bedd3e5318fd49d7cfa196b3d334885cc148b6eb3a759a8114ceb9e4";
        SM2 sm2 = SM2.build(publicKeyX, publicKeyY);

        // 原文数据
        String source = "我是中国人abc123";
        // 签名数据
        String sign = "7c8e5eeb2101acf3fadcbd700fd54bde006fae31090fc182b20ebffef649eab7c3250884c7e7ea9b17ce2618c1eea4f530b8c2738b118cc987ff59f363b8561a";
        // 验证结果
        boolean result = sm2.verify(source, sign);
        System.out.println(result);
        // output: true
    }
}