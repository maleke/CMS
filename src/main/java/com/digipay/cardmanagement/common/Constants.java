package com.digipay.cardmanagement.common;

public class Constants {
  public static final String EXCHANGE_NAME = "notification.topic";
  public static final String ROUTING_KEY_NAME = "notification.*.*";
  public static final String INCOMING_QUEUE_NAME = "notificationFetchQueue";
  public static final String DEAD_LETTER_QUEUE_NAME = "notificationFetchQueueDlx";
  public static final String SUCCESSFUL_TRANSFER_MONEY = "انتقال وجه با موفقیت انجام شد";
}
