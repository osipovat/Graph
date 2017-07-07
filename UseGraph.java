package exercise2;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UseGraph {

	/**
	 * @param args
	 * @throws VertexExistsException
	 * @throws GraphIsFullException
	 */

	// Please complete the code for the following method.
	// A correctly working method gets up to 5 marks
	// A quality implementation gets up to 3 marks
	static Graph<String> loadGraph(String pathName)
			throws FileNotFoundException, GraphIsFullException,
			VertexExistsException
	// Loads a String graph from a text file formatted as follows:
	// N: some number -> max number of vertices
	// V: vertexname -> at most N lines for this format
	// E: vertex1, vertex2 -> each line gives an edge from vertex1 to vertex2
	// (vertex1, vertex2 must be strings)
	// Example:
	// N: 5
	// V: A
	// V: B
	// V: C
	// V: D
	// E: A,B
	// E: A,C
	// E: C,D
	// The loadGraph must create the following graph:
	//
	// A --- B
	// |
	// |
	// C --- D
	//
	// If the text file is empty or contains at least the N: ... line, the graph
	// must be empty (not null!)
	//
	{
		Graph<String> g = new Graph<String>();
		Scanner getData = new Scanner(new File(pathName));
		while(getData.hasNextLine()) {
			String lineline = getData.nextLine();
			String line = lineline.replaceAll("\\s","");
			if (line.substring(0, 1).equals("N")) 
				g.setMaxVertices(Integer.parseInt(line.substring(line.length() - 1)));
			if (line.substring(0, 1).equals("V")) 
				g.addVertex(line.substring(line.length() - 1));
			if (line.substring(0, 1).equals("E"))
				if (line.length() != 3)
					if (line.substring(2, 3).equals(line.substring(4, 5)))
						g.addEdge(line.substring(line.length() - 1), line.substring(line.length() - 3, line.length() - 2));	
		}
		getData.close();
		return g;
	}

	public static void main(String[] args) throws FileNotFoundException,
			GraphIsFullException, VertexExistsException {
		// TODO Auto-generated method stub
		String pathname = args[0];
		Graph<String> g1 = loadGraph(pathname);
		System.out.println(g1.connectedComponents());
		
		
	}

}
