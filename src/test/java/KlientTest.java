import org.junit.Test;

import static org.junit.Assert.*;

public class KlientTest {

    @Test
    public void testImię(){
        //given
        Klient klient = new Klient();

        //when
        klient.setAdres("AAA");
        klient.setImię("Andrzej");
        klient.setNazwisko("Nowak");
        klient.setPesel(123456789123l);
        //then
        assertEquals(false,klient.isFirma());
        assertEquals("Andrzej",klient.getImię());
        assertEquals("Nowak", klient.getNazwisko());
        assertEquals("AAA", klient.getAdres());
        assertEquals(123456789123l,klient.getPesel());

    }
    @Test
    public void testFirma(){
        Klient firma= new Klient();

        firma.setFirma(true);
        firma.setNazwaFirmy("asa");
        firma.setNip(1234567891);

        assertEquals(true,firma.isFirma());
        assertEquals("asa", firma.getNazwaFirmy());
        assertEquals(1234567891,firma.getNip());
    }
}