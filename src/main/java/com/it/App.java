package com.it;

import com.it.dao.*;
import com.it.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final EventDAO eventDAO = EventDAOImpl.getInstance();
    private static final ClientDAO clientDAO = ClientDAOImpl.getInstance();
    private static final ServiceDAO serviceDAO = ServiceDAOImpl.getInstance();
    private static final EmployeeDAO employeeDAO = EmployeeDAOImpl.getInstance();
    private static final EquipmentDAO equipmentDAO = EquipmentDAOImpl.getInstance();

    public static void main( String[] args )
    {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session.toString());
        }

        /*Client c = new Client();
        c.setName("Client 1");
        clientDAO.save(c);

        Service s = new Service();
        s.setName("Service 1");
        serviceDAO.save(s);

        Resource em = new Employee();
        em.setName("Job Jobbins");
        employeeDAO.save((Employee)em);

        Resource eq = new Equipment();
        eq.setName("Job Jobbins");
        equipmentDAO.save((Equipment)eq);

        Set<Resource> resources = new HashSet<>();
        resources.add(em);
        resources.add(eq);

        Event event = new Event();
        event.setClient(c);
        event.setService(s);
        event.setResources(resources);
        eventDAO.save(event);*/

    }
}
