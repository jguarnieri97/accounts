package ar.edu.unlam.tpi.accounts.exceptions;

import static ar.edu.unlam.tpi.accounts.utils.Constants.BAD_REQUEST_ERROR;
import static ar.edu.unlam.tpi.accounts.utils.Constants.STATUS_BAD_REQUEST;

public class UserServiceBadRequestException extends GenericException {
    public UserServiceBadRequestException(String detail) {
        super(STATUS_BAD_REQUEST, BAD_REQUEST_ERROR, detail);
    }
}
