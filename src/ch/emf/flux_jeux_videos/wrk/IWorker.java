package ch.emf.flux_jeux_videos.wrk;

import java.util.ArrayList;

/**
 *
 * @author mario
 */
public interface IWorker {

    public boolean serialiseObjet(String filepath, Object obj);

    public Object deserialiseObjet(String filepath);

    public ArrayList<String> readTextFile(String filepath);

    public boolean writeTextFile(String filepath, ArrayList<String> linesToWrite);

    public boolean appendToTextFile(String filepath, String newLineContent);

    public boolean checkIfFileExists(String filepath);

    public boolean deleteFile(String filepath);
}
