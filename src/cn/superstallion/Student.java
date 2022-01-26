package cn.superstallion;

@ToString
public class Student extends Person{
    private String name;

    @ToString
    public String getName() {
        return name;
    }
}
