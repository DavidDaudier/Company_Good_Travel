
package Gestion_Bus;

public class Bus {
    private String codeBus;
    private String marque;
    private String modele;
    private String annee;
    private String nbreCylindre;
    private String typeTransmission;
    private String plaqueImm;

    public Bus() {
    }

    public Bus(String codeBus, String marque, String modele, String annee, String nbreCylindre, String typeTransmission, String plaqueImm) {
        this.codeBus = codeBus;
        this.marque = marque;
        this.modele = modele;
        this.annee = annee;
        this.nbreCylindre = nbreCylindre;
        this.typeTransmission = typeTransmission;
        this.plaqueImm = plaqueImm;
    }

    public String getCodeBus() {
        return codeBus;
    }

    public void setCodeBus(String codeBus) {
        this.codeBus = codeBus;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getNbreCylindre() {
        return nbreCylindre;
    }

    public void setNbreCylindre(String nbreCylindre) {
        this.nbreCylindre = nbreCylindre;
    }

    public String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    public String getPlaqueImm() {
        return plaqueImm;
    }

    public void setPlaqueImm(String plaqueImm) {
        this.plaqueImm = plaqueImm;
    }
}
