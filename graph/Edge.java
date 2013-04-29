package graph;
import list.*;


public class Edge{
    protected Vertex destination;
    protected Vertex origin;
    private int weight;
    private Edge partner;
    
    public Edge(Vertex origin, Vertex direction, int edgeWeight){
        destination=direction;
        origin=this.origin;
        weight = edgeWeight;
    }
    
    public int weight(){
        return this.weight;
    }
    
    public void weightChange(int a){
        weight=a;
    }
    
    public Edge partner(){
        return this.partner;
    }
    
    public void setPartner(Edge identity){
        partner=identity;
    }
}  
