public class Dog extends Animal {
    private double speed;

    public Dog(String name, int age) {
        super(name, age);
        setSound("Гав-гав");
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void run() {
        System.out.printf("Собака %s бежит со скоростью %d", getName() , speed);
    }
}
