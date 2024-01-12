// Sorry, ich hab das wieder mit zwei Dateien gemacht, also mit Tests.sc Datei zum Testen
// Nächstes Mal mache ich das irgendwie anders in einem Datei, diesmal hats leider wegen der
// Zeitknappheit nicht geklappt, tut mir leid


import scala.annotation.tailrec


// is_sorted[T:Ordering](List[T]) : Boolean
// Voraussetzung: die Liste enthält nur die Werte,
// die mit einander verglichen werden können (Ordering)
// Ergebnis: Der Wahrheitswert davon ist geliefert, dass die Liste
// sortiert ist
// Effekt: keiner
/*
Tests:
is_sorted(List(1, 2, 3, 4)) == true
is_sorted(List(1, 3, 3, 4)) == true
is_sorted(List(1, 3, 2, 4)) == false
is_sorted(List(4, 3, 2, 1)) == true
is_sorted(List('d', 'c', 'b', 'a')) == true
is_sorted(List('d', 'b', 'c', 'a')) == false
is_sorted[Int](List()) == true
 */

def is_sorted[T:Ordering](list: List[T]): Boolean = {
  val ord = summon[Ordering[T]]
  import ord.mkOrderingOps
  @tailrec
  def step(acc: Boolean, _list: List[T], f: (T, T) => Boolean): Boolean = {
    _list match
    {
      case x1::x2::xs => step(acc && f(x1, x2), x2::xs, f)
      case _ => acc
    }
  }
  if (list == Nil) true
  else
    {
      if (list.head <= list.last) {
        step(true, list, _ <= _)
      }
      else {
        step(true, list, _ >= _)
      }
    }

}


// sum_smaller[T:Numeric](List[T], T) : T
// Voraussetzung: die Liste enthält nur die Werte,
// die mit einander addiert werden können (Numeric)
// Ergebnis: Die Summe aller Werte, die kleiner als der Eingabewert sind,
// ist geliefert
// Effekt: keiner
/*
Tests:
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), 4) == -5
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), 50) == 33
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), 0) == -6
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), -10) == 0
sum_smaller(List(1), -5) == 0
sum_smaller(List(1), 5) == 1
sum_smaller(List(), 5) == 0
 */

def sum_smaller[T:Numeric](list: List[T], w: T): T = {
  val ord = summon[Ordering[T]]
  import ord.mkOrderingOps
  val num = summon[Numeric[T]]
  import num.mkNumericOps
  @tailrec
  def step(acc: T, _list: List[T]): T = {
    _list match
      case Nil => acc
      case x::xs => if (x < w) {step(x + acc, xs)} else {step(acc, xs)}
  }
  step(Numeric[T].fromInt(0), list)
}

enum EntwederOder[T1, T2]:
  case UserType1(value: T1)
  case UserType2(value: T2)


// safe_divide(Double, Double) : EntwederOder[Double, String]
// Voraussetzung: keine
// Ergebnis: falls der Teiler gleich 0 ist, ist ein String
// "Infinity" geliefert, andernfalls das Ergebnis der
// Division (Alle Ausgabewerte sind in EntwederOder gepackt)
// Effekt: keiner
/*
Tests:
safe_divide(5.4, 2.8) == 1.92857... (UserType1(1.92857...))
safe_divide(7, 0) == "Infinity" (UserType2("Infinity"))
 */

def safe_divide(a : Double, b : Double): EntwederOder[Double, String] = {
  import EntwederOder.*
  if (b == 0) UserType2("Infinity")
  else UserType1(a / b)
}


// unspecified_divide(Int, Int) : EntwederOder[Double, Int]
// Voraussetzung: der Teiler ist ungleich 0 (b != 0)
// Ergebnis: falls die erste Zahl durch die zweite Zahl teilbar ist
// ist ein Int-Wert geliefert, andernfalls ein Double-Wert
// (Alle Ausgabewerte sind in EntwederOder gepackt)
// Effekt: keiner
/*
Tests:
unspecified_divide(5, 2) == 2.5 (UserType1(2.0))
unspecified_divide(2, 5) == 0.4 (UserType1(0.4))
unspecified_divide(4, 2) = 2 (UserType2(2))
 */

def unspecified_divide(a: Int, b: Int): EntwederOder[Double, Int] = {
  import EntwederOder.*
  if (a % b == 0) UserType2(a/b)
  else UserType1(a/b)
}


// parse_year(EntwederOder[Double, Int]) : Int
// Voraussetzung: das eingegebene Jahr muss positiv sein
// Ergebnis: falls das Jahr als String eingegeben wurde z.B.
// 55 BC(Before Christ), ist eine (negative) Int-Zahl umgewandelt
// (Alle Eingabewerte sind in EntwederOder gepackt)
// Effekt: keiner
/*
Tests:
parse_year(UserType1(1999)) == 1999
parse_year(UserType2("48 AC")) == 48
parse_year(UserType2("324 BC")) == -324
 */

def parse_year(year: EntwederOder[Int, String]): Int = {
  import EntwederOder.*
  year match
    case UserType1(yr) => yr
    case UserType2(yr) =>
      yr.split(" ").last match
        case "BC" => -yr.split(" ").head.toInt
        case "AC" => yr.split(" ").head.toInt
}

enum LogicGate:
  case TRUE
  case FALSE
  case AND(gate1: LogicGate, gate2: LogicGate)
  case OR(gate1: LogicGate, gate2: LogicGate)
  case NOT(gate: LogicGate)


// eval(LogicGate) : LogicGate
// Voraussetzung: keine
// Ergebnis: der Wert des simulierten logischen Ausdrucks
// ist geliefert
// Effekt: keiner
/*
Tests:
eval(NOT(AND(TRUE, TRUE))) == FALSE  (NOT(1 ^ 1))
eval(NOT(AND(OR(TRUE, FALSE), NOT(OR(FALSE, TRUE))))) == TRUE
(NOT(1 v 0) ^ NOT(0 v 1))
 */

def eval(state: LogicGate): LogicGate = {
  import LogicGate.*
  state match
    case AND(gate1, gate2) => if (eval(gate1) == FALSE) FALSE else eval(gate2)
    case OR(gate1, gate2) => if (eval(gate1) == TRUE) TRUE else eval(gate2)
    case NOT(gate) => if (eval(gate) == TRUE) FALSE else TRUE
    case TRUE => TRUE
    case FALSE => FALSE
}


// negate_vals(LogicGate) : LogicGate
// Voraussetzung: keine
// Ergebnis: ein logischer Ausdruck ist geliefert, bei dem
// alle TRUE-Werte durch FALSE ersetzt wurden und alle
// FALSE-Werte - durch TRUE
// Effekt: keiner
/*
Tests:
negate_vals(NOT(AND(TRUE, TRUE))) == NOT(AND(FALSE,FALSE))
negate_vals(NOT(AND(OR(TRUE, FALSE), NOT(OR(FALSE, TRUE))))) ==
NOT(AND(OR(FALSE,TRUE),NOT(OR(TRUE,FALSE))))
 */

def negate_vals(state: LogicGate): LogicGate = {
  import LogicGate.*
  state match
    case AND(gate1, gate2) => AND(negate_vals(gate1), negate_vals(gate2))
    case OR(gate1, gate2) => OR(negate_vals(gate1), negate_vals(gate2))
    case NOT(gate) => NOT(negate_vals(gate))
    case TRUE => FALSE
    case FALSE => TRUE
}

/*
Aufgabe 1e
splitAt[T](List[T], Int) : (List[T], List[T])
Voraussetzung: Index i ist nicht negativ und kleiner als die
Länge der Liste
Effekt: keiner
Ergebnis: zwei Listen sind geliefert, die dadurch entstanden sind, dass die
ursprüngliche Liste an einer bestimmten Stelle i in zwei Teile geteilt wurde

Falls i = 0 oder die Liste leer ist, wird die mit dem Stern bezeichnete Zeile
nur einmal aufgerufen
Falls i > 0 und die Liste nicht leer ist, wird die Funktion splitAt i - 1 rekursiv
aufgerufen bis i tatsächlich 0 erreicht. Das bedeutet, dass die zu zählende
nachfolgende Zeile (x::left, right) auch i - 1 Mal aufgerufen wird und noch
einmal wird die Zeile (Nil, list) aufgerufen, die ausgeführt wird, falls i die 0
erreicht. 
  Also T(n, i) = i - 1 + 1 = i
 */

def split_at[T](list: List[T], i: Int): (List[T], List[T]) = {
  (list, i) match
    case (Nil, _) => (Nil, Nil)
    case (_, 0) => (Nil, list)
    case (x :: xs, i) =>
      val (left, right) = split_at(xs, i - 1)
      (x :: left, right)
}


@tailrec
def merge[T:Ordering](left: List[T], right: List[T], list: List[T]): List[T] = {
  val ord = summon[Ordering[T]]
  import ord.mkOrderingOps
  (left, right) match
    case (Nil, _) => list:::right
    case (_, Nil) => list:::left
    case (x::xs, y::ys) => if (x < y) merge(xs, y::ys, list:::List(x)) else merge(x::xs, ys, list:::List(y))
}


// merge_sort[T:Ordering](List[T]) : List[T]
// Voraussetzung: die Liste enthält nur die Werte,
// die mit einander verglichen werden können (Ordering)
// Ergebnis: eine aufsteigend sortierte Liste ist geliefert
// Effekt: keiner
/*
Tests:
merge_sort(List(2, 11, 7, 14, 2, -5, 3, 22, 4, 5, 9)) == List(-5, 2, 2, 3, 4, 5, 7, 9, 11, 14, 22)
merge_sort(List(7, 1, 5)) == List(1, 5, 7)
merge_sort(List(1, 2, 3, 4, 5)) == List(1, 2, 3, 4, 5)
merge_sort(List(5, 4, 3, 2, 1)) == List(1, 2, 3, 4, 5)
merge_sort(List(5, 3, 4, 2, 1)) == List(1, 2, 3, 4, 5)
merge_sort[Int](List()) == List()
 */

def merge_sort[T:Ordering](list: List[T]): List[T] = {
  list match
    case Nil => Nil
    case x::Nil => List(x)
    case _ =>
      val (left, right) = split_at(list, list.length / 2)
      // val-Deklarationen sind nicht erlaubt, aber in diesem Fall
      // ist es doch günstig so eine Deklaration zu machen, weil
      // sonst hätte man das so gemacht:
      // merge(merge_sort(split_at(list, list.length / 2).head), merge_sort(split_at(list, list.length / 2).last), List())
      // So wird split_at tatsächlich ein doppeltes Mal aufgerufen
      merge(merge_sort(left), merge_sort(right), List())
}

enum Vielleicht[+T]:
  case Nichts
  case Wert(t : T)


// map[T1, T2](Vielleicht[T1], T1 => T2) : Vielleicht[T2]
// Voraussetzung: keine
// Ergebnis: ein Ergebnis der Anwendung einer Funktion auf
// den Eingabe-Vielleicht-Wert ist geliefert
// Effekt: keiner
/*
Tests:
map(Wert(5), (_:Int) > 3) == true (Wert(true))
map(Nichts, (_:Int) > 3) == Nichts
 */

def map[T1,T2](v : Vielleicht[T1], f:T1=>T2) : Vielleicht[T2] = {
  import Vielleicht.*
  v match
    case Nichts => Nichts
    case Wert(t) => Wert(f(t))
}


// extract[T](List[Vielleicht[T]]) : List[T]
// Voraussetzung: keine
// Ergebnis: eine Liste mit extrahierten Daten, aus den
// alle Nichts-Einträge entfernt wurden, ist geliefert
// Effekt: keiner
/*
Tests:
extract[Double](List(Wert(1), Nichts, Wert(17), Wert(2.3), Nichts, Wert(-2))) ==
List(1.0, 17.0, 2.3, -2.0)
extract(List(Wert(1), Nichts, Wert(17), Wert(2.3), Nichts, Wert(-2))) ==
List(1, 17, 2.3, -2)
 */

def extract[T](list: List[Vielleicht[T]]): List[T] = {
  import Vielleicht.*
  list match
    case Nil => Nil
    case x::xs =>
      x match
        case Nichts => extract(xs)
        case Wert(t) => t::extract(xs)
}

enum Link:
  case G, S, P

enum Chain:
  case Empty
  case Join(left : Chain, l : Link, right : Chain)


// to_list(Chain) : List[Link]
// Voraussetzung: keine
// Ergebnis: eine Liste ist geliefert, die die
// Materialenkette darstellt, aus der 'Chain'
// besteht
// Effekt: keiner
/*
Tests:
to_list(Join(Join(Empty, G, Empty), S, Join(Empty, S, Join(Empty, P, Empty)))) == List(G, S, S, P)
to_list(Empty) == List()
 */

def to_list(chain: Chain): List[Link] = {
  import Chain.*
  chain match
    case Empty => Nil
    case Join(left, l, right) => to_list(left):::List(l):::to_list(right)
}


// get_price(Chain, Link => Int) : Int
// Voraussetzung: die Preisfunktion liefert einen
// positiven Int-Wert
// Ergebnis: der Gesamtpreis einer Kette ist geliefert
// Effekt: keiner
/*
Tests:
get_price(Join(Join(Empty, G, Empty), S, Join(Empty, S, Join(Empty, P, Empty))), get_link_price) == 209
get_price(Empty, get_link_price) == 0
 */

def get_price(chain: Chain, p: Link => Int): Int = {
  import Chain.*
  chain match
    case Empty => 0
    case Join(left, l, right) => get_price(left, p) + p(l) + get_price(right, p)
}


// get_price_map(Chain, Link => Int) : Int
// Voraussetzung: die Preisfunktion liefert einen
// positiven Int-Wert
// Ergebnis: der Gesamtpreis einer Kette ist geliefert,
// indem man map-Funktion und eine Faltung angewendet hat
// Effekt: keiner
/*
Tests:
get_price_map(Join(Join(Empty, G, Empty), S, Join(Empty, S, Join(Empty, P, Empty))), get_link_price) == 209
get_price_map(Empty, get_link_price) == 0
 */

def get_price_map(chain: Chain, p: Link => Int): Int = {
  to_list(chain).map(p).sum // oder: to_list(chain).map(p).foldRight(0)((_+_))
}

def get_link_price(l: Link): Int = {
  import Link.*
  l match
    case G => 100
    case S => 12
    case P => 85
}