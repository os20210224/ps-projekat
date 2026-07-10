package lib;

import javax.swing.JPanel;

public abstract class KlijentPanel extends JPanel {
	
	public String title;

	public KlijentPanel(String title) {
		this.title = title;
		setMinimumSize(getPreferredSize());
	}
	
	public abstract void updateTable();
	
}
