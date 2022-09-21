// You can experiment here, it won’t be checked

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

class MyClass<T> {

  private T t;

  public MyClass(T t) {
    this.t = t;
  }

  public T get() {
    return t;
  }
}
public class Task {
  public static void main(String[] args) {
    MyClass instance = new MyClass("Hello!");
    System.out.println(instance.get().getClass());
    Scanner scanner = new Scanner(System.in);
    String ldt = scanner.nextLine();
    LocalDateTime localDateTime = LocalDateTime.parse(ldt);
    //System.out.println(localDateTime);
    LocalDate localDate = localDateTime.toLocalDate();
    LocalTime localTime = localDateTime.toLocalTime();
    LocalTime localTime1 = localTime.plusHours(11);
   // System.out.println(localTime1);
    if(localTime1.compareTo(localTime)<0)
      System.out.println(localDate.plusDays(1));
    else
      System.out.println(localDate);
  }
}
