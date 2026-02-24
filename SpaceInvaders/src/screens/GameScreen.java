package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Board;

public class GameScreen extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    
 // las columnas, las rows y el tamanio de cada pixel (cada JLabel)
    private int colspix = 100; 
    private int rowspix  = 60;  
    private int pixelsize = 8;  
    private JPanel contentPane;
    private JPanel matrixPanel;
    private JLabel[][] pixelMatrix;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GameScreen window = new GameScreen();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructora de la pantallica
    public GameScreen() {
        setTitle("SpaceInvaders de los JaVaMalPeroCompila!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.add(createMatrixPanel(), BorderLayout.CENTER);
        pack();  
        setResizable(false);
        setLocationRelativeTo(null);
        
        Board.getMyBoard().addObserver(this);
    }

    // Aqui se ccrea la matriz de JLabels
    private JPanel createMatrixPanel() {
        if (matrixPanel == null) {
            matrixPanel = new JPanel(new GridLayout(rowspix, colspix, 0, 0));
            pixelMatrix = new JLabel[rowspix][colspix];
            
            for (int row = 0; row < rowspix; row++) {
                for (int column = 0; column < colspix; column++) {
                    JLabel pixel = new JLabel();
                    pixel.setOpaque(true);
                    pixel.setBackground(Color.BLACK);
                    pixel.setPreferredSize(new Dimension(pixelsize, pixelsize));
                    pixelMatrix[row][column] = pixel;
                    matrixPanel.add(pixel);
                }
            }
            matrixPanel.setPreferredSize(new Dimension(colspix * pixelsize, rowspix * pixelsize));
        }
        return matrixPanel;
    }
    
    @Override 
    public void update(Observable o, Object arg) {
    	//Aqui faltan cosas
    	//TODO
    	if (o == Board.getMyBoard() && arg instanceof Object[]) {
    		Object[] arr = (Object[]) arg;
    		if (arr.length > 0 && Boolean.TRUE.equals(arr[0])) {
            mirrorFromBoard(); // Aqui te lleva al metodo que va a hacer espejo para "pintar" la matriz con lo observado.
    		}
    	}
    }

    //Aqui RECORREMOS Board, para pintar las naves enemigos etcetc
    private void mirrorFromBoard() {
    	//TODO
    //he pensado utilizar un colorOnePixel para ir iluminando cada pixel segun lo que se encuentre en casilla, estan abajo.(?????)
    }
    
  //Colorea un pixel en la matriz.
    public void colorOnePixel(int x, int y, Color color) {
        if (x >= 0 && x < colspix && y >= 0 && y < rowspix) {
            pixelMatrix[y][x].setBackground(color);
        }
    }
}
