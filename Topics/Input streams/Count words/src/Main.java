import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        reader.close();

        text = text.trim();
        if(text.isBlank())
            System.out.println(0);
        else{
            String[] strings = text.split("\\s+");
            System.out.println(strings.length);
        }
    }
}