import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Andrew
 *
 */
public class UWGtest {

	private UWG uwg1;
	
	public UWGtest(){
		uwg1 = new UWG();
		
	}
	@Test
	public void test() {
		ArrayList<String> path = new ArrayList<String>();
		ArrayList<String> vertices = new ArrayList<String>();
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
		uwg1.insertEdge(1, "B", "D");
		uwg1.insertEdge(3, "A", "C");
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
		
		vertices.add("A");
		vertices.add("C");
		
		System.out.println(uwg1.toString());
		System.out.println(uwg1.totalPathWeight(path));
		System.out.println(uwg1.findMinEdge(vertices));
	}

}
