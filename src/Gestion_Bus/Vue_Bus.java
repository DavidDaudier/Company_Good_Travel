
package Gestion_Bus;


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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class Vue_Bus extends JPanel implements ActionListener, MouseListener{

    //Declaration de la liste des champs bus
    JTextField txCodeBus = null;                           //Code Bus
    JTextField txMarque = null;                            //Marque
    JTextField txModele = null;                            //Modele
    JTextField txAnnee = null;                             //Annee
    JComboBox<String> bnbreCylindre = null;                //Nombre de Cylindre
    JComboBox<String> btypeTransmission = null;            //Type de transmission
    JTextField txPlaqueImm = null;                         //Plaque d'immatriculation
    
    //Declaration d'un JTable pour le tableau
    JTable table = null;
    
    //Declaration et initialisation des bouttons
    JButton btR = null;                                    //boutton Reinitialiser
    JButton btE = null;                                    //boutton Enregistrer                                 
    JButton btA = null;                                    //boutton Afficher
    JButton btS = null;                                    //boutton Supprimer
    
    //Declaration de la classe Traitement_Bus et Bus
    Traitement_Bus tmB = new Traitement_Bus();
    Bus bu = new Bus();
    
    //Methode Vue_Bus
    public Vue_Bus() {
         
        this.setLayout(new BorderLayout());
        GridBagLayout gb = new GridBagLayout();
        JPanel form = new JPanel(gb);
        GridBagConstraints gbc = new GridBagConstraints();
        
        //ajouter le formulaire dans form
        JLabel lCode = new JLabel("Code Bus");
        txCodeBus = new JTextField(40);
        txCodeBus.setEditable(false); //rendre l'ID non éditable
        
        JLabel lMarque = new JLabel("Marque");
        txMarque = new JTextField(40);
        
        JLabel lModele = new JLabel("Modele");
        txModele = new JTextField(40);
        
        JLabel lAnnee = new JLabel("Annee");
        txAnnee = new JTextField(40);
        
        JLabel lNbreCylindret = new JLabel("Nombre de cylindres");
        String[] nbreCylindret={"4 Cylindres","6 Cylindres","8 Cylindres","10 Cylindres"};
        bnbreCylindre = new JComboBox<String>(nbreCylindret);
        
        JLabel lTypeTransmission = new JLabel("Type de transmission");
        String[] typeTransmission={"Automatique","Manuel"};
        btypeTransmission = new JComboBox<String>(typeTransmission);
        
        JLabel lPlaqueImm = new JLabel("Plaque d'immatricule");
        txPlaqueImm = new JTextField(40);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;   gbc.gridy = 0;
        form.add(lCode, gbc);
        gbc.gridx = 1;   gbc.gridy = 0;
        form.add(txCodeBus, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 1;
        form.add(lMarque, gbc);
        gbc.gridx = 1;   gbc.gridy = 1;
        form.add(txMarque, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 2;
        form.add(lModele, gbc);
        gbc.gridx = 1;   gbc.gridy = 2;
        form.add(txModele, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 3;
        form.add(lAnnee, gbc);
        gbc.gridx = 1;   gbc.gridy = 3;
        form.add(txAnnee, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 4;
        form.add(lNbreCylindret, gbc);
        gbc.gridx = 1;   gbc.gridy = 4;
        form.add(bnbreCylindre, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 5;
        form.add(lTypeTransmission, gbc);
        gbc.gridx = 1;   gbc.gridy = 5;
        form.add(btypeTransmission, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 6;
        form.add(lPlaqueImm, gbc);
        gbc.gridx = 1;   gbc.gridy = 6;
        form.add(txPlaqueImm, gbc);
        
        //Creation des bouttons
        btR = new JButton("Reinitialiser");
        btE = new JButton("Enregistrer");
        btA = new JButton("Afficher");
        btS = new JButton("Supprimer");

        //Ajouter les icones des bouttons
        btR.setIcon(new ImageIcon("./img/refresh.png"));
        btE.setIcon(new ImageIcon("./img/save.png"));
        btA.setIcon(new ImageIcon("./img/print.png"));
        btS.setIcon(new ImageIcon("./img/poubelle.png"));
        
        //Declaration d'un JPanel pour les bouttons
        JPanel panelbouton = new JPanel();
        
        //Ajouter les bouttons
        panelbouton.add(btR);
        btR.addActionListener(this);
        btR.setEnabled(false);
      
        panelbouton.add(btE);
        btE.addActionListener(this);  
        
        panelbouton.add(btA);
        btA.addActionListener(this); 
        
        panelbouton.add(btS);
        btS.addActionListener(this); 
        btS.setEnabled(false);
       
        //Reglage d'alignement des bouttons sur 2 colonnes et ajouter le formulaire
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 7;
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
                bu.setMarque(txMarque.getText());
                bu.setModele(txModele.getText());
                bu.setAnnee(txAnnee.getText());
                bu.setNbreCylindre(String.valueOf(bnbreCylindre.getSelectedItem()));
                bu.setTypeTransmission(String.valueOf(btypeTransmission.getSelectedItem()));
                bu.setPlaqueImm(txPlaqueImm.getText());
            
            if( txCodeBus.getText() == null || txCodeBus.getText().isEmpty())
                tmB.enregistrer(bu);
            
            else{
                bu.setCodeBus(txCodeBus.getText());
                tmB.modifier(bu);
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
                Logger.getLogger(Vue_Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //Evenement sur le boutton Supprimer
        else if(e.getSource() == btS){
            int nbreLigne = table.getSelectedRow();
            int rep;
            String code = ""+table.getValueAt(nbreLigne, 0);
            String question[]={"Oui","Non"};
            rep=JOptionPane.showOptionDialog(null,"Voulez-vous supprimer ce trajet?","Message de Confirmation",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,question,question[1]);
            if(rep==0){
                tmB.supprimer(code);
                actualiser();
            }
            else if(rep==1){
                actualiser();
            }
        }
    }
 
//Methode de reinitialiser les donnees
    void reinitialiser(){
        txCodeBus.setText(null);
        txMarque.setText(null);
        txModele.setText(null);
        txAnnee.setText(null);
        bnbreCylindre.setSelectedIndex(0);
        btypeTransmission.setSelectedIndex(0);
        txPlaqueImm.setText(null);
        
        //Desactiver les bouttons supprimer, reinitialiser et afficher le boutton Enregistrer
        btS.setEnabled(false);
        btR.setEnabled(false);
        btE.setText("Enregistrer");
        btE.setIcon(new ImageIcon("./img/save.png"));
    }
    
//Methode d'actualiser les donnees
    void actualiser(){
        Object[][] obj=new Object[tmB.afficher().size()][7];
        int i=0;
        for(Bus b: tmB.afficher()){
            obj[i][0]=b.getCodeBus();
            obj[i][1]=b.getMarque();
            obj[i][2]=b.getModele();
            obj[i][3]=b.getAnnee();
            obj[i][4]=b.getNbreCylindre();
            obj[i][5]=b.getTypeTransmission();
            obj[i][6]=b.getPlaqueImm();
            i++;
        }
        
        //entete du tableau Gestion Bus
        String entete[]={" CODE BUS "," MARQUE "," MODELE "," ANNEE "," NOMBRE CYLINDRE "," TYPE TRANSMISSIONS "," PLAQUE IMM "};      
        table.setModel(new DefaultTableModel(obj, entete));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Ajouter un Evenement en cliquant sur une ligne dans le tableau et l'apparition du boutton MODIFIER
        int nbreLigne=table.getSelectedRow();
        if(nbreLigne >= 0){
            btS.setEnabled(true);
            btR.setEnabled(true);
            btE.setText("Modifier");
            btE.setIcon(new ImageIcon("./img/edit.png"));
              
            txCodeBus.setText(""+table.getValueAt(nbreLigne, 0));
            txMarque.setText(""+table.getValueAt(nbreLigne, 1));
            txModele.setText(""+table.getValueAt(nbreLigne, 2));
            txAnnee.setText(""+table.getValueAt(nbreLigne, 3));
            bnbreCylindre.setSelectedItem(""+table.getValueAt(nbreLigne, 4));
            btypeTransmission.setSelectedItem(""+table.getValueAt(nbreLigne, 5));
            txPlaqueImm.setText(""+table.getValueAt(nbreLigne, 6));
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
