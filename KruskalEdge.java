public class KruskalEdge implements Comparable{
  
  private Object firstVertex;
  private Object secondVertex;
  private int weight;

  public KruskalEdge(Object first, Object second, int edgeWeight){
    firstVertex = first;
    secondVertex = second;
    weight = edgeWeight;
  }

  public Object firstVertex(){
    return firstVertex;
  }

  public Object secondVertex(){
    return secondVertex;
  }

  public int weight(){
    return weight;
  }

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

  public String toString(){
    return firstVertex.toString() + "-" + weight + "->" + secondVertex.toString();
  }
}
