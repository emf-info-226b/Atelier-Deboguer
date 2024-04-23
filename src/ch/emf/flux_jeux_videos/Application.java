package ch.emf.flux_jeux_videos;

import ch.emf.flux_jeux_videos.beans.JeuxVideo;
import ch.emf.flux_jeux_videos.wrk.Worker;
import java.util.ArrayList;

/**
 *
 * @author Your name
 */
public class Application {
    
    public final static String FILE_SERIALIZE = "src/files/jeux.ser";
    public final static String FILE_NEW = "src/files/nouveaux.txt";
    public final static String FILE_CORRECTION = "src/files/correction.txt";
    public final static String FILE_OUTPUT = "src/files/jeux.txt"; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Worker wrk = new Worker();
        ArrayList<JeuxVideo> jeux = (ArrayList<JeuxVideo>) wrk.deserialiseObjet(FILE_SERIALIZE);
        if (jeux == null) {
            jeux = new ArrayList<>();
        }
        ArrayList<String> newJeux = wrk.readTextFile(FILE_NEW);
        if (newJeux != null) {
            for (String ligne : newJeux) {
                jeux.add(new JeuxVideo(ligne));
            }
            wrk.writeTextFile("nouveaux.txt", new ArrayList<>());
        }

        ArrayList<String> corrections = wrk.readTextFile("corrections.txt");
        if (corrections != null) {
            for (String correction : corrections) {
                String[] corrSplit = correction.split("\t");
                if ((corrSplit != null) && (corrSplit.length == 2)) {
                    for (int i = 0; i < jeux.size(); i++) {
                        JeuxVideo jeuACorr = new JeuxVideo(corrSplit[0]);
                        if (jeux.get(i).getNom().equals(jeuACorr.getNom())) {
                            jeux.get(i).setNom(corrSplit[1]);
                        }
                    }
                }
            }
            wrk.writeTextFile(FILE_CORRECTION, new ArrayList<>());
        }
        if (wrk.serialiseObjet(FILE_SERIALIZE, jeux) == true) {
            System.out.println("Sérialisation réussie !");
        } else {
            System.out.println("Sérialisation échec !");
        }
        ArrayList<String> jeuxVideo = new ArrayList<>();
        for (JeuxVideo jeu : jeux) {
            jeuxVideo.add(jeu.getNom());
        }
        wrk.writeTextFile(FILE_OUTPUT, jeuxVideo);

    }

}
