package com.interviewprep.dsa.binarySearch.bitonicArraySearch;

public class FindPeakElementII {
    public int[] findPeakElementIi(int[][] nums) {
        int m = nums.length, n = nums[0].length;
        int left = 0, right = n - 1;
        while(left<=right){
            int mid = left + (right - left)/2;
            int maxRow = 0;
            for (int i=0; i<m; i++){
                if (nums[i][mid] > nums[maxRow][mid]) maxRow = i;
            }

            boolean isLeftHigher = mid-1>=0 && nums[maxRow][mid] < nums[maxRow][mid-1];
            boolean isRightHigher = mid+1<n && nums[maxRow][mid] < nums[maxRow][mid+1];
            if(!isLeftHigher && !isRightHigher){
                return new int[]{maxRow, mid};
            } else if (isLeftHigher){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return new int[]{-1,-1};
    }
}
