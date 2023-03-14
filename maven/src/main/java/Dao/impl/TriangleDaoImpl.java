package Dao.impl;

import Dao.ITriangleDaoImpl;
import Model.Triangle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;

public class TriangleDaoImpl implements ITriangleDaoImpl {
    public List<Triangle> SelectAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("FROM Triangle", Triangle.class)
                .getResultList();
    }
    public Triangle SelectById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Triangle.class, id);
    }

    public Triangle SelectBySides(double a, double b, double c) {
        String hql = "FROM Triangle T WHERE T.A = :a AND T.B = :b AND T.C = :c";
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(hql);
        query.setParameter("a", a);
        query.setParameter("b", b);
        query.setParameter("c", c);
        return (Triangle) query.getSingleResult();
    }

    public void Save(Triangle triangle) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(triangle);
        tx1.commit();
        session.close();
    }

    public void Update(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "update Triangle set isvalidtriangle = :isValid where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("isValid", true);
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
