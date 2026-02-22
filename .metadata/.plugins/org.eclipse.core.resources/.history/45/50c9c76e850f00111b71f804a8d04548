package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class pantallaJuego extends JFrame {
    private static final long serialVersionUID = 1L;
    
 // las columnas, las filas yel tamaño de cada pixel (cada JLabel)
    private int colpix = 100; 
    private int filpix  = 60;  
    private int tamañopix = 8;  

    private JPanel contentPane;
    private JPanel panelMatriz;
    private JLabel[][] matrizPixeles;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                pantallaJuego ventana = new pantallaJuego();
                ventana.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructora de la pantallica
    public pantallaJuego() {
        setTitle("SpaceInvaders de los JaVaMalPeroCompila!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.add(crearPanelMatriz(), BorderLayout.CENTER);

        pack();  
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // Aqui se ccrea la matriz de JLabels
    private JPanel crearPanelMatriz() {
        if (panelMatriz == null) {

            panelMatriz = new JPanel(new GridLayout(filpix, colpix, 0, 0));
            matrizPixeles = new JLabel[filpix][colpix];
            
            for (int fila = 0; fila < filpix; fila++) {
                for (int col = 0; col < colpix; col++) {
                    JLabel celda = new JLabel();
                    celda.setOpaque(true);
                    celda.setBackground(Color.BLACK);
                    celda.setPreferredSize(new Dimension(tamañopix, tamañopix));
                    matrizPixeles[fila][col] = celda;
                    panelMatriz.add(celda);
                }
            }

            panelMatriz.setPreferredSize(new Dimension(colpix * tamañopix, filpix * tamañopix));
        }
        return panelMatriz;
    }

    // Colorea un pixel en la matriz
    public void colorearPixel(int x, int y, Color color) {
        if (x >= 0 && x < colpix && y >= 0 && y < filpix) {
            matrizPixeles[y][x].setBackground(color);
        }
    }

    // Obtiene un pixel (JLabel) en (x, y)
    public JLabel getPixel(int x, int y) {
        if (x >= 0 && x < colpix && y >= 0 && y < filpix) {
            return matrizPixeles[y][x];
        }
        return null;
    }
}