package conway;
import java.util.concurrent.TimeUnit;

import conway.devices.ConwayOutput;

public class LifeController {
    private static final int DEFAULT_GENERATION_TIME = 1000;
    private final Life life;
    private IOutDev outdev;
    private final int generationTime;

    public LifeController(Life game) {  
        this(game, DEFAULT_GENERATION_TIME);
    }

    public LifeController(Life game, int generationTime) {  
        this.life = game;
        this.generationTime = generationTime;
        this.outdev = new ConwayOutput();      
    }

	protected void configureTheSystem() {
        outdev = new ConwayOutput();      
    }
    
    public void start() {
        System.out.println("---------Initial----------");
        displayGrid();
        play();         
    }
    
    private void play() {
        for (int i = 1; i <= 5; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(generationTime);
                System.out.println("--------- Generation " + i + " ---------");
                life.computeNextGen(outdev);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }    	
    }

    public void displayGrid() {
        for (int i = 0; i < life.getRows(); i++) {
            for (int j = 0; j < life.getCols(); j++) {
                outdev.displayCell(life.getCellState(i, j) ? "1" : "0");
            }
            outdev.displayCell("\n");
        }
    }
}
