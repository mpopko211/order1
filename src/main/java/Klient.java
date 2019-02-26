import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Klient {

    private String imie;
    private String nazwisko;
    private String nazwaFirmy;
    private long nip;
    private boolean firma;
    private long pesel;
    private String adres;

    public Klient(String imie, String nazwisko, long pesel, String adres) {

        this.imie = sprawdzCzyNiePuste(imie, "Imię");
        this.nazwisko = sprawdzCzyNiePuste(nazwisko, "Nazwisko");
        if (PeselValidator.valid(pesel)) {
            this.pesel = pesel;
        } else {
            throw new IllegalArgumentException("Niepoprawny PESEL");
        }

        this.adres = sprawdzCzyNiePuste(adres, "Adres");
    }

    public Klient(String nazwaFirmy, long nip, String adres) {
        this.nazwaFirmy = sprawdzCzyNiePuste(nazwaFirmy, "Nazwa firmy");
        this.firma = true;
        if (NipValidator.valid(nip)) {
            this.nip = nip;
        } else {
            throw new IllegalArgumentException("Niepoprawny NIP");
        }
        this.adres = sprawdzCzyNiePuste(adres, "Adres");
    }

    private String sprawdzCzyNiePuste(String value, String message) {
        if (value != null && value.length() > 0) {
            return value;
        } else {
            throw new IllegalArgumentException("Pole: " + message + " nie może być puste");
        }
    }

    public static void dodajKlienta(Set<Klient> klients, Klient klient) {


        boolean czyZnaleziono = false;
        for (Klient klient1 : klients) {
            if (klient1.getImie().equals(klient.getImie())
                    && klient1.getNazwisko().equalsIgnoreCase(klient.getNazwisko()) &&
                    klient1.getPesel() == klient.getPesel()) {
                czyZnaleziono = true;
                break;
            }
        }
        if (!czyZnaleziono) {
            klients.add(klient);
        }
    }
}



