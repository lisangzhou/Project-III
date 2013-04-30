package graph;
import list.*;


public class Edge{
    protected Vertex destination;
    protected Vertex origin;
    private int weight;
    private Edge partner;
    DListNode edgeNode;
    
    public Edge(Vertex origin, Vertex destination, int weight){
        this.destination = destination;
        this.origin = origin;
        this.weight = weight;
    }
    
    public int getWeight(){
        return weight;
    }
    
    public void setWeight(int w){
        weight = w;
    }
    
    public Edge getPartner(){
        return partner;
    }
    
    public void setPartner(Edge e){
        partner = e;
    }
    
    public void setNodeReference(DListNode n) {
        edgeNode = n;
    }
    
    public DListNode getNodeReference() {
    	return edgeNode;
    }
}  
