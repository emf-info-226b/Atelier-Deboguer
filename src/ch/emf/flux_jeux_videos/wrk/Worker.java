package ch.emf.flux_jeux_videos.wrk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class Worker implements IWorker {

    public final static Charset TEXT_FILE_CHARSET = StandardCharsets.UTF_8;

    @Override
    public boolean serialiseObjet(String filepath, Object obj) {
        boolean bResult = false;

        if (obj != null) {    // S'il n'y a rien à faire on ne fait rien (pas même flinguer le fichier) !

            ObjectOutputStream out = null;

            try {
                out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filepath)));
                out.writeObject(obj);
                out.flush();
                out.close();
                out = null;
                bResult = true;     // Si on est ici c'est que vraiment tout s'est bien passé !
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (out != null) {
                    try {
                        out.close();
                        out = null;
                    } catch (IOException ex) {
                    }
                }

                // Si l'écriture a échoué d'une façon où d'une autre, ne pas laisser un fichier incomplet
                if (!bResult) {
                    try {
                        new File(filepath).delete();
                    } catch (Exception e) {
                    }
                }
            }
        }

        return bResult;
    }

    @Override
    public Object deserialiseObjet(String filepath) {
        Object result = null;

        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filepath)));
            result = in.readObject();
            in.close();
            in = null;
        } catch (Exception ex) {
            result = null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException ex) {
                }
            }
        }

        return result;

    }

    @Override
    public ArrayList<String> readTextFile(String filepath) {
        ArrayList<String> resultat = null;

        BufferedReader br = null;

        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filepath), TEXT_FILE_CHARSET));

            resultat = new ArrayList<String>();

            String ligne;
            while ((ligne = br.readLine()) != null) {
                resultat.add(ligne);
            }

            br.close();
            br = null;
        } catch (Exception e) {
            resultat = null;
        } finally {
            // Toujours fermer le fichier si pas déjà fait !
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException ioe2) {
                    // On peut l'ignorer, le cas est déjà traité
                }
            }
        }

        return resultat;
    }

    @Override
    public boolean writeTextFile(String filepath, ArrayList<String> linesToWrite) {
        boolean resultat = false;

        if (linesToWrite != null) {    // S'il n'y a rien à faire on ne fait rien (pas même effacer le fichier) !

            BufferedWriter bw = null;

            try {
                // L'ancien fichier sera remplacé par le nouveau contenu, même si vide
                bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(filepath, false), TEXT_FILE_CHARSET));

                for (String ligne : linesToWrite) {
                    if (ligne != null) {
                        bw.write(ligne);
                        bw.newLine();
                    }
                }

                bw.flush();
                bw.close();
                bw = null;

                resultat = true;    // Si on est ici c'est que tout roule !
            } catch (Exception e) {
            } finally {
                // Toujours fermer le fichier si pas déjà fait !
                if (bw != null) {
                    try {
                        bw.close();
                        bw = null;
                    } catch (IOException ioe2) {
                        // On peut l'ignorer, le cas est déjà traité
                    }
                }

                // Si l'écriture a échoué d'une façon où d'une autre, ne pas laisser un fichier incomplet
                if (!resultat) {
                    try {
                        new File(filepath).delete();
                    } catch (Exception e) {
                    }
                }
            }
        }

        return resultat;
    }

    @Override
    public boolean appendToTextFile(String filepath, String newLineContent) {
        boolean resultat = false;

        if (newLineContent != null) {    // S'il n'y a rien à faire on ne fait rien !

            BufferedWriter bw = null;

            try {
                // L'ancien fichier ne sera PAS remplacé par le nouveau contenu, il sera rajouté à la fin de ce fichier.
                bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(filepath, true), TEXT_FILE_CHARSET));

                bw.write(newLineContent);
                bw.newLine();

                bw.flush();
                bw.close();
                bw = null;

                resultat = true;    // Si on est ici c'est que tout roule !
            } catch (Exception e) {
            } finally {
                // Toujours fermer le fichier si pas déjà fait !
                if (bw != null) {
                    try {
                        bw.close();
                        bw = null;
                    } catch (IOException ioe2) {
                        // On peut l'ignorer, le cas est déjà traité
                    }
                }
            }
        }

        return resultat;
    }

    @Override
    public boolean checkIfFileExists(String filepath) {
        boolean ok = false;
        try {
            File file = new File(filepath);
            if (file.exists()) {
                ok = true;
            }
        } catch (Exception e) {
        }
        return ok;

    }

    @Override
    public boolean deleteFile(String filepath) {
        boolean ok = false;
        try {
            File file = new File(filepath);
            file.delete();
            ok = true;
        } catch (Exception e) {
        }
        return ok;
    }

}
