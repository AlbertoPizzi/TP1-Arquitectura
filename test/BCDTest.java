import org.junit.Test;
import bcd_encoder.BCDEncoderImpl;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BCDTest {
    private final BCDEncoderImpl encoder = new BCDEncoderImpl();

    @Test
    public void encodeSuccessTest() {

        assertEquals("0001", encoder.encode(1));

        assertEquals("01001001", encoder.encode(49));

        assertEquals("0000", encoder.encode(0));
    }

    @Test
    public void decodeSuccessTest() {

        assertEquals(1, encoder.decode("0001"));

        assertEquals(49, encoder.decode("01001001"));

        assertEquals(0, encoder.decode("0000"));
    }

    @Test
    public void integerToBinaryTest(){
        BCDEncoderImpl bcdEncoder = new BCDEncoderImpl();
        String result = bcdEncoder.integerToBinary(123);
        assertEquals("1111011", result);
    }
    @Test
    public void decodeContainsANonBinaryCharacterTest(){
        BCDEncoderImpl encoder = new BCDEncoderImpl();
        Exception exception =  assertThrows(InputMismatchException.class, () -> encoder.decode("010000010101101F"));
        assertEquals("Input contains non-digit character", exception.getMessage());
    }

    @Test
    public void lengthIsIncorrectStringDecodeTest(){
        BCDEncoderImpl encoder = new BCDEncoderImpl();
        Exception exception = assertThrows(InputMismatchException.class, () -> encoder.decode("0100000101011"));
        assertEquals("Input length is not a multiple of 4", exception.getMessage());
    }
}

