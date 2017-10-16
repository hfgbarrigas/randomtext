Service implemented using spring flux that exposes a REST api and uses mongodb database to compute word frequency
and execution time from random text api.

Build
=======
mvn clean install

During build time a mongo container is created and deleted in order to run integration tests.

Run
=======
Needs docker installed.

Run - docker-compose build && docker-compose up (or start)

Stop - docker-compose stop

Api
======
curl localhost:8080/betvictor/text? - H 'Accept: application/json'
```
@Min(1) @RequestParam(name = "p_start", required = true) Integer pStart,
@RequestParam(name = "p_end", required = true) Integer pEnd,
@Min(1) @RequestParam(name = "w_count_min", required = true) Integer wCountMin,
@RequestParam(name = "w_count_max", required = true) Integer wCountMax
```

curl localhost:8080/betvictor/history - H 'Accept: application/json'

TODO
======
- remove dangling containers when build fails or it's interrupted
(docker ps -> docker kill/stop)
- Some improvements, move some values over to properties and leverage configuration server
