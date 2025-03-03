package unibo.disi.conwaygui.conway;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

import unibo.disi.conwaygui.conway.devices.ConwayInputMock;
import unibo.disi.conwaygui.conway.model.Cell;

public class conway25JavaTest {
private static ConwayInputMock cim;

	@BeforeClass
	public static void setup() {
		System.out.println("setup");
    	//configureTheSystem
        Life life           = new Life( 3,3 );
        LifeController cc   = new LifeController(life);   	
	}
	
	@After
	public void down() {
		System.out.println("down");
	}
	
	@Test
	public void test1() {
		System.out.println("ok test1");
		cim.simulateUserControl();
		//assert ??
	}
	
	@Test
	public void yyy() {
		System.out.println("ok yyy");
	}

	@Test
	public void cellAliveTest() {
		Life life = new Life(3, 3);
		LifeController lc = new LifeController(life);
		life.switchCellState( 1, 1 );
		Cell cell = life.getCurrentGrid().getCell(1, 1);
		assert cell.isAlive();
	}

	@Test
	public void cellDeadTest() {
		Life life = new Life(3, 3);
		LifeController lc = new LifeController(life);
		life.switchCellState( 1, 1 );
		life.switchCellState( 1, 1 );
		Cell cell = life.getCurrentGrid().getCell(1, 1);
		assert !cell.isAlive();
	}

	@Test
	public void cellKnownConfigEvolutionTest() {
		Life life = new Life(3, 3);
		LifeController lc = new LifeController(life);
		life.switchCellState(1, 1);
		life.switchCellState(1, 0);
		life.switchCellState(1, 2);
		lc.start();
		Cell cell = life.getCurrentGrid().getCell(0, 1);
		assertEquals(cell.isAlive(), true);
	}
}

//Con gradlew test, controllare - logs/apptest.log - build/reports/tests/test/index.html

