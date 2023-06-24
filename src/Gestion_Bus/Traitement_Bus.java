
package Gestion_Bus;

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

public class Traitement_Bus implements Operation_Bus<Bus>{

    File f=new File("bus.txt");
    FileWriter fw=null;
    ArrayList<Bus> bu = new ArrayList();
    StringTokenizer st = null;
    
    
/*================><    P A R T I E       E N R E G I S T R E R    B U S    ><================*/
    @Override
    public void enregistrer(Bus o) {
        if(o.getMarque().isEmpty() || o.getModele().isEmpty() || o.getAnnee().isEmpty() || o.getPlaqueImm().isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut saisir des donnes dans tous les cases","Message",JOptionPane.ERROR_MESSAGE);
        }
        else{
            Random r =new Random();
             String code= o.getMarque().substring(0,1) + o.getModele().substring(0,1) + (r.nextInt(99) + bu.size()+1);
             o.setCodeBus(code);
             
            //Récuperer la valeur taper par l'utilisateur
            String info = o.getCodeBus()+" | "+o.getMarque()+" | "+o.getModele()+"| "+o.getAnnee()+" | "+o.getNbreCylindre()+" | "+o.getTypeTransmission()+" | "+o.getPlaqueImm()+"\r\n";
            
            try {
                fw=new FileWriter(f,true);
                fw.write(info.toUpperCase());
                fw.close();
                JOptionPane.showMessageDialog(null,"Enregistrement réussi avec succes ! ", "Enregistrement -- Message Succes" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Traitement_Bus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }

    
    
/*================><    P A R T I E       M O D I F I E R      B U S     ><================*/ 
    @Override
    public boolean modifier(Bus o) {
        boolean c = false;
        for(Bus b:afficher()){
            if(o.getCodeBus().equals(b.getCodeBus())){
                if(o.getMarque().isEmpty() || o.getModele().isEmpty() || o.getAnnee().isEmpty() 
                   || o.getNbreCylindre().isEmpty() || o.getTypeTransmission().isEmpty() || o.getPlaqueImm().isEmpty()){
                     JOptionPane.showMessageDialog(null, "Il faut saisir des donnes dans tous les cases","Message",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String question[]={"Oui","Non"};
                    int rep=JOptionPane.showOptionDialog(null,"Voulez-vous modifier les information de ce bus?","Message de Confirmation",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,question,question[1]);
                    if(rep==0){
                        b.setMarque(o.getMarque());
                        b.setModele(o.getModele());
                        b.setTypeTransmission(o.getTypeTransmission());
                        b.setPlaqueImm(o.getPlaqueImm());
                        b.setAnnee(o.getAnnee());
                        b.setNbreCylindre(o.getNbreCylindre());
                        c = true;
                        break;
                    }
                }
            }
        }    
        if(c){
            try {
                fw=new FileWriter(f);
                for(Bus b:bu){
                    String nbreligne = b.getCodeBus()+"|" + b.getMarque()+"|" + b.getModele()+"|" + b.getTypeTransmission()+"|" + b.getPlaqueImm()
                    +"|" + b.getAnnee()+"|" + b.getNbreCylindre()+"\r\n";
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

    
    
/*================><    P A R T I E       A F F I C H E R      B U S     ><================*/
    @Override
    public ArrayList<Bus> afficher() {
        bu.clear();
        try {
            for(String i: Files.readAllLines(f.toPath())){
                st=new StringTokenizer(i,"|");
                bu.add(new Bus(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Traitement_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bu;
    }
    
    

/*================><    P A R T I E       S U P P R I M E R      B U S     ><================*/
    @Override
    public void supprimer(String codeBus) {
        try {
            for (Bus t : bu) {
                if(t.getCodeBus().equalsIgnoreCase(codeBus)){
                    bu.remove(t);
                    break;
                }
            }
            fw = new FileWriter(f);
            for(Bus b : bu){
                String info = b.getCodeBus()+" | "+b.getMarque()+" | "+b.getModele()+" | "+b.getAnnee()+" | "+b.getNbreCylindre()+" | "+b.getTypeTransmission()+" | "+b.getPlaqueImm()+"\r\n";
                fw.write(info.toUpperCase());
            }   
            fw.close();
            JOptionPane.showMessageDialog(null,"Suppression réussi avec succes! ", "       Suppression -- Message Succes" ,JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (IOException ex) {
            Logger.getLogger(Traitement_Bus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
