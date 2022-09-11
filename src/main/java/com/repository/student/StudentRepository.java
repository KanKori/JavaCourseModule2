package com.repository.student;

import com.config.HibernateSessionFactoryUtil;
import com.model.student.Student;
import com.repository.ICrudRepository;
import com.repository.grade.GradeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StudentRepository implements ICrudRepository<Student>, IStudentRepository {

    private static final Random RANDOM = new Random();

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepository.class);

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public Student create() {
        return new Student(
                "FName" + RANDOM.nextInt(100),
                "LName" + RANDOM.nextInt(100),
                RANDOM.nextInt((90-16)+16),
                LocalDate.now());
    }

    @Override
    public void save(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Student> students) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Student student : students) {
            session.save(student);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Student> getAll() {
        Session session = sessionFactory.openSession();
        List<Student> students = session.createQuery("select student from Student student", Student.class).getResultList();
        session.close();
        return students;
    }

    @Override
    public Optional<Student> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Student> student = Optional.ofNullable(session.find(Student.class, id));
        session.close();
        return student;
    }

    @Override
    public boolean update(Student student) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(student);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            LOGGER.info(String.valueOf(e));
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(findById(id).orElse(null));
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            LOGGER.info(String.valueOf(e));
            return false;
        }
    }

    @Override
    public List<Student> findStudentWithGPAHigherThan(double inputGPA, GradeRepository gradeRepository) {
        List<Student> result = new ArrayList<>();
        List<Student> studentsAll = getAll();

        for (Student student : studentsAll) {
            double avg = gradeRepository.getStudentGPA(student);
            if (avg > inputGPA) {
                result.add(student);
            }
        }
        return result;
    }
}
