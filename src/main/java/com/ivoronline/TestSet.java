package com.ivoronline;

import java.util.*;

public class TestSet {

  public static void main(String arg[]) {
  
    //CREATE MAP
    Set<String>              set = new HashSet();
  //Set<String>              set = new HashSet<String>();

    //ADD ELEMENT
    boolean   added        = set.add("John");     //Returns true if Set doesn't already contain element
              added        = set.add("Bill");

    //REMOVE ELEMENT
    boolean   removed      = set.remove("John");  //Returns true if Set contained element
                             set.clear();         //Remove all elements
                        
    //CHECK FOR ELEMENT
    boolean  containsValue = set.contains("John");
    
    //SIZE
    boolean isEmpty        = set.isEmpty();
    int     size           = set.size   ();
 
    //ITERATE THROUGH ELEMENTS
    for (String element : set) {
      System.out.println(element);
    }

    //DISPLAY HASHSET
    System.out.println(set);                      //{John=50, Bill=60}
    
  }

}
