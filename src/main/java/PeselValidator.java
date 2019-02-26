public class PeselValidator {


    public static boolean valid(long pesel) {
        String peselNaStringu = String.valueOf(pesel);
        if (peselNaStringu.length() != 11) {
            return false;
        }
        String[] split = peselNaStringu.split("");
        int sumaKontrolna = Integer.valueOf(split[0]) * 1 +
                Integer.valueOf(split[1]) * 3 +
                Integer.valueOf(split[2]) * 7 +
                Integer.valueOf(split[3]) * 9 +
                Integer.valueOf(split[4]) * 1 +
                Integer.valueOf(split[5]) * 3 +
                Integer.valueOf(split[6]) * 7 +
                Integer.valueOf(split[7]) * 9 +
                Integer.valueOf(split[8]) * 1 +
                Integer.valueOf(split[9]) * 3;


        sumaKontrolna = sumaKontrolna % 10;
        sumaKontrolna = 10 - sumaKontrolna;
        sumaKontrolna = sumaKontrolna % 10;

        return sumaKontrolna == Integer.valueOf(split[10]);
    }
}

