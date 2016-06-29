package by.iba.gomel.JAXWS;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.model.DataRequest;
import by.iba.gomel.model.DataResponse;
import by.iba.gomel.model.Record;

/**
 * This class describes business logic of application.
 */
public class RecordSOAPBusiness implements Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory
                                                         .getLogger(RecordSOAPBusiness.class);
    private DatabaseDAO         data;

    public RecordSOAPBusiness(final DatabaseDAO data) {
        this.data = data;
    }

    /**
     * 
     * @param dataRequest
     *            request to soap service.
     * @return all records.
     */
    public DataResponse getAllRecords(final DataRequest dataRequest) {
        List<Record> listRecords = null;
        if (getQuantityPages().getQuantityPages() != 0) {
            final int start = (int) ((dataRequest.getCurrentPage() - Constants.NUMBER_ONE) * Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE);
            listRecords = data.getRecords(start);
        }
        final DataResponse dataResponse = new DataResponse();
        dataResponse.setListRecords(listRecords);
        return dataResponse;
    }

    /**
     * 
     * @return quantity of pages.
     */
    public DataResponse getQuantityPages() {
        final DataResponse dataResponse = new DataResponse();
        final long quantityRecords = data.getQualityRecords();
        final int quantityPages = (int) Math
                .ceil((quantityRecords / Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE));
        dataResponse.setQuantityPages(quantityPages);
        return dataResponse;
    }

    /**
     * 
     * @param dataRequest
     *            data of request.
     * @return result of adding.
     */
    public DataResponse addRecordInDB(final DataRequest dataRequest) {
        final DataResponse dataResponse = new DataResponse();
        final Record record = getRecordFromRequest(dataRequest);
        if (data.checkExistRecord(record) == null) {
            data.addRecord(record);
            dataResponse.setResultAddingRecord(true);
        } else {
            dataResponse.setResultAddingRecord(false);
        }
        return dataResponse;
    }

    /**
     * 
     * @param dataRequest
     *            data of request.
     * @return result of changing.
     */
    public DataResponse changeRecordInDB(final DataRequest dataRequest) {
        final DataResponse dataResponse = new DataResponse();
        final Record record = getRecordFromRequest(dataRequest);
        if (data.changeRecord(record)) {
            dataResponse.setResultAddingRecord(true);
        } else {
            dataResponse.setResultAddingRecord(false);
        }
        return dataResponse;
    }

    /**
     * 
     * @param dataRequest
     *            data of request.
     * @return result of deleting.
     */
    public DataResponse deleteRecordInDB(final DataRequest dataRequest) {
        final DataResponse dataResponse = new DataResponse();
        final Record record = getRecordFromRequest(dataRequest);
        if (data.deleteRecord(record)) {
            dataResponse.setResultAddingRecord(true);
        } else {
            dataResponse.setResultAddingRecord(false);
        }
        return dataResponse;
    }

    /**
     * 
     * @param searchPhrase
     *            phrase for searching.
     * @return list of found records.
     */
    public DataResponse searchRecord(final DataRequest dataRequest) {
        final DataResponse dataResponse = new DataResponse();
        final List<Record> foundRecords = data.searchRecord(dataRequest.getSearchPhrase()
                .toLowerCase());
        dataResponse.setListRecords(foundRecords);
        return dataResponse;
    }

    /**
     * 
     * @param dataRequest
     *            data of request.
     * @return record.
     */
    private Record getRecordFromRequest(final DataRequest dataRequest) {
        final SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        final Record record = new Record();
        record.setAddress(dataRequest.getAddress());
        record.setFullName(dataRequest.getFullName());
        record.setItem(Integer.parseInt(dataRequest.getItem()));
        record.setMail(dataRequest.getMail());
        record.setPathFile(dataRequest.getPathFile());
        record.setPhoneNumber(dataRequest.getPhoneNumber());
        try {
            if (!dataRequest.getBirthDate().isEmpty()) {
                record.setBirthDate(formatter.parse(dataRequest.getBirthDate()));
            }
        } catch (final ParseException e) {
            RecordSOAPBusiness.LOGGER.error(Constants.PARSE_EXCEPTION, e);
        }
        return record;
    }

}
