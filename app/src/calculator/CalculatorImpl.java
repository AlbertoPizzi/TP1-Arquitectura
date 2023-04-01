package calculator;

import util.BinaryChecker;
import util.BinaryManipulator;

public class CalculatorImpl implements Calculator, BinaryManipulator {
    public CalculatorImpl(){}
    @Override
    public String sum(String a, String b){
        BinaryChecker.checkIsBinaryNumber(a);
        BinaryChecker.checkIsBinaryNumber(b);
        String[] strings = fillToMatch(a, b);
        char[] a1 = strings[0].toCharArray();
        char[] b1 = strings[1].toCharArray();
        char[] result = new char[a1.length];
        char carry = '0';
        for (int i = a1.length - 1; i >= 0; i--) {
            if (a1[i] == '0' && b1[i] == '0'){
                result[i] = carry;
                carry = '0';
            }
            else if (a1[i] == '1' && b1[i] == '1'){
                result[i] = carry;
                carry = '1';
            }
            else if (a1[i] == '1' && b1[i] == '0' || a1[i] == '0' && b1[i] == '1'){
                if (carry == '0')
                    result[i] = '1';
                else
                    result[i] = '0';
            }
        }
        return new String(result);
    }

    @Override
    public String sub(String a, String b){
        //subtract using complement
        BinaryChecker.checkIsBinaryNumber(a);
        BinaryChecker.checkIsBinaryNumber(b);

        String[] filled = fillToMatch(a, b);
        a = filled[0];
        b = filled[1];
        if (b.length() > a.length() || (b.length() == a.length() && b.compareTo(a) > 0)) {throw new IllegalArgumentException("Subtraction cannot result in a negative value");}
        b = complement(b);
        String result = sum(a, b);

        if (result.length() > a.length()) {
            result = result.substring(1);
        }
        return result;
    }

    @Override
    public String mult(String a, String b) {
        BinaryChecker.checkIsBinaryNumber(a);
        BinaryChecker.checkIsBinaryNumber(b);
        String[] filled = fillToMatch(a, b);
        a = filled[0];
        b = filled[1];

        int m = a.length(), n = b.length();
        int[] position = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (a.charAt(i) - '0') * (b.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + position[p2];
                position[p1] += sum / 10;
                position[p2] = (sum) % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : position) if (!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }


    @Override
    public String div(String a, String b) {
        BinaryChecker.checkIsBinaryNumber(a);
        BinaryChecker.checkIsBinaryNumber(b);
        if (b.length() > a.length() || (b.length() == a.length() && b.compareTo(a) > 0)) {
            throw new IllegalArgumentException("Division cannot result in a negative value");
        }
        if (b.equals("0")) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        String result = "";
        String temp_a = a.substring(0, b.length());

        for (int i = b.length(); i < a.length() + 1; i++) {
            if (binaryToDecimal(temp_a) >=  binaryToDecimal(b)) {
                temp_a = sub(temp_a, b);
                temp_a = temp_a.substring(1);
                if (i < a.length()) {temp_a = temp_a + a.charAt(i);}
                result = result + "1";
            } else {
                if (i < a.length()) {temp_a = temp_a + a.charAt(i);}
                result = result + "0";
            }
        }
        return result;
    }

    private int binaryToDecimal(String binary){
        int result = 0;
        int power = 1;
        for (int i = binary.length() -1; i >= 0; i--) {
            char c = binary.charAt(i);

            if (c == '1') {
                result += power;
            }

            power *= 2;
        }
        return result;
    }

    private char decimalToHexadecimal(int decimal) {
        if (decimal >= 0 && decimal <= 9) {
            return (char) ('0' + decimal);
        } else {
            return (char) ('A' + (decimal - 10));
        }
    }

    @Override
    public String toHex(String binary) {
        //this pads the binary string with zeros on the left until its length is a multiple of 4
        while (binary.length() % 4 != 0) {
            binary = "0" + binary;
        }

        StringBuilder hexBuilder = new StringBuilder();

        //loops through the binary string in groups of 4 digits
        for (int i = 0; i < binary.length(); i += 4) {
            String binaryDigit = binary.substring(i, i + 4);
            int decimalValue = binaryToDecimal(binaryDigit);
            char hexDigit = decimalToHexadecimal(decimalValue);
            hexBuilder.append(hexDigit);
        }

        return hexBuilder.toString();
    }

    @Override
    public String fromHex(String hex) {
        StringBuilder binaryBuilder = new StringBuilder();
        //loops through each hexadecimal digit and convert it to its corresponding binary value
        for (int i = 0; i < hex.length(); i++) {
            char hexDigit = hex.charAt(i);
            String binaryDigit = hexDigitToBinary(hexDigit);
            binaryBuilder.append(binaryDigit);
        }
        return binaryBuilder.toString();
    }
    private String hexDigitToBinary(char hexDigit) {
        int decimalValue;

        if (hexDigit >= '0' && hexDigit <= '9') {
            decimalValue = hexDigit - '0';
        } else if (hexDigit >= 'A' && hexDigit <= 'F') {
            decimalValue = hexDigit - 'A' + 10;
        } else if (hexDigit >= 'a' && hexDigit <= 'f') {
            decimalValue = hexDigit - 'a' + 10;
        } else {
            throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexDigit);
        }
        //converts the decimal value to a binary string with leading zeros
        String binaryString = decimalToBinary(decimalValue);
        int numLeadingZeros = 4 - binaryString.length();
        StringBuilder binaryBuilder = new StringBuilder();

        for (int i = 0; i < numLeadingZeros; i++) {
            binaryBuilder.append('0');
        }

        binaryBuilder.append(binaryString);
        return binaryBuilder.toString();
    }

    private String decimalToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder binaryBuilder = new StringBuilder();

        while (decimal > 0) {
            int remainder = decimal % 2;
            binaryBuilder.insert(0, remainder);
            decimal /= 2;
        }

        return binaryBuilder.toString();
    }
    @Override
    public String complement(String a){
        BinaryChecker.checkIsBinaryNumber(a);
        String result = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '0') {
                result += "1";
            } else {
                result += "0";
            }
        }
        return sum(result, "1");
    }
    @Override
    public  String[] fillToMatch(String a, String b){
        BinaryChecker.checkIsBinaryNumber(a);
        BinaryChecker.checkIsBinaryNumber(b);
        int maxLength = Math.max(a.length(), b.length());
        StringBuilder aBuilder = new StringBuilder(a);
        while (aBuilder.length() < maxLength) {
            aBuilder.insert(0, "0");
        }
        a = aBuilder.toString();
        StringBuilder bBuilder = new StringBuilder(b);
        while (bBuilder.length() < maxLength) {
            bBuilder.insert(0, "0");
        }
        b = bBuilder.toString();
        return new String[]{a, b};
    }

}
