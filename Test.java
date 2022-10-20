public class Test {

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku(new int[9][9]);

        sudoku.cargarValoresDeFichero("sudoku.txt");

        System.out.println("Sudoku a Resolver:");
        sudoku.print();

        if (sudoku.sudokuSolver()) {
            System.out.println("Sudoku Resuelto");
            sudoku.print();
        } else {
            System.out.println("Imposible resolver Sudoku");
        }
    }

}
