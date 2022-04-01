/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 *
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:SM2.java</p>
 * <p>创建时间	:2021-10-19 15:34:40 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

/**
 * 国密SM2非对称加密算法.
 *
 * @author zwb
 */
public class SM2 {
    /**
     * The Num 16.
     */
    static final int NUM_16 = 16;

    /**
     * 私钥缓存.
     */
    static Map<String, BCECPrivateKey> privateKeyMap = new ConcurrentHashMap<>();

    /**
     * 公钥缓存.
     */
    static Map<String, BCECPublicKey> publicKeyMap = new ConcurrentHashMap<>();

    /**
     * The x 9 EC parameters.
     */
    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");

    /**
     * The ec domain parameters.
     */
    private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(),
            x9ECParameters.getG(), x9ECParameters.getN());

    /**
     * The ec parameter spec.
     */
    private static ECParameterSpec ecParameterSpec = new ECParameterSpec(x9ECParameters.getCurve(),
            x9ECParameters.getG(), x9ECParameters.getN());

    /**
     * 默认公私钥对.
     */
    private static String defaultPrivateKey = "25f5870349ca799b9041f82e268dd943edd376ed60b56eff946a88ebaa9f6d8e";

    /**
     * The default public X.
     */
    private static String defaultPublicX = "7a08325cf5fd16f3bf8257b9abb0f5aae8e57d384aea1334f9de69ad57057132";

    /**
     * The default public Y.
     */
    private static String defaultPublicY = "8c7cd08c6a9ee1945c677d17f09b110da054d19bbe826983d640f91599e0176e";

    /**
     * 默认用户ID.
     */
    private static String defaultUserId = "1234567812345678";

    /**
     * The Constant RS_LEN.
     */
    private static final int RS_LEN = 32;

    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * The private key hex.
     */
    private String privateKeyHex;

    /**
     * The public key X hex.
     */
    private String publicKeyXHex;

    /**
     * The public key Y hex.
     */
    private String publicKeyYHex;

    /**
     * 使用默认公私钥对实例化对象.
     */
    public SM2() {
        this.privateKeyHex = defaultPrivateKey;
        this.publicKeyXHex = defaultPublicX;
        this.publicKeyYHex = defaultPublicY;
    }

    /**
     * 使用自定义私钥实例化对象.
     *
     * @param privateKeyHex 私钥16进制
     */
    public SM2(String privateKeyHex) {
        this.privateKeyHex = privateKeyHex;
        KeyPair keyPair = generateKeyPairByPrivateKey(privateKeyHex);
        this.publicKeyXHex = keyPair.getPublicKeyXHex();
        this.publicKeyYHex = keyPair.getPublicKeyYHex();
    }

    /**
     * 使用自定义公私实例化对象.
     *
     * @param publicKeyXHex 公钥X16进制
     * @param publicKeyYHex 公钥Y16进制
     */
    public SM2(String publicKeyXHex, String publicKeyYHex) {
        this.publicKeyXHex = publicKeyXHex;
        this.publicKeyYHex = publicKeyYHex;
    }

    /**
     * 使用自定义公私钥对实例化对象.
     *
     * @param privateKeyHex 私钥 16进制字符串
     * @param publicKeyXHex 公钥X 16进制字符串
     * @param publicKeyYHex 公钥Y 16进制字符串
     */
    public SM2(String privateKeyHex, String publicKeyXHex, String publicKeyYHex) {
        this.privateKeyHex = privateKeyHex;
        this.publicKeyXHex = publicKeyXHex;
        this.publicKeyYHex = publicKeyYHex;
    }

    /**
     * 构建sm2实例对象.
     *
     * @return the sm 2
     */
    public static SM2 build() {
        return new SM2();
    }

    /**
     * 根据私钥构建sm2实例对象.
     *
     * @param privateKeyHex 私钥 16进制字符串
     * @return the sm 2
     */
    public static SM2 build(String privateKeyHex) {
        return new SM2(privateKeyHex);
    }

    /**
     * 根据公钥构建sm2实例对象.
     *
     * @param publicKeyXHex 公钥X 16进制字符串
     * @param publicKeyYHex 公钥Y 16进制字符串
     * @return the sm 2
     */
    public static SM2 build(String publicKeyXHex, String publicKeyYHex) {
        return new SM2(publicKeyXHex, publicKeyYHex);
    }

    /**
     * 根据公钥构建sm2实例对象.
     *
     * @param privateKeyHex 私钥 16进制字符串
     * @param publicKeyXHex 公钥X 16进制字符串
     * @param publicKeyYHex 公钥Y 16进制字符串
     * @return the sm 2
     */
    public static SM2 build(String privateKeyHex, String publicKeyXHex, String publicKeyYHex) {
        return new SM2(privateKeyHex, publicKeyXHex, publicKeyYHex);
    }

    /**
     * 根据私钥生成公私钥对.
     *
     * @param privateKeyHex 16进制私钥字符串
     * @return the key pair
     */
    public static KeyPair generateKeyPairByPrivateKey(String privateKeyHex) {
        privateKeyHex = privateKeyHex.toLowerCase();
        BigInteger privateKeyBigInteger = new BigInteger(privateKeyHex, NUM_16);
        ECPoint point = generateKeyPublicPoint(privateKeyBigInteger, ecParameterSpec.getG());
        KeyPair keyPair = new KeyPair(privateKeyHex, point.getAffineXCoord().toString(),
                point.getAffineYCoord().toString());
        return keyPair;
    }

    /**
     * Generate key public point.
     *
     * @param t the t
     * @param G the g
     * @return the EC point
     */
    private static ECPoint generateKeyPublicPoint(BigInteger t, ECPoint G) {
        ECPoint Y = null;
        Y = G.multiply(t).normalize();
        if (G.equals(Y)) {
            Y = null;
        }
        return Y;
    }

    /**
     * 生成公私钥对.
     *
     * @return 公私钥对对象 KeyPair
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("EC", "BC");
            kpGen.initialize(ecParameterSpec, new SecureRandom());
            java.security.KeyPair kp = kpGen.generateKeyPair();
            BCECPrivateKey privateKey = (BCECPrivateKey) kp.getPrivate();
            BCECPublicKey publicKey = (BCECPublicKey) kp.getPublic();
            String privateKeyStr = privateKey.getD().toString(NUM_16);
            String publicKeyX = publicKey.getQ().getAffineXCoord().toString();
            String publicKeyY = publicKey.getQ().getAffineYCoord().toString();
            KeyPair keyPair = new KeyPair(privateKeyStr, publicKeyX, publicKeyY);
            return keyPair;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 公钥加密，使用UTF-8解码明文数据.
     *
     * @param data 明文字符串
     * @return 16进制加密字符串 string
     */
    public String encrypt(String data) {
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        return encrypt(dataBytes);
    }

    /**
     * 公钥加密.
     *
     * @param data 二进制字节数据
     * @return 16进制加密字符串 string
     */
    public String encrypt(byte[] data) {
        try {
            BCECPublicKey publicKey = getPublicKeyFromCache(this.publicKeyXHex, this.publicKeyYHex);
            ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(publicKey.getQ(),
                    ecDomainParameters);
            SM2Engine sm2Engine = new SM2Engine();
            sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
            byte[] out = changeC1C2C3ToC1C3C2(sm2Engine.processBlock(data, 0, data.length));
            String hexOut = HexUtil.byte2HexStr(out);
            if (hexOut != null && hexOut.length() > 2) {
                hexOut = hexOut.substring(2);
            }
            return hexOut;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 私钥解密.
     *
     * @param data 16进制密文数据
     * @return 明文字节数据 byte [ ]
     */
    public byte[] decrypt(String data) {
        try {
            data = "04" + data;
            BCECPrivateKey privateKey = getPrivateKeyFromCache(this.privateKeyHex);
            byte[] input = changeC1C3C2ToC1C2C3(HexUtil.hexStr2Bytes(data));
            ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(privateKey.getD(),
                    ecDomainParameters);
            SM2Engine sm2Engine = new SM2Engine();
            sm2Engine.init(false, ecPrivateKeyParameters);
            return sm2Engine.processBlock(input, 0, input.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密数据，并转换为明文字符串，默认使用UTF-8编码.
     *
     * @param data data 16进制密文数据
     * @return string 明文字符串，默认使用UTF-8编码
     */
    public String decryptToString(String data) {
        byte[] output = decrypt(data);
        String plain = new String(output, StandardCharsets.UTF_8);
        return plain;
    }

    /**
     * 私钥签名，明文数据使用UTF-8解码.
     *
     * @param data 明文字符串数据
     * @return 16进制签名数据 string
     */
    public String sign(String data) {
        if (data != null && !data.isEmpty()) {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            return sign(dataBytes);
        }
        return "";
    }

    /**
     * 私钥签名.
     *
     * @param data 明文字节数据
     * @return 16进制签名数据 string
     */
    public String sign(byte[] data) {
        try {
            return HexUtil.byte2HexStr(rsAsn1ToPlainByteArray(signSm3WithSm2Asn1Rs(data)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 公钥验签，明文数据使用UTF-8解码.
     *
     * @param data    明文字符串数据
     * @param signHex 16进制签名数据
     * @return 是否验证通过 boolean
     */
    public boolean verify(String data, String signHex) {
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        return verifySm3WithSm2Asn1Rs(dataBytes, rsPlainByteArrayToAsn1(HexUtil.hexStr2Bytes(signHex)));
    }

    /**
     * 公钥验签.
     *
     * @param data    明文数据
     * @param signHex 16进制签名数据
     * @return 是否验证通过 boolean
     */
    public boolean verify(byte[] data, String signHex) {
        return verifySm3WithSm2Asn1Rs(data, rsPlainByteArrayToAsn1(HexUtil.hexStr2Bytes(signHex)));
    }

    /**
     * Sign sm 3 with sm 2 asn 1 rs byte [ ].
     *
     * @param data the data
     * @return rs in <b>asn1 format</b>
     */
    public byte[] signSm3WithSm2Asn1Rs(byte[] data) {
        try {
            BCECPrivateKey privateKey = getPrivateKeyFromCache(this.privateKeyHex);
            byte[] userId = defaultUserId.getBytes(StandardCharsets.UTF_8);
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature signer = Signature.getInstance("SM3withSM2", "BC");
            signer.setParameter(parameterSpec);
            signer.initSign(privateKey, new SecureRandom());
            signer.update(data, 0, data.length);
            byte[] sig = signer.sign();
            return sig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verify sm 3 with sm 2 asn 1 rs boolean.
     *
     * @param data      the data
     * @param signature the signature
     * @return boolean boolean
     */
    public boolean verifySm3WithSm2Asn1Rs(byte[] data, byte[] signature) {
        try {
            BCECPublicKey publicKey = getPublicKeyFromCache(this.publicKeyXHex, this.publicKeyYHex);
            byte[] userId = defaultUserId.getBytes(StandardCharsets.UTF_8);
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature verifier = Signature.getInstance("SM3withSM2", "BC");
            verifier.setParameter(parameterSpec);
            verifier.initVerify(publicKey);
            verifier.update(data, 0, data.length);
            return verifier.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets private key from cache.
     *
     * @param privateKeyHex the private key hex
     * @return the private key from cache
     */
    static BCECPrivateKey getPrivateKeyFromCache(String privateKeyHex) {
        String key = privateKeyHex;
        BigInteger d = new BigInteger(privateKeyHex, NUM_16);
        if (privateKeyMap.containsKey(key)) {
            return privateKeyMap.get(key);
        }
        ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(d, ecParameterSpec);
        BCECPrivateKey privateKey = new BCECPrivateKey("EC", ecPrivateKeySpec, BouncyCastleProvider.CONFIGURATION);
        privateKeyMap.put(key, privateKey);
        return privateKey;
    }

    /**
     * Gets public key from cache.
     *
     * @param publicKeyXHex the public key x hex
     * @param publicKeyYHex the public key y hex
     * @return the public key from cache
     */
    static BCECPublicKey getPublicKeyFromCache(String publicKeyXHex, String publicKeyYHex) {
        String key = publicKeyXHex + publicKeyYHex;
        if (publicKeyMap.containsKey(key)) {
            return publicKeyMap.get(key);
        }
        BigInteger x = new BigInteger(publicKeyXHex, NUM_16);
        BigInteger y = new BigInteger(publicKeyYHex, NUM_16);
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y),
                ecParameterSpec);
        BCECPublicKey value = new BCECPublicKey("EC", ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
        publicKeyMap.put(key, value);
        return value;
    }

    /**
     * bc加解密使用旧标c1||c2||c3，此方法在加密后调用，将结果转化为c1||c3||c2.
     *
     * @param c1c2c3 字节数组
     * @return byte[] c1c3c2字节数组
     */
    private static byte[] changeC1C2C3ToC1C3C2(byte[] c1c2c3) {
        final int c1Len = (x9ECParameters.getCurve().getFieldSize() + 7) / 8 * 2 + 1; // sm2p256v1的这个固定65。可看GMNamedCurves、ECCurve代码。
        final int c3Len = 32;
        byte[] result = new byte[c1c2c3.length];
        System.arraycopy(c1c2c3, 0, result, 0, c1Len); // c1
        System.arraycopy(c1c2c3, c1c2c3.length - c3Len, result, c1Len, c3Len); // c3
        System.arraycopy(c1c2c3, c1Len, result, c1Len + c3Len, c1c2c3.length - c1Len - c3Len); // c2
        return result;
    }

    /**
     * bc加解密使用旧标c1||c3||c2，此方法在解密前调用，将密文转化为c1||c2||c3再去解密.
     *
     * @param c1c3c2 字节数组
     * @return byte[] c1c2c3字节数组
     */
    private static byte[] changeC1C3C2ToC1C2C3(byte[] c1c3c2) {
        final int c1Len = (x9ECParameters.getCurve().getFieldSize() + 7) / 8 * 2 + 1; // sm2p256v1的这个固定65。可看GMNamedCurves、ECCurve代码。
        final int c3Len = 32;
        byte[] result = new byte[c1c3c2.length];
        System.arraycopy(c1c3c2, 0, result, 0, c1Len); // c1: 0->65
        System.arraycopy(c1c3c2, c1Len + c3Len, result, c1Len, c1c3c2.length - c1Len - c3Len); // c2
        System.arraycopy(c1c3c2, c1Len, result, c1c3c2.length - c3Len, c3Len); // c3
        return result;
    }

    /**
     * BC的SM3withSM2签名得到的结果的rs是asn1格式的，这个方法转化成直接拼接r||s.
     *
     * @param rsDer rs in asn1 format
     * @return sign result in plain byte array
     */
    private static byte[] rsAsn1ToPlainByteArray(byte[] rsDer) {
        ASN1Sequence seq = ASN1Sequence.getInstance(rsDer);
        byte[] r = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(0)).getValue());
        byte[] s = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(1)).getValue());
        byte[] result = new byte[RS_LEN * 2];
        System.arraycopy(r, 0, result, 0, r.length);
        System.arraycopy(s, 0, result, RS_LEN, s.length);
        return result;
    }

    /**
     * BC的SM3withSM2验签需要的rs是asn1格式的，这个方法将直接拼接r||s的字节数组转化成asn1格式.
     *
     * @param sign in plain byte array
     * @return rs result in asn1 format
     */
    private static byte[] rsPlainByteArrayToAsn1(byte[] sign) {
        if (sign.length != RS_LEN * 2) {
            throw new RuntimeException("err rs. ");
        }
        BigInteger r = new BigInteger(1, Arrays.copyOfRange(sign, 0, RS_LEN));
        BigInteger s = new BigInteger(1, Arrays.copyOfRange(sign, RS_LEN, RS_LEN * 2));
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        try {
            return new DERSequence(v).getEncoded("DER");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Big int to fixex length bytes.
     *
     * @param rOrS the r or S
     * @return the byte[]
     */
    private static byte[] bigIntToFixexLengthBytes(BigInteger rOrS) {
        // for sm2p256v1, n is
        // 00fffffffeffffffffffffffffffffffff7203df6b21c6052b53bbf40939d54123,
        // r and s are the result of mod n, so they should be less than n and have
        // length<=32
        byte[] rs = rOrS.toByteArray();
        if (rs.length == RS_LEN) {
            return rs;
        } else if (rs.length == RS_LEN + 1 && rs[0] == 0) {
            return Arrays.copyOfRange(rs, 1, RS_LEN + 1);
        } else if (rs.length < RS_LEN) {
            byte[] result = new byte[RS_LEN];
            Arrays.fill(result, (byte) 0);
            System.arraycopy(rs, 0, result, RS_LEN - rs.length, rs.length);
            return result;
        } else {
            throw new RuntimeException("err rs: " + Hex.toHexString(rs));
        }
    }

}
