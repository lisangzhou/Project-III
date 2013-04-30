package graph;
import list.*;

public class Vertex{
    private DList adjacentEdges;
    private Object item;
    protected DListNode node;

  
  public Vertex (Object o){
      item = o;
      adjacentEdges=new DList();
  }
    
    public Object getItem(){
        return item;
    }
    
    public DList getAdjacentEdges(){
        return adjacentEdges;
    }

  public void setNode(DListNode input){
    this.node = input;
  }

  public DListNode getNode(){
    return this.node;
  }
    
}  
