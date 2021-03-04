package com.biz2tech.app.config;

import java.util.regex.Pattern;

/**
 * Application constants.
 */
public final class Constants {

  // Regex for acceptable logins
  public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

  public static final String SYSTEM_ACCOUNT = "system";
  public static final String ANONYMOUS_USER = "anonymoususer";
  public static final String DEFAULT_LANGUAGE = "en";
  public static final int DEFAULT_PAGE_SIZE = 1000;
  public static final Pattern PATTERN_COMMA = Pattern.compile(",");

  private Constants() {}
}
