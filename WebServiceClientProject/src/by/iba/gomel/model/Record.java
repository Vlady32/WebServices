package by.iba.gomel.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class contains fields and methods for working with records.
 */
@XmlRootElement
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlTransient
    private int               item;
    private String            fullName         = null;
    private String            address          = null;
    private String            phoneNumber      = null;
    private Date              creationDate;
    private String            mail             = null;
    private Date              birthDate        = null;
    private String            pathFile         = null;

    public Record() {
        // Empty constructor.
    }

    @PrePersist
    public void createTimestamp() {
        creationDate = new Date();
    }

    public int getItem() {
        return item;
    }

    public void setItem(final int item) {
        this.item = item;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(final String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Record [item=" + item + ", fullName=" + fullName + ", address=" + address
                + ", phoneNumber=" + phoneNumber + ", creationDate=" + creationDate + ", mail="
                + mail + ", birthDate=" + birthDate + ", pathFile=" + pathFile + "]";
    }

}
