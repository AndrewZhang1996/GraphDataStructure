
/**
 * @author Andrew
 *
 */
public class VertexNode {
	private String name;
	
	public VertexNode(String name){
		this.name = name;
	}
	
	public boolean equals(Object other){
		return other.equals(this.name);
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
