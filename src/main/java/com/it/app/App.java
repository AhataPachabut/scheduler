package com.it.app;

import com.it.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session.toString());
        }
        System.out.println( "Hello World!" );
    }
}
