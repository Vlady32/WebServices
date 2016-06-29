package by.iba.gomel.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class describes field of response to SOAP service.
 */
@XmlRootElement
public class DataResponse {

    private List<Record> listRecords;
    private int          quantityPages;
    private boolean      resultAddingRecord;

    public List<Record> getListRecords() {
        return listRecords;
    }

    public void setListRecords(final List<Record> listRecords) {
        this.listRecords = listRecords;
    }

    public int getQuantityPages() {
        return quantityPages;
    }

    public void setQuantityPages(final int quantityPages) {
        this.quantityPages = quantityPages;
    }

    /**
     * @return the resultAddingRecord
     */
    public boolean isResultAddingRecord() {
        return resultAddingRecord;
    }

    /**
     * @param resultAddingRecord
     *            the resultAddingRecord to set
     */
    public void setResultAddingRecord(final boolean resultAddingRecord) {
        this.resultAddingRecord = resultAddingRecord;
    }

}
