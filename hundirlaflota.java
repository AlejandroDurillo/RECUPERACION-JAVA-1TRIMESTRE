package RANDOM;

//IMPORTANTE IMPORTAR ESTO, SIN ESTO NO VA A FUNCIONAR NADA
import java.util.Random;
import java.util.Scanner;


/**
 * DISCLAIMER ESTE CODIGO ES PARA LLEGAR AL 5, ESTO OS DA PARA LO MINIMO Y ES MERAMENTE UNA GUIA 
 *  @author Alejandro Durillo
 */

public class hundirlaflota {

    //Aqui se declaran las constantes, recordad esto SIEMPRE EN MAYUSCULA
    static final int FILAS = 3; //Numero de filas que se quieren
    static final int COLUMNAS = 3; //Numero de columnas que se quieren

    //aquí se pueden poner los caracteres para el agua, el hundido y los chars del barco aliado y el enemigo
    static final String AGUA = "~";
    static final String BLOQUEO = "#"; //Esto es para que el mapa no nos muestre los barcos enemigos
    static final String VACIO = " "; // para inicializar el tablero

    //Aqui van los "Personajes"
    static final String BARCO = "B";
    static final String ENEMIGO = "O";

    //ahora vamos con las funciones, como en este hundir la flota vamosa tener un
    //Enemigo random hay que hacer 2 tableros

    static void inicializarTablero(String[][] tablero) { //es mas comodo inicializar el array aqui
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = VACIO;
                //aqui básicamente lo que se hace es recorrer el array y asignarle a los valores 0-1-2 el caracter "VACIO"
            }
        }
    }

    static void inicializarTableroEnemigo(String[][] tableroEnemigo) { //es mas comodo inicializar el array aqui
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tableroEnemigo[i][j] = VACIO;
                //aqui lo mismo pero para el enemigo
            }
        }
    }

   public static void pintarTablero(String[][] tablero) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                //IMPORTANTE: aqui SOLO pintamos, NO modificamos el tablero real
                System.out.print(BLOQUEO + " "); //Aqui hacemos que el tablero se vea con #
            }
            System.out.println();
        }
    }

    static void colocarBarcos(String[][] tablero) {
        Scanner sc = new Scanner(System.in);
        int barcospuestos = 3; //Aqui ponemos que los barcos que vamos a poner van a ser 3

        //esto hace que siempre que el numero de barcos que hayamos puesto sea diferente a 0
        while (barcospuestos != 0) {
            System.out.println("En que fila quieres poner tu barco? (0-2): ");
            int i = sc.nextInt();
            System.out.println("En que columna quieres poner el barco?");
            int j = sc.nextInt();

            //comprobamos que no se salga del tablero
            if (i < 0 || i >= FILAS || j < 0 || j >= COLUMNAS) {
                System.out.println("Posicion fuera del tablero");
                continue;
            }

            //ahora vamos con los if/else
            if (tablero[i][j].equals(BARCO)) {
                System.out.println("Aqui ya has puesto barco"); //comprobacion de error
            }

            if (tablero[i][j].equals(VACIO)) {
                tablero[i][j] = BARCO; //AQUI SE COLOCA REALMENTE EL BARCO
                System.out.println("Barco colocado");
                barcospuestos--;
            }
        }
    }

    static void barcosEnemigos(String[][] tableroEnemigo) {
        int barcosMalos = 3;
        Random rnd = new Random();

        while (barcosMalos != 0) {
            int i = rnd.nextInt(FILAS);
            int j = rnd.nextInt(COLUMNAS);

            // Comprobamos ANTES de colocar para no repetir posicion
            if (tableroEnemigo[i][j].equals(VACIO)) {
                tableroEnemigo[i][j] = ENEMIGO;
                barcosMalos--;
            }
        }
    }

    //OJO en el turno de ataque del jugador tienes que declarar el array ENEMIGO



    


    public static void main(String[] args) {
        String[][] tablero = new String[FILAS][COLUMNAS]; //inicializamos el tablero del jugador
        String[][] tableroEnemigo = new String[FILAS][COLUMNAS]; //tablero enemigo
        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        int barcosDerribados = 3; //aqui ponemos el numero de barcos enemigos que haya
        int barcosPerdidos = 3;
        boolean salir = false;

        inicializarTablero(tablero);
        inicializarTableroEnemigo(tableroEnemigo);

        pintarTablero(tablero);
        colocarBarcos(tablero);
        barcosEnemigos(tableroEnemigo);

        while(salir != true){
            //Turno ataque aliado
            while (barcosDerribados != 0) {
                System.out.println("Añade la fila que quieras atacar: ");
                int i = sc.nextInt();
                System.out.println("Añade la columna que quieras atacar");
                int j = sc.nextInt();

                //comprobamos limites del tablero
                if (i < 0 || i >= FILAS || j < 0 || j >= COLUMNAS) {
                    System.out.println("Disparo fuera del tablero");
                    continue;
                
                }

                //comprobaciones
                if (tableroEnemigo[i][j].equals(ENEMIGO)) {
                    System.out.println("Has dado al enemigo");
                    tableroEnemigo[i][j] = AGUA; //marcamos la posicion para no volver a contarla
                    barcosDerribados--;
                } else {
                    System.out.println("Has fallado, vuelvelo a intentar");
                }

                //ataque enemigo
                int ie = rnd.nextInt(FILAS);
                int je = rnd.nextInt(COLUMNAS);

                //comprobaciones del enemigo
                if(tablero[ie][je].equals(BARCO)){
                    System.out.println("Nos han dado");
                    tablero[ie][je] = AGUA;
                    barcosPerdidos--;
                    
                }else{
                    System.out.println("El enemigo ha fallado");
                }
                if(barcosPerdidos == 0){
                    System.out.println("Has perdido");
                    salir = true;
                    return;
                }

            }

            if(barcosDerribados == 0){
                System.out.println("ENHORABUENA HAS GANADO");
                salir = true;
            }
          


        }
       

        
    


        }
    }


    

