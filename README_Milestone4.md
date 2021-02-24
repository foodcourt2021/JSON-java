* The commit for Milestone 4 is `4211a7b`
* I followed the discussion in the review session on last Friday.
* In `toStream`, I first did a depth first search (DFS) of the converted JSON object.
* Then in the DFS process, I append every JSON object I encounter into a `List<JSONNode>`.
  Please note, I am doing DFS in a post-order way, meaning when visiting one node,
  the lower level nodes, i.e. the descendant nodes of the current node will first be append in the `List<JSONNode>`.
  Then the current node will be append in the `List<JSONNode>`.
* The `JSONNode` is a class I created as following:

```java
public class JSONNode {
    private JSONObject jsonObject;
    private String path;
    
    // Constructor and Getters
    // ...
}
```

* Finally, I use `list.stream()` to return a `Stream<JSONNode>` object. So the
  client can do all kinds of `filter`, `map` and `collect` staff.
* The tests are in `Milestone4Test.java`.
