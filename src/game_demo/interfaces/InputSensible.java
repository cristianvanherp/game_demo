package game_demo.interfaces;

import java.awt.event.MouseEvent;

public interface InputSensible {
	public void keyEvent();
	public void mouseEvent(MouseEvent mouseEvent);
	public void mouseMoved(MouseEvent mouseEvent);
}
