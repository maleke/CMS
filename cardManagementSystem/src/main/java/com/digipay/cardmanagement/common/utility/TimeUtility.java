package com.digipay.cardmanagement.common.utility;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeUtility {

  public static String getCurrentDate() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    Date date = new Date(System.currentTimeMillis());
    return formatter.format(date);
  }
}
