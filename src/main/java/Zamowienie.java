import lombok.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Zamowienie {

    private List<Pozycja> pozycje = new ArrayList<Pozycja>();

    public void dodajPozycje(Pozycja pozycja) {

        if (pozycja != null) {

            boolean czyZnaleziono = false;
            for (Pozycja pozycja1 : pozycje) {
                if (pozycja1.getNazwaTowaru().equals(pozycja.getNazwaTowaru())
                        && pozycja1.getCena() == pozycja.getCena()) {
                    pozycja1.setIleSztuk(pozycja1.getIleSztuk() + pozycja.getIleSztuk());
                    czyZnaleziono = true;
                    break;
                }
            }
            if (!czyZnaleziono) {
                pozycje.add(pozycja);
            }
        }
    }

    public double obliczWartosc() {
        double suma = 0;

        for (Pozycja pozycja : pozycje) {
            suma = suma + pozycja.obliczWartosc();
        }
        return suma;
    }

    public String toString() {
        String out = "Zamowienie:\n";
        for (Pozycja pozycja : pozycje) {
            out = out + pozycja.toString() + "\n";
        }
        out = out + "Razem: " + String.format("%.2f zł", obliczWartoscZRabatem()) + "\n";
        out = out + "Rabat: " + String.format("%.2f zł", obliczWartosc() - obliczWartoscZRabatem());

        return out;
    }

    public void usunPozycje(int index) {
        try {
            pozycje.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nie ma pozycji o takim indeksie");
        }


    }

    public void edytujPozycje(int index, String nazwaTowaru, int ilosc, double cena) {
        try {
            Pozycja edytowanaPozycja = pozycje.get(index);
            edytowanaPozycja.setNazwaTowaru(nazwaTowaru);
            edytowanaPozycja.setIleSztuk(ilosc);
            edytowanaPozycja.setCena(cena);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nie ma pozycji o takim indeksie");
        }

    }

    public double obliczWartoscZRabatem() {
        double suma = 0;
        for (Pozycja pozycja : pozycje) {
            suma = suma + pozycja.obliczWartoscZRabatem();
        }
        return suma;
    }

    public static void zapiszZamowienie(Zamowienie z, String nazwaPliku) {
        try {
            PrintWriter printWriter = new PrintWriter("pliki/" + nazwaPliku);

            for (Pozycja pozycja : z.getPozycje()) {
                printWriter.print(pozycja.getNazwaTowaru());
                printWriter.print("~");
                printWriter.print(pozycja.getIleSztuk());
                printWriter.print("~");
                printWriter.print(pozycja.getCena());
                printWriter.println();
            }
            printWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Zamowienie wczytajZamowienie(String nazwaPliku) {
        try {
            Scanner scanner = new Scanner(new File("pliki/" + nazwaPliku));
            Zamowienie zamowienie = new Zamowienie();
            while (scanner.hasNextLine()) {
                String odczyt = scanner.nextLine();

                String[] split = odczyt.split("~");
                try {
                    Pozycja pozycja = new Pozycja(split[0]
                            , Integer.valueOf(split[1])
                            , Double.valueOf(split[2])
                    );
                    zamowienie.dodajPozycje(pozycja);
                } catch (NumberFormatException e) {
                    // ignore
                }
            }
            return zamowienie;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}