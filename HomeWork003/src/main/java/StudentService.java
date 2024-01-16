import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StudentService {
    public static final String FILE_JSON = "student.json";
    public static final String FILE_BIN = "student.bin";
    public static final String FILE_XML = "student.xml";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static Student loadStudentFromFile(String fileName) {
        Student student = new Student();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    student = objectMapper.readValue(file, Student.class);
                }
                else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
                        student = (Student) ois.readObject();
                    }

                }
                else if (fileName.endsWith(".xml")) {
                    student = xmlMapper.readValue(file, Student.class);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return student;
    }
    public static void saveStudentToFile(String fileName, Student student) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), student);
            }
            else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
                    oos.writeObject(student);
                }
            }
            else if (fileName.endsWith(".xml")) {
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                xmlMapper.writeValue(new File(fileName), student);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
