## Healthcare demo

### Prerequisites

#### System Requirements

1. At least 8 GB of available RAM
2. At least 10 GB of available disk space
3. At least 2 CPU cores

#### Software Requirements
1. [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
   * Ensure that **JAVA_HOME** environment variable is set
   * Ensure that **$JAVA_HOME/bin** is on the **PATH** so that you can run **java** on command line at prompt from any folder
2. [JBoss Developer Studio 11.2.0](https://developers.redhat.com/download-manager/file/devstudio-11.2.0.GA-installer-standalone.jar) 
3. [JBoss Data Virtualization 6.4.0](https://developers.redhat.com/download-manager/file/jboss-dv-6.4.0-installer.jar)
4. [JBoss Data Grid 7.1.0 Server](https://developers.redhat.com/download-manager/file/jboss-datagrid-7.1.0-server.zip)
5. [JBoss Data Grid 7.1.2 Server Update](https://access.redhat.com/jbossnetwork/restricted/softwareDownload.html?softwareId=56221)
6. [JBoss Data Grid 7.1.2 Hot Rod Java Client Module for JBoss EAP ](https://access.redhat.com/jbossnetwork/restricted/softwareDownload.html?softwareId=56241)

#### Using Ansible
To configure the environment using Ansible one might change the default installation directory of Red Hat JBoss Data Grid and Red Hat JBoss Data Virtualization to accomodate your environment.

- Change <installpath></installpath> value in files/xml/dv64-install.xml
- Change install_home variable in group_vars/all.yml


