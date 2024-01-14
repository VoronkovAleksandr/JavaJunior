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
