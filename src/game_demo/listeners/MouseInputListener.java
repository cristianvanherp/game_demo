package game_demo.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game_demo.interfaces.InputSensible;

public class MouseInputListener implements MouseListener, MouseMotionListener {
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

	public void mouseDragged(MouseEvent arg0) {
		
	}

	public void mouseMoved(MouseEvent arg0) {
		this.inputSensible.mouseMoved(arg0);
	}
	
}
