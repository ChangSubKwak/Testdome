package javacode;
public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
    	if (sortedArray == null || sortedArray.length == 0)
    		return -1;
    	
        int len = sortedArray.length;
        if (sortedArray[len - 1] < lessThan)
        	return len;
        
        int low = 0, high = len - 1, mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (sortedArray[mid] == lessThan) return mid;
            else if (sortedArray[mid] > lessThan) {
            	if (mid >= 1 && lessThan > sortedArray[mid - 1])
            		return mid;
            	else
            		high = mid - 1;
            }
            else {
            	if (mid <= len - 1 && lessThan <= sortedArray[mid + 1])
            		return mid + 1;
            	else 
            		low  = mid + 1;
            }
        }
        //if (mid == len || sortedArray[mid] != lessThan)
        //if (mid == len) return len;
        //if (sortedArray[mid] == lessThan) return mid + 1;
        
        return -1;
    }
    
    public static void main(String[] args) {
    	System.out.println(SortedSearch.countNumbers(new int[] { 2 }, 2));
    	System.out.println(SortedSearch.countNumbers(new int[] { 1 }, 2));
    	System.out.println(SortedSearch.countNumbers(new int[] { 1, 2 }, 2));
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
    }
}
