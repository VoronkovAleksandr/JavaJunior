# JavaJunior

## Домашнее задание к Семинару 2.

Задача 1:

    Создайте абстрактный класс "Animal" с полями "name" и "age".
    Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
    Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
    Выведите на экран информацию о каждом объекте.
    Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
Решение:

    В модуле HomeWork002 содержится результат выполнения задачи 1 домашнего задания к Семинару 2

Дополнительная задача:

    Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
    В классе QueryBuilder который мы разработали на семинаре.

Решение:
    
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey){
        StringBuilder query = new StringBuilder("DELETE FROM ");


        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query
                                .append(columnAnnotation.name())
                                .append(" = '").append(primaryKey).append("'");
                        break;
                    }

                }
            }

        }
        else {
            return "";
        }
        return query.toString();
    }

## Домашнее задание к Семинару 3.

Задание 1

    Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
    Обеспечьте поддержку сериализации для этого класса.
    Создайте объект класса Student и инициализируйте его данными.
    Сериализуйте этот объект в файл.
    Десериализуйте объект обратно в программу из файла.
    Выведите все поля объекта, включая GPA, и ответьте на вопрос,
    почему значение GPA не было сохранено/восстановлено.

Задание 2.
    
    * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).

Решение:

    В модуле HomeWork003 содержится результат выполнения домашнего задания к Семинару 3

## Домашнее задание к Семинару 4.

Задание:

    Создайте базу данных (например, SchoolDB).
    В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
    Настройте Hibernate для работы с вашей базой данных.
    Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
    Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
    Убедитесь, что каждая операция выполняется в отдельной транзакции.

Решение:

    В модуле HomeWork004 содержится результат выполнения домашнего задания к Семинару 4