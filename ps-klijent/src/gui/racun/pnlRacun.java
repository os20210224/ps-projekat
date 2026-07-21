package gui.racun;

import domain.Racun;
import domain.StavkaRacuna;
import domain.enums.MetodPlacanja;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import lib.KlijentPanel;
import lib.mouseClickListener;
import main.Klijent;
import transfer.Response;
import transfer.enums.Status;

public class pnlRacun extends KlijentPanel {
	
	Racun selected_racun = null;
	StavkaRacuna selected_stavka = null;

	public pnlRacun(String title) {
		super(title);
		initComponents();
		
		resetComboBox();
		
		btnDeselektuj.setEnabled(false);
		btnPromeni.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnResetuj.setEnabled(false);
		
		btnDeselektujStavku.setEnabled(false);
		btnObrisiStavku.setEnabled(false);
		
		btnDodaj.addActionListener((e) -> {
//			String naziv = txtDatum.getText().trim();
//			String autor = txtAutor.getText().trim();
//			String br_str_str = txtBrStrana.getText().trim();
//			String cena_str_str = txtCenaStrane.getText().trim();
//			String cena_pov_str = txtCenaPoveza.getText().trim();
//			
//			if ("".equals(naziv)					||
//				"".equals(autor)					||
//				"".equals(br_str_str)				||
//				"".equals(cena_str_str)				||
//				"".equals(cena_pov_str)				||
//				cmbMetodPlacanja.getSelectedIndex() == -1	||
//				cmbPovez.getSelectedIndex() == -1
//				) {
//				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			
//			int br_str;
//			double cena_str;
//			double cena_pov;
//			
//			try {
//				br_str = Integer.parseInt(br_str_str);
//				cena_str = Double.parseDouble(cena_str_str);
//				cena_pov = Double.parseDouble(cena_pov_str);
//			} catch (NumberFormatException ne) {
//				JOptionPane.showMessageDialog(this, "Broj stranica i cene moraju biti brojevi", "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			
//			Format format = (Format) cmbMetodPlacanja.getSelectedItem();
//			Povez povez = (Povez) cmbPovez.getSelectedItem();
//			
//			Response res = Klijent.KreirajKnjiga(new Knjiga(format, br_str, povez, cena_str, cena_pov, naziv, autor));
//			
//			if (res.getStatus() == Status.SUCCESS) {
//				JOptionPane.showMessageDialog(this, "Knjiga je uspesno sacuvana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
//				updateTable();
//			} else {
//				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
//			}
		});

		tblRacun.addMouseListener(new mouseClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblRacun.getSelectedRows().length > 1) {
					selected_racun = null;
					napuniFormuRacun(selected_racun);
					btnPromeni.setEnabled(false);
					btnObrisi.setEnabled(false);
					deselectStavkaRacuna();
					updateTableStavka(true);
				} else {
					RacunTableModel model = (RacunTableModel) tblRacun.getModel();
					selected_racun = model.getRacun(tblRacun.getSelectedRow());
					napuniFormuRacun(selected_racun);
					btnPromeni.setEnabled(true);
					btnObrisi.setEnabled(true);
					updateTableStavka(false);
				}
				btnDeselektuj.setEnabled(true);
			}
		});
		
		tblStavkaRacuna.addMouseListener(new mouseClickListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tblStavkaRacuna.getSelectedRows().length > 1) {
					selected_stavka = null;
					napuniFormuStavkaRacuna(selected_stavka);
					btnObrisiStavku.setEnabled(false);
				} else {
					StavkaRacunaTableModel model = (StavkaRacunaTableModel) tblStavkaRacuna.getModel();
					selected_stavka = model.getStavka(tblStavkaRacuna.getSelectedRow());
					napuniFormuStavkaRacuna(selected_stavka);
					btnObrisiStavku.setEnabled(true);
				}
				btnDeselektujStavku.setEnabled(true);
			}
		});
		
		btnDeselektuj.addActionListener((e) -> {
			deselectRacun();
		});
		
		btnDeselektujStavku.addActionListener((e) -> {
			deselectStavkaRacuna();
		});
		
		btnTrazi.addActionListener((e) -> {
//			String naziv = txtDatum.getText().trim();
//			String autor = txtAutor.getText().trim();
//			String br_str_str = txtBrStrana.getText().trim();
//			String cena_str_str = txtCenaStrane.getText().trim();
//			String cena_pov_str = txtCenaPoveza.getText().trim();
//			
//			if ("".equals(naziv)					&&
//				"".equals(autor)					&&
//				"".equals(br_str_str)				&&
//				"".equals(cena_str_str)				&&
//				"".equals(cena_pov_str)				&&
//				cmbMetodPlacanja.getSelectedIndex() == -1	&&
//				cmbPovez.getSelectedIndex() == -1
//				) {
//				JOptionPane.showMessageDialog(this, "Mora se uneti kriterijum pretrage.", "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			
//			Knjiga k = new Knjiga();
//			
//			if (!"".equals(naziv)) {
//				k.setNaziv(naziv);
//			}
//			if (!"".equals(autor)) {
//				k.setAutor(autor);
//			}
//			if (!"".equals(br_str_str)) {
//				try {
//					k.setBrStranica(Integer.parseInt(br_str_str));
//				} catch (NumberFormatException ne) {
//					JOptionPane.showMessageDialog(this, "Broj strana mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//			}
//			if (!"".equals(cena_str_str)) {
//				try {
//					k.setCenaStranica(Double.parseDouble(cena_str_str));
//				} catch (NumberFormatException ne) {
//					JOptionPane.showMessageDialog(this, "Cena strana mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//			}
//			if (!"".equals(cena_pov_str)) {
//				try {
//					k.setCenaPoveza(Double.parseDouble(cena_pov_str));
//				} catch (NumberFormatException ne) {
//					JOptionPane.showMessageDialog(this, "Cena poveza mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
//					return;
//				}
//			}
//			if (cmbMetodPlacanja.getSelectedIndex() > -1) {
//				k.setFormat((Format) cmbMetodPlacanja.getSelectedItem());
//			}
//			if(cmbPovez.getSelectedIndex() > -1) {
//				k.setPovez((Povez) cmbPovez.getSelectedItem());
//			}
//			
//			Response res = Klijent.vratiListuKnjiga(k);
//			if (res.getStatus() == Status.FAILURE) {
//				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			
//			tblRacun.setModel(new KnjigaTableModel((List<Knjiga>) res.getObject()));
//			
//			btnResetuj.setEnabled(true);
		});
		
		btnResetuj.addActionListener((e) -> {
			deselectRacun();
			updateTable();
			btnResetuj.setEnabled(false);
		});
		
		btnObrisi.addActionListener((e) -> {
//			Response res = Klijent.obrisiKnjiga(selected_racun);
//			if (res.getStatus() == Status.FAILURE) {
//				JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise knjigu" + res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			JOptionPane.showMessageDialog(this, "Knjiga je uspesno obrisana.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
//			btnObrisi.setEnabled(false);
//			deselect();
//			updateTable();
		});
		
		btnPromeni.addActionListener((e) -> {
//			String naziv = txtDatum.getText().trim();
//			String autor = txtAutor.getText().trim();
//			String br_str_str = txtBrStrana.getText().trim();
//			String cena_str_str = txtCenaStrane.getText().trim();
//			String cena_pov_str = txtCenaPoveza.getText().trim();
//			
//			if ("".equals(naziv)					||
//				"".equals(autor)					||
//				"".equals(br_str_str)				||
//				"".equals(cena_str_str)				||
//				"".equals(cena_pov_str)				||
//				cmbMetodPlacanja.getSelectedIndex() == -1	||
//				cmbPovez.getSelectedIndex() == -1
//				) {
//				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
			
//			int br_str;
//			double cena_str;
//			double cena_pov;
//			
//			try {
//				br_str = Integer.parseInt(br_str_str);
//				cena_str = Double.parseDouble(cena_str_str);
//				cena_pov = Double.parseDouble(cena_pov_str);
//			} catch (NumberFormatException ne) {
//				JOptionPane.showMessageDialog(this, "Broj stranica i cene moraju biti brojevi", "Greska", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
			
//			Format format = (Format) cmbMetodPlacanja.getSelectedItem();
//			Povez povez = (Povez) cmbPovez.getSelectedItem();
//			
//			Response res = Klijent.PromeniKnjiga(new Knjiga(selected_racun.getIdKnjiga(), format, br_str, povez, cena_str, cena_pov, naziv, autor));
			
//			if (res.getStatus() == Status.SUCCESS) {
//				JOptionPane.showMessageDialog(this, "Knjiga je uspesno sacuvana", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
//				updateTable();
//			} else {
//				JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
//			}
		});
	}
	
	private void deselectRacun() {
		selected_racun = null;
		napuniFormuRacun(selected_racun);
		tblRacun.clearSelection();
		btnDeselektuj.setEnabled(false);
		btnObrisi.setEnabled(false);
		btnPromeni.setEnabled(false);
		updateTableStavka(true);
	}
	
	private void deselectStavkaRacuna() {
		selected_stavka = null;
		napuniFormuStavkaRacuna(selected_stavka);
		tblStavkaRacuna.clearSelection();
		btnDeselektujStavku.setEnabled(false);
		btnObrisiStavku.setEnabled(false);
		//btnPromeniStavku.setEnabled(false);
	}
	
	private void resetComboBox() {
		cmbMetodPlacanja.removeAllItems();
		for (MetodPlacanja mp : MetodPlacanja.values()) {
			cmbMetodPlacanja.addItem(mp);
		}
		cmbMetodPlacanja.setSelectedIndex(-1);
	}
	
	@Override
	public void updateTable() {
		Response res = Klijent.vratiListuRacun(new Racun());
		if (res.getStatus() == Status.FAILURE) {
			JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRacun.setModel(new RacunTableModel((List<Racun>) res.getObject()));
		updateTableStavka(true);
	}
	
	private void updateTableStavka(boolean empty) {
		if (empty) {
			tblStavkaRacuna.setModel(new StavkaRacunaTableModel(new ArrayList<>()));
			return;
		}
		Response res = Klijent.vratiListuStavkaRacuna(new StavkaRacuna(selected_racun.getIdRacun()));
		if (res.getStatus() == Status.FAILURE) {
			JOptionPane.showMessageDialog(this, res.getObject(), "Greska", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStavkaRacuna.setModel(new StavkaRacunaTableModel((List<StavkaRacuna>) res.getObject()));
	}
	
	private void napuniFormuRacun(Racun r) {
		if (r == null) {
			txtDatum.setText("");
			cmbMetodPlacanja.setSelectedIndex(-1);
			txtKupac.setText("");
			txtZaposleni.setText("");
			return;
		}
		txtDatum.setText(r.getDatum().toString());
		cmbMetodPlacanja.setSelectedIndex(r.getMetodPlacanja().ordinal());
		txtKupac.setText("" + r.getKupac().toString());
		txtZaposleni.setText("" + r.getZaposleni().toString());
	}
	
	private void napuniFormuStavkaRacuna(StavkaRacuna s) {
		
	}
	
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMetodPlacanja = new javax.swing.JLabel();
        cmbMetodPlacanja = new javax.swing.JComboBox<>();
        lblDatum = new javax.swing.JLabel();
        txtDatum = new javax.swing.JTextField();
        btnDodaj = new javax.swing.JButton();
        btnTrazi = new javax.swing.JButton();
        btnPromeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnDeselektuj = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRacun = new javax.swing.JTable();
        btnResetuj = new javax.swing.JButton();
        lblKupac = new javax.swing.JLabel();
        txtKupac = new javax.swing.JTextField();
        lblZaposleni = new javax.swing.JLabel();
        txtZaposleni = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStavkaRacuna = new javax.swing.JTable();
        btnObrisiStavku = new javax.swing.JButton();
        btnDeselektujStavku = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(717, 512));

        lblMetodPlacanja.setText("Metod placanja:");
        lblMetodPlacanja.setMaximumSize(new java.awt.Dimension(100, 20));
        lblMetodPlacanja.setMinimumSize(new java.awt.Dimension(100, 20));
        lblMetodPlacanja.setPreferredSize(new java.awt.Dimension(100, 20));

        cmbMetodPlacanja.setMaximumSize(new java.awt.Dimension(72, 23));

        lblDatum.setText("Datum:");
        lblDatum.setMaximumSize(new java.awt.Dimension(100, 20));
        lblDatum.setMinimumSize(new java.awt.Dimension(100, 20));
        lblDatum.setPreferredSize(new java.awt.Dimension(100, 20));

        txtDatum.setMaximumSize(new java.awt.Dimension(64, 23));

        btnDodaj.setText("Dodaj");

        btnTrazi.setText("Trazi");

        btnPromeni.setText("Promeni");

        btnObrisi.setText("Obrisi");

        btnDeselektuj.setText("Deselektuj");

        tblRacun.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblRacun);

        btnResetuj.setText("Resetuj");

        lblKupac.setText("Kupac:");
        lblKupac.setMaximumSize(new java.awt.Dimension(100, 20));
        lblKupac.setMinimumSize(new java.awt.Dimension(100, 20));
        lblKupac.setPreferredSize(new java.awt.Dimension(100, 20));

        txtKupac.setMaximumSize(new java.awt.Dimension(64, 23));

        lblZaposleni.setText("Zaposleni:");
        lblZaposleni.setMaximumSize(new java.awt.Dimension(100, 20));
        lblZaposleni.setMinimumSize(new java.awt.Dimension(100, 20));
        lblZaposleni.setPreferredSize(new java.awt.Dimension(100, 20));

        txtZaposleni.setMaximumSize(new java.awt.Dimension(64, 23));

        tblStavkaRacuna.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblStavkaRacuna);

        btnObrisiStavku.setText("Obrisi stavku");

        btnDeselektujStavku.setText("Deselektuj stavku");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblMetodPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbMetodPlacanja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnDeselektujStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisiStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnObrisiStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeselektujStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMetodPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMetodPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeselektuj;
    private javax.swing.JButton btnDeselektujStavku;
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiStavku;
    private javax.swing.JButton btnPromeni;
    private javax.swing.JButton btnResetuj;
    private javax.swing.JButton btnTrazi;
    private javax.swing.JComboBox<MetodPlacanja> cmbMetodPlacanja;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblKupac;
    private javax.swing.JLabel lblMetodPlacanja;
    private javax.swing.JLabel lblZaposleni;
    private javax.swing.JTable tblRacun;
    private javax.swing.JTable tblStavkaRacuna;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextField txtKupac;
    private javax.swing.JTextField txtZaposleni;
    // End of variables declaration//GEN-END:variables
}
