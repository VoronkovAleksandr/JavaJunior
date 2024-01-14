import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main {
    public static void main(String[] args) throws  NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Animal cat = new Cat("Барсик", 2);
        Animal dog = new Dog("Белка", 1);

        Animal[] animals = new Animal[2];
        animals[0] = cat;
        animals[1] = dog;
        Class<?> animalClass;
        StringBuilder sb = new StringBuilder();

        for (Animal animal :
                animals) {
            animalClass = animal.getClass();
            Field[] fields = animalClass.getDeclaredFields();
            Method[] methods = animal.getClass().getDeclaredMethods();
            Method makeSound = animalClass.getMethod("makeSound");

            sb.append("Животное: ");
            sb.append(animal);
            sb.append("\n");
            sb.append("Класс: ");
            sb.append(animalClass.getSimpleName());
            sb.append("\n");
            sb.append("Содержит поля:");
            sb.append("\n");
            for (Field field :
                    fields) {
                sb.append(field.getName());
                sb.append("\n");
            }
            sb.append("Содержит методы:");
            sb.append("\n");
            for (Method method :
                    methods) {
                sb.append(method.getName());
                sb.append("\n");
            }

            sb.append("Результат выполнения makeSound(): ");
            sb.append(makeSound.invoke(animal));

            sb.append("\n");
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
