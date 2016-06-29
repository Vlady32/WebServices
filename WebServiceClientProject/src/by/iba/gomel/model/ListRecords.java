package by.iba.gomel.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import by.iba.gomel.Constants;

@XmlRootElement(name = Constants.ELEMENT_RETURN)
@XmlAccessorType(XmlAccessType.FIELD)
public class ListRecords {

    @XmlElement(name = Constants.ELEMENT_LIST_RECORDS)
    private List<Record> listRecords;

    public ListRecords() {

    }

    /**
     * @return the listRecords
     */
    public List<Record> getListRecords() {
        return listRecords;
    }

    /**
     * @param listRecords
     *            the listRecords to set
     */
    public void setListRecords(final List<Record> listRecords) {
        this.listRecords = listRecords;
    }

}
