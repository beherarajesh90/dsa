package com.interviewprep.dsa.strings.stringManipulation;

public class CountAndSay {
    public String countAndSay1(int n) {
        String result = "1";
        for(int k=1; k<n; k++){
            StringBuilder str = new StringBuilder();
            int count = 1;
            for (int i = 1; i<result.length(); i++){
                if (result.charAt(i) == result.charAt(i-1)){
                    count++;
                } else {
                    str.append(count).append(result.charAt(i-1));
                    count = 1;
                }
            }
            str.append(count).append(result.charAt(result.length() - 1));
            result = str.toString();
        }
        return result;
    }

    public String countAndSay2(int n) {
        String result = "1";
        for(int k=1; k<n; k++){
            StringBuilder str = new StringBuilder();
            int count = 1;
            for (int i = 1; i<result.length(); i++){
                if (result.charAt(i) == result.charAt(i-1)){
                    count++;
                } else {
                    str.append(count).append(result.charAt(i-1));
                    count = 1;
                }
            }
            str.append(count).append(result.charAt(result.length() - 1));
            result = str.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay1(4));
    }
}
