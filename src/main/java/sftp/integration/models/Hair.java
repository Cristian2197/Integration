package sftp.integration.models;

import java.util.Map;

public class Hair {
    public String color;
    public String type;
    
    public Hair() {
    }
    public Hair(String color, String type) {
        this.color = color;
        this.type = type;
    }
    public Hair(Map<String, Object> hairMapped) {
        this.color = (String) hairMapped.get("color");
        this.type = (String) hairMapped.get("type");
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
