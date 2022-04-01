/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:PncCryptoTest.java</p>
 * <p>创建时间	:2021-10-19 15:41:04 </p>
 */

package edu.zjnu.arithmetic.sm.ares.test.java.cn.com.yitong.util.sm;

import java.nio.charset.StandardCharsets;

import edu.zjnu.arithmetic.sm.ares.sm.HexUtil;
import edu.zjnu.arithmetic.sm.ares.sm.PncCrypto;
import org.junit.jupiter.api.Test;

/**
 * The Class PncCryptoTest.
 */
public class PncCryptoTest {

    /**
     * Variety sm 4 encrypt.
     */
    @Test
    public void varietySm4EncryptTest() {
        // 明文
        String plain = "我是中国人abc123";
        // key 字符串，16个字节长度的字符串
        String key = "1234567890abcdef";

        byte[] data = plain.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        byte[] cipher = PncCrypto.varietySm4Encrypt(keyBytes, data);
        System.out.println(HexUtil.byte2HexStr(cipher));
        // output:C0D9DE1E1CED09BA44CBDA537D3E22107048390A5B4F8BF2759DA92EEFBFE6F453EFBF6E16AB8B170063298C67D312ED113BE48AD9D7D47AD067F3C730FD6BBD
    }

    /**
     * Variety sm 4 decrypt.
     */
    @Test
    public void varietySm4DecryptTest() {
        // key 字符串，16个字节长度的字符串
        String key = "1234567890abcdef";

        String cipherHex = "C0D9DE1E1CED09BA44CBDA537D3E22107048390A5B4F8BF2759DA92EEFBFE6F453EFBF6E16AB8B170063298C67D312ED113BE48AD9D7D47AD067F3C730FD6BBD";
        byte[] cipherBytes = HexUtil.hexStr2Bytes(cipherHex);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        byte[] data = PncCrypto.varietySm4Decrypt(keyBytes, cipherBytes);
        System.out.println(new String(data, StandardCharsets.UTF_8));
        // output:我是中国人abc123
    }

    /**
     * Req encrypt test.
     */
    @Test
    public void reqEncryptTest() {
        // 此公私钥对用于加解密 sm4对称密钥
        // String aPrivateKey = "849f592733edcac574855c25c01ea2ee71c173bc773647c23d4d195a78ea5a61";
        String aPublicKeyX = "0d5fd700cce099087ea9e8b0887b0a5c3e08ec36a532cdc7ec4270c7d2f50ca1";
        String aPublicKeyY = "62f3c6577eac0baaad2fb65f09029d6e2c067bdf5f266c6de4c9bd1c16d5d035";

        // 此公私钥对用于 明文数据签名验签
        String bPrivateKey = "C97F7901E6F3C3A033C1392DD01A5D7A6D46DC61EE5BBE87B7DFCA606EC51E34";
        // String bPublicKeyX =
        // "3E3A5AF830DE527BB16877077413D3576586C9B8E86CD3AA9447E0BBB90F4767";
        // String bPublicKeyY =
        // "886CBA348AAA79F57C1722DB085858EEA86004887F3CCF6051833AAD5CADE904";

        // 生成加密实例对象
        PncCrypto clientInstance = PncCrypto.buildClientInstance(aPublicKeyX, aPublicKeyY, bPrivateKey);

        // 生成随机加密key
        String key = PncCrypto.generateKey();
        // 明文数据
        String plain = "我是中国人abc123";

        String cipher = clientInstance.reqEncrypt(key,plain);
        System.out.println(cipher);
        // output:#30503D728DB0BA415B4C844B615FA9F2D3796E346DA2007C93A50EB12B9573A4E01DDCADE6DDEF1204767E3BEB4A6842D32CDE45A6EB29A9CEE7FC3130D6DF8C06DEF7565832E56110C613D84D6B6E8E9FC68CE3867625E7EB479F7AF3AAC4F9FAB776FAAED3532C8FCB9AB180BBC6EFA4AD9CFA2DBD38F769294AE408A39884004885B3B43EC677F1B0CF7C0857F04D020A7944A196B840FCCDA29550F1F1220B00509BE4C8055D5DC0505B141131066F6C1C8D250367A7A7AC3584590034CA4E62A63FA4F31B8A03CC2FD5CCAC7E962CEBFB618F1242F2C4888CB5D96A727F6246AEA587AF173A0EE27EE9F61A99A35F

    }

    /**
     * Req decrypt.
     */
    @Test
    public void reqDecryptTest() {
        // 此公私钥对用于加解密 sm4对称密钥
        String aPrivateKey = "849f592733edcac574855c25c01ea2ee71c173bc773647c23d4d195a78ea5a61";
        // String aPublicKeyX = "0d5fd700cce099087ea9e8b0887b0a5c3e08ec36a532cdc7ec4270c7d2f50ca1";
        // String aPublicKeyY = "62f3c6577eac0baaad2fb65f09029d6e2c067bdf5f266c6de4c9bd1c16d5d035";

        // 此公私钥对用于 明文数据签名验签
        // String bPrivateKey = "C97F7901E6F3C3A033C1392DD01A5D7A6D46DC61EE5BBE87B7DFCA606EC51E34";
        String bPublicKeyX = "3E3A5AF830DE527BB16877077413D3576586C9B8E86CD3AA9447E0BBB90F4767";
        String bPublicKeyY = "886CBA348AAA79F57C1722DB085858EEA86004887F3CCF6051833AAD5CADE904";

        // 生成解密实例对象
        PncCrypto serverInstance = PncCrypto.buildServerInstance(aPrivateKey, bPublicKeyX, bPublicKeyY);
        String cipher = "#30503D728DB0BA415B4C844B615FA9F2D3796E346DA2007C93A50EB12B9573A4E01DDCADE6DDEF1204767E3BEB4A6842D32CDE45A6EB29A9CEE7FC3130D6DF8C06DEF7565832E56110C613D84D6B6E8E9FC68CE3867625E7EB479F7AF3AAC4F9FAB776FAAED3532C8FCB9AB180BBC6EFA4AD9CFA2DBD38F769294AE408A39884004885B3B43EC677F1B0CF7C0857F04D020A7944A196B840FCCDA29550F1F1220B00509BE4C8055D5DC0505B141131066F6C1C8D250367A7A7AC3584590034CA4E62A63FA4F31B8A03CC2FD5CCAC7E962CEBFB618F1242F2C4888CB5D96A727F6246AEA587AF173A0EE27EE9F61A99A35F";

        String plain = serverInstance.reqDecrypt(cipher);
        System.out.println(plain);
        // output: 我是中国人abc123
    }

    /**
     * Resp encrypt.
     */
    @Test
    public void respEncrypt() {
        String key = "1234567890abcdef";
        String plain = "我是中国人abc123";
        String cipher = PncCrypto.respEncrypt(key, plain);
        System.out.println(cipher);
        // output:109093E2000040F143B2CB75AE18B4AB3F2E654098908DFAE36109615EE5087AB9366B9D203B9AA30DC239
    }

    /**
     * Resp decrypt.
     */
    @Test
    public void respDecrypt() {
        String key = "1234567890abcdef";
        String cipher = "109093E2000040F143B2CB75AE18B4AB3F2E654098908DFAE36109615EE5087AB9366B9D203B9AA30DC239";
        String plain = PncCrypto.respDecrypt(key, cipher);
        System.out.println(plain);
        // output: 我是中国人abc123
    }
}