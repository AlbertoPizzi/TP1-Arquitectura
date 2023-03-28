package util;

import java.util.InputMismatchException;

public class BinaryChecker {
    public static void checkIsBinaryNumber(String binary){
        for (char c : binary.toCharArray()) {
            if (!(c == '0' || c == '1')) throw new InputMismatchException("Input is not a binary number");
        }
    }
    public static void validateInputForEncoding(int a) {
        if (a < 0) {
            throw new InputMismatchException("Input must be a non-negative integer");
        }
        int tmp = a;
        while (tmp > 0) {tmp /= 10;}
        }
        public static void validateInputForDecoding(String a) {
            if (a == null || a.isEmpty()) {
                throw new InputMismatchException("Input cannot be null or empty");
            }
            for (char c : a.toCharArray()) {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException("Input contains non-digit character");
                }
                if (a.length() % 4 != 0) throw new InputMismatchException("Input length is not a multiple of 4");
            }
        }
    }
