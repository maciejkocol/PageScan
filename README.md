# Automated page scan in Java with Cucumber and Selenium WebDriver #

This project browses a selection of pages and validates the following:
- There are no console errors on page loads
- The response code from the page (200, 302, 404, etc.)
- All links on the page go to another live (non 4xx) page

Test scenarios are defined in the feature files at ./src/test/resources/com/automatedtest/scan.

## Included in the container

- VSCode

Installation
------------

### Docker
- Linux install: http://docs.docker.com/linux/step_one/
- Mac/Windows install: https://www.docker.com/products/docker-desktop

### Setup
```bash
    # Clone this repo
    https://github.com/maciejkocol/pagescan
    
    # Change directory into the cloned repo
    cd pagescan
    
    # Spool up the environment
    docker-compose up -d

    # Access project in browser with VSCode
    # Enter password at prompt: 'assess'
    open http://localhost:8080
    
    # to Shutdown the environment
    docker-compose down
```

## Run tests ##
To run tests inside the container, use a VSCode command line interface, such as `Terminal`. 

To specify browser type, add `-Dbrowser={browser}` where `{browser}` is either `chrome` or `firefox`. 

Sample test run:

```console
mvn test -Dbrowser=chrome
```

Scripts can be accessed locally at `~/automation-home/page-scan/`

## Results ##

Obtain detailed html report at `~/automation-home/page-scan/target/scan-result.html`

