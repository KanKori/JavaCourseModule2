package com.repository.group;

import com.config.HibernateSessionFactoryUtil;
import com.model.group.Group;
import com.model.student.Student;
import com.repository.ICrudRepository;
import com.repository.grade.GradeRepository;
import com.repository.subject.SubjectRepository;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupRepository implements ICrudRepository<Group>, IGroupRepository {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();


    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectRepository.class);
    private static GroupRepository instance;

    protected GroupRepository(GroupRepository instance) {
        GroupRepository.instance = instance;
    }

    public static GroupRepository getInstance() {
        if (instance == null) {
            instance = new GroupRepository();
        }
        return instance;
    }
    private Session session;

    public GroupRepository() {
        HibernateSessionFactoryUtil.getInstance();
        session = HibernateSessionFactoryUtil.getSession();
    }

    @Override
    public void save(Group group) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(group);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Group> groups) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        for (Group group : groups) {
            session.merge(group);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Group> getAll() {
        session = sessionFactory.openSession();
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
        return session.createQuery("from group g where g.name like :name", Group.class)
                .setParameter("name", "%" + name + "%")
                .list();
    }

    @Override
    public Map<Group, Integer> getCountStudentInEveryGroup() {
        try (Session session = sessionFactory.openSession()) {
            List<Object> list = session.createQuery("select s.group.name, count(s.id) from student s group by s.group.name").getResultList();
            Map<Group, Integer> studentsByGroup = new HashMap<>();
            list.forEach(pair -> {
                Object[] o = (Object[]) pair;
                studentsByGroup.put((Group) o[0], (Integer) o[1]);
            });
            return studentsByGroup;
        }
    }

    public Map<Group, Double> getCountGroupsGPA() {
        Map<Group, Double> result = new LinkedHashMap<>();

        List<Group> groups = getAll();

        for (Group group : groups) {
            List<Student> students = group.getStudents();
            int studentsCount = students.size();
            double totalAvg = 0;
            for (Student student : students) {
                double avg = GradeRepository.getInstance().getStudentGPA(student);
                totalAvg += avg;
            }
            double avg = totalAvg / studentsCount;
            result.put(group, avg);
        }
        return result;
    }
}
