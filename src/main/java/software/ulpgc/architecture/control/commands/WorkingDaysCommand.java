package software.ulpgc.architecture.control.commands;

import software.ulpgc.architecture.control.Command;
import software.ulpgc.architecture.model.Calendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDaysCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDaysCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        output.workingDays(calculateWorkingDays());
    }

    private int calculateWorkingDays() {
        Iterator<LocalDate> from = new Calendar().from(input.start());
        LocalDate current = input.start();
        int days = 0;
        while (current.isBefore(input.end())) {
            days++;
            current = from.next();
        }
        return days;
    }


    public interface Input {
        LocalDate start();
        LocalDate end();
    }

    public interface Output {
        void workingDays(int days);
    }

}
