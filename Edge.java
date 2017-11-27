
/**
 * @author Andrew
 *
 */
public class Edge {

	private int weight;
	private String fromVertex;
	private String toVertex;
	
	public Edge(int weight, String fromVertex, String toVertex)
	{
		this.weight = weight;
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	public void setFromVertex(String fromVertex)
	{
		this.fromVertex = fromVertex;
	}
	
	public void setToVertex(String toVertex)
	{
		this.toVertex = toVertex;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public String getFromVertex()
	{
		return fromVertex;
	}
	
	public String getToVertex()
	{
		return toVertex;
	}
	
	@Override
	public String toString()
	{
		return weight + ", " + fromVertex + ", " + toVertex; 
	}
}
