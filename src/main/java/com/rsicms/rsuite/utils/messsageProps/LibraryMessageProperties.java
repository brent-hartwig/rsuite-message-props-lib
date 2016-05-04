package com.rsicms.rsuite.utils.messsageProps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Serves up formatted messages from a messages properties file.
 */
public class LibraryMessageProperties {

  private static LibraryMessageProperties localMessageProps;
  public final static String DEFAULT_RESOURCE_PATH = "messages.properties";
  private Properties props;

  /**
   * Derive name of messages properties file from given class.
   * 
   * @param clazz
   * @throws IOException
   */
  protected LibraryMessageProperties(Class<? extends LibraryMessageProperties> clazz)
      throws IOException {
    this(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1).concat(".properties"));
  }

  protected LibraryMessageProperties(String resourcePath) throws IOException {

    this.props = new Properties();
    InputStream inputStream = null;
    try {
      inputStream = getClass().getResourceAsStream(resourcePath);
      if (inputStream == null) {
        throw new IOException(
            new StringBuilder("Unable to find the messages properties file identified by '")
                .append(resourcePath).append("'").toString());
      }
      props.load(inputStream);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (Exception e) {
          // ignore
        }
      }
    }
  }

  public static String get(String key, Object... args) {
    if (localMessageProps == null) {
      try {
        localMessageProps = new LibraryMessageProperties(DEFAULT_RESOURCE_PATH);
      } catch (IOException e) {
        e.printStackTrace();
        return "Oops! Message properties are not available.";
      }
    }
    return String.format(localMessageProps.props.getProperty(key, new StringBuilder("Oops! The \"")
        .append(key).append("\" message key is not defined.").toString()), args);
  }

}
