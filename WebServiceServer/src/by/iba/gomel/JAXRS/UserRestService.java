package by.iba.gomel.JAXRS;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import by.iba.gomel.Constants;
import by.iba.gomel.factories.DatabaseDAOFactory;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.model.User;

/**
 * This class describes methods for REST interface.
 */
@Path(Constants.REST_PATH_USER)
@Produces(MediaType.APPLICATION_XML)
@Stateless
public class UserRestService {

    @PersistenceContext(name = Constants.PERSISTENT_NAME_DB2)
    private EntityManager em;
    private DatabaseDAO   data;

    @PostConstruct
    public void init() {
        data = DatabaseDAOFactory.getInstance(em);
    }

    /**
     * 
     * @param user
     *            user.
     * @return user.
     */
    @POST
    @Path(Constants.REST_PATH_VALIDATE_USER)
    @Consumes(MediaType.APPLICATION_XML)
    public User validateUser(final User user) {
        final User gotUser = data.checkUser(user.getUserName(), user.getPassword());
        return gotUser;
    }

    /**
     * 
     * @param user
     *            user.
     * @return result of register user.
     */
    @POST
    @Path(Constants.REST_PATH_REGISTER_USER)
    @Consumes(MediaType.APPLICATION_XML)
    public String registerUser(final User user) {
        if (data.registerUser(user)) {
            return Constants.RESULT_SUCCESS;
        } else {
            return Constants.RESULT_FAIL;
        }
    }

    /**
     * 
     * @return list of users.
     */
    @GET
    @Path(Constants.REST_PATH_GET_ALL_USERS)
    public List<User> getAllUsers() {
        return data.getUsers();
    }

    /**
     * 
     * @param user
     *            user.
     * @return result of deleting user.
     */
    @POST
    @Path(Constants.REST_PATH_DELETE_USER)
    @Consumes(MediaType.APPLICATION_XML)
    public String deleteUser(final User user) {
        if (data.deleteUser(user)) {
            return Constants.RESULT_SUCCESS;
        } else {
            return Constants.RESULT_FAIL;
        }
    }

}
