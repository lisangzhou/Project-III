package graph;
import list.*;

public class Vertex{
    private int item;
    private DList edge;

  
  public Vertex(int item){
      this.item=item;
      edge=new DList();
  }
    
    public int item(){
        return this.item;
    }
    
    public DList edge(){
        return edge;
    }
}  
