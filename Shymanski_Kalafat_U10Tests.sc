is_sorted(List(1, 2, 3, 4))
is_sorted(List(1, 3, 3, 4))
is_sorted(List(1, 3, 2, 4))
is_sorted(List(4, 3, 2, 1))
is_sorted(List('d', 'c', 'b', 'a'))
is_sorted(List('d', 'b', 'c', 'a'))
is_sorted[Int](List())

sum_smaller(List(5, 9, 4, 1, 15, -6, 5), 4)
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), 50)
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), 0)
sum_smaller(List(5, 9, 4, 1, 15, -6, 5), -10)
sum_smaller(List(1), -5)
sum_smaller(List(1), 5)
sum_smaller(List(), 5)

import EntwederOder.*
safe_divide(5.4, 2.8)
safe_divide(7, 0)

unspecified_divide(5, 2)
unspecified_divide(2, 5)
unspecified_divide(4, 2)

parse_year(UserType1(1999))
parse_year(UserType2("48 AC"))
parse_year(UserType2("324 BC"))


import LogicGate.*
eval(NOT(AND(TRUE, TRUE)))
eval(NOT(AND(OR(TRUE, FALSE), NOT(OR(FALSE, TRUE)))))

negate_vals(NOT(AND(TRUE, TRUE)))
negate_vals(NOT(AND(OR(TRUE, FALSE), NOT(OR(FALSE, TRUE)))))

merge_sort(List(2, 11, 7, 14, 2, -5, 3, 22, 4, 5, 9))
merge_sort(List(7, 1, 5))
merge_sort(List(1, 2, 3, 4, 5))
merge_sort(List(5, 4, 3, 2, 1))
merge_sort(List(5, 3, 4, 2, 1))
merge_sort[Int](List())

import Vielleicht.*
map(Wert(5), (_:Int) > 3)
map(Nichts, (_:Int) > 3)

extract[Double](List(Wert(1), Nichts, Wert(17), Wert(2.3), Nichts, Wert(-2)))
extract(List(Wert(1), Nichts, Wert(17), Wert(2.3), Nichts, Wert(-2)))

import Link.*
import Chain.*
to_list(Join(Join(Empty, G, Empty), S, Join(Empty, S, Join(Empty, P, Empty))))
to_list(Empty)

get_price(Join(Join(Empty, G, Empty), S, Join(Empty, S, Join(Empty, P, Empty))), get_link_price)
get_price(Empty, get_link_price)

get_price_map(Join(Join(Empty, G, Empty), S, Join(Empty, S, Join(Empty, P, Empty))), get_link_price)
get_price_map(Empty, get_link_price)