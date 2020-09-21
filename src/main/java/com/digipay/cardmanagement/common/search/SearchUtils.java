package com.digipay.cardmanagement.common.search;

import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.exceptions.error.ErrorCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.logging.Logger;

public class SearchUtils {

  private static Logger logger = Logger.getLogger("SearchUtils");

  public static PageRequest getPageRequest(SearchablePage searchablePage) throws ServiceException {
    String direction =
        searchablePage.getDirection() != null ? searchablePage.getDirection() : "ASC";
    Sort.Direction dir = getDirection(direction);
    if (searchablePage.getOrder() != null)
      return PageRequest.of(
          searchablePage.getPage(), searchablePage.getTotal(), dir, searchablePage.getOrder());
    return PageRequest.of(searchablePage.getPage(), searchablePage.getTotal());
  }

  private static Sort.Direction getDirection(String direction) throws ServiceException {
    Sort.Direction dir;
    try {
      dir = Sort.Direction.fromString(direction);
    } catch (Exception exp) {
      exp.printStackTrace();
      throw new ServiceException(
          "Please make sure direction is valid ", ErrorCode.INTERNAL_ERROR.getCode());
    }
    return dir;
  }
}
