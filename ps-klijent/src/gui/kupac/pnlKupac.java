package gui.kupac;

import domain.FizickoLice;
import domain.Kupac;
import domain.PravnoLice;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import lib.KlijentPanel;
import lib.mouseClickListener;
import main.Klijent;
import transfer.Response;
import transfer.enums.Status;

public class pnlKupac extends KlijentPanel {
	
	Kupac selected = null;

	public pnlKupac(String title) {
		super(title);
		initComponents();
		
		btnDeselektuj.setEnabled(false);
		btnPromeni.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnResetuj.setEnabled(false);
		
		radioFizickoLice.setSelected(true);
		
		radioFizickoLice.addActionListener((e) -> {
			radioPravnoLice.setSelected(false);
			deselect();
			updateTable();
			txtIme.setEditable(true);
			txtPrezime.setEditable(true);
			txtNaziv.setEditable(false);
			txtAdresa.setEditable(false);
		});
		
		radioPravnoLice.addActionListener((e) -> {
			radioFizickoLice.setSelected(false);
			deselect();
			updateTable();
			txtIme.setEditable(false);
			txtPrezime.setEditable(false);
			txtNaziv.setEditable(true);
			txtAdresa.setEditable(true);
		});
		
		btnDodaj.addActionListener((e) -> {
			String telefon = txtTelefon.getText().trim();
			String email = txtEmail.getText().trim();
			String ime = txtIme.getText().trim();
			String prezime = txtPrezime.getText().trim();
			String naziv = txtNaziv.getText().trim();
			String adresa = txtAdresa.getText().trim();
			
			Response res;
			
			if (radioFizickoLice.isSelected()) {
				if ("".equals(telefon)	||
					"".equals(email)	||
					"".equals(ime)		||
					"".equals(prezime))	{
					JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				res = Klijent.KreirajFizickoLice(new FizickoLice(ime, prezime, telefon, email));
			} else {
				if ("".equals(telefon)	||
					"".equals(email)	||
					"".equals(naziv)	||
					"".equals(adresa))	{
					JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				res = Klijent.KreirajPravnoLice(new PravnoLice(naziv, adresa, telefon, email));
			}
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Kupac je uspesno sacuvan", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});

		tblKupac.addMouseListener(new mouseClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblKupac.getSelectedRows().length > 1) {
					selected = null;
					napuniFormu(selected);
					btnPromeni.setEnabled(false);
					btnObrisi.setEnabled(false);
				} else {
					if (radioFizickoLice.isSelected()) {
						FizickoLiceTableModel model = (FizickoLiceTableModel) tblKupac.getModel();
						selected = model.getKupac(tblKupac.getSelectedRow());
					} else if (radioPravnoLice.isSelected()) {
						PravnoLiceTableModel model = (PravnoLiceTableModel) tblKupac.getModel();
						selected = model.getKupac(tblKupac.getSelectedRow());
					}
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
			String telefon = txtTelefon.getText().trim();
			String email = txtEmail.getText().trim();
			String ime = txtIme.getText().trim();
			String prezime = txtPrezime.getText().trim();
			String naziv = txtNaziv.getText().trim();
			String adresa = txtAdresa.getText().trim();
			
			Response res;
			
			if (radioFizickoLice.isSelected()) {
				if ("".equals(telefon)	&&
					"".equals(email)	&&
					"".equals(ime)		&&
					"".equals(prezime))	{
					JOptionPane.showMessageDialog(this, "Mora se uneti kriterijum pretrage.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				FizickoLice l = new FizickoLice();
				
				if (!"".equals(telefon)) {
					l.setTelefon(telefon);
				}
				if (!"".equals(email)) {
					l.setEmail(email);
				}
				if (!"".equals(ime)) {
					l.setIme(ime);
				}
				if (!"".equals(prezime)) {
					l.setPrezime(prezime);
				}
				
				res = Klijent.vratiListuFizickoLice(l);
				
				if (res.getStatus() == Status.FAILURE) {
					JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				tblKupac.setModel(new FizickoLiceTableModel((List<FizickoLice>) res.getObject()));
			} else {
				if ("".equals(telefon)	&&
					"".equals(email)	&&
					"".equals(naziv)	&&
					"".equals(adresa))	{
					JOptionPane.showMessageDialog(this, "Mora se uneti kriterijum pretrage.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				PravnoLice l = new PravnoLice();
				
				if (!"".equals(telefon)) {
					l.setTelefon(telefon);
				}
				if (!"".equals(email)) {
					l.setEmail(email);
				}
				if (!"".equals(naziv)) {
					l.setNaziv(naziv);
				}
				if (!"".equals(adresa)) {
					l.setAdresa(adresa);
				}
				
				res = Klijent.vratiListuPravnoLice(l);
				
				if (res.getStatus() == Status.FAILURE) {
					JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				tblKupac.setModel(new PravnoLiceTableModel((List<PravnoLice>) res.getObject()));
			}
			
			btnResetuj.setEnabled(true);
		});
		
		btnResetuj.addActionListener((e) -> {
			deselect();
			updateTable();
			btnResetuj.setEnabled(false);
		});
		
		btnObrisi.addActionListener((e) -> {
			Response res;
			if (radioFizickoLice.isSelected()) {
				res = Klijent.ObrisiFizickoLice((FizickoLice) selected);
			} else {
				res = Klijent.ObrisiPravnoLice((PravnoLice) selected);
			}
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise kupca" + res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(this, "kupac je uspesno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
			btnObrisi.setEnabled(false);
			deselect();
			updateTable();
		});
		
		btnPromeni.addActionListener((e) -> {
			String telefon = txtTelefon.getText().trim();
			String email = txtEmail.getText().trim();
			String ime = txtIme.getText().trim();
			String prezime = txtPrezime.getText().trim();
			String naziv = txtNaziv.getText().trim();
			String adresa = txtAdresa.getText().trim();
			
			Response res;
			
			if (radioFizickoLice.isSelected()) {
				if ("".equals(telefon)	||
					"".equals(email)	||
					"".equals(ime)		||
					"".equals(prezime))	{
					JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				res = Klijent.PromeniFizickoLice(new FizickoLice(ime, prezime, selected.getIdKupac(), telefon, email));
			} else {
				if ("".equals(telefon)	||
					"".equals(email)	||
					"".equals(naziv)	||
					"".equals(adresa))	{
					JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
				res = Klijent.PromeniPravnoLice(new PravnoLice(naziv, adresa, selected.getIdKupac(), telefon, email));
			}
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Kupac je uspesno sacuvan", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	private void deselect() {
		selected = null;
		napuniFormu(selected);
		tblKupac.clearSelection();
		btnDeselektuj.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnPromeni.setEnabled(false);
	}
	
	@Override
	public void updateTable() {
		if (radioFizickoLice.isSelected()) {
			Response res = Klijent.vratiListuFizickoLice(new FizickoLice());
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblKupac.setModel(new FizickoLiceTableModel((List<FizickoLice>) res.getObject()));	
		} else if (radioPravnoLice.isSelected()) {
			Response res = Klijent.vratiListuPravnoLice(new PravnoLice());
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblKupac.setModel(new PravnoLiceTableModel((List<PravnoLice>) res.getObject()));
		}
	}
	
	private void napuniFormu(Kupac k) {
		if (k == null) {
			txtTelefon.setText("");
			txtEmail.setText("");
			txtIme.setText("");
			txtPrezime.setText("");
			txtNaziv.setText("");
			txtAdresa.setText("");
			return;
		}
		txtTelefon.setText(k.getTelefon());
		txtEmail.setText(k.getEmail());
		if (k instanceof FizickoLice) {
			txtIme.setText(((FizickoLice) k).getIme());
			txtPrezime.setText(((FizickoLice) k).getPrezime());
			radioFizickoLice.setSelected(true);
			radioPravnoLice.setSelected(false);
		} else if (k instanceof PravnoLice) {
			txtNaziv.setText(((PravnoLice) k).getNaziv());
			txtAdresa.setText(((PravnoLice) k).getAdresa());
			radioFizickoLice.setSelected(false);
			radioPravnoLice.setSelected(true);
		}
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTelefon = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtTelefon = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnTrazi = new javax.swing.JButton();
        btnPromeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnDeselektuj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKupac = new javax.swing.JTable();
        btnResetuj = new javax.swing.JButton();
        radioFizickoLice = new javax.swing.JRadioButton();
        radioPravnoLice = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lblIme = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        lblPrezime = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblNaziv = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        lblAdresa = new javax.swing.JLabel();
        txtAdresa = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(717, 512));

        lblTelefon.setText("Telefon:");
        lblTelefon.setMaximumSize(new java.awt.Dimension(100, 20));
        lblTelefon.setMinimumSize(new java.awt.Dimension(100, 20));
        lblTelefon.setPreferredSize(new java.awt.Dimension(100, 20));

        lblEmail.setText("Email:");
        lblEmail.setMaximumSize(new java.awt.Dimension(100, 20));
        lblEmail.setMinimumSize(new java.awt.Dimension(100, 20));
        lblEmail.setPreferredSize(new java.awt.Dimension(100, 20));

        txtTelefon.setMaximumSize(new java.awt.Dimension(64, 23));

        txtEmail.setMaximumSize(new java.awt.Dimension(64, 23));

        btnDodaj.setText("Dodaj");

        btnTrazi.setText("Trazi");

        btnPromeni.setText("Promeni");

        btnObrisi.setText("Obrisi");

        btnDeselektuj.setText("Deselektuj");

        tblKupac.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKupac);

        btnResetuj.setText("Resetuj");

        radioFizickoLice.setText("Fizicko Lice");

        radioPravnoLice.setText("Pravno Lice");

        lblIme.setText("Ime:");
        lblIme.setMaximumSize(new java.awt.Dimension(100, 20));
        lblIme.setMinimumSize(new java.awt.Dimension(100, 20));
        lblIme.setPreferredSize(new java.awt.Dimension(100, 20));

        txtIme.setMaximumSize(new java.awt.Dimension(64, 23));

        lblPrezime.setText("Prezime:");
        lblPrezime.setMaximumSize(new java.awt.Dimension(100, 20));
        lblPrezime.setMinimumSize(new java.awt.Dimension(100, 20));
        lblPrezime.setPreferredSize(new java.awt.Dimension(100, 20));

        txtPrezime.setMaximumSize(new java.awt.Dimension(64, 23));

        lblNaziv.setText("Naziv:");
        lblNaziv.setMaximumSize(new java.awt.Dimension(100, 20));
        lblNaziv.setMinimumSize(new java.awt.Dimension(100, 20));
        lblNaziv.setPreferredSize(new java.awt.Dimension(100, 20));

        txtNaziv.setMaximumSize(new java.awt.Dimension(64, 23));

        lblAdresa.setText("Adresa:");
        lblAdresa.setMaximumSize(new java.awt.Dimension(100, 20));
        lblAdresa.setMinimumSize(new java.awt.Dimension(100, 20));
        lblAdresa.setPreferredSize(new java.awt.Dimension(100, 20));

        txtAdresa.setMaximumSize(new java.awt.Dimension(64, 23));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioFizickoLice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioPravnoLice))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAdresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtIme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblIme, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetuj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioFizickoLice)
                            .addComponent(radioPravnoLice)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIme, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblAdresa;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblNaziv;
    private javax.swing.JLabel lblPrezime;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JRadioButton radioFizickoLice;
    private javax.swing.JRadioButton radioPravnoLice;
    private javax.swing.JTable tblKupac;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtNaziv;
    private javax.swing.JTextField txtPrezime;
    private javax.swing.JTextField txtTelefon;
    // End of variables declaration//GEN-END:variables
}
