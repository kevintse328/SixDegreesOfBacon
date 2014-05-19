SixDegreesOfBacon
=================

Java app to find the shortest path between an actor and Kevin Bacon.


How to Use
=================
- Create a folder com/sixdegreesofbacon/resources/films.
- Dump .json files describing movies and their casts into the films folder. They should be of the following format:
```
{
  "film": {
    "name": "Four Rooms"
  },
  "cast": [
    {
      "name": "Quentin Tarantino",
    },
    ...
  ]
}
```
- Compile into a .jar.
- Run as follows:
```
java -jar SixDegreesOfBacon.jar "<Actor Name>"
```
The output will look something like below:
```
Loading files...
Searching for shortest path between Johnny Depp and Kevin Bacon...
Johnny Depp-(Public Enemies)->Christian Stolte-(Novocaine)->Kevin Bacon
```
