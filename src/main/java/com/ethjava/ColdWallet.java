package com.ethjava;

import com.ethjava.utils.Environment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 冷钱包
 * 账号 交易相关
 */
public class ColdWallet {

	private static Web3j web3j;

	 private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
	private static String d = "/Users/wallet";

	private static String address = "0xa530d89646db11abfa701e148e87324355fc6ea7";

	private static String keystore = "{\"address\":\"a530d89646db11abfa701e148e87324355fc6ea7\",\"id\":\"246e7d1d-8f31-4a3e-951d-41722213a44f\",\"version\":3,\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"26d10977bc199f6b678e89d3b7c3874bab3cddda18b92c014890d80657d7cc6a\",\"cipherparams\":{\"iv\":\"beaa9a404f793e86460a1fc71a0372a8\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"f06eb3d208db1643671c6e0210789f05e6de1746252fe5b83a38618e2bd18f1e\"},\"mac\":\"0aa4f85dfecaf8203ad0ee22c47ff6fb35b8f47d8f56ddb890ef2d513a06a801\"}}\n";

	private static String privateKey = "f4529331f460fa88cc14eb981baf90201e7fc709386bf2f5b9ec687639f70086";

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		try {
			
			System.out.println(getUTCTimeStr());

			createWallet("11111111");
//			decryptWallet(keystore, "11111111");
//			testTransaction();
//			testTokenTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	   public static String getUTCTimeStr() {
	        StringBuffer UTCTimeBuffer = new StringBuffer();
	        // 1、取得本地时间：
	        Calendar cal = Calendar.getInstance() ;
	        // 2、取得时间偏移量：
	        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
	        // 3、取得夏令时差：
	        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
	        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
	        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
	        int year = cal.get(Calendar.YEAR);
	        int month = cal.get(Calendar.MONTH)+1;
	        int day = cal.get(Calendar.DAY_OF_MONTH);
	        int hour = cal.get(Calendar.HOUR_OF_DAY);
	        int minute = cal.get(Calendar.MINUTE); 
	        float minutes= cal.get(Calendar.MILLISECOND);
	        UTCTimeBuffer.append(year).append("-").append(paresZero(month)).append("-").append(paresZero(day)) ;
	        UTCTimeBuffer.append(" ").append(paresZero(hour)).append(":").append(paresZero(minute)).append(minutes/1000) ;
	        try{
	            format.parse(UTCTimeBuffer.toString()) ;
	            return "UTC--"+ UTCTimeBuffer.toString() ;
	        }catch(ParseException e)
	        {
	            e.printStackTrace() ;
	        }
	        return null ;
	    }
	   
	   private static String paresZero(int num)
	   {
		   String ret =   num<10? String.format("0%d", num):String.format("%d", num);
		   return ret;
	   }

	private static void testTransaction() {
		BigInteger nonce;
		EthGetTransactionCount ethGetTransactionCount = null;
		try {
			ethGetTransactionCount = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).send();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ethGetTransactionCount == null) return;
		nonce = ethGetTransactionCount.getTransactionCount();
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(30000);
		String to = "0x6c0f49aF552F2326DD851b68832730CB7b6C0DaF".toLowerCase();
		BigInteger value = Convert.toWei(BigDecimal.valueOf(0.5), Convert.Unit.ETHER).toBigInteger();
		String data = "";
		byte chainId = ChainId.ROPSTEN;//测试网络
		String privateKey = ColdWallet.privateKey;
		String signedData;
		try {
			signedData = signTransaction(nonce, gasPrice, gasLimit, to, value, data, chainId, privateKey);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				System.out.println(ethSendTransaction.getTransactionHash());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void testTokenTransaction(Web3j web3j, String fromAddress, String privateKey, String contractAddress, String toAddress, double amount, int decimals) {
		BigInteger nonce;
		EthGetTransactionCount ethGetTransactionCount = null;
		try {
			ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING).send();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ethGetTransactionCount == null) return;
		nonce = ethGetTransactionCount.getTransactionCount();
		System.out.println("nonce " + nonce);
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(60000);
		BigInteger value = BigInteger.ZERO;
		//token转账参数
		String methodName = "transfer";
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();
		Address tAddress = new Address(toAddress);
		Uint256 tokenValue = new Uint256(BigDecimal.valueOf(amount).multiply(BigDecimal.TEN.pow(decimals)).toBigInteger());
		inputParameters.add(tAddress);
		inputParameters.add(tokenValue);
		TypeReference<Bool> typeReference = new TypeReference<Bool>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);

		byte chainId = ChainId.NONE;
		String signedData;
		try {
			signedData = ColdWallet.signTransaction(nonce, gasPrice, gasLimit, contractAddress, value, data, chainId, privateKey);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();
				System.out.println(ethSendTransaction.getTransactionHash());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 创建钱包
	 *
	 * @param password 密码
	 */
	public static void createWallet(String password) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, CipherException, JsonProcessingException {
		WalletFile walletFile;
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		walletFile = Wallet.createStandard(password, ecKeyPair);
		System.out.println("address " + walletFile.getAddress());
		System.out.println("keypair:"+ecKeyPair.getPrivateKey());
		System.out.println("keypair:"+ecKeyPair.getPrivateKey().toString(16));
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		String jsonStr = objectMapper.writeValueAsString(walletFile);
		String [] atemArry = jsonStr.split("scrypt");
		if(atemArry.length>2)
		{
			int i = jsonStr.lastIndexOf("kdf");			
			String Json1 = jsonStr.substring(0, i-1);
			String Json2 = jsonStr.substring(i+14);		
			jsonStr=Json1+Json2;	
			
		}
	
		
		System.out.println("keystore json file " + jsonStr);
	}

	/**
	 * 解密keystore 得到私钥
	 *
	 * @param keystore
	 * @param password
	 */
	public static String decryptWallet(String keystore, String password) {
		String privateKey = null;
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		try {
			WalletFile walletFile = objectMapper.readValue(keystore, WalletFile.class);
			ECKeyPair ecKeyPair = null;
			ecKeyPair = Wallet.decrypt(password, walletFile);
			privateKey = ecKeyPair.getPrivateKey().toString(16);
			System.out.println(privateKey);
		} catch (CipherException e) {
			if ("Invalid password provided".equals(e.getMessage())) {
				System.out.println("密码错误");
			}
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	/**
	 * 签名交易
	 */
	public static String signTransaction(BigInteger nonce, BigInteger gasPrice, BigInteger gasLimit, String to,
										 BigInteger value, String data, byte chainId, String privateKey) throws IOException {
		byte[] signedMessage;
		RawTransaction rawTransaction = RawTransaction.createTransaction(
				nonce,
				gasPrice,
				gasLimit,
				to,
				value,
				data);

		if (privateKey.startsWith("0x")) {
			privateKey = privateKey.substring(2);
		}
		ECKeyPair ecKeyPair = ECKeyPair.create(new BigInteger(privateKey, 16));
		Credentials credentials = Credentials.create(ecKeyPair);

		if (chainId > ChainId.NONE) {
			signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
		} else {
			signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		}

		String hexValue = Numeric.toHexString(signedMessage);
		return hexValue;
	}

}
