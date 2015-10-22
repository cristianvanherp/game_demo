package game_demo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputListener implements MouseListener {
	private InputSensible inputSensible;
	
	public MouseInputListener(InputSensible inputSensible) {
		this.inputSensible = inputSensible;
	}

	public void mouseClicked(MouseEvent e) {
		this.inputSensible.mouseEvent(e);
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
}
