package xyz.markpost.traininformation.request;

import org.springframework.stereotype.Service;

/**
 *
 * TODO: do request
 * TODO: make responseDTO
 * TODO: method ad authentication
 * TODO: error handling
 * TODO: javaDoc
 * TODO: JUnit
 */
@Service
class RequestService {

  RequestService() {

  }

  ResponseDTO doRequest(RequestDTO request) {
    return new ResponseDTO(404, "", "");
  }


}
