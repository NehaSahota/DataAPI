package cpd3314.project;

import org.json.simple.JSONObject;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 
 * @author <Neha>
 */
@Root
public class Product {
    @Element
    private int id;
    @Element
     private  String name="";
    @Element
    private  String description="";
    @Element
    private  int quantity;
    @Element
    private  String dateAdded="";

    public Product() {
    }
    /**
     * Constructor for Product class
     * @param id
     * @param name
     * @param desc
     * @param quantity
     * @param dateAdded 
     */
    public Product(int id, String name, String desc, int quantity, String dateAdded) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.quantity = quantity;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    /**
     * 
     * @return XML formated string 
     */
    public String toXML(){
        return String.format("\t<product>\n\t\t<id>%d</id>\n\t\t<name>%s</name>"
                + "\n\t\t<description>%s</description>\n\t\t<quantity>%d</quantity>"
                + "\n\t\t<dateAdded>%s</dateAdded>\n\t</product>",
                id, name, description, quantity, dateAdded);
    
    }
    /**
     * 
     * @return YAML formated string
     */
    public String toYAML(){
     return String.format("dateAdded: \'%s\'\ndescription: %s\nid: %d\nname: %s\nquantity: %d\n",dateAdded,description,id,name,quantity);
    }
    /**
     * 
     * @return JSON formated string
     */
    public String toJSON(){
        
        JSONObject objJson = new JSONObject();
        objJson.put("name", name);
        objJson.put("id", id);
        objJson.put("description", description);
        objJson.put("quantity", quantity);
        objJson.put("dateAdded", dateAdded);
        String jsonText = objJson.toString();
       
     return jsonText;
    }
    /**
     * 
     * @return SQL formatted string
     */
    public String toSQL(){
     return String.format("INSERT INTO Products VALUES (%d, \"%s\", \"%s\", %d, \"%s\");",
                id, name, description, quantity, dateAdded);
    }
    /**
     * 
     * @return HTML formatted string
     */
    public String toHTML(){

          String str=("<div class=\"product\">\n"+
            "\t<h1>"+name+"</h1>\n"+
            "\t<p>ID: "+id+"</p>\n"+
            "\t<p>"+description+"</p>\n"+
            "\t<p>Quantity: "+quantity+"</p>\n"+
            "\t<p>Added: "+dateAdded+"</p>\n"+
            "</div>") ;
         
        return str;
    }
    
    
    
    
    
    
    
    
    
}
