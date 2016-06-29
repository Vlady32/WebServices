package by.iba.gomel.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class describes field of request to SOAP service.
 */
@XmlRootElement
public class DataRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private int               currentPage;
    private String            searchPhrase;

    private String            item;
    private String            fullName;
    private String            address;
    private String            phoneNumber;
    private String            mail;
    private String            pathFile;
    private String            birthDate;

    public DataRequest() {
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate
     *            the birthDate to set
     */
    public void setBirthDate(final String birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(final String item) {
        this.item = item;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName
     *            the fullName to set
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     *            the mail to set
     */
    public void setMail(final String mail) {
        this.mail = mail;
    }

    /**
     * @return the pathFile
     */
    public String getPathFile() {
        return pathFile;
    }

    /**
     * @param pathFile
     *            the pathFile to set
     */
    public void setPathFile(final String pathFile) {
        this.pathFile = pathFile;
    }

    /**
     * @return the searchPhrase
     */
    public String getSearchPhrase() {
        return searchPhrase;
    }

    /**
     * @param searchPhrase
     *            the searchPhrase to set
     */
    public void setSearchPhrase(final String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

}
