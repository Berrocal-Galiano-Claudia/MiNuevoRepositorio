package com.politecnicomalaga.TrabajoTallerServidor;

import java.util.Scanner;

public class MainConsola {

    //ATRIBUTOS
    Scanner s= new Scanner(System.in);
    int opcion;
    String respuesta;
    final static int NUM_COLUMNAS_1=4;
    final static int NUM_COLUMNAS_2=2;
    final static int NUM_COLUMNAS_3=3;
    final static int NUM_COLUMNAS_4=3;

    //COMPORTAMIENTO

    //CONSTRUCTOR VACIO
    public MainConsola(){

    }

    public void iniciar(){
        do {
            System.out.println("Aplicación FileChecker. Versión texto. Escoja una opción \n" +
                    "- 1: Check de ficheros CSV \n" +
                    "- 2. Generación de ficheros CSV \n" +
                    "- 3. Encriptación de ficheros CSV \n" +
                    "- 4. Desencriptación de ficheros CSV \n" +
                    "- 0. Salir.");

            opcion =s.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("Introduzca los datos de checkeo");
                    s.nextLine();
                    respuesta=s.nextLine();
                    if (!Controlador.getSingleton().comprobarColumnas(respuesta,NUM_COLUMNAS_1)) System.out.println("Formato no adecuado");
                    else System.out.println(Controlador.getSingleton().checkFile(respuesta));
                    break;
                case 2:
                    System.out.println("Introduzca los datos de generación");
                    s.nextLine();
                    respuesta=s.nextLine();
                    if (!Controlador.getSingleton().comprobarColumnas(respuesta,NUM_COLUMNAS_2)) System.out.println("Formato no adecuado");
                    else System.out.println(Controlador.getSingleton().generateFile(respuesta));
                    break;
                case 3:
                    System.out.println("Introduzca los datos de encriptación");
                    s.nextLine();
                    respuesta=s.nextLine();
                    if (!Controlador.getSingleton().comprobarColumnas(respuesta,NUM_COLUMNAS_3)) System.out.println("Formato no adecuado");
                    else System.out.println(Controlador.getSingleton().codeFile(respuesta));
                    break;
                case 4:
                    System.out.println("Introduzca los datos de desencriptación");
                    s.nextLine();
                    respuesta=s.nextLine();
                    if (!Controlador.getSingleton().comprobarColumnas(respuesta,NUM_COLUMNAS_4)) System.out.println("Formato no adecuado");
                    else System.out.println(Controlador.getSingleton().decodeFile(respuesta));
                    break;
                default:
                    System.out.println("Opcion no reconocida");
                    break;
            }


        }while (opcion!=0);

    }

}
