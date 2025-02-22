package edu.zjnu



/**
 * todo 
 *
 * @Date ${YEAR}-${MONTH}-${DAY} ${TIME}
 * @Author 杨海波
 **/


object Main {
  def main(args: Array[String]): Unit = {

    (1 to 5).foreach(println)

    for (i <- 1 to 5) {

      println(s"i = $i")
    }
  }
}
