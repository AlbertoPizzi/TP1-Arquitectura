import org.junit.Test;
import tp.BCDEncoderImpl;

import static org.junit.Assert.assertEquals;

public class BCDTest {
    private final BCDEncoderImpl encoder = new BCDEncoderImpl();

    @Test
    public void testEncode() {

        assertEquals("0001", encoder.encode(1));

        assertEquals("01001001", encoder.encode(49));

        assertEquals("0000", encoder.encode(0));
    }

    @Test
    public void testDecode() {

        assertEquals(1, encoder.decode("0001"));

        assertEquals(49, encoder.decode("01001001"));

        assertEquals(0, encoder.decode("0000"));
    }

    @Test
    public void integerToBinary(){
        BCDEncoderImpl bcdEncoder = new BCDEncoderImpl();
        String result = bcdEncoder.integerToBinary(123);
        assertEquals("1111011", result);
    }
}
