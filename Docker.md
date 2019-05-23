Docker
--
* [Install Docker Toolbox](https://docs.docker.com/v17.12/toolbox/toolbox_install_windows/)
* [Install VM 4.3.10 or 4.3.12](https://www.virtualbox.org/wiki/Download_Old_Builds_4_3_pre24)
* Run **Kitematic**
* In case of error: *ENOENT: no such file or directory, open '/Users/<redacted>/.docker/machine/machines/default/ca.pem'*, 
use: `docker-machine -D regenerate-certs default`
* Open [Hub.Docker](https://hub.docker.com/)
* Search `selenium/hub`
* Open `Docker Quickstart Terminal`
* Use command `docker run -d -P --name selenium-hub selenium/hub`
* Use command `docker ps`
* Back **Kitematic**
* Containers -> selenium-hub -> IP&PORTS -> ACCESS URL
* Open browser with **ACCESS URL**
* See the **Selenium Grid Hub** open of this page
* Use command for download v.2.53.1 `docker run -d -P --name selenium-hub selenium/hub:2.53.1`
* To see all docker images, use: `docker images`
* Use command `docker run -d -p 4446:4444 --name selenium-hub -P selenium/hub:2.53.1`
* Use command `docker run -d -P --link selenium-hub:hub selenium/node-firefox-debug:2.53.1`
* Use command `docker run -d -P --link selenium-hub:hub selenium/node-chrome-debug:2.53.1`
* Docker compose (add docker-compose.yml, cd path to file)`docker-compose up -d`