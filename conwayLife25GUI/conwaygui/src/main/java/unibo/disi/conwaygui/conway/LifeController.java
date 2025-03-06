package unibo.disi.conwaygui.conway;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import unibo.basicomm23.utils.CommUtils;
import unibo.disi.conwaygui.conway.devices.ConwayOutput;
import unibo.disi.conwaygui.conway.model.Cell;
import unibo.disi.conwaygui.conway.model.Grid;
import unibo.disi.conwaygui.webSocket.WSIoDev;

public class LifeController {
    private static final int DEFAULT_GENERATION_TIME = 1000;
    private final Life life;
    private IOutDev outdev;
    private final int generationTime;
    private boolean  running = false;
    protected int epoch = 0;

    public LifeController(Life game) {  
        this(game, DEFAULT_GENERATION_TIME);
    }

    public LifeController(Life game, int generationTime) {  
        this.life = game;
        this.generationTime = generationTime;
        outdev    = WSIoDev.getInstance(); 
        CommUtils.outyellow("LifeController CREATED ... "  + outdev);      
    }
    
    public void start() {
        System.out.println("---------Initial----------");
        displayGrid();
        running = true;
        play();
    }
    
    public void exitTheGame() {
        System.out.println("Exiting the game...");
        stopTheGame(); // Ferma il gioco in corso
        System.exit(0); // Termina l'applicazione
    }
    
    public void clearTheGame() {
        System.out.println("Clearing the game...");
        life.resetGrids(); // Resetta la griglia di gioco 
        displayGrid(); // Mostra la griglia vuota
    }

    public void stopTheGame() {
        running = false;
        System.out.println("Game stopped.");
    }
    public void play() {
        new Thread() {
			public void run() {			
				outdev.display("game started"); 
				while( running ) {
					try {
						TimeUnit.MILLISECONDS.sleep(generationTime);
						life.computeNextGen();
						
						//Come si riduce il traffico di rete?
						//Troppi messaggi con questo metodo?   						
						displayGrid(   );

						CommUtils.outblue("---------Epoch ---- "+epoch++ );
						boolean gridEmpty  = life.gridEmpty();
						boolean gridStable = life.gridStable();
						if( gridEmpty || gridStable ) {
				    		running = false;
				    		String reason = gridStable ? "stable" : "empty";
				    		outdev.display("grid GAME ENDED after " + epoch + 
				    				" Epochs since empty=" + gridEmpty + " stable="+ gridStable);
				    		epoch = 0;
				    	}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}//while
				outdev.display("game stopped"); 
			}
			}.start();
    }

    public void displayGrid() {
        Grid grid = life.getCurrentGrid();
 		int rows = grid.getRows();
		int cols = grid.getCols();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = grid.getCell(i,j);
				outdev.displayCell(cell); 
 			}			
		}
    }

    public Life getLife() {
        return life;
    }

    public void playTheGame() {
        play();
    }

    public void swithCellState(int i, int j) {
        life.switchCellState(i, j);
        
    }
}
