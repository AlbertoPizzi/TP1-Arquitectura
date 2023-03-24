package tp;

public class BCDEncoderImpl implements BCDEncoder {

    @Override
    public String encode(int a) {
        String decimalStr = String.valueOf(a);
        StringBuilder bcdBuilder = new StringBuilder();

        for (int i = 0; i < decimalStr.length(); i++) {
            int decimalDigit = Character.getNumericValue(decimalStr.charAt(i));

            String bcdDigit = integerToBinary(decimalDigit);
            bcdDigit = "0000" + bcdDigit;
            bcdDigit = bcdDigit.substring(bcdDigit.length() - 4);
            bcdBuilder.append(bcdDigit);
        }
        return bcdBuilder.toString();
    }

    @Override
    public int decode(String a) {
        StringBuilder decimalBuilder = new StringBuilder();

        for (int i = 0; i < a.length(); i += 4) {
            String bcdDigit = a.substring(i, i + 4);
            int decimalDigit = Integer.parseInt(bcdDigit, 2);
            decimalBuilder.append(decimalDigit);
        }
        return Integer.parseInt(decimalBuilder.toString());
    }
    public String integerToBinary(int a) {
        String binary = "";
        while (a > 0) {
            binary = (a % 2) + binary;
            a /= 2;
        }
        return binary;
    }
}