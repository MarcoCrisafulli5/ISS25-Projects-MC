package unibo.disi.conwaygui.conway.devices;

import unibo.disi.conwaygui.conway.model.Cell;
import unibo.disi.conwaygui.conway.IOutDev;
import unibo.basicomm23.utils.CommUtils;
import unibo.disi.conwaygui.webSocket.WSIoDev;


//
public class ConwayOutput implements IOutDev{
	private WSIoDev ws;
	
	public ConwayOutput(WSIoDev ws) {
		this.ws=ws;
	}
	
        @Override
	public void displayCell(Cell cell) {
		//CommUtils.outblue("ConwayOutput| displayCell "+ cell);
		int value = cell.isAlive()? 1 : 0;
		int x = cell.getX() + 1;  //mapping to GUI coords
		int y = cell.getY() + 1;
		String msg = "cell(" + y + "," + x + ","+ value + ")";		
		//CommUtils.outcyan("ConwayOutput| displayCell "+ msg);
		display( msg );
	}


	public void display(String msg) {
		//CommUtils.outcyan("ConwayOutput| display "+msg);
		ws.broadcastToWebSocket(msg); 
	}
 	
}
