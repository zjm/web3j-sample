package com.chuangshi.cloud.blockart.ethereumwallet;

/**
 * 运行配置项
 */
public class WalletEnvironment{
	public static String RPC_URL = "https://ropsten.infura.io/";
	public static String CheckContractStatus ="https://api-ropsten.etherscan.io/api?module=transaction&action=getstatus&txhash=%s&apikey=YourApiKeyToken";
	//public static String TransactionReceiptURLMainNet ="https://api.etherscan.io/api?module=proxy&action=eth_getTransactionReceipt&txhash=%s&apikey=YourApiKeyToken";
	//public static String TransactionReceiptURLTestNet ="https://api.etherscan.io/api?module=proxy&action=eth_getTransactionReceipt&txhash=%s&apikey=YourApiKeyToken";
	public static String TransactionReceiptURL ="https://api-ropsten.etherscan.io/api?module=proxy&action=eth_getTransactionReceipt&txhash=%s&apikey=YourApiKeyToken";
}
