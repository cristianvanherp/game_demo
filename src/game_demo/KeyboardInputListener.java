package game_demo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardInputListener extends KeyAdapter {
	private List<Integer> keysDown;
	private InputSensible inputSensible;
	
	public KeyboardInputListener(InputSensible inputSensible) {
		this.keysDown = new ArrayList<Integer>(0);
		this.inputSensible = inputSensible;
	}
	
	public void keyPressed(KeyEvent e) {
		if(!this.keysDown.contains(e.getKeyCode()))
			this.keysDown.add((Integer)e.getKeyCode());
		this.inputSensible.KeyEvent();
	}
	
	public void keyReleased(KeyEvent e) {
		if(this.keysDown.contains(e.getKeyCode()))
			this.keysDown.remove((Integer)e.getKeyCode());
		this.inputSensible.KeyEvent();
	}
	
	public boolean isKeyDown(Integer key) {
		return this.keysDown.contains(key);
	}
}
