package blockchain;

import java.util.HashMap;
import java.util.Map;

public class BlockchainData {
    public static Map<String, Integer> people = new HashMap<>();

    public static void init(){
        people.put("Donald", 100);
        people.put("Tom", 100);
        people.put("Sarah", 100);
        people.put("Nick", 100);
        people.put("Alice", 100);
    }
}
