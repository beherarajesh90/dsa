package com.interviewprep.dsa.recursion.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<Character, String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        backtrack(map, result, digits, new StringBuilder(""),0);
        return result;
    }

    private void backtrack(Map<Character, String> map, List<String> result, String digits, StringBuilder curStr, int curInd ) {
        if (curInd == digits.length()){
            result.add(curStr.toString());
            return;
        }
        String letters = map.get(digits.charAt(curInd));
        for(char ch: letters.toCharArray()){
            curStr.append(ch);
            backtrack(map, result, digits, curStr, curInd+1);
            curStr.deleteCharAt(curStr.length()-1);
        }
    }
}
