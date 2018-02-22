![Healthcare demo header](docs/images/header.png?raw=true "Healthcare demo")

### Prerequisites

- Requires Ansible 2.4 or newer
- Requires PostgreSQL 9.5 or newer
- Expects Linux or MacOS hosts

### Installing the healthcare demo

``` bash
$ cd healthcare-demo
$ ansible-playbook install.yaml
$ ansible-playbook configure.yaml
```

### Running the healthcare demo

``` bash
$ cd healthcare-demo/target
$ ansible-playbook run.yaml
```
