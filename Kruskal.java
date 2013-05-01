/* Kruskal.java */

import graph.*;
import set.*;
import dict.*;
import java.util.Random;
import list.*;

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
    DList allEdges = new DList();
    Object[] allVertices = g.getVertices();
    HashTableChained vertexMap = new HashTableChained(allVertices.length);
    DisjointSets vertexSet = new DisjointSets(allVertices.length);

    for(int j = 0; j < allVertices.length; j++){
      Object vertex = allVertices[j];
      minSpanningGraph.addVertex(vertex);
      vertexMap.insert(vertex,new Integer(j));
      Neighbors allNeighbors = g.getNeighbors(vertex);
      for(int i = 0; i < allNeighbors.neighborList.length; i++){
        allEdges.insertBack(new KruskalEdge(vertex,allNeighbors.neighborList[i],allNeighbors.weightList[i]));
      }
    }

    allEdges = mergeSort(allEdges);

    try{
      ListNode current = allEdges.front();
      for(int k = 0; k < allEdges.length(); k++){
        KruskalEdge currentEdge = (KruskalEdge) current.item();
        Object firstVertex = currentEdge.firstVertex();
        Object secondVertex = currentEdge.secondVertex();
        int firstVertexIndex = ((Integer) vertexMap.find(firstVertex).value()).intValue();
        int secondVertexIndex = ((Integer) vertexMap.find(secondVertex).value()).intValue();
        int firstVertexRoot = vertexSet.find(firstVertexIndex);
        int secondVertexRoot = vertexSet.find(secondVertexIndex);
        if(firstVertexRoot != secondVertexRoot){
          vertexSet.union(firstVertexRoot, secondVertexRoot);
          minSpanningGraph.addEdge(firstVertex,secondVertex,currentEdge.weight());
        }
        current = current.next();
      }
    } catch(InvalidNodeException e){}

    return minSpanningGraph;
  }

  /**
    * mergeSort() sorts the DList list, destroying list in the process. It returns another DList, 
    *   which contains the same elements as list, except in sorted order.
    @param list is the DList to be sorted. This is destroyed in the process of sorting. 
        The elements of list must implement the Comparable interface, or this sorting method will fail
  **/
  private static DList mergeSort(DList list){
    if(list.length() <= 1){
      return list;
    } else{
      
      int targetElement = list.length() / 2;
      DList firstHalf = new DList();
      try{
        for(int i = 0; i < targetElement; i++){
          firstHalf.insertBack(list.front().item());
          list.front().remove();
        }
      } catch(InvalidNodeException error){}
      
      DList firstHalfSorted = mergeSort(firstHalf);
      DList secondHalfSorted = mergeSort(list);
      return merge(firstHalfSorted, secondHalfSorted);
    }
  }

  /**
    * merge() merges two sorted DLists into a single sorted DList. This method destroys both input DLists, 
    *   and returns one sorted DList
    @param one is the first DList to be merged. Elements of this DList must implement the Comparable interface,
        and this DList must already be sorted. This DList will be destroyed in the merging process.
    @param two is the second DList to be merged. Elements of this DList must implement the Comparable interface,
        and this DList must already be sorted. This DList will be destroyed in the merging process.
  **/
  private static DList merge(DList one, DList two){
    DList solutionList = new DList();
    try{
      while(one.length() > 0 && two.length() > 0){
        Comparable oneFront = (Comparable) one.front().item();
        Comparable twoFront = (Comparable) two.front().item();
        if(oneFront.compareTo(twoFront) <= 0){
          solutionList.insertBack(oneFront);
          one.front().remove();
        } else{
          solutionList.insertBack(twoFront);
          two.front().remove();
        }
      }
      while(one.length() > 0){
        solutionList.insertBack(one.front().item());
        one.front().remove();
      }
      while(two.length() > 0){
        solutionList.insertBack(two.front().item());
        two.front().remove();
      }
    } catch(InvalidNodeException error){}
    return solutionList;
  }

}
