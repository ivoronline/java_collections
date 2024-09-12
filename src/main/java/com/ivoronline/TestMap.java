package com.ivoronline;

import java.util.*;

public class TestMap {

  public static void main(String arg[]) {
  
    //CREATE MAP
    Map<String, String>         map = new HashMap();                 //Key = Strings, Element = String
  //Map<String, String>         map = new HashMap<String, String>(); //Key = Strings, Element = String

    //ADD (KEY, ELEMENT)
                                map.put("key"    , "element");       //Add key-element
                                map.put("JohnKey", "John"   );       //Add key-element
                                map.put("BillKey", "Bill"   );       //Add key-element
                 
    //GET ELEMENT AT KEY
    String   name             = map.get("key");                      //Get element with Key = "key"
    
    //REMOVE ELEMENT AT KEY
    String   removed          = map.remove("key");                   //Remove element with Key = "key"
                                map.clear ( );                       //Remove all elements
                                
    //CHECK FOR KEY or ELEMENT
    boolean   containsElement = map.containsValue("element");
    boolean   containsKey     = map.containsKey  ("key"    );
    
    //GET SIZE
    boolean  isEmpty          = map.isEmpty();
    int      size             = map.size   ();
    
    //ITERATE THROUGH KEYS
    Iterator<String> iterator = map.keySet().iterator();
    while( iterator.hasNext() == true ) {
      String key = iterator.next();
      System.out.println(key);
    }
    
    //ITERATE THROUGH VALUES
    iterator = map.values().iterator();
    while( iterator.hasNext() == true ) {
      String value = iterator.next();
      System.out.println(value);
    }
    
    //DISPLAY ALL ELEMENTS
    System.out.println(map);                                      //{BillKey=Bill, JohnKey=John, key=element}
    
  }

}
