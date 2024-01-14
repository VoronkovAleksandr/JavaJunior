public class Cat extends Animal{
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Cat(String name, int age) {
        super(name, age);
        setSound("Мяу-мяу");
    }
}
