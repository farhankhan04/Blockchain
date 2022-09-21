package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Blockchain {

    private List<Block> chain;
    private int n = 0;
    private Instant lastBlockCreated;
    private List<Message> messages;
    private int min_identifier = 1;
    private int identifier;
    private List<Message> tempMessages;
    private boolean blockGenerationInProgress = false;

    private boolean identifierGeneratedButMessageNotAdded = false;

    public Blockchain(){
        setChain(new ArrayList<>());
        lastBlockCreated = Instant.now();
        setMessages(new ArrayList<>());
        setTempMessages(new ArrayList<>());
        setIdentifier(0);
    }

    public synchronized void createBlock(int taskNumber, final int id, final long time, final long magicNumber, final String hashOPrevious, final String hash, final List<Message> currentMessages) {
        blockGenerationInProgress = true;
        int size = getChain().size();
        if(getChain().isEmpty() || getChain().get(size-1).getHash().equals(hashOPrevious) && !identifierGeneratedButMessageNotAdded){ // to ensure that some other thread hasn't beaten this thread to create the block
            //System.out.println("block id: "+id);
            setTempMessages(getMessages());
            setMessages(new ArrayList<>());
            min_identifier = getTempMessages().stream().map(Message::getIdentifier).max(Comparator.naturalOrder()).orElse(min_identifier+1);
            //System.out.println("min_identifier: "+min_identifier);
            setIdentifier(min_identifier+1);
            //System.out.println("identifier: "+getIdentifier()+"\n");
            final Block block = new Block();
            getChain().add(block);
            block.setTaskNumber(taskNumber);
            block.setId(id);
            block.setTimestamp(time);
            block.setMagicNumber(magicNumber);
            block.setHash(hash);
            block.setHashOfPrevious(hashOPrevious);
            Instant instant = Instant.now();
            long timelapse = Duration.between(lastBlockCreated, instant).toSeconds();
            lastBlockCreated = instant;
            block.setTimeOfGeneration(String.valueOf(timelapse));
            block.setMessages(currentMessages);
            if(timelapse>60) {
                setN(getN()-1);
            }
            else if(timelapse<10) {
                setN(getN()+1);
            }
        }
        blockGenerationInProgress=false;
    }

    public synchronized void addMessage(final Message message){
        if(message.getIdentifier() <= min_identifier)
           return;
        if(transactionValid(message.getText()))
            getMessages().add(message);
    }

    private boolean transactionValid(String text) {
        //System.out.println(text);
        final List<String> strings = Arrays.asList(text.split(" "));
        final String sender = strings.get(0);
        final String receiver = strings.get(5);
        final int amountPresentSender = BlockchainData.people.get(sender);
        final int amountPresentReceiver = BlockchainData.people.get(sender);
        final int transactionAmount = Integer.parseInt(strings.get(2));
        if(amountPresentSender>=transactionAmount){
            BlockchainData.people.put(sender, amountPresentSender-transactionAmount);
            BlockchainData.people.put(receiver, transactionAmount+amountPresentReceiver);
            return true;
        }
        return false;
    }

    public synchronized Integer getIdentifierForTransaction() {
        if(!blockGenerationInProgress) {
            int temp = getIdentifier();
            setIdentifier(temp + 1);
           // System.out.println("No of blocks already created: "+chain.size()+" id returned for transaction: "+ (temp + 1));
            return temp+1;
        }
        else
            return null;
    }

    public synchronized void setIdentifierGeneratedButMessageNotAdded(boolean identifierGeneratedButMessageNotAdded) {
        this.identifierGeneratedButMessageNotAdded = identifierGeneratedButMessageNotAdded;
    }


    public boolean validate() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        int min_identifier = 0;
        for(int i = 1; i < chain.size(); i++){
            Block block = chain.get(i);
            if(!block.getHashOfPrevious().equals(chain.get(i - 1).getHash()))
                return false;
            min_identifier = chain.get(i-1).getMessages().stream().map(Message::getIdentifier).max(Comparator.naturalOrder()).orElse(min_identifier);
            for(Message message : chain.get(i).getMessages()){
                if(!validateMessage(message))
                    return false;
                if(message.getIdentifier() <= min_identifier)
                    return false;
            }
        }
        return true;
    }

    private boolean validateMessage(Message message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(message.getPublicKey());
        sig.update(message.getText().getBytes(StandardCharsets.UTF_8));
        sig.update(((Integer)message.getIdentifier()).byteValue());
        return sig.verify(message.getSign());
    }

    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem: hash) {
                String hex = Integer.toHexString(0xff & elem);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> getTempMessages() {
        return tempMessages;
    }

    public void setTempMessages(List<Message> tempMessages) {
        this.tempMessages = tempMessages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Block> getChain() {
        return chain;
    }

    public void setChain(List<Block> chain) {
        this.chain = chain;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }
}