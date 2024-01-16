package mingu.spring.jobrunr;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.annotations.Job;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobrunrService {

    @Job(name = "The parametrized job", retries = 2)
    public void execute(String name) {
        log.info("{} :: The parameterized job with name", name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("Error while executing job", e);
        } finally {
            log.info("{} :: Job has finished...", name);
        }
    }
}
