package com.ethjava;

import com.ethjava.sol.Ballot;
import com.ethjava.utils.Environment;
import com.ethjava.utils.FileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.*;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.concurrent.ExecutionException;

/**
 * 冷钱包 账号 交易相关
 */
public class ColdWallet {

	private static Web3j web3j;

	// private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static String dirPath = "D:\\Users\\wallet\\Ethereum";
	private static String fileName = "UTC--2018-08-06T11-14-51.908Z--09beea460ac60fd68275eeff7eeacbfd1fa1bf4e";

	private static String address = "0xa530d89646db11abfa701e148e87324355fc6ea7";

	private static String keystore = "{\"address\":\"a530d89646db11abfa701e148e87324355fc6ea7\",\"id\":\"246e7d1d-8f31-4a3e-951d-41722213a44f\",\"version\":3,\"crypto\":{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"26d10977bc199f6b678e89d3b7c3874bab3cddda18b92c014890d80657d7cc6a\",\"cipherparams\":{\"iv\":\"beaa9a404f793e86460a1fc71a0372a8\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"f06eb3d208db1643671c6e0210789f05e6de1746252fe5b83a38618e2bd18f1e\"},\"mac\":\"0aa4f85dfecaf8203ad0ee22c47ff6fb35b8f47d8f56ddb890ef2d513a06a801\"}}\n";

	private static String privateKey = "f4529331f460fa88cc14eb981baf90201e7fc709386bf2f5b9ec687639f70086";

	private static String encryptKey = "12345678";
	private static String  contractVoteAddress = "0x4b0000b54761A5CdB3B905b35a7fE75F4a8a8f6C";		

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		try {

			// createWallet("11111111");
			// decryptWallet(keystore, "11111111");
//			testTransaction();
//			testTokenTransaction();
			// Vote(new BigInteger("0"),new BigInteger("0"),new BigInteger("0"));
			/*String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
			BigInteger activeId = new BigInteger("0");
			BigInteger artistId = new BigInteger("1");
			BigInteger ticket = new BigInteger("12");
			VoteSyn(activeId, activeId, ticket, prikey);*/
			//getArtisVoteCount(new BigInteger("0"),new BigInteger("1"));
			//getVoterVoteCount(new BigInteger("0"),"0x579503Df81E09f304eB8a0ef843aebbE4b9ab72e");
			//1,["10","11","12","13"],["0xa0a","0xa1b","0xa32a","0xa33a"] activityId, List<BigInteger> artistIds, List<byte[]> proposalNames)
			List<BigInteger> artistIds = new ArrayList<>();
			artistIds.add(new BigInteger("10"));
			artistIds.add(new BigInteger("11"));
			artistIds.add(new BigInteger("12"));
			artistIds.add(new BigInteger("13"));
			
			List<byte[]> names = new ArrayList<>();
			names.add("1a1".getBytes());
			names.add("1a2".getBytes());
			names.add("1a3".getBytes());
			names.add("1a4".getBytes());
			
			//setVotedArtist(new BigInteger("2"),artistIds);
			setArtistInfoSyn(new BigInteger("4"),artistIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getUTCTimeStr() {
		StringBuffer UTCTimeBuffer = new StringBuffer();
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millsecond = cal.get(Calendar.MILLISECOND);

		UTCTimeBuffer.append(year).append("-").append(paresZero(month)).append("-").append(paresZero(day));
		UTCTimeBuffer.append("T").append(paresZero(hour)).append("-").append(paresZero(minute)).append("-")
				.append(second).append(".").append(millsecond).append("Z").append("--");
		return "UTC--" + UTCTimeBuffer.toString();
	}

	private static String paresZero(int num) {
		String ret = num < 10 ? String.format("0%d", num) : String.format("%d", num);
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
		if (ethGetTransactionCount == null)
			return;
		nonce = ethGetTransactionCount.getTransactionCount();
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(30000);
		String to = "0x02B7467c6Df772A7D3B8C346afD6DA4923e9B16A".toLowerCase();
		BigInteger value = Convert.toWei(BigDecimal.valueOf(0.5), Convert.Unit.ETHER).toBigInteger();
		String data = "";
		byte chainId = ChainId.ROPSTEN;// 测试网络
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

	private static void testTokenTransaction(Web3j web3j, String fromAddress, String privateKey, String contractAddress,
			String toAddress, double amount, int decimals) {
		BigInteger nonce;
		EthGetTransactionCount ethGetTransactionCount = null;
		try {
			ethGetTransactionCount = web3j.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.PENDING)
					.send();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ethGetTransactionCount == null)
			return;
		nonce = ethGetTransactionCount.getTransactionCount();
		System.out.println("nonce " + nonce);
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(60000);
		BigInteger value = BigInteger.ZERO;
		// token转账参数
		String methodName = "transfer";
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();
		Address tAddress = new Address(toAddress);
		Uint256 tokenValue = new Uint256(
				BigDecimal.valueOf(amount).multiply(BigDecimal.TEN.pow(decimals)).toBigInteger());
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
			signedData = ColdWallet.signTransaction(nonce, gasPrice, gasLimit, contractAddress, value, data, chainId,
					privateKey);
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
	public static void createWallet(String password) throws InvalidAlgorithmParameterException,
			NoSuchAlgorithmException, NoSuchProviderException, CipherException, JsonProcessingException {
		WalletFile walletFile;
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		walletFile = Wallet.createStandard(password, ecKeyPair);
		System.out.println("address " + walletFile.getAddress());
		System.out.println("keypair:" + ecKeyPair.getPrivateKey());
		System.out.println("keypair:" + ecKeyPair.getPrivateKey().toString(16));
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		String jsonStr = objectMapper.writeValueAsString(walletFile);
		String[] atemArry = jsonStr.split("scrypt");
		if (atemArry.length > 2) {
			int i = jsonStr.lastIndexOf("kdf");
			String Json1 = jsonStr.substring(0, i - 1);
			String Json2 = jsonStr.substring(i + 14);
			jsonStr = Json1 + Json2;

		}
		FileUtils.makeDir(dirPath);
		String fileName = String.format("%s%s", getUTCTimeStr(), walletFile.getAddress()); // dirPath+File.separator+
																							// getUTCTimeStr() +
																							// walletFile.getAddress();
		String testFile = dirPath + File.separator + "temp";
		// FileUtils.saveFileByStr(testFile, jsonStr);
		FileUtils.encryptFile(fileName, jsonStr);
		FileUtils.decrypt(fileName, fileName);
		String dencryptStr = FileUtils.readKeyPairFromFile(fileName);
		decryptWallet(dencryptStr, "11111111");

//		 byte[] deMsgBytes1 = FileUtils.decrypt();
//	     System.out.println("文件解密后："+new String(deMsgBytes1));

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
		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);

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

	private static BigInteger getNonce(String address) {

		EthGetTransactionCount ethGetTransactionCount = null;
		try {
			ethGetTransactionCount = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).send();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ethGetTransactionCount == null) {
			return new BigInteger("0");
		}

		return ethGetTransactionCount.getTransactionCount();

	}

	public static void createWeb3j() {
		if (web3j == null) {
			web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		}
	}

	/**
	 * * 投票
	 */
	public static String Vote(BigInteger activId, BigInteger artisid, BigInteger ticket) {
		createWeb3j();
		//String contractAddress = "0x170f5C9CAf03b9936a36bd2Fb1da653D64eB3E29";
		int max = 10;
		int min = 2;
		int randomNumber = (int) Math.round(Math.random() * (max) + min);

		System.out.println("10~100之间随机数：" + randomNumber);

		String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
		Credentials credentials = Credentials.create(prikey);// 可以根据私钥生成
		String address = credentials.getAddress();

		System.out.println("createWeb3jaddress--:" + address);
		Ballot contract = Ballot.load(contractVoteAddress, web3j, credentials,
				Convert.toWei(String.valueOf(randomNumber), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		try {
			BigInteger activeId = new BigInteger("0");
			BigInteger artistId = new BigInteger("1");
			BigInteger tickets = new BigInteger("12");
			TransactionReceipt receipt = contract.vote(activeId, artistId, tickets).sendAsync().get();
			//System.out.println("transHash:" + receipt.getTransactionHash());
			return receipt.getTransactionHash();

			

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * @param activeIds 投票期Id
	 * @param artistIds 艺术家Id
	 * @param tickets 票数
	 * @param prikey 私钥
	 * @return
	 */
	public static String VoteSyn(BigInteger activeIds, BigInteger artistIds, BigInteger tickets, String prikey) {
		createWeb3j();
		//String contractAddress = "0x170f5C9CAf03b9936a36bd2Fb1da653D64eB3E29";		
		Credentials credentials = Credentials.create(prikey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		try {

			BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(10), Convert.Unit.GWEI).toBigInteger();
			BigInteger gasLimit = BigInteger.valueOf(55360);
			BigInteger value = BigInteger.ZERO;
			// vote投票
			String methodName = "vote";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			inputParameters.add(new Uint256(activeIds));
			inputParameters.add(new Uint256(artistIds));
			inputParameters.add(new Uint256(tickets));
			String hash = "0xa74970a2120d060fe8f3e4ddb12437143365ccee8ee56708b748a27bfa26311e";
			
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);
			String data = FunctionEncoder.encode(function);
			byte chainId = ChainId.ROPSTEN;
			String signedData;			
			signedData = signTransaction(getNonce(address), gasPrice, gasLimit, contractVoteAddress, value, data, chainId, prikey);
			String hashret = "";
			if (signedData != null) {
				EthSendTransaction et = web3j.ethSendRawTransaction(signedData).send();
				hashret = et.getTransactionHash();
				// System.out.println("ethSendTransaction:"+et.getTransactionHash());
				if (et.hasError()) {
					System.out.println("err:" + et.getError().getMessage());
				}
				
			}

			return hashret;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	public static String setArtistInfoSyn(BigInteger activityId, List<BigInteger> artistIds) {
		createWeb3j();
		//String contractAddress = "0x170f5C9CAf03b9936a36bd2Fb1da653D64eB3E29";	
		String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
		Credentials credentials = Credentials.create(prikey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		try {

			BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(10), Convert.Unit.GWEI).toBigInteger();
			BigInteger gasLimit = BigInteger.valueOf(553600);//537368
			BigInteger value = BigInteger.ZERO;
			// vote投票
			String methodName = "setVoteArtis";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			inputParameters.add(new Uint256(activityId));	
			/**
			 * new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(artistIds, org.web3j.abi.datatypes.generated.Uint256.class)))
			 */
			//inputParameters.addAll(artistIds);
			inputParameters.add(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                    org.web3j.abi.Utils.typeMap(artistIds, org.web3j.abi.datatypes.generated.Uint256.class)));
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);
			String data = FunctionEncoder.encode(function);
			byte chainId = ChainId.ROPSTEN;
			String signedData;			
			signedData = signTransaction(getNonce(address), gasPrice, gasLimit, contractVoteAddress, value, data, chainId, prikey);
			String hashret = "";
			if (signedData != null) {
				EthSendTransaction et = web3j.ethSendRawTransaction(signedData).send();
				hashret = et.getTransactionHash();
				System.out.println("ethSendTransaction:"+hashret);
				if (et.hasError()) {
					System.out.println("err:" + et.getError().getMessage());
				}
				
			}

			return hashret;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	  *设置投票艺术家 id
	 * @param activityId
	 * @param artistIds
	 * @param tickets
	 * @param prikey
	 * @return
	 */
	public static String setVotedArtist(BigInteger activityId, List<BigInteger> artistIds) {
		createWeb3j();
		//String contractAddress = "0x170f5C9CAf03b9936a36bd2Fb1da653D64eB3E29";			
		String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
		Credentials credentials = Credentials.create(prikey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		System.out.println("createWeb3jaddress--:" + address);
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(10), Convert.Unit.GWEI).toBigInteger();
		BigInteger gasLimit = BigInteger.valueOf(537368);
		Ballot contract = Ballot.load(contractVoteAddress, web3j, credentials,gasPrice,	gasLimit);
		try {		
			//BigInteger value = BigInteger.ZERO;
			String hash = "";
			
			TransactionReceipt receipt = contract.setVoteArtis(activityId, artistIds).sendAsync().get();
			hash = receipt.getTransactionHash();
			System.out.println("hash:"+hash);
			return hash;

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("msg:"+e.getMessage());
			return e.getMessage()+" or activity already exist";
		}
	}
	
	
	/**
	 * 
	 * @param activeId 活动ID
	 * @param artistId 艺术家ID
	 * @return 获取艺术家所得票数
	 */
	public static int getArtisVoteCount(BigInteger activeId, BigInteger artistId) {
		String methodName = "getArtisVoteCount";
		String fromAddr = "0x0000000000000000000000000000000000000000";
		int decimal = 0;
		List<Type> inputParameters = new ArrayList<>();
		inputParameters.add(new Uint256(activeId));
		inputParameters.add(new Uint256(artistId));		
		List<TypeReference<?>> outputParameters = new ArrayList<>();
       
		TypeReference<Uint8> typeReference = new TypeReference<Uint8>() {
		};
		outputParameters.add(typeReference);

		Function function = new Function(methodName, inputParameters, outputParameters);

		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractVoteAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			System.out.println("result:"+results.get(0).getValue().toString());
			decimal = Integer.parseInt(results.get(0).getValue().toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return decimal;
	}
	

	/**
	 * 
	 * @param activeId 活动ID
	 * @param artistId 艺术家ID
	 * @return 获取某个地址投票的数量
	 */
	public static int getVoterVoteCount(BigInteger activeId, String _addr) {
		String methodName = "getVoterVoteCount";
		String fromAddr = "0x0000000000000000000000000000000000000000";
		int decimal = 0;
		Address addr = new Address(_addr);
		List<Type> inputParameters = new ArrayList<>();
		inputParameters.add(new Uint256(activeId));
		inputParameters.add(addr);		
		List<TypeReference<?>> outputParameters = new ArrayList<>();
       
		TypeReference<Uint8> typeReference = new TypeReference<Uint8>() {
		};
		outputParameters.add(typeReference);

		Function function = new Function(methodName, inputParameters, outputParameters);

		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddr, contractVoteAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).sendAsync().get();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			System.out.println("result:"+results.get(0).getValue().toString());
			//decimal = Integer.parseInt(results.get(0).getValue().toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return decimal;
	}
	

}
