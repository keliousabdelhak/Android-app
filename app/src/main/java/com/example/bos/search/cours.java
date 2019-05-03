package com.example.bos.search;

import android.net.Uri;

/**
 * Created by Bos on 06/06/2018.
 */

public class cours {

    private String Domaine;
    private String titre_cours;
    private String spécialité;
    private String video;
    private String description;
    private String pdf ;
    private String date ;


    public cours() {

    }


    public cours(String domaine , String Titre_cours , String Spécialité , String description ) {
        this.Domaine = domaine;
        this.titre_cours = Titre_cours;
        this.spécialité = Spécialité;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        description = Description;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getSpécialité() {
        return spécialité;
    }

    public void setSpécialité(String Spécialité) {
        spécialité = Spécialité;
    }


    public String getDomaine() {
        return Domaine;
    }

    public void setDomaine(String domaine) {
        Domaine = domaine;
    }

    public String getTitre_cours() {
        return titre_cours;
    }

    public void setTitre_cours(String Titre_cours) {
        titre_cours = Titre_cours;
    }

    public void setVideo(String Video) {
        video = Video ;
    }

    public String getVideo() {
        return video;
    }


   /* public String getSpec() {
        return spécialité;
    }

    public void setSpec(String spec) { spécialité = spec;
    }
   */
}
