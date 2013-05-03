package graph;
import list.*;

public class Vertex{
    private DList adjacentEdges;
    private Object item;
    private DListNode node;

  
    /**
     *  Vertex() constructor
     *  Creates a Vertex and assigns a vertex object to its item field.
     */    
    public Vertex (Object o){
      item = o;
      adjacentEdges=new DList();
    }
    
    /**
     *  getItem() returns vertex object of the Vertex. 
     */
    public Object getItem(){
        return item;
    }
    
    /**
     *  getAdjacentEdges() returns a DList of adjacent edges of the Vertex. 
     */
    public DList getAdjacentEdges(){
        return adjacentEdges;
    }
    
    /**
     *  setNode(DListNode n) sets the node of the Vertex. 
     *  @param n is the node that the Vertex is assigned to.
     */
    protected void setNode(DListNode n) {
        node = n;
    }
    
    /**
     *  getNode() returns the node of the Vertex. 
     */
    protected DListNode getNode() {
    	return node;
    }
    
}  
