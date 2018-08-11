package com.ethjava.utils;

/**
 * 运行配置项
 */
public class WalletEnvironment{
	public static String RPC_URL = "https://ropsten.infura.io/";
	//https://api-ropsten.etherscan.io/api?module=transaction&action=getstatus&txhash=0x52199b1436f63322dda52bc7b9efa6e63c21a231825a35b5299236e171e944a0&apikey=YourApiKeyToken
	public static String CheckContractStatus ="https://api-ropsten.etherscan.io/api?module=transaction&action=getstatus&txhash=%s&apikey=YourApiKeyToken";
}
