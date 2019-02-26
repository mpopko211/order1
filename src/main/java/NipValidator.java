public class NipValidator {

    public static boolean valid(long nip){

        String nipString = String.valueOf(nip);
        if (nipString.length() != 10){
            return false;
        }

        String[] split = nipString.split("");

        int sumaKonrolna = Integer.valueOf(split[0])*6
                + Integer.valueOf(split[1])* 5
                + Integer.valueOf(split[2])* 7
                + Integer.valueOf(split[3])* 2
                + Integer.valueOf(split[4])* 3
                + Integer.valueOf(split[5])* 4
                + Integer.valueOf(split[6])* 5
                + Integer.valueOf(split[7])* 6
                + Integer.valueOf(split[8])* 7;

        sumaKonrolna = sumaKonrolna % 11;

        return sumaKonrolna == Integer.valueOf(split[9]);
    }
}
