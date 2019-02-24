import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PeselValidatorTest {

    @Test
    public void testPoprawnyPesel() {
        //given
        List<Long> pesele = new ArrayList<Long>();
        pesele.add(85061148788l);
        pesele.add(77111051896l);
        pesele.add(70010227544l);
        pesele.add(50063076869l);
        pesele.add(76091435647l);
        pesele.add(52121397626l);
        pesele.add(53031731847l);
        pesele.add(85050966832l);
        //when

        for (Long aLong : pesele) {
            boolean valid = PeselValidator.valid(aLong);
            assertTrue(valid);
        }

    }

    @Test
    public void testZlyPesel() {
        //given
        long pesel = 92022673119l;
        //when
        boolean valid = PeselValidator.valid(pesel);
        //then
        assertFalse(valid);
    }
}