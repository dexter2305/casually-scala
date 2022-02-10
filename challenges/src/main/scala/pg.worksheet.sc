val x = "1234567890"

(for (i <- 0 until x.length() by 2) yield (x.substring(i, i+2).reverse)).mkString