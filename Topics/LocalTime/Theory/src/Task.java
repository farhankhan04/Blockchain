// You can experiment here, it won’t be checked

import java.time.LocalTime;
import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String t1 = scanner.nextLine();
    String t2 = scanner.nextLine();
    LocalTime time1 = LocalTime.parse(t1);
    LocalTime time2 = LocalTime.parse(t2);
    System.out.println(Math.abs((time2.getHour()-time1.getHour())*3600+(time2.getMinute()-time1.getMinute())*60+(time2.getSecond()-time1.getSecond())));
  }
}
