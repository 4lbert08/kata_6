package software.ulpgc.kata_6.architecture.control.commands;

import software.ulpgc.kata_6.architecture.control.Command;
import software.ulpgc.kata_6.architecture.model.Calendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDateCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDateCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        output.result(calculateDate());
    }

    private LocalDate calculateDate() {
        Iterator<LocalDate> from = new Calendar().from(input.start());
        LocalDate current = input.start();
        int workingDays = input.workingDays();
        while (workingDays>0) {
            workingDays--;
            current = from.next();
        }
        return current;
    }


    public interface Input {
        LocalDate start();
        int workingDays();
    }

    public interface Output {
        void result(LocalDate result);
    }

}
