
package Gestion_Trajet;


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

public class Vue_Trajet extends JPanel implements ActionListener, MouseListener{

    //Declaration de la liste des champs trajet
    JTextField txCodeTrajet = null;                    //Code Trajet
    JTextField txpointDepart = null;                   //Point de depart
    JTextField txpointArrivee = null;                  //Point d'arriver
    JComboBox<String> bheureDepart = null;             //Heure depart
    JTextField txPrix = null;                          //PrixTrajet
    
    //Declaration d'un JTable pour le tableau
    JTable table = null;  
    
    //Declaration et initialisation des bouttons
    JButton btR = null;                               //boutton Reinitialiser
    JButton btC = null;                               //boutton Creer trajet
    JButton btA = null;                               //boutton Afficher
    JButton btS = null;                               //boutton Supprimer
    
    //Declaration de la classe Traitement_Trajet et Trajet
    Traitement_Trajet tmT = new Traitement_Trajet();
    Trajet tr = new Trajet();
    
    //Methode Vue_Trajet
    public Vue_Trajet() {
         
        this.setLayout(new BorderLayout());
        GridBagLayout gb = new GridBagLayout();
        JPanel form = new JPanel(gb);
        GridBagConstraints gbc = new GridBagConstraints();
        
        //ajouter le formulaire dans form
        JLabel lCode = new JLabel("Code Trajet");
        txCodeTrajet = new JTextField(40);
        txCodeTrajet.setEditable(false);
        
        JLabel lPointDepart = new JLabel("Point de Depart");
        txpointDepart = new JTextField(40);
        
        JLabel lPointArrivee = new JLabel("Point D'arrivee");
        txpointArrivee = new JTextField(40);
        
        JLabel lheureDepart = new JLabel("Heure Depart");
        String[] heureDepart={"6H : 00","7H : 00","8H : 00","9H : 00","10H : 00","11H : 00","12H : 00","01H : 00"};
        bheureDepart = new JComboBox<>(heureDepart);
        
        JLabel lPrix = new JLabel("Prix Trajet");
        txPrix = new JTextField(40);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(6, 6, 6, 6);
        
        gbc.gridx = 0;   gbc.gridy = 0;
        form.add(lCode, gbc);
        gbc.gridx = 1;   gbc.gridy = 0;
        form.add(txCodeTrajet, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 1;
        form.add(lPointDepart, gbc);
        gbc.gridx = 1;   gbc.gridy = 1;
        form.add(txpointDepart, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 2;
        form.add(lPointArrivee, gbc);
        gbc.gridx = 1;   gbc.gridy = 2;
        form.add(txpointArrivee, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 3;
        form.add(lheureDepart, gbc);
        gbc.gridx = 1;   gbc.gridy = 3;
        form.add(bheureDepart, gbc);
        
        gbc.gridx = 0;   gbc.gridy = 4;
        form.add(lPrix, gbc);
        gbc.gridx = 1;   gbc.gridy = 4;
        form.add(txPrix, gbc);
        
        //Creation des bouttons
        btR = new JButton("Reinitialiser");
        btC = new JButton("Creer Trajet");
        btA = new JButton("Afficher");
        btS = new JButton("Supprimer");
        
        //Ajouter les icones des bouttons
        btR.setIcon(new ImageIcon("./img/refresh.png"));
        btC.setIcon(new ImageIcon("./img/add.png"));
        btA.setIcon(new ImageIcon("./img/print.png"));
        btS.setIcon(new ImageIcon("./img/poubelle.png"));

        //Declaration d'un JPanel pour les bouttons
        JPanel panelbouton = new JPanel();
        
        //Ajouter les bouttons et ses evenements
        panelbouton.add(btR);
        btR.addActionListener(this);
        btR.setEnabled(false);
      
        panelbouton.add(btC);
        btC.addActionListener(this); 
        
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
        
        //Evenement sur le boutton Creer Trajet
        else 
            if(e.getSource() == btC){
                tr.setPointDepart(txpointDepart.getText());
                tr.setPointArrivee(txpointArrivee.getText());
                tr.setHeureDepart(String.valueOf(bheureDepart.getSelectedItem()));
                tr.setPrixTrajet(Double.parseDouble(txPrix.getText()));
            
            if( txCodeTrajet.getText() == null || txCodeTrajet.getText().isEmpty())
                tmT.creerTrajet(tr);
            
            else{
                tr.setCodeTrajet(txCodeTrajet.getText());
                tmT.modifier(tr);
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
                Logger.getLogger(Vue_Trajet.class.getName()).log(Level.SEVERE, null, ex);
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
                tmT.supprimer(code);
                actualiser();
            }
            else if(rep==1){
                actualiser();
            }
        }
    }
    
//Methode de reinitialiser les donnees
    void reinitialiser(){
        txCodeTrajet.setText(null);
        txpointDepart.setText(null);
        txpointArrivee.setText(null);
        bheureDepart.setSelectedIndex(0);
        txPrix.setText(null);
        
        //Desactiver les bouttons supprimer, reinitialiser et afficher le boutton Creer Trajet
        btS.setEnabled(false);
        btR.setEnabled(false);
        btC.setText("Creer Trajet");
        btC.setIcon(new ImageIcon("./img/add.png"));
    }
    
//Methode d'actualiser les donnees
    void actualiser(){
        Object[][] obj=new Object[tmT.afficher().size()][5];
        int i=0;
        for(Trajet t: tmT.afficher()){
            obj[i][0]=t.getCodeTrajet();
            obj[i][1]=t.getPointDepart();
            obj[i][2]=t.getPointArrivee();
            obj[i][3]=t.getHeureDepart();
            obj[i][4]=t.getPrixTrajet();
            i++;
        }
        
        //entete du tableau Gestion Trajet
        String entete[]={" CODE TRAJET "," POINT DEPART "," POINT D'ARRIVEE "," HEURE DEPART "," PRIX TRAJET "};
        table.setModel(new DefaultTableModel(obj, entete));
    }

    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        //Ajouter un Evenement en cliquant sur une ligne dans le tableau et l'apparition du boutton MODIFIER
        int nbreLigne=table.getSelectedRow();
        if(nbreLigne >= 0){
            btS.setEnabled(true);
            btR.setEnabled(true);
            btC.setText("Modifier");
            btC.setIcon(new ImageIcon("./img/edit.png"));
            
            txCodeTrajet.setText(""+table.getValueAt(nbreLigne, 0));
            txpointDepart.setText(""+table.getValueAt(nbreLigne, 1));
            txpointArrivee.setText(""+table.getValueAt(nbreLigne, 2));
            bheureDepart.setSelectedItem(""+(String.valueOf(table.getValueAt(nbreLigne, 3)).substring(0,3)));
            txPrix.setText(""+table.getValueAt(nbreLigne, 4));
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
