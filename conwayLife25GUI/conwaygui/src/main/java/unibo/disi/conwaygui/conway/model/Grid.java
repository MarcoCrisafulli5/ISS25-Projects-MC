package unibo.disi.conwaygui.conway.model;

public class Grid {
    private int rows, cols;
    private Cell[][] grid;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];

        // Inizializzazione delle celle
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j, false); // false = cella morta
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            return grid[x][y];
        }
        return null; // Evita IndexOutOfBoundsException
    }

    public int getCellNeighbors(int x, int y) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx, ny) && grid[nx][ny].isAlive()) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public void setCellState(int x, int y, boolean alive) {
        Cell cell = getCell(x, y);
        if (cell != null) {
            cell.setAlive(alive);
        }
    }

    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].isAlive() ? "O " : ". ");
            }
            System.out.println();
        }
    }

    public void resetGrids() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j, false); // false = cella morta
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
}
