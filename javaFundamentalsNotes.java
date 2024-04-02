import java.util.HashMap;

public class javaFundamentalsNotes {
    {
        /*
        Compiling Java code -> bytecode
        JVM (Java Virtual Machine) executes bytecode

        OOP Postulates:
        1. Encapsulation is the bundling of data and
        methods that operate on that data into a single unit, called a class.
        2. Abstraction refers to the process of simplifying complex reality by
        modeling classes appropriate to the problem, and working at the most relevant
        level of detail. It allows programmers to focus on the essential aspects of an
        object while hiding unnecessary implementation details.
        3. Inheritance is a mechanism that allows a class (subclass or derived class) to
        inherit properties and behavior (methods) from another class (superclass or base class).
        4. Polymorphism allows objects of different classes to be treated as objects of a common
        superclass. It enables methods to be written that can operate on objects of various types
        and classes, leading to more flexible and reusable code. @Override
        5. Association represents the relationship between two or more classes, where objects of
        one class are related to objects of another class.
        6. Composition is a strong form of association where one class (the composite) contains
        objects of another class (the component) as part of its state.
        7. Aggregation is a special form of association where objects of one class are associated
        with objects of another class in a whole-part relationship. Unlike composition,
        the component objects can exist independently of the composite object,
        and their lifecycles are not tightly bound.

        in cmd:
        Compiling javac helloWorld.java -> get HelloWorld.class
        Executing java HelloWorld

         Types:

         boolean - True or False
         char - Unicode from UTF-16
         byte - 8 bit signed integer
         short - 16 bit signed integer
         int - 32 bit signed integer
         long - 64 bit signed integer
         float - 32 bit floating point (IEE 754-1985)
         double - 64 bit floating point

         Order of compiling:
         - associate the fields with their default values
         - execute explicit initializers
         - execute initialization blocks
         - constructor


    * private - available only in that class
    * none/package - available in whole package
    * protected - available in package and inherited class
    * public - global access

         */
    }
    public class FromObject extends Object {
        public String toString() {
            return "";
        }
        /*This method returns a string representation of the object.
        By default, it returns the class name followed by the "@" sign and the hash code of the object.
        It's often overridden in subclasses to provide more meaningful string representations.
         */

        public boolean equals(Object obj) {
            return true;
        }
        /*This method compares the current object with the specified object for equality.
        By default, it compares object references (memory addresses).
        Subclasses often override this method to define their own notion of equality.
         */

        public int hashCode() {
            return 0;
        }
        /*This method returns a hash code value for the object.
        It's used in conjunction with the equals() method to implement hash-based collections like HashMap and HashSet.
         */

        // getClass();
        /*This method returns the runtime class of the object as an instance of the Class class.
        It's useful for obtaining runtime information about objects.
         */

        public FromObject clone() {
            return new FromObject();
        }
        /*This method creates and returns a copy of the object.
        It's used to create a shallow copy of an object.
        However, to use this method effectively, the class must implement the Cloneable interface and override the clone() method.
         */

        public void finalize() {}
        /*This method is called by the garbage collector before reclaiming the memory occupied by the object.
        It's rarely used since it's not guaranteed when or if the finalize() method will be invoked.
         */

        /*
        wait(), notify(), and notifyAll(): These methods are used for inter-thread communication and synchronization.
        They're typically used in multithreaded programming to coordinate the activities of multiple threads.
        */
    }
        static final double pi=3.14;
        double n = javaFundamentalsNotes.pi;
        /*
         * static fields - has only one occurrence per class
         * static method - can directly access only to static methods and static fields, to others need reference
         * static block - initialize static fields and other statements. While executing can throw only exceptions
         that can me caught.
         *
         */
}




