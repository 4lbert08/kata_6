package software.ulpgc.app.javalin.adapters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.ulpgc.architecture.control.commands.WorkingDateCommand;

import java.io.IOException;
import java.time.LocalDate;

public class WorkingDateAdapter {
    public WorkingDateCommand.Input inputFor(HttpServletRequest request) {
        return new WorkingDateCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(request.getParameter("start"));
            }

            @Override
            public int workingDays() {
                return Integer.parseInt(request.getParameter("days"));
            }
        };
    }

    public WorkingDateCommand.Output outputFor(HttpServletResponse response){
        return result -> {
            try {
                response.getWriter().write(result.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
