package exercise2;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import stack.*;

public class Graph<T> implements GraphInterface<T> {
	public static final int DEF_CAPACITY = 10;
	public static final int NULL_EDGE = 0;
	public static final int DEFAULT_WEIGHT = 1;
	private int numVertices;
	private int maxVertices;
	private T[] vertices;
	private int[][] edges;
	private boolean[] marks; // marks[i] is mark for vertices[i]

	public Graph() {
		numVertices = 0;
		maxVertices = DEF_CAPACITY;
		vertices = (T[]) new Object[DEF_CAPACITY];
		marks = new boolean[DEF_CAPACITY];
		edges = new int[DEF_CAPACITY][DEF_CAPACITY];
	}
	
	public Graph(int maxV)
	// Instantiates a graph with capacity maxV.
	{
		numVertices = 0;
		maxVertices = maxV;
		vertices = (T[]) new Object[maxV];
		marks = new boolean[maxV];
		edges = new int[maxV][maxV];
	}

	public int getMaxVertices() {
		return maxVertices;
	}

	public void setMaxVertices(int maxVertices) {
		this.maxVertices = maxVertices;
	}

	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public T[] getVertices() {
		return vertices;
	}

	public void setVertices(T[] vertices) {
		this.vertices = vertices;
	} 
	
	public int[][] getEdges() {
		return edges;
	}

	public void setEdges(int[][] edges) {
		this.edges = edges;
	} 
	
	public boolean[] getMarks() {
		return marks;
	}

	public void setMarks(boolean[] marks) {
		this.marks = marks;
	} 
	public boolean isEmpty()
	// Returns true if this graph is empty; otherwise, returns false.
	{
		return (numVertices == 0);
	}

	public boolean isFull()
	// Returns true if this graph is full; otherwise, returns false.
	{
		return (numVertices == maxVertices);
	}
	public boolean inArray(T vertex, T[] array) {
		for (T i : array) {
			if (i.equals(vertex))
				return true;
		}
		return false;
	}
	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	// A quality implementation gets up to 2 marks
	public void addVertex(T vertex) throws GraphIsFullException,
			VertexExistsException {
		// If graph is full, it throws GraphIsFullException
		// If vertex is already in this graph, it throws VertexExistsException
		// Otherwise adds vertex to this graph.
		//
		
		if (this.isFull()) 
			throw new GraphIsFullException();
		if (inArray(vertex, this.vertices))
			throw new VertexExistsException();
			
		else
			if (vertex != null)
				this.vertices = addElementVertex(this.vertices, vertex);
				this.edges = addElementEdge(this.edges);
				this.numVertices += 1;
	}

	public T[] addElementVertex(T[] array, T elem) {
	    array  = Arrays.copyOf(array, array.length + 1);
	    array[array.length - 1] = elem;
	    return array;
	}
	public int[][] addElementEdge(int[][] array) {
	    array  = Arrays.copyOf(array, array.length + 1);
	    array[array.length - 1] = new int[array.length - 1];
	    int i = 0;
	    while (i < this.numVertices) {
	    	array[array.length - 1][i] = 0;
	    	i += 1;
	    }
	    int m = 0;
	    while (m < array.length) {
	    	array[m] = Arrays.copyOf(array[m], array[m].length + 1);
	    	array[m][array[m].length - 1] = 0;
	    	m += 1;
	    }
	    return array;
	}
	
	public String toStringVertex() {
		String result = "";
		for (T i : this.vertices) {
			result += i + ", ";
		}
		return result;
	}
	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	// A quality implementation gets up to 2 marks
	public void addEdge(T fromVertex, T toVertex)
	// Adds an edge with the specified weight from fromVertex to toVertex.
	{
		int i = 0;
		int fromVertexIndex = 0;
		int toVertexIndex = 0;
		if (fromVertex.equals(toVertex) == false)
			if (fromVertex != null) 
				if (toVertex != null)
					while (i < this.numVertices) {
						
						if (this.vertices[i].equals(fromVertex))
							fromVertexIndex = i;
						
						if (this.vertices[i].equals(toVertex))
							
							toVertexIndex = i;
						
						i += 1;
					}
					if (fromVertexIndex != toVertexIndex)
						this.edges[toVertexIndex][fromVertexIndex] = 1;
         				this.edges[fromVertexIndex][toVertexIndex] = 1;
	}
	
	
	public String toStringEdge() {
		String result = "";
		for (int[] i : this.edges) {
			for (int j : i) {
				result += j + ", ";
			}
			result += "\n";
		}
		return result;
	}
	
	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks

	public Queue<T> getToVertices(T vertex)
	// Returns a queue of the vertices that are adjacent from vertex.
	{
		int i = 0;
		int vertexIndex = -1;
		
		while (i < this.numVertices) {
		
			if (this.vertices[i].equals(vertex))
				
       	 		vertexIndex = i;
			i += 1;
        }
		int j = 0;
		List<Integer> l = new ArrayList<>();
		while (j < this.numVertices) {
        	if (vertexIndex != -1)
        		if (this.edges[vertexIndex][j] == 1)
        			l.add(j);
        	j += 1;
        }
        Queue<T> queue = new LinkedList<>();
        for (int z : l) {
        	queue.add(this.vertices[z]);
        }
        return queue;
	}

	public void clearMarks()
	// Sets marks for all vertices to false.
	{
		for (int i = 0; i < numVertices; i++)
			marks[i] = false;
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 1 mark

	public void markVertex(T vertex)
	// Sets mark for vertex to true.
	{
		int i = 0;
		while (i < this.numVertices) {
       	 	if (this.vertices[i].equals(vertex))
       	 		marks[i] = true;
       	 	i += 1;
		}
	}

	public String toStringMark() {
		String result = "";
		for (boolean i : this.marks) {
			result += i + ", ";
		}
		return result;
	}
	
	// Please complete the code for the following method.
	// A correctly working method gets up to 1 mark

	public boolean isMarked(T vertex)
	// Returns true if vertex is marked; otherwise, returns false.
	{
		int i = 0;
		while (i < this.numVertices) {
       	 	if (this.vertices[i].equals(vertex))
       	 		if (marks[i] == true) 
       	 			return true;
       	 	i += 1;
		}
		return false;
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 5 marks
	// A quality implementation gets up to 3 marks
	private Set<T> DFSVisit(T startVertex)
	// Uses depth-first search algorithm to visit as much vertices as
	// possible
	// starting from startVertex.
	// In the process, keeps track of all vertices reachable from
	// startVertex
	// by adding them to a Set<T> variable.
	// Once done, returns the Set<T> that we build up.
	//
	{
		Set<T> s = new HashSet<>();
		Stack<T> st = new Stack<>();
		st.push(startVertex);
		T cur;
		while (st.isEmpty() == false) {
			cur = st.pop();
			s.add(cur);
			
			markVertex(cur);
			if (getToVertices(cur).isEmpty() == false) 
				for (T i : getToVertices(cur)) {
					if (isMarked(i) == false)
						
						st.push(i);
				}	
		}
		return s;
	}

	public boolean inSet(T vertex, Set<T> set) {
		for (T i : set) {
			if (i.equals(vertex))
				return true;
		}
		return false;
	}
	
	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	// A quality implementation gets up to 2 marks
	public ArrayList<Set<T>> connectedComponents()
	// Returns a list of connected components of the graph
	// For each vertex that does not belong to the connected components
	// already on the list,
	// call DFSVisit to obtain a set of vertices connected to the current
	// vertex
	// Add the set to the list
	{
		ArrayList<Set<T>> list = new ArrayList<Set<T>>();
		for (T v : this.vertices) {
			
			Set<T> set = new HashSet<>();
			for (Set<T> s : list) {
				for (T k : s) {
					set.add(k);
				}
				
			}
			Set<T> m = new HashSet<T>();
			if (inSet(v, set) == false)
				m = DFSVisit(v);
				list.add(m);
		}
		ArrayList<Set<T>> list2 = new ArrayList<Set<T>>();
		for (Set<T> i : list) {
			if (i.isEmpty() == false)
				list2.add(i);
		}
		return list2;
	}

}
