We added the following 2 functions into `XML.java`

```java
public static CompletableFuture<String> toJSONObject(Reader aReader,
                                                         Function<JSONObject, String> function,
                                                         Function<Throwable, String> errorHandler)
                                                         
private static JSONObject toJSONObjectDelayedProcessing(Reader reader, int delay)
```

The first function returns a `CompletableFuture<String>` object, it expects one `Function<JSONObject, String> function`
and one `Function<Throwable, String> errorHandler` as argument.
The second function is to add some delay to mimic the processing a large XML file.

The test cases ae in `Milestone5Test.java`. We tested 2 different cases, one is processing successfully and one is processing with
failure. We also tested the async return of the function by comparing the time when the function returns and the results are available.
We make sure the results are available at least 2 seconds later than the function return.
Since we mimic the XML processing will take 2 seconds to finish.
