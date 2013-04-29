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
      if(!first.equals(second) && (firstRoot == -1 || secondRoot == -1 || firstRoot != secondRoot)){
        minSpanningGraph.addEdge(first,second,edge.weight());
        vertexSet.union(firstRoot,secondRoot);
      }
    }
    return minSpanningGraph;
  }

  /**
    quickSort() sorts an array using in-place quicksort
    @param array is the array that is being sorted
    @param low is the lower index bound of the array being sorted. Inclusive
    @param high is the upper index bound of the array being sorted. Inclusive
  **/
  private static void quickSort(Comparable[] array, int low, int high){
    if(high - low > 1){
      int pivotIndex = randomNumber(low,high);
      Comparable pivot = array[pivotIndex];
      array[pivotIndex] = array[high];
      array[high] = pivot;

      int i = low - 1;
      int j = high;
      do{
        do{i++; } while(array[i].compareTo(pivot) < 0);
        do{j--; } while(array[j].compareTo(pivot) > 0);

        if(i < j){
          Comparable ith = array[i];
          array[i] = array[j];
          array[j] = ith;
        }
      } while(i < j);
      
      array[high] = array[i];
      array[i] = pivot;
      quickSort(array,low,i-1);
      quickSort(array,i+1,high);
    }

  }

  /**
    randomNumber() produces a random number between low and high, inclusive
    @param low is the lower bound for random number generation. The random number generated is an integer greater than or equal to this lower bound.
    @param high is the upper bound for random number generation. The random number generated is an integer less than or equal to this upper bound. high must be greater than
      low, otherwise behavior may be unexpected.
  **/
  private static int randomNumber(int low, int high){
    Random random = new Random();

    int r = random.nextInt();
    int randomValue = r % (high - low + 1);
    if(randomValue < 0){
      return low - randomValue;
    } else{
      return randomValue + low;
    }
  }

}
