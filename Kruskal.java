/* Kruskal.java */

import graph.*;
import set.*;
import dict.*;
import java.util.Random;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   */
  public static WUGraph minSpanTree(WUGraph g){
    WUGraph minSpanningGraph = new WUGraph();
    KruskalEdge[] allEdges = new KruskalEdge[g.edgeCount() * 2];
    Object[] allVertices = g.getVertices();
    HashTableChained vertexMap = new HashTableChained(allVertices.length);
    DisjointSet vertexSet = new DisjointSet(allVertices.length);

    // get all of the edges. also create a map for vertices to indices in the disjoint set
    int indexCounter = 0;
    for(int j = 0; j < allVertices.length; j++){
      Object vertex = allVertices[j];

      HashTableChained.insert(g,new Integer(j));
      
      Neighbors allNeighbors = vertex.getNeighbors(vertex);
      for(int i = 0; i < allNeighbors.neighborList.length; i++){
        KruskalEdge[indexCounter] = new KruskalEdge(vertex,allNeighbors.neighborList[i],allNeighbors.weightList[i]);
        indexCounter++;
      }
    }

    quickSort(allEdges, 0, allEdges.length() - 1);
    
    for(KruskalEdge edge : allEdges){
      Object first = edge.firstVertex();
      Object second = edge.secondVertex();
      int firstRoot = vertexSet.find(vertexMap.find(first).value().intValue());
      int secondRoot = vertexSet.find(vertexMap.find(second).value().intValue());
      if(firstRoot == -1 || secondRoot == -1 || firstRoot != secondRoot){
        minSpanningGraph.addEdge(first,second,edge.weight());
        vertexSet.union(firstRoot,secondRoot);
      }
    }
    return minSpanningGraph;
  }

  private void quickSort(Comparable[] array, int low, int high){
    if(high - low > 1){
      

    }

  }

}
