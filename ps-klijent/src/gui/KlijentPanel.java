package gui;

import javax.swing.JPanel;

public class KlijentPanel extends JPanel {
	
	public String title;

	public KlijentPanel(String title) {
		this.title = title;
		setMinimumSize(getPreferredSize());
	}
	
}
