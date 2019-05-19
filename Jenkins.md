#### Run jenkins
`java -jar jenkins.war`
#### Using different ports for Jenkins
`java -jar -httpPort=9999 jenkins.war`
#### Build
Root POM `<path>\pom.xml`

Goals and options `clean test`
#### HTML Publisher
This plugin publishes HTML reports
#### Post-build Actions
* Publish HTML reports
* HTML directory to archive - `<path>target\surefire-reports\html`
* Index page[s] - `extent.html`
* Report title - `Extent Reports`
* Restart jenkins with: 
`java -Dhudson.model.DirectoryBrowserSupport.CSP="" -jar jenkins.war`
* This build is parameterized --> Add parameter --> Choise Parameter
* Name `browser`
* Choises `firefox, chrome, ie`