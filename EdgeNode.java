
/**
 * @author Andrew
 *
 */
public class EdgeNode {

	private int weight;
	private int fromVertex;
	private int toVertex;
	public EdgeNode(int weight, int fromVertex, int toVertex){
		this.weight = weight;
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public int getFromVertex()
	{
		return fromVertex;
	}
	
	public int getToVertex()
	{
		return toVertex;
	}
	
	@Override
	public String toString()
	{
		return weight + ", " + fromVertex + ", " + toVertex; 
	}
}
