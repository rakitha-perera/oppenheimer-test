# Oppenheimer Project Test


## <a name="run-tests"></a> Running Tests

### <a name="run-pre-requisites"></a> Configuration

The following properties in the Application.yaml file can be configured. 

* runner.browser: browser that will be used, supported values: `chrome`, `firefox`
* runner.headless: run browser in a headless mode or not, supported values: `true`, `false`
* runner.timeout: default webdriver wait timeout in seconds


* api.base-url: base url for oppenheimer-project Rest API
* api.port: hosted port for oppenheimer-project Rest API


* file-paths.add-user-csv: the temporary location that the csv files to be stored before uploading

### <a name="run-gradle"></a> Running tests via Gradle

Command: `./gradlew clean test

### <a name="run-intellij"></a> Running tests in IntelliJ

* Open project in IntelliJ
* Right-click on the feature folder located under src/test/resources and select 'Run'

## <a name="run-tests"></a> Next Steps

* Integration of a reporting framework (e.g. Extent Reports)

