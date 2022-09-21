package blockchain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    private static final int MAX_THREADS = 5;
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Blockchain blockchain = new Blockchain();
        BlockchainData.init();
        ExecutorService executors = Executors.newFixedThreadPool(MAX_THREADS);
        List<String> names = new ArrayList<>(BlockchainData.people.keySet());
        for(int i = 1; i <= MAX_THREADS; i++) {
            executors.submit(new BlockchainGenerator(blockchain, i));
            executors.submit(new BlockchainClient(blockchain, names.get(i%5)));
        }
        executors.shutdown();

        executors.awaitTermination((long) 1.5, TimeUnit.MINUTES);

        int n = 0;
        for(int i = 0; i < BlockchainConstants.no_of_blocks; i++) {
            System.out.println("Block:");
            System.out.println("Created by miner # " + blockchain.getChain().get(i).getTaskNumber());
            System.out.println(blockchain.getChain().get(i).getMessages().get(0).getText());
            System.out.println("Id: " + blockchain.getChain().get(i).getId());
            System.out.println("Timestamp: " + blockchain.getChain().get(i).getTimestamp());
            System.out.println("Nonce: " + blockchain.getChain().get(i).getMagicNumber());
            System.out.println("Hash of the previous block:\n" + blockchain.getChain().get(i).getHashOfPrevious());
            System.out.println("Hash of the block:\n" + blockchain.getChain().get(i).getHash());
            System.out.print("Block data:");
            if(blockchain.getChain().get(i).getMessages().size()==1){
                System.out.println("\nNo transactions");
            }
            else{
                System.out.println();
                for(Message message : blockchain.getChain().get(i).getMessages().stream().filter(message -> !message.getText().contains("miner")).collect(Collectors.toList())){
                    System.out.println(message.getText());
                }
            }
            int timelapse = Integer.parseInt(blockchain.getChain().get(i).getTimeOfGeneration());
            System.out.println("Block was generating for " + timelapse + " seconds");
            if(timelapse>60) {
                n--;
                System.out.println("N was decreased to "+n);
            }
            else if(timelapse<10) {
                n++;
                System.out.println("N was increased to "+n);
            }
            else
                System.out.println("N stays the same");
            System.out.println();
        }
        blockchain.validate();
    }
}
