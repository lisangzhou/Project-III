/* WUGraph.java */

package graph;

import list.*;
import dict.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
    private int numVertices;
    private int numEdges;
    private HashTableChained vertexHashTable;
    private DList vertices;
    private HashTableChained edges;
   
    
    /**
     * WUGraph() constructs a graph having no vertices or edges.
     *
     * Running time:  O(1).
     */
    public WUGraph(){
        vertexHashTable=new HashTableChained();
        edges = new HashTableChained();
        vertices = new DList();
    }
    
    /**
     * vertexCount() returns the number of vertices in the graph.
     *
     * Running time:  O(1).
     */
    public int vertexCount(){
        return numVertices;
    }
    
    /**
     * edgeCount() returns the number of edges in the graph.
     *
     * Running time:  O(1).
     */
    public int edgeCount(){
        return numEdges;
    }
    
    /**
     * getVertices() returns an array containing all the objects that serve
     * as vertices of the graph.  The array's length is exactly equal to the
     * number of vertices.  If the graph has no vertices, the array has length
     * zero.
     *
     * (NOTE:  Do not return any internal data structure you use to represent
     * vertices!  Return only the same objects that were provided by the
     * calling application in calls to addVertex().)
     *
     * Running time:  O(|V|).
     */
    public Object[] getVertices(){
    	
        Object[] result = new Object[numVertices];
        try{
            DListNode currentNode = (DListNode) vertices.front();
            for(int i = 0; i < vertices.length(); i++){
                result[i] = ((Vertex) currentNode.item()).getItem();
                currentNode = (DListNode) currentNode.next();
            }
        } catch(InvalidNodeException error){}
        return result;
    }
    
    /**
     * addVertex() adds a vertex (with no incident edges) to the graph.  The
     * vertex's "name" is the object provided as the parameter "vertex".
     * If this object is already a vertex of the graph, the graph is unchanged.
     *
     * Running time:  O(1).
     */
    public void addVertex(Object vertex){
        if(vertexHashTable.find(vertex) == null){
            Vertex vertexToAdd = new Vertex(vertex);
            vertices.insertBack(vertexToAdd);
            vertexToAdd.setNode((DListNode) vertices.back());
            vertexHashTable.insert(vertex, vertexToAdd);
            numVertices++;
        }   
    }
    
    /**
     * removeVertex() removes a vertex from the graph.  All edges incident on the
     * deleted vertex are removed as well.  If the parameter "vertex" does not
     * represent a vertex of the graph, the graph is unchanged.
     *
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public void removeVertex(Object vertex) {
    	if (!isVertex(vertex)) {
    		return;
    	} else {
    		Vertex v = (Vertex) vertexHashTable.find(vertex).value();
    		DList adjacentEdges = v.getAdjacentEdges();
    		DListNode n = (DListNode) adjacentEdges.front();
    		try {
    			while (n.isValidNode()) {
    				Edge e = ((Edge) n.item());
    				n = (DListNode) n.next();
    				removeEdge(e.getOrigin().getItem(), e.getDestination().getItem());
    			}
    			v.getNode().remove();
    			vertexHashTable.remove(vertex);
    			numVertices--;
    		} catch(InvalidNodeException error){}
    	}

    }
    
    /**
     * isVertex() returns true if the parameter "vertex" represents a vertex of
     * the graph.
     *
     * Running time:  O(1).
     */
    public boolean isVertex(Object vertex){
        return vertexHashTable.find(vertex) != null;
    }
    
    /**
     * degree() returns the degree of a vertex.  Self-edges add only one to the
     * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
     * of the graph, zero is returned.
     *
     * Running time:  O(1).
     */
    public int degree(Object vertex){
        if(vertexHashTable.find(vertex)!=null){
        	return ((Vertex) vertexHashTable.find(vertex).value()).getAdjacentEdges().length();
        } else {
        	return 0;
        }
        
    }
    
    /**
     * getNeighbors() returns a new Neighbors object referencing two arrays.  The
     * Neighbors.neighborList array contains each object that is connected to the
     * input object by an edge.  The Neighbors.weightList array contains the
     * weights of the corresponding edges.  The length of both arrays is equal to
     * the number of edges incident on the input vertex.  If the vertex has
     * degree zero, or if the parameter "vertex" does not represent a vertex of
     * the graph, null is returned (instead of a Neighbors object).
     *
     * The returned Neighbors object, and the two arrays, are both newly created.
     * No previously existing Neighbors object or array is changed.
     *
     * (NOTE:  In the neighborList array, do not return any internal data
     * structure you use to represent vertices!  Return only the same objects
     * that were provided by the calling application in calls to addVertex().)
     *
     * Running time:  O(d), where d is the degree of "vertex".
     */
    public Neighbors getNeighbors(Object vertex) {
    	int count = 0;
    	Neighbors neighbors = new Neighbors();
    	neighbors.neighborList = new Object[degree(vertex)];
    	neighbors.weightList = new int[degree(vertex)];
    	if (!isVertex(vertex)) {
    		return null;
    	}
    	else {
        	Vertex internal = (Vertex) vertexHashTable.find(vertex).value();
        	if (internal.getAdjacentEdges().length() == 0) {
        		return null;
        	}
        	DListNode n = (DListNode) internal.getAdjacentEdges().front();
        	try {
        		while (n.isValidNode()){
        			neighbors.neighborList[count] = ((Edge) n.item()).getDestination().getItem();
        			neighbors.weightList[count] = ((Edge) n.item()).getWeight();
        			count++;
        			n = (DListNode) n.next();
        		}
        	} catch(InvalidNodeException error){} 

    	}
    	return neighbors;
    }
    
    /**
     * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
     * u and v does not represent a vertex of the graph, the graph is unchanged.
     * The edge is assigned a weight of "weight".  If the edge is already
     * contained in the graph, the weight is updated to reflect the new value.
     * Self-edges (where u == v) are allowed.
     *
     * Running time:  O(1).
     */
    public void addEdge(Object u, Object v, int weight){
    	VertexPair vertexPair = new VertexPair(u,v);
        if (!isVertex(u) || !isVertex(v)) {
        	return;
        }
        else if (edges.find(vertexPair) != null) {
        	Edge edgeToUpdate = (Edge) edges.find(vertexPair).value();
        	edgeToUpdate.setWeight(weight);    
        	if (edgeToUpdate.getPartner() != null)
        		edgeToUpdate.getPartner().setWeight(weight);
        }
        else {
        	Vertex origin = (Vertex) vertexHashTable.find(u).value();
        	Vertex destination = (Vertex) vertexHashTable.find(v).value();
        	Edge edgeToAdd = new Edge(origin, destination, weight);
        	origin.getAdjacentEdges().insertBack(edgeToAdd);
        	if (!u.equals(v)) {
        		Edge partnerToAdd = new Edge(destination, origin, weight);
        		edgeToAdd.setPartner(partnerToAdd);
        		partnerToAdd.setPartner(edgeToAdd);
        		destination.getAdjacentEdges().insertBack(partnerToAdd);
        		partnerToAdd.edgeNode = (DListNode) destination.getAdjacentEdges().back();
        	}
        	edgeToAdd.edgeNode = (DListNode) origin.getAdjacentEdges().back(); 	
        	edges.insert(vertexPair,edgeToAdd);
            numEdges++;
        }
    }
    
    /**
     * removeEdge() removes an edge (u, v) from the graph.  If either of the
     * parameters u and v does not represent a vertex of the graph, the graph
     * is unchanged.  If (u, v) is not an edge of the graph, the graph is
     * unchanged.
     *
     * Running time:  O(1).
     */
    public void removeEdge(Object u, Object v) {
    	if (!isEdge(u,v)) {
    		return;
    	}
    	else {
    		try {
    			Entry e = edges.find(new VertexPair(u,v));
        		Edge remove = (Edge) e.value();
        		if (remove.getPartner() != null) {
            		Edge removePartner = remove.getPartner();
            		removePartner.edgeNode.remove();
        		}
        		remove.edgeNode.remove();
        		edges.remove(new VertexPair(u,v));
        		numEdges--;
    		}  catch(InvalidNodeException error){}	
    	}	
    }
    
    /**
     * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
     * if (u, v) is not an edge (including the case where either of the
     * parameters u and v does not represent a vertex of the graph).
     *
     * Running time:  O(1).
     */
    public boolean isEdge(Object u, Object v) {
    	if (edges.find(new VertexPair(u,v)) != null) {
   		  return true;
   	  	} else {
   		  return false;
   	  	}
    }
    
    /**
     * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
     * an edge (including the case where either of the parameters u and v does
     * not represent a vertex of the graph).
     *
     * (NOTE:  A well-behaved application should try to avoid calling this
     * method for an edge that is not in the graph, and should certainly not
     * treat the result as if it actually represents an edge with weight zero.
     * However, some sort of default response is necessary for missing edges,
     * so we return zero.  An exception would be more appropriate, but
     * also more annoying.)
     *
     * Running time:  O(1).
     */
    public int weight(Object u, Object v) {
    	Entry e = edges.find(new VertexPair(u,v));
      	if (e == null) {
      		return 0;
      	} else {
      		return ((Edge) e.value()).getWeight();
      	}
    }  
}
