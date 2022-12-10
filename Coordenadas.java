

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Coordenadas {

    public static List<String> cordeUsadasJugador1 = new ArrayList<String>();
    public static List<String> cordeUsadasJugador2 = new ArrayList<String>();
    public static List<String> cordeNOusadas1 = new ArrayList<String>();
    public static List<String> cordeNOusadas2 = new ArrayList<String>();
    public static List<String> barcos = new ArrayList<String>();
    public static int [][] tablero=null;
    public static int[][] tablero2=null;

    public static int [][] tableroNuevo1=null;
    public static int[][] tableroNuevo2=null;

    public static int puntajeJ1 = 0;
    public static int puntajeJ2 = 0;

    public static void main(String[] args) {

        int columna;
        int fila;

        Scanner entrada = new Scanner(System.in);

        System.out.print("**INGRESE NOMBRE DEL PRIMER JUGADOR:");
        String nombrejugador1 = entrada.nextLine();
        Jugador jugador1 = new Jugador();
        jugador1.nombre=nombrejugador1;
        jugador1.imprimirNombre();
        List<Barco> barcosJugador1 = crearBarcos();


        System.out.print("**INGRESE NOMBRE DEL SEGUNDO JUGADOR:");
        String nombrejugador2 = entrada.nextLine();
        Jugador jugador2 = new Jugador();
        jugador2.nombre=nombrejugador2;
        jugador2.imprimirNombre();
        List<Barco> barcosJugador2 = crearBarcos();


        System.out.println("****DEFINIR TAMAÑO DEL TABLERO***");
        System.out.print("  TAMAÑO DE COLUMNAS: ");
        columna = entrada.nextInt();
        System.out.print("  TAMAÑO DE FILAS: ");
        fila = entrada.nextInt();
        crearTablero(columna, fila);
        crearTablero2(columna, fila);
        System.out.println();
        imprimirTablero();

        for(Barco b: barcosJugador1){
            boolean cordenadasnoCorrectas=true;
            while(cordenadasnoCorrectas) {
                jugador1.imprimirNombre();
                System.out.println("-------COLOCACION DE BARCOS--------");
                System.out.println("EN QUE COORDENADAS COLOCARA SU BARCO DE TAMAÑO: " + b.tamanio);
                System.out.println("CORDENADA 1: ");
                String cords = entrada.next();
                boolean cordenadaValida = validarCordenadas(cords, columna, fila, "Jugador1");
                if(cordenadaValida){
                    String xy[] = cords.split(",");
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);
                    tablero[x][y] = 1;

                    if (b.tamanio > 1) {
                        System.out.println("CORDENADA 2: ");
                        String cords2 = entrada.next();
                        cordenadaValida = validarCordenadas(cords2, columna, fila, "Jugador1");
                        if(cordenadaValida) {
                            tablero[x][y] = b.tamanio;
                            String xy2[] = cords2.split(",");
                            int x2 = Integer.parseInt(xy2[0]);
                            int y2 = Integer.parseInt(xy2[1]);

                            tablero[x2][y2] = (b.tamanio - b.tamanio - b.tamanio);

                            //recorre hacia adelante
                            int contador = 1;
                            for (int i = Integer.parseInt(xy[1]); i < columna; i++) {
                                if (tablero[Integer.parseInt(xy[0])][i] == (b.tamanio - b.tamanio - b.tamanio)) {
                                    break;
                                }

                                ++contador;
                            }

                            if (contador == b.tamanio) {
                                System.out.println("ACEPTADO, SE ENCONTRO ENFRENTE");
                                System.out.println("CONTADOR:" + contador);
                                cordenadasnoCorrectas = false;
                                cordeUsadasJugador1.add(cords);
                                if(b.tamanio == 3) {
                                    String coord3 = x2 +","+ (y2-1);
                                    cordeUsadasJugador1.add(coord3);
                                }
                                if(b.tamanio == 4) {
                                    String coord3 = x2 +","+ (y2-1);
                                    cordeUsadasJugador1.add(coord3);
                                    String coord4 = x2 +","+ (y2-2);
                                    cordeUsadasJugador1.add(coord4);
                                }
                                cordeUsadasJugador1.add(cords2);
                            }

                            ///OTRO IF
                            if (cordenadasnoCorrectas) {
                                //recorre hacia atras
                                contador = 1;
                                for (int i = Integer.parseInt(xy[1]); i > 0; i--) {
                                    if (tablero[Integer.parseInt(xy[0])][i] == (b.tamanio - b.tamanio - b.tamanio)) {
                                        break;
                                    }

                                    ++contador;
                                }

                                if (contador == b.tamanio) {
                                    System.out.println("ACEPTADO, SE ENCONTRO ATRAS");
                                    System.out.println("CONTADOR:" + contador);
                                    cordenadasnoCorrectas = false;
                                    cordeUsadasJugador1.add(cords);
                                    if(b.tamanio == 3) {
                                        String coord3 = x2 +","+ (y2+1);
                                        cordeUsadasJugador1.add(coord3);
                                    }
                                    if(b.tamanio == 4) {
                                        String coord3 = x2 +","+ (y2+1);
                                        cordeUsadasJugador1.add(coord3);
                                        String coord4 = x2 +","+ (y2+2);
                                        cordeUsadasJugador1.add(coord4);
                                    }
                                    cordeUsadasJugador1.add(cords2);
                                }

                                if (cordenadasnoCorrectas) {
                                    //recorre hacia abajo
                                    contador = 1;
                                    for (int i = Integer.parseInt(xy[0]); i < fila; i++) {
                                        if (tablero[i][Integer.parseInt(xy[1])] == (b.tamanio - b.tamanio - b.tamanio)) {
                                            ++contador;
                                            break;
                                        }
                                        ++contador;
                                    }

                                    if (contador == b.tamanio) {
                                        System.out.println("ACEPTADO, SE ENCONTRO ABAJO");
                                        System.out.println("CONTADOR:" + contador);
                                        cordenadasnoCorrectas = false;
                                        cordeUsadasJugador1.add(cords);
                                        if(b.tamanio == 3) {
                                            String coord3 = (x2-1) +","+ y2;
                                            cordeUsadasJugador1.add(coord3);
                                        }
                                        if(b.tamanio == 4) {
                                            String coord3 = (x2-1) +","+ y2;
                                            cordeUsadasJugador1.add(coord3);
                                            String coord4 = (x2-2) +","+ y2;
                                            cordeUsadasJugador1.add(coord4);
                                        }
                                        cordeUsadasJugador1.add(cords2);
                                    }
                                    if (cordenadasnoCorrectas) {
                                        //recorre hacia arriba
                                        contador = 1;
                                        for (int i = Integer.parseInt(xy[0]); i > 0; i--) {
                                            if (tablero[i][Integer.parseInt(xy[1])] == (b.tamanio - b.tamanio - b.tamanio)) {
                                                break;
                                            }
                                            ++contador;
                                        }
                                        if (contador == b.tamanio) {
                                            System.out.println("ACEPTADO, SE ENCONTRO ARRIBA");
                                            System.out.println("CONTADOR:" + contador);
                                            cordenadasnoCorrectas = false;
                                            cordeUsadasJugador1.add(cords);
                                            if(b.tamanio == 3) {
                                                String coord3 = (x2+1) +","+ y2;
                                                cordeUsadasJugador1.add(coord3);
                                            }
                                            if(b.tamanio == 4) {
                                                String coord3 = (x2+1) +","+ y2;
                                                cordeUsadasJugador1.add(coord3);
                                                String coord4 = (x2+2) +","+ y2;
                                                cordeUsadasJugador1.add(coord4);
                                            }
                                            cordeUsadasJugador1.add(cords2);
                                        }else{
                                            tablero[x2][y2]=0;
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        cordenadasnoCorrectas=false;
                        cordeUsadasJugador1.add(cords);
                    }
                }
            }
            imprimirTablero();
        }


        for(Barco b: barcosJugador2){
            boolean cordenadasnoCorrectas=true;
            while(cordenadasnoCorrectas) {
                jugador2.imprimirNombre();
                System.out.println("EN QUE COORDENADAS COLOCARA SU BARCO DE TAMAÑO: " + b.tamanio);
                System.out.println("CORDENADA 1: ");
                String cords = entrada.next();
                boolean cordenadaValida = validarCordenadas(cords, columna, fila, "Jugador2");
                if(cordenadaValida){
                    String xy[] = cords.split(",");
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);
                    tablero2[x][y] = 1;

                    if (b.tamanio > 1) {
                        System.out.println("CORDENADA 2: ");
                        String cords2 = entrada.next();
                        cordenadaValida = validarCordenadas(cords2, columna, fila, "Jugador2");
                        if(cordenadaValida) {
                            tablero2[x][y] = b.tamanio;
                            String xy2[] = cords2.split(",");
                            int x2 = Integer.parseInt(xy2[0]);
                            int y2 = Integer.parseInt(xy2[1]);

                            tablero2[x2][y2] = (b.tamanio - b.tamanio - b.tamanio);

                            //recorre hacia adelante
                            int contador = 1;
                            for (int i = Integer.parseInt(xy[1]); i < columna; i++) {
                                if (tablero2[Integer.parseInt(xy[0])][i] == (b.tamanio - b.tamanio - b.tamanio)) {
                                    break;
                                }

                                ++contador;
                            }

                            if (contador == b.tamanio) {
                                System.out.println("ACEPTADO, SE ENCONTRO ENFRENTE");
                                System.out.println("CONTADOR:" + contador);
                                cordenadasnoCorrectas = false;
                                cordeUsadasJugador2.add(cords);
                                if(b.tamanio == 3) {
                                    String coord3 = x2 +","+ (y2-1);
                                    cordeUsadasJugador2.add(coord3);
                                }
                                if(b.tamanio == 4) {
                                    String coord3 = x2 +","+ (y2-1);
                                    cordeUsadasJugador2.add(coord3);
                                    String coord4 = x2 +","+ (y2-2);
                                    cordeUsadasJugador2.add(coord4);
                                }
                                cordeUsadasJugador2.add(cords2);

                            }

                            ///OTRO IF
                            if (cordenadasnoCorrectas) {
                                //recorre hacia atras
                                contador = 1;
                                for (int i = Integer.parseInt(xy[1]); i > 0; i--) {
                                    if (tablero2[Integer.parseInt(xy[0])][i] == (b.tamanio - b.tamanio - b.tamanio)) {
                                        break;
                                    }

                                    ++contador;
                                }

                                if (contador == b.tamanio) {
                                    System.out.println("ACEPTADO, SE ENCONTRO ATRAS");
                                    System.out.println("CONTADOR:" + contador);
                                    cordenadasnoCorrectas = false;
                                    cordeUsadasJugador2.add(cords);
                                    if(b.tamanio == 3) {
                                        String coord3 = x2 +","+ (y2+1);
                                        cordeUsadasJugador2.add(coord3);
                                    }
                                    if(b.tamanio == 4) {
                                        String coord3 = x2 +","+ (y2+1);
                                        cordeUsadasJugador2.add(coord3);
                                        String coord4 = x2 +","+ (y2+2);
                                        cordeUsadasJugador2.add(coord4);
                                    }
                                    cordeUsadasJugador2.add(cords2);
                                }

                                if (cordenadasnoCorrectas) {
                                    //recorre hacia abajo
                                    contador = 1;
                                    for (int i = Integer.parseInt(xy[0]); i < fila; i++) {
                                        if (tablero2[i][Integer.parseInt(xy[1])] == (b.tamanio - b.tamanio - b.tamanio)) {
                                            ++contador;
                                            break;
                                        }
                                        ++contador;
                                    }

                                    if (contador == b.tamanio) {
                                        System.out.println("ACEPTADO, SE ENCONTRO ABAJO");
                                        System.out.println("CONTADOR:" + contador);
                                        cordenadasnoCorrectas = false;
                                        cordeUsadasJugador2.add(cords);
                                        if(b.tamanio == 3) {
                                            String coord3 = (x2-1) +","+ y2;
                                            cordeUsadasJugador2.add(coord3);
                                        }
                                        if(b.tamanio == 4) {
                                            String coord3 = (x2-1) +","+ y2;
                                            cordeUsadasJugador2.add(coord3);
                                            String coord4 = (x2-2) +","+ y2;
                                            cordeUsadasJugador2.add(coord4);
                                        }
                                        cordeUsadasJugador2.add(cords2);
                                    }
                                    if (cordenadasnoCorrectas) {
                                        //recorre hacia arriba
                                        contador = 1;
                                        for (int i = Integer.parseInt(xy[0]); i > 0; i--) {
                                            if (tablero2[i][Integer.parseInt(xy[1])] == (b.tamanio - b.tamanio - b.tamanio)) {
                                                break;
                                            }
                                            ++contador;
                                        }
                                        if (contador == b.tamanio) {
                                            System.out.println("ACEPTADO, SE ENCONTRO ARRIBA");
                                            System.out.println("CONTADOR:" + contador);
                                            cordenadasnoCorrectas = false;
                                            cordeUsadasJugador2.add(cords);
                                            if(b.tamanio == 3) {
                                                String coord3 = (x2+1) +","+ y2;
                                                cordeUsadasJugador2.add(coord3);
                                            }
                                            if(b.tamanio == 4) {
                                                String coord3 = (x2+1) +","+ y2;
                                                cordeUsadasJugador2.add(coord3);
                                                String coord4 = (x2+2) +","+ y2;
                                                cordeUsadasJugador2.add(coord4);
                                            }
                                            cordeUsadasJugador2.add(cords2);
                                        }else{
                                            tablero2[x2][y2]=0;
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        cordenadasnoCorrectas=false;
                        cordeUsadasJugador2.add(cords);
                    }
                }
            }
            imprimirTablero2();
        }

        for(String c : cordeUsadasJugador1) {
            System.out.println(c);
        }
        System.out.println("------------------------");
        for(String c : cordeUsadasJugador2) {
            System.out.println(c);
        }

        boolean seguirJuego = true;
        boolean turno1= true;
        boolean turno2= false;
        while(seguirJuego) {

            if(turno1) {
                tirar(jugador1, null, entrada);
                turno1 = false;
                turno2 = true;
            }else{
                if(turno2) {
                    tirar(null, jugador2, entrada);
                    turno2 = false;
                    turno1 = true;
                }
            }

            if(puntajeJ1 >= 10) {
                System.out.println("GANO EL JUGADOR "+jugador1.nombre+" CON "+puntajeJ1+" PUNTOS!!");
                System.out.println("PERDEDOR JUGADOR "+jugador2.nombre+" CON "+puntajeJ2+" PUNTOS!!");
                seguirJuego = false;
            }

            if(puntajeJ2 >= 10) {
                System.out.println("GANO EL JUGADOR "+jugador2.nombre+" CON "+puntajeJ2+" PUNTOS!!");
                System.out.println("PERDEDOR JUGADOR "+jugador1.nombre+" CON "+puntajeJ1+" PUNTOS!!");
                seguirJuego = false;
            }

        }



    }

    public static void tirar(Jugador jugador1, Jugador jugador2, Scanner entrada) {

        if(jugador1 != null) {
            System.out.println("*****************9=TIRO ACERTADO,  7=TIRO NO ACERTADO, 0=POSICION SIN ATACAR****************");
            System.out.print("**TIRA JUGADOR " +jugador1.nombre+ ":");
            String coordenadas = entrada.next();
            String[] xy = coordenadas.split(",");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            boolean encontre=false;
            if(!cordeNOusadas2.contains(coordenadas)) {
                for(String c: cordeUsadasJugador2) {
                    if(c.equals(coordenadas)) {
                        ++puntajeJ1;
                        tableroNuevo2[x][y] = 9;
                        cordeNOusadas2.add(coordenadas);
                        System.out.println("+1 pts");
                        encontre = true;
                        break;
                    }
                }


                if(!encontre) {
                    tableroNuevo2[x][y] = 7;
                }

            }

            imprimirTableroBatalla("Jugador1");
        }else {
            System.out.println("*****************9=TIRO ACERTADO,  7=TIRO NO ACERTADO, 0=POSICION SIN ATACAR****************");
            System.out.print("**TIRA JUGADOR " +jugador2.nombre + ":");
            String coordenadas2 = entrada.next();
            String[] xy2 = coordenadas2.split(",");
            int x2 = Integer.parseInt(xy2[0]);
            int y2 = Integer.parseInt(xy2[1]);
            boolean encontre=false;
            if(!cordeNOusadas1.contains(coordenadas2)) {
                for(String c: cordeUsadasJugador1) {
                    if(c.equals(coordenadas2)) {
                        ++puntajeJ2;
                        tableroNuevo1[x2][y2] = 9;
                        cordeNOusadas1.add(coordenadas2);
                        System.out.println("+1 pts");
                        encontre = true;
                        break;
                    }
                }

                if(!encontre) {
                    tableroNuevo1[x2][y2] = 7;
                }
            }

            imprimirTableroBatalla("Jugador2");
        }

    }

    public static void crearTablero(int columna, int fila){
        tablero = new int[fila][columna];
        tableroNuevo1 = new int[fila][columna];
    }
    public static void crearTablero2(int columna, int fila){
        tablero2 = new int[fila][columna];
        tableroNuevo2 = new int[fila][columna];
    }


    public static void imprimirTableroBatalla(String tipo){


        int columna=tableroNuevo1[0].length;

        System.out.print("    ");
        if(columna<11) {
            for (int i = 0; i < columna; i++) {
                System.out.print(i + " |" + " ");
            }
        }
        else if(columna>10) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " |" + " ");
            }
            for (int j = 10; j < columna; j++) {
                System.out.print(j + "|" + " ");
            }
        }
        System.out.println();

        for(int i=0; i< tableroNuevo1.length; i++){
            if(i>=10){
                System.out.print(i+ "| ");
            }else{
                System.out.print(i+ " | ");
            }
            for(int j=0; j < tableroNuevo1[i].length; j++){
                if(tipo.equals("Jugador1")) {
                    System.out.print(tableroNuevo2[i][j]+" | ");
                }else {
                    System.out.print(tableroNuevo1[i][j]+" | ");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("*******ESTE ES EL TABLERO*******");
    }

    public static void imprimirTablero(){

        int columna=tablero[0].length;

        System.out.print("    ");
        if(columna<11) {
            for (int i = 0; i < columna; i++) {
                System.out.print(i + " |" + " ");
            }
        }
        else if(columna>10) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " |" + " ");
            }
            for (int j = 10; j < columna; j++) {
                System.out.print(j + "|" + " ");
            }
        }
        System.out.println();

        for(int i=0; i< tablero.length; i++){
            if(i>=10){
                System.out.print(i+ "| ");
            }else{
                System.out.print(i+ " | ");
            }
            for(int j=0; j < tablero[i].length; j++){
                System.out.print(tablero[i][j]+" | ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("*******ESTE ES EL TABLERO*******");
    }

    public static void imprimirTablero2(){

        int columna=tablero2[0].length;

        System.out.print("    ");
        if(columna<11) {
            for (int i = 0; i < columna; i++) {
                System.out.print(i + " |" + " ");
            }
        }
        else if(columna>10) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " |" + " ");
            }
            for (int j = 10; j < columna; j++) {
                System.out.print(j + "|" + " ");
            }
        }
        System.out.println();

        for(int i=0; i< tablero2.length; i++){
            if(i>=10){
                System.out.print(i+ "| ");
            }else{
                System.out.print(i+ " | ");
            }
            for(int j=0; j < tablero2[i].length; j++){
                System.out.print(tablero2[i][j]+" | ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("*******ESTE ES EL TABLERO 2*******");
    }
    public static class Jugador{
        public String nombre;
        public void imprimirNombre(){
            System.out.println(nombre);
        }

    }

    public static class Barco{
        public int tamanio;
    }

    public static List<Barco> crearBarcos(){
        List<Barco> barcosJugador = new ArrayList<Barco>();
        Barco barco1 = new Barco();
        barco1.tamanio=1;
        barcosJugador.add(barco1);
        Barco barco2 = new Barco();
        barco2.tamanio=2;
        barcosJugador.add(barco2);
        Barco barco3 = new Barco();
        barco3.tamanio=3;
        barcosJugador.add(barco3);
        Barco barco4 = new Barco();
        barco4.tamanio=4;
        barcosJugador.add(barco4);
        return barcosJugador;
    }

    public static boolean validarCordenadas(String cords, int columna, int fila, String tipo){
        boolean cordenadaValida=true;
        if(tipo.equals("Jugador1")){
            for(String cu:cordeUsadasJugador1){
                if(cu.equals(cords)){
                    cordenadaValida=false;
                    return cordenadaValida;
                }
            }
        }
        if(tipo.equals("Jugador2")){
            for(String cu:cordeUsadasJugador2){
                if(cu.equals(cords)){
                    cordenadaValida=false;
                    return cordenadaValida;
                }
            }
        }

        String xy[]=cords.split(",");
        int x=Integer.parseInt(xy[0]);
        int y=Integer.parseInt(xy[1]);

        if(x>columna-1){
            System.out.println("**CORDENADA X NO VALIDA: " +x+ " **");
            cordenadaValida=false;
        }
        if(y>fila-1){
            System.out.println("**CORDENADA Y NO VALIDA: " +y+ " **");
            cordenadaValida=false;
        }
        return cordenadaValida;
    }
}
