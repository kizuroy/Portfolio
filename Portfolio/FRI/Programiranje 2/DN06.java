public class DN06 {

    public static int bsdChecksum(String niz) {
        int checksum = 0;
        for (int i = 0; i<niz.length(); i++) {
            checksum = (checksum >> 1) + ((checksum & 1) << 15);
            checksum += niz.charAt(i);
            checksum &= 0xffff;
        }
        return checksum;
    }
    public static String povecaj(String niz) {

        char[] charniz = niz.toCharArray();
        int zadnjacrka = charniz.length - 1;

        if (charniz[zadnjacrka] == 'z') {
            charniz[zadnjacrka] = 'a';

            for (int i = zadnjacrka - 1; i >= 0; i--) {
                if (charniz[i] != 'z') {
                    charniz[i]++;
                    break;
                } else {
                    charniz[i] = 'a';
                }
            }
        } else {
            charniz[zadnjacrka]++;
        }
        return new String(charniz);
    }



    public static void main(String[] args) {
        //Naredimo string s samimi a-ji dolzine argumenta.
        String kontrolna = "a".repeat(args[0].length());

        int najdi = bsdChecksum(args[0]);
        int najdeno = bsdChecksum(kontrolna);

        while (najdi != najdeno){
            kontrolna = povecaj(kontrolna);
            najdeno = bsdChecksum(kontrolna);
        }

        System.out.println(kontrolna);
}}