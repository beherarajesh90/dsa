package com.interviewprep.dsa.binarySearch.allocationProblems;

//https://www.propeers.in/roadmaps/69692150442404dc257236a1/allocate-minimum-number-of-pages?todoItemId=69b4767d8789fde24cfdc7e7
public class AllocateMinimumNumberOfPages {
    public int allocateMinimumNumberOfPages(int[] nums, int k) {
        int minPages = 0, maxPages = 0, result = -1;
        for(int num: nums){
            minPages = Math.max(minPages, num);
            maxPages += num;
        }
        while(minPages <= maxPages){
            int mid = minPages + (maxPages - minPages)/2;
            if(canAllocate(nums, mid, k)){
                result = mid;
                maxPages = mid - 1;
            } else{
                minPages = mid + 1;
            }
        }
        return result;
    }

    private boolean canAllocate(int[] nums, int maxPages, int k){
        int students = 1, totalPages = 0;
        for(int pages: nums){
            if (totalPages + pages > maxPages){
                students++;
                totalPages = pages;
                if (students > k) return false;
            } else{
                totalPages += pages;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AllocateMinimumNumberOfPages obj = new AllocateMinimumNumberOfPages();
        int[] nums = {12, 34, 67, 90};
        int k = 2;
        System.out.println(obj.allocateMinimumNumberOfPages(nums, k));
    }
}
