public class KruskalEdge implements Comparable{
  
  private Object firstVertex;
  private Object secondVertex;
  private int weight;


  /**
    * KruskalEdge() constructs a KruskalEdge object
    @param first is the origin vertex of the edge
    @param second is the destination vertex of the edge
    @param edgeWeight is the weight of the edge
  **/
  public KruskalEdge(Object first, Object second, int edgeWeight){
    firstVertex = first;
    secondVertex = second;
    weight = edgeWeight;
  }

  /**
    firstVertex() returns the origin vertex of the KruskalEdge object
  **/
  public Object firstVertex(){
    return firstVertex;
  }

  /**
    secondVertex() returns the destination vertex of the KruskalEdge object
  **/
  public Object secondVertex(){
    return secondVertex;
  }

  /**
    weight() returns the weight of the edge
  **/
  public int weight(){
    return weight;
  }

  /**
    compareTo() implements the Comparable interace
  **/
  public int compareTo(Object o){
    if(o instanceof KruskalEdge){
      if(weight < ((KruskalEdge) o).weight()){
        return -1;
      } else if(weight == ((KruskalEdge) o).weight()){
        return 0;
      } else{
        return 1;
      }
    } else{
      return 0;
    }
  }
}
