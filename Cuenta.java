package com.politecnicomalaga.TrabajoTallerServidor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cuenta implements Testeador,Generador, Encriptador{


    //METODOS DE LAS INTERFACES
    @Override
    public String code(String sCSV)  {

        String[] columnas=sCSV.split(";");

        this.borrarDatosFichero(columnas[2]);

        return leerFicheroEncriptar(columnas[1],columnas[2],Integer.parseInt(columnas[0]));
    }

    @Override
    public String decode(String sCSV) {
        String[] columnas=sCSV.split(";");
        this.borrarDatosFichero(columnas[2]);
        // uso el mismo metodo pero poniendo la clave a negativa
        return leerFicheroEncriptar(columnas[1],columnas[2],-Integer.parseInt(columnas[0]));
    }

    @Override
    public String generate(String sCSV) {
        String[] columnas=sCSV.split(";");
        return grabarGenerarEnFichero(columnas[1],Integer.parseInt(columnas[0]));

    }


    @Override
    public String test(String sCSV){

        String[] columnas=sCSV.split(";");
        return leerEnFichero(columnas[1], columnas[2], columnas[3], Integer.parseInt(columnas[0]));

    }


    private String leerFicheroEncriptar(String fichero_a_leer, String ficheroEncriptado, int clave) {
        String sCSV = "";

        Scanner sc;
        byte lines = 0;
        FileReader fw= null;
        try {
            fw = new FileReader(fichero_a_leer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        try {
            //fichero del que queremos leer
            // fr = new FileReader();
            sc = new Scanner(fw);
            //mientras tenga otra linea
            while (sc.hasNextLine()){
                sCSV = sc.nextLine();
              grabarEnFichero(this.codificar(sCSV,clave),ficheroEncriptado,true);
                lines++;
            }
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }


    private String leerEnFichero(String fichero_a_leer, String ficheroBien, String ficheroMal, int num_columnas)  {
        String sCSV = "";

        Scanner sc;
        byte lines = 0;
        FileReader fw= null;
        try {
            fw = new FileReader(fichero_a_leer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return e.getMessage();
        }

        try {
            //fichero del que queremos leer
            // fr = new FileReader();
            sc = new Scanner(fw);
            //mientras tenga otra linea
            while (sc.hasNextLine()){
                sCSV = sc.nextLine();
                if(Controlador.getSingleton().comprobarColumnas(sCSV,num_columnas)){
                    grabarEnFichero(sCSV,ficheroBien,true);
                }else{
                    grabarEnFichero(sCSV,ficheroMal,true);
                }
                lines++;
            }
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }


    private String grabarEnFichero(String sCSV, String fichero, boolean append){
        //Abrir para escritura el fichero
        FileWriter fw = null;

        try {
            //abre el fichero y lo escribe
            fw = new FileWriter(fichero, append);

            //Adicionar al final la linea tal cual y un "\n"
            fw.write(sCSV + "\n");
            //Hace que lo haga ya
            fw.flush();
        } catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (fw != null)
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return e.getMessage();
                }
        }

        return "";
    }



    private String grabarGenerarEnFichero(String nombre_fichero, int num_columnas){
        //Abrir para escritura el fichero
        FileWriter fw = null;

        try {
            //abre el fichero y lo escribe
            fw = new FileWriter(nombre_fichero, false);

            //Adicionar al final la linea tal cual y un "\n"
            fw.write(generar(num_columnas));

            //Hace que lo haga ya
            fw.flush();
        } catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        } finally {
            if (fw != null)
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return e.getMessage();
                }
        }
            return "";
    }


    ///OTROS METODOS

    private String generar(int numero){
        String resultado="";
        for(int contador=1; contador<=numero; contador++){
            for(int contador2=1; contador2<=numero; contador2++){
                if(contador2==numero) resultado+=contador;
                else resultado+=contador+ ";";
            }
            resultado+="\n";
        }

        return resultado;
    }

    private String codificar(String frase, int clave){
        String resultado="";
        char letra;
        for(int contador=0; contador<frase.length();contador++){
            letra= (char) (frase.charAt(contador)+clave);
            resultado+=letra;

        }
        return resultado;
    }


    private void borrarDatosFichero(String nombre_fichero){
        //Abrir para escritura el fichero
        FileWriter fw = null;

        try {
            //abre el fichero y lo escribe
            fw = new FileWriter(nombre_fichero, false);

            //Adicionar al final la linea tal cual y un "\n"
            fw.write("");

            //Hace que lo haga ya
            fw.flush();
        } catch (IOException ignored){

        } finally {
            if (fw != null)
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block

                }
        }
    }

}
