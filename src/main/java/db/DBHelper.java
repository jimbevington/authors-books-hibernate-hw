package db;

import models.Book;
import org.hibernate.*;

import java.util.List;

public class DBHelper {

    private static Transaction transaction;
    private static Session session;

    public static void save(Object object){

        session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> List<T> getAll(String className){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;

        try {
            transaction = session.beginTransaction();
            String hql = "from " + className;
            results = session.createQuery(hql).list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


    public static void deleteAll(String className){
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            String hql = "delete from " + className;
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Book> getBooksByAuthor(int authorId){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Book> books = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from Book where authorId = :authorId";
            Query query = session.createQuery(hql);
            query.setInteger("authorId", authorId);
            books = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return books;
    }


    public static <T> T findByID(String className, int id){

        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;

        try {
            transaction = session.beginTransaction();
            String hql = "from " + className + " where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", id);
            results = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

//        return first item if results isn't empty ...
        if (results.size() > 0) {
            return results.get(0);
        }
//        ... otherwise, return null
        return null;
    }


}
