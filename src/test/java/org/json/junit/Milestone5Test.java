package org.json.junit;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Milestone5Test {

    @Test
    public void testJSONObjectAsync() {
        String xmlBooks = "<title>Pride and Prejudice</title>" +
                "<author>Jane Austin</author>";
        
        Function<JSONObject, String> f = jo -> {
            StringWriter stringWriter = new StringWriter();
            return "Success: " + jo.write(stringWriter).toString();
        };

        Function<Throwable, String> h = jo -> "";
        long start = System.nanoTime();

        CompletableFuture<String> future = XML.toJSONObject(new StringReader(xmlBooks), f, h);
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        String ret;
        try {
            ret = future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Results returned after " + retrievalTime + " msecs");
        assertEquals("Success: {\"author\":\"Jane Austin\",\"title\":\"Pride and Prejudice\"}", ret);
        assertTrue((retrievalTime-invocationTime)>2000);
    }

    @Test
    public void testJSONObjectAsyncException() {
        Function<JSONObject, String> f = jo -> "";
        Function<Throwable, String> h = ex -> "Failed: " + ex.getMessage();

        String badXmlBooks = "<title>Pride and Prejudice";

        long start = System.nanoTime();
        CompletableFuture<String> future = XML.toJSONObject(new StringReader(badXmlBooks), f, h);
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        String ret;
        try {
            ret = future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Results returned after " + retrievalTime + " msecs");
        assertEquals("Failed: org.json.JSONException: Unclosed tag title at 26 [character 27 line 1]", ret);
        assertTrue((retrievalTime-invocationTime)>2000);
    }
}
