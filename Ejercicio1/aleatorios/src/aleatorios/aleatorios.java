package aleatorios;

import java.util.Random;

public class aleatorios {

  public static void main(String[] args) {
    if (args.length == 1 && args[0].matches("\\d\\d*")) {

      int iteractions = Integer.parseInt(args[0]);

      Random randomNum = new Random();// creamos un objeto de Random

      for (int i = 0; i < iteractions; i++) {
        System.out.println(randomNum.nextInt(101));
      }
    }
    else {
      System.out.println("Los parámetros son incorrectos");
    }
  }
}
