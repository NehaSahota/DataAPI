package cpd3314.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Final Term Project for CPD-3314 Java Development I
 *
 * @author <Neha>
 */
public class CPD3314Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String fileName = "CPD3314";
        String format = "XML";
        String sort = null;
        String date = null;
        int id = -1;
        String findcontent = null;
        Serializer serial = new Persister();
        File input = new File("ORIGINALS.xml");
        Products obj = serial.read(Products.class, input);
        List<Product> productobj = obj.getProducts();

        int length = productobj.size();

        //splitting the argument passed and saving the value of format passed in  format field
        for (String arg : args) {
            if (arg.contains("-format=")) {
                format = arg.split("=")[1];
            }

        }
        //splitting the argument passed and saving the value of sort passed in sort field
        for (String arg : args) {
            if (arg.contains("-sort=")) {
                sort = arg.split("=")[1];
            }
        }

        //splitting the argument passed and saving the value of limit passed in length field
        for (String arg : args) {
            if (arg.contains("-limit=")) {
                int index = arg.indexOf("=");
                length = Integer.parseInt(arg.substring(index + 1));
            }
        }
        //splitting the argument passed and saving the value of getID passed in id field
        for (String arg : args) {
            if (arg.contains("-getID=")) {
                id = Integer.parseInt(arg.split("=")[1]);
            }

        }
        //splitting the argument passed and saving the value of getDate passed in date field
        for (String arg : args) {
            if (arg.contains("-getDate=")) {
                int index = arg.indexOf("=");
                date = arg.substring(index + 1);
            }

        }
        
        /**
         * splitting the argument passed and saving the value of output file passed in fileName field
         */
        
        for (String arg : args) {
            if (arg.contains("-o=")) {
                int index = arg.indexOf("=");
                fileName = arg.substring(index + 1);
            }
        }
        /**
         * splitting the argument passed and saving the value of find passed in findcontent field
         */
                
        for (String arg : args) {
            if (arg.contains("-find=")) {
                findcontent = arg.split("=")[1];

            }
        }
        /**
         * Sorting the list alphabetically by either name,id or date
         */
        if (sort != null) {
            if (sort.contains("A")) {
                Collections.sort(productobj, productNameObj);
            } else if (sort.contains("I")) {
                Collections.sort(productobj, productIdObj);
            } else if (sort.contains("D")) {
                Collections.sort(productobj, productDateObj);
            }
        }
        /**
         * Checking if the id exists in the Product list and if it return true
         * then adding the product  in the new ArrayList called list
         * -1 is the sentinal value
         */
        if (id != -1) {
            ArrayList<Product> list = new ArrayList<>();

            for (Product prod1 : productobj) {
                if (prod1.getId() == id) {
                    list.add(prod1);
                }

            }

            productobj = list;

        }
        /**
         * Checking if the date exists in the Product list and if it return true
         * then adding the product  in the new ArrayList called list
         */
        if (date != null) {
            ArrayList<Product> list = new ArrayList<>();
            for (Product prod1 : productobj) {
                if (prod1.getDateAdded().equals(date)) {
                    list.add(prod1);
                }

            }
            productobj = list;

        }
        /**
         * Checking if the findcontent exists in the Product list name or description and if it return true
         * then adding the product  in the new ArrayList called list
         */
        if (findcontent != null) {
            ArrayList<Product> list = new ArrayList<>();
            for (Product prod1 : productobj) {
                if (prod1.getName().contains(findcontent) || prod1.getDescription().contains(findcontent)) {
                    list.add(prod1);
                }
            }
            productobj = list;
        }
        /**
         * switching the format field to select the format in which the file 
         * is supposed to be formatted
         */
        switch (format) {

            case "XML":
                formatXML(length, productobj, fileName, sort);
                break;
            case "HTML":
                formatHTML(length, productobj, fileName, sort);
                break;
            case "YAML":
                formatYAML(length, productobj, fileName, sort);
                break;
            case "JSON":
                formatJSON(length, productobj, fileName, sort);
                break;
            case "SQL":
                formatSQL(length, productobj, fileName, sort);
                break;
            default:
                System.out.println(" Wrong input");

        }

    }
    
    /**
     * This method  formats the output file using the parameters length,productobj,fileName,sort
     * by calling the toXML method defined in the Product class and writes the output to the 
     * specified file
     * @param length to limit the output file to a specified length
     * @param productobj list object
     * @param fileName to give a specified file name 
     * @param sort to sort the the list
     * @throws FileNotFoundException
     * @throws Exception 
     */
    public static void formatXML(int length, List<Product> productobj, String fileName, String sort) throws FileNotFoundException, Exception {

        try (
                PrintWriter write = new PrintWriter(fileName + ".xml")) {

            write.println("<products>");
            for (int i = 0; i < length && i < productobj.size(); i++) {
                write.println(productobj.get(i).toXML());

            }
            write.println("</products>");
            write.close();
        }

    }
    /**
     * 
     * This method  formats the output file to JSON using the parameters length,productobj,fileName,sort
     * by calling the  toJSON method defined in the Product class and writes the output to the specified file
     * @param length to limit the output file to a specified length
     * @param productobj list object
     * @param fileName to give a specified file name 
     * @param sort to sort the the list
     * @throws FileNotFoundException
     * @throws Exception 
     */
    public static void formatJSON(int length, List<Product> productobj, String fileName, String sort) throws FileNotFoundException, Exception {

        try (
                PrintWriter write = new PrintWriter(fileName + ".json")) {

            write.print("{ \"products\" : [ ");
            for (int i = 0; i < length && i < productobj.size(); i++) {

                String str = "";

                for (i = 0; i <= length - 1; i++) {
                    str += (productobj.get(i).toJSON());
                    str += ", ";
                }
                str = str.substring(0, str.length() - 2);
                str = str + ("] }");
                write.print(str);

                write.close();

            }
        }
    }
    /**
     * 
     *This method  formats the output file toSQL using the parameters length,productobj,fileName,sort
     * by calling the toSQL method defined in the Product class and writes the output to the specified file
     * @param length to limit the output file to a specified length
     * @param productobj list object
     * @param fileName to give a specified file name 
     * @param sort to sort the the list
     * @throws FileNotFoundException
     * @throws Exception 
     */
    public static void formatSQL(int length, List<Product> productobj, String fileName, String sort) throws FileNotFoundException, Exception {

        try (
                PrintWriter write = new PrintWriter(fileName + ".sql")) {

            write.print("CREATE TABLE Products (id INT, name VARCHAR(50), description TEXT, quantity INT, dateAdded DATE);\n");
            for (int i = 0; i <= length - 1 && i < productobj.size(); i++) {
                write.println(productobj.get(i).toSQL());

            }

            write.close();
        }

    }
    /**
     * 
     * This method  formats the output file to YAML using the parameters length,productobj,fileName,sort
     * by calling the toYAML method defined in the Product class and writes the output to the specified file
     * @param length to limit the output file to a specified length
     * @param productobj list object
     * @param fileName to give a specified file name 
     * @param sort to sort the the list
     * @throws FileNotFoundException
     * @throws Exception 
     */
    public static void formatYAML(int length, List<Product> productobj, String fileName, String sort) throws FileNotFoundException, Exception {

        try (
                PrintWriter write = new PrintWriter(fileName + ".yaml")) {
            for (int i = 0; i < length && i < productobj.size(); i++) {
                write.println("---");
                write.print(productobj.get(i).toYAML());

            }
            write.println("---");
            write.close();
        }

    }
    /**
     * 
     * This method  formats the output file to HTML using the parameters length,productobj,fileName,sort by calling toHTML method 
     * defined in the Product class and writes the output to the specified file
     * @param length to limit the output file to a specified length
     * @param productobj list object
     * @param fileName to give a specified file name 
     * @param sort to sort the the list
     * @throws FileNotFoundException
     * @throws Exception  
     */
    public static void formatHTML(int length, List<Product> productobj, String fileName, String sort) throws FileNotFoundException, Exception {

        try (
                PrintWriter write = new PrintWriter(fileName + ".html")) {

            write.print("<!DOCTYPE html>\n<html>\n<head>\n</head>\n<body>\n");
            for (int i = 0; i < length && i < productobj.size(); i++) {
                write.println(productobj.get(i).toHTML());

            }
            write.print("</body>\n</html>");
            write.close();
        }

    }
    /**
     * taken from project pdf
     * comparing the names of products in the ArrayList
     * 
     */
    private static Comparator<Product> productNameObj = new Comparator<Product>() {
        @Override
        public int compare(Product prod1, Product prod2) {
            return prod1.getName().compareTo(prod2.getName());
        }
    };
    /**
     * taken from project pdf
     * comparing the id's of products stored in the ArrayList
     */
    private static Comparator<Product> productIdObj = new Comparator<Product>() {
        @Override
        public int compare(Product prod1, Product prod2) {
            int p1Id = prod1.getId();
            int p2Id = prod2.getId();
            return p1Id - p2Id;
        }
    };
    /**
     * taken from project pdf
     * comparing the dateAdded of products stored in the ArrayList
     */
    private static final Comparator<Product> productDateObj = new Comparator<Product>() {
        @Override
        public int compare(Product prod1, Product prod2) {
            return (prod1.getDateAdded().compareTo(prod2.getDateAdded()));
        }
    };

}
