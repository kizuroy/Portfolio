public class DN02 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Napaka pri uporabi programa!");
            System.exit(1);
        }

        // Ugotovimo najdaljšo besedo
        int maxLen = 0;
        for (String word : args) {
            if (word.length() > maxLen) {
                maxLen = word.length();
            }
        }

        // Izpišemo besede z okvirjem
        String border = String.format("%" + (maxLen + 4) + "s", "").replace(' ', '*');
        System.out.println(border);
        for (String word : args) {
            String formatted = String.format("* %-" + maxLen + "s *", word);
            System.out.println(formatted);
        }
        System.out.println(border);
    }
}
