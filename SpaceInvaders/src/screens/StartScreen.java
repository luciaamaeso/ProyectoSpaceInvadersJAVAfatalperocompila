package screens;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Board;

public class StartScreen extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel wallpaperStartScreen;  //Fondo
    private JButton startButton;  //Boton de la nave normal
    private JButton startButtonCheck;  //Boton de la nave para la confirmacion del click
    private StartController controller;  //Controlador
    private final Board model = Board.getMyBoard();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StartScreen window = new StartScreen();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public StartScreen() {
        setBackground(new Color(0, 0, 0));
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(StartScreen.class.getResource("/img/spaceinvaderslogo.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 471);
        contentPane = new JPanel(null);
        setContentPane(contentPane);
        contentPane.add(getWallpaperStartScreen());
        contentPane.add(getStartButton());
        contentPane.add(getStartButtonCheck());
        setLocationRelativeTo(null);

        //Esto es para el orden de visualizacion, para que al visualizarse el boton de la nave de confirmacion, 
        //se vea por encima del fondo y del boton de la nave normal.
        contentPane.setComponentZOrder(getStartButtonCheck(), 0);
        contentPane.setComponentZOrder(getStartButton(), 1);
        contentPane.setComponentZOrder(getWallpaperStartScreen(), 2);
        startButtonCheck.setVisible(false);
        
        //Aniadimos el observer al board.
        model.addObserver(this); 
    }

    private JLabel getWallpaperStartScreen() {
        if (wallpaperStartScreen == null) {
            wallpaperStartScreen = new JLabel("fondillo");
            wallpaperStartScreen.setBounds(0, 0, 694, 442);
            wallpaperStartScreen.setIcon(new ImageIcon(StartScreen.class.getResource("/img/pantallastart.png")));
            wallpaperStartScreen.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return wallpaperStartScreen;
    }

    private JButton getStartButton() {
        if (startButton == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/nave.png"));
            startButton = new JButton(icon);
            startButton.setBounds(288, 236, 106, 85);
            startButton.setRolloverIcon(icon);
            startButton.setPressedIcon(icon);
            startButton.setContentAreaFilled(false);
            startButton.setBorderPainted(false);
            startButton.setFocusPainted(false);
            startButton.setOpaque(false);
            startButton.setText(null);
            startButton.addActionListener(getController()); // Llamar al evento del controlador.
        }
        return startButton;
    }

    private JButton getStartButtonCheck() {
        if (startButtonCheck == null) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/navemasclara.png"));
            startButtonCheck = new JButton(icon);
            startButtonCheck.setBounds(288, 236, 106, 85);
            startButtonCheck.setRolloverIcon(icon);
            startButtonCheck.setPressedIcon(icon);
            startButtonCheck.setContentAreaFilled(false);
            startButtonCheck.setBorderPainted(false);
            startButtonCheck.setFocusPainted(false);
            startButtonCheck.setOpaque(false);
            startButtonCheck.setText(null);
        }
        return startButtonCheck;
    }


    private StartController getController() {
    	if (controller == null) {
        controller = new StartController(this, model);
    	}
    	return controller;
    }

    
    @Override
    public void update(Observable o, Object arg) {
    	//Aqui compruebo que el observable es el model(el board) y que llega un array de objetos.
        if (o == model && arg instanceof Object[]) {
            Object[] a = (Object[]) arg;
    		//Llega con una longitud mayor a 0 y lo que se encuentra es un boolean true? Pues se abre la GameScreen.
            if (a.length > 0 && Boolean.TRUE.equals(a[0])) {
                GameScreen game = new GameScreen();
                game.setVisible(true);
                dispose();
            }
        }
    }
    private class StartController implements ActionListener {
        private StartScreen screen;
        private Board model;

        public StartController(StartScreen screen, Board model) {
            this.screen = screen;
            this.model = model;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            //Mostrar y ocultar el botón 3 (la nave clara para "confirmar" que hemos hecho click)
            screen.startButtonCheck.setVisible(true);
            screen.startButtonCheck.paintImmediately(0,0, screen.startButtonCheck.getWidth(), screen.startButtonCheck.getHeight());
            screen.startButtonCheck.setVisible(false);
            //Crear el tablero
            model.setBoard();
        }
    }
 }