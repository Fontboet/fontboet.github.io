import java.util.LinkedList;
import java.io.*;

public class Lecteur {

    private String docName;

    public Lecteur(String docName) {
        this.docName = docName;
    }

    public LinkedList<Mail> init() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.docName));
            LinkedList<Mail> liste = new LinkedList<Mail>();
            String ligne = reader.readLine();
            while (ligne != null) {
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                String[] parts = ligne.split("\\s+",2);
                if (parts.length == 2) {
                    Mail mail = new Mail(parts[0].trim(), parts[1].trim());
                    liste.add(mail);
                } else {
                    System.err.println("Mauvais format de fichier");
                }
                ligne = reader.readLine();
            }
            return liste;
            } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
    }
}