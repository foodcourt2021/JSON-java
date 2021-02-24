package org.json.junit;

import org.json.JSONNode;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class Milestone4Test {

    private final String xmlBooks =
            "<Books>" +
                "<book>" +
                    "<title>AAA</title>" +
                    "<author>ASmith</author>" +
                "</book>" +
                "<book>" +
                    "<title>BBB</title>" +
                    "<author>BSmith</author>" +
                "</book>" +
            "</Books>";

    @Test
    public void testJSONObjectToStream() {
        JSONObject jsonObject = XML.toJSONObject(xmlBooks);
        Stream<JSONNode> stream = jsonObject.toStream();
        List<JSONNode> list = stream.collect(Collectors.toList());
        assertEquals(4, list.size());
        assertEquals(list.get(0).getPath(), "/Books/book/0");
        Util.compareActualVsExpectedJsonObjects(
                list.get(0).getJsonObject(), XML.toJSONObject("<title>AAA</title><author>ASmith</author>"));
        assertEquals(list.get(1).getPath(), "/Books/book/1");
        Util.compareActualVsExpectedJsonObjects(
                list.get(1).getJsonObject(), XML.toJSONObject("<title>BBB</title><author>BSmith</author>"));
        assertEquals(list.get(2).getPath(), "/Books");
        Util.compareActualVsExpectedJsonObjects(
                list.get(2).getJsonObject(),
                XML.toJSONObject("<book>" +
                        "<title>AAA</title><author>ASmith</author>" +
                        "</book>" +
                        "<book>" +
                        "<title>BBB</title><author>BSmith</author>" +
                        "</book>"));
        assertEquals(list.get(3).getPath(), "");
        Util.compareActualVsExpectedJsonObjects(list.get(3).getJsonObject(), jsonObject);
    }

    @Test
    public void testChainOperationsMap() {
        JSONObject jsonObject = XML.toJSONObject(xmlBooks);
        Stream<JSONNode> stream = jsonObject.toStream();
        stream.map(jsonNode -> jsonNode.getPath()).forEach(System.out::println);
        assertEquals(
    "/Books/book/0, /Books/book/1, /Books, ",
            XML.toJSONObject(xmlBooks).toStream().map(jsonNode -> jsonNode.getPath()).collect(Collectors.joining(", ")));
    }

    @Test
    public void testChainOperationsMapAndFilter() {
        JSONObject jsonObject = XML.toJSONObject(xmlBooks);
        Stream<JSONNode> stream = jsonObject.toStream();
        List<String> titles =
            stream
            .filter(jsonNode -> jsonNode.getPath().indexOf("/Books/book/") >= 0)
            .map(jsonNode -> (String)jsonNode.getJsonObject().get("title"))
            .collect(Collectors.toList());
        assertEquals(titles.get(0), "AAA");
        assertEquals(titles.get(1), "BBB");
    }

}
