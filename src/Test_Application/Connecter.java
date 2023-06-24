package Test_Application;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Connecter extends JDialog implements ActionListener{
    
        //Declaration des donnees importants
        JLabel pseudo=null;
        JLabel Pwd=null;
        JButton btnConnect=null;
        JButton btnQuitter=null;
        
    //Methode Connecter
    public Connecter(){
        JDialog jd=new JDialog(this,"Connexion");
        jd.setTitle("Connecter");
        jd.setIconImage(new ImageIcon("./img/login1.png").getImage());
        
        JPanel login=new JPanel();
        
        // Ajouter une bordure au JPanel
        // Utilisation de la classe BORDER et BORDERFACTORY
        login.setBorder(BorderFactory.createTitledBorder("                   Système de Connexion"));
        
        // Creation des JLabel
        pseudo=new JLabel("Utilisateur");
        pseudo.setFont(new Font("Playfair Display", Font.BOLD, 20));
        pseudo.setForeground(Color.blue);
        
        Pwd=new JLabel("Password ");
        Pwd.setFont(new Font("Playfair Display", Font.BOLD, 20));
        Pwd.setForeground(Color.blue);

        // Creer des JTextField
        JTextField txNom=new JTextField(20);
        JPasswordField txPwd=new JPasswordField(20);
        
        // Creation des JButton
        btnConnect=new JButton("Connecter");
        btnQuitter=new JButton("Quitter");
        
        //Ajouter les icones des bouttons
        btnConnect.setIcon(new ImageIcon("./img/open.png"));
        btnQuitter.setIcon(new ImageIcon("./img/exit.png"));
        
        // Creation d'un JPanel pour  les boutons, placés à l'intérieur du formulaire
        JPanel panelBouton = new JPanel();    
        panelBouton.setBackground(null);
        
        panelBouton.add(btnConnect);
        btnConnect.addActionListener(this);
        
        panelBouton.add(btnQuitter);
        btnQuitter.addActionListener(this);
        
        //Affichage de la Nota Bene
        JLabel NB=new JLabel("Ce Système d'authentification ne génère pas de password correct");
        JLabel NB1=new JLabel(" Mais pour accéder à l'application, vous devez authentifier");
        NB.setForeground(Color.GRAY);
        NB1.setForeground(Color.GRAY);
        System.out.println("Pwd:"+txPwd.getPassword());
        System.out.println("Pwd:"+txPwd.getText());
        
        // Ajouter les composants dans le JPanel
        login.add(pseudo);
        login.add(txNom);
        login.add(Pwd);
        login.add(txPwd);
        login.add(btnConnect);
        login.add(btnQuitter);
        login.add(NB);
        login.add(NB1);
       
        // ajouter le JPanel dans le Jwindow
        jd.add(login);
        
        
        //Les metats donnees de JDialog
        jd.setSize(405, 200);
        jd.getContentPane().setBackground(Color.gray);
        jd.setLocationRelativeTo(this);
        jd.setResizable(false);
        jd.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        jd.setModal(true);
        jd.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int rep;
        if(e.getSource() == btnConnect)
        { 
            new Application().setVisible(true);
            this.dispose();
        }
        else if(e.getSource() == btnQuitter){
            String question[]={"Oui","Non"};
            rep=JOptionPane.showOptionDialog(null,"Voulez-vous vraiment quitter le programme?","Question",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,question,question[1]);
            if(rep==0){
                System.exit(0);
            }
        }
    }
    
    public static void main(String[] args) {
        new Connecter().setVisible(true);
    }
}