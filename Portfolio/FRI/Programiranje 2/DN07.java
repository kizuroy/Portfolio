import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
public class DN07 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Napačno število argumentov!");
            System.out.println("Uporaba: java DN07 <število> <direktorij> [dodatni argumenti]");
            return;
        }

        int naloga = Integer.parseInt(args[0]);
        File direktorij = new File(args[1]);

        if (!direktorij.isDirectory()) {
            System.out.println("Drugi argument mora biti veljaven direktorij.");
            return;
        }

        switch (naloga) {
            case 1:
                izpisi_datoteke(direktorij);
                break;
            case 2:
                najvecja_datoteka(direktorij);
                break;
            case 3:
                izpis_vsebine(direktorij, Integer.parseInt(args[2]));
                break;
            case 4:
                kopiraj_datoteko(args[2], args[3]);
                break;
            case 5:
                break;
            case 6:
                najdiVDatotekah(direktorij, args[2]);
                break;
            case 7:
                drevo(direktorij);
                break;
            case 8:
                resiMatematicneIzraze(direktorij);
                break;
            case 9:
                nNajvecjih(direktorij, Integer.parseInt(args[2]));
                break;
            default:
                System.out.println("Naloga ne obstaja.");
        }
    }

    public static void izpisi_datoteke(File f) {
        File[] datoteke = f.listFiles();

        for (File datoteka : datoteke) {
            String imeDatoteke = datoteka.getName();
            double velikostDatoteke = datoteka.length() / 1000.0;
            String tipDatoteke = datoteka.isDirectory() ? "Mapa" : "Datoteka";
            System.out.printf("%-20s %-10s %7.3f\n", imeDatoteke, tipDatoteke, velikostDatoteke);
        }
    }

    public static void najvecja_datoteka(File f) {
        File[] datoteke = f.listFiles();

        double najvecjaVelikost = -1;
        double najmanjsaVelikost = Double.MAX_VALUE;
        String najvecjaIme = "";
        String najmanjsaIme = "";

        for (File datoteka : datoteke) {
            if (datoteka.isFile()) {

                double velikostDatoteke = datoteka.length() / 1000.0;

                if (velikostDatoteke > najvecjaVelikost) {
                    najvecjaVelikost = velikostDatoteke;
                    najvecjaIme = datoteka.getName();
                }

                if (velikostDatoteke < najmanjsaVelikost) {
                    najmanjsaVelikost = velikostDatoteke;
                    najmanjsaIme = datoteka.getName();
                }
            }
        }
        System.out.printf("%s %.3f\n", najvecjaIme, najvecjaVelikost);
        System.out.printf("%s %.3f\n", najmanjsaIme, najmanjsaVelikost);
    }

    public static void izpis_vsebine(File f, int n) {
        File[] datoteke = f.listFiles();
        for (File datoteka : datoteke) {
            if (datoteka.isFile()) {
                if (datoteka.getName().endsWith(".txt")) {
                    System.out.println(datoteka.getName());
                    try (BufferedReader br = new BufferedReader(new FileReader(datoteka))) {
                        String vrstica;
                        int stevec = 0;
                        while ((vrstica = br.readLine()) != null && stevec < n) {
                            System.out.printf("    %s%n", vrstica);
                            stevec++;
                        }
                    } catch (IOException e) {
                        System.out.println("Napaka pri branju datoteke: " + e.getMessage());
                    }
                } else {
                    System.out.println(datoteka.getName() + " (ni tekstovna datoteka)");
                }
            }
        }
    }

    public static void kopiraj_datoteko(String vhodnaDatoteka, String izhodnaDatoteka) {
        // Preveri, ali izhodna datoteka že obstaja
        File izhodnaFile = new File(izhodnaDatoteka);
        if (izhodnaFile.exists()) {
            // Preveri, ali je izhodna datoteka prazna
            if (izhodnaFile.length() > 0) {
                System.out.println("Napaka pri kopiranju, datoteka že vsebuje besedilo.");
                return;
            }
        }

        try {
            // Ustvari vhodni in izhodni tok za kopiranje vsebine datoteke
            BufferedReader vhodniTok = new BufferedReader(new FileReader(new File(vhodnaDatoteka)));
            BufferedWriter izhodniTok = new BufferedWriter(new FileWriter(izhodnaFile));

            String vrstica;
            while ((vrstica = vhodniTok.readLine()) != null) {
                izhodniTok.write(vrstica);
                izhodniTok.newLine();
            }

            // Zapri tokove
            vhodniTok.close();
            izhodniTok.close();

            System.out.println("Datoteka " + vhodnaDatoteka + " je bila uspešno skopirana v datoteko " + izhodnaDatoteka);
        } catch (IOException e) {
            System.out.println("Napaka pri kopiranju datoteke: " + e.getMessage());
        }
    }

    public static void najdiVDatotekah(File f, String iskanNiz) {
        File[] files = f.listFiles();
        if (files == null) {
            System.out.println("Napaka pri branju vsebine direktorija.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".txt")) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String vrstica;
                        int vrsticaSt = 1;
                        while ((vrstica = br.readLine()) != null) {
                            if (vrstica.contains(iskanNiz)) {
                                System.out.println(file.getName() + " " + vrsticaSt + ": " + vrstica);
                            }
                            vrsticaSt++;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void drevo(File f) {
        drevo(f, 0);
        // Kličemo preobremenjeno metodo s začetno stopnjo zamika 0
    }

    private static void drevo(File f, int stopnjaZamika) {
        // Izpis zamika
        System.out.print(getNizZamika(stopnjaZamika));

        // Izpis imena direktorija z '/' na koncu
        System.out.println("/" + f.getName());

        File[] datoteke = f.listFiles();

        for (File datoteka : datoteke) {
            if (datoteka.isFile()) {

                // Izpis imena datoteke z dodanim zamikom
                System.out.println(getNizZamika(stopnjaZamika + 1) + datoteka.getName());

            } else if (datoteka.isDirectory()) {

                // Rekurzivni klic za poddirektorije z povečano stopnjo zamika
                drevo(datoteka, stopnjaZamika + 1);
            }
        }
    }

    private static String getNizZamika(int stopnjaZamika) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < stopnjaZamika; i++) {

            sb.append("    "); // Štiri presledke za vsako stopnjo zamika
        }
        return sb.toString();
    }

    public static void resiMatematicneIzraze(File f) {
        File[] files = f.listFiles();

        for (File file : files) {

            if (file.isFile()) {
                boolean izraziNajdeni = false;
                System.out.println(file.getName());

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;

                    while ((line = br.readLine()) != null) {

                        if (jeMatematicniIzraz(line)) {

                            izraziNajdeni = true;
                            String[] izrazi = line.split("\\s+");

                            for (String izraz : izrazi) {

                                int vsota = izracunajIzraz(izraz);
                                System.out.println("  " + izraz + "=" + vsota);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Napaka pri branju datoteke: " + file.getName());
                }
            }
        }
    }

    private static boolean jeMatematicniIzraz(String line) {
        return line.matches("[0-9+\\-\\s]+");
    }

    private static int izracunajIzraz(String izraz) {
        int vsota = 0;
        char operacija = '+';

        for (int i = 0; i < izraz.length(); i++) {

            char c = izraz.charAt(i);
            if (Character.isDigit(c)) {

                int stevka = Character.getNumericValue(c);

                if (operacija == '+')
                {
                    vsota += stevka;

                } else {

                    vsota -= stevka;

                }

            } else if (c == '+') {

                operacija = '+';

            } else if (c == '-') {

                operacija = '-';
            }
        }
        return vsota;
    }

    public static void nNajvecjih(File f, int n) {

        // Ustvarimo seznam datotek v direktoriju in poddirektorijih
        ArrayList<File> files = new ArrayList<>();
        recursiveListFiles(f, files);

        // Ustvarimo seznam velikosti datotek
        ArrayList<Long> sizes = new ArrayList<>();
        for (File file : files) {
            sizes.add(file.length());
        }

        // Sortiramo seznam velikosti datotek v padajočem vrstnem redu
        for (int i = 0; i < sizes.size() - 1; i++) {

            for (int j = 0; j < sizes.size() - i - 1; j++) {

                if (sizes.get(j) < sizes.get(j + 1)) {

                    Collections.swap(sizes, j, j + 1);
                    Collections.swap(files, j, j + 1);
                }
            }
        }

        // Izpišemo n največjih datotek in njihove velikosti
        for (int i = 0; i < n && i < files.size(); i++) {
            System.out.println(files.get(i).getName() + " " + sizes.get(i));
        }
    }

    public static void recursiveListFiles(File f, ArrayList<File> files) {

        // Rekurzivna metoda za iskanje datotek v poddirektorijih
        File[] fileList = f.listFiles();

        if (fileList != null) {

            for (File file : fileList) {
                if (file.isDirectory()) {
                    recursiveListFiles(file, files);
                } else {
                    files.add(file);
                }
            }
        }
    }
}

