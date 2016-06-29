package by.iba.gomel.logic;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

import by.iba.gomel.Constants;
import by.iba.gomel.controllers.SessionController;
import by.iba.gomel.model.ListUsers;
import by.iba.gomel.model.User;

/**
 * This class uses for business logic of application associated with user.
 */
public class UserLogic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param user
     *            user for validating.
     * @return result of validating.
     */
    public String validateUser(final User user) {
        final User response = getResponseFromRest(Constants.URL_REST_VALIDATE_USER, user,
                User.class);
        if (response != null) {
            SessionController.setAttributesSession(Constants.ATTRIBUTE_NAME_LOGIN,
                    response.getUserName());
            SessionController.setAttributesSession(Constants.ATTRIBUTE_NAME_TYPE,
                    response.getType());
            return Constants.RESULT_SUCCESS;
        } else {
            SessionController.addMessage(Constants.MESSAGE_LOGIN_ERROR,
                    Constants.TAG_ERROR_MESSAGE_PASSWORD);
            return Constants.RESULT_FAIL;
        }
    }

    /**
     *
     * @param user
     *            this user will be registered in application.
     * @return result of registration.
     */
    public boolean registerUser(final User user) {
        if (user.getPassword().equals(user.getConfirmedPassword())) {
            final String response = getResponseFromRest(Constants.URL_REST_REGISTER_USER, user,
                    String.class);
            if (response.equals(Constants.RESULT_SUCCESS)) {
                return true;
            } else {
                SessionController.addMessage(Constants.MESSAGE_REGISTRATION_LOGIN_ERROR,
                        Constants.TAG_ERROR_MESSAGE_REGISTR);
            }
        } else {
            SessionController.addMessage(Constants.MESSAGE_REGISTRATION_PASSWORDS_ERROR,
                    Constants.TAG_ERROR_MESSAGE_REGISTR);
        }
        return false;
    }

    /**
     * 
     * @return result of user's output.
     */
    public boolean logOutUser() {
        SessionController.getSession().invalidate();
        return true;
    }

    /**
     * 
     * @return list of all users.
     */
    public List<User> getAllUsers() {
        final RestClient rest = new RestClient();
        final Resource resource = rest.resource(Constants.URL_REST_GET_ALL_USERS);
        final ListUsers response = resource.contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML).get(ListUsers.class);
        if (response.getListUsers() == null) {
            SessionController.addMessage(Constants.MESSAGE_WRONG_VIEW, null);
        }
        return response.getListUsers();
    }

    /**
     * 
     * @param user
     *            this user will be deleted form database.
     */
    public void deleteUserFromDB(final User user) {
        final String response = getResponseFromRest(Constants.URL_REST_DELETE_USER, user,
                String.class);
        if (response.equals(Constants.RESULT_SUCCESS)) {
            SessionController.addMessage(Constants.MESSAGE_DELETE_USER_SUCCESS, null);
        } else {
            SessionController.addMessage(Constants.MESSAGE_DELETE_USER_ERROR, null);
        }
    }

    private <T, E> E getResponseFromRest(final String URL, final T object, final Class<E> type) {
        final RestClient rest = new RestClient();
        final Resource resource = rest.resource(URL);
        final E response = resource.contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML).post(type, object);
        return response;
    }

}
