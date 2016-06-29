package by.iba.gomel.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import by.iba.gomel.Constants;
import by.iba.gomel.logic.UserLogic;
import by.iba.gomel.model.User;

/**
 * This bean uses for manipulating all requests associated with user.
 */
@ManagedBean(name = Constants.USER_BEAN_NAME)
@ViewScoped
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;
    private User              user;
    private UserLogic         userLogic;

    public UserController() {
        user = new User();
        userLogic = new UserLogic();
    }

    /**
     *
     * @return list of registered users.
     */
    public List<User> getListUsers() {
        return userLogic.getAllUsers();
    }

    /**
     * 
     * @return result of logging.
     */
    public String signIn() {
        return userLogic.validateUser(user);
    }

    /**
     *
     * @return result of registration user.
     */
    public String register() {
        if (userLogic.registerUser(user)) {
            return Constants.RESULT_SUCCESS;
        }
        return null;
    }

    /**
     *
     * @return result of output user.
     */
    public String logOut() {
        if (userLogic.logOutUser()) {
            return Constants.RESULT_LOG_OUT;
        }
        return null;
    }

    /**
     *
     * @param user
     *            deleting this user from database.
     */
    public void deleteUser(final User user) {
        userLogic.deleteUserFromDB(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
