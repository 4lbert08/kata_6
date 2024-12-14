package software.ulpgc.architecture.control;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Builder> builders;

    public CommandFactory() {
        builders = new HashMap<>();
    }

    public CommandFactory register(String key, Builder value) {
        builders.put(key, value);
        return this;
    }
    public Executor with(HttpServletRequest request, HttpServletResponse response) {
        return commandName -> builders.get(commandName).build(request, response);
    }

    public interface Executor {
        Command build(String commandName);
    }

    public interface Builder {
        Command build(HttpServletRequest request, HttpServletResponse response);
    }
}
