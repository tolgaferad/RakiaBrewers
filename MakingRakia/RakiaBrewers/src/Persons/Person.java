package Persons;

import Brewery.Brewery;
import Enums.TypeFruit;

public abstract class Person{

    protected String name;
    protected int age;
    protected TypeFruit typeFruit;
    protected Brewery brewery;

    public Person(String name, int age, TypeFruit typeFruit,Brewery brewery) {
        this.name = name;
        this.age = age;
        this.typeFruit = typeFruit;
        this.brewery=brewery;
    }

    public String getName() {
        return name;
    }
}

