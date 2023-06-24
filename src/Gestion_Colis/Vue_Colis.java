
package Gestion_Colis;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Vue_Colis extends JPanel implements ActionListener, MouseListener{
    
    //Declaration de la liste des champs trajet
    JTextField txCodeColis = null;                          //Code Colis
    JTextField txExpediteur = null;                         //Expediteur
    JTextField txDestinataire = null;                       //Destinateur
    JTextField txTelExpd = null;                            //Telephone Expediteur
    JTextField txTelDest = null;                            //Telephone Destinateur
    JTextField txMontPaye = null;                           //Montant Paye
    
    //Declaration d'un JTable pour le tableau
    JTable table = null;
    
    //Declaration et initialisation des bouttons
    JButton btR = null;                                     //boutton Reinitialiser
    JButton btE = null;                                     //boutton Enregistrer
    JButton btA = null;                                     //boutton Afficher
    
    //Declaration de la classe Traitement_Colis et Colis
    Traitement_Colis tmC = new Traitement_Colis();
    Colis col = new Colis();
    
    //Methode Vue_Colis
    public Vue_Colis() {
         
        this.setLayout(new BorderLayout());
        GridBagLayout gb = new GridBagLayout();
        JPanel form = new JPanel(gb);
        GridBagConstraints gbc = new GridBagConstraints();
 
        //ajouter le formulaire dans form
        JLabel lCode = new JLabel("Code Colis");
        txCodeColis = new JTextField(40);
        txCodeColis.setEditable(false);
        
        JLabel lExpediteur = new JLabel("Expediteur");
        txExpediteur = new JTextField(40);
        
        JLabel lDestinataire = new JLabel("Destinataire");
        txDestinataire = new JTextField(40);
        
        JLabel lTelExp = new JLabel("Telephone expediteur");
        txTelExpd = new JTextField(40);
        
        JLabel lTelDest = new JLabel("Telephone destinataire");
        txTelDest = new JTextField(40);
        
        JLabel lMontPaye = new JLabel("Montant Paye");
        txMontPaye = new JTextField(40);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;   gbc.gridy = 0;
        form.add(lCode, gbc);
        gbc.gridx = 1;   gbc.gridy = 0;
        form.add(txCodeColis, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 1;
        form.add(lExpediteur, gbc);
        gbc.gridx = 1;   gbc.gridy = 1;
        form.add(txExpediteur, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 2;
        form.add(lDestinataire, gbc);
        gbc.gridx = 1;   gbc.gridy = 2;
        form.add(txDestinataire, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 3;
        form.add(lTelExp, gbc);
        gbc.gridx = 1;   gbc.gridy = 3;
        form.add(txTelExpd, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 4;
        form.add(lTelDest, gbc);
        gbc.gridx = 1;   gbc.gridy = 4;
        form.add(txTelDest, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 5;
        form.add(lMontPaye, gbc);
        gbc.gridx = 1;   gbc.gridy = 5;
        form.add(txMontPaye, gbc);
        
        //Creation des bouttons
        btR = new JButton("Reinitialiser");
        btE = new JButton("Enregistrer");
        btA = new JButton("Afficher");
        
        //Ajouter les icones des bouttons
        btR.setIcon(new ImageIcon("./img/refresh.png"));
        btE.setIcon(new ImageIcon("./img/save.png"));
        btA.setIcon(new ImageIcon("./img/print.png"));

        //Declaration d'un JPanel pour les bouttons
        JPanel panelbouton = new JPanel();
        
        //Ajouter les bouttons et ses evenements
        panelbouton.add(btR);
        btR.addActionListener(this);
        btR.setEnabled(false);
      
        panelbouton.add(btE);
        btE.addActionListener(this);  
        
        panelbouton.add(btA);
        btA.addActionListener(this); 
        
        //Reglage d'alignement des bouttons sur 2 colonnes et ajouter le formulaire
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 6;
        form.add(panelbouton, gbc);

        //Declaration d'un JPanel pour le tableau
        JPanel panelTable = new JPanel();
        
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.addMouseListener(this);        
        actualiser(); 
        
        panelTable.add(new JScrollPane(table));
        
        //Ajouter les JPanel dans le Jframe
        this.add(form, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);

    }
    
//Methode des evenements sur les bouttons
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Evenement sur le boutton Reinitialiser
        if(e.getSource() == btR){
            reinitialiser();
        }
        
        //Evenement sur le boutton Enregistrer
        else 
            if(e.getSource() == btE){
                col.setCodeColis(txCodeColis.getText());
                col.setExpediteur(txExpediteur.getText());
                col.setDestinataire(txDestinataire.getText());
                col.setTelExpd(txTelExpd.getText());
                col.setTelDest(txTelDest.getText());
                col.setMntPaye(Double.parseDouble(txMontPaye.getText()));
            
            if( txCodeColis.getText() == null || txCodeColis.getText().isEmpty())
                tmC.enregistrer(col);
            
            else{
                col.setCodeColis(txCodeColis.getText());
                tmC.modifier(col);
            }
            actualiser(); 
            reinitialiser();
        }
            
        //Evenement sur le boutton Afficher
        else if(e.getSource() == btA){
            int nbreLigne = table.getSelectedRow();
            try {
                table.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Vue_Colis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//Methode de reinitialiser les donnees
    void reinitialiser(){
        txCodeColis.setText(null);
        txExpediteur.setText(null);
        txDestinataire.setText(null);
        txTelExpd.setText(null);
        txTelDest.setText(null);
        txMontPaye.setText(null);
        
        //Desactiver le boutton reinitialiser et afficher le boutton Enregistrer
        btR.setEnabled(false);
        btE.setText("Enregistrer");
        btE.setIcon(new ImageIcon("./img/save.png"));
    }
    
//Methode d'actualiser les donnees
    void actualiser(){
        Object[][] obj=new Object[tmC.afficher().size()][6];
        int i=0;
        for(Colis col: tmC.afficher()){
            obj[i][0]=col.getCodeColis();
            obj[i][1]=col.getExpediteur();
            obj[i][2]=col.getDestinataire();
            obj[i][3]=col.getTelExpd();
            obj[i][4]=col.getTelDest();
            obj[i][5]=col.getMntPaye();
            i++;
        }
        
        //entete du tableau Gestion Colis
        String entete[]={"Code COLIS","EXPEDITEUR","DESTINATEUR","TEL EXP.","TEL DEST.","MONTANT"};      
        table.setModel(new DefaultTableModel(obj, entete));
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Ajouter un Evenement en cliquant sur une ligne dans le tableau et l'apparition du boutton MODIFIER
        int nbreLigne=table.getSelectedRow();
        if(nbreLigne >= 0){
            btR.setEnabled(true);
            btE.setText("Modifier");
            btE.setIcon(new ImageIcon("./img/edit.png"));
              
            txCodeColis.setText(""+table.getValueAt(nbreLigne, 0));
            txExpediteur.setText(""+table.getValueAt(nbreLigne, 1));
            txDestinataire.setText(""+table.getValueAt(nbreLigne, 2));
            txTelExpd.setText(""+table.getValueAt(nbreLigne, 3));
            txTelDest.setText(""+table.getValueAt(nbreLigne, 4));
            txMontPaye.setText(""+table.getValueAt(nbreLigne, 5));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
