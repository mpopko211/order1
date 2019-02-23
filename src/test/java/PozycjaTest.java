import org.junit.Test;

import static org.junit.Assert.*;

public class PozycjaTest {

    @Test
    public void testTworzeniePozycji() {
        //given
        Pozycja pozycja = new Pozycja();
        //when
        pozycja.setNazwaTowaru("chleb");
        pozycja.setIleSztuk(2);
        pozycja.setCena(5.20);
        //then
        assertEquals("chleb", pozycja.getNazwaTowaru());
        assertEquals(2, pozycja.getIleSztuk());
        assertEquals(Double.valueOf(5.20), Double.valueOf(pozycja.getCena()));
    }

    @Test
    public void testKonstruktoraZParametrami() {
        //given
        Pozycja pozycja1 = new Pozycja("chleb", 2, 5.20);
        //when

        //then
        assertEquals("chleb", pozycja1.getNazwaTowaru());
        assertEquals(2, pozycja1.getIleSztuk());
        assertEquals(Double.valueOf(5.20), Double.valueOf(pozycja1.getCena()));

    }

    @Test
    public void testObliczWartos() {
        //given
        Pozycja pozycja2 = new Pozycja("kielbasa", 3, 5.0);
        //when
        double wartoscPozycji = pozycja2.obliczWartosc();

        //then
        assertEquals(Double.valueOf(15), Double.valueOf(wartoscPozycji));
    }

    @Test
    public void testObliczWartosPoZmianie() {
        //given
        Pozycja pozycja2 = new Pozycja("kielbasa", 3, 5.0);
        //when
        pozycja2.setIleSztuk(5);
        double wartoscPozycji = pozycja2.obliczWartosc();

        //then
        assertEquals(Double.valueOf(25), Double.valueOf(wartoscPozycji));
    }

    @Test
    public void testToString() {
        //given
        Pozycja pozycja3 = new Pozycja("chleb", 5, 10);
        //when
        System.out.println(pozycja3);
        //then
    }

    @Test
    public void testPromocjaPoniżej5Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 4, 10);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(40), Double.valueOf(wartoscZRabatem));
    }

    @Test
    public void testPromocjaPwyżej5Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 6, 100);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(570), Double.valueOf(wartoscZRabatem));
    }

    @Test
    public void testPromocjaPwyżej10Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 19, 100);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(1710), Double.valueOf(wartoscZRabatem));
    }

    @Test
    public void testPromocjaPwyżej20Sztuk() {
        //given
        Pozycja pozycja = new Pozycja("chleb", 40, 100);
        //when
        double wartoscZRabatem = pozycja.obliczWartoscZRabatem();
        //then
        assertEquals(Double.valueOf(3400), Double.valueOf(wartoscZRabatem));
    }
}