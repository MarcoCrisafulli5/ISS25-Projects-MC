package unibo.disi.conwaygui.conway;

import unibo.disi.conwaygui.conway.model.Cell;

/*
 * Contratto definito dalla business logic
 */
public interface IOutDev {
	public void displayCell(Cell cell);
	public void display(String msg);
}
