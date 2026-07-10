package gui.knjiga;

import domain.Knjiga;
import domain.enums.Format;
import domain.enums.Povez;
import java.awt.event.MouseEvent;
import java.util.List;
import lib.KlijentPanel;
import javax.swing.JOptionPane;
import lib.mouseClickListener;
import main.Klijent;
import transfer.Response;
import transfer.enums.Status;

public class pnlKnjiga extends KlijentPanel {
	
	Knjiga selected = null;

	public pnlKnjiga(String title) {
		super(title);
		initComponents();
		
		resetComboBoxes();
		
		btnDeselektuj.setEnabled(false);
		btnPromeni.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnResetuj.setEnabled(false);
		
		btnDodaj.addActionListener((e) -> {
			String naziv = txtNaziv.getText().trim();
			String autor = txtAutor.getText().trim();
			String br_str_str = txtBrStrana.getText().trim();
			String cena_str_str = txtCenaStrane.getText().trim();
			String cena_pov_str = txtCenaPoveza.getText().trim();
			
			if ("".equals(naziv)					||
				"".equals(autor)					||
				"".equals(br_str_str)				||
				"".equals(cena_str_str)				||
				"".equals(cena_pov_str)				||
				cmbFormat.getSelectedIndex() == -1	||
				cmbPovez.getSelectedIndex() == -1
				) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int br_str;
			int cena_str;
			int cena_pov;
			
			try {
				br_str = Integer.parseInt(br_str_str);
				cena_str = Integer.parseInt(cena_str_str);
				cena_pov = Integer.parseInt(cena_pov_str);
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(this, "Broj stranica i cene moraju biti brojevi", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Format format = (Format) cmbFormat.getSelectedItem();
			Povez povez = (Povez) cmbPovez.getSelectedItem();
			
			Response res = Klijent.KreirajKnjiga(new Knjiga(format, br_str, povez, cena_str, cena_pov, naziv, autor));
			
			if (res.getStatus() == Status.SUCCESS) {
				JOptionPane.showMessageDialog(this, "Knjiga je uspesno sacuvana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			}
		});

		tblKnjiga.addMouseListener(new mouseClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblKnjiga.getSelectedRows().length > 1) {
					selected = null;
					napuniFormu(selected);
					btnPromeni.setEnabled(false);
					btnObrisi.setEnabled(false);
				} else {
					KnjigaTableModel model = (KnjigaTableModel) tblKnjiga.getModel();
					selected = model.getKnjiga(tblKnjiga.getSelectedRow());
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
			String naziv = txtNaziv.getText().trim();
			String autor = txtAutor.getText().trim();
			String br_str_str = txtBrStrana.getText().trim();
			String cena_str_str = txtCenaStrane.getText().trim();
			String cena_pov_str = txtCenaPoveza.getText().trim();
			
			if ("".equals(naziv)					&&
				"".equals(autor)					&&
				"".equals(br_str_str)				&&
				"".equals(cena_str_str)				&&
				"".equals(cena_pov_str)				&&
				cmbFormat.getSelectedIndex() == -1	&&
				cmbPovez.getSelectedIndex() == -1
				) {
				JOptionPane.showMessageDialog(this, "Mora se uneti kriterijum pretrage.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Knjiga k = new Knjiga();
			
			if (!"".equals(naziv)) {
				k.setNaziv(naziv);
			}
			if (!"".equals(autor)) {
				k.setAutor(autor);
			}
			if (!"".equals(br_str_str)) {
				try {
					k.setBrStranica(Integer.parseInt(br_str_str));
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(this, "Broj strana mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (!"".equals(cena_str_str)) {
				try {
					k.setBrStranica(Integer.parseInt(cena_str_str));
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(this, "Broj strana mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (!"".equals(cena_pov_str)) {
				try {
					k.setBrStranica(Integer.parseInt(cena_pov_str));
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(this, "Broj strana mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (cmbFormat.getSelectedIndex() > -1) {
				k.setFormat((Format) cmbFormat.getSelectedItem());
			}
			if(cmbPovez.getSelectedIndex() > -1) {
				k.setPovez((Povez) cmbPovez.getSelectedItem());
			}
			
			Response res = Klijent.vratiListuKnjiga(k);
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			tblKnjiga.setModel(new KnjigaTableModel((List<Knjiga>) res.getObject()));
			
			btnResetuj.setEnabled(true);
		});
		
		btnResetuj.addActionListener((e) -> {
			deselect();
			updateTable();
			btnResetuj.setEnabled(false);
		});
		
		btnObrisi.addActionListener((e) -> {
			Response res = Klijent.obrisiKnjiga(selected);
			if (res.getStatus() == Status.FAILURE) {
				JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise knjigu" + res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(this, "Knjiga je uspesno obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
			btnObrisi.setEnabled(false);
			deselect();
			updateTable();
		});
		
	}
	
	private void deselect() {
		selected = null;
		napuniFormu(selected);
		tblKnjiga.clearSelection();
		btnDeselektuj.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnPromeni.setEnabled(false);
	}
	
	private void resetComboBoxes() {
		cmbFormat.removeAllItems();
		for (Format f : Format.values()) {
			cmbFormat.addItem(f);
		}
		cmbFormat.setSelectedIndex(-1);
			
		cmbPovez.removeAllItems();
		for (Povez p : Povez.values()) {
			cmbPovez.addItem(p);
		}
		cmbPovez.setSelectedIndex(-1);
	}
	
	@Override
	public void updateTable() {
		Response res = Klijent.vratiListuKnjiga(new Knjiga());
		if (res.getStatus() == Status.FAILURE) {
			JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblKnjiga.setModel(new KnjigaTableModel((List<Knjiga>) res.getObject()));
	}
	
	private void napuniFormu(Knjiga k) {
		if (k == null) {
			txtNaziv.setText("");
			txtAutor.setText("");
			cmbFormat.setSelectedIndex(-1);
			cmbPovez.setSelectedIndex(-1);
			txtBrStrana.setText("");
			txtCenaStrane.setText("");
			txtCenaPoveza.setText("");
			return;
		}
		txtNaziv.setText(k.getNaziv());
		txtAutor.setText(k.getAutor());
		cmbFormat.setSelectedIndex(k.getFormat().ordinal());
		cmbPovez.setSelectedIndex(k.getPovez().ordinal());
		txtBrStrana.setText("" + k.getBrStranica());
		txtCenaStrane.setText("" + k.getCenaStranica());
		txtCenaPoveza.setText("" + k.getCenaPoveza());
}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFormat = new javax.swing.JLabel();
        cmbPovez = new javax.swing.JComboBox<>();
        lblPovez = new javax.swing.JLabel();
        cmbFormat = new javax.swing.JComboBox<>();
        lblBrStrana = new javax.swing.JLabel();
        lblCenaStrane = new javax.swing.JLabel();
        lblCenaPoveza = new javax.swing.JLabel();
        txtBrStrana = new javax.swing.JTextField();
        txtCenaStrane = new javax.swing.JTextField();
        txtCenaPoveza = new javax.swing.JTextField();
        lblNaziv = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        txtNaziv = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnTrazi = new javax.swing.JButton();
        btnPromeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnDeselektuj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKnjiga = new javax.swing.JTable();
        btnResetuj = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(717, 512));

        lblFormat.setText("Format:");
        lblFormat.setMaximumSize(new java.awt.Dimension(100, 20));
        lblFormat.setMinimumSize(new java.awt.Dimension(100, 20));
        lblFormat.setPreferredSize(new java.awt.Dimension(100, 20));

        cmbPovez.setMaximumSize(new java.awt.Dimension(72, 23));

        lblPovez.setText("Povez:");
        lblPovez.setMaximumSize(new java.awt.Dimension(100, 20));
        lblPovez.setMinimumSize(new java.awt.Dimension(100, 20));
        lblPovez.setPreferredSize(new java.awt.Dimension(100, 20));

        cmbFormat.setMaximumSize(new java.awt.Dimension(72, 23));

        lblBrStrana.setText("Br. strana:");
        lblBrStrana.setMaximumSize(new java.awt.Dimension(100, 20));
        lblBrStrana.setMinimumSize(new java.awt.Dimension(100, 20));
        lblBrStrana.setPreferredSize(new java.awt.Dimension(100, 20));

        lblCenaStrane.setText("Cena strane:");
        lblCenaStrane.setMaximumSize(new java.awt.Dimension(100, 20));
        lblCenaStrane.setMinimumSize(new java.awt.Dimension(100, 20));
        lblCenaStrane.setPreferredSize(new java.awt.Dimension(100, 20));

        lblCenaPoveza.setText("Cena poveza:");
        lblCenaPoveza.setMaximumSize(new java.awt.Dimension(100, 20));
        lblCenaPoveza.setMinimumSize(new java.awt.Dimension(100, 20));
        lblCenaPoveza.setPreferredSize(new java.awt.Dimension(100, 20));

        lblNaziv.setText("Naziv:");
        lblNaziv.setMaximumSize(new java.awt.Dimension(100, 20));
        lblNaziv.setMinimumSize(new java.awt.Dimension(100, 20));
        lblNaziv.setPreferredSize(new java.awt.Dimension(100, 20));

        lblAutor.setText("Autor:");
        lblAutor.setMaximumSize(new java.awt.Dimension(100, 20));
        lblAutor.setMinimumSize(new java.awt.Dimension(100, 20));
        lblAutor.setPreferredSize(new java.awt.Dimension(100, 20));

        txtNaziv.setMaximumSize(new java.awt.Dimension(64, 23));

        txtAutor.setMaximumSize(new java.awt.Dimension(64, 23));

        btnDodaj.setText("Dodaj");

        btnTrazi.setText("Trazi");

        btnPromeni.setText("Promeni");

        btnObrisi.setText("Obrisi");

        btnDeselektuj.setText("Deselektuj");

        tblKnjiga.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKnjiga);

        btnResetuj.setText("Resetuj");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblCenaStrane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCenaStrane, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblBrStrana, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBrStrana, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblPovez, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cmbPovez, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCenaPoveza, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCenaPoveza, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
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
                    .addComponent(lblNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPovez, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPovez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBrStrana, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBrStrana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCenaStrane, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCenaStrane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCenaPoveza, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCenaPoveza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeselektuj;
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPromeni;
    private javax.swing.JButton btnResetuj;
    private javax.swing.JButton btnTrazi;
    private javax.swing.JComboBox<Format> cmbFormat;
    private javax.swing.JComboBox<Povez> cmbPovez;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblBrStrana;
    private javax.swing.JLabel lblCenaPoveza;
    private javax.swing.JLabel lblCenaStrane;
    private javax.swing.JLabel lblFormat;
    private javax.swing.JLabel lblNaziv;
    private javax.swing.JLabel lblPovez;
    private javax.swing.JTable tblKnjiga;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtBrStrana;
    private javax.swing.JTextField txtCenaPoveza;
    private javax.swing.JTextField txtCenaStrane;
    private javax.swing.JTextField txtNaziv;
    // End of variables declaration//GEN-END:variables
}
