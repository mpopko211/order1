import lombok.*;

import java.io.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pozycja {
    private String nazwaTowaru;
    private int ileSztuk;
    private double cena;

    public double obliczWartosc() {
        return ileSztuk * cena;
    }

    public String toString() {

        String output = String.format("%-20s", nazwaTowaru)
                + String.format("%10.2f zł", cena)
                + String.format("%4d szt.", ileSztuk)
                + String.format("%10.2f zł", obliczWartosc());
        return output;
    }

    public double obliczWartoscZRabatem() {
        if (ileSztuk < 5) {
            return obliczWartosc();
        } else if (ileSztuk >= 10) {
            return obliczWartosc() * 0.95;
        } else if (ileSztuk <= 20) {
            return obliczWartosc() * 0.9;
        } else {
            return obliczWartosc() * 0.85;
        }
    }
}

