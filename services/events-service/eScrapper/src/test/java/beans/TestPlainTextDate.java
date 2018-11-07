package beans;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlainTextDate {

    @Test
    public void testGetters() {
       PlainTextDate plainTextDate = new PlainTextDate("1", "1", "mon");

        assertEquals(plainTextDate.getDay(), "1");
        assertEquals(plainTextDate.getDayname(), "mon");
        assertEquals(plainTextDate.getMonth(), "1");
    }

    @Test
    public void testSetters() {
        PlainTextDate plainTextDate = new PlainTextDate();
        plainTextDate.setDayname("mon");
        plainTextDate.setDay("1");
        plainTextDate.setMonth("1");

        assertEquals(plainTextDate.getDay(), "1");
        assertEquals(plainTextDate.getDayname(), "mon");
        assertEquals(plainTextDate.getMonth(), "1");
    }
}
