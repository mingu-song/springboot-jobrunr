package mingu.spring.jobrunr;

import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@RestController
public class JobrunrController {
    private final JobScheduler jobScheduler;
    private final JobrunrService jobrunrService;

    @GetMapping("/run/simple/{name}")
    public ResponseEntity<String> runSimpleWithName(@PathVariable String name) {
        jobScheduler.enqueue(() -> jobrunrService.execute(name));
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/run/future/{name}")
    public ResponseEntity<String> runFutureWithName(@PathVariable String name) {
        jobScheduler.schedule(Instant.now().plus(Duration.ofMinutes(1)), () -> jobrunrService.execute(name));
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/run/repeat/{name}")
    public ResponseEntity<String> runRepeatWithName(@PathVariable String name) {
        jobScheduler.scheduleRecurrently(Cron.every30seconds(), () -> jobrunrService.execute(name));
        return ResponseEntity.ok("OK");
    }
}
