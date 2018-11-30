package ordenarnumeros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ordenarNumeros {

  public static void main(String[] args) {
    boolean b_correcto = true;

    List<Integer> lEnteros; //Crear una lista de enteros
    lEnteros = new ArrayList<>(); //Inicializamos la lista.

    try {
      BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
      String stLinea;

      if (!brInput.ready()) {
        lEnteros = pedirNumeros();
        if (lEnteros == null) {
          System.out.println("La cadena introducida no es correcta");
          System.exit(0);
        }
      }
      else {
        stLinea = brInput.readLine();
        System.out.println("Los números desordenados son: ");
        while (stLinea != null) {

          if (!stLinea.matches("[0-9]*")) {
            System.out.println("no encontrados numeros");
            System.exit(0);
          }
          System.out.println(stLinea);
          lEnteros.add(Integer.parseInt(stLinea));
          stLinea = brInput.readLine();
        }
        brInput.close();
      }
    } catch (Exception e) {
      System.out.println("Hubo una excepción");
    }

    Collections.sort(lEnteros);

    System.out.println(
        "\nLos números ordenados son:\n");
    lEnteros.forEach(
        (i_num) -> {
          System.out.println(i_num);
        }
    );
  }

  private static List<Integer> pedirNumeros() {
    Scanner sc_lector = new Scanner(System.in);

    List<Integer> lNumeros; //Crear una lista de enteros
    lNumeros = new ArrayList<>(); //Inicializamos la lista.

    String scCadena = "";
    System.out.println(""
        + "Introduce números separados por espacios.\n"
        + "Cualquier otro caracter no es válido"
        + "cuando acabes. Introduce ENTER"
    );

    scCadena = sc_lector.nextLine(); //devuelve línea entera (elimina el \n final del buffer)
    scCadena = scCadena.replaceAll("\\s+", " "); //reemplaza varios espacios seguidos por uno
    //scCadena=sc_lector.nextLine().replaceAll("\\s+", " ");

    String[] tokens = scCadena.split(" ");
    //String[] tokens = sc_lector.nextLine().replaceAll("\\s+", " ").split(" ");

    for (String token : tokens) {
      if (!token.matches("[0-9]*")) {
        return null;
      }
      //System.out.println(token);
      lNumeros.add(Integer.parseInt(token));
    }
    return lNumeros;
  }

  private static List<Integer> RecibirNumeros(String[] args) {
    try {
      BufferedReader brInput = new BufferedReader(new InputStreamReader(System.in));
      String stLinea = "";

      List<Integer> lEnteros = new ArrayList<>();

      stLinea = brInput.readLine();
      while (stLinea != null) {

        if (!stLinea.matches("[0-9]*")) {
          return null;
        }
        lEnteros.add(Integer.parseInt(stLinea));
        stLinea = brInput.readLine();
      }
      brInput.close();

      return lEnteros;
    } catch (IOException | NumberFormatException e) {
      System.out.println("Hubo una excepción");
      return null;
    }

  }
}
