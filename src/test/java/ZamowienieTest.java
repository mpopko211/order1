import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ZamowienieTest {

    @Test
    public void testKonstruktoraBezArgumentow() {
        //given
        Zamowienie zamowienie = new Zamowienie();
        //when
        List<Pozycja> listaPozycji = new ArrayList<Pozycja>();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 2, 8.5);

        listaPozycji.add(pozycja);
        listaPozycji.add(pozycja2);
        zamowienie.setPozycje(listaPozycji);
        //then
        assertEquals(2, zamowienie.getPozycje().size());
    }

    @Test
    public void testKonstruktoraZArgumentami() {
        //given
        List<Pozycja> listaPozycji = new ArrayList<Pozycja>();
        Pozycja pozycja = new Pozycja("Mleko", 7, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 2, 8.5);

        listaPozycji.add(pozycja);
        listaPozycji.add(pozycja2);
        //when
        Zamowienie zamowienie2 = new Zamowienie(listaPozycji);
        //then
        assertEquals(2, zamowienie2.getPozycje().size());
    }

    @Test
    public void testDodawaniaJednejPozycji() {
        //given
        Zamowienie zamowienie3 = new Zamowienie();
        //when
        Pozycja pozycja3 = new Pozycja("Woda", 10, 1.40);
        zamowienie3.dodajPozycje(pozycja3);
        //then
        assertEquals(1, zamowienie3.getPozycje().size());
    }

    @Test
    public void testDodawaniaJednejPozycjiNullowej() {
        //given
        Zamowienie zamowienie4 = new Zamowienie();
        //when
        zamowienie4.dodajPozycje(null);
        //then
        assertEquals(0, zamowienie4.getPozycje().size());
    }

    @Test
    public void testSumyZamowienia2Pozycji() {
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 2, 8.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //when
        double suma = zamowienie5.obliczWartosc();
        //then
        assertEquals(Double.valueOf(pozycja.obliczWartosc() + pozycja2.obliczWartosc())
                , Double.valueOf(suma));
    }

    @Test
    public void testWyswietlZamowienie() {
        //given
        Zamowienie zamowienie5 = new Zamowienie();
        Pozycja pozycja = new Pozycja("Mleko", 12, 2.5);
        Pozycja pozycja2 = new Pozycja("Chleb", 3, 8.5);
        zamowienie5.dodajPozycje(pozycja);
        zamowienie5.dodajPozycje(pozycja2);
        //when

        //then
        //System.out.println(zamowienie5);

        assertTrue(zamowienie5.toString().contains("Razem: "));
        assertTrue(zamowienie5.toString().contains("Zamowienie:"));
        assertTrue(zamowienie5.toString().contains(
                String.format("%.2f", zamowienie5.obliczWartoscZRabatem())));

    }

    @Test
    public void testUsuwaniaPozycji() {
        //given
        Zamowienie zamowienie6 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("masło", 4, 7.50);

        zamowienie6.dodajPozycje(pozycja);
        zamowienie6.dodajPozycje(pozycja2);
        //when
        zamowienie6.usunPozycje(1);
        //then
        assertEquals(1, zamowienie6.getPozycje().size());
        System.out.println(zamowienie6.getPozycje().get(0));

    }

    @Test
    public void testUsuwaniaNieistniejacejPozycji() {
        //given
        Zamowienie zamowienie6 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("masło", 4, 7.50);

        zamowienie6.dodajPozycje(pozycja);
        zamowienie6.dodajPozycje(pozycja2);
        //when
        zamowienie6.usunPozycje(2);
        //then
        assertEquals(2, zamowienie6.getPozycje().size());

    }

    @Test
    public void testEdytowaniaPozycji() {
        //given
        Zamowienie zamowienie7 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("masło", 4, 7.50);

        zamowienie7.dodajPozycje(pozycja);
        zamowienie7.dodajPozycje(pozycja2);

        //when
        zamowienie7.edytujPozycje(0, "bulka", 1, 0.80);

        //then
        Pozycja pozycja3 = zamowienie7.getPozycje().get(0);
        assertEquals("bulka", pozycja3.getNazwaTowaru());
        assertEquals(1, pozycja3.getIleSztuk());
        assertEquals(Double.valueOf(0.80), Double.valueOf(pozycja3.getCena()));

    }

    @Test
    public void testDodawaniaIstniejacejPozycji() {

        //given
        Zamowienie zamowienie8 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("maslo", 4, 7.50);

        zamowienie8.dodajPozycje(pozycja);
        zamowienie8.dodajPozycje(pozycja2);

        //when
        zamowienie8.dodajPozycje(pozycja2);
        //then
        assertEquals(2, zamowienie8.getPozycje().size());
        Pozycja pozycja3 = zamowienie8.getPozycje().get(1);
        assertEquals("maslo", pozycja3.getNazwaTowaru());
        assertEquals(8, pozycja3.getIleSztuk());


    }

    @Test
    public void testDodawaniaIstniejacejPozycjiZNowaCena() {
        //given
        Zamowienie zamowienie9 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("maslo", 4, 7.50);

        zamowienie9.dodajPozycje(pozycja);
        zamowienie9.dodajPozycje(pozycja2);

        //when
        Pozycja pozycja3 = new Pozycja("maslo", 3, 8.50);
        zamowienie9.dodajPozycje(pozycja3);

        //then
        assertEquals(3, zamowienie9.getPozycje().size());
        Pozycja pozycja4 = zamowienie9.getPozycje().get(1);
        assertEquals("maslo", pozycja4.getNazwaTowaru());
        assertEquals(4, pozycja4.getIleSztuk());
    }

    @Test
    public void testSumyZRabatem() {
        //given
        Zamowienie zamowienie9 = new Zamowienie();
        Pozycja pozycja = new Pozycja("chleb", 2, 2.50);
        Pozycja pozycja2 = new Pozycja("maslo", 4, 7.50);
        Pozycja pozycja3 = new Pozycja("kawa", 10, 9.50);

        zamowienie9.dodajPozycje(pozycja);
        zamowienie9.dodajPozycje(pozycja2);
        zamowienie9.dodajPozycje(pozycja3);
        //when
        double suma = zamowienie9.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(pozycja.obliczWartoscZRabatem()
                        + pozycja2.obliczWartoscZRabatem() + pozycja3.obliczWartoscZRabatem())
                , Double.valueOf(suma));
        System.out.println(zamowienie9);
    }
}

