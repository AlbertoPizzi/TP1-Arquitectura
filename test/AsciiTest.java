import ascii_encoder.AsciiImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsciiTest {
    private final AsciiImpl encoder = new AsciiImpl();

    @Test
    public void test001_encoderTestSuccess() {
        assertEquals("65", encoder.encode("01000001"));
        assertEquals("66", encoder.encode("01000010"));
        assertEquals("67", encoder.encode("01000011"));
        assertEquals("68", encoder.encode("01000100"));
        assertEquals("69", encoder.encode("01000101"));

    }
    @Test
    public void test002_EncoderTestSuccessWithFullWord(){
        assertEquals("hello world", encoder.encode("0110100001100101011011000110110001101111001000000111011101101111011100100110110001100100"));
//        assertEquals("87111114108100", encoder.encode("10101111101111111001011011001100100"));
    }
    @Test
    public void test003_DecoderTestSuccess(){
        assertEquals("01000001", encoder.decode("A"));
        assertEquals("01000010", encoder.decode("B"));
        assertEquals("01000011", encoder.decode("C"));
        assertEquals("01000100", encoder.decode("D"));
        assertEquals("01000101", encoder.decode("E"));
    }
    @Test
    public void test004_DecoderTestSuccessWithFullWord(){
        assertEquals("0110100001100101011011000110110001101111001000000111011101101111011100100110110001100100", encoder.decode("hello world"));
        assertEquals("0100100001100101011011000110110001101111001000000101011101101111011100100110110001100100", encoder.decode("Hello World"));

    }

}
