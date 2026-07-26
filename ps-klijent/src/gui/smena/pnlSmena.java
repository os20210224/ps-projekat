package gui.smena;

import domain.Smena;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import lib.KlijentPanel;
import javax.swing.JOptionPane;
import lib.mouseClickListener;
import main.Klijent;
import transfer.Response;
import transfer.enums.Status;

public class pnlSmena extends KlijentPanel {
	
	Smena selected = null;

	public pnlSmena(String title) {
		super(title);
		initComponents();
		
		btnDeselektuj.setEnabled(false);
		btnPromeni.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnResetuj.setEnabled(false);
		
		btnDodaj.addActionListener((e) -> {
			String ime = txtIme.getText().trim();
			String vremePocetkaStr = txtVremePocetka.getText().trim();
			String vremeKrajaStr = txtVremeKraja.getText().trim();
			
			if ("".equals(ime)			||
				"".equals(vremePocetkaStr)	||
				"".equals(vremeKrajaStr)) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			LocalTime vremePocetka;
			LocalTime vremeKraja;
			try {
				vremePocetka = LocalTime.parse(vremePocetkaStr);
				vremeKraja = LocalTime.parse(vremeKrajaStr);
			} catch (DateTimeParseException ex) {
				JOptionPane.showMessageDialog(this, "Vreme mora biti uneto u formatu HH:mm", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Response res = Klijent.KreirajSmena(new Smena(0, vremePocetka, vremeKraja, ime));
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Smena je uspesno sacuvana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});

		tblSmena.addMouseListener(new mouseClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblSmena.getSelectedRows().length > 1) {
					selected = null;
					napuniFormu(selected);
					btnPromeni.setEnabled(false);
					btnObrisi.setEnabled(false);
				} else {
					SmenaTableModel model = (SmenaTableModel) tblSmena.getModel();
					selected = model.getSmena(tblSmena.getSelectedRow());
					napuniFormu(selected);
					btnPromeni.setEnabled(true);
					btnObrisi.setEnabled(true);
				}
				btnDeselektuj.setEnabled(true);
			}
		});
		
		btnDeselektuj.addActionListener((e) -> {
			deselect();
		});
		
		btnTrazi.addActionListener((e) -> {
			String ime = txtIme.getText().trim();
			String vremePocetka = txtVremePocetka.getText().trim();
			String vremeKraja = txtVremeKraja.getText().trim();
			
			if ("".equals(ime)			&&
				"".equals(vremePocetka)	&&
				"".equals(vremeKraja)) {
				JOptionPane.showMessageDialog(this, "Mora se uneti kriterijum pretrage.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Smena s = new Smena();
			
			if (!"".equals(ime)) {
				s.setIme(ime);
			}
			if (!"".equals(vremePocetka)) { 
				try {
					s.setVremePocetka(LocalTime.parse(vremePocetka));
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(this, "Vreme mora biti uneto u formatu HH:mm", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (!"".equals(vremeKraja)) {
				try {
					s.setVremeKraja(LocalTime.parse(vremeKraja));
				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(this, "Vreme mora biti uneto u formatu HH:mm", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			Response res = Klijent.vratiListuSmena(s);
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			tblSmena.setModel(new SmenaTableModel((List<Smena>) res.getObject()));
			
			btnResetuj.setEnabled(true);
		});
		
		btnResetuj.addActionListener((e) -> {
			deselect();
			updateTable();
			btnResetuj.setEnabled(false);
		});
		
		btnObrisi.addActionListener((e) -> {
			Response res = Klijent.ObrisiSmena(selected);
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise smenu" + res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(this, "Smena je uspesno obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
			btnObrisi.setEnabled(false);
			deselect();
			updateTable();
		});
		
		btnPromeni.addActionListener((e) -> {
			String ime = txtIme.getText().trim();
			String vremePocetkaStr = txtVremePocetka.getText().trim();
			String vremeKrajaStr = txtVremeKraja.getText().trim();
			
			if ("".equals(ime)			||
				"".equals(vremePocetkaStr)	||
				"".equals(vremeKrajaStr)) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			LocalTime vremePocetka;
			LocalTime vremeKraja;
			try {
				vremePocetka = LocalTime.parse(vremePocetkaStr);
				vremeKraja = LocalTime.parse(vremeKrajaStr);
			} catch (DateTimeParseException ex) {
				JOptionPane.showMessageDialog(this, "Vreme mora biti uneto u formatu HH:mm", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Smena s = new Smena(selected.getIdSmena(), vremePocetka, vremeKraja, ime);

			Response res = Klijent.PromeniSmena(s);
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Smena je uspesno sacuvana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	private void deselect() {
		selected = null;
		napuniFormu(selected);
		tblSmena.clearSelection();
		btnDeselektuj.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnPromeni.setEnabled(false);
	}
	
	@Override
	public void updateTable() {
		Response res = Klijent.vratiListuSmena(new Smena());
		if (res.getStatus() == Status.FAILURE) {
			JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblSmena.setModel(new SmenaTableModel((List<Smena>) res.getObject()));
	}
	
	private void napuniFormu(Smena s) {
		if (s == null) {
			txtIme.setText("");
			txtVremePocetka.setText("");
			txtVremeKraja.setText("");
			return;
		}
		txtIme.setText(s.getIme());
		txtVremePocetka.setText(s.getVremePocetka().toString());
		txtVremeKraja.setText(s.getVremeKraja().toString());
}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIme = new javax.swing.JLabel();
        lvlVremePocetka = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        txtVremePocetka = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnTrazi = new javax.swing.JButton();
        btnPromeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnDeselektuj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSmena = new javax.swing.JTable();
        btnResetuj = new javax.swing.JButton();
        lblVremeKraja = new javax.swing.JLabel();
        txtVremeKraja = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(717, 512));

        lblIme.setText("Ime:");
        lblIme.setMaximumSize(new java.awt.Dimension(100, 20));
        lblIme.setMinimumSize(new java.awt.Dimension(100, 20));
        lblIme.setPreferredSize(new java.awt.Dimension(100, 20));

        lvlVremePocetka.setText("Vreme pocetka:");
        lvlVremePocetka.setMaximumSize(new java.awt.Dimension(100, 20));
        lvlVremePocetka.setMinimumSize(new java.awt.Dimension(100, 20));
        lvlVremePocetka.setPreferredSize(new java.awt.Dimension(100, 20));

        txtIme.setMaximumSize(new java.awt.Dimension(64, 23));

        txtVremePocetka.setMaximumSize(new java.awt.Dimension(64, 23));

        btnDodaj.setText("Dodaj");

        btnTrazi.setText("Trazi");

        btnPromeni.setText("Promeni");

        btnObrisi.setText("Obrisi");

        btnDeselektuj.setText("Deselektuj");

        tblSmena.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSmena);

        btnResetuj.setText("Resetuj");

        lblVremeKraja.setText("Vreme kraja:");
        lblVremeKraja.setMaximumSize(new java.awt.Dimension(100, 20));
        lblVremeKraja.setMinimumSize(new java.awt.Dimension(100, 20));
        lblVremeKraja.setPreferredSize(new java.awt.Dimension(100, 20));

        txtVremeKraja.setMaximumSize(new java.awt.Dimension(64, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lvlVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIme, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIme, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtVremePocetka, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtVremeKraja, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIme, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lvlVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeselektuj;
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPromeni;
    private javax.swing.JButton btnResetuj;
    private javax.swing.JButton btnTrazi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblVremeKraja;
    private javax.swing.JLabel lvlVremePocetka;
    private javax.swing.JTable tblSmena;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtVremeKraja;
    private javax.swing.JTextField txtVremePocetka;
    // End of variables declaration//GEN-END:variables
}
