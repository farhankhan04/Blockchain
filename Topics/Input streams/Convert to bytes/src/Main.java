import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String text = reader.readLine();

        char[] chars = text.toCharArray();
        byte[] bytes = new byte[chars.length];
        int i = 0;
        for(char c: chars){
            bytes[i++] = (byte) c;
        }
        for(byte b : bytes)
            System.out.print(String.valueOf(b));
    }
}