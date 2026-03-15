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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Board;

@SuppressWarnings("deprecation")
public class GameScreen extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    
 //Las columnas, las rows y el tamanio de cada pixel (cada JLabel)
    private int colspix = 100; 
    private int rowspix  = 60;  
    private int pixelsize = 8;  
    private JPanel contentPane;
    private JPanel matrixPanel;
    private JLabel[][] pixelMatrix;
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

    // Constructora de la pantalla de juego
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
        activarControles(getController());
        addWindowListener(getController());
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
    		gController = new GameController(this);
    	}
    	return gController;
    }
    
    @Override 
    public void update(Observable o, Object arg) {
    	if (arg instanceof int[][]) { //Si te llega la matriz vas actualizando la vista con el tablero actualizado
    			int[][] mat = (int[][]) arg;
    			mirrorFromBoard(mat);
    	} else if (arg instanceof String) {//Si te llega una string significa que has perdido o ganado el juego
    			String message = (String) arg;
    			if (message.contains("perdido")) {//Aqui se perdería el juego
    				JOptionPane.showMessageDialog(this, "¡Has muerto! Un alien ha llegado al final o te ha matado.", "Vuelve a intentarlo", JOptionPane.INFORMATION_MESSAGE);
    				System.exit(0); }
    			else if (message.contains("ganado")) {//Aqui se ganaria el juego
        			JOptionPane.showMessageDialog(this, "¡Has ganado! Has salvado la tierra.", "¿Premio o castigo?", JOptionPane.INFORMATION_MESSAGE);
        			System.exit(0);
    			}
    		}
    	}
  

    //Aqui RECORREMOS Board, para pintar las naves enemigos etcetc
    private void mirrorFromBoard(int[][] mat) {
    	for (int r = 0; r < rowspix; r++) {
    		for (int c = 0; c < colspix; c++) {
    			if (mat[r][c] == 0) {
                    this.colorOnePixel(r, c, Color.BLACK);} //El fondo
    			else if (mat[r][c] == 1){
    				this.colorOnePixel(r, c, Color.MAGENTA);} //Los aliens
    			else if(mat[r][c] == 2) {
    				this.colorOnePixel(r, c, Color.CYAN);} //La nave
    			else {
    				this.colorOnePixel(r, c, Color.YELLOW); //Los disparos
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
        Set<Integer> teclasPresionadas = new HashSet<>();
        addKeyListener(new KeyAdapter() {     
            public void keyPressed(KeyEvent e) {
            	teclasPresionadas.add(e.getKeyCode());
            	for (int k: teclasPresionadas) {
            		if (k == KeyEvent.VK_A) {
                        controller.actionPerformed(new ActionEvent(this, 0, "A"));}
                    else if (k == KeyEvent.VK_D) {
                        controller.actionPerformed(new ActionEvent(this, 0, "D"));}
                    else if (k == KeyEvent.VK_W) {
                        controller.actionPerformed(new ActionEvent(this, 0, "W"));}
                    else if (k == KeyEvent.VK_S) {
                        controller.actionPerformed(new ActionEvent(this, 0, "S"));}
                    else if (k == KeyEvent.VK_SPACE) {
                        controller.actionPerformed(new ActionEvent(this, 0, "SPACE"));
                    }
            	}
            }
            public void keyReleased(KeyEvent e) {
                teclasPresionadas.remove(e.getKeyCode());
            }
            
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    
    private class GameController implements ActionListener, WindowListener {
    	

        public GameController(GameScreen screen) { 
        }
        
        //Según lo que le llegue del activarControles mueve al jugador o dispara
        	public void actionPerformed(ActionEvent e) { 
        		String teclado = e.getActionCommand();
                if ("A".equals(teclado)) {
                    Board.getMyBoard().movePlayerLeft();}
                else if ("W".equals(teclado)) {
                	Board.getMyBoard().movePlayerUp();}
                else if ("S".equals(teclado)) {
                	Board.getMyBoard().movePlayerDown();}
                else if ("D".equals(teclado)) {
                	Board.getMyBoard().movePlayerRight();}
                else if ("SPACE".equals(teclado)) {
                	Board.getMyBoard().shoot();;
                }
        	}


        	//Se generan metodos que no utilizamos con el WindowListener, no se pueden quitar porque dan error
        	//El windowOpened hace que se empiece a actualizar el tablero según se abra la GameScreen
			@Override
			public void windowOpened(WindowEvent e) {
    			Board.getMyBoard().actBoard();
			}



			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}



			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}



        	
    }
    
}