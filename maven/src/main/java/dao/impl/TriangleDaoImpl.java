package dao.impl;

import dao.TriangleDao;
import model.Triangle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class TriangleDaoImpl implements TriangleDao {
    public List<Triangle> selectAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM Triangle", Triangle.class)
                .getResultList();
    }
    public Triangle selectById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Triangle.class, id);
    }

    public Triangle selectBySides(double a, double b, double c) {
        String hql = "FROM Triangle T WHERE T.a = :a AND T.b = :b AND T.c = :c";
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(hql);
        query.setParameter("a", a);
        query.setParameter("b", b);
        query.setParameter("c", c);
        return (Triangle) query.getSingleResult();
    }

    public void save(Triangle triangle) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(triangle);
        tx1.commit();
        session.close();
    }
}
