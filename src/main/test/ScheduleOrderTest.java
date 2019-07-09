import com.serviceSystem.service.ScheduledService;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleOrderTest {
    @Test
    void scheduleService() throws InterruptedException {
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
        schedule.scheduleAtFixedRate(new ScheduledService(),0,1, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }
}
