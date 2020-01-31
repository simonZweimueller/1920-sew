package at.htl.control;

import at.htl.entity.Person;
import at.htl.entity.Status;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class InitBean {

    @Inject
    PersonRepository personRepository;

    private static final Logger LOGGER = Logger.getLogger(InitBean.class.getSimpleName());

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        //LOGGER.setLevel(Level.WARNING);
        LOGGER.info("The application is starting...");

        Person david = new Person(
                "David",
                LocalDate.of(2001, 9, 24),
                Status.SINGLE
        );

        personRepository.persist(david);
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }
}
