import java.util.Arrays;

public class CalculatorImpl implements Calculator, Util{
    @Override
    public String sum(String a, String b) {
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
    public String sub(String a, String b) {//Complemento a la base y sumamos
        String[] strings = fillToMatch(a, b);
        String bComplement = sum(swap(strings[1]),"1");
        return sum(a,bComplement);
    }

    @Override
    public String mult(String a, String b) {
        return null;
    }

    @Override
    public String div(String a, String b) {
        return null;
    }

    @Override
    public String toHex(String binary) {
        return null;
    }

    @Override
    public String fromHex(String hex) {
        return null;
    }

    @Override
    public String swap(String a) {
        return Arrays.toString(swap(a.toCharArray()));
    }
    private char[] swap(char[] a){
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '0')
                a[i] = '1';
            else
                a[i] = '0';
    }
    return a;
    }
    @Override
    public String[] fillToMatch(String a, String b) {
        if (a.length() > b.length())
            return new String[]{a,fillZeros(b, a.length()-b.length())};
        else if (a.length() < b.length())
            return new String[]{fillZeros(a, b.length()-a.length()),b};
        else return new String[]{a, b};
    }
    private String fillZeros(String a, int length){
        char[] zeros = new char[length - a.length()];
        Arrays.fill(zeros, '0');
        return new String(zeros) + a;
    }

}
