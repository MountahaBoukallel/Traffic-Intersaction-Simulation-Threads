# Traffic-Intersaction-Simulation-Threads
I built a traffic intersection with traffic lights and moving cars. it was built using java Swing and Threads I hope you like it 
Section 2 - Programmation concurrente de l’application
L’utilisation de principes de programmation concurrente se fait notamment dans les 3 classes java suivantes 
	●	SimulationManager du package controller
	●	CarsGenerator  du package common
	●	TrafficLight du package common 
Le groupe a préférer choisir l’implémentation de interface Runable à l’héritage de la classe Thread pour plusieurs raisons, on en cite le partage des objets qui est très important dans ce type de projet, aussi la liberté laissée par l’inteface ( pas de restriction quand a l’utilisation d’autres classes (héritage non possible avec classe thread), la possibilité d’implémenter d’autre interfaces ... tout cela à été déterminant quand a ce choix décisif)
	1- Simulation Manager : 
Cette classe est la classe principale responsable de la plus grande partie de la simulation.


	2- Cars Generator :
Classe responsable de générer les voitures, 
On a déclarer 3 variables responsable du type de trafic voulue ( heavy trafic , medium trafic, light trafic). Puis on a déclarer an array list qui est responsable des points ou vont étre crée/ générer les voitures ( un point est déclarer pour chaque position géografique )
La méthode  genCars(int traffic, Road road) permet de de créer  les voitures en fonction du paramétrage du trafic définie et dans la route définie. TQ n est  le flot du trafic ( si il y a plusieurs voitures ou non ) de la manière suivante 
heavy trafic = 50 voitures, medium trafic = 30 voitures, light trafic = 10 voitures 
Les conditions : Permettent de créer les voitures , ou elle apparenterons et ou elles disparations et quand une autre voiture aparétera
Puis on a générer une nouvelle voiture avec Car car = new Car(Color.WHITE, p.x, p.y, 40, 30, /*rand.nextInt(5) + 10*/ 15, index);  
Dont les paramètres  ( couleur = blanche, la position géographique  qui permet de définir ou cette voiture sera positionnée quand elle aparetera, les dimensions de cette voiture , la vitesse à la quelle elle est lancée)
Cette voiture créer va initialisée un nouveaux thread en utilisant la methode Thread t = new Thread(car); , qui sera ensuite ajouté avec  threads.add(t);
on a aussi définie un test  System.out.println("x=" + p.x + ", y=" + p.y + ", index=" + index); Qui nous permet simplement de vérifier au niveau de la console si le thread a bien été crée

	3- Traffic Light :
Classe Responsable de la circularité et gestion des feux du carrefour.
Dans sa méthode run qui va permettre de définir quand le sleep sera fait tq chaque valeur du feu ( vert, jaune , rouge) aura un temps d’attente obligatoire différent 
Exemple explicatif avec la route 0 dont l’id = 0
if (id == 0) {
                    Util.roadLight[id] = Color.GREEN; 
                    Thread.sleep(3000);
/* si le feu de la route 0 est vert on imposera un sleep de 3 seconde avant la changement de couleur*/
                    Util.roadLight[id] = Color.YELLOW;
                    Thread.sleep(1000);
/* si le feu de la route 0 est orange on imposera un sleep de 1 seconde avant la changement de couleur ce délais est plus court et suit la la logique véritable des feux de circulation car il permet simplement de vider la voie et d’avertir les future voitures pour diminuer leur vitesse et ne pas s’engager sur l’intersection*/
                    Util.roadLight[id] = Color.RED;
                    Thread.sleep(4000);
/* si le feu de la route 0 est rouge on imposera un sleep de 4 seconde avant la changement de couleur ce qui englobera la durée du feu vert et du feu orange de la voie opposée */

Le même principe est posée et utiliser pour les autres routes avec différents ID, les routes adjacente on le même code car elles s’exécutent en parallèle ( par exemple route id 1 et id 5 sont des routes parallèle ).
	4- 
Section 3- Programmation non concurrente de l’application
1- package model		Ce package regroupe deux classe Direction.java et Car.java
Car.java :
On a commencer par définir les valeur graphique usuelle de la voiture avec dans l’ordre:  sa couleur ,sa position géographique, sa hauteur et largeur, et sa vitesse...etc 
Les variables proximity permet de définir la proximité par rapport au autres voitures, et passed définie si la voiture passe ou non 
Dans la méthode Car(Color color, int x, int y, int width, int height, int speed, int lane) la variable lane entrée en paramètre, permet de définir la direction de la voiture
exemple excplicatif :
 if (lane == 1) {
            this.direction = Direction.N_SE;
            this.indexSem = 3; }
/* lane = 1  donc signifie que la voiture  se dirige du nord vers sud-est */
La méthode run() de cette classe sert a définir si un thread est lancée de la classe Util,si la condition s’avérée vraie  donc on va déplacer la voiture selon le type de trafic choisie. 
Puis on vérifie  l’index du sémaphore associe a cette voiture ce qui nous permettra de mettre en exécution la méthode roadLight de util.java , ce qui stopperas pendant 100ms  la voiture si la couleur du feu est rouge.
Les autres méthodes de cette classe (moveWestEast; moveWestSouth ...etc) nous permettent de calculer les possibilités de direction de la voiture 
La méthode isOnScreen() nous permet de tester si la voiture appairait ou pas sur l’écran et dans quelle Line/ route elle se situe 

Direction.java : 	Cette classe nous sert a définir les différente directions possibles qui peuvent être prise par la voiture 

	1- package View 
C’est un paquet dédier a l’interface graphique et au design de l’application, on retrouve notamment 2 classes Road.java et SoundManager.java
Road :
Classe permettant de designer l’aspect graphique de la route en utilisant la librairie Swing, en important l’image a partir de Image.IOE on définit la structure générale de la route avec les mesures voulue ( width et lenght) 
On utilise également une arrayList contenat le voitures passant par cette route (définie et généré par la classe Car) 
La méthode collision nous sert afin de compresser les voitures quand elle attendent le feu vert, 

Sound Manager :		Une classe qui nous permet de simuler les sons de voitures qui passer, pour une application plus réaliste.Contient un test console avec System.out.print("the audio canno't be currently used");
     
3- package Controller  : ce paquet ne contient que la classe Simulation Manager qui a déjà été expliquée dans la section 2 du rapport 

4- package Common 
Ce package est composée de 3 classes CarGeneratior et TrafficLight précédemment expliqué dans la section 2 ,il sert principalement a regrouper les classes commune a toute l’application et qui n’ont pas une partie spécifique ou elle sont identifiée.

Util.java :
C’est une classe principalement graphique qui nous permet de définir les graphique des feux de circulation en utilisant des images ( que nous avons mis dans le dossier image attachée au projet) et comme les images sont des cases vides on les a implémentée en ajoutant les couleurs appropriée a chaque feu 
On a aussi ajoutée une fonctionnalité d’un passage piéton avec 
public static Color crosswalkColor[] = {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE};
La méthode   public static ArrayList<Point> semCoord = new ArrayList<>() nous permet de coordiner les feux selon les point de positionnement géographique précédemment utilisée dans la classe trafic lights , et la coordination véritable des feux se fait dans la méthode  public static void changeRoadLight(int index) qui selon l’index de la route ciblé et la couleur courent du feu changera cette dernière 
Exemple explicatif : 
if (roadLight[index] == Color.RED) roadLight[index] = Color.GREEN;
/* si la route avec l’index entrée en paramétre de la methode a son feu en position rouge, alors la methode changeRoadLight(int index) va changer la couleur du feu vers la couleur suivante qui est vert */


SimulationManager()
Extends Frame from awt class to draw the elements of the application, like: building the road and cars to produce the look we see on screen later on chapter 04.
It uses setBound() method to Moves and resizes component. 
The new location of the top-left corner is specified by x and y, and the new size is specified by width and height.
This method changes layout-related information, and therefore, invalidates the component hierarchy.
the startSimulation parameter starts the simulation when you click on the start button on the screen.

      this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
Sets the operation that will happen by default when the user initiates a "close" on this frame. 

this.frame.setResizable(false);
setResizable(): Sets whether this frame is resizable by the user, we passwed false because we didn't want our screen to be adjustable 

prepareLightThreads():
here we created a new traffic light and instantiate  a new thread and lanced it.

in the run() we used the repaint() method to Repaint he road then disabled the thread for 100 ms so we don't get any error then thrown  a InterruptedExeption in the catch block.

the actionPerformed() method in this class specifies the type of traffic will be shown on screen depending on what the user has chosen.


Section 4 - Interface Graphique 
	1. Capture d’écran avec paramétrage de trafic light / léger 
	A- Avant démarrage et lancement des threads :

![image](https://user-images.githubusercontent.com/67691101/153721154-9f0f66d7-86be-41f6-a5a2-d46fd196d91a.png)
  Before hitting the Start Button
  ![Capture1](https://user-images.githubusercontent.com/67691101/153721173-ecf9c21c-8e8c-4654-bef7-f914be65920a.PNG)\
  Light Traffic
  
![Capture2](https://user-images.githubusercontent.com/67691101/153721194-fec82ded-1d16-43fa-81ba-2d1e858e9ba7.PNG)

Medium Traffic
  ![Capture](https://user-images.githubusercontent.com/67691101/153721215-45fbcd79-1b4a-4bf1-a87e-84ad57633d01.PNG)
Havey Traffic:
  
  
  
  ![Capture4](https://user-images.githubusercontent.com/67691101/153721220-a336f033-2c81-41d0-8cc8-2c653dc4f528.PNG)
