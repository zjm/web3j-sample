package com.ethjava.sol;

import com.ethjava.utils.Environment;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigInteger;

public class SolSample {
	public static void main(String[] args) {
		
		Credentials credentials = Credentials.create("d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc");//可以根据私钥生成
		//d2d3ac78638113e4c4cce38e6c77db66bea06a845986e760918995bf4fe427cc
		System.out.println(credentials.getAddress());
		
//		deploy();
//		use();
	}

	private static void deploy() {
		Web3j web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		Credentials credentials = null;//可以根据私钥生成
		RemoteCall<TokenERC20> deploy = TokenERC20.deploy(web3j, credentials,
				Convert.toWei("10", Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(3000000),
				BigInteger.valueOf(5201314),
				"my token", "mt");
		try {
			TokenERC20 tokenERC20 = deploy.send();
			tokenERC20.isValid();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void use() {
		Web3j web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		String contractAddress = null;
		Credentials credentials = null;//可以根据私钥生成
		TokenERC20 contract = TokenERC20.load(contractAddress, web3j, credentials,
				Convert.toWei("10", Convert.Unit.GWEI).toBigInteger(),
				BigInteger.valueOf(100000));
		String myAddress = null;
		String toAddress = null;
		BigInteger amount = BigInteger.ONE;
		try {
			BigInteger balance = contract.balanceOf(myAddress).send();
			TransactionReceipt receipt = contract.transfer(toAddress, amount).send();
			//etc..
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
