/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_Colis;

/**
 *
 * @author David DAUDIER
 */
public class Colis {
    private String codeColis;
    private String expediteur;
    private String destinataire;
    private String telExpd;
    private String telDest;
    private double mntPaye;

    public Colis() {
    }

    public Colis(String codeColis, String expediteur, String destinataire, String telExpd, String telDest, double mntPaye) {
        this.codeColis = codeColis;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.telExpd = telExpd;
        this.telDest = telDest;
        this.mntPaye = mntPaye;
    }

    public String getCodeColis() {
        return codeColis;
    }

    public void setCodeColis(String codeColis) {
        this.codeColis = codeColis;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getTelExpd() {
        return telExpd;
    }

    public void setTelExpd(String telExpd) {
        this.telExpd = telExpd;
    }

    public String getTelDest() {
        return telDest;
    }

    public void setTelDest(String telDest) {
        this.telDest = telDest;
    }

    public double getMntPaye() {
        return mntPaye;
    }

    public void setMntPaye(double mntPaye) {
        this.mntPaye = mntPaye;
    }
}
