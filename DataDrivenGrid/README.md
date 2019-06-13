Use docker for execute tests on different nodes
---
* Cd folder `/docker`
* Creating nodes `docker-compose up -d`
* Creating scale `docker-compose scale nodechrome=5 nodefirefox=5`
* Stop running containers `docker-compose stop`
* Use `docker-machine stop` to stop host
* Use `docker-machine restart` to restart host
* Run `docker-machine env default` to point to a host called default, 
follow on-screen instructions to complete `env` setup, and run `docker ps`, `docker run hello-world`, and so forth.