import java.util.ArrayList;

/**
 * @author Andrew
 *
 */
public class Test {

	/**
	 * @param input
	 * @return an UWG that is a Minimal Spanning Tree (MST) of the input graph
	 */
	public static UWG Prim(UWG input)
	{
		UWG mst = new UWG(input.getNumVertices(),input.getNumEdges());
		ArrayList<String> vertices = new ArrayList<String>();
		Edge tempEdge = new Edge(0,null,null);
		String tempAdder = null;
		
		mst.setVertices(input.getVertices());
		mst.setFirstFreeVertex(input.getFirstFreeVertex());
		while(true)
		{
			tempEdge = input.findMinEdge(vertices);
			if(tempEdge != null)
			{
				mst.insertEdge(tempEdge.getWeight(), tempEdge.getFromVertex(), tempEdge.getToVertex());
				if(vertices.isEmpty())
				{
					vertices.add(tempEdge.getFromVertex());
					vertices.add(tempEdge.getToVertex());
				}else{
					for(String vertice: vertices)
					{
						if(vertice != tempEdge.getFromVertex())
						{
							tempAdder = tempEdge.getFromVertex();
						}
						if(vertice != tempEdge.getToVertex())
						{
							tempAdder = tempEdge.getToVertex();
						}
					}
					if(tempAdder != null)
					{
						vertices.add(tempAdder);
						tempAdder = null;
					}
				}
			}else{
				break;
			}
		}
		
		return mst;
	}

	
	public static void main(String[] args) {
		ArrayList<String> path = new ArrayList<String>();
		
		/*The UWG on the assignment sheet*/
		UWG uwg1 = new UWG();
		
		uwg1.insertVertex("A");
		uwg1.insertVertex("B");
		uwg1.insertVertex("C");
		uwg1.insertVertex("D");
		try{
			uwg1.insertVertex("E");
		}catch(Exception e){
			System.out.println(e.getMessage() + "\n");
		}
		
		uwg1.insertEdge(5, "A", "A");
		uwg1.insertEdge(4, "A", "B");
		uwg1.insertEdge(3, "A", "C");
		uwg1.insertEdge(1, "B", "D");
		uwg1.insertEdge(2, "C", "D");
		try{
			uwg1.insertEdge(6, "D", "D");
		}catch(Exception e)
		{
			System.out.println(e.getMessage() + "\n");
		}
		
		path.add("A");
		path.add("A");
		path.add("C");
		path.add("D");
		
		System.out.println(uwg1.toString());
		System.out.println("The degree of 'A':" + uwg1.degree("A"));
		System.out.println("Total path weight of {'A','A','C','D'}: " + uwg1.totalPathWeight(path));
		System.out.println();
		
		/*The UWG on the slide*/
		UWG uwg2 = new UWG(8,13);
		path.clear();
		
		uwg2.insertVertex("A");
		uwg2.insertVertex("B");
		uwg2.insertVertex("C");
		uwg2.insertVertex("D");
		uwg2.insertVertex("E");
		uwg2.insertVertex("F");
		uwg2.insertVertex("G");

		uwg2.insertEdge(2, "A", "B");
		uwg2.insertEdge(4, "A", "C");
		uwg2.insertEdge(1, "A", "D");
		uwg2.insertEdge(3, "B", "D");
		uwg2.insertEdge(10, "B", "E");
		uwg2.insertEdge(2, "C", "D");
		uwg2.insertEdge(2, "D", "E");
		uwg2.insertEdge(5, "C", "F");
		uwg2.insertEdge(8, "D", "F");
		uwg2.insertEdge(4, "D", "G");
		uwg2.insertEdge(6, "E", "G");
		uwg2.insertEdge(1, "F", "G");
		
		path.add("A");
		path.add("D");
		path.add("E");
		path.add("G");
		path.add("F");
		
		System.out.println(uwg2.toString());
		System.out.println("The degree of 'D':" + uwg2.degree("D"));
		System.out.println("Total path weight of {'A','D','E','G','F'}: " + uwg2.totalPathWeight(path));
		System.out.println();
		
		
		path.clear();
		path.add("A");
		path.add("B");
		path.add("E");
		path.add("G");
		path.add("F");
		path.add("C");
		path.add("D");
		path.add("G");
		System.out.println("The degree of 'E':" + uwg2.degree("E"));
		System.out.println("Total path weight of {'A','B','E','G','F','C','D','G'}: " + uwg2.totalPathWeight(path));
		System.out.println();
		
		/*Task 4*/
		System.out.println(Prim(uwg2).toString());
		
		
		/*My UWG*/
		UWG uwg3 = new UWG(6,9);
		path.clear();
		
		uwg3.insertVertex("A");
		uwg3.insertVertex("B");
		uwg3.insertVertex("C");
		uwg3.insertVertex("D");
		uwg3.insertVertex("E");

		uwg3.insertEdge(2, "A", "B");
		uwg3.insertEdge(3, "A", "C");
		uwg3.insertEdge(9, "A", "D");
		uwg3.insertEdge(10, "B", "E");
		uwg3.insertEdge(4, "C", "D");
		uwg3.insertEdge(11, "D", "E");
		uwg3.insertEdge(1, "C", "E");
		uwg3.insertEdge(6, "B", "C");
		
		path.add("A");
		path.add("C");
		path.add("E");
		path.add("B");
		path.add("C");
		path.add("D");
		path.add("E");
		path.add("B");
		
		System.out.println(uwg3.toString());
		System.out.println("The degree of 'C':" + uwg3.degree("D"));
		System.out.println("Total path weight of {'A','C','E','B','C','D','E','B'}: " + uwg3.totalPathWeight(path));
		System.out.println();
	}

}
