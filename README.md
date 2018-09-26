# State Server!

Vistar serves up a mound of geospatial data both internally and to third
parties. What we need is a server to tell us which state, if any, a point is in.
Some simplified geometries are included in states.json (so greatly simplified,
that some of the smaller ones disappear).

It need not be fast, but the code should be readable, and the results should be
correct.

## Expected Behavior

  $ ./state-server &
  [1] 21507
  $ curl  -d "longitude=-77.036133&latitude=40.513799" http://localhost:8080/
  ["Pennsylvania"]
  $


## Notes

Given that file, it took one of us about an hour to implement something that
worked correctly. You're welcome to take it however far you want, but we're
expecting something along those lines.

And if there's anything special we have to do to run your program, just let us
know. A Makefile never hurt anyone.

## Solution
Tools used:
* Java 8 : 1.8.0_181
* Spring Boot : 2.0.5
* Maven : 3.5.4
* Docker (optional) : 8.06.0

Referred the Ray Casting Algorithm (https://en.wikipedia.org/wiki/Point_in_polygon).

## How to Run
There are two options to run
* Docker 

If you have docker installed, run the following command: `./docker.sh`


* Terminal 

From the source folder run, `./state-server.sh`

## Hitting the Server
Open a terminal to hit the web application server. Curl request:
`curl  -d "longitude=-77.036133&latitude=40.513799" http://localhost:8080/ `