# ring-example

A simple example for REST services using ring/compojure and java.jdbc

## Usage
The application is using a mysql database. I assume that you are able to setup mysql and
an empty database. To initialize the database on startup, uncomment the init handler in project.clj

Note to self: use
```
lein ring uberwar
```
to generate a war including all dependencies.

## License

Copyright © 2013 Ralph Guderlei
Distributed under the MIT license.
