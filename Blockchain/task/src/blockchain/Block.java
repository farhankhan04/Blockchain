package blockchain;

import java.util.List;

public class Block{
    private int id;
    private long timestamp;
    private String hashOfPrevious;
    private String hash;
    private String timeOfGeneration;
    private long magicNumber;
    private int taskNumber;
    private List<Message> messages;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTimeOfGeneration() {
        return timeOfGeneration;
    }

    public void setTimeOfGeneration(String timeOfGeneration) {
        this.timeOfGeneration = timeOfGeneration;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getHashOfPrevious() {
        return hashOfPrevious;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void setHashOfPrevious(String hashOfPrevious) {
        this.hashOfPrevious = hashOfPrevious;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}