import java.util.*; 
import java.io.*;

class Main {

  public static String ArrayChallenge(String[] strArr) {
    // code goes here  
    Map<Integer,List<Integer>>parentToChildren = new HashMap<>();
    Map<Integer,Integer>childToParent = new HashMap<>();
    Set<Integer> allNodes = new HashSet<>();

    for(String pair : strArr){
      pair=pair.replaceAll("[()\\s]","");
      String[] nums = pair.split(",");
      int child=Integer.parseInt(nums[0]);
      int parent=Integer.parseInt(nums[1]);

      if(childToParent.containsKey(child)){
        return "false";
      }
      childToParent.put(child , parent);

      parentToChildren.putIfAbsent(parent,new ArrayList<>());
      parentToChildren.get(parent).add(child);
      if(parentToChildren.get(parent).size()>2){
        return "false";
      }
      allNodes.add(child);
      allNodes.add(parent);
    }

    Set<Integer>roots=new HashSet<>(allNodes);
    roots.removeAll(childToParent.keySet());
    if(roots.size()!=1){
      return "false";
    }

    int root=roots.iterator().next();
    Set<Integer> visited=new HashSet<>();
    if(!dfs(root,parentToChildren,visited)){
      return "false";
    }

    if(visited.size()!=allNodes.size()){
      return "false";
    }
    return "true";
  }
      
      public static boolean dfs(int node,Map<Integer,List<Integer>> tree,Set<Integer>visited){
        if(visited.contains(node)) return false;
        visited.add(node);
        if(tree.containsKey(node)){
          for(int child: tree.get(node)){
            if(!dfs(child,tree,visited)) return false;
          }
        }
        return true;
      }


  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);
    System.out.print(ArrayChallenge(s.nextLine())); 
  }

}
