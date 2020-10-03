package part1.lesson02.task03;

public class Person {
    private int age;
    private String name;
    //private Sex sex = new Sex();

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
//        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Sex getSex() {
//        return this.sex;
//    }

//    public void setSex(String sex) {
//        //this.sex = this.sex.getMan() == sex ?  this.sex.getMan() :  this.sex.getWomen();
//        this.sex = this.sex.man;
//        this.sex = new Sex().getWoman();
//        this.sex = (String)this.sex.getMan();
//    }
}
