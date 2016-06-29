package by.iba.gomel.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import by.iba.gomel.Constants;

@XmlRootElement(name = Constants.ELEMENT_USERS)
@XmlAccessorType(XmlAccessType.FIELD)
public class ListUsers {

    @XmlElement(name = Constants.ELEMENT_USER)
    private List<User> listUsers;

    public ListUsers() {
        // empty constructor.
    }

    /**
     * @return the listUsers
     */
    public List<User> getListUsers() {
        return listUsers;
    }

    /**
     * @param listUsers
     *            the listUsers to set
     */
    public void setListUsers(final List<User> listUsers) {
        this.listUsers = listUsers;
    }

}
