package graph;
import list.*;
public class Edge{

  DListNode firstAdjacencyNode;
  DListNode secondAdjacencyNode;
  int weight;

  public Edge(DListNode first, DListNode second, int edgeWeight){
    firstAdjacencyNode = first;
    secondAdjacencyNode = second;
    weight = edgeWeight;
  }
}  
