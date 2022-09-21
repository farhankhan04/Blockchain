package blockchain;

import java.security.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class BlockchainGenerator implements Runnable{

    private final Blockchain blockchain;
    private final int taskNumber;
    private final String name;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    BlockchainGenerator(Blockchain blockchain, int taskNumber) throws NoSuchAlgorithmException {
        this.blockchain = blockchain;
        this.taskNumber = taskNumber;
        this.name = "miner" + taskNumber;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        this.privateKey = keyPairGenerator.generateKeyPair().getPrivate();
        this.publicKey = keyPairGenerator.generateKeyPair().getPublic();
    }

    @Override
    public void run() {
        Random rd = new Random();
        long magicNumber = 0;
        while (blockchain.getChain().size() < BlockchainConstants.no_of_blocks) {
            int n = blockchain.getN();
            char[] chars = new char[n];
            for (int i = 0; i < n; i++)
                chars[i] = '0';
            final String zeros = String.copyValueOf(chars);
            int totalMessages = blockchain.getChain().stream().map(block -> block.getMessages().size()).reduce(0, Integer::sum);

            final String generatedString = name+" gets 100 VC";
            final List<Message> messages = new ArrayList<>();
            Signature rsa;
            try {
                rsa = Signature.getInstance("SHA1withRSA");
                rsa.initSign(this.privateKey);
                rsa.update(generatedString.getBytes());
                int identifier = totalMessages+1;
                rsa.update(((Integer) identifier).byteValue());
                byte[] sign = rsa.sign();
                final Message msg = new Message(generatedString, identifier, sign, publicKey);
                messages.add(msg);
            } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
                e.printStackTrace();
            }
            messages.addAll(blockchain.getTempMessages());
            String concatenatedMessages = concatenate(messages);

            int id = blockchain.getChain().size() + 1;
            String hashOPrevious = "0";
            int size = blockchain.getChain().size();
            if (!blockchain.getChain().isEmpty())
                hashOPrevious = blockchain.getChain().get(size - 1).getHash();

            long time = new Date().getTime();
            String hash = blockchain.hash(String.valueOf(id) + time + magicNumber + hashOPrevious + concatenatedMessages);

            while(!(hash.length()>=n && hash.substring(0,n).equals(zeros))) {
                time = new Date().getTime();
                magicNumber = rd.nextLong();
                hash = blockchain.hash(String.valueOf(id) + time + magicNumber + hashOPrevious + concatenatedMessages);
            }
            blockchain.createBlock(taskNumber, id, time, magicNumber, hashOPrevious, hash, messages);
        }
    }

    private String concatenate(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for(Message message: messages){
            sb.append(message.getText());
        }
        return sb.toString();
    }
}
