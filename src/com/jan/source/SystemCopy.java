package com.jan.source;

import java.util.concurrent.ConcurrentHashMap;

class Animal implements Cloneable{
    private String name;

    private Person person;

    public Animal(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class User {
    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Person implements Cloneable{
    private int id;

    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Person() {
    }

    public Person(int id, User user) {
        this.id = id;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = null;
        p = (Person)super.clone();
        return p;
    }
}

public class SystemCopy {
    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User("jan", "ee");
        User u2 = new User("liao", "aa");
        User u3 = new User("fang", "ff");
        User[] src = new User[3];
        src[0] = u1;src[1] = u2;src[2] = u3;
        User[] dest = new User[3];
        System.arraycopy(src, 0, dest, 0, 3);
        System.out.println(src[0] == dest[0]);
        dest[0].setEmail("99");
        System.out.println(src[0]);
        System.out.println(dest[0]);

        int[] arr = {1, 2, 3, 4};
        int[] arr1 = new int[4];
        System.arraycopy(arr, 0, arr1, 0, 4);

        Person p = new Person(1, u1);
        System.out.println(p);
        Person clone = (Person) p.clone();
        System.out.println(p == clone);
        System.out.println(clone);

        Animal a = new Animal("dd", p);
        Animal aa = (Animal)a.clone();
        aa.setName("io");
        System.out.println(a);
        System.out.println(aa);

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("ss", "ss");
    }
}
