package spinal

import spinal.core._
import spinal.idslplugin.{PostInitCallback, ValCallback}



//class Wuff extends PostInitCallback{
//  println("Wuff init")
//  override def postInitCallback() = {
//    println("WUFF postInitCallback")
//    this
//  }
//}
//
//class Rawrr(arg : Int) extends Wuff{
//  println("Rawrr init")
//}
//
//object Miaou {
//  def main(args: Array[String]): Unit = {
//    val x = new Rawrr(2)
//  }
//}

//class IoBundle extends Bundle
//
//class CCCCCCCCCC1 {
//  val x = 4
//  val io = new Bundle
//  val y = 6
//}

//object Main{
//  def main(args: Array[String]): Unit = {
//    new CCCCCCCCCC1
//  }
//}
object Main{
  def main(args: Array[String]): Unit = {
    new CCCCCCCCCC1
  }
}


class CCCCCCCCCC1 extends Component {
  val io = new Bundle{

  }
  val x = 1
  val y = 2
  val z = new {
    val aa = 5
  }


  println("CCCCCCCCCC1 constructor done")
}

