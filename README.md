# rsuite-message-props-lib
RSuite Java library of message property utilities.

Version 0.0.1 is not yet operational.  A fix may be to have the extension override get(String, Object...) to ensure it is using a singleton instance.

Draft usage pattern:

1. Consumers of this library are to extend LibraryMessageProperties.  Example:

'
package com.rsicms.rsuite.containerWizard;

import java.io.IOException;

import com.rsicms.rsuite.utils.messsageProps.LibraryMessageProperties;

/**
 * Serves up formatted messages from a messages properties file whose name base
 * name matches this class' name, extension is ".properties", and may be found
 * in the same JAR file.
 */
public class ContainerWizardMessageProperties extends LibraryMessageProperties {

  public ContainerWizardMessageProperties() throws IOException {
    super(ContainerWizardMessageProperties.class);
  }

}
'

2. Consumers are also to include a Java message properties file in the same JAR as their LibraryMessageProperties extension.  The file name should match the Java class extension name + ".properties".  Continuing the previous example, the filename should be 'ContainerWizardMessageProperties.properties'.

