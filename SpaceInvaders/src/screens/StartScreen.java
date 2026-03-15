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

@SuppressWarnings("deprecation")
public class StartScreen extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel wallpaperStartScreen;  //Fondo
    private JButton startButton;  //Boton de la nave normal
    private JButton startButtonCheck;  //Boton de la nave para la confirmacion del click
    private StartController controller;  //Controlador
    
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
        Board.getMyBoard().addObserver(this); 
    }

    private JLabel getWallpaperStartScreen() { //El fondo de la pantalla de start
        if (wallpaperStartScreen == null) {
            wallpaperStartScreen = new JLabel("fondillo");
            wallpaperStartScreen.setBounds(0, 0, 694, 442);
            wallpaperStartScreen.setIcon(new ImageIcon(StartScreen.class.getResource("/img/pantallastart.png")));
            wallpaperStartScreen.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return wallpaperStartScreen;
    }

    private JButton getStartButton() { //El botón de la nave
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

    private JButton getStartButtonCheck() { //La nave que se aclara como confirmación de que empiezas el juego
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


    public StartController getController() {
    	if (controller == null) {
        controller = new StartController(this);
    	}
    	return controller;
    }

    
    @Override
    public void update(Observable o, Object arg) {
    	//Aqui compruebo llega la matriz (se ha construido).
        	if (arg instanceof int[][]) {
        		Board.getMyBoard().deleteObserver(this);
                GameScreen game = new GameScreen(); //Creas la GameScreen y la pones visible quitando la StartScreen
                game.setVisible(true);
                this.setVisible(false);
            }
        }
    
    private class StartController implements ActionListener {
        private StartScreen screen;

        public StartController(StartScreen screen) {
            this.screen = screen;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            //Mostrar y ocultar el botón 3 (la nave clara para "confirmar" que hemos hecho click)
            screen.startButtonCheck.setVisible(true);
            screen.startButtonCheck.paintImmediately(0,0, screen.startButtonCheck.getWidth(), screen.startButtonCheck.getHeight());
            screen.startButtonCheck.setVisible(false);
            //Crear el tablero
            Board.getMyBoard().setBoard();
        }
    
    }
}
   