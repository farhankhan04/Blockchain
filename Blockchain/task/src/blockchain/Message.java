package blockchain;

import java.security.PublicKey;

public class Message {

    private String text;

    private int identifier;

    private byte[] sign;

    private PublicKey publicKey;

    public Message(final String text, final int identifier, final byte[] sign, final PublicKey publicKey) {
        this.text = text;
        this.identifier = identifier;
        this.sign = sign;
        this.publicKey = publicKey;
    }

    public String getText() {
        return text;
    }

    public int getIdentifier() {
        return identifier;
    }

    public byte[] getSign() {
        return sign;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}
