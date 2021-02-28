/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

import csulb.cecs323.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 */
public class StarterApplication {
   private EntityManager entityManager;

   private static final Logger LOGGER = Logger.getLogger(StarterApplication.class.getName());

   public StarterApplication(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("starter_unit");
      EntityManager manager = factory.createEntityManager();
      StarterApplication hw4application = new StarterApplication(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      hw4application.createStudentEntity();

      tx.commit();
      LOGGER.fine("End of Transaction");

   }

   /**
    * Create and persist a Student object to the database.
    */
   public void createStudentEntity() {
      LOGGER.fine("Creating Student object");

      List<Student> students = new ArrayList<>();
      Student student = new Student();
      student.setFirstName("Grace");
      student.setLastName("Hopper");
      student.setGpa(4);

      students.add(student);

      student = new Student();
      student.setFirstName("Steve");
      student.setLastName("Wozniak");
      student.setGpa(3.9);

      students.add(student);

      this.entityManager.persist(student);
      LOGGER.info("Persisted to DB: " + student);
      LOGGER.info("Transient object: " + students.get(0));

      // Need to flush changes to DB to update the in-memory object with its auto-generated id
      this.entityManager.flush();
      LOGGER.info("Persisted object after flush (non-null id): " + student);
   }

}
