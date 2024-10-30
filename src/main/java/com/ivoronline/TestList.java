package com.ivoronline;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestList {

  //=========================================================================================================
  // MAIN
  //=========================================================================================================
  public static void main(String arg[]) {
    basic();
    sort();
    extract();
  }
  
  //=========================================================================================================
  // BASIC
  //=========================================================================================================
  public static void basic() {
  
    //CREATE EMPTY LIST
    List                    list0 = new ArrayList();         //Store Objects
    List<String>            list  = new ArrayList();         //Store Strings
    List<String>            list1 = new ArrayList<String>(); //Store Strings
    List<String>            list3 = Collections.EMPTY_LIST;  //Immutable empty List of Strings
    
    //CREATE & INITIALIZE - Using List Methods
    List<String>            list5 = new ArrayList(){{ add("John"); add (0, "Bill"); }};
    List<String>            list4 = List.of("John", "Bill");                          //Immutable List
    
    //CREATE & INITIALIZE - Using Arrays.asList()
    List<String>            list6 =                 Arrays.asList("John", "Bill");    //Immutable List
    List<String>            list7 = new ArrayList<>(Arrays.asList("John", "Bill"));   //Immutable List
    
    //CREATE & INITIALIZE - Using Collections Methods
    List<String>            list8 = Collections.unmodifiableList(list);               //Immutable List
    List<String>            list9 = Collections.singletonList("John");                //Immutable List 1 item
    
    //CREATE & INITIALIZE - Using Streams
    List<String>            list10 = Stream.of("John", "Bill").collect(Collectors.toList());
    List<String>            list11 = Stream.of("John", "Bill").collect(Collectors.toCollection(ArrayList::new));
    List<String>            list12 = Stream.of("John", "Bill").collect(Collectors.collectingAndThen(Collectors.toList(),
                            Collections::unmodifiableList));
    
    //ADD ELEMENTS
    Collections.addAll(list, "John", "Bill");               //Add elements at the end of List
                            list.add(    "John");           //Add element  at the end of List
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

  //=========================================================================================================
  // SORT
  //=========================================================================================================
  public static void sort() {
  
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();;
    Collections.addAll(
       listPerson
      ,new Person(1L, "Susan", 40L)
      ,new Person(2L, "Jill" , 20L)
      ,new Person(3L, "John" , 50L)
      ,new Person(4L, "Bob"  , 30L)
    );
    
    //SORT - Using List Methods
    listPerson.sort(Comparator.comparing(Person::getAge));                  //Sort by Age
    listPerson.sort(Comparator.comparing(Person::getName));                 //Sort by Name

    //SORT - Using Collections Methods
    Collections.sort(listPerson, Comparator.comparingLong(Person::getAge)); //Sort by Age

    //DISPLAY LISTS
    System.out.println(listPerson);
    
  }
  
  //=========================================================================================================
  // EXTRACT
  //=========================================================================================================
  public static void extract() {
  
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();;
    Collections.addAll(
       listPerson
      ,new Person(1L, "Susan", 40L)
      ,new Person(2L, "Jill" , 20L)
      ,new Person(3L, "John" , 50L)
      ,new Person(4L, "Bob"  , 30L)
    );
    
    //EXTRACT IDS
    List<Long> listId = listPerson
      .stream()
      .map(Person::getId)
      .collect(Collectors.toList());

    //DISPLAY LISTS
    System.out.println(listId);
    
  }

}
