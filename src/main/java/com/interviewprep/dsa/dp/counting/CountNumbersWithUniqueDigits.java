package com.interviewprep.dsa.dp.counting;

//https://leetcode.com/problems/count-numbers-with-unique-digits/description/
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        // return countNumbersWithUniqueDigitsRec(n);

        return countNumbersWithUniqueDigitsOptimal(n);
    }

    //recursive
    private int countNumbersWithUniqueDigitsRec(int n){
        if(n == 0) return 1;
        int limit = (int) Math.pow(10,n);
        int count = 0;
        for(int i=0; i<limit; i++){
            if(hasUniqueDigits(i)) count++;
        }
        return count;
    }

    private boolean hasUniqueDigits(int num){
        boolean[] seen = new boolean[10];

        if(num == 0) return true;

        while(num>0){
            int digit = num%10;
            if(seen[digit]) return false;
            seen[digit] = true;
            num = num/10;
        }

        return true;
    }

    private int countNumbersWithUniqueDigitsOptimal(int n) {
        if(n == 0) return 1;
        int result = 10; //total no of digits
        int uniqueDigits = 9;   //first digit cannot be zero 1-9
        int availableDigits = 9;    // Remaining choices for next position
        for(int i=2; i<=n && availableDigits>0; i++){
            uniqueDigits *= availableDigits;
            result += uniqueDigits;
            availableDigits--;
        }
        return result;
    }
}
