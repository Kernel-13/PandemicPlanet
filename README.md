# What is "Pandemic Planet"?
"Pandemic Planet" is a simple java program whose main aim is to estimate how fast a disease can spread through a network, defined by nodes and edges, by simulating an infection in one of the nodes and letting it infect some of their neighbors.

To make the spread of the disease as accurate as possible, we decided to work with 3 simple & basic epidemic models:
  * SI (Susceptible-Infected): Nodes that get infected with the disease will stay infected forever. (HIV, Hepatitis B)
  * SIS (Susceptible-Infected-Susceptible): Nodes can get infected, but can also recover from the infection, becoming healthy nodes again. This also means that they can get infected again. (Ebola, Common cold, etc) (Ebola, Common cold, etc)
  * SIR (Susceptible-Infected-Recovered): Nodes can get infected and recover, but this time the recovered node can't spread the disease / get infected again. This is because a recovered node has either become immune, or died.

## How does it work?
Given a network, the program will proceed to infect one of the nodes on the first day of infection. After that, the spread/erradication of the disease will depend of the infection/recovery rate, which are 2 of the variables the user can choose before beginning with the simulation.

## What do I need?
As I said before, the program works with a network defined by nodes and edges. This network needs to be provided by the user in the form of 2 files: a Nodes file, which must contain information about the nodes, and a Edges file, which must contain the connections between the nodes.<br />
These 2 files must have a special formatting:
  * **Nodes file**: This file must contain an identifier (number) of one node in each line. It can be as simple as:<br />
    1<br />
    2<br />
    3<br />
    ...<br />
    
  * **Edges File**: Must contain 2 identifiers separated by a ';'. This tells the program that X is connected to Y. Example:<br />
    1;2<br />
    2;5<br />
    56;2<br />
    23;5<br />
    ...<br />
    
#### **Have in mind that every ID(number) that appears in your Edges file, must also appear in the Nodes file. Otherwise, the program won't do anything, as it lacks information.**
  
To make things easier, I included example files in the root directory. The nodes/edges information was obtained from the datasets at https://openflights.org/data.html. 

## Now what?
There is nothing else to do. Go on and try it. <br/>
But please have in mind that my program lacks some variables used in real models (like death/births, or human migration), so the results are not really accurate. Don't use it to predict the next epidemic/pandemic (I'm looking at you WHO). <br/>

## External Libraries / Dependencies
* <a href="https://github.com/yannrichet/jmatharray">JMathArray</a>
* <a href="https://github.com/yannrichet/jmathio">JMathIO</a>
* <a href="https://github.com/yannrichet/jmathplot">JMathPlot</a>

## But why?
This program was part of our SNA (Social Netowork Analysis) final project. My friends and I decided to work on a program able to predict the spread of a pathogen on a real-world network, since it would look interesting to someone who doesn't know about SNA, or how is it used in the real world. <br/>

### If you find any bugs / have any questions, you can always find me on twitter (<a href="https://twitter.com/SenbaRambu">@SenbaRambu</a>).
