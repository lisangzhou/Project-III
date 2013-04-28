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

  /*
  public boolean equals(Object o){
    if(o instanceof KruskalEdge){
      boolean firstCheck = firstVertex.equals(((KruskalEdge) o).firstVertex()) && secondVertex.equals(((KruskalEdge) o).secondVertex()) && weight == (((KruskalEdge) o).weight();
      boolean secondCheck = firstVertex.equals(((KruskalEdge) o).secondVertex()) && secondVertex.equals(((KruskalEdge) o).firstVertex()) && weight == (((KruskalEdge) o).weight();
      return firstCheck || secondCheck;
    } else{
      return false;
    }
  }
  */
  public int compareTo(KruskalEdge o){
    if(weight < o.weight()){
      return -1;
    } else if(weight == o.weight()){
      return 0;
    } else{
      return 1;
    }
  }
}
