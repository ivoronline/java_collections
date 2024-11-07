package com.ivoronline;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestList {

  //=========================================================================================================
  // MAIN
  //=========================================================================================================
  public static void main(String arg[]) {
    basic();
    extract();
    convertToMap();
    filter();
    sort();
    sortWithNull();
  }
  
  //=========================================================================================================
  // BASIC
  //=========================================================================================================
  public static void basic() {
 
    //LOG
    System.out.println("\n===== BASIC");
    
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
  // EXTRACT
  //=========================================================================================================
  public static void extract() {
 
    //LOG
    System.out.println("\n===== EXTRACT");
    
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();
                 listPerson.add(new Person(1L, "Susan", 40L));
                 listPerson.add(new Person(2L, "Jill" , 20L));
                 listPerson.add(new Person(3L, "John" , 50L));
                 listPerson.add(new Person(4L, "Bob"  , 30L));
    
    //EXTRACT IDS
    List<Long> listId = listPerson.stream()
      .map(Person::getId)
      .collect(Collectors.toList());

    //DISPLAY LISTS
    System.out.println(listId);
    
  }
  
  //=========================================================================================================
  // CONVERT TO MAP
  //=========================================================================================================
  public static void convertToMap() {
  
    //LOG
    System.out.println("\n===== CONVERT TO MAP");
  
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();
                 listPerson.add(new Person(1L, "Susan", 40L));
                 listPerson.add(new Person(2L, "Jill" , 20L));
                 listPerson.add(new Person(3L, "John" , 50L)); //Change to 1L to test when duplicates
                 listPerson.add(new Person(4L, "Bob"  , 30L));
    
    //EXTRACT TO MAP - Use id as Key
    Map<Long, Person> mapPerson = listPerson.stream()
      .collect(Collectors.toMap(Person::getId, Function.identity())); //Exception if duplicate Key found

    //EXTRACT TO MAP - If duplicate Key found => Replace existing Element with next one
    Map<Long, Person> mapPerson1 = listPerson.stream()
      .collect(Collectors.toMap(Person::getId, Function.identity(), (a1, a2) -> a2)); //1L John
      
    //EXTRACT TO MAP - If duplicate Key found => Keep existing Element
    Map<Long, Person> mapPerson2 = listPerson.stream()
      .collect(Collectors.toMap(Person::getId, Function.identity(), (a1, a2) -> a1)); //1L Susan

    //DISPLAY LISTS
    System.out.println(mapPerson);
    System.out.println(mapPerson1);
    System.out.println(mapPerson2);
    System.out.println(mapPerson.get(2));  //Returns null since it is int
    System.out.println(mapPerson.get(2L));
    
  }
  //=========================================================================================================
  // FILTER
  //=========================================================================================================
  public static void filter() {
    
    //LOG
    System.out.println("\n===== FILTER");
    
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();
                 listPerson.add(new Person(1L, "Susan", 40L));
                 listPerson.add(new Person(2L, "Jill" , 20L));
                 listPerson.add(new Person(3L, "NA"   , 20L));
                 listPerson.add(new Person(4L, "John" , 50L));
                 listPerson.add(new Person(5L, "NA"   , 60L));
                 listPerson.add(new Person(6L, "Bob"  , 30L));
    
    //USING PLAIN JAVA
     List<Person> newList = new ArrayList<>();
     for (Person person : listPerson){
        if (person.getAge() > 20) newList.add(person);
     }
    
    //USING STREAM - FILTER BY AGE
    List<Person> filteredListPerson = listPerson.stream()
      .filter(p -> p.getAge() > 20)
      .collect(Collectors.toList());
      
    //USING STREAM - FILTER BY COMPLEX CODE
    filteredListPerson = listPerson.stream()
      .filter(p -> {
         if(20 < p.getAge() && p.getAge() < 60) { return true;  }
         else                                   { return false; }
      })
      .collect(Collectors.toList());
      
    //DISPLAY LISTS
    System.out.println(newList);
    System.out.println(filteredListPerson);
    
  }
  
  //=========================================================================================================
  // SORT
  //=========================================================================================================
  public static void sort() {
  
    //LOG
    System.out.println("\n===== SORT");
    
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();
                 listPerson.add(new Person(1L, "Susan", 40L));
                 listPerson.add(new Person(2L, "Jill" , 20L));
                 listPerson.add(new Person(3L, "NA"   , 20L));
                 listPerson.add(new Person(4L, "John" , 50L));
                 listPerson.add(new Person(5L, "NA"   , 60L));
                 listPerson.add(new Person(6L, "Bob"  , 30L));
    
    //SORT - Using List Methods
    listPerson.sort(Comparator.comparing(Person::getAge )); //Sort by Age
    listPerson.sort(Comparator.comparing(Person::getName)); //Sort by Name

    //SORT - Using Collections Methods
    Collections.sort(listPerson, Comparator.comparing(Person::getName));    //Sort by Name
    Collections.sort(listPerson, Comparator.comparing(Person::getAge ));    //Sort by Age
    Collections.sort(listPerson, Comparator.comparingLong(Person::getAge)); //Sort by Age
    
    Collections.sort(listPerson, new PersonNameComparator());               //Sort by Name
    Collections.sort(listPerson, new PersonNameComparatorNAFirst());        //Sort by Name - NA first
    Collections.sort(listPerson, new PersonNameComparatorNALast());         //Sort by Name - NA last
    
    Collections.sort(listPerson, new PersonAgeComparator());                //Sort by Age
    Collections.sort(listPerson, new PersonAgeComparator30First());         //Sort by Age - 30 first
    Collections.sort(listPerson, new PersonAgeComparator30Last());          //Sort by Age - 30 last

    Collections.sort(listPerson, (a, b) -> {                                //Sort by Name
      return a.getName().compareTo(b.getName());
    });
    
    Collections.sort(listPerson, (a, b) -> {                                //Sort by Name - NA last
      if     (a.getName().equals("NA")) { return  1; }
      else if(b.getName().equals("NA")) { return -1; }
      else   { return a.getName().compareTo(b.getName()); }
    });
    
    //DISPLAY LISTS
    System.out.println(listPerson);
    System.out.println(listPerson.get(2));
    
  }

  //=========================================================================================================
  // SORT WITH NULL
  //=========================================================================================================
  public static void sortWithNull() {
  
    //LOG
    System.out.println("\n===== SORT WITH NULL");
    
    //CREATE PERSON LIST
    List<Person> listPerson = new ArrayList();
                 listPerson.add(new Person(1L, "Susan", 40L));
                 listPerson.add(new Person(2L, "Jill" , 10L));
                 listPerson.add(new Person(7L,  null  , null));
                 listPerson.add(new Person(3L, "NA"   , 20L));
               //listPerson.add(null);                               //For null Objects
                 listPerson.add(new Person(4L, "John" , 30L));
                 listPerson.add(new Person(5L, "NA"   , 30L));
                 listPerson.add(new Person(6L, "Bob"  , 50L));
               //listPerson.add(null);                               //For null Objects
                 listPerson.add(new Person(8L,  null  , null));
                 
    System.out.println(listPerson);

    //SORT - Using List Methods
    listPerson.sort(Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.naturalOrder()))); //Sort by Name
    listPerson.sort(Comparator.comparing(Person::getAge , Comparator.nullsLast (Comparator.naturalOrder()))); //Sort by Age

    //SORT - Using Collections Methods
    Collections.sort(listPerson, Comparator.comparing(Person::getName, Comparator.nullsFirst(Comparator.naturalOrder()))); //Sort by Name
    Collections.sort(listPerson, Comparator.comparing(Person::getAge , Comparator.nullsLast (Comparator.naturalOrder()))); //Sort by Age

    Collections.sort(listPerson, new PersonNameComparatorNullFirst  ());       //Sort by Name - NULL    First
    Collections.sort(listPerson, new PersonNameComparatorNullNAFirst());       //Sort by Name - NULL NA First
    Collections.sort(listPerson, new PersonNameComparatorNullLast   ());       //Sort by Name - NULL    Last
    Collections.sort(listPerson, new PersonNameComparatorNANullLast ());       //Sort by Name - NA NULL Last
    Collections.sort(listPerson, new PersonNameComparatorNANullObjectsLast ());//Sort by Name - NULL Objects

    Collections.sort(listPerson, new PersonAgeComparatorNullFirst());          //Sort by Age  - NULL    First
    Collections.sort(listPerson, new PersonAgeComparatorNull30First());        //Sort by Age  - NULL    First
    Collections.sort(listPerson, new PersonAgeComparatorNullLast());           //Sort by Age  - NULL    Last
    Collections.sort(listPerson, new PersonAgeComparator30NullLast());         //Sort by Age  - NULL    Last
    Collections.sort(listPerson, new PersonAgeComparator30NullObjectsLast());  //Sort by Age  - NULL Objects

    Collections.sort(listPerson, (a, b) -> {                                   //Sort by Name - NULL last
      if     (a.getName() == null) { return  1; }
      else if(b.getName() == null) { return -1; }
      else   { return a.getName().compareTo(b.getName()); }
    });
    
    Collections.sort(listPerson, new PersonAgeComparatorNullLast());           //Sort by Age  - NULL    Last

    //DISPLAY LISTS
    System.out.println(listPerson);
    System.out.println(listPerson.get(2));
    
  }
  
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR
//=========================================================================================================
class PersonNameComparator implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      return a.getName().compareTo(b.getName());
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NULL FIRST
//=========================================================================================================
class PersonNameComparatorNullFirst implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getName() == null) { return -1; }
      else if(b.getName() == null) { return  1; }
      else   { return a.getName().compareTo(b.getName()); }
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NA FIRST
//=========================================================================================================
class PersonNameComparatorNAFirst implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getName().equals("NA")) { return -1; }
      else if(b.getName().equals("NA")) { return  1; }
      else   { return a.getName().compareTo(b.getName()); }
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NULL NA FIRST
//=========================================================================================================
class PersonNameComparatorNullNAFirst implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
    
      if     (a.getName() == null     ) { return  1; }
      else if(b.getName() == null     ) { return -1; }
      
      if     (a.getName().equals("NA")) { return -1; }
      else if(b.getName().equals("NA")) { return  1; }
      
      else   { return a.getName().compareTo(b.getName()); }
      
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NULL LAST
//=========================================================================================================
class PersonNameComparatorNullLast implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getName() == null) { return  1; }
      else if(b.getName() == null) { return -1; }
      else   { return a.getName().compareTo(b.getName()); }
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NA LAST
//=========================================================================================================
class PersonNameComparatorNALast implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getName().equals("NA")) { return  1; }
      else if(b.getName().equals("NA")) { return -1; }
      else   { return a.getName().compareTo(b.getName()); }
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NA LAST NULL LAST
//=========================================================================================================
class PersonNameComparatorNANullLast implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
    
      if     (a.getName() == null     ) { return  1; }
      else if(b.getName() == null     ) { return -1; }
      
      else if(a.getName().equals("NA")) { return  1; }
      else if(b.getName().equals("NA")) { return -1; }
      
      else   { return a.getName().compareTo(b.getName()); }
      
    }
}

//=========================================================================================================
// CLASS: PERSON NAME COMPARATOR NA NULL OBJECTS LAST
//=========================================================================================================
class PersonNameComparatorNANullObjectsLast implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
    
      if     (a           == null     ) { return  1; }
      else if(b           == null     ) { return -1; }
      
      else if(a.getName() == null     ) { return  1; }
      else if(b.getName() == null     ) { return -1; }
      
      else if(a.getName().equals("NA")) { return  1; }
      else if(b.getName().equals("NA")) { return -1; }
      
      else   { return a.getName().compareTo(b.getName()); }
      
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR
//=========================================================================================================
class PersonAgeComparator implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      return Long.compare(a.getAge(), b.getAge());
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR 30 FIRST
//=========================================================================================================
class PersonAgeComparator30First implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      
      if     (a.getAge().equals(30L)) { return -1; }
      else if(b.getAge().equals(30L)) { return  1; }
      
      return Long.compare(a.getAge(), b.getAge());
      
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR NULL FIRST
//=========================================================================================================
class PersonAgeComparatorNullFirst implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getAge() == null) { return -1; }
      else if(b.getAge() == null) { return  1; }
      return Long.compare(a.getAge(), b.getAge());
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR NULL 30 FIRST
//=========================================================================================================
class PersonAgeComparatorNull30First implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
    
      if     (a.getAge() == null    ) { return -1; }
      else if(b.getAge() == null    ) { return  1; }
      
      else if(a.getAge().equals(30L)) { return -1; }
      else if(b.getAge().equals(30L)) { return  1; }
      
      return Long.compare(a.getAge(), b.getAge());
      
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR NULL LAST
//=========================================================================================================
class PersonAgeComparatorNullLast implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getAge() == null) { return  1; }
      else if(b.getAge() == null) { return -1; }
      return Long.compare(a.getAge(), b.getAge());
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR 30 LAST
//=========================================================================================================
class PersonAgeComparator30Last implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
      if     (a.getAge().equals(30L)) { return  1; }
      else if(b.getAge().equals(30L)) { return -1; }
      return Long.compare(a.getAge(), b.getAge());
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR 30 NULL LAST
//=========================================================================================================
class PersonAgeComparator30NullLast implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
    
      if     (a.getAge() == null    ) { return  1; }
      else if(b.getAge() == null    ) { return -1; }
      
      else if(a.getAge().equals(30L)) { return  1; }
      else if(b.getAge().equals(30L)) { return -1; }
      
      return Long.compare(a.getAge(), b.getAge());
      
    }
}

//=========================================================================================================
// CLASS: PERSON AGE COMPARATOR 30 NULL OBJECTS LAST
//=========================================================================================================
class PersonAgeComparator30NullObjectsLast implements java.util.Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
    
      if     (a          == null    ) { return  1; }
      else if(b          == null    ) { return -1; }
      
      else if(a.getAge() == null    ) { return  1; }
      else if(b.getAge() == null    ) { return -1; }
      
      else if(a.getAge().equals(30L)) { return  1; }
      else if(b.getAge().equals(30L)) { return -1; }
      
      return Long.compare(a.getAge(), b.getAge());
      
    }
}
