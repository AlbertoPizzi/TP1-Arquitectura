import org.junit.Test;
import tp.*;
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
}
