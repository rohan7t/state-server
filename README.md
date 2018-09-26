# State Server!

This is a server to tell us which state, if any, a point is in.
Some simplified geometries are included in states.json (so greatly simplified,
that some of the smaller ones disappear).

## Expected Behavior

  $ ./state-server &
  [1] 21507
  $ curl  -d "longitude=-77.036133&latitude=40.513799" http://localhost:8080/
  ["Pennsylvania"]
  $

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
