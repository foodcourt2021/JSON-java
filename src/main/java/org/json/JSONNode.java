package org.json;

public class JSONNode {
    private JSONObject jsonObject;
    private String path;

    public JSONNode(JSONObject jsonObject, String path) {
        this.jsonObject = jsonObject;
        this.path = path;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getPath() {
        return path;
    }
}
