package blockchain;

import java.security.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BlockchainClient implements Runnable{

    private final Blockchain blockchain;
    private final String name;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    BlockchainClient(final Blockchain blockchain, final String name) throws NoSuchAlgorithmException{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);

        this.blockchain = blockchain;
        this.name = name;
        this.privateKey = keyPairGenerator.generateKeyPair().getPrivate();
        this.publicKey = keyPairGenerator.generateKeyPair().getPublic();
    }
    @Override
    public void run() {
        Random rd = new Random();
        while (blockchain.getChain().size() < BlockchainConstants.no_of_blocks){
            try {
                TimeUnit.MILLISECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer identifier = blockchain.getIdentifierForTransaction();
            if(identifier==null) {
                continue;
            }
            blockchain.setIdentifierGeneratedButMessageNotAdded(true);
            String transactionString = createTransaction(rd);
            Signature rsa;
            try {
                rsa = Signature.getInstance("SHA1withRSA");
                rsa.initSign(this.privateKey);
                rsa.update(transactionString.getBytes());
                rsa.update(identifier.byteValue());
                byte[] sign = rsa.sign();
                final Message msg = new Message(transactionString, identifier, sign, publicKey);
                blockchain.addMessage(msg);
                blockchain.setIdentifierGeneratedButMessageNotAdded(false);
            } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
                e.printStackTrace();
            }
        }
    }

    private String createTransaction(Random rd) {
        final List<String> receivers = BlockchainData.people.keySet().stream().filter(name->!name.equals(this.name)).collect(Collectors.toList());
        final String receiver = receivers.get(rd.nextInt(BlockchainData.people.size()-1));
        final int amount = rd.nextInt(50)+1;
        return name+" sent "+amount+" VC to "+receiver;
    }
}
