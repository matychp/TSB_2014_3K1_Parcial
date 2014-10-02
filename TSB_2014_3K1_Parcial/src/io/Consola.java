package io;

/**
 * Clase para facilitar operaciones de carga por teclado en consola estandar
 * @author Instructores de CISCO System para su curso de Fundamentos de Java 1.1 - Modificado por Valerio Frittelli.
 * @version Mayo de 2004.
*/
 
public class Consola
{
    /**
     * Lee desde el teclado de consola un caracter simple y lo retorna. Si ocurre algun problema mientras se efect�a 
     * la lectura, el metodo retornara un caracter de espacio en blanco.
     * @return un valor char con el caracter leido desde el teclado de consola.
     */
    public static char readChar()
    {
        char ch=' ';
        try
        {
            ch = (char)System.in.read();
            System.in.read();
        }
        catch(java.io.IOException e)
        {
        }
        return ch;
    }


    /**
     * Lee y retorna un String desde teclado. La lectura se da por concluida al introducir un salto de linea (es decir, 
     * al presionar <Enter>).
     * @return el String leido (sin el salto de linea).
    */
    public static String readLine()
    { 
       int ch;
       String r = "";
       boolean done = false;
       while (!done)
       {
        try
        {
            ch = System.in.read();
            if (ch < 0 || (char)ch == '\n') { done = true; }
            else 
            {
                 if ((char)ch != '\r') { r = r + (char) ch; }
            }
        }
        catch(java.io.IOException e)
        {
            done = true;
        }
       }
       return r;
    }
  
    /**
     * Lee un integer desde teclado. La lectura se da por concluida al introducir un salto de linea (es decir, 
     * al presionar <Enter>). Si la secuencia inctroducida por teclado NO ES un numero entero, el metodo ignorara 
     * la carga y emitira un mensaje solicitando una nueva carga.
     * @return el valor cargado, como un valor int.
     */
    public static int readInt()    
    {
       while(true)
       { 
          try
          {
              return Integer.parseInt(readLine().trim());
          }
          catch(NumberFormatException e)
          {
              System.out.println("No es un integer. Por favor, pruebe otra vez!");
          }
       }
    }
  
    /**
     * Lee un double desde teclado. La lectura se da por concluida al introducir un salto de linea (es decir, 
     * al presionar <Enter>). Si la secuencia inctroducida por teclado NO ES un numero double valido, el metodo ignorara 
     * la carga y emitira un mensaje solicitando una nueva carga.
     * @return el valor cargado, como un valor double.
     */
    public static double readDouble()
    {
       while(true)
       { 
           try
           {
              return Double.parseDouble(readLine().trim());
           }
           catch(NumberFormatException e)
           {
              System.out.println("No es un flotante. Por favor, pruebe otra vez!");
           }
       }
    }
}
