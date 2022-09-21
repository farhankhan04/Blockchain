import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;


class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Set<String> set= new HashSet<>();
        set.add("2M");
        set.add("6M");
        set.add("12M");
        set.add("24M");
        set.add("9M");
        set.add("18M");
        System.out.println(set);
        Set<String> set1 = set.stream().sorted((o1, o2) -> {
            Integer tenure1 = Integer.parseInt(o1.split("M")[0]);
            Integer tenure2 =  Integer.parseInt(o2.split("M")[0]);
            return tenure1.compareTo(tenure2);
        }).
                collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println(set1);
        System.out.println(set1.toArray()[set1.size()-1]);
        for(String s : set1){
            System.out.println(s);
        }

        System.out.println(Math.round(3243.45434));

        List<Integer> l = new ArrayList<>();
        l.add(33);
        int m = l.stream().max(Comparator.naturalOrder()).orElse(4234234);
        System.out.println(m);

        System.out.println(Math.round(24323200.00/24));
        System.out.println((double) Math.round(24323200.00/24)/100);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l2.add(4);
        System.out.println(l1.containsAll(l2));

        BigDecimal bd = BigDecimal.TEN;
        System.out.println(String.valueOf(bd.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_EVEN)));

        File f  = new File("/Users/i341850/Downloads/Blockchain/Topics/dataset_91065.txt");
        Scanner scanner = new Scanner(f);
        int n = 0;
        while (scanner.hasNext()){
            int no = Integer.parseInt(scanner.nextLine());
            if(no==0)
                break;;
            if(no%2==0)
                n++;
        }
        System.out.println(n);

        long l3 = (long) 5635.55;
        System.out.println((int) l3%3600);

        byte[] bytes = new byte[10];
        InputStream inputStream = new FileInputStream("file.txt");
        int numberOfBytes = inputStream.read(bytes);
        System.out.println(numberOfBytes);

        byte[] content = new byte[] {'J', 'a', 'v', 'a'};
        OutputStream outputStream = new FileOutputStream("file.txt", true);
        outputStream.write(content);
        outputStream.close();

        Writer writer = new FileWriter("file.txt", false);
        writer.write("streams");
        writer.close();

        CharArrayWriter charArrayWriter = new CharArrayWriter();
        String[] words = new String[]{"rewerw", "fsdfg"};
        for(String word: words){
            charArrayWriter.write(word);
        }

        Thread t1 = new Thread(() -> System.out.println("1"));
        //Thread t2 = new Thread(() -> System.out.println("2"));
        //Thread t3 = new Thread(() -> System.out.println("3"));
        t1.start();
        //System.out.println(1/0);
        t1.join();
        System.out.println(charArrayWriter.toCharArray());
        //invokeMethods(t1, t2, t3);

        System.out.println(Thread.currentThread().isDaemon()?"daemon":"not daemon");
        System.out.println(Double.parseDouble("0.0"));

        List<String> messages = new ArrayList<>(Arrays.asList("adf", "reefd"));
        System.out.println("messages"+messages);
    }

    public static void invokeMethods(Thread t1, Thread t2, Thread t3) throws InterruptedException {
        t3.start();
        t3.join();
        t2.start();
        t2.join();
        t1.start();
        t1.join();

    }
}

abstract class B{
    static void m1(){

    }
    abstract void m2();

    void m3() {
        
    }
}