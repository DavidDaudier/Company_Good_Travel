
package Gestion_Reservation;

import Gestion_Trajet.Trajet;
import Gestion_Trajet.Traitement_Trajet;
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

public class Traitement_Reservation implements Operation_Reservation<Reservation>{

    File f=new File("reserve.txt");
    FileWriter fw=null;
    File TrajetChoisieReserve=null;
    FileWriter fwTrajet=null;
    ArrayList<Reservation> re = new ArrayList();
    StringTokenizer st = null;
    ArrayList<String> trajet=new ArrayList();
    
    
    
/*================><    P A R T I E     E N R E G I S T R E R    R E S E R V AT I O N    ><================*/
    @Override
    public void enregistrer(Reservation o) {
        if(o.getNomPass().isEmpty() || o.getPrenomPass().isEmpty() || o.getDateVoy().isEmpty()){
            JOptionPane.showMessageDialog(null, "Il faut saisir des donnes dans tous les cases","Message",JOptionPane.ERROR_MESSAGE);
        }
        else{
            Random r =new Random();
             String code= o.getNomPass().substring(0,1) + o.getPrenomPass().substring(0,1) + (r.nextInt(99) + re.size()+1);
             o.setNumReservation(code);
             
            //Récuperer la valeur taper par l'utilisateur
            String info = o.getNumReservation()+" | "+o.getNomPass()+" | "+o.getPrenomPass()+"| "+o.getTrajetChoisie()+" | "+o.getHeureVoy()+" | "+o.getDateVoy()+"\r\n";
            
            try {
                fw=new FileWriter(f,true);
                fw.write(info.toUpperCase());
                fw.close();
                JOptionPane.showMessageDialog(null,"Enregistrement réussi avec succes ! ", "Enregistrement -- Message Succes" ,JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Traitement_Reservation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    
    
/*================><    P A R T I E       M O D I F I E R      R E S E R V A T I O N    ><================*/ 
    @Override
    public boolean modifier(Reservation o) {
        boolean c = false;
        for(Reservation r:afficher()){
            if(o.getNumReservation().equals(r.getNumReservation())){
                if(o.getNomPass().isEmpty() || o.getPrenomPass().isEmpty() || o.getTrajetChoisie().isEmpty() 
                   || o.getDateVoy().isEmpty()){
                     JOptionPane.showMessageDialog(null, "Il faut saisir des donnes dans tous les cases","Message",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String question[]={"Oui","Non"};
                    int rep=JOptionPane.showOptionDialog(null,"Voulez-vous modifier les informations de cette reservation?","Message de Confirmation",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,question,question[1]);
                    if(rep==0){
                        r.setNomPass(o.getNomPass());
                        r.setPrenomPass(o.getPrenomPass());
                        r.setTrajetChoisie(o.getTrajetChoisie());
                        r.setHeureVoy(o.getHeureVoy());
                        r.setDateVoy(o.getDateVoy());
                        c = true;
                        break;
                    }
                }
            }
        }    
        if(c){
            try {
                fw=new FileWriter(f);
                for(Reservation r:re){
                    String nbreligne = r.getNumReservation()+" | "+r.getNomPass()+" | "+r.getPrenomPass()+"| "+r.getTrajetChoisie()+" | "+r.getHeureVoy()+" | "+r.getDateVoy()+"\r\n";
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

    
    
/*================><    P A R T I E       A F F I C H E R      R E S E R V A T I O N    ><================*/
    @Override
    public ArrayList<Reservation> afficher() {
        re.clear();
        try {
            for(String i: Files.readAllLines(f.toPath())){
                st=new StringTokenizer(i,"|");
                re.add(new Reservation(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Traitement_Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }

    
    
/*================><    P A R T I E       S U P P R I M E R      R E S E R V A T I O N     ><================*/
    @Override
    public void supprimer(String numReservation) {
        try {
            for (Reservation r : re) {
                if(r.getNumReservation().equalsIgnoreCase(numReservation)){
                    re.remove(r);
                    break;
                }
            }
            fw = new FileWriter(f);
            for(Reservation r : re){
                String info = r.getNumReservation()+" | "+r.getNomPass()+" | "+r.getPrenomPass()+" | "+r.getTrajetChoisie()+" | "+r.getHeureVoy()+" | "+r.getDateVoy()+"\r\n";
                fw.write(info.toUpperCase());
            }   
            fw.close();
            JOptionPane.showMessageDialog(null,"Suppression réussi avec succes! ", "       Suppression -- Message Succes" ,JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (IOException ex) {
            Logger.getLogger(Traitement_Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

/*================><    P A R T I E        R E C U P E R E R      T R A J E T     ><================*/
    public ArrayList<String> listeDesTrajets() throws IOException{
        
        TrajetChoisieReserve = new File("trajet.txt");
        trajet.clear();
        
        for(String i : Files.readAllLines(TrajetChoisieReserve.toPath()))
        {
            st = new StringTokenizer(i," | ");
            Trajet t = new Trajet(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),Double.parseDouble(st.nextToken()));
            trajet.add(t.getPointDepart()+" - "+ t.getPointArrivee());
        }
        
        return trajet;
    }
}
