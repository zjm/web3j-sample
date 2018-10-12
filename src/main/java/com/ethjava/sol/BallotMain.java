package com.ethjava.sol;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthHashrate;
import org.web3j.protocol.core.methods.response.EthMining;
import org.web3j.protocol.core.methods.response.EthProtocolVersion;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple9;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import com.ethjava.utils.Environment;

public class BallotMain {
	
	private static Credentials credentials;
	private static Web3j web3j ;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 credentials = Credentials.create("d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc");//可以根据私钥生成
		 String address = credentials.getAddress();
		 System.out.println(address);
		 web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		 
		 //ethInfo();
		// use();
		 gtArtistInfo();

	}
	private static void ethInfo() {
		 Web3ClientVersion web3ClientVersion;
			try {
				web3ClientVersion = web3j.web3ClientVersion().send();
				String clientVersion = web3ClientVersion.getWeb3ClientVersion();
				System.out.println("clientVersion " + clientVersion);
				//区块数量
				EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
				BigInteger blockNumber = ethBlockNumber.getBlockNumber();
				System.out.println(blockNumber);
				//挖矿奖励账户
				//是否在同步区块
				EthSyncing ethSyncing = web3j.ethSyncing().send();
				boolean isSyncing = ethSyncing.isSyncing();
				System.out.println(isSyncing);
				//是否在挖矿
				EthMining ethMining = web3j.ethMining().send();
				boolean isMining = ethMining.isMining();
				System.out.println(isMining);
				//当前gas price
				EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
				BigInteger gasPrice = ethGasPrice.getGasPrice();
				System.out.println("gasPrice:"+gasPrice);
				//挖矿速度
				EthHashrate ethHashrate = web3j.ethHashrate().send();
				BigInteger hashRate = ethHashrate.getHashrate();
				System.out.println("hashRate:"+hashRate);
				//协议版本
				EthProtocolVersion ethProtocolVersion = web3j.ethProtocolVersion().send();
				String protocolVersion = ethProtocolVersion.getProtocolVersion();
				System.out.println("protocolVersion:"+protocolVersion);
				//连接的节点数
				NetPeerCount netPeerCount = web3j.netPeerCount().send();
				BigInteger peerCount = netPeerCount.getQuantity();
				System.out.println(peerCount);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	private static void use() {	
		String contractAddress = "0x71812301fBB2e081BF0a9D1e8955e4281319B97c";	
		int max=10;
		int min=2;
		int randomNumber = (int) Math.round(Math.random()*(max-min)+min); 
		
		System.out.println("10~100之间随机数："+randomNumber);
//		ArtToken contract = ArtToken.load(contractAddress, web3j, credentials,
//				Convert.toWei(String.valueOf(randomNumber), Convert.Unit.GWEI).toBigInteger(),
//				BigInteger.valueOf(100000));
		Ballot contract = Ballot.load(contractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(randomNumber), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		String myAddress = "0x579503Df81E09f304eB8a0ef843aebbE4b9ab72e";
		String toAddress = "0x02B7467c6Df772A7D3B8C346afD6DA4923e9B16A";
		BigInteger amount = new BigInteger("10000000000000000000");
		try {
			BigInteger perIdx = new BigInteger("0");
//			BigInteger retNum =  contract.getProposalNum().send();
//			System.out.println("retNum:"+retNum);
			BigInteger activeId = new BigInteger("0");
			BigInteger artistId = new BigInteger("1");
			BigInteger ticket = new BigInteger("12");
			TransactionReceipt receipt = contract.vote(activeId, artistId, ticket).send();
			System.out.println("transHash:"+receipt.getTransactionHash());
//			BigInteger balance = contract.balanceOf(myAddress).send();
//			System.out.println("balance:"+balance);
//			TransactionReceipt receipt = contract.transfer(toAddress, amount).send();
//			System.out.println("gasUsed:"+receipt.getGasUsed());
//			System.out.println("transHash:"+receipt.getTransactionHash());
			//etc..
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void gtArtistInfo() {

		
		String contractAddress = "0xCF6d39aD5CF5fb6701757AB6294996e7183E5a38";	
		int max=10;
		int min=2;
		int randomNumber = (int) Math.round(Math.random()*(max-min)+min); 
		
		System.out.println("10~100之间随机数："+randomNumber);
//		ArtToken contract = ArtToken.load(contractAddress, web3j, credentials,
//				Convert.toWei(String.valueOf(randomNumber), Convert.Unit.GWEI).toBigInteger(),
//				BigInteger.valueOf(100000));
		Traceability contract = Traceability.load(contractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(randomNumber), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		String hash = "0xa74970a2120d060fe8f3e4ddb12437143365ccee8ee56708b748a27bfa26311e";
		byte[] arryHash = Numeric.hexStringToByteArray(hash);
		//byte[] arryHash = hash.getBytes();
		System.out.println("length:"+arryHash.length);
		
		try {			
			
			Tuple9 tuple = new Tuple9<BigInteger,BigInteger, String, String, String, BigInteger,String, String, String>(new BigInteger("0"),new BigInteger("0"), "", "", "",
					new BigInteger("0"),"","","");
			tuple = contract.artistInfo(arryHash).send();
			System.out.println("tuple5:"+tuple.getValue1()+","+tuple.getValue2()+","+tuple.getValue3()+","+tuple.getValue4()+","+tuple.getValue5());
			
			//TransactionReceipt receipt = contract.vote(activeId, artistId, ticket).send();
			//System.out.println("transHash:"+receipt.getTransactionHash());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
