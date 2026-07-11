package gui;

import lib.KlijentPanel;
import gui.knjiga.pnlKnjiga;
import gui.kupac.pnlKupac;
import gui.racun.pnlRacun;
import gui.zaposleni.pnlZaposleni;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import main.Klijent;

public class FrmKlijent extends javax.swing.JFrame {
	
	Klijent klijent;
	
	KlijentPanel current = null;
	
	KlijentPanel racun;
	KlijentPanel kupac;
	KlijentPanel zaposleni;
	KlijentPanel knjiga;
	KlijentPanel smena;
	
	public FrmKlijent(Klijent klijent) {
		this.klijent = klijent;
		initComponents();
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setLayout(null);
		
		racun = new pnlRacun("Racun");
		kupac = new pnlKupac("Kupac");
		zaposleni = new pnlZaposleni("Zaposleni");
		knjiga = new pnlKnjiga("Knjiga");
//		smena;
		
		setMenuListeners();
	}
	
	private void removeCurrent() {
		if (current == null) {
			return;
		}
		remove(current);
		current = null;
	}

	private void setMenuListeners() {
		
		menuRacun.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				setPanel(racun);
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		});
		
		menuKupac.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				setPanel(kupac);
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		});
		
		menuZaposleni.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				setPanel(zaposleni);
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		});
		
		menuKnjiga.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				setPanel(knjiga);
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		});
		
		menuSmena.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				//setPanel();
			}
			public void menuDeselected(MenuEvent e) {}
			public void menuCanceled(MenuEvent e) {}
		});
		
	}
	
	private void setPanel(KlijentPanel panel) {
		removeCurrent();
		current = panel;
		Point location = getLocation();
		Rectangle bounds = new Rectangle(0, 0, current.getPreferredSize().width, current.getPreferredSize().height + (int)(menu.getHeight() * 2.5));
		setBounds(bounds);
		current.setBounds(bounds);
		setLocation(location);
		setTitle(current.title);
		add(current);
		current.updateTable();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JMenuBar();
        menuRacun = new javax.swing.JMenu();
        menuKupac = new javax.swing.JMenu();
        menuZaposleni = new javax.swing.JMenu();
        menuKnjiga = new javax.swing.JMenu();
        menuSmena = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(717, 512));
        setPreferredSize(new java.awt.Dimension(717, 512));

        menuRacun.setText("Racun");
        menu.add(menuRacun);

        menuKupac.setText("Kupac");
        menu.add(menuKupac);

        menuZaposleni.setText("Zaposleni");
        menu.add(menuZaposleni);

        menuKnjiga.setText("Knjiga");
        menu.add(menuKnjiga);

        menuSmena.setText("Smena");
        menu.add(menuSmena);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 717, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 488, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuKnjiga;
    private javax.swing.JMenu menuKupac;
    private javax.swing.JMenu menuRacun;
    private javax.swing.JMenu menuSmena;
    private javax.swing.JMenu menuZaposleni;
    // End of variables declaration//GEN-END:variables
}
