package com.it;

import com.it.dao.*;
import com.it.model.*;
import com.it.repository.ClientRepository;
import com.it.repository.EventRepository;
import com.it.repository.ResourceRepository;
import com.it.repository.ServiceRepository;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class App
{
    private static final ClientDAO clientDAO = ClientDAOImpl.getInstance();
    private static final ServiceDAO serviceDAO = ServiceDAOImpl.getInstance();
    private static final ResourceDAO resourceDAO = ResourceDAOImpl.getInstance();
    private static final EventDAO eventDAO = EventDAOImpl.getInstance();

    @Autowired
    private ClientRepository clientRepository;
    private ServiceRepository serviceRepository;
    private ResourceRepository resourceRepository;
    private EventRepository eventRepository;

    public static void main( String[] args )
    {
//        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        System.out.println(annotatedClassApplicationContext.getBeanDefinitionNames().toString());

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session.toString());
        }

        Client c;

        c = new Client();
        c.setName("Vasya");
        clientDAO.save(c);

        c = new Client();
        c.setName("Andrew");
        clientDAO.save(c);

        c = new Client();
        c.setName("Lena");
        clientDAO.save(c);

        Service s;

        s = new Service();
        s.setName("man's haircut");
        serviceDAO.save(s);

        s = new Service();
        s.setName("woman's haircut");
        serviceDAO.save(s);

        Resource employee;

        employee = new Employee();
        employee.setName("Master1");
        resourceDAO.save(employee);

        employee = new Employee();
        employee.setName("Master2");
        resourceDAO.save(employee);

        Resource equipment;

        equipment = new Equipment();
        equipment.setName("Seat1");
        resourceDAO.save(equipment);

        equipment = new Equipment();
        equipment.setName("Seat2");
        resourceDAO.save(equipment);

        equipment = new Equipment();
        equipment.setName("Seat4");
        resourceDAO.save(equipment);

        equipment = new Equipment();
        equipment.setName("Seat4");
        resourceDAO.save(equipment);

        Event event = new Event();
        event.setClient(c);
        event.setService(s);
        Set<Resource> resources = new HashSet<Resource>();
        resources.add(resourceDAO.getOne(1L));
        resources.add(resourceDAO.getOne(3L));
        event.setResources(resources);
        eventDAO.save(event);

    }
}
