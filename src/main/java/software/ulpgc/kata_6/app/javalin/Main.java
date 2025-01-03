package software.ulpgc.kata_6.app.javalin;

import software.ulpgc.kata_6.app.javalin.adapters.WorkingDateAdapter;
import software.ulpgc.kata_6.app.javalin.adapters.WorkingDaysAdapter;
import software.ulpgc.kata_6.architecture.control.CommandFactory;
import software.ulpgc.kata_6.architecture.control.commands.WorkingDateCommand;
import software.ulpgc.kata_6.architecture.control.commands.WorkingDaysCommand;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory()
                .register("working-date", workingDateBuilder())
                .register("working-days", workingDaysBuilder());
        new WorkingDaysService(8080, factory).start();
    }

    private static CommandFactory.Builder workingDaysBuilder() {
        return ((request, response) -> {
            var adapter = new WorkingDaysAdapter();
            return new WorkingDaysCommand(adapter.inputFor(request), adapter.outputFor(response));
        });
    }

    private static CommandFactory.Builder workingDateBuilder() {
        return ((request, response) -> {
            var adapter = new WorkingDateAdapter();
            return new WorkingDateCommand(adapter.inputFor(request), adapter.outputFor(response));
        });
    }
}
