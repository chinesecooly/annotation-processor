package cn.superstallion;

@ToString
public class Teacher extends Person{
    private String name;
    private String age;

    @ToString
    public String getName() {
        return name;
    }

    @ToString
    public String getAge() {
        return age;
    }
}
