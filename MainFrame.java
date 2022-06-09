package com.politecnicomalaga.TrabajoTallerServidor;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainFrame extends JFrame implements ActionListener {

    //Hueco en entre los Layouts en pixeles
    private static final byte GAP = 10;
    Controlador miControlador;

    //Declaramos los paneles a utilizar
    protected JPanel panelBase;
    protected JPanel panelBotonera;
    protected JPanel panelCuerpo;
    protected JLabel titulo;
    protected JLabel instrucciones;

    protected JTextField respuesta;
    protected JLabel mensaje;

    String frase;


    protected JButton btckeck;
    protected JButton generar;
    protected JButton encriptar;
    protected JButton desencriptar;

    public MainFrame(String title) {
        super(title);

        //Asignamos tamaño a la ventana y hacemos que se cierre cuando clickeemos en la X
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        setBounds(200, 100, 1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Creamos los paneles
        panelBase = new JPanel();
        panelBase.setLayout(new BoxLayout(panelBase, BoxLayout.Y_AXIS));
        panelBotonera = new JPanel(new FlowLayout(FlowLayout.CENTER, GAP, GAP));
        panelCuerpo = new JPanel(new GridLayout(17, 1, GAP, GAP));


        titulo=new JLabel("Aplicacion FileChecker. Version grafica. Escoja una opcion");
        instrucciones=new JLabel("Comando a realizar:");
        respuesta=new JTextField(20);
        mensaje=new JLabel("");



        //Añadimos los paneles a la ventana
        this.add(panelBase);

        panelBase.add(panelBotonera);
        panelBase.add(panelCuerpo);
        panelBase.add(Box.createRigidArea(new Dimension(800, 200)));


        //añadimos al panelFormularioCobrados sus componentes

        panelCuerpo.add(titulo);
        panelCuerpo.add(instrucciones);
        panelCuerpo.add(respuesta);
        panelCuerpo.add(mensaje);


        btckeck = new JButton("Check de ficheros CSV");
        encriptar = new JButton("Encriptacion de ficheros CSV");
        desencriptar = new JButton("Desencriptacion de ficheros CSV");
        generar = new JButton("Generacion de ficheros CSV");


        //Añadimos los botones de la Botonera
        panelBotonera.add(btckeck);
        panelBotonera.add(generar);
        panelBotonera.add(encriptar);
        panelBotonera.add(desencriptar);
        encriptar.setBounds(200, 10, 200, 50);
        btckeck.setBounds(200, 10, 200, 50);
        desencriptar.setBounds(200, 10, 200, 50);
        generar.setBounds(200, 10, 200, 50);


        //Activamos el ActionListener de los botones
        btckeck.addActionListener(this);
        encriptar.addActionListener(this);
        desencriptar.addActionListener(this);
        generar.addActionListener(this);

    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        String text = ((JButton) ae.getSource()).getText();
        // TODO Auto-generated method stub
        if (text.contentEquals("Check de ficheros CSV")) {
            frase=respuesta.getText();
            if(DatosCorrectos(frase,4)) mensaje.setText(Controlador.getSingleton().checkFile(frase));

        }else if(text.contentEquals("Generacion de ficheros CSV")){
            frase=respuesta.getText();
            if(DatosCorrectos(frase,2)) mensaje.setText(Controlador.getSingleton().generateFile(frase));

            }else if (text.contentEquals("Encriptacion de ficheros CSV")){
            frase=respuesta.getText();
            if(DatosCorrectos(frase,3))mensaje.setText(Controlador.getSingleton().codeFile(frase));

            }else if(text.contentEquals("Desencriptacion de ficheros CSV")){
            frase=respuesta.getText();
            if(DatosCorrectos(frase,3)) mensaje.setText(Controlador.getSingleton().decodeFile(frase));

            }
        }

    private boolean DatosCorrectos(String respuesta, int numColumnas){
        if(!Controlador.getSingleton().comprobarColumnas(respuesta,numColumnas)) {
            mensaje.setText("Entornos 2022");
            return false;
        }else return true;
    }



}
