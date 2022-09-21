import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        List<Integer> l = new ArrayList<>();
        String list = scanner.nextLine();
        n = Integer.parseInt(scanner.nextLine());
        String[] strings = list.split(" ");
        for(String s: strings){
            l.add(Integer.parseInt(s));
        }
        int diff = Integer.MAX_VALUE;
        for(int no: l) {
            if(Math.abs(n-no) < diff)
                diff = Math.abs(n-no);
        }
        List<Integer> result = new ArrayList<>();
        for(int no: l){
            if(Math.abs(n-no)==diff)
                result.add(no);
        }
        result.sort(Comparator.naturalOrder());
        for(int r: result){
            System.out.print(r+" ");
        }

    }
}