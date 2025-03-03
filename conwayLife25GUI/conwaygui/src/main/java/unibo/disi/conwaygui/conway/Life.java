package unibo.disi.conwaygui.conway;

import unibo.disi.conwaygui.conway.model.Grid;

/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
        private final int rows, cols;
        private Grid currentGrid;
        private Grid nextGrid;
    
        public Life(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.currentGrid = new Grid(rows, cols);
            this.nextGrid = new Grid(rows, cols);
        }
    
        protected void resetGrids() {
            currentGrid.resetGrids();
            nextGrid.resetGrids();
        }
    
        protected void computeNextGen(IOutDev outdev) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    applyRules(i, j);
                    outdev.displayCell(currentGrid.getCell(i, j).isAlive() ? "1" : "0");
                }
                outdev.displayCell("\n");
            }
            copyAndResetGrid();
            outdev.displayCell("\n");
        }
    
        protected void copyAndResetGrid() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    currentGrid.setCellState(i, j, nextGrid.getCell(i, j).isAlive());
                    nextGrid.setCellState(i, j, false);
                }
            }
        }
    
        protected void applyRules(int row, int col) {
            int numNeighbors = currentGrid.getCellNeighbors(row, col);
            boolean isAlive = currentGrid.getCell(row, col).isAlive();
            boolean newState = isAlive ? (numNeighbors == 2 || numNeighbors == 3) : (numNeighbors == 3);
            nextGrid.setCellState(row, col, newState);
        }
    
        public void switchCellState(int i, int j) {
            boolean currentState = currentGrid.getCell(i, j).isAlive();
            currentGrid.setCellState(i, j, !currentState);
        }
    
        public boolean getCellState(int i, int j) {
            return currentGrid.getCell(i, j).isAlive();
               }       

               public int getRows() {
                return rows;
            }
            
            public int getCols() {
                return cols;
            }

            public Grid getCurrentGrid() {
                return currentGrid;
            }

            public Grid getNextGrid() {
                return nextGrid;
            }

            public void callProtectedMethodResetGrids(){
                resetGrids();
            }
            
        
}
