import calculator.CalculatorImpl;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {

    @Test
    public void sumTest(){
        CalculatorImpl calculator = new CalculatorImpl();
        String result = calculator.sum("00111","1001000"); // 7 + 72 = 79
        assert result.equals("1001111");
    }
    @Test
    public void subTest(){
        CalculatorImpl calculator = new CalculatorImpl();
        String result = calculator.sub("1001000","00111"); // 72 - 7 = 65
        assert result.equals("1000001");
    }
    @Test
    public void sumNonBinaryInputTest(){
        CalculatorImpl calculator = new CalculatorImpl();
        Exception exception =  assertThrows(InputMismatchException.class, () -> calculator.sum("00113","1001000"));
        assertEquals("Input is not a binary number", exception.getMessage());
    }
    @Test
    public void subNonBinaryInputTest(){
        CalculatorImpl calculator = new CalculatorImpl();
        Exception exception =  assertThrows(InputMismatchException.class, () -> calculator.sub("00113","1001000"));
        assertEquals("Input is not a binary number", exception.getMessage());
    }
    @Test
    public void subIsNegativeTest(){
        CalculatorImpl calculator = new CalculatorImpl();
        Exception exception =  assertThrows(IllegalArgumentException.class, () -> calculator.sub("00111","1001000"));
        assertEquals("Subtraction cannot result in a negative value", exception.getMessage());
    }
    @Test
    public void subIsNegativeDifferentLengthTest(){
        CalculatorImpl calculator = new CalculatorImpl();
        Exception exception =  assertThrows(IllegalArgumentException.class, () -> calculator.sub("00000000111","1001000"));
        assertEquals("Subtraction cannot result in a negative value", exception.getMessage());
    }
}
