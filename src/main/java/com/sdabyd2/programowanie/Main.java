package com.sdabyd2.programowanie;

import com.sdabyd2.programowanie.entity.BookEntity;
import com.sdabyd2.programowanie.entity.CategoryEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    private static final SessionFactory sessionFactory;
    static {
        try{
            Configuration configuration = new Configuration();
            configuration.configure(); //domyślnie odwołuje sie do pliku hibernate.cfg.xml
            sessionFactory = configuration.buildSessionFactory(); //sesja przechowuje połączenie do DB
        }catch(Throwable ex){
            throw new ExceptionInInitializerError(); //wyrzucany wyjatek gdy nie uda sięstworzyć sesji
        }
    }
    public static Session getSession() throws HibernateException{
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        Transaction tx = null;
        Session session = getSession();

        BookEntity bookEntity= new BookEntity();
        bookEntity.setAuthor("J. Rowling");
        bookEntity.setISBN("180479025632");
        bookEntity.setTitle("Harry Potter i kamień filozoficzny");
        bookEntity.setCategory("fantasy");
        bookEntity.setPublisher("Media Rodzina");

        tx = session.beginTransaction();
        session.save(bookEntity);
        tx.commit();

        List<BookEntity> bookEntitiesList = session.createCriteria(BookEntity.class).list();
        bookEntitiesList.forEach(b-> System.out.println(b.getTitle()));

        //List<BookEntity> listInAnotherWayMaked = session.createQuery("FROM" + BookEntity.class.getName()).list();   //z uzyciem HQL
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Powieść obyczajowa");

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setName("Powieść detektywistyczna");

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setName("Baśń");

        tx = session.beginTransaction();
        session.save(categoryEntity);
        session.save(categoryEntity1);
        session.save(categoryEntity2);
        tx.commit();

        List<CategoryEntity> categoryEntityList = session.createQuery("FROM" + CategoryEntity.class.getName()).list();
        for(CategoryEntity categoryEntity3: categoryEntityList){
            System.out.println(categoryEntity3);
        }

    }
}
