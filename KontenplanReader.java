import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

/**
 * Beschreiben Sie hier die Klasse KontenplanReader.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class KontenplanReader
{
    private BufferedReader reader;
    private ArrayList <String> kontenNamenListe = new ArrayList<>();
    private String inputPath;

    /**
     * Konstruktor für Objekte der Klasse KontenplanReader
     */
    public KontenplanReader()
    {
        this.inputPath = "Kontenplan.txt";
    }


    public ArrayList<String> getKontenplanAsList() {
        try {
            reader = new BufferedReader(new FileReader(this.inputPath));
            String line = reader.readLine();
            while (line != null) {
                this.kontenNamenListe.add(line);
                line = reader.readLine();
                //  System.out.println(line);
                // read next line

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for ( String l : kontenNamenListe){
            System.out.println(l);
        }

        return this.kontenNamenListe;
    }
}
