package com.repository.group;

import com.config.HibernateSessionFactoryUtil;
import com.model.group.Group;
import com.model.student.Student;
import com.repository.ICrudRepository;
import com.repository.grade.GradeRepository;
import com.repository.subject.SubjectRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupRepository implements ICrudRepository<Group>, IGroupRepository {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectRepository.class);

    @Override
    public void save(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Group> groups) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Group group : groups) {
            session.save(group);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Group> getAll() {
        Session session = sessionFactory.openSession();
        List<Group> groups = session.createQuery("select group from Group group", Group.class).getResultList();
        session.close();
        return groups;
    }

    @Override
    public Optional<Group> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Group> group = Optional.ofNullable(session.find(Group.class, id));
        session.close();
        return group;
    }

    @Override
    public boolean update(Group group) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(group);
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
    public List<Group> findGroupByName(String name) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);

        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        TypedQuery<Group> query = session.createQuery(criteriaQuery);
        List<Group> groups = query.getResultList();

        session.close();
        return groups;
    }

    @Override
    public Map<Group, Integer> getCountStudentInEveryGroup() {
        List<Group> groups = getAll();

        Map<Group, Integer> result = new LinkedHashMap<>();
        for (Group group : groups) {
            int count = group.getStudents().size();
            result.put(group, count);
        }
        return result;
    }

    @Override
    public Map<Group, Double> getCountGroupsGPA(GradeRepository gradeRepository) {
        Map<Group, Double> result = new LinkedHashMap<>();

        List<Group> groups = getAll();

        for (Group group : groups) {
            List<Student> students = group.getStudents();
            int studentsCount = students.size();
            double totalAvg = 0;
            for (Student student : students) {
                double avg = gradeRepository.getStudentGPA(student);
                totalAvg += avg;
            }
            double avg = totalAvg / studentsCount;
            result.put(group, avg);
        }
        return result;
    }
}
