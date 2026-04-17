package com.interviewprep.dsa.strings.regularExpressions;

//https://leetcode.com/problems/validate-ip-address/description/
public class ValidateIPAddress {
    public String validIPAddress(String queryIP) {
        if(checkIfIPv4(queryIP)) return "IPv4";
        if (checkIfIPv6(queryIP)) return "IPv6";
        return "Neither";
    }

    private boolean checkIfIPv4(String queryIP){
        String[] nums = queryIP.split("\\.",-1);
        if(nums.length != 4) return false;
        for(int i=0; i<nums.length; i++){
            if (nums[i].length()>1 && nums[i].charAt(0) == '0') return false;

            try{
                Integer n = Integer.valueOf(nums[i]);
                if(n<0 || n>255) return false;
            } catch(Exception e){
                return false;
            }
        }
        return true;
    }

    private boolean checkIfIPv6(String queryIP){
        String[] nums = queryIP.split("\\:",-1);
        if(nums.length != 8) return false;
        for (String num : nums) {
            if (num.isEmpty() || num.length() > 4) return false;
            for (char c : num.toCharArray()) {
                if (!isHexDigit(c)) return false;
            }
        }
        return true;
    }

    private boolean isHexDigit(char c) {
        return (c>='a' && c<='f') || (c>='A' && c<='F') || (c>='0' && c<='9');
    }
}
