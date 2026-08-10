package com.interviewprep.dsa.strings.regularExpressions;

//https://leetcode.com/problems/valid-number/description/
public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null) return false;

        s = s.trim();
        if (s.length() == 0) return false;

        int eIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (eIndex != -1) return false; // more than one e
                eIndex = i;
            }
        }

        if (eIndex != -1) {
            String left = s.substring(0, eIndex);
            String right = s.substring(eIndex + 1);

            return isDecimal(left) && isInteger(right);
        } else {
            return isDecimal(s);
        }
    }

    private boolean isInteger(String s) {
        if (s.length() == 0) return false;

        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }

        if (i == s.length()) return false;

        for (; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private boolean isDecimal(String s) {
        if (s.length() == 0) return false;

        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }

        boolean seenDigit = false;
        boolean seenDot = false;

        for (; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '.') {
                if (seenDot) return false;
                seenDot = true;
            } else {
                return false;
            }
        }

        return seenDigit;
    }
}
