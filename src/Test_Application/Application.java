
package Test_Application;

import Gestion_Bus.Vue_Bus;
import Gestion_Colis.Vue_Colis;
import Gestion_Reservation.Vue_Reservation;
import Gestion_Trajet.Vue_Trajet;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Application extends JFrame{
    
    public Application(){
        
    /* =============>< A F F I C H A G E     P R I N C I P A L E ><==============*/
        this.setTitle("Compagnie GOOD TRAVEL");
        this.setIconImage(new ImageIcon("./img/bus.png").getImage());
        JTabbedPane Tabpane = new JTabbedPane();
        Tabpane.setFont(new Font("Playfair Display", Font.BOLD, 15));
        Tabpane.setForeground(Color.DARK_GRAY);

        // Ajouter les JPanel dans le JTabbedPane
        accueil a = new accueil();
        Tabpane.addTab("Accueil", a);
        
        Vue_Trajet vtr = new Vue_Trajet();
        Tabpane.addTab("Gestion Trajet", vtr);
        
        Vue_Bus vbu = new Vue_Bus();
        Tabpane.addTab("Gestion Bus", vbu);
        
        Vue_Reservation vre = new Vue_Reservation();
        Tabpane.addTab("Gestion Reservation", vre);
        
        Vue_Colis vco = new Vue_Colis();
        Tabpane.addTab("Gestion Colis", vco);
        
        //Ajouts le JTabbedPane dans JFrame
        this.add(Tabpane);
        
        
        //Les metats donnees de JFrame
        this.setMinimumSize(new Dimension(500, 100));
        this.setExtendedState(6);
        this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
    
    public class accueil extends JPanel{
    
    public accueil(){
        this.setLayout(new FlowLayout());
        JLabel lImage=new JLabel(new ImageIcon("img/GOODTRAVEL.png"));
        this.setMinimumSize(new Dimension(2500, 2500));
        this.setBounds(0, 0, 1200, 700);
        this.add(lImage);
        this.setVisible(true);
        
    }
    
}


}
