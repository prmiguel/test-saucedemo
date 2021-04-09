# test-saucedemo
Sauce Demo Web UI Tests


### Application Under Test (AUT)
[saucedemo](https://www.saucedemo.com/)

### Test Cases
All test cases are listed in the document [test-cases.txt](./test-cases.txt).

### Setup Test Development Environment

#### Requirements

Install the following software:
- AdoptJDK (at least version 1.8 or higher)
- IntelliJ IDE (CE or EE) - no pluggins needed
- Gradle (optional since the project contains the wrapper)
- Chrome (latest version)
- Firefox (latest version)


### Test Execution
#### Command Line Test Execution
Run all tests:
```sh
./gradlew clean test
```
> You can also set the following parameters:
> -Dsaucedemo.web.url="<your url>"
> -Dbrowser.name="<chrome or firefox>"
> -Dbrowser.headless="<true or false>"
Default values:
- saucedemo.web.url="https://www.saucedemo.com"
- browser.name="chrome"
- browser.headless="true"


### Report
Once the execution is clomplete, you can see the reports in `./reports` folder
- `.\reports\gradle\test-results\test` contains Junit XML files.
- `.\reports\gradle\tests\test\index.html` contains the HTML report.


### TODO
- [ ] Move test cases to markdown file in order to improve readability.

