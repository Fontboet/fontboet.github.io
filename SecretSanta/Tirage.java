import java.util.LinkedList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Secret Santa 2024
 * 21/12/2024   
 */
public class Tirage {

    public LinkedList<Mail> noms;
    private int budget;

    public Tirage(String fichier, String budget) {
        this.create(fichier);
        this.budget = Integer.valueOf(budget);
    }

    public Mail getRandMail() {
        Random rand = new Random();
        int size = this.noms.size();
        int randInt = rand.nextInt(size);
        Mail mail = noms.get(randInt);
        noms.remove(randInt);
        return mail;
    }

    public void create(String fichier) {

        Lecteur lecteur = new Lecteur(fichier);
        this.noms = lecteur.init();
    }

    public String mailContent(String dest) {
        String contentMail = "Subject:Secret Santa LMPE\\\\n" + "Salut !\\\\nTu dois offrir un cadeau à " + dest + ".\\\\nLe budget est de maximum " + this.budget + "€.\\\\nBisous !";
        return contentMail;
    }

    public void secretSantaNoMail() {
        Mail firstMail = this.getRandMail();
        String mail1 = firstMail.getMail();
        String nom1 = firstMail.getNom();
        Mail mail = this.getRandMail();
        String mail2 = mail.getMail();
        String nom2 = mail.getNom();
        String fmail = mail1;
        String fnom = nom1;
        String contentMail = nom1 + " doit offrir à " + nom2;
        while (!this.noms.isEmpty()) {
            System.out.println(contentMail);
            mail1 = mail2;
            nom1 = nom2;
            mail = this.getRandMail();
            mail2 = mail.getMail();
            nom2 = mail.getNom();
            contentMail = nom1 + " doit offrir à " + nom2;
            }
        System.out.println(contentMail + "out");
        contentMail = nom2 + " doit offrir à " + fnom;
        System.out.println(contentMail + "  out");
    }


    public void secretSantaTest() {
        try {
            Mail firstMail = this.getRandMail();
            String mail1 = firstMail.getMail();
            String nom1 = firstMail.getNom();
            Mail mail = this.getRandMail();
            String mail2 = mail.getMail();
            String nom2 = mail.getNom();
            String fmail = mail1;
            String fnom = nom1;
            String contentMail = nom1 + " doit offrir à " + nom2;
            while (!this.noms.isEmpty()) {
                ProcessBuilder pb = new ProcessBuilder();
                pb.command("bash","-c","echo " + contentMail + " | msmtp theetos120@gmail.com");
                Process proc = pb.start();
                try {
                    proc.waitFor();
                    mail1 = mail2;
                    nom1 = nom2;
                    mail = this.getRandMail();
                    mail2 = mail.getMail();
                    nom2 = mail.getNom();
                    contentMail = nom1 + " doit offrir à " + nom2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ProcessBuilder pb = new ProcessBuilder();
            pb.command("bash","-c","echo " + contentMail + " | msmtp theetos120@gmail.com");
            Process proc = pb.start();
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contentMail = nom2 + " doit offrir à " + fnom;

            pb = new ProcessBuilder();
            pb.command("bash","-c","echo " + contentMail + " | msmtp theetos120@gmail.com");
            proc = pb.start();
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void secretSanta() {
        try {
            Mail firstMail = this.getRandMail();
            String mail1 = firstMail.getMail();
            String nom1 = firstMail.getNom();
            Mail mail = this.getRandMail();
            String mail2 = mail.getMail();
            String nom2 = mail.getNom();
            String fmail = mail1;
            String fnom = nom1;
            String contentMail = this.mailContent(nom2);
            // String contentMail = "Subject:Secret Santa LMPE\\\\n" + "Salut !\\\\nTu dois offrir un cadeau à " + nom2;
            while (!this.noms.isEmpty()) {
                ProcessBuilder pb = new ProcessBuilder();
                pb.command("bash","-c","printf \"" + contentMail + "\" | msmtp " + mail1);
                Process proc = pb.start();
                try {
                    proc.waitFor();
                    mail1 = mail2;
                    nom1 = nom2;
                    mail = this.getRandMail();
                    mail2 = mail.getMail();
                    nom2 = mail.getNom();
                    contentMail = this.mailContent(nom2);
                    // contentMail = "Subject:Secret Santa LMPE\\\\n" + "Salut !\\\\nTu dois offrir un cadeau à " + nom2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ProcessBuilder pb = new ProcessBuilder();
            pb.command("bash","-c","printf \"" + contentMail + "\" | msmtp " + mail1);
            Process proc = pb.start();
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contentMail = this.mailContent(fnom);
            // contentMail = "Subject:Secret Santa LMPE\\\\n" + "Salut !\\\\nTu dois offrir un cadeau à " + fnom;

            pb = new ProcessBuilder();
            pb.command("bash","-c","printf \"" + contentMail + "\" | msmtp " + mail2);
            proc = pb.start();
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return this.noms.size();
    }


    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Erreur : utilisation java Tirage fichier.txt budget");
            return;
        }
        Tirage suce = new Tirage(args[0], args[1]);
        System.out.println("Nombre de personnes : " + suce.getSize());
        // suce.secretSantaNoMail();
        // suce.secretSantaTest();
        suce.secretSanta();
    }
} 


