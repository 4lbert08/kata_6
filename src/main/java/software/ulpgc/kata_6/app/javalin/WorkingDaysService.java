package software.ulpgc.kata_6.app.javalin;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.ulpgc.kata_6.architecture.control.CommandFactory;
import software.ulpgc.kata_6.architecture.control.Service;

public class WorkingDaysService implements Service {
    private final int port;
    private final CommandFactory factory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
    }

    @Override
    public void start() {
        app =  Javalin.create()
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .start(port);
    }

    private void execute(String command, HttpServletRequest req, HttpServletResponse res) {
        factory.with(req,res).build(command).execute();
    }

    @Override
    public void stop() {
        app.stop();
    }
}
