package by.iba.gomel;

/**
 * This class contains all constants which uses in this application.
 */
public class Constants {

    public static final int    DEFAULT_PAGE                         = 1;
    public static final int    NUMBER_VIEW_PAGE                     = 0;
    public static final int    NUMBER_ADD_PAGE                      = 1;
    public static final int    NUMBER_EDIT_PAGE                     = 2;
    public static final int    NUMBER_SEARCH_PAGE                   = 3;
    public static final int    NUMBER_CONTROL_PAGE                  = 4;
    public static final int    NUMBER_USERS                         = 50;

    public static final String ATTRIBUTE_NAME_LOGIN                 = "login";
    public static final String ATTRIBUTE_NAME_TYPE                  = "type";
    public static final String ATTRIBUTE_LINK_TYPE                  = "link";
    public static final String ATTRIBUTE_TYPE_USER                  = "type_user";
    public static final String ATTRIBUTE_PAGE_TYPE                  = "page";

    public static final String DEFAULT_PATH_NO_IMAGE                = "C:\\photos\\no_picture.png";
    public static final String FILE_MESSAGES_PROPERTY               = "resources.messages";
    public static final String FILE_REQUESTS_PROPERTY               = "resources.requests_db2";
    public static final String FILE_CONFIG_PROPERTY                 = "resources.config";
    public static final String RECORD_BEAN_NAME                     = "recordBean";
    public static final String SUB_BEAN_NAME                        = "subBean";
    public static final String USER_BEAN_NAME                       = "userBean";
    public static final String DEFAULT_PATH_IMAGE                   = "/files/no_picture.png";
    public static final String DEFAULT_DEFAULT_SEPARATOR            = "/";
    public static final String PATH_VALUE_PHOTOS                    = "C:\\photos";
    public static final String DEFAULT_ENCODING                     = "UTF-8";
    public static final String PARAMETER_ALL                        = "all";
    public static final String TAG_ERROR_MESSAGE_REGISTR            = "registrationForm:confirmPassword";
    public static final String TAG_ERROR_MESSAGE_PASSWORD           = "loginForm:passwordLogin";
    public static final String TAG_ERROR_ADD_FORM                   = "addForm";
    public static final String TAG_CHANGE_DELETE_FORM               = "changeDeleteForm";
    public static final String TABLE_NAME_USERS                     = "users";
    public static final String TABLE_NAME_RECORDS                   = "records";
    public static final String PERSISTENT_NAME_DB2                  = "PhoneBookEJB";
    public static final String NAMESPACE_GOOGLE                     = "google";
    public static final String NAMESPACE_GOOGLE_URL                 = "http://google.com";
    public static final String HEADER_ELEMENT_COMPANY               = "company";
    public static final String HEADER_ELEMENT_COMPANY_GOOGLE_VALUE  = "google";
    public static final String ELEMENT_HANDLER_NAME                 = "handle";
    public static final String ELEMENT_RETURN                       = "return";
    public static final String DATE_FORMAT                          = "yyyy-MM-dd HH:mm:ss";
    public static final String ELEMENT_LIST_RECORDS                 = "listRecords";
    public static final String ELEMENT_USERS                        = "users";
    public static final String ELEMENT_USER                         = "user";

    public static final String PROPERTY_PATH_LOGIN_PAGE             = "path.page.login";
    public static final String PROPERTY_PATH_MAIN_PAGE              = "path.page.main";

    public static final String RESULT_SUCCESS                       = "success";
    public static final String RESULT_SUCCESS_PROFILE               = "successProfile";
    public static final String RESULT_FAIL                          = "fail";
    public static final String RESULT_LOG_OUT                       = "logout";

    public static final String TYPE_USER                            = "user";
    public static final String TYPE_GUEST                           = "guest";
    public static final String TYPE_ADMIN                           = "admin";
    public static final String TYPE_EMPTY                           = "";

    public static final String PAGE_VIEW                            = "/jsf/view.xhtml";
    public static final String PAGE_ADD                             = "/jsf/add.xhtml";
    public static final String PAGE_CONTROL                         = "/jsf/control.xhtml";
    public static final String PAGE_EDIT                            = "/jsf/edit.jsf";
    public static final String PAGE_SEARCH                          = "/jsf/search.jsf";

    public static final String EXCEPTION_EXCEPTION                  = "exception";
    public static final String SOAP_EXCEPTION                       = "SOAPException";
    public static final String UNSUPPORTED_OPERATION_EXCEPTION      = "UnsupportedOperationException";
    public static final String IO_EXCEPTION                         = "IOxception";
    public static final String PARSER_CONFIGURATION_EXCEPTION       = "ParserConfigurationException";
    public static final String JAXB_EXCEPTION                       = "JAXBException";
    public static final String FILE_NOT_FOUND_EXCEPTION             = "FileNotFoundException";
    public static final int    ONE_KB                               = 1024;

    public static final String PARAMETER_RECORD_ADDRESS             = "address";
    public static final String PARAMETER_RECORD_BIRTHDAY            = "birthDate";
    public static final String PARAMETER_RECORD_FULLNAME            = "fullName";
    public static final String PARAMETER_RECORD_MAIL                = "mail";
    public static final String PARAMETER_RECORD_PHONE_NUMBER        = "phoneNumber";
    public static final String PARAMETER_RECORD_PATH_FILE           = "pathFile";
    public static final String PARAMETER_RECORD_ITEM                = "item";

    public static final String MESSAGE_LOGIN_ERROR                  = "message.loginerror";
    public static final String MESSAGE_REGISTRATION_PASSWORDS_ERROR = "message.registrationpasswordserror";
    public static final String MESSAGE_REGISTRATION_LOGIN_ERROR     = "message.registrationloginerror";
    public static final String MESSAGE_WRONG_VIEW                   = "message.wrongview";
    public static final String MESSAGE_WRONG_CONTROL                = "message.wrongcontrol";
    public static final String MESSAGE_ADD_RECORD_ERROR             = "message.addRecordError";
    public static final String MESSAGE_ADD_RECORD_SUCCESS           = "message.addRecordSuccess";
    public static final String MESSAGE_CHANGE_RECORD_ERROR          = "message.changeRecordError";
    public static final String MESSAGE_CHANGE_RECORD_SUCCESS        = "message.changeRecordSuccess";
    public static final String MESSAGE_DELETE_RECORD_ERROR          = "message.deleteRecordError";
    public static final String MESSAGE_DELETE_RECORD_SUCCESS        = "message.deleteRecordSuccess";
    public static final String MESSAGE_DELETE_USER_ERROR            = "message.deleteUserError";
    public static final String MESSAGE_DELETE_USER_SUCCESS          = "message.deleteUserSuccess";
    public static final String MESSAGE_MENU_VIEWING                 = "main_menu_viewing";
    public static final String MESSAGE_MENU_ADDING                  = "main_menu_adding";
    public static final String MESSAGE_MENU_EDITING                 = "main_menu_editing";
    public static final String MESSAGE_MENU_SEARCHING               = "main_menu_searching";
    public static final String MESSAGE_MENU_CONTROL                 = "main_menu_control_users";
    public static final String MESSAGE_SEARCH_NOTHING               = "search_message_nothing";

    public static final String URL_REST_VALIDATE_USER               = "http://localhost:9080/WebServiceServer/rest/user/validateUser";
    public static final String URL_REST_REGISTER_USER               = "http://localhost:9080/WebServiceServer/rest/user/registerUser";
    public static final String URL_REST_GET_ALL_USERS               = "http://localhost:9080/WebServiceServer/rest/user/getAllUsers";
    public static final String URL_REST_DELETE_USER                 = "http://localhost:9080/WebServiceServer/rest/user/deleteUser";
    public static final String URL_SOAP_RECORD_SERVICE              = "http://localhost:9080/WebServiceServer/RecordSOAPServiceService";

    public static final String PARAMETER_CURRENT_PAGE_NAME          = "currentPage";
    public static final String PARAMETER_EVENT_NAME                 = "parameterEvent";
    public static final String PARAMETER_SEARCH_PHRASE              = "searchPhrase";
    public static final String EVENT_GET_ALL_RECORDS                = "1";
    public static final String EVENT_GET_QUANTITY_PAGES             = "2";
    public static final String PARAMETER_EVENT_ADD_RECORD           = "3";
    public static final String PARAMETER_EVENT_CHANGE_RECORD        = "4";
    public static final String PARAMETER_EVENT_DELETE_RECORD        = "5";
    public static final String PARAMETER_EVENT_SEARCH_RECORD        = "6";

}
