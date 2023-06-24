
package Gestion_Trajet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;


public class Traitement_Trajet implements Operation_Trajet<Trajet> {
    
    File f=new File("trajet.txt");
    FileWriter fw=null;
    ArrayList<Trajet> tr = new ArrayList();
    StringTokenizer st = null;
    
    
/*================><    P A R T I E       C R E E R      T R A J E T     ><================*/    
    @Override
    public void creerTrajet(Trajet o) {
        if(o.getPointDepart().isEmpty() || o.getPointArrivee().isEmpty() || o.getHeureDepart().isEmpty()
                || String.valueOf(o.getPrixTrajet() ).isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Il faut saisir des donnés dans tous les cases","Message Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else if(o.getPointDepart().equalsIgnoreCase(o.getPointArrivee())){
            JOptionPane.showMessageDialog(null, "Désolé! Veillez entrer votre point de départ et d'arriver","Message Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else if(o.getPrixTrajet() <= 0){
            JOptionPane.showMessageDialog(null, "Le prix doit être suppérieur à zéro", "Message Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Random r =new Random();
             String code= o.getPointDepart().substring(0,1) + o.getPointArrivee().substring(0,1)+(r.nextInt(99) + tr.size()+1);
             o.setCodeTrajet(code);
             
            //Récuperer la valeur taper par l'utilisateur
            String info = o.getCodeTrajet()+"|"+o.getPointDepart()+"|"+o.getPointArrivee()+"|"+o.getHeureDepart()+"|"+o.getPrixTrajet()+"\r\n";
            
            try {
                fw=new FileWriter(f,true);
                fw.write(info.toUpperCase());
                fw.close();
                JOptionPane.showMessageDialog(null,"Création réussi avec succès! ", "       Création -- Message Succès" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Traitement_Trajet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    
    
/*================><    P A R T I E       M O D I F I E R      T R A J E T     ><================*/     
    @Override
    public boolean modifier(Trajet o) {
        boolean c = false;
        for(Trajet t:afficher() ){
            if(o.getCodeTrajet().equals(t.getCodeTrajet())){
                if(o.getPrixTrajet() <= 0){
                    JOptionPane.showMessageDialog(null, "Le prix doit être suppérieur à zéro", "Message Erreur",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    String question[]={"Oui","Non"};
                    int rep=JOptionPane.showOptionDialog(null,"Voulez-vous modifier ce trajet?","Message de Confirmation",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,question,question[1]);
                    if(rep==0){
                        t.setPointDepart(o.getPointDepart());
                        t.setPointArrivee(o.getPointArrivee());
                        t.setHeureDepart(o.getHeureDepart());
                        t.setPrixTrajet(o.getPrixTrajet());
                        c = true;
                        break;
                    }
                }
            }
        }    
        if(c){
            try {
                fw=new FileWriter(f);
                String nbreligne;
                for(Trajet t:tr){
                    nbreligne = t.getCodeTrajet()+"|"+t.getPointDepart()+"|"+t.getPointArrivee()+"|"+t.getHeureDepart()+"|"+t.getPrixTrajet()+"\r\n";
                    fw.write(nbreligne.toUpperCase());
                }
                fw.close();
                JOptionPane.showMessageDialog(null,"Modification réussi avec succès! ", "       Modification -- Message Succès" ,JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ex) {
                Logger.getLogger(Traitement_Trajet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return c;
    }
    
    

/*================><    P A R T I E       A F F I C H E R      T R A J E T     ><================*/ 
    @Override
    public ArrayList<Trajet> afficher() {
        tr.clear();
        try {
            for(String i: Files.readAllLines(f.toPath())){
                st=new StringTokenizer(i,"|");
                tr.add(new Trajet(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(), Double.parseDouble(st.nextToken())));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Traitement_Trajet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tr;
    }
    
    

/*================><    P A R T I E       S U P P R I M E R      T R A J E T     ><================*/    
    @Override
    public void supprimer(String codeTrajet) {
        try {
            for (Trajet t : tr) {
                if(t.getCodeTrajet().equalsIgnoreCase(codeTrajet)){
                    tr.remove(t);
                    break;
                }
            }
            fw = new FileWriter(f);
            for(Trajet t : tr){
                String info = t.getCodeTrajet()+"|"+t.getPointDepart()+"|"+t.getPointArrivee()+"|"+t.getHeureDepart()+"|"+t.getPrixTrajet()+"\r\n";
                fw.write(info.toUpperCase());
            }   
            fw.close();
            JOptionPane.showMessageDialog(null,"Suppression réussi avec succès! ", "       Suppression -- Message Succès" ,JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (IOException ex) {
            Logger.getLogger(Traitement_Trajet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
