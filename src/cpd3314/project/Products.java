/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpd3314.project;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author c0646567
 */
@Root
public class Products {

    /**
     * taken from xml serialization example
     * getting and setting the Product list
     */
@ElementList(inline=true, entry="product")
private List<Product> products;
   
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
