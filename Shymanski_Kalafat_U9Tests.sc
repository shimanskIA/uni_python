import scala.math.BigDecimal.double2bigDecimal

replace(List(1, 2, 2, 3, 4), 5, 1)
replace(List(1, 2, 2, 3, 4), 2, 3)
replace(List(1, 2, 2, 3, 4), 3, 2)
replace(List(1, 1, 1, 1, 1), 1, 10)
replace(List('a', 'c', 'b', 'c', '1', 'a'), 'c', 'e')
replace(List('a', 'c', 'b', 'c', '1', 'a'), 'e', 'c')

replace_rec(List(1, 2, 2, 3, 4), 5, 1)
replace_rec(List(1, 2, 2, 3, 4), 2, 3)
replace_rec(List(1, 2, 2, 3, 4), 3, 2)
replace_rec(List(1, 1, 1, 1, 1), 1, 10)
replace_rec(List('a', 'c', 'b', 'c', '1', 'a'), 'c', 'e')
replace_rec(List('a', 'c', 'b', 'c', '1', 'a'), 'e', 'c')

is_sorted(List(1, 2, 3, 4), (_:Int) < (_:Int))
is_sorted(List(1, 3, 3, 4), (_:Int) < (_:Int))
is_sorted(List(1, 3, 3, 4), (_:Int) <= (_:Int))
is_sorted(List(1, 3, 2, 4), (_:Int) <= (_:Int))
is_sorted(List(4, 3, 2, 1), (_:Int) > (_:Int))
is_sorted(List('d', 'c', 'b', 'a'), (_:Char) > (_:Char))
is_sorted(List('d', 'b', 'c', 'a'), (_:Char) > (_:Char))

take_numbers(List(1254, 399, 567, 2143812, 3437, 119, 33221, 998), 3, 10, count_digits)
take_numbers(List(1254, 399, 567, 2143812, 3437, 119, 33221, 998), 3, 15, count_digits)
take_numbers(List(1254, 399, 587, 2143812, 3437, 119, 33221, 998), 3, 20, count_digits)
take_numbers(List(1254, 399, 587, 2143812, 3437, 119, 33221, 998), 3, 25, count_digits)
take_numbers(List(1254, 399, 587, 2143812, 3437, 119, 33221, 998), 3, 100, count_digits)

reverse(List(1, 2, 3, 4))
reverse(List(4, 3, 2, 1))
reverse(List(9, 9, 9, 9))
reverse(List(9, 9, 9, 1))

partition(List(1, 2, 3, 4, 5, 6, 7, 8), (x:Int)=>x%2==0)
partition(List(1, 2, 3, 4, 5, 6, 7, 8), (x:Int)=>x%3==0)
partition(List(1, 2, 3, 4, 5, 6, 7, 8), (x:Int)=>x%10==0)
partition(List('a', 'b', 'c', 'd', 'e'), (x:Char)=>x>'c')

lconcat(List(List(1,3),List(4,5),Nil,List(3)))
lconcat(List(Nil, Nil, Nil))
lconcat(List(Nil, Nil, Nil, List(1)))

argmax((-10.0 to 10.0 by 0.1).toList, x => -x*x + 6*x - 5) 
argmax((-10.0 to 10.0 by 0.1).toList, x => x*x + 6*x - 5)
argmax((-10.0 to 10.0 by 0.1).toList, x => 0)
argmax((-10.0 to 10.0 by 0.1).toList, x => -x*x*x*x)