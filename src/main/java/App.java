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
                    for (Map.Entry<String, Zamowienie> entry : dane.entrySet()) {
                        Zamowienie.zapiszZamowienie(
                                entry.getValue(),
                                entry.getKey() + ".txt"
                        );
                    }
                    break;
                case 3:
                    Zamowienie zamowienie = new Zamowienie();
                    System.out.println("Podaj numer zamowienia");
                    dane.put(scanner.next(), zamowienie);
                    dodajPozycje(scanner, zamowienie);
                    break;
                case 4:
                    System.out.println("Podaj numer zamówienia");
                    String numerZamowienia = scanner.next();
                    Zamowienie poszukiwaneZamowienie = dane.get(numerZamowienia);
                    if (poszukiwaneZamowienie != null) {
                        System.out.println("Czy edytować pozycję zamówienia? (t/n)");
                        if (scanner.next().equals("t")) {
                            edytujPozycje(scanner, poszukiwaneZamowienie);
                        }
                        System.out.println("Czy dodać nowa pozycję zamówienia? (t/n)");
                        if (scanner.next().equals("t")) {
                            dodajPozycje(scanner, poszukiwaneZamowienie);
                        }
                    } else {
                        System.out.println("Nieudało się odnaleźć zamówienia o numerze " + poszukiwaneZamowienie);
                    }
                    break;
                case 5:
                    System.out.println("##########################");
                    for (Map.Entry<String, Zamowienie> entry : dane.entrySet()) {
                        System.out.println("Zamowienie o nr: " + entry.getKey());
                        System.out.println(entry.getValue());
                        System.out.println("##########################");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    public static void edytujPozycje(Scanner scanner, Zamowienie zamowienie) {
        boolean czyEdytowaVPozycje = true;
        do {
            System.out.println("Podaj numer pozycji na zamówieniu");
            int numerPozycji = scanner.nextInt();

            String nazwa = nazwaTowaru(scanner);
            double cena = cenaTowaru(scanner);
            int ilosc = iloscTowaru(scanner);

            zamowienie.edytujPozycje(numerPozycji, nazwa, ilosc, cena);
            System.out.println("Czy edytować kolejną pozycję? (t/n)");
            String znak = scanner.next();
            czyEdytowaVPozycje = znak.equals("t");
        } while (czyEdytowaVPozycje);
    }

    public static void dodajPozycje(Scanner scanner, Zamowienie zamowienie) {
        boolean czyDodacPozycje = true;
        do {
            String nazwa = nazwaTowaru(scanner);
            double cena = cenaTowaru(scanner);
            int ilosc = iloscTowaru(scanner);

            zamowienie.dodajPozycje(new Pozycja(nazwa, ilosc, cena));
            System.out.println("Czy dodac kolejna pozycje? (t/n)");
            String znak = scanner.next();
            czyDodacPozycje = znak.equals("t");
        } while (czyDodacPozycje);
    }

    public static String nazwaTowaru(Scanner scanner) {
        System.out.println("Podaj nazwe towaru?");
        return scanner.next();
    }

    public static double cenaTowaru(Scanner scanner) {
        System.out.println("Podaj cena towaru?");
        return scanner.nextDouble();
    }

    public static int iloscTowaru(Scanner scanner) {
        System.out.println("Podaj ilosc towaru?");
        return scanner.nextInt();
    }

}

