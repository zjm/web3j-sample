package com.ethjava;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ethjava.sol.ArtistTraceabilityInfo;
import com.ethjava.sol.EquitySetpInfo;
import com.ethjava.sol.Traceability;
import com.ethjava.sol.TransferArtInfo;
import com.ethjava.utils.BusinessException;
import com.ethjava.utils.DesCrypto;
import com.ethjava.utils.FileUtils;
import com.ethjava.utils.WalletEnvironment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.util.test.FixedSecureRandom;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.*;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.tx.ChainId;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;


import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * 冷钱包 账号 交易相关
 */
public class EthWallet {

	private static Web3j web3j;

	private static String encryptKey = "p8k1d5y9";

	private static String contractVoteAddress = "0x4b0000b54761A5CdB3B905b35a7fE75F4a8a8f6C";
	private static String traceabilityContractAddress = "0xCd4aD516E826c63Af10dFA39Bf32CE1cF79Cf12A";
	private static String contractATIAddress = "0x856DD72DADeA09f7d484d1a57eD1f85631E8f57C";
	private static String contractAETAddress = "0xBEeE86896Ba858DE09248B9319bd76C31609feCe";

	private static BigInteger gasLimit = BigInteger.valueOf(553600);
	private static Float priGasPrice = 5.0f;//G wei
	private static String queryPrikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";

	public static void main(String[] args) {



//		web3j = Web3j.build(new HttpService(WalletEnvironment.RPC_URL));
//		String abc ="Qma38BbC6geXpWmvGJwytDNY45XD7JTND1NqLfn2FYeLtg".toLowerCase();
//        abc = new String(Hash.sha3(abc));
//		System.out.println("abc:"+abc);
		// 0x579503Df81E09f304eB8a0ef843aebbE4b9ab72e
		//String hash = "0xa74970a2120d060fe0f3e4ddb12437143367ccee8ee56708b758a27bfa26331f";
		//setArtistTraceabilityInfo(hash, new BigInteger("2"), "name_2", "20180820", "备注2");
		//getTransferArtInfo("0x091ae17f94aa3c5d9d10a9f5967ec1c99fc4ce1453a4d8bc73907ba0adfe97da");
		//getTraceabilityArtistInfo(hash);
		//String addr="0x579503Df81E09f304eB8a0ef843aebbE4b9ab72e";
		//getTokenBalance(addr,(byte)2);
		//getBalance(addr);


//
//		String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
//		String addr="0x02B7467c6Df772A7D3B8C346afD6DA4923e9B16A";
//       // getTokenBalance(0x37bdd9e4fdc2b8e42f8c6d613a18d0d89123d9a3,0);
//		String ret="";//
//		// tokenTransaction(prikey,contractATIAddress,addr,5000,18);
//		String hash ="0xe62069383f624b461b6dfc3bde0d2a4df00f364cde835a8c992bfebadb30b201";
//		byte retByte =  checkTransHashStatus(hash);
//		System.out.println("ret:"+retByte);
		//String token ="d57a11d9c6ccd5680eaa493dbd6a07123c22370143262ecac22147c99962cc92e80f2ca332b6e8aec3cd184a2cc076af1e3da6f334dce722cac73a8c62603bdb598cda5180b70ef29ff22222d8ce035ec521612e3d64d0a27c0477ff04c2205f0c144b3158c9e84919dcf529f82b285007abe81649a06af88b336ac689765549279b7792709bcafe2bb1a16f6b5c902f7e194ef269651c18398adf374bf3bb440386118160b871e381a828b6b1e45771c286b981c28e9719c00127a5448c4017921c90a9b0e1f07c1eab145665248764603f8f1678b1262f1477303b6abb98247087df8f09fd9642f763eb17fde221bc40973d04db74fa691e843dc2a0d23f19f85acf92d1890edecfe03f9bfbd011de64742d58028f010b69c97e21237ca651471270867637abfa2d054748272e30df1e2f49f33104d1f15526db8c991c3579cb9c336b1d162f385c10e2883efe50c6079a2e75f02cc74ef1a2286bc51b526f4719bb9342701a2e8d9763deb41e115cff755ead6c3976be63ce834053a6355845a71379882a7620d47a579bdeb398cc0b9c2534351fd172a3fbac9b736efed7dd90eb5bc8a5f6964f356fedbfd9267a920c6e825aa541ae121a624e80dcfc091c4ea0abeea5d4d259e0a833c2200549de2f682dd0b2399fec116161c0e31b8431f4fb5fc4d110ed8a9abe4a6041a952";
//		String toAddress = "0xcc4d0329757f43e65db48d9923b3422622de8f45";
		String token ="d57a11d9c6ccd5684925b5813e2aad27b96c35364d22153953da3dda0aca8d2b2ee33533baefe334e175da5889f7b97c967c0c5892fac6bccac73a8c62603bdb598cda5180b70ef29ff22222d8ce035ec521612e3d64d0a27c0477ff04c2205f0c144b3158c9e849866147087340971363003bf10e7f342f9b18aec70f782e577b5603a1afc4d33533c8dfc865e9d0187e194ef269651c18a391d17632e112b48ede00d9fc9f1ff1c721261e3b4a1d3f89417f2638a3f17f361097257421a374bae0b25a4a750beb2b0260de17ec8a439f73daacfc6feca99728793b18c0c6547087df8f09fd9642f763eb17fde221bc40973d04db74fa691e843dc2a0d23f19f85acf92d1890edecfe03f9bfbd011de64742d58028f010b69c97e21237ca651e220b911eeffb0a59248dbeb9d0f6c42c41858b3ff14416538fdb81e4d24d4b1aa6a4afb7fabcacf44a500c03873161a4d2353fb70d13933df88198ab4d13ef9ffb342c87e45817e8d9763deb41e115c0554f7cd92637a38e7de5531c48663b8718838b698ecc23d24dcacf71ddce560dad40ce065530b441a9659e0d3f8659fe456bac390d51cb63790dced2d8166c5920c6e825aa541ae780e98c20b2499a73f7fd59034dc3d368076a4b4d7577082e0cfa9e23f2fe202f8d7748a4b7bbe9d31f4fb5fc4d110ed8a9abe4a6041a952";
//		System.out.println("leng:"+token.length());
        String toAddress = "0x7d38db2304b9c3f67608348d4a0756ad523a452a";
//		boolean isa = isAddress(toAddress);
//		String ret = transfer(token,toAddress,2,(byte)1);
//		System.out.println("ret:"+ret);

		/////////test ipfs
		//String ipfskey="QmWvY1v2UfYUCQdHYPUEvWwngXkv7AFq3NQguqUngrNdx5";
		/*String ipfskey ="QmWvY1v2UfYUCQdHYPUEvWwngXkv7AFq3NQguqUngrNdx5".toLowerCase();

		ArtistTraceabilityInfo ati = new ArtistTraceabilityInfo();
		ati.setVersion("1");//传整形
		ati.setArtistId("5566898956656");
		ati.setArtistName("艺术家姓名");
		ati.setData("2018-8-23 15:32:00");
		ati.setNote("艺术家介绍");
		ati.setArtTitle("艺术标题");
		ati.setTecSkill("技法");
		ati.setArtSpecification("重5.03克");
		//set info
		setArtistTraceabilityInfo(ipfskey,queryPrikey,ati);
		//查询 0xc5cee2544383651a825c4336a4eb6f28b71ea7195936b027f256fbb299770aaf
		//String transHash = "0xc5cee2544383651a825c4336a4eb6f28b71ea7195936b027f256fbb299770aaf";
		ArtistTraceabilityInfo ati2 =getTraceabilityArtistInfo(ipfskey);
		System.out.println("get ati2:"+ati2.getTecSkill()+",time:"+ati2.getData());
		//-------------流转-----------
		System.out.println("key:"+ipfskey);
		TransferArtInfo tai = new TransferArtInfo();
		tai.setSellerMemberId("5689991");
		tai.setSeller("出售买者281");
		tai.setBuyerMemberId("66689898991");
		tai.setBuyer("购买者281");
		tai.setTransTime("2018-9-21 15:30:28");
		tai.setNote("备注信息");

		//
		setArtistTransfer(ipfskey,tai);
		//----查询--------0x87ee24a4568ec403b77ea28de12c52d06da7fd71470fa6d32e033d21afec990e
		List<TransferArtInfo> list =  getTransferArtInfo(ipfskey);
		for(TransferArtInfo info:list)
		{
		//	System.out.println("info:"+info.getBuyerMemberId()+","+info.getBuyer()+","+info.getSellerMemberId()+","+info.getSeller()+","+info.getTransTime()+","+info.getNote());

		}*/


		////////end test ipfs

		//System.out.println("ret2:"+ret);
//		String  toAddress ="0x02B7467c6Df772A7D3B8C346afD6DA4923e9B16A";
		// eTHTransaction(prikey,toAddress);
		 try {

		    // createWallet();
			// transferTest();
		// FileUtils.readKeyPairFromFile();
		// decryptWallet(keystore, "11111111");
//			testTransaction();
//			testTokenTransaction();

//			List<BigInteger> artistIds = new ArrayList<>();
//			artistIds.add(new BigInteger("10"));
//			artistIds.add(new BigInteger("11"));
//			artistIds.add(new BigInteger("12"));
//			artistIds.add(new BigInteger("13"));
//			setVotedArtist(new BigInteger("3"),artistIds);
//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证eth地址合法性
	 * @param address 待验证地址
	 * @return true eth地址，false非eth地址
	 */
	public static boolean  isAddress(String address)
	{
		boolean isAdress = false;
		if ( address.isEmpty() || !address.toLowerCase().startsWith("0x") || address.length()!=42 )
		{
			return  isAdress;
		}
		String addressCont = address.substring(2,address.length());
		for(int i=0;i<addressCont.length();i++){
			char cc = addressCont.charAt(i);
			if(cc=='0'||cc=='1'||cc=='2'||cc=='3'||cc=='4'||cc=='5'||cc=='6'||cc=='7'||cc=='8'||cc=='9'||cc=='A'||cc=='B'||cc=='C'||
					cc=='D'||cc=='E'||cc=='F'||cc=='a'||cc=='b'||cc=='c'||cc=='c'||cc=='d'||cc=='e'||cc=='f'){
				isAdress = true;
			}else
			{
				isAdress = false;
				break;
			}
		}
		return isAdress;

	}
	/**
	 *转账
	 * @param content 加密内容
	 * @param toAddress 接收币地址
	 * @param amount 币数量
	 * @param tokenType 0.代表ATI,1.代表AET
	 * @return  hash:成功,-1 目标地址错误
	 */
	public static String transfer(String content,String toAddress,double amount,byte tokenType)
	{
		if (!isAddress(toAddress))
		{
			throw  new BusinessException("地址不正确");
		}
		String contractAddr = contractATIAddress;
		if (tokenType==1)
		{
			contractAddr=contractAETAddress;
		}
		//keystore
		String priKey ="";
		try {
			DesCrypto desCrypto = new DesCrypto();
			priKey = desCrypto.decrypt(content);
			priKey = decryptWallet(priKey,encryptKey);
		}catch (Exception e)
		{
			e.printStackTrace();
		}

		String rethash = tokenTransaction(priKey,contractAddr,toAddress,amount,18);
		if (rethash.isEmpty())
		{
			throw  new BusinessException("请确认ETH手续费是否充足！");
		}
		//System.out.println("rethash:"+rethas.length());
		return  rethash;
	}

	public static String getUTCTimeStr() {
		StringBuffer UTCTimeBuffer = new StringBuffer();
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int millsecond = cal.get(Calendar.MILLISECOND);

		UTCTimeBuffer.append("UTC--").append(year).append("-").append(paresZero(month)).append("-")
				.append(paresZero(day));
		UTCTimeBuffer.append("T").append(paresZero(hour)).append("-").append(paresZero(minute)).append("-")
				.append(second).append(".").append(millsecond).append("Z").append("--");
		return UTCTimeBuffer.toString();
	}

	private static String paresZero(int num) {
		String ret = num < 10 ? String.format("0%d", num) : String.format("%d", num);
		return ret;
	}

	/**
	 * 根据msg计算hash值
	 *
	 * @param msg 需要计算的hash值
	 * @return 16进制hash值
	 */
	public static String getMsgHash(String msg) {
		return Hash.sha3(msg);
	}

	/**
	 * 查询ETH余额
	 * @param address 待查询钱包地址
	 * @return
	 */

	public static double getBalance(String address) {
		BigInteger balance = null;
		double ethValue=0.0f;
		createWeb3j();
		try {
			EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
			balance = ethGetBalance.getBalance();
			ethValue = Convert.fromWei(String.valueOf(balance), Convert.Unit.ETHER).doubleValue();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("address " + address + " balance " + ethValue + " eth");
		return ethValue;
	}


	/**
	 *查询token余额
	 * @param fromAddress 余额查询地址
	 * @param tokenType 0.代表ATI,1.代表AET
	 * @return
	 */
	public static double getTokenBalance(String fromAddress, byte tokenType) {
		double ethValue=0.0f;
        createWeb3j();
        String contractAddr = contractATIAddress;
        if (tokenType==1)
		{
			contractAddr=contractAETAddress;
		}
		String methodName = "balanceOf";
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();
		Address address = new Address(fromAddress);
		inputParameters.add(address);

		TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddr, data);

		EthCall ethCall;
		BigInteger balanceValue = BigInteger.ZERO;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			balanceValue = (BigInteger) results.get(0).getValue();
			ethValue = Convert.fromWei(String.valueOf(balanceValue), Convert.Unit.ETHER).doubleValue();
			//System.out.println("balanceValue:"+ethValue);
		} catch (IOException e) {
			ethValue=0;
			e.printStackTrace();
		}
		return ethValue;
	}


	/**
	 * ETH转账
	 *
	 * @param priKey    钱包私钥
	 * @param toAddress 接收钱包地址
	 * @param ethValue  EHT数量，如0.5 代表0.5个ETH
	 * @param _gasPrice 交易费用，1--100；按现在交易价格建议3--10之间
	 * @return 交易hash值
	 */
	private static String eTHTransaction(String priKey, String toAddress, double ethValue, float _gasPrice) {
		createWeb3j();
		Credentials credentials = Credentials.create(priKey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		BigInteger nonce = getNonce(address);
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(_gasPrice), Convert.Unit.GWEI).toBigInteger();
		BigInteger value = Convert.toWei(BigDecimal.valueOf(ethValue), Convert.Unit.ETHER).toBigInteger();
		String data = "";
		String retHash = "";
		byte chainId = ChainId.ROPSTEN;// 测试网络
		String signedData;
		try {
			signedData = signTransaction(nonce, gasPrice, gasLimit, toAddress.toLowerCase(), value, data, chainId,
					priKey);
			if (signedData != null) {
				EthSendTransaction ethTrans = web3j.ethSendRawTransaction(signedData).send();
				retHash = ethTrans.getTransactionHash();
				if (ethTrans.hasError()) {
					System.out.println("etherror:" + ethTrans.getError().getMessage());
				}
				// ethTrans.hashCode();
				System.out.println("retHash:" + retHash);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return retHash;
	}

	/**
	 * ERC20转账
	 *
	 * @param privateKey      钱包私钥
	 * @param contractAddress 合约地址
	 * @param toAddress       接收钱包地址
	 * @param amount          token数量
	 * @param decimals        单位精度
	 */
	private static String tokenTransaction(String privateKey, String contractAddress, String toAddress, double amount,
										 int decimals) {
		createWeb3j();
		Credentials credentials = Credentials.create(privateKey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		double balanValue = getTokenBalance(address, (byte) 1);
		if (balanValue < amount)
		{
			System.out.println("余额不足==");
			throw  new BusinessException("余额不足！");
		}
		//System.out.println("address:"+address);
		BigInteger nonce = getNonce(address);
		BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(3), Convert.Unit.GWEI).toBigInteger();
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
		String retHash ="";
		String signedData;
		try {
			signedData = EthWallet.signTransaction(nonce, gasPrice, gasLimit, contractAddress, value, data, chainId,
					privateKey);
			if (signedData != null) {
				EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(signedData).send();

				retHash = ethSendTransaction.getTransactionHash();
				if(ethSendTransaction.hasError())
				{
					System.out.println("error:"+ethSendTransaction.getError().getMessage());
				}


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retHash;
	}

	/**
	 * 创建钱包
	 *
	 */
	public static String[] createWallet() throws InvalidAlgorithmParameterException,
			NoSuchAlgorithmException, NoSuchProviderException, CipherException, JsonProcessingException {
		WalletFile walletFile;
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		walletFile = Wallet.createStandard(encryptKey, ecKeyPair);
		walletFile.getAddress();
	//	System.out.println("address " + walletFile.getAddress());
	//	System.out.println("keypair0:" + ecKeyPair.getPrivateKey());
	//	System.out.println("keypair1:" + ecKeyPair.getPrivateKey().toString(16));
//		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
//		String jsonStr = objectMapper.writeValueAsString(walletFile);
		String jsonStr = JSON.toJSONString(walletFile);
		// System.out.println(jsonStr);
		String[] atemArry = jsonStr.split("scrypt");
		if (atemArry.length > 2) {
			int i = jsonStr.lastIndexOf("kdf");
			String Json1 = jsonStr.substring(0, i - 1);
			String Json2 = jsonStr.substring(i + 14);
			jsonStr = Json1 + Json2;
		}
		String content="";
		DesCrypto desCrypto = null;
		try {
			desCrypto = new DesCrypto();
			content = desCrypto.encrypt(jsonStr);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// FileUtils.makeDir(dirPath);
		//String fileName = String.format("%s%s", getUTCTimeStr(), walletFile.getAddress()); // dirPath+File.separator+
		// getUTCTimeStr() +
		// walletFile.getAddress();
		// String testFile = dirPath + File.separator+"temp";
		// FileUtils.saveFileByStr(testFile, jsonStr);
		//String encryFile = fileName+"0";
		//fileName = FileUtils.encryptFile(fileName, jsonStr);
		//FileUtils.decrypt(fileName,encryFile);
		//String content= FileUtils.readKeyPairFromFile(fileName);
		//System.out.println("content:"+content);
		//content = decryptWallet(content,encryptKey);
		//System.out.println("decrycontent:"+content);
		// FileUtils.decrypt(fileName, fileName);
		// String dencryptStr = FileUtils.readKeyPairFromFile(fileName);
		//FileUtils.deleteFile();
		String[] retArry = new String[2];
		retArry[0] = content;

		retArry[1] = "0x"+walletFile.getAddress();
		return retArry;
		// decryptWallet(dencryptStr, "11111111");

//		 byte[] deMsgBytes1 = FileUtils.decrypt();
//	     System.out.println("文件解密后："+new String(deMsgBytes1));

	}

	/**
	 * 解密keystore 得到私钥
	 *
	 * @param keystore 钱包文件内容
	 * @param password 钱包密码
	 */
	public static String decryptWallet(String keystore, String password) {
		String privateKey = null;
		ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
		try {
			WalletFile walletFile = objectMapper.readValue(keystore, WalletFile.class);
			ECKeyPair ecKeyPair = null;
			ecKeyPair = Wallet.decrypt(password, walletFile);
			privateKey = ecKeyPair.getPrivateKey().toString(16);
			// System.out.println("privatekey:"+privateKey);
		} catch (CipherException e) {
			if ("Invalid password provided".equals(e.getMessage())) {
				System.out.println("密码错误");
			}
			throw  new BusinessException("密码错误！");
			//e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	/**
	 * 交易数据签名
	 *
	 * @param nonce      交易nonc值
	 * @param gasPrice   交易价格 Gwei
	 * @param to         接受地址
	 * @param value      交易值
	 * @param data       交易数据
	 * @param chainId    网络id
	 * @param privateKey 私钥
	 * @return 返回加密交易数据
	 * @throws IOException
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

	/**
	 * 创建web3j 调用web3j库库
	 */
	public static void createWeb3j() {
		if (web3j == null) {
			web3j = Web3j.build(new HttpService(WalletEnvironment.RPC_URL));
		}
	}

	/**
	 * 查询nonce值
	 *
	 * @param address 交易钱包地址
	 * @return nonce整数值
	 */
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

	/**
	 * 设置投票艺术家 id
	 *
	 * @param activityId 活动ID
	 * @param artistIds  艺术家ID列表
	 * @return 返回设置流水哈希值，否则异常
	 */
	public static String setVotedArtist(BigInteger activityId, List<BigInteger> artistIds) {
		createWeb3j();
		// String contractAddress = "0x170f5C9CAf03b9936a36bd2Fb1da653D64eB3E29";
		String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
		Credentials credentials = Credentials.create(prikey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		try {

			BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(10), Convert.Unit.GWEI).toBigInteger();
			BigInteger value = BigInteger.ZERO;
			String methodName = "setVoteArtis";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			inputParameters.add(new Uint256(activityId));
			inputParameters.add(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
					org.web3j.abi.Utils.typeMap(artistIds, org.web3j.abi.datatypes.generated.Uint256.class)));
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);
			String data = FunctionEncoder.encode(function);
			byte chainId = ChainId.ROPSTEN;
			String signedData;
			signedData = signTransaction(getNonce(address), gasPrice, gasLimit, contractVoteAddress, value, data,
					chainId, prikey);
			String hashret = "";
			if (signedData != null) {
				EthSendTransaction et = web3j.ethSendRawTransaction(signedData).send();
				hashret = et.getTransactionHash();
				System.out.println("ethSendTransaction:" + hashret);
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
	 * 链上投票
	 *
	 * @param activeId 活动ID
	 * @param artistId 艺术家id
	 * @param ticket   票数
	 * @return 返回投票流水哈希值，返回为空投票出现错误
	 */
	public static String vote(BigInteger activeId, BigInteger artistId, BigInteger ticket) {
		createWeb3j();
		String contractAddress = "0x170f5C9CAf03b9936a36bd2Fb1da653D64eB3E29";
		String prikey = "d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc";
		Credentials credentials = Credentials.create(prikey);// 可以根据私钥生成
		String address = credentials.getAddress().toLowerCase();
		try {

			BigInteger gasPrice = Convert.toWei(BigDecimal.valueOf(10), Convert.Unit.GWEI).toBigInteger();
			BigInteger value = BigInteger.ZERO;
			String methodName = "vote";
			List<Type> inputParameters = new ArrayList<>();
			List<TypeReference<?>> outputParameters = new ArrayList<>();
			inputParameters.add(new Uint256(activeId));
			inputParameters.add(new Uint256(artistId));
			inputParameters.add(new Uint256(ticket));
			TypeReference<Bool> typeReference = new TypeReference<Bool>() {
			};
			outputParameters.add(typeReference);
			Function function = new Function(methodName, inputParameters, outputParameters);
			String data = FunctionEncoder.encode(function);
			byte chainId = ChainId.ROPSTEN;
			String signedData;
			signedData = signTransaction(getNonce(address), gasPrice, gasLimit, contractAddress, value, data, chainId,
					prikey);
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
			System.out.println("result:" + results.get(0).getValue().toString());
			decimal = Integer.parseInt(results.get(0).getValue().toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return decimal;
	}

	/**
	 *
	 * @param activeId 活动ID
	 * @param _addr    投票地址
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
			System.out.println("result:" + results.get(0).getValue().toString());
			// decimal = Integer.parseInt(results.get(0).getValue().toString());
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
		return decimal;
	}

	/**
	 * 查询链上交易状态
	 *
	 * @param hashValue 交易hash值
	 * @return byte类型 0:交易失败;1.交易成功;2.交易等待;3.异常
	 */
	public static byte checkTransHashStatus(String hashValue) {
		byte ret = 0;
		try {
			String result = String.format(WalletEnvironment.TransactionReceiptURL, hashValue);
			result = FileUtils.sendHttpGet(result);
			JSONObject jsObj = JSONObject.parseObject(result);
			result = jsObj.getString("result");
			byte[] retStatus = null;
			if (result == null || result.isEmpty()) {
				ret = 3;
			} else {
				retStatus = Numeric.hexStringToByteArray(jsObj.getJSONObject("result").getString("status"));
				ret = retStatus[0];
			}

		}catch (Exception e)
		{
			e.printStackTrace();
			 ret = 3;
		}
		return ret;


	}
	//////////////////////////////////// 溯源//Start/////////////////////////////////////////

	/**
	 *
	 * @param ipfsHash ipfs hash(QmWvY1v2UfYUCQdHYPUEvWwngXkv7AFq3NQguqUngrNdx5)
	 * @param privateKey 溯源账号私钥
	 * @param ati 艺术品信息
	 * @return hash 交易hash值
	 */
	public static String setArtistTraceabilityInfo(String ipfsHash, String privateKey,ArtistTraceabilityInfo ati) {

		createWeb3j();
		String ipfsEthHash = Hash.sha3(ipfsHash);
		byte[] arryHash = Numeric.hexStringToByteArray(ipfsEthHash);
		Credentials credentials = Credentials.create(privateKey);// 可以根据私钥生成
		String address = credentials.getAddress();
		System.out.println("createWeb3jaddress--:" + address);
		Traceability contract = Traceability.load(traceabilityContractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(priGasPrice), Convert.Unit.GWEI).toBigInteger(), gasLimit);
		try {
			BigInteger bi = BigInteger.valueOf(Long.valueOf(ati.getArtistId()));
			TransactionReceipt receipt = contract.setArtistInfor(arryHash,bi,ati.getArtistName(), ati.getData(), ati.getNote(),
					ati.getArtTitle(),ati.getTecSkill(),ati.getArtSpecification()).sendAsync()
					.get();
			//System.out.println("transHash:" + receipt.getTransactionHash());
			return receipt.getTransactionHash();

		} catch (Exception e) {
			e.printStackTrace();
			throw  new BusinessException("请确认ETH手续费是否充足！");

		}

	}

	/**
	 *
	 * @param ipfsHash ipfshash
	 * @return 溯源艺术品信息
	 */
	public static ArtistTraceabilityInfo getTraceabilityArtistInfo(String ipfsHash) {
		createWeb3j();
		String vhash = Hash.sha3(ipfsHash);
		byte[] arryHash = Numeric.hexStringToByteArray(vhash);
		Credentials credentials = Credentials.create(queryPrikey);
		Traceability contract = Traceability.load(traceabilityContractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(priGasPrice), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		//BigInteger, BigInteger, String, String, String, BigInteger, String, String, String
		Tuple9 tuple = new Tuple9<BigInteger,BigInteger, String, String, String, BigInteger,String, String, String>(new BigInteger("0"),new BigInteger("0"), "", "", "",
				new BigInteger("0"),"","","");
		ArtistTraceabilityInfo retArtistInfo = new ArtistTraceabilityInfo();
		try {
			tuple = contract.artistInfo(arryHash).send();
			retArtistInfo.setVersion(tuple.getValue1().toString());
			retArtistInfo.setArtistId(tuple.getValue2().toString());
			retArtistInfo.setArtistName(tuple.getValue3().toString());
			retArtistInfo.setData(tuple.getValue4().toString());
			retArtistInfo.setNote(tuple.getValue5().toString());
			retArtistInfo.setCreateTime(tuple.getValue6().toString());
			retArtistInfo.setArtTitle(tuple.getValue7().toString());
			retArtistInfo.setTecSkill(tuple.getValue8().toString());
			retArtistInfo.setArtSpecification(tuple.getValue9().toString());
			System.out.println("result:"+retArtistInfo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retArtistInfo;
	}

	/**
	 * 设置艺术流转
	 * @param ipfs_Hash
	 * @param tai 艺术品信息
	 * @return
	 */

    public static  String setArtistTransfer(String ipfs_Hash,TransferArtInfo tai)
	{
		createWeb3j();
		//String ipfsEthHash =  Hash.sha3(ipfs_Hash).toString();
		byte[] arryHash = Numeric.hexStringToByteArray(Hash.sha3(ipfs_Hash));
		Credentials credentials = Credentials.create(queryPrikey);// 可以根据私钥生成
		String address = credentials.getAddress();
		System.out.println("createWeb3jaddress--:" + address);
		Traceability contract = Traceability.load(traceabilityContractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(priGasPrice), Convert.Unit.GWEI).toBigInteger(), gasLimit);
		String retStr ="";

		try {

			TransactionReceipt receipt = contract.setTransfer(arryHash,tai.getBuyerMemberId(),tai.getSeller(),tai.getBuyerMemberId(),
					tai.getBuyer(),tai.getTransTime(),tai.getNote()).sendAsync().get();

			retStr = receipt.getTransactionHash();
			System.out.println("hash:"+retStr);

		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return  retStr;

	}

	/**
	 *
	 * @param ipfshash ipfshash值
	 * @return 溯源艺术流转信息
	 */
	public static List<TransferArtInfo> getTransferArtInfo(String ipfshash) {
		createWeb3j();
		String vhash = Hash.sha3(ipfshash);
		//vhash="0x091ae17f94aa3c5d9d10a9f5967ec1c99fc4ce1453a4d8bc73907ba0adfe97da";
		byte[] arryHash = Numeric.hexStringToByteArray(vhash);
		Credentials credentials = Credentials.create(queryPrikey);
		Traceability contract = Traceability.load(traceabilityContractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(priGasPrice), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		String objsplit="&_&_&";
		String  filesplit="&-&-&";
		List<TransferArtInfo> listTransInfo = new ArrayList<TransferArtInfo>();
		try {
			String retInfo = contract.getTransInfo(arryHash).sendAsync().get();
			System.out.println("TransferretInfo:"+retInfo);
		    String[] InfoArry = retInfo.split(objsplit);
			String[] files = null;
			TransferArtInfo artInfo = null;
			int i=0;
			//System.out.println("result:" + retInfo);
             for(String strObj:InfoArry) {
				 files = strObj.split(filesplit);
				// System.out.println("resultstrObj:" + strObj);
				 i=0;
				 artInfo = new TransferArtInfo();
                 for(String strFile:files) {
                 	switch (i)
					{
						case 0:
							artInfo.setSellerMemberId(strFile);
							break;
						case 1:
							artInfo.setSeller(strFile);
							break;
						case 2:
							artInfo.setBuyerMemberId(strFile);
							break;
						case 3:
							artInfo.setBuyer(strFile);
							break;
						case 4:
							artInfo.setTransTime(strFile);
							break;
						case 5:
							artInfo.setNote(strFile);
							break;
							default:
								break;
					}
                 	i++;
				 }
				 listTransInfo.add(artInfo);
				 //System.out.println("result:" + artInfo.getNote());
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTransInfo;
	}



	/**
	 * 权益步骤
	 * @param privateKey
	 * @param esi
	 * @return
	 */
	public static String setExchangeEquitySetp(String privateKey,EquitySetpInfo esi) {

		createWeb3j();
//		String ipfsEthHash = Hash.sha3(ipfsHash);
//		byte[] arryHash = Numeric.hexStringToByteArray(ipfsEthHash);
		Credentials credentials = Credentials.create(privateKey);// 可以根据私钥生成
		String address = credentials.getAddress();
		System.out.println("createWeb3jaddress--:" + address);
		Traceability contract = Traceability.load(traceabilityContractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(priGasPrice), Convert.Unit.GWEI).toBigInteger(), gasLimit);
		try {
			TransactionReceipt receipt = null;
//			BigInteger bi = BigInteger.valueOf(Long.valueOf(ati.getArtistId()));
//			TransactionReceipt receipt = contract.setArtistInfor(arryHash,bi,ati.getArtistName(), ati.getData(), ati.getNote(),
//					ati.getArtTitle(),ati.getTecSkill(),ati.getArtSpecification()).sendAsync()
//					.get();
			receipt = contract.setExchangeEquitySetp(esi.getArtworkId(),esi.getArtistId(),esi.getArtistName(),
					esi.getEquityTitle(),esi.getStepName(),esi.getConstTime(),esi.getCreateTime(),esi.getNote()).sendAsync().get();
			//System.out.println("transHash:" + receipt.getTransactionHash());
			return receipt.getTransactionHash();

		} catch (Exception e) {
			e.printStackTrace();
			throw  new BusinessException("请确认ETH手续费是否充足！");

		}

	}

	/**
	 * 获取行权骤信息
	 * @param workId 行权id
	 * @return
	 */

	public static List<EquitySetpInfo> getExchangeEquitySetp(BigInteger workId)
	{
		createWeb3j();
		Credentials credentials = Credentials.create(queryPrikey);
		Traceability contract = Traceability.load(traceabilityContractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(priGasPrice), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		String objsplit="&_&_&";
		String  filesplit="&-&-&";
		List<EquitySetpInfo> listTransInfo = new ArrayList<EquitySetpInfo>();
		try {
			String retInfo = contract.getExchangeEquitySetp(workId).sendAsync().get();
			System.out.println("TransferretInfo:"+retInfo);
			String[] InfoArry = retInfo.split(objsplit);
			String[] files = null;
			EquitySetpInfo setpInfo = null;
			int i=0;
			//System.out.println("result:" + retInfo);
			for(String strObj:InfoArry) {
				files = strObj.split(filesplit);
				// System.out.println("resultstrObj:" + strObj);
				i=0;
				setpInfo = new EquitySetpInfo();
				for(String strFile:files) {
					switch (i)
					{
						case 0:
							setpInfo.setArtistId(strFile);
							break;
						case 1:
							setpInfo.setArtistName(strFile);
							break;
						case 2:
							setpInfo.setEquityTitle(strFile);
							break;
						case 3:
							setpInfo.setStepName(strFile);
							break;
						case 4:
							setpInfo.setConstTime(strFile);
							break;
						case 5:
							setpInfo.setCreateTime(strFile);
							break;
						case 6:
							setpInfo.setNote(strFile);
							break;
						default:
							break;
					}
					i++;
				}
				listTransInfo.add(setpInfo);
				//System.out.println("result:" + artInfo.getNote());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTransInfo;
	}


	//////////////////////////////////// 溯源//End/////////////////////////////////////////

}
