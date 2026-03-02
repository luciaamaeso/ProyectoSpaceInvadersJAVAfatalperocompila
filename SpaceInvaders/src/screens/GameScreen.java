package screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private Board board = Board.getMyBoard();
    GameController gController;

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
        board.addObserver(this);
        activarControles(getController());
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
    private GameController getController() {
    	if (gController == null) {
    		gController = new GameController(this, board);
    	}
    	return gController;
    }
    
    @Override 
    public void update(Observable o, Object arg) {
    	if (o == board && arg instanceof int[][]) {
    		int[][] mat = (int[][]) arg;
            mirrorFromBoard(mat); // Aqui te lleva al metodo que va a hacer espejo para "pintar" la matriz con lo observado.
    	}
    }

    //Aqui RECORREMOS Board, para pintar las naves enemigos etcetc
    private void mirrorFromBoard(int[][] mat) {
    	for (int r = 0; r < rowspix; r++) {
    		for (int c = 0; c < colspix; c++) {
    			if (mat[r][c] == 0) {
                    this.colorOnePixel(r, c, Color.BLACK);}
    			else if (mat[r][c] == 1){
    				this.colorOnePixel(r, c, Color.PINK);}
    			else if(mat[r][c] == 2) {
    				this.colorOnePixel(r, c, Color.BLUE);}
    			else {
    				this.colorOnePixel(r, c, Color.YELLOW);
    			}
    		}	
        }
        }    
    
  //Colorea un pixel en la matriz.
    public void colorOnePixel(int row, int col, Color color) {
        if (row >= 0 && row < rowspix && col >= 0 && col < colspix) {
            pixelMatrix[row][col].setBackground(color);
        }
    }
    public void activarControles(GameController controller) {//Este método es como un teclado, que lee lo que el usuario teclea para que el 
    														//controlador avise al modelo del cambio.
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    controller.actionPerformed(new ActionEvent(this, 0, "A"));}
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    controller.actionPerformed(new ActionEvent(this, 0, "D"));}
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                    controller.actionPerformed(new ActionEvent(this, 0, "W"));}
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    controller.actionPerformed(new ActionEvent(this, 0, "S"));}
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    controller.actionPerformed(new ActionEvent(this, 0, "SPACE"));}
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }
    private class GameController implements ActionListener {
        private GameScreen screen;
        private Board model;

        public GameController(GameScreen screen, Board model) {
            this.screen = screen;
            this.model = model;
        }
        	public void actionPerformed(ActionEvent e) {  
        		String teclado = e.getActionCommand();
                if ("A".equals(teclado)) {
                    model.movePlayerLeft();}
                else if ("W".equals(teclado)) {
                    model.movePlayerUp();}
                else if ("S".equals(teclado)) {
                    model.movePlayerDown();}
                else if ("D".equals(teclado)) {
                    model.movePlayerRight();}
                else if ("SPACE".equals(teclado)) {
                    model.playerShoot();;
                }
  
        }
    }
    
}
