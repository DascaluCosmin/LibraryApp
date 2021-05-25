package repository;

import domain.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

public class ReviewRepositoryDB implements ReviewRepository {

    private static final Logger LOGGER = LogManager.getLogger(ReviewRepositoryDB.class);
    private static final SessionFactory SESSION_FACTORY = HibernateSessionFactory.getInstance();

    public ReviewRepositoryDB() {
        LOGGER.info("Initializing ReviewRepositoryDB");
    }

    @Override
    public void save(Review entity) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();
            } catch (RuntimeException exception) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    @Override
    public Review delete(Integer integer) {
        return null;
    }

    @Override
    public void modify(Review entity) {

    }

    @Override
    public Review findOne(Integer integer) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                return session.get(Review.class, integer);
            } catch (RuntimeException exception) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Review> findAll() {
        return null;
    }
}
