package lenguaje;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/**
 *
 * @author ramonbl
 */
public class lenguaje {

  /**
   * Crea N conjuntos de palabras aleatorias y las guarda en un rafArchivo F
   *
   * @param args será el nº de palabras y la ruta del Archivo
   */
  public static void main(String[] args) {
    int iCont = 0;//contador para el bucle while, como maximo valdra iNumPalabras - 1

    //declaramos variable para el Acceso Aleatorio a Ficheos y para el bloqueo
    RandomAccessFile rafArchivo = null;
    FileLock flBloqueo = null;

    try {

      int iNumPalabras = Integer.valueOf(args[0]);//cogemos de los argumentos las veces que se repetira el proceso
      String stPath = args[1];//cogemos de los argumentos la ruta de guardado    

      //Construimos el RAF para el acceso al archivo
      rafArchivo = new RandomAccessFile(stPath, "rwd");
      //Entramos en la sección crítica
      flBloqueo = rafArchivo.getChannel().lock();

      try {

//    System.out.print("¿Número de cadenas a generar?: ");//**para pruebas**
//    int iNumPalabras = Integer.parseInt(br.readLine());//**para pruebas**
        while (iCont < iNumPalabras) { //iniciamos el bucle para realizar varias palabras
          String stAbecedario = "abcdefghijklmnñopqrstuvwxyz";
          String stCadena = "";
          //generamos la longitud de la stCadena (máximo de 20 letras para que no sean muy extensas)
          int longitudCadena = (int) Math.floor(Math.random() * 20 + 1);
          for (int x = 0; x < longitudCadena; x++) {
            //seleccionamos la posición de uno de los caracteres
            int iPosChar = (int) Math.floor(Math.random() * 27);
            //lo añadimos a la cadena
            stCadena = stCadena + stAbecedario.charAt(iPosChar);
          }

          //Mostramos en consola
          System.out.println(stCadena);

          //Vamos a la última posición del RAF
          rafArchivo.seek(rafArchivo.length());

          //Hacemos la escritura en el RAF
          if (rafArchivo.length() != 0) {
            rafArchivo.writeBytes(System.getProperty("line.separator") + stCadena);
          }
          else {
            rafArchivo.writeBytes(stCadena);
          }

          iCont++;
        }
        flBloqueo.release();
        flBloqueo = null;

      } catch (IOException e) {

        System.err.println("ERROR: Has introducido un caracter no permitido.");
        System.err.println(e);
      }

    } catch (FileNotFoundException ex) {
      System.out.println(ex.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
    finally {
      try {

//        flBloqueo.release(); //liberamos el flBloqueo del canal del rafArchivo
//        flBloqueo = null; // quitamos la referencia para que el recolector de basura pueda liberar memoria

        if (rafArchivo != null) {
          rafArchivo.close();
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

}
