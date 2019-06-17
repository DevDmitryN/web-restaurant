
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {
    @Test
    public void creationObject(){
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(myFormatObj.format(localDateTime));
        String date = "2017-06-17";
        String time = "T11:46:33";
        localDateTime = LocalDateTime.parse(date+time);
        System.out.println(myFormatObj.format(localDateTime));
        localDateTime = LocalDateTime.parse("2017-06-17 11:46:33",myFormatObj);
        System.out.println(localDateTime);
    }
}
