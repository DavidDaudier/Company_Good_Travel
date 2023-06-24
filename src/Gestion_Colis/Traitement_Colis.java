
package Gestion_Colis;


import Gestion_Bus.Traitement_Bus;
import Gestion_Trajet.Traitement_Trajet;
import Gestion_Trajet.Trajet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Traitement_Colis implements Operation_Colis<Colis>{

    File f=new File("colis.txt");
    FileWriter fw=null;
    ArrayList<Colis> co = new ArrayList();
    StringTokenizer st = null;
    
    
/*================><    P A R T I E       E N R E G I S T R E R    C O L I S    ><================*/
    @Override
    public void enregistrer(Colis o) {
        if(o.getExpediteur().isEmpty() || o.getDestinataire().isEmpty() || o.getTelExpd().isEmpty() || o.getTelDest().isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut saisir des donnes dans tous les cases","Message",JOptionPane.ERROR_MESSAGE);
        }
        else if(o.getMntPaye() <= 0){
            JOptionPane.showMessageDialog(null, "Le prix doit etre suppérieur a zéro", "Message",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            Random r =new Random();
             String code= o.getExpediteur().substring(0,2) + o.getDestinataire().substring(0,2) + (r.nextInt(99) + co.size()+1);
             o.setCodeColis(code);
             
            //Récuperer la valeur taper par l'utilisateur
            String info = o.getCodeColis()+" | "+o.getExpediteur()+" | "+o.getDestinataire()+"| "+o.getTelExpd()+" | "+o.getTelDest()+" | "+o.getMntPaye()+"\r\n";
            
            try {
                fw=new FileWriter(f,true);
                fw.write(info.toUpperCase());
                fw.close();
                JOptionPane.showMessageDialog(null,"Enregistrement réussi avec succes ! ", "Enregistrement -- Message Succes" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Traitement_Colis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    
    
/*================><    P A R T I E       M O D I F I E R      C O L I S    ><================*/
    @Override
    public boolean modifier(Colis o) {
        boolean c = false;
        for(Colis col:afficher()){
            if(o.getCodeColis().equals(col.getCodeColis())){
                if(o.getExpediteur().isEmpty() || o.getDestinataire().isEmpty() || o.getTelExpd().isEmpty() 
                   || o.getTelDest().isEmpty() || o.getMntPaye()<=0){
                     JOptionPane.showMessageDialog(null, "Il faut saisir des donnes dans tous les cases","Message",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String question[]={"Oui","Non"};
                    int rep=JOptionPane.showOptionDialog(null,"Voulez-vous modifier les informations de ce Colis?","Message de Confirmation",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,question,question[1]);
                    if(rep==0){
                        col.setExpediteur(o.getExpediteur());
                        col.setDestinataire(o.getDestinataire());
                        col.setTelExpd(o.getTelExpd());
                        col.setTelDest(o.getTelDest());
                        col.setMntPaye(o.getMntPaye());
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
                for(Colis col:co){
                    nbreligne = col.getCodeColis()+" | "+col.getExpediteur()+" | "+col.getDestinataire()+" | "+col.getTelExpd()+" | "+col.getTelDest()+" | "+col.getMntPaye()+"\r\n";
                    fw.write(nbreligne.toUpperCase());
                }
                fw.close();
                JOptionPane.showMessageDialog(null,"Modification réussi avec succes! ", "       Modification -- Message Succes" ,JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ex) {
                Logger.getLogger(Traitement_Trajet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return c;
    }

    
    
/*================><    P A R T I E       A F F I C H E R      C O L I S    ><================*/
    @Override
    public ArrayList<Colis> afficher() {
        co.clear();
        try {
            for(String i: Files.readAllLines(f.toPath())){
                st=new StringTokenizer(i,"|");
                co.add(new Colis(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),Double.parseDouble(st.nextToken())));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Traitement_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return co;
    }
    
}
