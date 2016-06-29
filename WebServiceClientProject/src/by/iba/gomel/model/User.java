package by.iba.gomel.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import by.iba.gomel.Constants;

/**
 * This class contains fields and methods for working with user.
 */
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            userName;
    private String            password;
    @XmlTransient
    private String            confirmedPassword;
    @XmlTransient
    private String            type             = Constants.TYPE_USER;

    public User() {
        // Empty constructor.
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(final String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
