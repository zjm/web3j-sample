package com.ethjava.sol;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Ballot extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555033600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610a2d806100a16000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806302f43895146100b4578063108b2fb7146101155780632e4176cf1461015a578063486add03146101b15780635a571867146101fc5780637872159a1461027e57806385858c29146102bf5780638a6655d61461036c5780638da5cb5b146103ad578063f2fde38b14610404578063fd7ae4bc14610447575b600080fd5b3480156100c057600080fd5b506100ff60048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610499565b6040518082815260200191505060405180910390f35b34801561012157600080fd5b50610140600480360381019080803590602001909291905050506104f7565b604051808215151515815260200191505060405180910390f35b34801561016657600080fd5b5061016f610517565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101bd57600080fd5b506101e6600480360381019080803590602001909291908035906020019092919050505061053d565b6040518082815260200191505060405180910390f35b34801561020857600080fd5b5061027c600480360381019080803590602001909291908035906020019082018035906020019080806020026020016040519081016040528093929190818152602001838360200280828437600081840152601f19601f82011690508083019250505050505050919291929050505061056f565b005b34801561028a57600080fd5b506102a9600480360381019080803590602001909291905050506106ce565b6040518082815260200191505060405180910390f35b3480156102cb57600080fd5b5061030a60048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506106e6565b60405180868152602001851515151581526020018481526020018381526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019550505050505060405180910390f35b34801561037857600080fd5b506103ab600480360381019080803590602001909291908035906020019092919080359060200190929190505050610756565b005b3480156103b957600080fd5b506103c261090d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561041057600080fd5b50610445600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610932565b005b34801561045357600080fd5b5061047c60048036038101908080359060200190929190803590602001909291905050506109d0565b604051808381526020018281526020019250505060405180910390f35b60006002600084815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000154905092915050565b60056020528060005260406000206000915054906101000a900460ff1681565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600060036000848152602001908152602001600020600083815260200190815260200160002060010154905092915050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156105cc57600080fd5b600083101515156105dc57600080fd5b600015156005600085815260200190815260200160002060009054906101000a900460ff16151514151561060f57600080fd5b81516004600085815260200190815260200160002081905550600090505b815181101561069d57818181518110151561064457fe5b90602001906020020151600360008581526020019081526020016000206000848481518110151561067157fe5b90602001906020020151815260200190815260200160002060000181905550808060010191505061062d565b60016005600085815260200190815260200160002060006101000a81548160ff021916908315150217905550505050565b60046020528060005260406000206000915090505481565b6002602052816000526040600020602052806000526040600020600091509150508060000154908060010160009054906101000a900460ff16908060020154908060030154908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905085565b600080841015151561076757600080fd5b6000600360008681526020019081526020016000206000858152602001908152602001600020600001541415151561079e57600080fd5b6002600085815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020905060018160010160006101000a81548160ff021916908315150217905550828160020181905550338160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555083816003018190555081816000016000828254019250508190555081600360008681526020019081526020016000206000858152602001908152602001600020600101600082825401925050819055503373ffffffffffffffffffffffffffffffffffffffff167ff6012fcc2096f6bb1a83c17545daaad3e511ac6a9ba85c84c81f433eedf34e7c85858560405180848152602001838152602001828152602001935050505060405180910390a250505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561098d57600080fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60036020528160005260406000206020528060005260406000206000915091505080600001549080600101549050825600a165627a7a72305820cac4af565a18f586db61193d124ddc14cdfff14aadcb240e8b7a7c0a10960ea50029";

    public static final String FUNC_GETVOTERVOTECOUNT = "getVoterVoteCount";

    public static final String FUNC_ACTIVITYEXIST = "ActivityExist";

    public static final String FUNC_CHAIRPERSON = "chairperson";

    public static final String FUNC_GETARTISVOTECOUNT = "getArtisVoteCount";

    public static final String FUNC_SETVOTEARTIS = "setVoteArtis";

    public static final String FUNC_ATVIENUM = "AtvieNum";

    public static final String FUNC_VOTERS = "Voters";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_ARTISTS = "Artists";

    public static final Event VOTER_EVENT = new Event("voter", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    protected Ballot(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Ballot(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> getVoterVoteCount(BigInteger activId, String _addr) {
        final Function function = new Function(FUNC_GETVOTERVOTECOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(activId), 
                new org.web3j.abi.datatypes.Address(_addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> ActivityExist(BigInteger param0) {
        final Function function = new Function(FUNC_ACTIVITYEXIST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> chairperson() {
        final Function function = new Function(FUNC_CHAIRPERSON, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getArtisVoteCount(BigInteger activId, BigInteger artisId) {
        final Function function = new Function(FUNC_GETARTISVOTECOUNT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(activId), 
                new org.web3j.abi.datatypes.generated.Uint256(artisId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setVoteArtis(BigInteger activId, List<BigInteger> artistIds) {
        final Function function = new Function(
                FUNC_SETVOTEARTIS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(activId), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(artistIds, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> AtvieNum(BigInteger param0) {
        final Function function = new Function(FUNC_ATVIENUM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple5<BigInteger, Boolean, BigInteger, BigInteger, String>> Voters(BigInteger param0, String param1) {
        final Function function = new Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.Address(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple5<BigInteger, Boolean, BigInteger, BigInteger, String>>(
                new Callable<Tuple5<BigInteger, Boolean, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple5<BigInteger, Boolean, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, Boolean, BigInteger, BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (Boolean) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> vote(BigInteger activId, BigInteger artisId, BigInteger ticket) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(activId), 
                new org.web3j.abi.datatypes.generated.Uint256(artisId), 
                new org.web3j.abi.datatypes.generated.Uint256(ticket)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> Artists(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_ARTISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ballot.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Ballot> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ballot.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<VoterEventResponse> getVoterEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTER_EVENT, transactionReceipt);
        ArrayList<VoterEventResponse> responses = new ArrayList<VoterEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VoterEventResponse typedResponse = new VoterEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.activId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.artistId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<VoterEventResponse> voterEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, VoterEventResponse>() {
            @Override
            public VoterEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTER_EVENT, log);
                VoterEventResponse typedResponse = new VoterEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.activId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.artistId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<VoterEventResponse> voterEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTER_EVENT));
        return voterEventObservable(filter);
    }

    public static Ballot load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Ballot load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ballot(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class VoterEventResponse {
        public Log log;

        public String from;

        public BigInteger activId;

        public BigInteger artistId;

        public BigInteger value;
    }
}
