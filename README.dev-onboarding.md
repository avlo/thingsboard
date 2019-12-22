below useful info & links to get developers started working locally

# REQUIRED TOOLS
Tool | (Working) Version
--- | ---
git | 2.17.1
java | openjdk 1.8.0_151
maven | Apache Maven 3.6.1
npm | 6.11.3
gradle | 4.4.1

below optional for using modular or clustered architecture:

Tool | (Working) Version
--- | ---
docker | 19.03.5
docker-compose | 1.17.1

# REPOSITORIES
greenfutureworks TB source-code repo:
https://github.com/avlo/thingsboard/

*send nick a github request to get pull request and branch creation permission*

# ARCHITECTURE

modular architecture (what we use in production and what i use locally):

https://thingsboard.io/docs/reference/msa/

as mentioned earlier, if you use above modular architecture, you'll also need docker and docker-compose.

(if you prefer to use monolithic architecture locally instead of modular:

https://thingsboard.io/docs/reference/monolithic/)

*note: for either architecture, we only use mqtt (no http/soap) transport for device telemetry.*
# CONFIGURE BUILD ENVIRONMENT

for either modular/monolithic architecture, TB mvn uses gradle to build sub-modules, so you'll need to configure both maven and gradle truststore.  

for java, run below command or add it to your **~/.bashrc** file:
```sh
$ export MAVEN_OPTS="-Djavax.net.ssl.trustStore=<path_to_your>/java-1.8.0-openjdk-1.8.0.151-1.b12.el6_9.x86_64/jre/lib/security/cacerts"
```
and for gradle, add following lines to **/<your_home_dir>/.gradle/gradle.properties** file:
```sh
systemProp.javax.net.ssl.trustStore=<path_to_your>/java-1.8.0-openjdk-1.8.0.151-1.b12.el6_9.x86_64/jre/lib/security/cacerts
systemProp.javax.net.ssl.trustStorePassword=changeit
```
# BUILD APPLICATION LOCALLY
from project root directory, run:
```sh
~git/thingsboard$ mvn clean install -Dmaven.test.skip=true -Ddockerfile.skip=false -Dlicense.skip=true
```
it'll take some time to complete all modules, once completed, run command:
```sh
~git/thingsboard$ docker images
```
and you should see following:
```sh
thingsboard/tb-web-ui           2.4.1               717bd8b3ce54        4 days ago          188MB
thingsboard/tb-mqtt-transport   2.4.1               b2f70a13b550        13 days ago         704MB
thingsboard/tb-node             2.4.1               16f03f19aec3        13 days ago         871MB
thingsboard/tb-js-executor      2.4.1               189dde9a8b1d        13 days ago         181MB
```

# RUNNING APPLICATION
from thingsboard root directory, go to docker directory:
```sh
~git/thingsboard$ cd docker
~git/thingsboard/docker$
```

then run start docker start command:
```sh
 ~git/thingsboard/docker$ ./docker-start-services.sh 
```

to stop running services, from same directory run:
```sh
~git/thingsboard/docker$ ./docker-stop-services.sh
```

# USEFUL DOCUMENTATION

some useful thingsboard links:
thingsboard documentation home:
	https://thingsboard.io/docs/	
	
guides:
	https://thingsboard.io/docs/guides/
	
widget development
	https://thingsboard.io/docs/user-guide/contribution/widgets-development/

# RAY'S REQUEST

*if tenant & customer are not already created, you'll need to login as 

sysadmin@thingsboard.org 
(pass: sysadmin) 

then create a tenant.  then as a tenant, create a customer.*

#### CREATE TELEMETRY
git pull pub-sub scripts:

https://github.com/avlo/pub-sub

update attributes-data.json and telemetry-data.json as needed

#### RAYS ISSUE & HOW TO REPRODUCE

[official bug submitted to TB](https://github.com/thingsboard/thingsboard/issues/2268)

[file that was modified by TB developers](https://github.com/thingsboard/thingsboard/pull/2271/commits/735bba10536d32b4a06560997c053b7ad8902880) for initial "hiding" fix.  this might be useful as a starting source file to go for future widget work.

[widget development documentation](https://thingsboard.io/docs/user-guide/contribution/widgets-development/) guide might also accomplish same fix as above without modifying application code:
	
### ADDITIONAL RESOURCES

cloud application hosting by http://digitalocean.com

we run a docker-based docklet there and pull docker images from our own docker hub repo at https://hub.docker.com/r/avlo/
