package com.ivoronline;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
  Long   id;
  String name;
  Long   age;
}
