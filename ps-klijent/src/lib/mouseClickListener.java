package lib;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract public class mouseClickListener implements MouseListener {
	
	abstract public void mouseClicked(MouseEvent e);

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
