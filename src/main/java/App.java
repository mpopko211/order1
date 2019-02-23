import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static Map<String, Zamowienie> dane = new HashMap<String, Zamowienie>();

    public static void main(String[] args) {
        System.out.println("Aplikacja wspomagajaca obsługe zamowień.");
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("-- MENU --");
            System.out.println("1 - Odczyt zamówień (plik)");
            System.out.println("2 - Zapis zamówień (plik)");
            System.out.println("3 - Dodaj nowe zamówiene");
            System.out.println("4 - Edytuj zamówienie");
            System.out.println("5 - Wyświetl podsumowanie zamówień");
            System.out.println();
            System.out.println("0 - Wyście z programu");
            System.out.println("--------");

            int menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    File folder = new File("pliki");
                    File[] listOfFiles = folder.listFiles();

                    for (File listOfFile : listOfFiles) {
                        Zamowienie zamowienie = Zamowienie.wczytajZamowienie(listOfFile.getName());
                        String key = listOfFile.getName().replace(".txt", "");
                        dane.put(key, zamowienie);
                    }

                    break;
                case 2:
                    for (Map.Entry<String, Zamowienie> stringZamowienieEntry : dane.entrySet()) {
                        Zamowienie.zapiszZamowienie(stringZamowienieEntry.getValue(),
                                stringZamowienieEntry.getKey() + ".txt");
                    }

                    break;
                case 3:
                    boolean czyDodacPozycje = true;
                    Zamowienie zamowienie = new Zamowienie();
                    System.out.println("Podaj numer zamowienia");
                    dane.put(scanner.next(), zamowienie);
                    do {
                        System.out.println("Podaj nazwe towaru");
                        String nazwa = scanner.next();
                        System.out.println("Podaj cene towaru");
                        double cena = Double.parseDouble(scanner.next());
                        System.out.println("Podaj ilosc towaru");
                        int ilosc = scanner.nextInt();
                        zamowienie.dodajPozycje(new Pozycja(nazwa, ilosc, cena));
                        System.out.println("Czy dodac kolejna pozycje");
                        czyDodacPozycje = scanner.nextBoolean();
                    } while (czyDodacPozycje);

                    break;
                case 4:

                    break;
                case 5:
                    for (Map.Entry<String, Zamowienie> stringZamowienieEntry : dane.entrySet()) {
                        System.out.println("Zamowienie o nr: " + stringZamowienieEntry.getKey());
                        System.out.println(stringZamowienieEntry.getValue());
                    }

                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

}
