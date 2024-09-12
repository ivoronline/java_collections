package com.ivoronline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {

  public static void main(String arg[]) {
  
    //CREATE LIST
    List<String>            list = new ArrayList();         //Stores Strings.
  //List<String>            list = new ArrayList<String>(); //Stores Strings.
  //List                    list = new ArrayList();         //Stores Objects

    //ADD ELEMENT
                            list.add(    "John");           //Add element at the end of List.
                            list.add (0, "Bill");           //Insert element at index 0 (shift higher right)
                 
    //GET ELEMENT
    String   name          = list.get      (0);             //Get element at index 0
    
    //REMOVE ELEMENT
    String   removed       = list.remove(0);                //Remove element at index 3 (shift higher left)
                             list.clear ( );                //Remove all elements
                                
    //CHECK FOR ELEMENT
    boolean  containsValue = list.contains("John");
    
    //GET SIZE
    boolean  isEmpty       = list.isEmpty();
    int      size          = list.size   ();
    
    //ITERATE THROUGH ELEMENTS
    Iterator<String> iterator = list.iterator();
    while( iterator.hasNext() == true ) {
      String value = iterator.next();
      System.out.println(value);
      iterator.remove();                                    //Removes current element
    }
    
    //DISPLAY ALL ELEMENTS
    System.out.println(list);                               //[Bill, John]
    
  }

}
