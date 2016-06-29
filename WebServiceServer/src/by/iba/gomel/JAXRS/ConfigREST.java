package by.iba.gomel.JAXRS;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * Configuration of REST interface.
 */
public class ConfigREST extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(by.iba.gomel.JAXRS.UserRestService.class);
        return classes;
    }

}
