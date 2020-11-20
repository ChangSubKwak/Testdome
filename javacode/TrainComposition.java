package javacode;
import java.util.ArrayDeque;
import java.util.Deque;

public class TrainComposition {
	Deque<Integer> dq = new ArrayDeque<>();
	
    public void attachWagonFromLeft(int wagonId) {
    	dq.offerFirst(wagonId);
//        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public void attachWagonFromRight(int wagonId) {
    	dq.offerLast(wagonId);
//        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public int detachWagonFromLeft() {
    	return dq.pollFirst();
//        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public int detachWagonFromRight() {
    	return dq.pollLast();
//        throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static void main(String[] args) {
        TrainComposition train = new TrainComposition();
        train.attachWagonFromLeft(7);
        train.attachWagonFromLeft(13);
        System.out.println(train.detachWagonFromRight()); // 7 
        System.out.println(train.detachWagonFromLeft()); // 13
    }
}
