import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {

    type Daytime = (Int, Int, Int)


    // get_t_chill(double, double) : double
    // Voraussetzung: v und t sind Zahlen
    // Ergebnis: Die gefühlte Temperatur vom Wind ist geliefert
    // Effekt: keiner
    /*
    Tests:
    get_t_chill(0, 0) == 13.12
    Sonst weiß ich nicht, wenn die Formel richtig ist, muss dann der
    Rest auch stimmen
     */

    def get_t_chill(v: Double, t:Double): Double = {
      13.12 + 0.6215 * t + (0.3965 * t - 11.37) * math.pow(v, 0.16)
    }


    // invert(int) : int
    // Voraussetzung: eine natürliche Zahl wird in die Funktion eingegeben
    // Ergebnis: eine umgedrehte (verglichen mit n) Zahl ist geliefert
    // Effekt: keiner
    /*
    Tests:
    invert(47142) == 24174
    invert(99991) == 19999
    invert(1) == 1
    invert(666) == 666
     */

    def invert(n: Int): Int = {
      @tailrec
      def step(acc1: Int, acc2: Int, i: Int): Int = {
        i match
        {
          case 0 => acc1
          case _ => step((acc1 + (acc2 % 10) * math.pow(10, i - 1)).asInstanceOf[Int], acc2 / 10, i - 1)
        }
      }
      step(0, n, n.toString.length)
    }


    // count_as_troll(int) : string
    // Voraussetzung: eine natürliche Zahl wird in die Funktion eingegeben
    // Ergebnis: ein String mit 'Trollvorstellung' von der Zahl ist geliefert
    // Effekt: keiner
    /*
    Tests:
    count_as_troll(32) == "LOTS-LOTS"
    count_as_troll(31) == "LOTS-Many-Many-Many-Three"
    count_as_troll(33) == "LOTS-LOTS-One"
    count_as_troll(1) == "One"
    count_as_troll(7) == "Many-Three"
     */

    def count_as_troll(n: Int): String = {
      @tailrec
      def step(acc: String, j: Int, m: Int, i: Int): String = {
        i match
        {
          case 0 =>
            j match
            {
              case 0 => (acc + "Many-" * m).dropRight(1)
              case 1 => acc + "Many-" * m + "One"
              case 2 => acc + "Many-" * m + "Two"
              case 3 => acc + "Many-" * m + "Three"
            }
          case _ =>
            if (j == 3)
              {
                if (m == 3)
                  {
                    step(acc + "LOTS-", 0, 0, i - 1)
                  }
                else step(acc, 0, m + 1, i - 1)
              }
            else
              {
                step(acc, j + 1, m, i - 1)
              }
        }
      }
      step("", 0, 0, n)
    }


    // is_leap_year(int) : bool
    // Voraussetzung: eine natürliche Zahl wird in die Funktion eingegeben
    // Ergebnis: ein Wahrheitswert davon, dass das eingegebene Jahr ein Schaltjahr ist,
    // ist geliefert
    // Effekt: keiner
    /*
    Tests:
    is_leap_year(2008) == true
    is_leap_year(1900) == false
    is_leap_year(2020) == true
    is_leap_year(2021) == false
     */

    def is_leap_year(year: Int): Boolean = {
      if (year % 4 == 0)
        {
          if (year % 100 != 0)
            {
              true
            }
          else
            {
              if (year % 400 == 0) true
              else false
            }
        }
      else false
    }


    // count_digits(int) : int
    // Voraussetzung: eine natürliche Zahl wird in die Funktion eingegeben
    // Ergebnis: die Quersumme der eingegebenen Zahl ist geliefert
    // Effekt: keiner
    /*
    Tests:
    count_digits(47142) == 18
    count_digits(24174) == 18
    count_digits(99999) == 45
    count_digits(1) == 1
     */

    def count_digits(n: Int) = {
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


    // is_daytime(Daytime) : bool
    // Voraussetzung: ein Tuple aus drei ganzen Zahlen wird eingegeben
    // Ergebnis: der Wahrheitswert davon, dass die drei im Tuple enthaltenen
    // ganzen Zahlen eine Uhrzeit darstellen, geliefert ist
    // Effekt: keiner
    /*
    Tests:
    is_daytime(0, 0, 0) == true
    is_daytime(23, 59, 59) == true
    is_daytime(24, 0, 0) == false
    is_daytime(0, -1, 0) == false
    is_daytime(17, 24, 55) == true
     */

    def is_daytime(time: Daytime): Boolean = {
      if ((time._1 < 24 && time._1 >= 0) && (time._2 < 60 && time._2 >= 0) && (time._3 < 60 && time._3 >= 0)) true
      else false
    }


    // tick(Daytime) : Daytime
    // Voraussetzung: eine Uhrzeit wird eingegeben
    // Ergebnis: die Uhrzeit nach 1 Sekunde ist geliefert
    // Effekt: keiner
    /*
    Tests:
    tick((13, 46, 8)) == (13, 46, 9)
    tick((13, 46, 59)) == (13, 47, 0)
    tick((13, 59, 59)) == (14, 0, 0)
    tick((23, 59, 59)) == (0, 0, 0)
     */

    def tick(time: Daytime): Daytime = {
      if (time._3 == 59)
        {
          if (time._2 == 59)
            {
              if (time._1 == 23)
                {
                  (0, 0, 0)
                }
              else
                {
                  (time._1 + 1, 0, 0)
                }
            }
          else
            {
              (time._1, time._2 + 1, 0)
            }
        }
      else
      {
        (time._1, time._2, time._3 + 1)
      }
    }


    // tick(Daytime) : Daytime
    // Voraussetzung: eine Uhrzeit wird eingegeben
    // Ergebnis: die Uhrzeit vor 1 Sekunde ist geliefert
    // Effekt: keiner
    /*
    Tests:
    kcit((13, 46, 8)) == (13, 46, 7)
    kcit((13, 46, 0)) == (13, 45, 59)
    kcit((13, 0, 0)) == (12, 59, 59)
    kcit((0, 0, 0)) == (23, 59, 59)
     */

    def kcit(time: Daytime): Daytime = {
      if (time._3 == 0)
      {
        if (time._2 == 0)
        {
          if (time._1 == 0)
          {
            (23, 59, 59)
          }
          else
          {
            (time._1 - 1, 59, 59)
          }
        }
        else
        {
          (time._1, time._2 - 1, 59)
        }
      }
      else
      {
        (time._1, time._2, time._3 - 1)
      }
    }


    // add_seconds(Daytime) : Daytime
    // Voraussetzung: eine Uhrzeit und eine natürliche Anzahl
    // von Sekunden werden eingegeben
    // Ergebnis: die Uhrzeit nach der eingegebenen Anzahl von Sekunden
    // ist geliefert
    // Effekt: keiner
    /*
    Tests:
    add_seconds((13, 45, 8), 25) == (13, 45, 33)
    add_seconds((13, 45, 8), 58) == (13, 46, 6)
    add_seconds((13, 45, 8), 60) == (13, 46, 8)
    add_seconds((13, 45, 8), 3600) == (14, 45, 8)
    add_seconds((23, 58, 59), 1) == (23, 59, 0)
    add_seconds((23, 59, 59), 1) == (0, 0, 0)
     */

    def add_seconds(time: Daytime, seconds: Int): Daytime = {
      @tailrec
      def step(step_time: Daytime, i: Int ) : Daytime = {
        i match
        {
          case 0 => step_time
          case _ => step(tick(step_time), i - 1)
        }
      }
      step(time, seconds)
    }


    // add_minutes(Daytime) : Daytime
    // Voraussetzung: eine Uhrzeit und eine natürliche Anzahl
    // von Minuten werden eingegeben
    // Ergebnis: die Uhrzeit nach der eingegebenen Anzahl von Minuten
    // ist geliefert
    // Effekt: keiner
    /*
    Tests:
    add_minutes((13, 45, 8), 8) == (13, 53, 8)
    add_minutes((13, 45, 8), 33) == (14, 18, 8)
    add_minutes((13, 45, 8), 60) == (14, 45, 8)
    add_minutes((23, 59, 0), 1) == (0, 0, 0)
     */

    def add_minutes(time: Daytime, minutes: Int): Daytime = {
      add_seconds(time, minutes * 60)
    }


    // add_hours(Daytime) : Daytime
    // Voraussetzung: eine Uhrzeit und eine natürliche Anzahl
    // von Stunden werden eingegeben
    // Ergebnis: die Uhrzeit nach der eingegebenen Anzahl von Stunden
    // ist geliefert
    // Effekt: keiner
    /*
    Tests:
    add_hours((13, 45, 8), 2) == (15, 45, 8)
    add_hours((13, 45, 8), 24) == (13, 45, 8)
    add_hours((13, 45, 8), 16) == (8, 45, 8)
     */

    def add_hours(time: Daytime, hours: Int): Daytime = {
      ((hours + time._1) % 24, time._2, time._3)
    }

    println
    println(get_t_chill(0, 0))
    println

    println(invert(47142))
    println(invert(99991))
    println(invert(1))
    println(invert(666))
    println

    println(count_as_troll(32))
    println(count_as_troll(31))
    println(count_as_troll(33))
    println(count_as_troll(1))
    println(count_as_troll(7))
    println

    println(is_leap_year(2008))
    println(is_leap_year(1900))
    println(is_leap_year(2020))
    println(is_leap_year(2021))
    println

    println(count_digits(47142))
    println(count_digits(24174))
    println(count_digits(99999))
    println(count_digits(1))
    println

    println(is_daytime(0, 0, 0))
    println(is_daytime(23, 59, 59))
    println(is_daytime(24, 0, 0))
    println(is_daytime(0, -1, 0))
    println

    println(tick((13, 46, 8)))
    println(tick((13, 46, 59)))
    println(tick((13, 59, 59)))
    println(tick((23, 59, 59)))
    println

    println(kcit(13, 46, 8))
    println(kcit(13, 46, 0))
    println(kcit(13, 0, 0))
    println(kcit(0, 0, 0))
    println

    println(add_seconds((13, 45, 8), 25))
    println(add_seconds((13, 45, 8), 58))
    println(add_seconds((13, 45, 8), 60))
    println(add_seconds((13, 45, 8), 3600))
    println(add_seconds((23, 58, 59), 1))
    println(add_seconds((23, 59, 59), 1))
    println

    println(add_minutes((13, 45, 8), 8))
    println(add_minutes((13, 45, 8), 33))
    println(add_minutes((13, 45, 8), 60))
    println(add_minutes((23, 59, 0), 1))
    println

    println(add_hours((13, 45, 8), 2))
    println(add_hours((13, 45, 8), 24))
    println(add_hours((13, 45, 8), 19))
    println

    /*
    Aufgabe 1b:
    acc1 = 0  acc2 = 47142  i = 5
      acc1 = 0 + (47142 % 10) * 10^4 = 20000
      acc2 = 47142 / 10 = 1714
      i = 5 - 1 = 4
    acc1 = 20000  acc2 = 4714  i = 4
      acc1 = 20000 + (4714 % 10) * 10^3 = 24000
      acc2 = 4714 / 10 = 471
      i = 4 - 1 = 3
    acc1 = 24000  acc2 = 471  i = 3
      acc1 = 24000 + (471 % 10) * 10^2 = 24100
      acc2 = 471 / 10 = 47
      i = 3 - 1 = 2
    acc1 = 24100  acc2 = 47  i = 2
      acc1 = 24100 + (47 % 10) * 10 = 24170
      acc2 = 47 / 10 = 4
      i = 2 - 1 = 1
    acc1 = 24170  acc2 = 4  i = 1
      acc1 = 24170 + (4 % 10) * 1 = 24174
      acc2 = 4 / 10 = 0
      i = 1 - 1 = 0
    return acc1 = 24174
     */

    /*
    Aufgabe 1d:
      Rekursion:
        Vorteile:
          - bessere Lesbarkeit
              Implementierung entspricht besser dem sachlichen
              Zusammenhang
          - weniger Code ist nötig
          - einige Algorithmen sind wesentlich einfacher
          mit Rekursion
            Zum Beispiel die, die mit Baumstruktur
            zu tun haben
        Nachteile:
          - speicheraufwendig
              Verglichen mit Schleifen
          - längere Laufzeiten
              Verglichen mit Schleifen
          - Schwierigkeiten beim Debugging
          - Möglichkeit den Aufrufstack zu überfluten

        Schleifen:
          Vorteile:
            - Einfachheit und Klarheit
                Es ist einfacher eine Schleife zu programmieren als
                einen rekursiven oder endrekursiven Algorithmus
            - Effektivität und Performance
                Schleifen sind normalerweise schneller als Rekursion
                und als Endrekursion. Außerdem wird dabei kein Stack
                benutzt, was vorteilhaft für den Speichergebrauch ist
            - bessere Kontrolle über den Programmzustand
          Nachteile:
            - mehr Code ist nötig
                Verglichen mit Rekursion und Endrekursion
            - eignen sich nicht für alle Aufgaben
                z.B. Gauss Aufgaben mit Schachfiguren werden zu lange ausgeführt
                wegen der Durchgang aller Möglichen Kombinationen.
            - nicht immer durchsichtig

        Endrekursion:
          Vorteile:
            - bessere Lesbarkeit
                Im Vergleich zu Schleifen. Allerdings ist die
                Lesbarkeit schlechter, als bei einer vollständigen
                Rekursion
            - weniger Code ist nötig
                Verglichen mit Schleifen. Für eine vollständige Rekursion
                braucht man jedoch weniger Code
            - einige Algorithmen sind wesentlich einfacher
              mit Rekursion
            - erkennbar von einigen Programmiersprachen
                Zum Beispiel Scala kann Endrekursion erkennen und die
                durch Schleifen ersetzen. Somit hat man sowohl die Vorteile
                von Schleifen als auch von der Rekursion
          Nachteile:
            - speicheraufwendig
                Verglichen mit Rekursion ist weniger speicheraufwendig. Im
                Vergleich mit Schleifen - doch.
            - längere Laufzeiten
                Verglichen mit Schleifen. Jedoch ist schneller als
                vollständige Rekursion
            - Schwierigkeiten beim Debugging
            - Möglichkeit den Aufrufstack zu überfluten
                Passiert aber wesentlich seltener als bei Rekursion

          Beispiel für Fakultät:

          def factorial(n):
            if n == 0: return 1
            else return n * fact(n - 1)

          Eine Rekursive Funktion ist intuitiv klar und sehr einfach lesbar und besteht dazu
          nur aus zwei Zeilen. Das Problem ist aber, dass wenn man versucht Fakultät von
          1000 zu finden, dann kriegt man RecursionError: maximum recursion depth exceeded

          def factorial2(n):
            res = 1
            for i in range(1, n):
              res = res * i
            return res

          Für die Funktion mit der Schleife gibt dieses Problem nicht und man kann ruhig
          Fakultät von 1000 errechnen.

          Die Vorteile von der Endrekursion über Rekursion kann man am Beispiel vom Fibonacci
          Algorithmus merken. Das Beispiel wurde im Video gezeigt. Also ein rekursiver
          Algorithmus hat dabei Laufzeit O(4^n), was enorm aufwendig ist. Mit Endrekursion
          hat es gelungen die Laufzeit wesentlich zu verbessen und den Algorithmus für die
          größeren Zahlen anwendbar zu machen.
     */

    /*
    Aufgabe 2d:
    Referenzielle Transparenz bedeutet, dass eine Funktion (oder die ganze Programm)
    durch einen Wert ersetzt werden kann. Das bedeutet, dass jedes Mal, wenn wir
    die gleichen Parameter in die Funktion übertragen, bekommen wir auch den gleichen
    Wert geliefert. Ein Beispiel in Python könnte sein:

    def mul_transparent(a, b):                      def mul_not_transparent(a, b):
      return a * b                                    input(a)
                                                      input(b)
                                                      return a * b

     Im ersten Fall ist die Funktion nur von den Eingabewerten abhängig und bei gleichen
     Eingabewerten wird immer das gleiche Ergebnis geliefert.
     Zum Beispiel mul_transparent(7, 8) wird immer 56 sein und jeder Aufruf der Funktion
     kann durch diesen Wert ersetzt werden.
     Die zweite Funktion ist dagegen nicht transparent, da die Eingabewerte während der
     Ausführung vom Nutzer neu initialisiert werden. D.h. jetzt ist die Funktion auch vom User
     abhängig und ist nicht transparent.
     Transparente Funktionen haben den Vorteil, dass sie unabhängig sind und deswegen
     können sie einfacher getestet und verbessert (refactored) werden. Außerdem sind
     die transparenten Funktionen einfach besser lesbar und verständlicher.
     Manchmal kann auch sein, dass ein bestimmter Zweck mit einer transparenter Funktion
     nicht erreichbar ist, dann muss man eine nicht transparente Implementierung nutzen.
     */
  }
}