import java.io.*;

public class DN08 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Ne dela popravi HITRO!!!!!!!!");
            return;
        }

        String datoteka = args[0];
        String[] planeti = args[1].toLowerCase().split("\\+");

        Planet[] planetiniObjekti = preberiPodatkeODatoteke(datoteka);
        double skupnaPovrsina = 0;

        for (Planet planet : planetiniObjekti) {
            for (String ime : planeti) {
                if (planet.getIme().toLowerCase().equals(ime)) {
                    skupnaPovrsina += planet.povrsina();
                }
            }
        }
        System.out.printf("Povrsina planetov \"%s\" je %.0f milijonov km2%n", args[1], skupnaPovrsina);
    }

    public static Planet[] preberiPodatkeODatoteke(String datoteka) {
        Planet[] planeti = new Planet[8];

        try (BufferedReader br = new BufferedReader(new FileReader(datoteka))) {

            String vrstica;
            int stevec = 0;

            while ((vrstica = br.readLine()) != null && stevec < 8) {
                String[] podatki = vrstica.split(":");

                if (podatki.length == 2) {

                    String ime = podatki[0].trim();
                    int radij = Integer.parseInt(podatki[1].trim());
                    planeti[stevec] = new Planet(ime, radij);

                    stevec++;
                }
            }
        } catch (IOException e) {
            System.out.println("Napaka pri branju datoteke: " + e.getMessage());
        }

        return planeti;
    }
}
class Planet {
    private String ime;
    private int radij;

    public Planet(String ime, int radij) {
        this.ime = ime;
        this.radij = radij;
    }

    public String getIme() {
        return ime;
    }

    public int getRadij() {
        return radij;
    }

    public double povrsina() {
        //Nevem tikja sem pretvoru na milijone ne?
        return (4 * Math.PI * radij * radij) / 1000000;
    }
}
