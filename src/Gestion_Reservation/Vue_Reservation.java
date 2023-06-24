
package Gestion_Reservation;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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

public class Vue_Reservation extends JPanel implements ActionListener, MouseListener ,FocusListener{
    
    //Declaration de la liste des champs reservation
    JTextField txNumReserve = null;                              //Numero Reservation
    JTextField txNomPass = null;                                 //Nom Passager
    JTextField txPrenomPass = null;                              //Prenom Passager
    JComboBox<String> bTrajetChoisie =  new JComboBox();                //Trajet Choisie
    DefaultComboBoxModel bTrajet = (DefaultComboBoxModel) bTrajetChoisie.getModel();
    JComboBox<String> bHeureChoisie = null;                      //Heure Choisie
    JTextField txDateVoy = null;                                 //Date Voyage
    
    //Declaration d'un JTable pour le tableau
    JTable table = null;
    
    //Declaration et initialisation des bouttons
    JButton btR = null;                                          //boutton Reinitialiser
    JButton btE = null;                                          //boutton Enregistrer
    JButton btA = null;                                          //boutton Afficher
    JButton btS = null;                                          //boutton Supprimer
    
    //Declaration de la classe Traitement_Reservation et Reservation
    Traitement_Reservation tmR = new Traitement_Reservation();
    Reservation re = new Reservation();
    
    //Methode Vue_Reservation
    public Vue_Reservation() {
         
        this.setLayout(new BorderLayout());
        GridBagLayout gb = new GridBagLayout();
        JPanel form = new JPanel(gb);
        GridBagConstraints gbc = new GridBagConstraints();

        //ajouter le formulaire dans form
        JLabel lNumReserve = new JLabel("Numero Reservation");
        txNumReserve = new JTextField(40);
        txNumReserve.setEditable(false);
        
        JLabel lNomPass = new JLabel("Nom passager");
        txNomPass = new JTextField(40);
        
        JLabel lPrenomPass = new JLabel("Prenom passager");
        txPrenomPass = new JTextField(40);
        
        JLabel lTrajetChoisie = new JLabel("Trajet Choisie");
        bTrajetChoisie = new JComboBox<>(bTrajet);
        
        
        JPanel comboTrajet= new JPanel();
        bTrajetChoisie.setPreferredSize(new Dimension(550,25)); 
        
        
        bTrajetChoisie.addFocusListener(this);
        
        TrajetChoisieReserve();
        comboTrajet.add(bTrajetChoisie);
        
        JLabel lHeureChoisie = new JLabel("Heure Choisie");
        String[] HeureChoisie={"6H : 00","7H : 00","8H : 00","9H : 00","10H : 00","11H : 00","12H : 00","01H : 00"};
        bHeureChoisie = new JComboBox<>(HeureChoisie);
        
        JLabel lDateVoy = new JLabel("Date Voyage");
        txDateVoy = new JTextField(40);
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);
        
        gbc.gridx = 0;   gbc.gridy = 0;
        form.add(lNumReserve, gbc);
        gbc.gridx = 1;   gbc.gridy = 0;
        form.add(txNumReserve, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 1;
        form.add(lNomPass, gbc);
        gbc.gridx = 1;   gbc.gridy = 1;
        form.add(txNomPass, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 2;
        form.add(lPrenomPass, gbc);
        gbc.gridx = 1;   gbc.gridy = 2;
        form.add(txPrenomPass, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 3;
        form.add(lTrajetChoisie, gbc);
        gbc.gridx = 1;   gbc.gridy = 3;
        form.add(comboTrajet, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 4;
        form.add(lHeureChoisie, gbc);
        gbc.gridx = 1;   gbc.gridy = 4;
        form.add(bHeureChoisie, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 5;
        form.add(lDateVoy, gbc);
        gbc.gridx = 1;   gbc.gridy = 5;
        form.add(txDateVoy, gbc);
        
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
                re.setNumReservation(txNumReserve.getText());
                re.setNomPass(txNomPass.getText());
                re.setPrenomPass(txPrenomPass.getText());
                re.setTrajetChoisie(String.valueOf(bTrajetChoisie.getSelectedItem()));
                re.setHeureVoy(String.valueOf(bHeureChoisie.getSelectedItem()));
                re.setDateVoy(txDateVoy.getText());
            
            if( txNumReserve.getText() == null || txNumReserve.getText().isEmpty())
                tmR.enregistrer(re);
            else{
                re.setNumReservation(txNumReserve.getText());
                tmR.modifier(re);
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
                Logger.getLogger(Vue_Reservation.class.getName()).log(Level.SEVERE, null, ex);
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
                tmR.supprimer(code);
                actualiser();
                reinitialiser();
            }
            else if(rep==1){
                actualiser();
            }
        }
    }
    
    

//Methode de reinitialiser les donnees
    void reinitialiser(){
        txNumReserve.setText(null);
        txNomPass.setText(null);
        txPrenomPass.setText(null);
        bTrajetChoisie.setSelectedIndex(0);
        bHeureChoisie.setSelectedIndex(0);
        txDateVoy.setText(null);
        
        //Desactiver les bouttons supprimer, reinitialiser et afficher le boutton Enregistrer
        btS.setEnabled(false);
        btR.setEnabled(false);
        btE.setText("Enregistrer");
        btE.setIcon(new ImageIcon("./img/save.png"));
        actualiser();
    }
    
    void actualiser(){
        Object[][] obj=new Object[tmR.afficher().size()][6];
        int i=0;
        for(Reservation r: tmR.afficher()){
            obj[i][0]=r.getNumReservation();
            obj[i][1]=r.getNomPass();
            obj[i][2]=r.getPrenomPass();
            obj[i][3]=r.getTrajetChoisie();
            obj[i][4]=r.getHeureVoy();
            obj[i][5]=r.getDateVoy();
            i++;
        }
        
        //entete du tableau Gestion Reservation
        String entete[]={"NUM. RESERVE","NOM PASS.","PRENOM PASS.","TRAJET CHOISIE","HEURE VOY.","DATE VOY."};      
        table.setModel(new DefaultTableModel(obj,entete));
    }
    
    
   public void TrajetChoisieReserve(){ 
        try {
            bTrajet.removeAllElements();
            for(String i: tmR.listeDesTrajets()){
                bTrajet.addElement(i);
            }
        } catch (IOException ex) {
            Logger.getLogger(Vue_Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
              
            txNumReserve.setText(""+table.getValueAt(nbreLigne, 0));
            txNomPass.setText(""+table.getValueAt(nbreLigne, 1));
            txPrenomPass.setText(""+table.getValueAt(nbreLigne, 2));
            bTrajet.setSelectedItem(""+String.valueOf(table.getValueAt(nbreLigne, 3) ) );
            bHeureChoisie.setSelectedItem(""+String.valueOf(table.getValueAt(nbreLigne, 4)));
            txDateVoy.setText(""+table.getValueAt(nbreLigne, 5));
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

    @Override
    public void focusGained(FocusEvent e) {
         TrajetChoisieReserve();
         System.out.println(bTrajet);
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    
}
