package javacode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class RoutePlanner_X {
	
	/*
    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn,
                                      boolean[][] mapMatrix) {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        if (mapMatrix == null)               	return false;
        if (toRow >= mapMatrix.length)       	return false;
        if (toColumn >= mapMatrix[0].length)	return false;
        if (!mapMatrix[toRow][toColumn])		return false;
        if (fromRow >= mapMatrix.length)       	return false;
        if (fromColumn >= mapMatrix[0].length) 	return false;
    	if (!mapMatrix[fromRow][fromColumn])	return false;
    	
    	if (fromRow == toRow && fromColumn == toColumn) return true;
    	
    	boolean      result = routeExists(fromRow + 1, fromColumn, toRow, toColumn, mapMatrix);
    	if (!result) result = routeExists(fromRow, fromColumn + 1, toRow, toColumn, mapMatrix);

    	return result;
    }
    */
	
	static HashMap<String, ArrayList<String>> graph;

	public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn, boolean[][] mapMatrix) {
	    if(fromRow < 0 || fromColumn < 0 || toRow < 0 || toColumn < 0) {
	        return false;
	    }
	    if(fromRow >= mapMatrix.length || fromColumn >= mapMatrix[0].length || toRow >= mapMatrix.length || toColumn >= mapMatrix[0].length) {
	        return false; 
	    }
	    if(!mapMatrix[fromRow][fromColumn] || !mapMatrix[toRow][toColumn]) {
	        return false;
	    }
	    
	    if(fromRow == toRow && fromColumn == toColumn) {
	        return true;
	    }
	    
	    constructGraph(mapMatrix);
	    return bfs(fromRow + "-" + fromColumn, toRow + "-" + toColumn);
	}


	public static void constructGraph(boolean[][] mapMatrix) {
	    graph = new HashMap<String, ArrayList<String>>();
	    
	    for(int i = 0; i < mapMatrix.length; i++) {
	        for(int j = 0; j < mapMatrix[i].length; j++) {
	            if(!mapMatrix[i][j]) {
	                continue;
	            }
	            String currId = i + "-" + j;
	            if(i-1 >= 0) {
	                if(mapMatrix[i-1][j]) {
	                    addEdge(currId, (i-1) + "-" + j);
	                }
	            }
	            if(i+1 < mapMatrix.length) {
	                if(mapMatrix[i+1][j]) {
	                    addEdge(currId, (i+1) + "-" + j);
	                }
	            }
	            if(j-1 >= 0) {
	                if(mapMatrix[i][j-1]) {
	                    addEdge(currId, i + "-" + (j-1));
	                }
	            }
	            if(j+1 < mapMatrix[i].length) {
	                if(mapMatrix[i][j+1]) {
	                    addEdge(currId, i + "-" + (j+1));
	                }
	            }
	        }
	    }
	}

	public static void addEdge(String from, String to) {
	    if(graph.containsKey(from)) {
	        graph.get(from).add(to);
	    } else {
	        ArrayList<String> neighbour = new ArrayList<String>();
	        neighbour.add(to);
	        graph.put(from, neighbour);
	    }
	}

	public static boolean bfs(String start, String end) {
	    LinkedList<String> queue = new LinkedList<String>();        // FIFO queue for BFS
	    HashSet<String> visited = new HashSet<String>();
	    
	    queue.add(start);
	    visited.add(start);
	    
	    String curr;
	    while(!queue.isEmpty()) {
	        curr = queue.poll();
	        
	        if(curr.equals(end)) {
	            return true;
	        }
	        
	        if(!graph.containsKey(curr)) {
	            return false;
	        }
	        
	        for(String next : graph.get(curr)) {
	            if(!visited.contains(next)) {
	                visited.add(next);
	                queue.add(next);
	            }
	        }
	    }
	    
	    return false;
	}
	
        
    public static void main(String[] args) {
        boolean[][] mapMatrix = {
            {true,  false, false},
            {true,  true,  false},
            {false, true,  true}
        };
        
        System.out.println(routeExists(0, 0, 2, 2, mapMatrix));
    }
}