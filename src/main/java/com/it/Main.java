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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Main {
    private static final ClientDAO clientDAO = ClientDAOImpl.getInstance();
    private static final ServiceDAO serviceDAO = ServiceDAOImpl.getInstance();
    private static final ResourceDAO resourceDAO = ResourceDAOImpl.getInstance();
    private static final EventDAO eventDAO = EventDAOImpl.getInstance();

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private EventRepository eventRepository;

    public static void main(String[] args) {
       // testHibernate();
        testSpringData();
    }

    public static void testHibernate() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session.toString());
        }

        //transcient
        Client c = new Client();
        c.setName("Vasya");
        clientDAO.save(c);
        //persistent
        c = clientDAO.getOne(c.getId());

        Service s = new Service();
        s.setName("man's haircut");
        serviceDAO.save(s);
        s = serviceDAO.getOne(s.getId());

        Resource employee = new Employee();
        employee.setName("Master");
        resourceDAO.save(employee);
        employee = resourceDAO.getOne(employee.getId());

        Resource equipment = new Equipment();
        equipment.setName("Seat");
        resourceDAO.save(equipment);
        equipment = resourceDAO.getOne(equipment.getId());

        Event event = new Event();
        event.setClient(c);
        event.setService(s);
        Set<Resource> resources = new HashSet<Resource>();
        resources.add(employee);
        resources.add(equipment);
        event.setResources(resources);
        eventDAO.save(event);
    }

    public static void testSpringData(){

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //System.out.println(annotatedClassApplicationContext.getBeanDefinitionNames().toString());

        Main main = appContext.getBean(Main.class);

        //transcient
        Client c = new Client();
        c.setName("Tanya");
        main.clientRepository.save(c);
        //persistent
        c = main.clientRepository.getOne(c.getId());

        Service s = new Service();
        s.setName("woman's haircut");
        main.serviceRepository.save(s);
        s = main.serviceRepository.getOne(s.getId());

        Resource employee = new Employee();
        employee.setName("Master_");
        main.resourceRepository.save(employee);
        employee = main.resourceRepository.getOne(employee.getId());

        Resource equipment = new Equipment();
        equipment.setName("Seat_");
        main.resourceRepository.save(equipment);
        equipment = main.resourceRepository.getOne(equipment.getId());

        Event event = new Event();
        //event.setClient(c);
        event.setService(s);
        Set<Resource> resources = new HashSet<Resource>();
        resources.add(employee);
        resources.add(equipment);
        event.setResources(resources);
        main.eventRepository.save(event);
    }
}