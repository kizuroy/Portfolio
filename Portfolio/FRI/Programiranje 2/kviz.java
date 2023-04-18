public class kviz {

    public static void preveriInNarisi(int[] kraljice) {
        int n = kraljice.length; // Velikost šahovske deske
        boolean napadajo = false; // Ali se kraljice napadajo

        // Preveri, ali se kraljice napadajo
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Preveri, ali se kraljice napadajo po vrsticah, diagonalah ali anti-diagonalah
                if (kraljice[i] == kraljice[j] || // Preveri vrstice
                        kraljice[i] - kraljice[j] == i - j || // Preveri diagonale
                        kraljice[i] - kraljice[j] == j - i) { // Preveri anti-diagonale
                    napadajo = true;
                    break;
                }
            }
            if (napadajo) {
                break;
            }
        }

        // Izris šahovske deske
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (kraljice[j] == i) {
                    System.out.print("K ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }

        // Izpiši sporočilo, ali se kraljice napadajo ali ne
        if (napadajo) {
            System.out.println("Kraljice se napadajo");
        } else {
            System.out.println("Kraljice se ne napadajo");
        }
    }


    public static void main(String[] args) {
        int[] kraljice1 = {1, 3, 0, 2};
        int[] kraljice2 = {1, 0, 3, 2};
        System.out.println("Primer 1:");
        preveriInNarisi(kraljice1);
        System.out.println("Primer 2:");
        preveriInNarisi(kraljice2);
    }

}
