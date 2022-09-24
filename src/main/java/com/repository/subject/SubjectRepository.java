package com.repository.subject;

import com.config.HibernateSessionFactoryUtil;
import com.model.subject.Subject;
import com.repository.ICrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Random;


public class SubjectRepository implements ICrudRepository<Subject>, ISubjectRepository {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private static final Random RANDOM = new Random();

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectRepository.class);

    private static SubjectRepository instance;

    protected SubjectRepository(SubjectRepository instance) {
        SubjectRepository.instance = instance;
    }

    public static SubjectRepository getInstance() {
        if (instance == null) {
            instance = new SubjectRepository();
        }
        return instance;
    }
    private Session session;

    public SubjectRepository() {
        HibernateSessionFactoryUtil.getInstance();
        session = HibernateSessionFactoryUtil.getSession();
    }

    public Subject create() {
        return new Subject(RANDOM.nextInt(900),
                "ScienceSubject-" + RANDOM.nextInt(100));
    }
    @Override
    public void save(Subject subject) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(subject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Subject> subjects) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Subject subject : subjects) {
            session.merge(subject);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Subject> getAll() {
        Session session = sessionFactory.openSession();
        List<Subject> subjects = session.createQuery("select subject from Subject subject", Subject.class).getResultList();
        session.close();
        return subjects;
    }

    @Override
    public Optional<Subject> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Subject> subject = Optional.ofNullable(session.find(Subject.class, id));
        session.close();
        return subject;
    }

    @Override
    public boolean update(Subject subject) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(subject);
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
    public Subject getBestAvg() {
        String hql = "Select s from Grade g join g.subject s join g.students st_g " +
                "group by g.subject order by avg(g.value) desc";
        Query<Subject> query = session.createQuery(hql, Subject.class);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public Subject getWorstAVG() {
        String hql = "Select s from Grade g join g.subject s join g.students st_g " +
                "group by g.subject order by avg(g.value)";
        Query<Subject> query = session.createQuery(hql, Subject.class);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
}
