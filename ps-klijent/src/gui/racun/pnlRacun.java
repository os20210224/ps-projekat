package gui.racun;

import domain.Knjiga;
import domain.Kupac;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Zaposleni;
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
		btnDodajStavku.setEnabled(false);
		btnPromeniStavku.setEnabled(false);
		
		enableFormStavka(false);
		
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
					btnDodajStavku.setEnabled(false);
					deselectStavkaRacuna();
					updateTableStavka(true);
					enableFormStavka(false);
				} else {
					RacunTableModel model = (RacunTableModel) tblRacun.getModel();
					selected_racun = model.getRacun(tblRacun.getSelectedRow());
					napuniFormuRacun(selected_racun);
					btnPromeni.setEnabled(true);
					btnObrisi.setEnabled(true);
					btnDodajStavku.setEnabled(true);
					updateTableStavka(false);
					enableFormStavka(true);
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
					btnPromeniStavku.setEnabled(false);
				} else {
					StavkaRacunaTableModel model = (StavkaRacunaTableModel) tblStavkaRacuna.getModel();
					selected_stavka = model.getStavka(tblStavkaRacuna.getSelectedRow());
					napuniFormuStavkaRacuna(selected_stavka);
					btnObrisiStavku.setEnabled(true);
					btnPromeniStavku.setEnabled(true);
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
		
		btnDodajStavku.addActionListener((e) -> {
			String kolicina_str = txtKolicina.getText().trim();
			
			if ("".equals(kolicina_str)					||
				cmbKnjiga.getSelectedIndex() == -1
				) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int kolicina;
			try {
				kolicina = Integer.parseInt(kolicina_str);
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(this, "Kolicina mora biti broj", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Knjiga knjiga = (Knjiga) cmbKnjiga.getSelectedItem();
			
			StavkaRacuna stavka = new StavkaRacuna(selected_racun.getIdRacun(), 0l, knjiga.getCena(), kolicina, knjiga);
			
			selected_racun.addStavka(stavka);
			updateTableStavka(selected_racun.getStavkeRacuna());
			deselectStavkaRacuna();
		});
		
		btnObrisiStavku.addActionListener((e) -> {
			selected_racun.removeStavka(selected_stavka);
			updateTableStavka(selected_racun.getStavkeRacuna());
			deselectStavkaRacuna();
		});
		
		btnPromeniStavku.addActionListener((e) -> {
			String kolicina_str = txtKolicina.getText().trim();
			
			if ("".equals(kolicina_str)					||
				cmbKnjiga.getSelectedIndex() == -1
				) {
				JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int kolicina;
			try {
				kolicina = Integer.parseInt(kolicina_str);
			} catch (NumberFormatException ne) {
				JOptionPane.showMessageDialog(this, "Kolicina mora biti broj", "Greska", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Knjiga knjiga = (Knjiga) cmbKnjiga.getSelectedItem();
			
			StavkaRacuna stavka = new StavkaRacuna(
				selected_stavka.getIdRacun(),
				selected_stavka.getRb(),
				knjiga.getCena(),
				kolicina,
				knjiga
			);
			
			if (knjiga.getIdKnjiga() != selected_stavka.getKnjiga().getIdKnjiga()) {
				selected_racun.removeStavka(selected_stavka);
				selected_racun.addStavka(stavka);
			} else {
				selected_racun.updateStavka(stavka);
			}
			
			updateTableStavka(selected_racun.getStavkeRacuna());
			deselectStavkaRacuna();
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
		enableFormStavka(false);
		btnDodajStavku.setEnabled(false);
		deselectStavkaRacuna();
	}
	
	private void deselectStavkaRacuna() {
		selected_stavka = null;
		napuniFormuStavkaRacuna(selected_stavka);
		tblStavkaRacuna.clearSelection();
		btnDeselektujStavku.setEnabled(false);
		btnObrisiStavku.setEnabled(false);
		btnPromeniStavku.setEnabled(false);
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
		updateComboKupac();
		updateComboKnjiga();
		updateComboZaposleni();
	}
	
	private void updateTableStavka(boolean empty) {
		if (empty) {
			tblStavkaRacuna.setModel(new StavkaRacunaTableModel(new ArrayList<>()));
		} else {
			tblStavkaRacuna.setModel(new StavkaRacunaTableModel(selected_racun.getStavkeRacuna()));
		}
	}
	
	private void updateTableStavka(List<StavkaRacuna> stavke) {
		tblStavkaRacuna.setModel(new StavkaRacunaTableModel(stavke));
	}
	
	private void napuniFormuRacun(Racun r) {
		if (r == null) {
			txtDatum.setText("");
			cmbMetodPlacanja.setSelectedIndex(-1);
			cmbKupac.setSelectedIndex(-1);
			cmbZaposleni.setSelectedIndex(-1);
			return;
		}
		txtDatum.setText(r.getDatum().toString());
		cmbMetodPlacanja.setSelectedIndex(r.getMetodPlacanja().ordinal());
		cmbKupac.setSelectedItem(selected_racun.getKupac());
		for (int i = 0; i < cmbKupac.getItemCount(); i++) {
			if (selected_racun.getKupac().toString().equals(cmbKupac.getItemAt(i).toString())) {
				cmbKupac.setSelectedIndex(i);
				break;
			}
		}
		for (int i = 0; i < cmbZaposleni.getItemCount(); i++) {
			if (selected_racun.getZaposleni().toString().equals(cmbZaposleni.getItemAt(i).toString())) {
				cmbZaposleni.setSelectedIndex(i);
				break;
			}
		}
	}
	
	private void napuniFormuStavkaRacuna(StavkaRacuna s) {
		if (s == null) {
			cmbKnjiga.setSelectedIndex(-1);
			txtKolicina.setText("");
			return;
		}
		for (int i = 0; i < cmbKnjiga.getItemCount(); i++) {
			if (selected_stavka.getKnjiga().toString().equals(cmbKnjiga.getItemAt(i).toString())) {
				cmbKnjiga.setSelectedIndex(i);
				break;
			}
		}
		txtKolicina.setText("" + selected_stavka.getKolicina());
	}
	
	private void updateComboKupac() {
		Response res = Klijent.vratiListuKupac(new Kupac());
		if (res.getStatus() == Status.FAILURE) {
			// handle
			return;
		}
		List<Kupac> kupci = (List<Kupac>) res.getObject();
		cmbKnjiga.removeAllItems();
		for (Kupac k : kupci) {
			cmbKupac.addItem(k);
		}
		cmbKupac.setSelectedIndex(-1);
	}
	
	private void updateComboKnjiga() {
		Response res = Klijent.vratiListuKnjiga(new Knjiga());
		if (res.getStatus() == Status.FAILURE) {
			// handle
			return;
		}
		List<Knjiga> knjige = (List<Knjiga>) res.getObject();
		cmbKnjiga.removeAllItems();
		for (Knjiga k : knjige) {
			cmbKnjiga.addItem(k);
		}
		cmbKnjiga.setSelectedIndex(-1);
	}
	
	private void updateComboZaposleni() {
		Response res = Klijent.vratiListuZaposleni(new Zaposleni());
		if (res.getStatus() == Status.FAILURE) {
			// handle
			return;
		}
		List<Zaposleni> zaposleni = (List<Zaposleni>) res.getObject();
		cmbZaposleni.removeAllItems();
		for (Zaposleni z : zaposleni) {
			cmbZaposleni.addItem(z);
		}
		cmbZaposleni.setSelectedIndex(-1);
	}
	
	private void enableFormStavka(boolean enable) {
		cmbKnjiga.setEnabled(enable);
		txtKolicina.setEnabled(enable);
		txtKnjiga.setEnabled(enable);
		btnTraziKnjigu.setEnabled(enable);
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
        lblZaposleni = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStavkaRacuna = new javax.swing.JTable();
        btnObrisiStavku = new javax.swing.JButton();
        btnDeselektujStavku = new javax.swing.JButton();
        lblKnjiga = new javax.swing.JLabel();
        cmbKnjiga = new javax.swing.JComboBox<>();
        btnTraziKnjigu = new javax.swing.JButton();
        txtKnjiga = new javax.swing.JTextField();
        btnDodajStavku = new javax.swing.JButton();
        lblKolicina = new javax.swing.JLabel();
        txtKolicina = new javax.swing.JTextField();
        txtKupac = new javax.swing.JTextField();
        txtZaposleni = new javax.swing.JTextField();
        btnTraziKupca = new javax.swing.JButton();
        btnTraziZaposlenog = new javax.swing.JButton();
        cmbKupac = new javax.swing.JComboBox<>();
        cmbZaposleni = new javax.swing.JComboBox<>();
        btnPromeniStavku = new javax.swing.JButton();

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

        lblZaposleni.setText("Zaposleni:");
        lblZaposleni.setMaximumSize(new java.awt.Dimension(100, 20));
        lblZaposleni.setMinimumSize(new java.awt.Dimension(100, 20));
        lblZaposleni.setPreferredSize(new java.awt.Dimension(100, 20));

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

        btnObrisiStavku.setText("Obrisi Stavku");

        btnDeselektujStavku.setText("Deselektuj Stavku");

        lblKnjiga.setText("Knjiga:");
        lblKnjiga.setMaximumSize(new java.awt.Dimension(100, 20));
        lblKnjiga.setMinimumSize(new java.awt.Dimension(100, 20));
        lblKnjiga.setPreferredSize(new java.awt.Dimension(100, 20));

        cmbKnjiga.setMaximumSize(new java.awt.Dimension(72, 23));

        btnTraziKnjigu.setText("Trazi Knjigu");

        btnDodajStavku.setText("Dodaj Stavku");

        lblKolicina.setText("Kolicina:");

        txtKupac.setMaximumSize(new java.awt.Dimension(64, 23));

        txtZaposleni.setMaximumSize(new java.awt.Dimension(64, 23));

        btnTraziKupca.setText("Trazi Kupca");

        btnTraziZaposlenog.setText("Trazi Zaposlenog");

        cmbKupac.setMaximumSize(new java.awt.Dimension(72, 23));

        cmbZaposleni.setMaximumSize(new java.awt.Dimension(72, 23));

        btnPromeniStavku.setText("Promeni Stavku");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbKupac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbZaposleni, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMetodPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMetodPlacanja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTraziKupca, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnTraziZaposlenog, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtKnjiga, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTraziKnjigu, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblKnjiga, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblKolicina))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbKnjiga, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnObrisiStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnDodajStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnDeselektujStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnPromeniStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMetodPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMetodPlacanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKnjiga, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKnjiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKolicina))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTraziKupca, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTraziZaposlenog, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtZaposleni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTraziKnjigu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKnjiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnObrisiStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDeselektujStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnDodajStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPromeniStavku, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPromeni, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnTrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDeselektuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnResetuj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeselektuj;
    private javax.swing.JButton btnDeselektujStavku;
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnDodajStavku;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiStavku;
    private javax.swing.JButton btnPromeni;
    private javax.swing.JButton btnPromeniStavku;
    private javax.swing.JButton btnResetuj;
    private javax.swing.JButton btnTrazi;
    private javax.swing.JButton btnTraziKnjigu;
    private javax.swing.JButton btnTraziKupca;
    private javax.swing.JButton btnTraziZaposlenog;
    private javax.swing.JComboBox<Knjiga> cmbKnjiga;
    private javax.swing.JComboBox<Kupac> cmbKupac;
    private javax.swing.JComboBox<MetodPlacanja> cmbMetodPlacanja;
    private javax.swing.JComboBox<Zaposleni> cmbZaposleni;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblKnjiga;
    private javax.swing.JLabel lblKolicina;
    private javax.swing.JLabel lblKupac;
    private javax.swing.JLabel lblMetodPlacanja;
    private javax.swing.JLabel lblZaposleni;
    private javax.swing.JTable tblRacun;
    private javax.swing.JTable tblStavkaRacuna;
    private javax.swing.JTextField txtDatum;
    private javax.swing.JTextField txtKnjiga;
    private javax.swing.JTextField txtKolicina;
    private javax.swing.JTextField txtKupac;
    private javax.swing.JTextField txtZaposleni;
    // End of variables declaration//GEN-END:variables
}
