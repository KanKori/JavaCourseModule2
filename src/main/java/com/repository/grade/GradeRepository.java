package com.repository.grade;

import com.config.HibernateSessionFactoryUtil;
import com.model.grade.Grade;
import com.model.student.Student;
import com.model.subject.Subject;
import com.repository.ICrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeRepository implements ICrudRepository<Grade>, IGardeRepository {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private static final Logger LOGGER = LoggerFactory.getLogger(GradeRepository.class);

    private static GradeRepository instance;

    protected GradeRepository(GradeRepository instance) {
        GradeRepository.instance = instance;
    }

    public static GradeRepository getInstance() {
        if (instance == null) {
            instance = new GradeRepository();
        }
        return instance;
    }
    private Session session;

    public GradeRepository() {
        HibernateSessionFactoryUtil.getInstance();
        session = HibernateSessionFactoryUtil.getSession();
    }

    public Grade create(List<Student> studentList, Subject subject, int value) {
        return  new Grade(studentList, subject, value);
    }
    @Override
    public void save(Grade grade) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(grade);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Grade> grades) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        for (Grade grade : grades) {
            session.merge(grade);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Grade> getAll() {
        session = sessionFactory.openSession();
        List<Grade> grades = session.createQuery("select grade from Grade grade", Grade.class).getResultList();
        session.close();
        return grades;
    }

    @Override
    public Optional<Grade> findById(String id) {
        session = sessionFactory.openSession();
        Optional<Grade> grade = Optional.ofNullable(session.find(Grade.class, id));
        session.close();
        return grade;
    }

    @Override
    public boolean update(Grade grade) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(grade);
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
    public double getStudentGPA(Student student) {
        double total = 0;
        List<Subject> subjectList = new ArrayList<>();
        List<Grade> grades = getAll();

        for (Grade grade : grades) {
            Subject subject = grade.getSubject();
            int value = grade.getValue();
            List<Student> students = grade.getStudents();
            if (students.contains(student)) {
                subjectList.add(subject);
                total += value;
            }
        }

        double subjectCount = subjectList.size();
        return total / subjectCount;
    }
}
