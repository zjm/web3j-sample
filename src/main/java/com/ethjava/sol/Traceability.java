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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class Traceability extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061149d806100606000396000f300608060405260043610610098576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680622607f91461009d57806310bd55ac146100ce57806368a51f1c1461025e5780638da5cb5b1461036b5780639a1ed32b146103c2578063cc8668de146104cf578063da38a63e1461055f578063dff3c78d146105c8578063f2fde38b1461063f575b600080fd5b3480156100a957600080fd5b506100cc6004803603810190808035600019169060200190929190505050610682565b005b3480156100da57600080fd5b506100fd600480360381019080803560001916906020019092919050505061085e565b60405180868152602001806020018060200180602001858152602001848103845288818151815260200191508051906020019080838360005b83811015610151578082015181840152602081019050610136565b50505050905090810190601f16801561017e5780820380516001836020036101000a031916815260200191505b50848103835287818151815260200191508051906020019080838360005b838110156101b757808201518184015260208101905061019c565b50505050905090810190601f1680156101e45780820380516001836020036101000a031916815260200191505b50848103825286818151815260200191508051906020019080838360005b8381101561021d578082015181840152602081019050610202565b50505050905090810190601f16801561024a5780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b34801561026a57600080fd5b50610369600480360381019080803560001916906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a5c565b005b34801561037757600080fd5b50610380610d7e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103ce57600080fd5b506104cd600480360381019080803560001916906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610da3565b005b3480156104db57600080fd5b506104e4611072565b6040518080602001828103825283818151815260200191508051906020019080838360005b83811015610524578082015181840152602081019050610509565b50505050905090810190601f1680156105515780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561056b57600080fd5b506105c6600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611110565b005b3480156105d457600080fd5b5061063d6004803603810190808035600019169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611185565b005b34801561064b57600080fd5b50610680600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061132e565b005b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106dd57600080fd5b60018060008360001916600019168152602001908152602001600020600001819055506040805190810160405280600381526020017f616263000000000000000000000000000000000000000000000000000000000081525060016000836000191660001916815260200190815260200160002060010190805190602001906107679291906113cc565b506040805190810160405280600c81526020017f646174653230313830383230000000000000000000000000000000000000000081525060016000836000191660001916815260200190815260200160002060020190805190602001906107cf9291906113cc565b506040805190810160405280601581526020017f6e6f746520e4bfa1e4bd9be4bfa1e681af78696e78000000000000000000000081525060016000836000191660001916815260200190815260200160002060030190805190602001906108379291906113cc565b50426001600083600019166000191681526020019081526020016000206004018190555050565b6001602052806000526040600020600091509050806000015490806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109105780601f106108e557610100808354040283529160200191610910565b820191906000526020600020905b8154815290600101906020018083116108f357829003601f168201915b505050505090806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109ae5780601f10610983576101008083540402835291602001916109ae565b820191906000526020600020905b81548152906001019060200180831161099157829003601f168201915b505050505090806003018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a4c5780601f10610a2157610100808354040283529160200191610a4c565b820191906000526020600020905b815481529060010190602001808311610a2f57829003601f168201915b5050505050908060040154905085565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610ab757600080fd5b6000600102856000191614151515610ace57600080fd5b600084111515610add57600080fd5b600060016000876000191660001916815260200190815260200160002060000154141515610b0a57600080fd5b8360016000876000191660001916815260200190815260200160002060000181905550826001600087600019166000191681526020019081526020016000206001019080519060200190610b5f9291906113cc565b50816001600087600019166000191681526020019081526020016000206002019080519060200190610b929291906113cc565b50806001600087600019166000191681526020019081526020016000206003019080519060200190610bc59291906113cc565b5042600160008760001916600019168152602001908152602001600020600401819055507f9955e2bbf615d94327e18ac58964ed8981532f6039af9dc0610e233e5b6997588585858585604051808660001916600019168152602001858152602001806020018060200180602001848103845287818151815260200191508051906020019080838360005b83811015610c6b578082015181840152602081019050610c50565b50505050905090810190601f168015610c985780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b83811015610cd1578082015181840152602081019050610cb6565b50505050905090810190601f168015610cfe5780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b83811015610d37578082015181840152602081019050610d1c565b50505050905090810190601f168015610d645780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390a15050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610dfe57600080fd5b8360016000876000191660001916815260200190815260200160002060000181905550826001600087600019166000191681526020019081526020016000206001019080519060200190610e539291906113cc565b50816001600087600019166000191681526020019081526020016000206002019080519060200190610e869291906113cc565b50806001600087600019166000191681526020019081526020016000206003019080519060200190610eb99291906113cc565b5042600160008760001916600019168152602001908152602001600020600401819055507f9955e2bbf615d94327e18ac58964ed8981532f6039af9dc0610e233e5b6997588585858585604051808660001916600019168152602001858152602001806020018060200180602001848103845287818151815260200191508051906020019080838360005b83811015610f5f578082015181840152602081019050610f44565b50505050905090810190601f168015610f8c5780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b83811015610fc5578082015181840152602081019050610faa565b50505050905090810190601f168015610ff25780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b8381101561102b578082015181840152602081019050611010565b50505050905090810190601f1680156110585780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390a15050505050565b60028054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111085780601f106110dd57610100808354040283529160200191611108565b820191906000526020600020905b8154815290600101906020018083116110eb57829003601f168201915b505050505081565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561116b57600080fd5b80600290805190602001906111819291906113cc565b5050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156111e057600080fd5b6002600160008460001916600019168152602001908152602001600020600001819055508060016000846000191660001916815260200190815260200160002060010190805190602001906112369291906113cc565b506040805190810160405280600c81526020017f6461746532303138303832300000000000000000000000000000000000000000815250600160008460001916600019168152602001908152602001600020600201908051906020019061129e9291906113cc565b506040805190810160405280601581526020017f6e6f746520e4bfa1e4bd9be4bfa1e681af78696e78000000000000000000000081525060016000846000191660001916815260200190815260200160002060030190805190602001906113069291906113cc565b5042600160008460001916600019168152602001908152602001600020600401819055505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561138957600080fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061140d57805160ff191683800117855561143b565b8280016001018555821561143b579182015b8281111561143a57825182559160200191906001019061141f565b5b509050611448919061144c565b5090565b61146e91905b8082111561146a576000816000905550600101611452565b5090565b905600a165627a7a7230582023642d810cc1375e03abd92750a20fca7ebd9a73301efb2248882db81d7669830029";

    public static final String FUNC_SETARTISTINFOR1 = "setArtistInfor1";

    public static final String FUNC_ARTISTINFO = "ArtistInfo";

    public static final String FUNC_SETARTISTINFOR = "setArtistInfor";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETARTISTINFOR0 = "setArtistInfor0";

    public static final String FUNC_TESTNAME = "testName";

    public static final String FUNC_SETARTISTINFOR3 = "setArtistInfor3";

    public static final String FUNC_SETARTISTINFOR2 = "setArtistInfor2";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event SETARTISTINFO_EVENT = new Event("SetArtistInfo", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected Traceability(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Traceability(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> setArtistInfor1(byte[] ipfsHash) {
        final Function function = new Function(
                FUNC_SETARTISTINFOR1, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(ipfsHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<BigInteger, String, String, String, BigInteger>> ArtistInfo(byte[] param0) {
        final Function function = new Function(FUNC_ARTISTINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<BigInteger, String, String, String, BigInteger>>(
                new Callable<Tuple5<BigInteger, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, String, String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, String, String, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setArtistInfor(byte[] ipfsHash, BigInteger artistId, String artistName, String date, String note) {
        final Function function = new Function(
                FUNC_SETARTISTINFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(ipfsHash), 
                new org.web3j.abi.datatypes.generated.Uint256(artistId), 
                new org.web3j.abi.datatypes.Utf8String(artistName), 
                new org.web3j.abi.datatypes.Utf8String(date), 
                new org.web3j.abi.datatypes.Utf8String(note)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setArtistInfor0(byte[] ipfsHash, BigInteger artistId, String artistName, String date, String note) {
        final Function function = new Function(
                FUNC_SETARTISTINFOR0, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(ipfsHash), 
                new org.web3j.abi.datatypes.generated.Uint256(artistId), 
                new org.web3j.abi.datatypes.Utf8String(artistName), 
                new org.web3j.abi.datatypes.Utf8String(date), 
                new org.web3j.abi.datatypes.Utf8String(note)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> testName() {
        final Function function = new Function(FUNC_TESTNAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setArtistInfor3(String artistName) {
        final Function function = new Function(
                FUNC_SETARTISTINFOR3, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(artistName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setArtistInfor2(byte[] ipfsHash, String artistName) {
        final Function function = new Function(
                FUNC_SETARTISTINFOR2, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(ipfsHash), 
                new org.web3j.abi.datatypes.Utf8String(artistName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Traceability> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Traceability.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Traceability> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Traceability.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<SetArtistInfoEventResponse> getSetArtistInfoEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SETARTISTINFO_EVENT, transactionReceipt);
        ArrayList<SetArtistInfoEventResponse> responses = new ArrayList<SetArtistInfoEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SetArtistInfoEventResponse typedResponse = new SetArtistInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.artistId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.artistName = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.date = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.note = (String) eventValues.getNonIndexedValues().get(4).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SetArtistInfoEventResponse> setArtistInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, SetArtistInfoEventResponse>() {
            @Override
            public SetArtistInfoEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SETARTISTINFO_EVENT, log);
                SetArtistInfoEventResponse typedResponse = new SetArtistInfoEventResponse();
                typedResponse.log = log;
                typedResponse.hash = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.artistId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.artistName = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.date = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.note = (String) eventValues.getNonIndexedValues().get(4).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<SetArtistInfoEventResponse> setArtistInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETARTISTINFO_EVENT));
        return setArtistInfoEventObservable(filter);
    }

    public static Traceability load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Traceability(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Traceability load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Traceability(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SetArtistInfoEventResponse {
        public Log log;

        public byte[] hash;

        public BigInteger artistId;

        public String artistName;

        public String date;

        public String note;
    }
}
