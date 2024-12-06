# Pipes
Sigleplayer game Pipes, where the the task is to connect pipe pieces to make a continuous pipeline. 
This is a desktop JAVA aplication, AWT and Swing libraries are used for graphical user interface.
The layout of pipe pieces is generated by modified [randomized depth first search](https://www.baeldung.com/cs/maze-generation#dfs-maze) algorithm.

## My motivation
This is the second assigment I was given in the "Object-Oriented Programming" course at my university, as well as my first time using Maven. I coded this in April of 2023.
It is overall my third object-ortiented programming project with graphic user interface, as before starting the course, 
I completed four assignments from the same course (from previous years) as a self-directed challenge. Two of them contain GUI. 
My goal was to deepen my understanding of Java and object-oriented programming principles, as well as to prepare myself for the actual course. 
These projects can be found on my GitHub: 
[Duck hunt](https://github.com/amj-j/duck-hunt-java), 
[Monopoly lite](https://github.com/amj-j/monopoly-lite-java), 
[Reversi](https://github.com/amj-j/reversi-java)
and [Rook in maze](https://github.com/amj-j/rook-in-maze-java).

### Goal
The purpose of this assignment is to practide the foundational principles of GUI development,
such as event-driven programming and component-based design,
which are also used in modern frameworks, through the Java Swing library.

## Prerequisites
You need to have Java 1.8 (also known as Java 8) or higher installed on you computer to run this app.

## Installation
1. Download the ```pipes.jar``` file.
2. Open your terminal and navigate to folder in which the downloaded ```pipes.jar``` file is located.
3. Type this command into the terminal to run the app: ```java -jar pipes.jar```.
4. The app will run in a new window.

## Gameplay
There is a set of pipe pieces in a rectangular area. They can all together make a continuous pipeline. 
Each pipe piece is already in its place, however, it is rotated randomly, which makes the pipeline disconnected.
Players task is to rotate the pipe pieces correctly by clicking on them with mouse to create a continuous pipeline. (All provided pipe pieces must be used.)
Both tiles that contain the end pieces of the pipeline are highlighted (they have an outline).
After connecting the pieces, the "Check" button in the menu must be clicked, to check if the pieces are aligned correctly.

### Menu
- component for changing the size of the rectangle containing pipe pieces (the bigger the size, the harder the difficulty).
- current size text
- score - counter of solved puzzles
- Reset button - resets the score and loads a new set of pipe pieces
- Check button - checks the alignment of the tiles
  - if all pieces are rotated corectly, score is increased by 1 and new level is loaded
  - if not, the correctly rotated pieces from the beginning of the pipeline are highlighted in green and the first incorrectly roatated piece is highlighted in red
- changing the size will reset the score

## Architecture
This was the third time I tried to implement MVC pattern, to make the game logic indipendent of the AWT and Swing classes. Similarly to the last time, (my [Rook in maze](https://github.com/amj-j/rook-in-maze-java) project), I opted for an approach where the view and the model communicate with each other exclusively through the controller. The main controller, found in ```PipesController.java``` implements the ```IEventListener``` interface and is added as user input listener to the view. It also contains instances of the implementation of view interface (```MainWindow```) and of model interface (```PipesModel```). On user input, the event listener (controller) is notified by the view about the event and calls corresponding method that makes changes to the model calling its methods and then calls view methods to update the view accordingly. The main MVC classes are instantiated inside the ```MVCInitializer.java``` file. Project lombok dependency is included in maven configuration file ```pom.xml``` to automatize the generation of some getters, setters and constructors. 
