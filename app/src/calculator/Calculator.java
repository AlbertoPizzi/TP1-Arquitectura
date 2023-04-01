package calculator;
public interface Calculator {
    String sum(String a, String b);
    String sub(String a, String b);
    String mult(String a, String b);
    String div(String dividend, String divisor);
    String toHex(String binary);
    String fromHex(String hex);
}
