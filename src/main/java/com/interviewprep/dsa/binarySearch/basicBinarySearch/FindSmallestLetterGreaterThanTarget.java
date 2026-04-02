package com.interviewprep.dsa.binarySearch.basicBinarySearch;

public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int left=0, right=letters.length-1;
        char result=letters[0];
        while (left<=right){
            int mid = (left+right)/2;
            if(letters[mid]>target){
                right = mid-1;
                result=letters[mid];
            } else{
                left = mid+1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[] chars = {'c','f','j'};
        System.out.println(new FindSmallestLetterGreaterThanTarget().nextGreatestLetter(chars, 'c'));
    }
}
