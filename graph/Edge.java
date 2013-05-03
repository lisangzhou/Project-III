package graph;
import list.*;


public class Edge{
    private Vertex destination;
    private Vertex origin;
    private int weight;
    private Edge partner;
    protected DListNode edgeNode;
    
    /**
     *  Edge() constructor
     *  Creates an Edge and assigns an origin vertex, a destination vertex, and a weight to it.
     */
    public Edge(Vertex origin, Vertex destination, int weight){
        this.destination = destination;
        this.origin = origin;
        this.weight = weight;
    }
    
    /**
     *  getWeight() returns weight of the Edge. 
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     *  setWeight(int w) sets the weight of the Edge. 
     *  @param w is the weight
     */
    protected void setWeight(int w){
        weight = w;
    }
    
    /**
     *  getPartner() returns partner of the Edge. 
     */
    public Edge getPartner(){
        return partner;
    }
    
    /**
     *  setPartner(Edge e) sets the Edge's partner. 
     *  @param e is the partner.
     */
    protected void setPartner(Edge e){
        partner = e;
    }
    
    /**
     *  setNodeReference(DListNode n) sets the edgeNode of the Edge. 
     *  @param n is the node that the edgeNode is set to.
     */
    protected void setNodeReference(DListNode n) {
        edgeNode = n;
    }
    
    /**
     *  getNodeReference() returns edgeNode of the Edge. 
     */
    public DListNode getNodeReference() {
        return edgeNode;
    }
    
    /**
     *  getOrigin() returns origin vertex of the Edge. 
     */
    public Vertex getOrigin() {
    	return origin;
    }
    
    /**
     *  getDestination() returns destination vertex of the Edge. 
     */
    public Vertex getDestination() {
    	return destination;
    }
}  
