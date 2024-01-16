import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.*;

public class Student implements Externalizable {
    private String name;

    private int age;

    private transient double GPA;

    public Student() {
    }

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonIgnore
    public Double getGPA() {
        return GPA;
    }

    public void setGPA(Double GPA) {
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Данные о студенте:\n" +
                "Имя: " + name + "\n" +
                "Возраст: " + age + "\n" +
                "Средний балл: " + GPA + "\n";
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
        if (!out.getClass().getSimpleName().equals("ObjectOutputStream")) {
            out.writeDouble(GPA);
        } else {
            out.writeDouble(0);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
        GPA = in.readDouble();
    }
}
