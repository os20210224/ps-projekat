package gui;

import java.awt.Color;
import main.Server;

public class FrmServer extends javax.swing.JFrame {
    
	Server srv;
	
    public FrmServer(Server srv) {
		this.srv = srv;
        initComponents();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        
        btnStop.setEnabled(false);
		lblStatus.setForeground(Color.RED);
        
        btnDbSubmit.addActionListener((e) -> {
            srv.setDbCredentials(
                txtDbAdress.getText(),
                txtDbPort.getText(),
                txtDbName.getText(),
                txtDbUser.getText(),
                String.valueOf(pswDbPassword.getPassword())
            );
        });
	
		btnStart.addActionListener((e) -> {
			cycleButtons();
			srv.start();
			lblStatus.setText("ONLINE");
			lblStatus.setForeground(new Color(24, 150, 50));
		});
		
		btnStop.addActionListener((e) -> {
			cycleButtons();
			srv.stop();
			lblStatus.setText("OFFLINE");
			lblStatus.setForeground(Color.RED);
		});
    }
	
	private void cycleButtons() {
		btnStart.setEnabled(!btnStart.isEnabled());
        btnStop.setEnabled(!btnStop.isEnabled());
	}
    
    public void logDB(String log) {
        logDatabase.setText(logDatabase.getText() + log + "\n");
    }
    
    public void log(String log) {
        logServer.setText(logServer.getText() + log + "\n");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlServer = new javax.swing.JPanel();
        Naslov = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logServer = new javax.swing.JTextArea();
        pnlDatabase = new javax.swing.JPanel();
        dbNaslov = new javax.swing.JLabel();
        adress = new javax.swing.JLabel();
        txtDbAdress = new javax.swing.JTextField();
        txtDbName = new javax.swing.JTextField();
        name = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        txtDbUser = new javax.swing.JTextField();
        password = new javax.swing.JLabel();
        btnDbSubmit = new javax.swing.JButton();
        pswDbPassword = new javax.swing.JPasswordField();
        lblDbLog = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logDatabase = new javax.swing.JTextArea();
        txtDbPort = new javax.swing.JTextField();
        port = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlServer.setPreferredSize(new java.awt.Dimension(354, 488));

        Naslov.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        Naslov.setText("Server");

        btnStart.setText("START");

        btnStop.setText("STOP");

        status.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        status.setText("Status:");

        lblStatus.setText("OFFLINE");

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        jLabel1.setText("Server log:");

        logServer.setColumns(20);
        logServer.setRows(5);
        jScrollPane1.setViewportView(logServer);

        javax.swing.GroupLayout pnlServerLayout = new javax.swing.GroupLayout(pnlServer);
        pnlServer.setLayout(pnlServerLayout);
        pnlServerLayout.setHorizontalGroup(
            pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlServerLayout.createSequentialGroup()
                        .addComponent(Naslov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1025, 1025, 1025))
                    .addGroup(pnlServerLayout.createSequentialGroup()
                        .addGroup(pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(status, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStart, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlServerLayout.createSequentialGroup()
                        .addGroup(pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlServerLayout.setVerticalGroup(
            pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlServerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Naslov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status)
                    .addComponent(lblStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        dbNaslov.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        dbNaslov.setText("Baza podataka");

        adress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adress.setText("adress:");

        txtDbAdress.setText("localhost");

        txtDbName.setText("ps-projekat");

        name.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        name.setText("name:");

        user.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        user.setText("user:");

        txtDbUser.setText("root");

        password.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        password.setText("password:");

        btnDbSubmit.setText("submit");

        lblDbLog.setFont(new java.awt.Font("Liberation Sans", 1, 13)); // NOI18N
        lblDbLog.setText("Database log:");

        logDatabase.setColumns(20);
        logDatabase.setRows(5);
        jScrollPane2.setViewportView(logDatabase);

        txtDbPort.setText("3306");

        port.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        port.setText("port:");

        javax.swing.GroupLayout pnlDatabaseLayout = new javax.swing.GroupLayout(pnlDatabase);
        pnlDatabase.setLayout(pnlDatabaseLayout);
        pnlDatabaseLayout.setHorizontalGroup(
            pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatabaseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatabaseLayout.createSequentialGroup()
                        .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlDatabaseLayout.createSequentialGroup()
                                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(user, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pswDbPassword)
                                    .addComponent(txtDbName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnDbSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlDatabaseLayout.createSequentialGroup()
                        .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                            .addGroup(pnlDatabaseLayout.createSequentialGroup()
                                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDatabaseLayout.createSequentialGroup()
                                        .addComponent(adress, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDbAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dbNaslov, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDbLog)
                                    .addGroup(pnlDatabaseLayout.createSequentialGroup()
                                        .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDbPort, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pnlDatabaseLayout.setVerticalGroup(
            pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatabaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dbNaslov)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress)
                    .addComponent(txtDbAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(port)
                    .addComponent(txtDbPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name)
                    .addComponent(txtDbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user)
                    .addComponent(txtDbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatabaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password)
                    .addComponent(pswDbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDbSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDbLog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlServer, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Naslov;
    private javax.swing.JLabel adress;
    private javax.swing.JButton btnDbSubmit;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JLabel dbNaslov;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDbLog;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea logDatabase;
    private javax.swing.JTextArea logServer;
    private javax.swing.JLabel name;
    private javax.swing.JLabel password;
    private javax.swing.JPanel pnlDatabase;
    private javax.swing.JPanel pnlServer;
    private javax.swing.JLabel port;
    private javax.swing.JPasswordField pswDbPassword;
    private javax.swing.JLabel status;
    private javax.swing.JTextField txtDbAdress;
    private javax.swing.JTextField txtDbName;
    private javax.swing.JTextField txtDbPort;
    private javax.swing.JTextField txtDbUser;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
