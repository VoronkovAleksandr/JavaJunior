import models.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        Course course = new Course("Beginner", 6);

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
        }
        System.out.println("Course save successfully");

        try (Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            course.setTitle("intermediate");
            course.setDuration(12);
            session.update(course);
            session.getTransaction().commit();
        }
        System.out.println("Course update successfully");


        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Course retrievedCourse = session.get(Course.class, course.getId());
            session.getTransaction().commit();
            System.out.println(retrievedCourse);
        }
        System.out.println("Course retrieved successfully");




        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
        }
        System.out.println("Course delete successfully");

    }
}
