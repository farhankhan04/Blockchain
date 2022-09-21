import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.stream.Stream;

// Curry it: f(x, y, z) = x + y^2 + z^3
public class Task {

  public static int calc(int x, int y, int z) {

    IntFunction<IntFunction<IntFunction<Integer>>> f = value -> value1 -> value2 -> value + value1 * value1 + value2 * value2 * value2;

    return f.apply(x).apply(y).apply(z);
  }

  // Don't change the code below
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

   /* String[] values = scanner.nextLine().split(" ");

    int x = Integer.parseInt(values[0]);
    int y = Integer.parseInt(values[1]);
    int z = Integer.parseInt(values[2]);*/

    //System.out.println(calc(x, y, z));
    System.out.println(Stream.of(1, 2, 4, 5).parallel().reduce(1, Integer::sum));
    System.out.println(Stream.of(1, 2, 3, 4, 5).parallel().reduce(1, Integer::sum));
    System.out.println(Stream.of(1, 2, 3, 4, 5).parallel().reduce(0, Integer::sum) + 1);
    System.out.println(Stream.of(1, 2, 3, 4, 5).reduce(1, Integer::sum));
  }
}