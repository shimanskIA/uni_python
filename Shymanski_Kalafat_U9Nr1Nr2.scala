import scala.annotation.tailrec

def count_digits(n: Int): Int = {
  @tailrec
  def step(acc1: Int, acc2:Int, i: Int): Int = {
    i match
    {
      case 0 => acc1
      case _ => step(acc1 + acc2 % 10, acc2 / 10, i - 1)
    }
  }
  step(0, n, n.toString.length)
}


// replace[T](List[T], T, T) : List[T]
// Voraussetzung: keine
// Ergebnis: Eine Liste, bei der alle Elemente a:T durch b:T ersetzt
// sind, ist geliefert
// Effekt: keiner
/*
Tests:
replace(List(1, 2, 2, 3, 4), 5, 1) == (1, 2, 2, 3, 4)
replace(List(1, 2, 2, 3, 4), 2, 3) == (1, 3, 3, 3, 4)
replace(List(1, 2, 2, 3, 4), 3, 2) == (1, 2, 2, 2, 4)
replace(List(1, 1, 1, 1, 1), 1, 10) == (10, 10, 10, 10, 10)
replace(List('a', 'c', 'b', 'c', '1', 'a'), 'c', 'e') == ('a', 'e', 'b', 'e', '1', 'a')
replace(List('a', 'c', 'b', 'c', '1', 'a'), 'e', 'c') == ('a', 'c', 'b', 'c', '1', 'a')
 */

def replace[T](list: List[T], a: T, b: T): List[T] = {
  list.map(x => if (x.equals(a)) {b} else x)
}


// replace_rec[T](List[T], T, T) : List[T]
// Voraussetzung: keine
// Ergebnis: Eine Liste, bei der alle Elemente a:T durch b:T rekursiv ersetzt
// sind, ist geliefert
// Effekt: keiner
/*
Tests:
replace_rec(List(1, 2, 2, 3, 4), 5, 1) == (1, 2, 2, 3, 4)
replace_rec(List(1, 2, 2, 3, 4), 2, 3) == (1, 3, 3, 3, 4)
replace_rec(List(1, 2, 2, 3, 4), 3, 2) == (1, 2, 2, 2, 4)
replace_rec(List(1, 1, 1, 1, 1), 1, 10) == (10, 10, 10, 10, 10)
replace_rec(List('a', 'c', 'b', 'c', '1', 'a'), 'c', 'e') == ('a', 'e', 'b', 'e', '1', 'a')
replace_rec(List('a', 'c', 'b', 'c', '1', 'a'), 'e', 'c') == ('a', 'c', 'b', 'c', '1', 'a')
 */

def replace_rec[T](list: List[T], a: T, b: T): List[T] = {
  list match {
    case Nil => Nil
    case x::xs => (if (x.equals(a)) {b} else x) :: replace_rec(xs, a, b)
  }

}


// isSorted[T](List[T], (T, T) => Boolean) : Boolean
// Voraussetzung: keine
// Ergebnis: Der Wahrheitswert davon ist geliefert, dass die Liste
// gemäß des Vergleichsoperators sortiert ist
// Effekt: keiner
/*
Tests:
is_sorted(List(1, 2, 3, 4), (_:Int) < (_:Int)) == true
is_sorted(List(1, 3, 3, 4), (_:Int) < (_:Int)) == false
is_sorted(List(1, 3, 3, 4), (_:Int) <= (_:Int)) == true
is_sorted(List(1, 3, 2, 4), (_:Int) <= (_:Int)) == false
is_sorted(List(4, 3, 2, 1), (_:Int) > (_:Int)) == true
is_sorted(List('d', 'c', 'b', 'a'), (_:Char) > (_:Char)) == true
is_sorted(List('d', 'b', 'c', 'a'), (_:Char) > (_:Char)) == false
 */

@tailrec
def is_sorted[T](list: List[T], f: (T, T) => Boolean): Boolean = {
  list match {
    case x1::x2::xs => f(x1, x2) && is_sorted(x2::xs, f)
    case _ => true
  }
}


// take_numbers(List[Int], Int, Int, Int => Int) : List[Int]
// Voraussetzung: die Liste besteht aus ganzen positiven Zahlen
// Ergebnis: Eine Liste mit n ersten Elementen ist geliefert, deren Quersumme
// größer als eine bestimmte Zahl ist
// Effekt: keiner
/*
Tests:
take_numbers(List(1254, 399, 567, 2143812, 3437, 119, 33221, 998), 3, 10, count_digits) == (1254, 399, 567)
take_numbers(List(1254, 399, 567, 2143812, 3437, 119, 33221, 998), 3, 15, count_digits) == (399, 567, 2143812)
take_numbers(List(1254, 399, 587, 2143812, 3437, 119, 33221, 998), 3, 20, count_digits) == (399, 2143812, 998)
take_numbers(List(1254, 399, 587, 2143812, 3437, 119, 33221, 998), 3, 25, count_digits) == (998)
take_numbers(List(1254, 399, 587, 2143812, 3437, 119, 33221, 998), 3, 100, count_digits) == ()
 */

def take_numbers(list: List[Int], n: Int, value: Int, f: Int => Int): List[Int] = {
  n match {
    case 0 => Nil
    case _ => list match {
      case Nil => Nil
      case x::xs => if (value > f(x)) {take_numbers(xs, n, value, f)} else x :: take_numbers(xs, n - 1, value, f)
    }
  }
}


// reverse[T](List[T]) : List[T]
// Voraussetzung: keine
// Ergebnis: Eine umgedrehte Liste ist geliefert
// Effekt: keiner
/*
Tests:
reverse(List(1, 2, 3, 4)) == (4, 3, 2, 1)
reverse(List(4, 3, 2, 1)) == (1, 2, 3, 4)
reverse(List(9, 9, 9, 9)) == (9, 9, 9, 9)
reverse(List(9, 9, 9, 1)) == (1, 9, 9, 9)
 */

def reverse[T](list: List[T]): List[T] = {
  list.foldRight(Nil.asInstanceOf[List[T]])((x, xs) => xs ::: List(x))
}


// partition[T](List[T], T => Boolean) : List[List[T]]
// Voraussetzung: keine
// Ergebnis: Es ist eine Liste aus zwei Listen geliefert. Die erste Innenliste besteht
// aus Elementen, die das Prädikat erfüllen, die zweite Liste besteht aus Elementen, die
// das Prädikat nicht erfüllen
// Effekt: keiner
/*
Tests:
partition(List(1, 2, 3, 4, 5, 6, 7, 8), (x:Int)=>x%2==0) == ((2, 4, 6, 8), (1, 3, 5, 7))
partition(List(1, 2, 3, 4, 5, 6, 7, 8), (x:Int)=>x%3==0) == ((3, 6), (1, 2, 4, 5, 7, 8))
partition(List(1, 2, 3, 4, 5, 6, 7, 8), (x:Int)=>x%10==0) == ((), (1, 2, 3, 4, 5, 6, 7, 8))
partition(List('a', 'b', 'c', 'd', 'e'), (x:Char)=>x>'c') == (('d', 'e'), ('a', 'b', 'c'))
 */

def partition[T](list: List[T], p: T => Boolean): List[List[T]] = {
  def partition_help(list: List[T], p: T => Boolean) : List[T] = {
    list match {
      case Nil => Nil
      case x::xs => if (p(x)) {x::partition_help(xs, p)} else partition_help(xs, p)
    }
  }
  List(partition_help(list, p), partition_help(list, (x:T) => !p(x)))
}


// lconcat[T](List[List[T]]) : List[T]
// Voraussetzung: eine List von Listen des gleichen Typs wird in die Funktion
// übertragen
// Ergebnis: Eine Liste, die alle Elemente der Unterlisten enthält, ist geliefert
// Effekt: keiner
/*
Tests:
lconcat(List(List(1,3),List(4,5),Nil,List(3))) == (1, 3, 4, 5, 3)
lconcat(List(Nil, Nil, Nil)) == ()
lconcat(List(Nil, Nil, Nil, List(1))) == (1)
 */

def lconcat[T](list: List[List[T]]): List[T] = {
  list match {
    case Nil => Nil
    case x::xs => x:::lconcat(xs)
  }
}


// argmax(List[BigDecimal], BigDecimal => BigDecimal) : BigDecimal
// Voraussetzung: die Liste besteht aus Zahlen und für alle Werte, die in der Liste
// enthalten sind, ist die Funktion definiert
// Ergebnis: der Wert aus der Liste ist geliefert, bei dem die Funktion
// den größten Wert annimmt
// Effekt: keiner
/*
Tests:
argmax((-10.0 to 10.0 by 0.1).toList, x => -x*x + 6*x - 5) == 3.0
argmax((-10.0 to 10.0 by 0.1).toList, x => x*x + 6*x - 5) == 10.0
argmax((-10.0 to 10.0 by 0.1).toList, x => 0) == -10.0
argmax((-10.0 to 10.0 by 0.1).toList, x => -x*x*x*x) == 0.0
 */

def argmax(list: List[BigDecimal], f: BigDecimal => BigDecimal): BigDecimal = {
  @tailrec
  def argmax_help(list: List[BigDecimal], max_x: BigDecimal): BigDecimal = {
    list match {
      case Nil => max_x
      case x::xs => if (f(x) > f(max_x)) {argmax_help(xs, x)} else argmax_help(xs, max_x)
    }
  }
  argmax_help(list.drop(1), list.head)


  /*
    Aufgabe 2d:
      foldr((x:Int,rek:List[Int])=>x::rek, Nil, List(1,2,3,4)):
        1::(2, 3, 4) => 1::foldr(f, Nil, (2, 3, 4))
              2::(3, 4) => 2::foldr(f, Nil, (3, 4))
                    3::(4) => 3::foldr(f, Nil, (4))
                       4::Nil => 4::foldr(f, Nil, Nil)
                                    Nil
        Am Ende hat man dann folgendes:
        1::(2::(3::(4::Nil)))
  
      foldl((rek:List[Int],x:Int)=>x::rek, Nil, List(1,2,3,4))
        1::(2, 3, 4) => foldl(f, (1::Nil), (2, 3, 4))
        2::(3, 4) => foldl(f, 2::(1::Nil), (3, 4))
        3::(4) => foldl(f, 3::(2::(1::Nil)), (4))
        4::Nil => foldl(f, 4::(3::(2::(1::Nil))), Nil)
        => 4::(3::(2::(1::Nil)))
        Am Ende hat man dann folgendes:
        4::(3::(2::(1::Nil)))
     */
}

