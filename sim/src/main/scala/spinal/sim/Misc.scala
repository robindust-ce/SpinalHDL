package spinal.sim

import java.io.File

import org.apache.commons.io.FileUtils

object SimError{
  def apply(message : String): Unit ={
    System.out.flush()
    Thread.sleep(20)
    System.err.println("\n\n" + message)
    throw new Exception()
  }
}

object WaveFormat{
  object VCD extends WaveFormat("vcd")
  object FST extends WaveFormat("fst")
  object DEFAULT extends WaveFormat
  object NONE extends WaveFormat
}

class WaveFormat(val ext : String = "???")


trait Backend{
  val uniqueId       = Backend.allocateUniqueId()
}

object Backend{
  private var uniqueId = 0
  def allocateUniqueId(): Int = {
    this.synchronized {
      uniqueId = uniqueId + 1
      uniqueId
    }
  }

  val osName         = System.getProperty("os.name").toLowerCase
  val isWindows      = osName.contains("windows")
  val isMac          = osName.contains("mac") || osName.contains("darwin")
  val isLinux = !isWindows && !isMac

  val jdk = System.getProperty("java.home").replace("/jre","").replace("\\jre","")
}
