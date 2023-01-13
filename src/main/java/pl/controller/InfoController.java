package pl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Julian-Paul Haslinger (P22080)
 *
 */
@RestController
public class InfoController {

  @Autowired
  private HttpServletRequest context;

  /**
   * fallback for all other requests
   * 
   * @return error message with the request URI + stacktrace (informational)
   */

  @RequestMapping(value = "/info", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<String> info() {
    String version = "Raindrops V5.0.0.1-SNAPSHOT";
    return new ResponseEntity<String>(version + " - DAE5 - WS 2022 (VS Code Version)"
        + " No method selected! Just found the (non-existent) page: '" + context.getRequestURI() + "'!", HttpStatus.OK);
  }
}