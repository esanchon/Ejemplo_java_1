import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eloy
 */
public class Sudoku {

    private int[][] sudoku;

    public Sudoku(int[][] sudoku) {
        this.sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.sudoku[i][j] = sudoku[i][j];
            }
        }
    }

    public boolean sudokuSolver() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (sudoku[row][col] == 0) {
                    for (int valor = 1; valor <= 9; valor++) {
                        if (esValido(row, col, valor)) {
                            sudoku[row][col] = valor;
                            if (sudokuSolver()) {
                                return true;
                            } else {
                                sudoku[row][col] = 0;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true; // sudoku solved
    }

    private boolean esValido(int row, int col, int valor) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == valor) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == valor) {
                return false;
            }
        }

        int inicioX = row - row % 3;
        int inicioY = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[inicioX + i][inicioY + j] == valor) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + sudoku[i][j]);
            }

            System.out.println();
        }

        System.out.println();
    }


    void cargarValoresDeFichero(String nombreFichero) {

        int[][] sudokuAux = new int[9][9];

        BufferedReader flujoLectura = null;


        try {
            flujoLectura = new BufferedReader(new FileReader(nombreFichero));
            int cuantos = Integer.parseInt(flujoLectura.readLine());
            for (int i = 0; i < cuantos; i++) {
                String linea = flujoLectura.readLine();
                String lineavalores[] = linea.split(" ");


                int fila = Integer.parseInt(lineavalores[0]);
                int columna = Integer.parseInt(lineavalores[1]);
                int valor = Integer.parseInt(lineavalores[2]);

                sudokuAux[fila-1][columna-1] = valor;

            }

            this.sudoku = sudokuAux;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sudoku.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sudoku.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                flujoLectura.close();
            } catch (IOException ex) {
                Logger.getLogger(Sudoku.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
