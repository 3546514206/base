package edu.zjnu.arithmetic.sword2offer;


/**
 *     11011010
 * 100010101101
 * 100110000111
 */
public class AddBinary {

    public static void main(String[] args) {
        String test = (new AddBinary()).addBinary("100010101101", "11011010");
        System.out.println(test);
    }

    private String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();

        int i = a.length() - 1;
        int j = b.length() - 1;

        int carry = 0;

        while (i >= 0 || j >= 0) {
            int digitA = 0;
            int digitB = 0;
            if (i >= 0) {
                digitA = a.charAt(i) - '0';
                i--;
            }

            if (j >= 0) {
                digitB = b.charAt(j) - '0';
                j--;
            }

            int sum = digitA + digitB + carry;

            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            result.append(sum);

        }

        if (carry == 1) {
            result.append(1);
        }


        return result.reverse().toString();
    }


}
