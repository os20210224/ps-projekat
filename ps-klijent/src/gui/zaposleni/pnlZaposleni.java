package gui.zaposleni;

import domain.Zaposleni;
import java.awt.event.MouseEvent;
import java.util.List;
import lib.KlijentPanel;
import javax.swing.JOptionPane;
import lib.mouseClickListener;
import main.Klijent;
import transfer.Response;
import transfer.enums.Status;

public class pnlZaposleni extends KlijentPanel {
	
	Zaposleni selected = null;

	public pnlZaposleni(String title) {
		super(title);
		initComponents();
		
		btnDeselektuj.setEnabled(false);
		btnPromeni.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnResetuj.setEnabled(false);
		
		btnDodaj.addActionListener((e) -> {
			String ime = txtIme.getText().trim();
			String prezime = txtPrezime.getText().trim();
			String username = txtUsername.getText().trim();
			String password = String.valueOf(pswPassword.getPassword()).trim();
			
			if ("".equals(ime)		||
				"".equals(prezime)	||
				"".equals(username)	||
				"".equals(password)) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Response res = Klijent.KreirajZaposleni(new Zaposleni(ime, prezime, username, password));
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Zaposleni je uspesno sacuvan", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});

		tblZaposleni.addMouseListener(new mouseClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblZaposleni.getSelectedRows().length > 1) {
					selected = null;
					napuniFormu(selected);
					btnPromeni.setEnabled(false);
					btnObrisi.setEnabled(false);
				} else {
					ZaposleniTableModel model = (ZaposleniTableModel) tblZaposleni.getModel();
					selected = model.getZaposleni(tblZaposleni.getSelectedRow());
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
			String prezime = txtPrezime.getText().trim();
			String username = txtUsername.getText().trim();
			
			if ("".equals(ime)		&&
				"".equals(prezime)	&&
				"".equals(username)) {
				JOptionPane.showMessageDialog(this, "Mora se uneti kriterijum pretrage.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Zaposleni z = new Zaposleni();
			
			if (!"".equals(ime)) {
				z.setIme(ime);
			}
			if (!"".equals(prezime)) {
				z.setPrezime(prezime);
			}
			if (!"".equals(username)) {
				z.setUsername(username);
			}
			
			Response res = Klijent.vratiListuZaposleni(z);
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			tblZaposleni.setModel(new ZaposleniTableModel((List<Zaposleni>) res.getObject()));
			
			btnResetuj.setEnabled(true);
		});
		
		btnResetuj.addActionListener((e) -> {
			deselect();
			updateTable();
			btnResetuj.setEnabled(false);
		});
		
		btnObrisi.addActionListener((e) -> {
			Response res = Klijent.obrisiZaposleni(selected);
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise zapsolenog" + res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(this, "Zaposleni je uspesno obrisan.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
			btnObrisi.setEnabled(false);
			deselect();
			updateTable();
		});
		
		btnPromeni.addActionListener((e) -> {
			String ime = txtIme.getText().trim();
			String prezime = txtPrezime.getText().trim();
			String username = txtUsername.getText().trim();
			String password = String.valueOf(pswPassword.getPassword()).trim();
			
			if ("".equals(ime)		||
				"".equals(prezime)	||
				"".equals(username)) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Zaposleni z = new Zaposleni(selected.getIdZaposleni(), ime, prezime, username, password);
			
			if ("".equals(password)) {
				z.setPassword(selected.getPassword());
			} else {
				z.setPassword(password);
			}
			
			Response res = Klijent.PromeniZaposleni(z);
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Zaposleni je uspesno sacuvan", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});
		
	}
	
	private void deselect() {
		selected = null;
		napuniFormu(selected);
		tblZaposleni.clearSelection();
		btnDeselektuj.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnPromeni.setEnabled(false);
	}
	
	@Override
	public void updateTable() {
		Response res = Klijent.vratiListuZaposleni(new Zaposleni());
		if (res.getStatus() == Status.FAILURE) {
			JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblZaposleni.setModel(new ZaposleniTableModel((List<Zaposleni>) res.getObject()));
	}
	
	private void napuniFormu(Zaposleni z) {
		if (z == null) {
			txtIme.setText("");
			txtPrezime.setText("");
			txtUsername.setText("");
			pswPassword.setText("");
			return;
		}
		txtIme.setText(z.getIme());
		txtPrezime.setText(z.getPrezime());
		txtUsername.setText(z.getUsername());
}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIme = new javax.swing.JLabel();
        lblPrezime = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        txtPrezime = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnTrazi = new javax.swing.JButton();
        btnPromeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnDeselektuj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblZaposleni = new javax.swing.JTable();
        btnResetuj = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        pswPassword = new javax.swing.JPasswordField();

        setMinimumSize(new java.awt.Dimension(717, 512));

        lblIme.setText("Ime:");
        lblIme.setMaximumSize(new java.awt.Dimension(100, 20));
        lblIme.setMinimumSize(new java.awt.Dimension(100, 20));
        lblIme.setPreferredSize(new java.awt.Dimension(100, 20));

        lblPrezime.setText("Prezime:");
        lblPrezime.setMaximumSize(new java.awt.Dimension(100, 20));
        lblPrezime.setMinimumSize(new java.awt.Dimension(100, 20));
        lblPrezime.setPreferredSize(new java.awt.Dimension(100, 20));

        txtIme.setMaximumSize(new java.awt.Dimension(64, 23));

        txtPrezime.setMaximumSize(new java.awt.Dimension(64, 23));

        btnDodaj.setText("Dodaj");

        btnTrazi.setText("Trazi");

        btnPromeni.setText("Promeni");

        btnObrisi.setText("Obrisi");

        btnDeselektuj.setText("Deselektuj");

        tblZaposleni.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblZaposleni);

        btnResetuj.setText("Resetuj");

        lblUsername.setText("Username:");
        lblUsername.setMaximumSize(new java.awt.Dimension(100, 20));
        lblUsername.setMinimumSize(new java.awt.Dimension(100, 20));
        lblUsername.setPreferredSize(new java.awt.Dimension(100, 20));

        txtUsername.setMaximumSize(new java.awt.Dimension(64, 23));

        lblPassword.setText("Password:");
        lblPassword.setMaximumSize(new java.awt.Dimension(100, 20));
        lblPassword.setMinimumSize(new java.awt.Dimension(100, 20));
        lblPassword.setPreferredSize(new java.awt.Dimension(100, 20));

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
                                    .addComponent(lblPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIme, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIme, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtPrezime, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                    .addComponent(pswPassword))))
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
                    .addComponent(lblPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPrezime;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField pswPassword;
    private javax.swing.JTable tblZaposleni;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPrezime;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
