![Healthcare demo header](docs/images/header.png?raw=true "Healthcare demo")

### Prerequisites

- Expects Linux or MacOS hosts
- Requires Ansible 2.4 or newer
- Requires PostgreSQL 9.5 or newer
- Optional Android Studio 3.0.1

### Download Binaries

Download the following binaries from [developers.redhat.com](http://developers.redhat.com) and 
and [Red Hat Customer portal](http://access.redhat.com/downloads) 

1. [JBoss Data Virtualization 6.4.0](https://developers.redhat.com/download-manager/file/jboss-dv-6.4.0-installer.jar)
2. [JBoss Data Grid 7.1.0 Server](https://developers.redhat.com/download-manager/file/jboss-datagrid-7.1.0-server.zip)
3. [JBoss Data Grid 7.1.2 Server Update](https://access.redhat.com/jbossnetwork/restricted/softwareDownload.html?softwareId=56221)
4. [JDG 7.1.2 Hot Rod Java Client Module for JBoss EAP](https://access.redhat.com/jbossnetwork/restricted/softwareDownload.html?softwareId=56241)

and place these in the binaries folder.

### Setup PostgreSQL databases

- Create medication database

``` bash
$ psql -f files/data/medicationdb.ddl 
```

- Create medical database 

``` bash
$ psql -f files/data/medicaldb.ddl 
```


### Installing the healthcare demo

``` bash
$ cd healthcare-demo
$ ansible-playbook install.yaml
```

### Running the healthcare demo

``` bash
$ cd healthcare-demo
$ ansible-playbook run.yaml
```
