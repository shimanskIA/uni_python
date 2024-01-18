/*
Aufgabe 1a
Bei Zuweisungen von komplexen Objekten verwendet Scala Referenzsemantik.
Also zum Beispiel:
  var a: Array[Int] = Array(1, 2, 3)
  var b: Array[Int] = a

In diesem Fall verweisen zwei variablen a und b auf denselben Ort
im Speicher. Anders formuliert in variablen a und b ist eine (und dieselbe)
Adresse gespeichert (wie z.B. I@5158a9f7). Wenn man jetzt schreibt:
  println(a)
  println(b)

Dann wird die Adresse in Console angezeigt, wo sich ein Int-Array mit Zahlen
1, 2 und 3 befindet und die Ausgabe wird gleich für a und b sein.
Das hat auch den Effekt, dass wenn man Array a ändert, ändert man den Zustand
des Speichers und da b auch auf dieses Speicherstück verweist, wird man folgendes
beobachten:
  a(1) = 10
  println(a) // 1, 10, 3
  println(b) // 1, 10, 3

Das heißt, dass obwohl wir nur den ersten Array modifiziert haben, hat sich auch
die Ausgabe beim zweiten Array geändert, da sich a und b auf dasselbe Speicherstück
beziehen

Für einfache Datentype wie Int oder Double wird allerdings Wertsemantik bei
Zuweisungen benutzt. Das bedeutet, dass in einer Variablen nicht mehr ein
Referenz gespeichert ist, sondern der Wert selbst. Zum Beispiel:
  var c: Int = 5
  var d: Int = c
  c = 10
  println(c) // 10
  println(d) // 5

Hier sieht man, dass die Tatsache, dass der Variablen a ein anderer Wert zugewiesen
wurde, keinen Einfluss auf den in der Variablen b gespeicherten Wert hat.

Scala (auch wie Java) verwendet call-by-value (und call-by-name) Konvention, das bedeutet,
dass die Objekte selbst und nicht die Referenzen auf die Objekte in die Funktionen übertragen
werden. Wenn man ein Int-Wert (z.B,) in eine Funktion eingibt, dann wird nicht die Adresse des
Wertes, sondern der Wert selbst übertragen, so kann man den Wert der ursprünglichen Variablen gar
nicht beeinflussen. Es gibt allerdings nicht so viele unterschiede mit call-by-reference, falls
man versucht ein komplexes Objekt in die funktion zu übergeben, weil die Variable schon nur die
Adresse enthält und es wird tatsächlich nur die Kopie der Adresse von der Funktion erhalten. Das
heißt, dass innerhalb der Funktion die Adresse des Objektes nicht modifiziert werden kann, aber
das Objekt selbst - doch. In Scala wird auch call-by-value Konzept angewendet. Bei call-by-value
Konvention die in eine Funktion übertragene Werte werden nicht einmal vor dem Aufruf der Funktion
ausgerechnet, sondern sie werden jedes Mal innerhalb der Funktion umgerechnet, wenn man zu ihnen
zugreift.
 */

// Eine Test-Funktion, braucht man kein 5-Schritte-Prinzip glaube ich

def run_examples(): Unit =
{
  var a: Array[Int] = Array(1, 2, 3)
  var b: Array[Int] = a
  println(a)
  println(b)
  a(1) = 10
  println(a.mkString(" "))
  println(b.mkString(" "))

  var c: Int = 5
  var d: Int = c
  c = 10
  println(c)
  println(d)
}

/*
Aufgabe 1b
In der PDF-Datei ...
 */

/*
Aufgabe 1c
Die Klasse Geom3D wurde abstract gemacht, weil das meiner Meinung nach
mehr dem Zusammenhang entspricht. Also Geom3D ist eine abstrakte
Entität, die keine Objekte (Exemplare) haben kann (ich kann mir mindestens
keine Aufgabe vorstellen, bei der ein Objekt der Klasse Geom3D zu schaffen wäre).
Das ist keine gute Idee den Würfel als Unterklasse vom Quader zu
machen, weil der Konstruktor vom Quader drei Variablen erwartet, aber
für den Würfel ist nur eine Variable erforderlich. So könnte man auch
theoretisch einen Würfel erstellen, bei dem nicht alle Seiten gleich lang
sind. Oder es ist einfach überflüßig drei Variablen in den Konstruktor
zu übergeben, wenn nur eine nötig ist. (Meine Vermutung)
 */

abstract class Geom3D
{
  def calculate_volume: Double
  def calculate_surface_area: Double
}

class Cube(private var side: Double) extends Geom3D
{
  require(side >= 0)

  // calculate_volume: Double
  // Voraussetzung: keine
  // Ergebnis: Das Volumen vom Würfel ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
   */
  def calculate_volume: Double =
    Math.pow(side, 3)

  // calculate_surface_area: Double
  // Voraussetzung: keine
  // Ergebnis: Der Oberflächeninhalt vom Würfel ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_surface_area: Double =
    6 * Math.pow(side, 2)
}

class Cuboid(private var length: Double,
             private var width: Double,
             private var height: Double) extends Geom3D
{
  require(length >= 0 && width >= 0 && height >= 0)

  // calculate_volume: Double
  // Voraussetzung: keine
  // Ergebnis: Das Volumen vom Quader ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_volume: Double =
    length * width * height

  // calculate_surface_area: Double
  // Voraussetzung: keine
  // Ergebnis: Der Oberflächeninhalt vom Quader ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_surface_area: Double =
    2 * length * width + 2 * length * height + 2 * width * height
}

class Sphere(private var radius: Double) extends Geom3D
{
  require(radius >= 0)

  // calculate_volume: Double
  // Voraussetzung: keine
  // Ergebnis: Das Volumen von der Kugel ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_volume: Double =
    4/3 * Math.PI * Math.pow(radius, 3)

  // calculate_surface_area: Double
  // Voraussetzung: keine
  // Ergebnis: Der Oberflächeninhalt von der Kugel ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_surface_area: Double =
    4 * Math.PI * Math.pow(radius, 2)
}


class RegularTetrahedron(private var side: Double) extends Geom3D
{
  require(side >= 0)

  // calculate_volume: Double
  // Voraussetzung: keine
  // Ergebnis: Das Volumen vom gleichschenkligen Tetraeder ist geliefert
  // (ich habe mir gedacht für ein normales Tetraeder wäre es zu kompliziert
  // und Geometrie ist dabei kein Ziel)
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_volume: Double =
    Math.sqrt(3) / 12 * Math.pow(side, 3)

  // calculate_surface_area: Double
  // Voraussetzung: keine
  // Ergebnis: Der Oberflächeninhalt vom gleichschenkligen Tetraeder ist geliefert
  // Effekt: keiner
  /*
  Tests befinden sich in der Test-Methode
  ...
  */
  def calculate_surface_area: Double =
    3 * Math.sqrt(3) * Math.pow(side, 2) / 2
}


// calculate_total_volume(Array[Geom3D]): Double
// Voraussetzung: keine
// Ergebnis: Das gesamte Volumen von allen 3D-Figuren in Array ist
// geliefert
// Effekt: keiner
/*
Tests:
calculate_total_volume(Array(
    Cube(3),
    Cube(2.1),
    Cuboid(5.2, 4.3, 1.1),
    Cube(0.32),
    Sphere(11),
    RegularTetrahedron(1.4))) == 4242.74...
calculate_total_volume(Array(
    Cube(0),
    Cube(0),
    Cuboid(0, 0, 0),
    Cube(0),
    Sphere(0),
    RegularTetrahedron(0))) == 0.0
 */

def calculate_total_volume(figures: Array[Geom3D]): Double = {
  var total_volume: Double = 0
  for figure <- figures do {
    total_volume += figure.calculate_volume
  }
  total_volume
}


// calculate_total_surface_area(Array[Geom3D]): Double
// Voraussetzung: keine
// Ergebnis: Der gesamte Oberflächeninhalt von allen 3D-Figuren in Array ist
// geliefert
// Effekt: keiner
/*
Tests:
calculate_total_surface_area(Array(
    Cube(3),
    Cube(2.1),
    Cuboid(5.2, 4.3, 1.1),
    Cube(0.32),
    Sphere(11),
    RegularTetrahedron(1.4))) == 1672.31...
calculate_total_surface_area(Array(
    Cube(0),
    Cube(0),
    Cuboid(0, 0, 0),
    Cube(0),
    Sphere(0),
    RegularTetrahedron(0))) == 0.0
 */

def calculate_total_surface_area(figures: Array[Geom3D]): Double = {
  var total_surface_area: Double = 0
  for figure <- figures do {
    total_surface_area += figure.calculate_surface_area
  }
  total_surface_area
}


// Eine Test-Funktion, braucht man kein 5-Schritte-Prinzip glaube ich

def geom3Demo(): Unit = {
  println(calculate_total_volume(Array(
    Cube(3),
    Cube(2.1),
    Cuboid(5.2, 4.3, 1.1),
    Cube(0.32),
    Sphere(11),
    RegularTetrahedron(1.4))))
  println(calculate_total_surface_area(Array(
    Cube(3),
    Cube(2.1),
    Cuboid(5.2, 4.3, 1.1),
    Cube(0.32),
    Sphere(11),
    RegularTetrahedron(1.4))))
  println(calculate_total_volume(Array(
    Cube(0),
    Cube(0),
    Cuboid(0, 0, 0),
    Cube(0),
    Sphere(0),
    RegularTetrahedron(0))))
  println(calculate_total_surface_area(Array(
    Cube(0),
    Cube(0),
    Cuboid(0, 0, 0),
    Cube(0),
    Sphere(0),
    RegularTetrahedron(0))))
}

/*
Aufgabe 1d
Das Klassendiagramm ist in der PDF-Datei ...

md.printMe() // "Read it!"
Der statische Typ von md ist State, aber der dynamische Typ ist
Maryland, der die Methode printMe() 'overridet'. Das bedeutet, dass
die Methode in der Oberklasse (statischer Typ) von der Methode der
Unterklasse versteckt wird und wenn printMe() aufgerufen wird, wird
genau die Methode der Unterklasse (des dynamischen Typs) aufgerufen.

mid.printMe() // "Ship it!"
Die gleiche Erklärung wie oben

obj.asInstanceOf[Place].printMe() // "Buy it!"
Die Funktion asInstanceOf[Place] bedeutet, dass das Objekt obj sich
als ein Objekt der Klasse Place verhalten soll. Früher wurde dem Objekt obj
der Klasse Any ein Objekt der Klasse Place zugewiesen, aber
dadurch, dass das Objekt zur Klasse Any gehört, waren nur die
Methoden der Klasse Any zugänglich. Indem man asInstanceOf benutzt
hat, wurden auch die der Klasse Place gehörende Methoden 'entsperrt'
und die Methode printMe() hat den Text 'geprintet', der der Klasse
Place eigen ist. Das geht aber nur dann, wenn dem Objekt der Klasse
Any vorher ein Objekt einer anderen entsprechenden Klasse zugewiesen
wurde (in dem Fall der Klasse Place).

obj.asInstanceOf[Maryland].printMe() // "Read it!"
In der vorgängigen Zeile obj = md wurde der dynamische Typ der
Variablen obj auf Maryland geändert (statische Typ auf State),
deswegen der Aufruf führt zu solcher Ausgabe. Allerdings muss
man explizit mit asInstanceO[Maryland] hinweisen , dass wir jetzt
das Objekt obj als eine Entität der Klasse Maryland betrachten.

obj.asInstanceOf[Place].printMe() // "Box it!"
Obwohl der statische Typ in diesem Fall Place ist und man versucht
obj als ein Objekt dieser Klasse zu behandeln, wird doch die Methode der
Klasse Region aufgerufen, weil sie diese Methode in der Oberklasse
versteckt und der Compiler die Methode der Elternklasse einfach nicht
sieht.
 */


// play_guess_number_game(): Unit
// Voraussetzung: User gibt eine ganze Zahl, die im Definitionsbereich von Int
// liegt
// Ergebnis: kein (Nichts ist geliefert)
// Effekt: es wird ein Zahlenspiel durchgeführt, bei dem der Nutzer eine Zahl
// zwischen 1 und 100 raten soll, alle Schritte des Spiels werden auf dem Bildschirm
// angezeigt
/*
Zum testen muss man das Spiel starten und spielen
...
*/

def play_guess_number_game(): Unit = {
  var number: Int = scala.util.Random.between(1, 100)
  while number > 0 do
    {
      println("Enter a number between 1 and 100: ")
      var guess: Int = scala.io.StdIn.readInt()
      if (guess > number) println("Too big!")
      else if (guess < number) println("Too small!")
      else
        println("Correct guess! " + number)
        number = 0
    }

}

class Car(private val manufacturer: String,
          private val model: String,
          private val production_year: Int,
          private var mileage: Int)
{
  require(production_year >= 1800 && mileage >= 0)

  private var is_new: Boolean = mileage < 1000 && production_year >= 2020
  private var plate_number: Int = Car.number
  // Der Schildnummerzähler wird um 1 größer
  Car.number += 1

  def this(manufacturer: String,
  model: String,
  production_year: Int) =
    this(manufacturer, model, production_year, 0)


  // drive(Int): Unit
  // Voraussetzung: keine
  // Ergebnis: Nichts ist geliefert
  // Effekt: der Kilometerstand vom Auto wird um die Strecke vergrößert.
  // Falls der neue Kilometerstand 1000 km überschreitet, wird das Auto als
  // benutzt angesehen und is_new wird auf false gestellt
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def drive(distance: Int): Unit =
    if (distance > 0)
      mileage += distance
      if (mileage >= 1000) is_new = false

  // estimate_price: Double
  // Voraussetzung: keine
  // Ergebnis: der geschätzte Preis vom Auto ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def estimate_price: Double =
    var new_price: Double = 0
    manufacturer match
      case "Mercedes" => new_price = 70000
      case "BMW" => new_price = 60000
      case "Volkswagen" => new_price = 40000
      case "Opel" => new_price = 30000
      case "Ferrari" => new_price = 100000
      case _ => new_price = 20000
    if (is_new) new_price
    else new_price * Math.pow(0.95, 2024 - production_year) * Math.pow(0.999998, mileage)

  // get_info: String
  // Voraussetzung: keine
  // Ergebnis: ein String mit dem Info übers Auto ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def get_info: String =
    manufacturer
      + " " + model
      + " produced in " + production_year
      + ", has mileage " + mileage
      + ", the estimated price is " + estimate_price + "$"


  // break(): Unit
  // Voraussetzung: keine
  // Ergebnis: Nichts ist geliefert
  // Effekt: das Auto geht kaputt und is_new wird somit auf false
  // gestellt
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def break(): Unit =
    is_new = false

  // get_plate_number: Int
  // Voraussetzung: keine
  // Ergebnis: die Schildnummer vom Auto ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def get_plate_number: Int = plate_number
}

/*
Aufgabe 2b
Wie geschrieben in der ersten Aufgabe für die komplexen Objekten
(alle Objekte der Klasse Car sind komplexe Objekte) gilt die Referenzsemantik.
Das bedeutet das die Variablen nicht das Objekt selbst enthalten, sondern nur
die Adresse des Teils des Speichers, wo sich das Objekt befindet (etwa genauer
und mit Beispielen in der Aufgabe 1a).
Wenn man schreibt
  var car: Car = Car(...)

schafft man ein Objekt der Klasse Car, das nicht direkt in der Variablen car
gespeichert ist, d.h. wenn jetzt println(car) schreibt, kriegt man nur
die Adresse ausgegeben.
 */

class SportCar(manufacturer: String,
               model: String,
               production_year: Int,
               mileage: Int,
               private var maximal_speed: Int) extends Car(manufacturer, model, production_year, mileage)
{
  private var has_been_pimped: Boolean = false

  def this(manufacturer: String,
           model: String,
           production_year: Int,
           mileage: Int) =
    this(manufacturer, model, production_year, mileage, 200)

  // get_info: String
  // Voraussetzung: keine
  // Ergebnis: ein String mit dem Info übers Sportauto ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  override def get_info: String =
    super.get_info + " The maximal speed is " + maximal_speed + "km/h"

  // estimate_price: Double
  // Voraussetzung: keine
  // Ergebnis: der geschätzte Preis vom Sportauto ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  override def estimate_price: Double =
    var demo_price: Double = super.estimate_price * Math.pow(1.01, maximal_speed - 180)
    if (has_been_pimped)
      demo_price *= 1.25
      demo_price
    else demo_price

  // pimp_my_ride(Int): Unit
  // Voraussetzung: keine
  // Ergebnis: Nicht ist geliefert
  // Effekt: Das Sportauto wird 'getunet', die maximale Geschwindigkeit wird
  // vergrößert und has_been_pimped Flag wird auf true gestellt
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def pimp_my_ride(extra_kmh: Int): Unit =
    if (extra_kmh > 0)
      maximal_speed += extra_kmh
      has_been_pimped = true

  // get_has_been_pimped: Boolean
  // Voraussetzung: keine
  // Ergebnis: der Wahrheitswert davon ist geliefert, dass das Sportauto
  // 'getunet' wurde
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  def get_has_been_pimped: Boolean = has_been_pimped

}

class Minivan(manufacturer: String,
                   model: String,
                   production_year: Int,
                   mileage: Int,
                   private var passenger_amount: Int) extends Car(manufacturer, model, production_year, mileage)
{
  def this(manufacturer: String,
           model: String,
           production_year: Int,
           mileage: Int) =
    this(manufacturer, model, production_year, mileage, 3)

  // get_info: String
  // Voraussetzung: keine
  // Ergebnis: ein String mit dem Info übers Minivan ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  override def get_info: String =
    super.get_info + " The vehicle can carry " + passenger_amount + " persons"

  // estimate_price: Double
  // Voraussetzung: keine
  // Ergebnis: der geschätzte Preis vom Minivan ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  override def estimate_price: Double =
    super.estimate_price * Math.pow(1.05, passenger_amount - 3)
}

class Truck(manufacturer: String,
              model: String,
              production_year: Int,
              mileage: Int,
              private var load_capacity: Int) extends Car(manufacturer, model, production_year, mileage)
{
  def this(manufacturer: String,
           model: String,
           production_year: Int,
           mileage: Int) =
    this(manufacturer, model, production_year, mileage, 3000)

  // get_info: String
  // Voraussetzung: keine
  // Ergebnis: ein String mit dem Info über den LKW ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  override def get_info: String =
    super.get_info + " The vehicle can carry " + load_capacity + " kilogramms"

  // estimate_price: Double
  // Voraussetzung: keine
  // Ergebnis: der geschätzte Preis vom LKW ist geliefert
  // Effekt: keiner
  /*
    Tests befinden sich in der Test-Methode
    ...
     */
  override def estimate_price: Double =
    super.estimate_price * Math.pow(1.0001, load_capacity - 1500)
}

private object Car:
  private var number: Int = 1000


// Eine Test-Funktion, braucht man kein 5-Schritte-Prinzip glaube ich

def test_drive(): Unit = {
  var mercedes: Car = Car("Mercedes", "CL500", 2012, 120000)
  var bmw: Car = Car("BMW", "X5", 2021)
  var volkswagen: Car = Car("Volkswagen", "Polo", 1993, 950000)
  var toyota: Car = Car("Toyota", "Camry", 2008, 265000)
  mercedes.drive(150)
  println(bmw.estimate_price)
  bmw.drive(1500)
  println(bmw.estimate_price)
  println(mercedes.get_info)
  println(bmw.get_info)
  println(volkswagen.get_info)
  println(toyota.get_info)
  println(mercedes.get_plate_number)
  println(bmw.get_plate_number)
  println(volkswagen.get_plate_number)
  println(toyota.get_plate_number)

  var sportcar: Car = SportCar("Ferrari", "Some", 2005, 90000, 320)
  var minivan: Car = Minivan("Mercedes", "SomeMinivan", 2002, 372000, 8)
  var truck: Car = Truck("Renault", "Very big 3.0", 2011, 412000, 50000)
  println(sportcar.estimate_price)
  println(sportcar.get_info)
  println(minivan.estimate_price)
  println(minivan.get_info)
  println(truck.estimate_price)
  println(truck.get_info)
  sportcar.asInstanceOf[SportCar].pimp_my_ride(20)
}

/*
Aufgabe 2d
Ein Beispiel könnte sein:
  var sportcar: Car = SportCar("Ferrari", "Some", 2005, 90000, 320)
  var minivan: Car = Minivan("Mercedes", "SomeMinivan", 2002, 372000, 8)
  var truck: Car = Truck("Renault", "Very big 3.0", 2011, 412000, 50000)
  println(sportcar.estimate_price)
  println(sportcar.get_info)
  println(minivan.estimate_price)
  println(minivan.get_info)
  println(truck.estimate_price)
  println(truck.get_info)

Hier wird einer Variablen des Typs Car ein Objekt der Unterklasse SportCar
(oder Minivan oder Truck) zugewiesen. Der Compiler sieht die Variable sportcar
(minivan, truck) als ein Objekt der Oberklasse Car. Car ist in diesem Fall der
statische Typ und SportCar - der dynamische Typ. Es ist möglich zu allen nicht privaten
(und nicht protected) Methoden der Oberklasse zuzugreifen. Unmöglich ist es aber
die eigenen Methoden der Unterklasse aufzurufen, weil das Object SportCar im Program als
ein Objekt der Klasse Car betrachtet wird, also ein SportCar wird auf ein Car beschränkt
(alle überschrieben Methoden werden jedoch von der Unterklasse aufgerufen, wie get_info
oder estimate_price). Wenn man will das Objekt sportcar auf ein Objekt der Klasse
SportCar erweitern, muss man das dem Compiler mit asInstanceOf[SportCar] explizit zeigen.
Also sportcar.pimp_my_ride(20) würde nicht gehen, aber:
sportcar.asInstanceOf[SportCar].pimp_my_ride(20) geht, weil man sportcar jetzt als ein
Objekt der Klasse SportCar betrachtet. Dieses Prinzip wurde auch in der Aufgabe 1d
ausführlich erklärt.
 */
