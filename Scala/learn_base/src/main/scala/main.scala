package edu.zjnu



/**
 * todo 
 *
 * @Date ${YEAR}-${MONTH}-${DAY} ${TIME}
 * @Author 杨海波
 **/

@main
def main(): Unit =

  (1 to 5).map(println)

  for (i <- 1 to 5) {
    println(s"i = $i")
  }
