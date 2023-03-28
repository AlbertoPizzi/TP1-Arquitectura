package bcd_encoder;

public interface BCDEncoder {
    String encode(int a);
    int decode(String a);
}