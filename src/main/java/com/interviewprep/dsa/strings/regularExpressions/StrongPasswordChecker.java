package com.interviewprep.dsa.strings.regularExpressions;

public class StrongPasswordChecker {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false;

        // Check character types
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }

        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        // Case 1: too short
        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        }

        // Count repeating sequences
        int replace = 0;
        int[] mod = new int[3];

        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && password.charAt(j) == password.charAt(i)) {
                j++;
            }

            int len = j - i;
            if (len >= 3) {
                replace += len / 3;
                mod[len % 3]++;
            }

            i = j;
        }

        // Case 2: length in [6, 20]
        if (n <= 20) {
            return Math.max(missingTypes, replace);
        }

        // Case 3: too long, need deletions
        int delete = n - 20;

        // Use deletions to reduce replacements
        int use = Math.min(delete, mod[0]);
        replace -= use;
        delete -= use;

        use = Math.min(delete / 2, mod[1]);
        replace -= use;
        delete -= use * 2;

        // For remaining runs, every 3 deletions reduce 1 replacement
        replace -= delete / 3;

        return (n - 20) + Math.max(missingTypes, replace);
    }
}
