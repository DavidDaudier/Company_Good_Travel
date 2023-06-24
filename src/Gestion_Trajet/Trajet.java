/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_Trajet;

/**
 *
 * @author David DAUDIER
 */
public class Trajet {
    private String codeTrajet;
    private String pointDepart;
    private String pointArrivee;
    private String heureDepart;
    private double prixTrajet;

    public Trajet() {
    }

    public Trajet(String codeTrajet, String pointDepart, String pointArrivee, String heureDepart, double prixTrajet) {
        this.codeTrajet = codeTrajet;
        this.pointDepart = pointDepart;
        this.pointArrivee = pointArrivee;
        this.heureDepart = heureDepart;
        this.prixTrajet = prixTrajet;
    }

    public String getCodeTrajet() {
        return codeTrajet;
    }

    public void setCodeTrajet(String codeTrajet) {
        this.codeTrajet = codeTrajet;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getPointArrivee() {
        return pointArrivee;
    }

    public void setPointArrivee(String pointArrivee) {
        this.pointArrivee = pointArrivee;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public double getPrixTrajet() {
        return prixTrajet;
    }

    public void setPrixTrajet(double prixTrajet) {
        this.prixTrajet = prixTrajet;
    }  
}
