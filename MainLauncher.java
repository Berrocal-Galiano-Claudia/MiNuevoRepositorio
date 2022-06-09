package com.politecnicomalaga.TrabajoTallerServidor;



import java.util.Scanner;

public class MainLauncher {


    //COMPORTAMIENTO

    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        String resultado;
        MainConsola consola;
        consola=new MainConsola();


        System.out.println("Aplicación FileChecker. Escriba el número de la opción deseada y pulse Intro para continuar.");
        System.out.println("- 1: Versión texto \n" +
                "- 2. Versión gráfica \n" +
                "- 0 o Cualquier otra cosa: salir. \n");
        resultado=s.nextLine();

        switch (resultado){
            case "1":
                consola.iniciar();
                break;
            case "2":
                MainFrame frame = new MainFrame("Trabajo taller");
                frame.setVisible(true);
                break;
            default:
                break;

        }

    }


}
