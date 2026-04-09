package com.interviewprep.dsa.strings.slidingWindow;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0, maxCount=0, longest=0;
        for(int right=0; right<s.length(); right++){
            maxCount = Math.max(maxCount, ++freq[s.charAt(right)-'A']);
            while((right - left + 1) - maxCount > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LongestRepeatingCharacterReplacement();
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement("AABABBA", 1));
    }
}
