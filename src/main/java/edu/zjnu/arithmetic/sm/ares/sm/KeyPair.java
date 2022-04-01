/*
 * Copyright (c) 2021 ShangHai P&C Information Technology Co.,Ltd. All rights reserved.
 * 
 * <p>项目名称	:pnc-crypto2</p>
 * <p>包名称    	:cn.com.yitong.util.sm</p>
 * <p>文件名称	:KeyPair.java</p>
 * <p>创建时间	:2021-10-19 15:13:19 </p>
 */

package edu.zjnu.arithmetic.sm.ares.sm;

/**
 * 公私钥对.
 *
 * @author zwb
 */
public class KeyPair {

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
	 * Instantiates a new Key pair.
	 */
	public KeyPair() {

	}

	/**
	 * Instantiates a new Key pair.
	 *
	 * @param privateKeyHex the private key hex
	 * @param publicKeyXHex the public key x hex
	 * @param publicKeyYHex the public key y hex
	 */
	public KeyPair(String privateKeyHex, String publicKeyXHex, String publicKeyYHex) {
		this.privateKeyHex = privateKeyHex;
		this.publicKeyXHex = publicKeyXHex;
		this.publicKeyYHex = publicKeyYHex;
	}

	/**
	 * Gets private key hex.
	 *
	 * @return the private key hex
	 */
	public String getPrivateKeyHex() {
		return privateKeyHex;
	}

	/**
	 * Sets private key hex.
	 *
	 * @param privateKeyHex the private key hex
	 */
	public void setPrivateKeyHex(String privateKeyHex) {
		this.privateKeyHex = privateKeyHex;
	}

	/**
	 * Gets public key x hex.
	 *
	 * @return the public key x hex
	 */
	public String getPublicKeyXHex() {
		return publicKeyXHex;
	}

	/**
	 * Sets public key x hex.
	 *
	 * @param publicKeyXHex the public key x hex
	 */
	public void setPublicKeyXHex(String publicKeyXHex) {
		this.publicKeyXHex = publicKeyXHex;
	}

	/**
	 * Gets public key y hex.
	 *
	 * @return the public key y hex
	 */
	public String getPublicKeyYHex() {
		return publicKeyYHex;
	}

	/**
	 * Sets public key y hex.
	 *
	 * @param publicKeyYHex the public key y hex
	 */
	public void setPublicKeyYHex(String publicKeyYHex) {
		this.publicKeyYHex = publicKeyYHex;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "{\n" + "    \"privateKey\": \"" + privateKeyHex + "\",\n" + "    \"publicKeyX\": \"" + publicKeyXHex
				+ "\",\n" + "    \"publicKeyY\": \"" + publicKeyYHex + "\"\n" + '}';
	}
}
