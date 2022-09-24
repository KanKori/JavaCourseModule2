package com.repository.educator;

import com.config.HibernateSessionFactoryUtil;
import com.model.educator.Educator;
import com.repository.ICrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class EducatorRepository implements ICrudRepository<Educator>, IEducatorRepository {

    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private static final Logger LOGGER = LoggerFactory.getLogger(EducatorRepository.class);

    private static EducatorRepository instance;

    protected EducatorRepository(EducatorRepository instance) {
        EducatorRepository.instance = instance;
    }

    public static EducatorRepository getInstance() {
        if (instance == null) {
            instance = new EducatorRepository();
        }
        return instance;
    }
    private Session session;

    public EducatorRepository() {
        HibernateSessionFactoryUtil.getInstance();
        session = HibernateSessionFactoryUtil.getSession();
    }

    @Override
    public void save(Educator educator) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(educator);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveAll(List<Educator> educators) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Educator educator : educators) {
            session.merge(educator);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Educator> getAll() {
        Session session = sessionFactory.openSession();
        List<Educator> educators = session.createQuery("select educator from Educator educator", Educator.class).getResultList();
        session.close();
        return educators;
    }

    @Override
    public Optional<Educator> findById(String id) {
        Session session = sessionFactory.openSession();
        Optional<Educator> educator = Optional.ofNullable(session.find(Educator.class, id));
        session.close();
        return educator;
    }

    @Override
    public boolean update(Educator educator) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(educator);
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
    public List<Educator> findByFirstAndLastName(String firstName, String lastName) {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Educator e where e.firstName = :firstName or e.lastName = :lastName", Educator.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }
}
