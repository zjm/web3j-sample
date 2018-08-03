package com.ethjava.sol;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthHashrate;
import org.web3j.protocol.core.methods.response.EthMining;
import org.web3j.protocol.core.methods.response.EthProtocolVersion;
import org.web3j.protocol.core.methods.response.EthSyncing;
import org.web3j.protocol.core.methods.response.NetPeerCount;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import com.ethjava.utils.Environment;

import java.io.IOException;
import java.math.BigInteger;

public class ArtMain {
	private static Credentials credentials;
	private static Web3j web3j ;
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 credentials = Credentials.create("d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc");//可以根据私钥生成
		 String address = credentials.getAddress();
		 System.out.println(address);
		 web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		 ethInfo();
		 use();

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
		String contractAddress = "0x47bA663EC1B083D6316a9AE29C395180DC461bFA";	
		int max=10;
		int min=2;
		int randomNumber = (int) Math.round(Math.random()*(max-min)+min); 
		
		System.out.println("10~100之间随机数："+randomNumber);
		ArtToken contract = ArtToken.load(contractAddress, web3j, credentials,
				Convert.toWei(String.valueOf(randomNumber), Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		String myAddress = "0x579503Df81E09f304eB8a0ef843aebbE4b9ab72e";
		String toAddress = "0x02B7467c6Df772A7D3B8C346afD6DA4923e9B16A";
		BigInteger amount = new BigInteger("10000000000000000000");
		try {
			BigInteger balance = contract.balanceOf(myAddress).send();
			System.out.println("balance:"+balance);
			TransactionReceipt receipt = contract.transfer(toAddress, amount).send();
			System.out.println("gasUsed:"+receipt.getGasUsed());
			System.out.println("transHash:"+receipt.getTransactionHash());
			//etc..
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
