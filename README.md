![Healthcare demo header](docs/images/header.png?raw=true "Healthcare demo")

### Prerequisites

- Expects Linux or MacOS hosts
- Requires Ansible 2.4 or newer
- Requires PostgreSQL 9.5 or newer
- Optional Android Studio 3.0.1

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
