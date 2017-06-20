package fr.sgib.bankkata;

import fr.sgib.bankkata.infrastructure.Clock;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ClockTest {

    private Clock clock;

    @Before
    public void setUp() throws Exception {
        clock = new TestableClock();
    }

    @Test
    public void return_today_date_in_dd_MM_yyyy_format() throws Exception {

        String today = clock.todayAsString();

        Assertions.assertThat(today).isEqualTo("18/06/2017");
    }

    private class TestableClock extends Clock {

        LocalDate today() {
            return LocalDate.of(2017,06,18);
        }
    }
}
