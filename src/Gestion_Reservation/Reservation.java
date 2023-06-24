/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion_Reservation;

/**
 *
 * @author David DAUDIER
 */
public class Reservation {
    private String numReservation;
    private String nomPass;
    private String prenomPass;
    private String trajetChoisie;
    private String heureVoy;
    private String dateVoy;

    public Reservation() {
    }

    public Reservation(String numReservation, String nomPass, String prenomPass, String trajetChoisie, String heureVoy, String dateVoy) {
        this.numReservation = numReservation;
        this.nomPass = nomPass;
        this.prenomPass = prenomPass;
        this.trajetChoisie = trajetChoisie;
        this.heureVoy = heureVoy;
        this.dateVoy = dateVoy;
    }

    public String getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }

    public String getNomPass() {
        return nomPass;
    }

    public void setNomPass(String nomPass) {
        this.nomPass = nomPass;
    }

    public String getPrenomPass() {
        return prenomPass;
    }

    public void setPrenomPass(String prenomPass) {
        this.prenomPass = prenomPass;
    }

    public String getTrajetChoisie() {
        return trajetChoisie;
    }

    public void setTrajetChoisie(String trajetChoisie) {
        this.trajetChoisie = trajetChoisie;
    }

    public String getHeureVoy() {
        return heureVoy;
    }

    public void setHeureVoy(String heureVoy) {
        this.heureVoy = heureVoy;
    }

    public String getDateVoy() {
        return dateVoy;
    }

    public void setDateVoy(String dateVoy) {
        this.dateVoy = dateVoy;
    }
}
