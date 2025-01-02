package software.ulpgc.kata_6.architecture.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import static java.time.DayOfWeek.*;

public class Calendar {
    public Iterator<LocalDate> from(LocalDate date){return iteratorFrom(date);}

    private Iterator<LocalDate> iteratorFrom(LocalDate date) {
        return new Iterator<LocalDate>() {
            LocalDate current = date;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public LocalDate next() {
                LocalDate result = current.plusDays(1);
                while(!isWorkingDay(result)) result = result.plusDays(1);
                current = result;
                return result;
            }
        };
    }

    private final static Set<DayOfWeek> workingDays = Set.of(MONDAY, TUESDAY,WEDNESDAY,THURSDAY,FRIDAY);
    private boolean isWorkingDay(LocalDate result) {
        return workingDays.contains(result.getDayOfWeek());
    }
}
