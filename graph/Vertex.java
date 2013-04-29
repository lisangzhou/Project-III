package graph;
import list.*;

public class Vertex{
    private Object item;
    private DList edge;

  
  public Vertex(Object item){
      this.item=item;
      edge=new DList();
  }
    
    public Object item(){
        return this.item;
    }
    
    public DList edge(){
        return edge;
    }
    
}  
