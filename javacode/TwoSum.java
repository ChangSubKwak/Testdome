package javacode;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {
    	int len = list.length;
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0 ; i < len ; i++) {
    		if (map.containsKey(sum - list[i] ))
   				return new int[] {map.get(sum-list[i]), i};
    		map.put(list[i], i);
    	}
    	return null;
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[] { 3, 1, 5, 7, 5, 9 }, 10);
//    	int[] indices = findTwoSum(new int[] { 3, 5, 5, 2, 5, 9 }, 10);
        if(indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        }
    }
}
