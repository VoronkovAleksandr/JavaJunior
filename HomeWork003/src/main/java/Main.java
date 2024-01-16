public class Main {
    public static void main(String[] args) {
        Student i = new Student("Александр", 41 ,4.8d);
        Student j = new Student("Егор", 34, 3.5d);
        Student k = new Student("Иван", 25, 4d);

        StudentService.saveStudentToFile(StudentService.FILE_BIN, i);
        StudentService.saveStudentToFile(StudentService.FILE_JSON, j);
        StudentService.saveStudentToFile(StudentService.FILE_XML, k);

        Student c = StudentService.loadStudentFromFile(StudentService.FILE_BIN);
        Student d = StudentService.loadStudentFromFile(StudentService.FILE_JSON);
        Student e = StudentService.loadStudentFromFile(StudentService.FILE_XML);

        System.out.println("Из BIN");
        System.out.println(c);
        System.out.println("Из JSON");
        System.out.println(d);
        System.out.println("Из XML");
        System.out.println(e);


    }
}
