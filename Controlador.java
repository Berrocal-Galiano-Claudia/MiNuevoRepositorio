package com.example.csv;

public class Controlador {
    private static Controlador singleton;

    //COMPORTAMIENTOS

    public static Controlador getSingleton() {
        if (singleton == null) {
            singleton = new Controlador();
        }
        return singleton;
    }
}

