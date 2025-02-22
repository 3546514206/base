package com.java8.annotations;

/**
 * @author landyl
 * @create 5:55 PM 03/02/2018
 */
// Using variant 2 the java compiler implicitly sets up the @Hints annotation under the hood.
// That's important for reading annotation informations via reflection.
//Variant 1: Using the container annotation (old school)
//@Hints({@Hint("hint1"), @Hint("hint2")})
//Variant 2: Using repeatable annotations (new school)
@Hint("hint1")
@Hint("hint2")
class Person {

}
