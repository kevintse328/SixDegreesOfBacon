SixDegreesOfBacon
=================

Java app to find the shortest path between an actor and Kevin Bacon


How to Use
=================
- Create a folder com/sixdegreesofbacon/resources/films.
- Dump .json files describing movies and their casts into the films folder. They should be of the following format:
```
{
  "film": {
    "image": "http://image.tmdb.org/t/p/w185/zbwxBgGO9wtauX8dDfG16ZYZdn.jpg",
    "name": "Four Rooms"
  },
  "cast": [
    {
      "name": "Quentin Tarantino",
      "image": "http://image.tmdb.org/t/p/w185/6grjDWpEIPL5QdRbUZFxVEp5TCd.jpg"
    },
    ...
  ]
}
```
- Compile into a .jar.
- Run as follows: java -jar SixDegreesOfBacon.jar "<Actor Name>"
```
java -jar SixDegreesOfBacon.jar "Johnny Depp"
```
The output will look something like below:
```
Loading files...
Searching for shortest path between Johnny Depp and Kevin Bacon...
Johnny Depp-(Public Enemies)->Christian Stolte-(Novocaine)->Kevin 
```
