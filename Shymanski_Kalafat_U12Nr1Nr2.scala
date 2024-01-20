import scala.collection.mutable.Stack
import scala.reflect.ClassTag

/*
Aufgabe 1a
Auf die Mehrfachvererbung wurde wahrscheinlich wegen ihrer Komplexität
verzichtet. Die Probleme tauchen dann auf, wenn sowohl beide (oder mehrere)
Elternklassen als auch die Unterklasse über die Methoden mit der gleichen
Signatur verfügen. Dann ist es für den Compiler problematisch zu entscheiden,
welche der Methoden tatsächlich aufgerufen wurde und welche der Methoden eine
höhere Priorität haben soll.
 */

// Aufgabe 1b
def is_valid_expression(ex: Stack[Char]): Boolean = {
  val open_ex = Stack[Char]()
  while (ex.nonEmpty)
    {
      val el = ex.pop()
      el match
        case '[' => open_ex.push(el)
        case '{' => open_ex.push(el)
        case '(' => open_ex.push(el)
        case ']' => if open_ex.isEmpty || open_ex.pop() != '[' then return false
        case '}' => if open_ex.isEmpty || open_ex.pop() != '{' then return false
        case ')' => if open_ex.isEmpty || open_ex.pop() != '(' then return false
    }
  open_ex.isEmpty
}


trait MyStack[T]:
  def push(x : T) : Unit
  def pop() : T
  def top : T
  def is_empty : Boolean
  def size : Int


// Aufgabe 1c
class LinkedNodesStack[T] extends MyStack[T]:
  private class Node(val item: T, val next: Node)
  {
    // Methode zum Testen mit Datentypen, die sich printen lassen
    def print_nodes(): Unit =
      println(item)
      if next != null then next.print_nodes()
  }

  private var topNode : Node = null
  private var _size : Int = 0

  def is_empty: Boolean = topNode == null

  def size: Int = _size

  def push(x: T): Unit =
    topNode = Node(x, topNode)
    _size = _size + 1

  def pop(): T =
    if !is_empty then
      val result: T = topNode.item
      topNode = topNode.next
      _size = _size - 1
      result
    else throw Exception("Stack is empty")

  def top: T =
    if !is_empty then topNode.item
    else throw Exception("Stack is empty")

  def multipush(elements: T*): Unit =
    elements.foreach(push)

  // Methode zum Testen mit Datentypen, die sich printen lassen
  def print_items(): Unit =
    topNode.print_nodes()


class ArrayStack[T : ClassTag](capacity: Int) extends MyStack[T]:

  private val n : Int = if capacity < 1 then 1 else capacity
  private val array : Array[T] = new Array[T](n)
  private var amount : Int = 0

  def is_empty: Boolean = amount == 0

  def size: Int = amount

  def push(x: T): Unit =
    if amount < array.length then
      array(amount) = x
      amount = amount + 1
    else throw new Exception("The stack is full")

  def pop(): T =
    if !is_empty then
      val result: T = array(amount - 1)
      array(amount - 1) = null.asInstanceOf[T]
      amount = amount - 1
      result
    else throw new Exception("Stack is empty")

  def top: T =
    if !is_empty then array(amount - 1)
    else throw new Exception("Stack is empty")

  def multipush(elements: T*): Unit =
    elements.foreach(push)

  def print_items(): Unit =
    for i <- amount - 1 to 0 by -1 do
      println(array(i))


// Aufgabe 1d
class DynamicArrayStack[T : ClassTag] extends MyStack[T]:

  private var array : Array[T] = new Array[T](1)
  private var amount : Int = 0

  private def resize(): Unit =
    val cap: Int = array.length
    if cap / 8 <= amount && amount < cap then return
    val newArray: Array[T] = new Array[T](if cap / 8 > amount then cap / 4 else cap * 4)
    for i <- 0 until amount do
      newArray(i) = array(i)
    array = newArray

  // Methode zum Vergleichen
  private def old_resize(): Unit =
    val cap: Int = array.length
    if cap / 4 <= amount && amount < cap then return
    val newArray: Array[T] = new Array[T](if cap / 4 > amount then cap / 2 else cap * 2)
    for i <- 0 until amount do
      newArray(i) = array(i)
    array = newArray

  def is_empty: Boolean = amount == 0

  def size: Int = amount

  def push(x: T): Unit =
    array(amount) = x
    amount = amount + 1
    resize()

  // Methode zum Vergleichen
  private def old_push(x: T): Unit =
    array(amount) = x
    amount = amount + 1
    old_resize()

  def top: T =
    if !is_empty then array(amount - 1)
    else throw new Exception("Stack is empty")

  def pop(): T =
    if !is_empty then
      val result: T = array(amount - 1)
      array(amount - 1) = null.asInstanceOf[T]
      amount = amount - 1
      resize()
      result
    else throw new Exception("Stack is empty")

  // Methode zum Vergleichen
  def old_pop(): T =
    if !is_empty then
      val result: T = array(amount - 1)
      array(amount - 1) = null.asInstanceOf[T]
      amount = amount - 1
      old_resize()
      result
    else throw new Exception("Stack is empty")

  def multipush(elements: T*): Unit =
    elements.foreach(push)

  // Methode zum Vergleichen
  def old_multipush(elements: T*): Unit =
    elements.foreach(old_push)

  // Methode zum Testen
  def get_capacity: Int = array.length


/*
Aufgabe 2a
 a) Überladen
    Beim Überladen gibt es 2 Methoden (Funktionen) mit dem gleichen Namen, aber
mit Unterschiedlichen Eingabeparametern. Dabei spielt die Reihenfolge, Typen und
die Anzahl der Parametern eine Rolle. Man darf aber keine Methode (Funktion) mit
der gleichen Signatur aber unterschiedlichen Typen des Ausgabewertes schaffen.

  Beispiele für Überladen der Methode def func(a: Int, b: Int): Unit = ...
    def func(a: Int, b: Int, c: Int): Unit = ...
    def func(a: Int, b: String): Unit = ...
    def func(a: String, b: Double): Unit = ...

  gilt aber als kein Überladen:
    def func(new_name_a: Int, new_name_b: Int): Unit = ...
    def func(a: Int, b: Int): Int = ...

  b) Coercion
    Unter Coercion wird Type-Casting gemeint (glaube ich), also die Möglichkeit
eine Variable eines Typs in einen anderen Typ zu überführen. Es gibt verschiedene Arte
von Coercion:
  1. Coercion zwischen Value-Typen:
    Dafür brauch man keinen speziellen Syntax oder Methode. Das Überführen erfolgt gemäß
dem folgenden Muster:
  Byte —> Short —> Int —> Long —> Float —> Double
                    ^
                    |
                  Char
In die entgegengesetzte Richtung geht es aber nicht.
  
  2. Coercion zwischen Referenz-Typen erfolgt durch asInstanceOf[Type] Methode:
    Ein Beispiel aus der vorherigen Hausaufgabe:
      var p : Person = Student("Anton", 19, 621637)
      p.asInstanceOf[Student].work() // "Study, Study ..."
      
  c) Parametrische Polymorphie
    Bei parametrischer Polymorphie beschreibt man eine Methode oder Funktion
so, dass die für unterschiedliche Typen funktioniert (manchmal sollen die Typen
über irgendeine gemeinsame Eigenschaft verfügen, wie z.B. Vergleichbarkeit)

  Beispiel einer Funktion die ein Parameter des Typs T bekommt und einen Wert des
Typs T liefert:
    def func[T](param: T): T = ...

  Beispiel eines polymorphen Traits:
    trait MyQueue[T]:
      def enqueue(x : T) : Unit

  Beispiel einer polymorphen Klasse:
    class ExampleClass[T](val ex1: T, val ex2: List[T])
      ...

Generell haben wir damit schon mehrmals bei den vorherigen Hausaufgaben zu tun gehabt

  d) Inklusionpolymorphie (override)
    Bei der Inklusionspolymophie haben die Unterklassen die Möglichkeit
eine in der Oberklasse definierte Methode auf eine eigene Art und Weise
auszuführen, indem diese Methode in den Unterklassen umdefiniert wird.

  Eine Methode in einer Oberklasse könnte so aussehen:
    def do_smth(): Unit = ...

  Um diese Methode umzudefinieren muss man das Keywort override nutzen:
    def override do_smth(): Unit = ...

  Wichtig dabei ist, dass die Signaturen beider Methoden gleich sein müssen
(im Unterschied zum Overload).

  Inklusionspolymorphie braucht man um die Methoden der Unterklassen mehr
der Logik der reelen Welt anzupassen. Also mit der Inklusionspolymorphie erreicht
man, dass die Methoden, die prinzipiell etwas gleiches machen, auch denselben Namen
tragen, der dieses 'gleiches' auch gleich nennt.
 */


trait MyQueue[T]:
  def enqueue(x : T) : Unit
  def dequeue() : T
  def is_empty : Boolean
  def size : Int


// Aufgabe 2b, 2d
class ArrayQueue[T : ClassTag] extends MyQueue[T]:

  private var n : Int = 1
  private var array: Array[T] = new Array[T](n)
  private var front: Int = 0
  private var back: Int = 0
  private var _is_empty: Boolean = true

  private def resize(): Unit =
    val amount = size
    val old_n = n
    if n / 4 <= amount && amount < n then return
    if n / 4 > amount then n = n / 2 else n = n * 2
    val newArray: Array[T] = new Array[T](n)
    var j = 0
    if front < back then
      for i <- front until back do
        newArray(j) = array(i)
        j += 1
    else
      for i <- front until old_n do
        newArray(j) = array(i)
        j += 1
      for i <- 0 until back do
        newArray(j) = array(i)
        j += 1
    array = newArray
    front = 0
    back = amount
  
  def is_empty: Boolean = _is_empty
  
  def size: Int =
    if back == front && !_is_empty then
      n
    else
      (n + back - front) % n

  def enqueue(x: T): Unit =
    val newBack: Int = (back + 1) % n
    array(back) = x
    back = newBack
    _is_empty = false
    resize()

  def dequeue(): T =
    if !is_empty then
      val result: T = array(front)
      array(front) = null.asInstanceOf[T]
      front = (front + 1) % n
      if back == front then _is_empty = true
      resize()
      result
    else throw new Exception("The queue is empty")
    
  def get_capacity: Int = n

  // Methode zum Testen mit Datentypen, die sich printen lassen
  def print_items(): Unit =
    if front < back then
      for i <- back - 1 to front by -1 do
        println(array(i))
    else
      for i <- n - 1 to front by - 1 do
        println(array(i))
      for i <- back - 1 to 0 by - 1 do
        println(array(i))


// Aufgabe 2e
class ArrayQueueVip[T : ClassTag] extends MyQueue[T]:

  private val vip_queue: ArrayQueue[T] = new ArrayQueue[T]()
  private val queue: ArrayQueue[T] = new ArrayQueue[T]()

  def is_empty: Boolean = vip_queue.is_empty && queue.is_empty

  def size: Int =
    vip_queue.size + queue.size

  def enqueue(x: T): Unit =
    queue.enqueue(x)

  def enqueue(x: T, is_vip: Boolean = false): Unit =
    if is_vip then
      vip_queue.enqueue(x)
    else
      queue.enqueue(x)

  def dequeue(): T =
    if !vip_queue.is_empty then
      vip_queue.dequeue()
    else
      queue.dequeue()

  // Methode zum Testen mit Datentypen, die sich printen lassen
  def print_items(): Unit =
    queue.print_items()
    println("VIPs:")
    vip_queue.print_items()


@main
def main(): Unit = {
  println(is_valid_expression(Stack('(', '[', '{', '}', ']', ')', '(', ')', '{', '{', '(', ')', '[', ']', '}', '}')))
  println(is_valid_expression(Stack('(', '(', '(', ')', ')', ')', '(', ')', '(', ')', '(', '(', ')', ')')))
  println(is_valid_expression(Stack('(', ')', '(', ')', '(', ')')))
  println(is_valid_expression(Stack('(', '[', ')', ']')))
  println(is_valid_expression(Stack('(', '(', ')')))
  println(is_valid_expression(Stack('(', '(', ')', '[', ']')))
  println(is_valid_expression(Stack('}', '{')))
  println()

  val node_stack = LinkedNodesStack[Int]()
  val arr_stack = ArrayStack[Int](10)
  node_stack.push(33)
  node_stack.multipush(1, 2, 3, 12, 26, -5, 0)
  node_stack.push(22)
  node_stack.print_items()
  println()
  arr_stack.push(22)
  arr_stack.multipush(4, 7, -7, 5, 2, 0)
  arr_stack.push(33)
  arr_stack.print_items()
  println()

  var dyn_stack = DynamicArrayStack[Int]()
  println(dyn_stack.get_capacity)
  dyn_stack.multipush(4, 3, 9, 12)
  println(dyn_stack.get_capacity)
  dyn_stack.multipush(19, 10, 5, 5, 6, 88, 32, 56)
  println(dyn_stack.get_capacity)
  for i <- 1 to 11 do
    dyn_stack.pop()
    println(dyn_stack.get_capacity)
  dyn_stack = DynamicArrayStack[Int]()
  println(dyn_stack.get_capacity)
  dyn_stack.old_multipush(4, 3, 9, 12)
  println(dyn_stack.get_capacity)
  dyn_stack.old_multipush(19, 10, 5, 5, 6, 88, 32, 56)
  println(dyn_stack.get_capacity)
  for i <- 1 to 11 do
    dyn_stack.old_pop()
    println(dyn_stack.get_capacity)
  println()
    
  val queue = ArrayQueue[Int]()
  println(queue.get_capacity)
  for i <- 1 to 10 do
    queue.enqueue(scala.util.Random.between(1, 100))
    println(queue.get_capacity)
  println()
  queue.print_items()
  println()
  for i <- 1 to 7 do
    queue.dequeue()
    println(queue.get_capacity)
  println()
  queue.print_items()
  queue.dequeue()
  queue.dequeue()
  println()
  println(queue.get_capacity)
  println()
  queue.print_items()
  println()

  val vip_queue = ArrayQueueVip[Int]()
  for i <- 1 to 10 do
    vip_queue.enqueue(scala.util.Random.between(1, 100))
  for i <- 1 to 5 do
    vip_queue.enqueue(scala.util.Random.between(1, 100), true)
  vip_queue.print_items()
  println()
  vip_queue.dequeue()
  vip_queue.dequeue()
  vip_queue.print_items()
}