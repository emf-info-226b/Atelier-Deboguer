package ch.emf.flux_jeux_videos.beans;

import java.io.Serializable;

/**
 *
 * @author Kaynaki
 */
public class JeuxVideo{

    public JeuxVideo(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String nom;

}
