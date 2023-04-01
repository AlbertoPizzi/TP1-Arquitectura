package ascii_encoder;



public class AsciiImpl implements AsciiEncoder{
    @Override
    public String encode(String binary) {
         StringBuilder encodedMessage = new StringBuilder();
         int length = binary.length();
         if(length % 8 != 0) {
             throw new IllegalArgumentException("Binary string must be a multiple of 8");
         }
        for (int i = 0; i < length  ; i+= 8) {
            String l = binary.substring(i, i + 8).toUpperCase();
            int charCode = Integer.parseInt(l, 2);
            encodedMessage.append((char) charCode);
        }
        return encodedMessage.toString();
    }

    @Override
    public String decode(String ascii) {
        StringBuilder decodedMessage = new StringBuilder();
        for (char c : ascii.toCharArray()) {
            decodedMessage.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return decodedMessage.toString();
    }
}
