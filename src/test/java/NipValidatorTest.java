import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NipValidatorTest {

    @Test
    public void testDobryNip() {

        List<Long> nip = new ArrayList<Long>();
        nip.add(1110790274l);
        nip.add(9327887314l);
        nip.add(5242279480l);
        nip.add(1085688586l);
        nip.add(7749194667l);
        nip.add(9714238729l);

        for (Long aLong : nip) {
            boolean valid = NipValidator.valid(aLong);
            assertTrue(valid);

        }
    }
}