package com.interviewprep.dsa.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(0, 0, "", n, result);
        return result;
    }

    private void backtrack(int openPCount, int closePCount, String str, int n, List<String> result){
        if(openPCount == closePCount && openPCount+closePCount == 2 * n){
            result.add(str);
            return;
        }

        if(openPCount < n){
            backtrack(openPCount + 1, closePCount, str+"(", n , result);
        }

        if(closePCount < openPCount){
            backtrack(openPCount, closePCount+1, str + ")", n , result);
        }
    }

    //solution using string builder
    List<String> res;

    public List<String> generateParenthesisUsingStringBuilder(int n) {
        res = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(""));
        return res;
    }

    public void backtrack(int n, int i, int j, StringBuilder sb) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }
        if (i < n) {
            sb.append("(");
            backtrack(n, i + 1, j, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (j < i) {
            sb.append(")");
            backtrack(n, i, j + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


//    Every time you do this: sr + "("
//
//    A new String object is created
//    Old string remains unchanged (because String is immutable)
//    This leads to:
//    More memory usage
//    More object creation
//    Slower performance (especially for large n)

    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();
        List<String> result = solution.generateParenthesisUsingStringBuilder(2);
        System.out.println(result);
    }
}
