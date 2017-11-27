import java.util.ArrayList;

/**
 * @author Andrew
 *
 */
public class UWG {
	private static final int DEFAULT_MAX_VERTICES = 4;
	private static final int DEFAULT_MAX_EDGES = 5;
	private VertexNode[] vertexNodes;
	private EdgeNode[] edgeNodes;
	private int firstFreeVertex = 0;
	private int firstFreeEdge = 0;
	
	public UWG()
	{
		vertexNodes = new VertexNode[DEFAULT_MAX_VERTICES];
		edgeNodes = new EdgeNode[DEFAULT_MAX_EDGES];
	}
	
	public UWG(int vertexSize,int edgeSize){
		vertexNodes = new VertexNode[vertexSize];
		edgeNodes = new EdgeNode[edgeSize];
	}
	
	public int getFirstFreeVertex()
	{
		return firstFreeVertex;
	}
	
	public void setFirstFreeVertex(int firstFreeVertex)
	{
		this.firstFreeVertex = firstFreeVertex;
	}
	
	public int getFirstFreeEdge()
	{
		return firstFreeEdge;
	}
	
	public void setFirstFreeEdge(int firstFreeEdge)
	{
		this.firstFreeEdge = firstFreeEdge;
	}
	
	public VertexNode[] getVertices()
	{
		return vertexNodes;
	}
	
	public void setVertices(VertexNode[] vertexNodes)
	{
		this.vertexNodes = vertexNodes;
	}
	
	public EdgeNode[] getEdges()
	{
		return edgeNodes;
	}
	
	public void setEdges(EdgeNode[] edgeNodes)
	{
		this.edgeNodes = edgeNodes;
	}
	
	public int getNumVertices()
	{
		return firstFreeVertex;
	}
	
	public int getNumEdges()
	{
		return firstFreeEdge;
	}
	
	
	/**
	 * @param name
	 */
	public void insertVertex(String name)
	{
		if(firstFreeVertex < vertexNodes.length)
		{
			vertexNodes[firstFreeVertex] = new VertexNode(name);
			firstFreeVertex ++;
		}
		else{
			throw new OutOfSpaceException("The array of vertex is full.");
		}
	}
	
	/**
	 * @param weight
	 * @param fromVertex
	 * @param toVertex
	 */
	public void insertEdge(int weight, String fromVertex, String toVertex)
	{
		int tempFromVertex = -1;
		int tempToVertex = -1;
		boolean flag = true;
		if(firstFreeEdge < edgeNodes.length)
		{
			/*search the fromVertex and toVertex in ArrayList<String> vertexNodes*/
			for(int i = 0;i < firstFreeVertex;i++)
			{
				if(vertexNodes[i].equals(fromVertex))
				{
					tempFromVertex = i;
				}
				if(vertexNodes[i].equals(toVertex))
				{
					tempToVertex = i;
				}
			}
			/*If tempFromVertex and tempToVertex are not -1 in the same time, it means fromVertex and toVertex are exist.*/
			if((tempFromVertex != -1)&&(tempToVertex != -1))
			{
				for(int i = 0;i < firstFreeEdge;i++)
				{
					if(((edgeNodes[i].getFromVertex() != tempFromVertex)||(edgeNodes[i].getToVertex() != tempToVertex))&&((edgeNodes[i].getFromVertex() != tempToVertex)||(edgeNodes[i].getToVertex() != tempFromVertex)))
					{
						flag = true;
					}else{
						flag = false;
					}
				}
				/*If firstFreeEdge is 0, the information will add into the edgeNodes directly.*/
				if(flag)
				{
					edgeNodes[firstFreeEdge] = new EdgeNode(weight, tempFromVertex, tempToVertex);
					firstFreeEdge ++;
				}
				else
				{
					throw new IllegalArgumentException("The edge already exits.");
				}
			}else if((tempFromVertex != -1)&&(tempToVertex == -1))
			{
				throw new IllegalArgumentException("The toVertex doesn't exist.");
			}else if((tempFromVertex == -1)&&(tempToVertex != -1))
			{
				throw new IllegalArgumentException("The fromVertex doesn't exist.");
			}else{
				throw new IllegalArgumentException("The fromVertex and toVertex don't exist.");
			}
			
		}else{
			throw new OutOfSpaceException("The array of edge is full.");
		}
	}
	
	/**
	 * @param name
	 * @return the number of edges leading from that vertex.
	 */
	public int degree(String name){
		int tempVertex = -1;
		int count = 0;
		/*search the name in vertexNodes.*/
		for(int i = 0;i < firstFreeVertex;i++)
		{
			if(vertexNodes[i].equals(name))
			{
				tempVertex = i;
				break;
			}
		}
		if(tempVertex != -1)
		{
			for(int i = 0;i < firstFreeEdge;i++)
			{
				/*this condition means it have found a vertice connecting to the vertice.*/
				if((edgeNodes[i].getFromVertex() == tempVertex)||(edgeNodes[i].getToVertex() == tempVertex))
				{
					/*this condition means the vertice is connecting to itself.*/
					if((edgeNodes[i].getFromVertex() == tempVertex)&&(edgeNodes[i].getToVertex() == tempVertex))
					{
						count += 2;
					}else{
						count += 1;
					}
				}
			}
		}else{
			System.out.println("The vertex doesn't exist.");
		}
		return count;
	}
	
	
	/**
	 * @param path
	 * @return the sum of the weights on the edges which indicated by the vertices named in the array list.
	 */
	public int totalPathWeight(ArrayList<String> path)
	{
		String[] tempPath = new String[path.size()];
		tempPath = path.toArray(tempPath);
		
		int totalWeight = 0;
		int tempFromVertex = -1;
		int tempToVertex = -1;
		boolean flag = false;
		
		for(int i = 0;i < tempPath.length - 1;i++)
		{
			for(int j = 0;j < firstFreeVertex;j++)
			{
				if(vertexNodes[j].equals(tempPath[i]))
				{
					tempFromVertex = j;
				}
				if(vertexNodes[j].equals(tempPath[i+1]))
				{
					tempToVertex = j;
				}
			}
			if((tempFromVertex != -1)&&(tempToVertex != -1))
			{
				for(int k = 0;k < firstFreeEdge;k++)
				{
					if(((edgeNodes[k].getFromVertex() == tempFromVertex)&&(edgeNodes[k].getToVertex() == tempToVertex))||((edgeNodes[k].getFromVertex() == tempToVertex)&&(edgeNodes[k].getToVertex() == tempFromVertex)))
					{
						totalWeight += edgeNodes[k].getWeight();
						flag = true;
						break;
					}
				}
				if(flag = false){
					throw new IllegalArgumentException("The path doesn't exist.");
				}
			}else
			{
				throw new IllegalArgumentException("The vertex(s) doesn't(don't) exist.");
			}
		}
		
		return totalWeight;
	}
	
	
	/**
	 * @param vertices
	 * @return an edge of the graph of minimum 
	 * If no such edge exists then will return null.
	 */
	public Edge findMinEdge(ArrayList<String> vertices)
	{
		int minWeight = 0;
		int tempWeight = 0;
		Edge minEdge = new Edge(minWeight,null,null);
		int tempVertex = -1;
		int tempOther = -1;
		boolean flag = false;
		boolean exit = false;
		if(vertices.isEmpty())
		{
			minWeight = edgeNodes[0].getWeight();
			for(int i = 1;i < firstFreeEdge;i++)
			{
				if(edgeNodes[i].getWeight() < minWeight)
				{
					minWeight = edgeNodes[i].getWeight();
					minEdge.setWeight(minWeight);
					minEdge.setFromVertex(vertexNodes[edgeNodes[i].getFromVertex()].toString());
					minEdge.setToVertex(vertexNodes[edgeNodes[i].getToVertex()].toString());
				}
			}
			return minEdge;
		}else{
			
			ArrayList<String> Others = new ArrayList<String>();
			
			for(int i = 0;i < firstFreeVertex;i++)
			{
				for(String vertice: vertices)
				{
					if(vertice == vertexNodes[i].toString())
					{
						exit = true;
						break;
					}
				}
				if(exit)
				{
					exit = false;
				}else{
					Others.add(vertexNodes[i].toString());
				}
			}
			
			for(String vertice: vertices)
			{
				for(int i = 0;i < firstFreeVertex;i++)
				{
					if(vertice == vertexNodes[i].toString())
					{
						tempVertex = i;
						break;
					}
				}
				for(String other:Others)
				{
					for(int j = 0;j < firstFreeVertex;j++)
					{
						if(other == vertexNodes[j].toString())
						{
							tempOther = j;
							break;
						}
					}
					for(int k = 0;k < firstFreeEdge;k++)
					{
						if(((edgeNodes[k].getFromVertex() == tempVertex)&&(edgeNodes[k].getToVertex() == tempOther))||((edgeNodes[k].getFromVertex() == tempOther)&&(edgeNodes[k].getToVertex() == tempVertex)))
						{
							tempWeight = edgeNodes[k].getWeight();
							flag = true;
							break;
						}
					}
					if(flag = true){
						/*at the first time the minWeight should initialize to the first element.*/
						if(minWeight == 0)
						{
							minWeight = tempWeight;
							minEdge.setWeight(minWeight);
							minEdge.setFromVertex(vertice);
							minEdge.setToVertex(other);
							flag = false;
						}else{
							if(tempWeight < minWeight)
							{
								minWeight = tempWeight;
								minEdge.setWeight(minWeight);
								minEdge.setFromVertex(vertice);
								minEdge.setToVertex(other);
							}
							flag = false;
						}
					}
				}
			}
			if(minWeight == 0)
			{
				return null;
			}
			else{
				return minEdge;
			}
		}
	}
	
	
	
	@Override
	public String toString()
	{
		String message = null;
		if(firstFreeVertex < vertexNodes.length)
		{
			message = "firstFreeVertex = " + firstFreeVertex + "\n";
		}
		else
		{
			message = "firstFreeVertex = null (The array of vertex is full)" + "\n";
		}
		if(firstFreeEdge < edgeNodes.length)
		{
			message += "firstEdgeEdge = " + firstFreeEdge + "\n\n";
		}
		else
		{
			message += "firstFreeEdge = null (The array of edge is full)" + "\n\n";
		}
		
		for(int i = 0;i < firstFreeVertex;i++)
		{
			message += i + ": " + vertexNodes[i].toString() + "\n";
		}
		
		message += "\n";
		
		for(int i = 0;i < firstFreeEdge;i++)
		{
			message += i + ": " + edgeNodes[i].toString() + "\n";
		}
		
		return message;
	}
}
