package interfaz;

import estructuras.Heap;
import estructuras.SimpleList;
import io.Consola;

/**
 *
 * @author matychp
 */
public class Main {

    private static Heap<Integer> h;
    private static int cantidad = 0;

    /**
     * Implementacion de un menú de opciones.
     * @param args 
     */
    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\tMenú de Opciones");
            System.out.println();
            System.out.println("1.  Test propuesto por el programador automatico (El programa carga todos los datos).");
            System.out.println("2.  Test propuesto por el programador manual (El usuario carga todos los datos).");
            System.out.println();
            System.out.println("3.  Crear un Heap de tamaño n.");
            System.out.println("4.  Cargar el Heap manualmente.");
            System.out.println("5.  Cargar el Heap automaticamente.");
            System.out.println("6.  Probar si tiene suerte agregando un número.");
            System.out.println("7.  Mostrar los dos optimos del heap.");
            System.out.println("8.  Remover los dos optimos del heap.");
            System.out.println("9.  toString() por niveles.");
            System.out.println("10.  toString() sin niveles.");
            System.out.println("11.  Extraer los elementos del Heap mostrandolos.");
            System.out.println();
            System.out.println("0.  Salir del programa.");

            System.out.print("Ingrese una opcion: ");
            opcion = Consola.readInt();
            
            if(!heapCreado()) { opcion = -1; }
            
            switch (opcion) {
                case 1:
                    testPropuesto();
                    break;
                case 2:
                    testPropuestoManual();
                case 3:
                    crear();
                    break;
                case 4:
                    cargaManual();
                    break;
                case 5:
                    cargaAutomatica();
                    break;
                case 6:
                    agregarConSuerte();
                    break;
                case 7:
                    mostrarOptimos();
                    break;
                case 8:
                    removerOptimos();
                    break;
                case 9:
                    toStringPorNiveles();
                    break;
                case 10:
                    toStringSinNiveles();
                    break;
                case 11:
                    extraerElementos();
                    break;
                case 0:
                    System.out.println("Saliendo del programa... P:");
                    break;
                case -1:
                    System.out.println("Todavia no creó el Heap, por favor utilice la opcion 1..Gracias...");
                    break;
                default:
                    System.out.println("Opcion incorrecta -_-\nIngrese de nuevo...");
            }
            System.out.println("Presione Enter para volver al menú...");
            Consola.readLine();
        } while (opcion != 0);
    }
    
    /**
     * Metodo que se encarga de llamar a los métodos de carga manual.
     * Y ademas se agregan metodos que realizan ciertas operaciones con el Heap.
     */
    private static void testPropuestoManual(){
        crear();
        cargaManual();
        agregarConSuerte();
        mostrarOptimos();
        removerOptimos();
        extraerElementos();
        toStringPorNiveles();
    }
    
    //A partir de acá los métodos manuales y automáticos.

    /**
     * Primero chequea que no exista un Heap ya creado. De ser cierto informa la situacion y pide confirmacion para sobreescribir el Heap.
     * Luego carga por teclado el tamaño inicial del Heap y el sentido del Heap (ascendente o descendente). Ambos con control de entrada.
     * Por ultimo crea un Heap con los datos pedidos anteriormente.
     */
    private static void crear() {
        if (heapCreado()) {
            System.out.println("Se detectó un Heap existente...");
            System.out.println("Está seguro que desea continuar?, se perderá su Heap anterior.");
            int check;
            System.out.print("Presione 's' y luego 'Enter' para confirmar, cualquier tecla para cancelar: ");
            check = Consola.readChar();
            if (check != 's') {
                return;
            }
        }
        do {
            System.out.print("Ingrese el tamaño del arreglo (la cantidad de elementos que tendrá): ");
            cantidad = Consola.readInt();
        } while (cantidad <= 0);

        char ascendencia;
        do {
            System.out.print("Ingrese 't' si quiere un heap ascendente ó 'f' si quiere uno descentende.");
            ascendencia = Consola.readChar();
        } while (ascendencia != 't' && ascendencia != 'f');

        boolean ascendenciaBool = false;
        if (ascendencia == 't') {
            ascendenciaBool = true;
        }
        h = new Heap(cantidad, ascendenciaBool);
    }

    /**
     * Realiza la carga de la cantidad de elementos elegido en el metodo crear().
     */
    private static void cargaManual() {
        System.out.println("Comienza la carga...");
        int contador = 0;
        while (contador != cantidad) {
            System.out.print("Ingrese el número que desea agregar: ");
            int T = Consola.readInt();
            h.add(T);
            contador++;
        }
        System.out.println("La carga finalizó...");
    }

    /**
     * Rellena el Heap con valores aleatorios (entre 0 y el tamaño inicial del Heap).
     */
    private static void cargaAutomatica() {
        System.out.println("Comienza la carga...");
        int contador = 0;
        while (contador != cantidad) {
            h.add((int) (Math.random() * cantidad));
            contador++;
        }
        System.out.println("La carga finalizó...");
    }

    /**
     * Carga por teclado un numero, y si la insercion se realiza en tiempo constante, 
     * imprime cierto mensaje. Si no, imprime otro mensaje.
     * Por ultimo pregunta si se desea probar suerte de nuevo.
     */
    private static void agregarConSuerte() {
        System.out.print("Ingrese el numero a probar suerte: ");
        int suerte = Consola.readInt();
        if (h.lucky(suerte)) {
            System.out.println("El número se ingresó en la raiz... :D");
        } else {
            System.out.println("Seguí participando... insercion logaritmica :/");
        }
        char again;
        System.out.println("Deseas probar tu suerte de nuevo?...");
        System.out.print("(Presiona 's' para aceptar, o cualquier otra tecla para cancelar: )");
        again = Consola.readChar();
        if (again == 's') {
            agregarConSuerte();
        }
    }

    /**
     * Muestra los actuales dos optimos del Heap.
     * Si el Heap es ascendente, serán los dos menores de todo el Heap.
     * Si el Heap es descendente, serán los dos mayores de todo el Heap.
     */
    private static void mostrarOptimos() {
        SimpleList dosOptimosGet = h.getBoth();
        System.out.println("Optimos: \n" + dosOptimosGet.toString());
    }

    /**
     * Idem al anterior, pero en vez de acceder solo a los optimos, se los elimina del Heap además.
     */
    private static void removerOptimos() {
        SimpleList dosOptimosRemove = h.removeBoth();
        System.out.println("Removiendo los dos optimos: \n" + dosOptimosRemove.toString());

    }

    /**
     * Muestra el contenido del Heap (o sea, se verá el arbol binario completo o casi completo)
     * (notese que esto significa que no está mostrando el Heap ordenado).
     * Y encierra entre [ ] a cada nivel del arbol binario completo o casi completo.
     */
    private static void toStringPorNiveles() {
        System.out.println(h.toString());
    }

    /**
     * Idem al anterior, la unica diferencia es que muestra todos los nodos del arbol
     * solamente separados por ", ". NO agrupa entre [ ] los nodos de cada nivel del arbol.
     */
    private static void toStringSinNiveles() {
        System.out.println(h.toStringSinNiveles());
    }

    /**
     * Extrae uno por uno los elementos del Heap.
     * De esta forma irá mostrando, dependiendo de si el Heap es ascendente (de menor a mayor),
     * o si es descendente (de mayor a menor).
     * Notese que luego de esta operacion el Heap quedará vacio.
     */
    private static void extraerElementos() {
        System.out.print("Mostramos en orden de extraccion: \n[ ");
        while (!h.isEmpty()) {
            int x = h.remove();
            System.out.print(x + " ");
        }
        System.out.println("]\n");
    }

    /**
     * Test propuesto a modo de ejemplo, con todas las operaciones.
     * Por si no se quiere probar con valores manualmente, y solo se desea probar con valores ya establecidos previamente.
     */
    private static void testPropuesto() {
        //Creo un Heap de tamaño 6, inicialmente.
        h = new Heap<>(6, true);
        System.out.println("Se creó el Heap correctamente...");
        System.out.println("Ascendente y de tamaño inicial 6...");

        //Se agregan algunos elementos. Y el Heap se ordena cada vez que se agrega uno.
        h.add(5);
        h.add(12);
        h.add(20);
        h.add(8);
        h.add(10);
        h.add(11);
        System.out.println("Se cargaron algunos números...");

        //Se ponen dos ejemplos del metodo lucky(). Si el Heap es ascendente, el primer lucky() da true, y el siguiente false.
        //En caso de que el Heap sea descendente, el primero devuelve false, y el segundo true.
        System.out.println("Lucky?: " + h.lucky(4));
        System.out.println("Lucky?: " + h.lucky(30));
        pause("Presione Enter para continuar...");
        
        //Se obtienen los dos optimos.
        //Si el Heap es ascendente, debe devolver la raiz y el hijo menor.
        //Si el Heap es descendente, debe devolver la raiz y el hijo mayor.
        SimpleList dosOptimosGet = h.getBoth();
        System.out.println("Optimos: \n" + dosOptimosGet.toString());
        pause("Presione Enter para continuar...");

        //Lo mismo que el getBoth(), pero esta vez elimina los optimos.
        SimpleList dosOptimosRemove = h.removeBoth();
        System.out.println("Removiendo los dos optimos: \n" + dosOptimosRemove.toString());
        pause("Presione Enter para continuar...");

        //Vector ordenado en forma de arbol binario completo o casi completo.
        System.out.println("Vector tipo arbol binario: \n" + h.toString());
        pause("Presione Enter para volver al menu...");

        //A continuacion se extrae del Heap uno a uno el menor(si es ascendente) o el mayor(descendente).
        //Y se muestran por pantalla.
        System.out.print("Mostramos en orden de extraccion: \n[ ");
        while (!h.isEmpty()) {
            int x = h.remove();
            System.out.print(x + " ");
        }
        System.out.println("]\n");
        pause("Presione Enter para continuar...");
    }
    
    /**
     * Comprueba si el Heap no apunta a null.
     * Esto sirve para "obligar" de cierta forma al usuario a utilizar las opciones 1,2,3.
     * Para poder utilizar el programa.
     * @return 
     */
    private static boolean heapCreado(){
        return (h != null);
    }
    
    /**
     * Usa la clase Consola para generar una "pausa".
     * Hasta que el usuario no presione Enter, el programa "se pausa".
     * Esto se utiliza para darle tiempo al usuario a confirmar los datos de la opcion elegida.
     */
    private static void pause(String unString){        
        System.out.print(unString);
        Consola.readLine();
    }
}
