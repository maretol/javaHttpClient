
import java.io.*;
import java.util.*;

public class Node implements Serializable{
	private String name;
	private Node parent;
	private List<Node> children = new ArrayList<Node>();

	private Node(){
	}

	public Node(String name, Node parent){
		this.name = name;
		this.parent = parent;
	}

	public void addChild(Node child){
		this.children.add(child);
	}

	public List<Node> getChildren(){
		return children;
	}

	@Override
	public String toString(){
		return name;
	}
}