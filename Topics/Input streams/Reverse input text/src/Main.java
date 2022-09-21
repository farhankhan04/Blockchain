import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();

        reader.close();
        char[] chars = text.toCharArray();
        int n = chars.length;
        char[] charsReverse = new char[n];

        int i = n-1;
        for(char c: chars){
            charsReverse[i] = c;
            i--;
        }
        System.out.println(String.copyValueOf(charsReverse));
    }
}