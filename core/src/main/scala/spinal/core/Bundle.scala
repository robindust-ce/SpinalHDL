/*
 * SpinalHDL
 * Copyright (c) Dolu, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */

package spinal.core

import scala.collection.mutable.ArrayBuffer

/**
 * Created by PIC18F on 08.01.2015.
 */

object Bundle {

}





class Bundle extends MultiData with Nameable with OverridedEqualsHashCode {

  def assignAllByName(that: Bundle) = {
    for ((name, element) <- elements) {
      val other = that.find(name)
      if (other == null) SpinalError("Bundle assignement is not complet at " + ScalaLocated.getScalaTrace)
      element := other
    }
  }

  def assignSomeByName(that: Bundle) : Unit = {
    for ((name, element) <- elements) {
      val other = that.find(name)
      if (other != null) {
        element match{
          case b : Bundle => b.assignSomeByName(other.asInstanceOf[Bundle])
          case _ => element assignFrom(other, false)
        }
      }
    }
  }

  override def assignFromImpl(that: AnyRef, conservative: Boolean): Unit = {
    assert(!conservative)
    that match {
      case that: Bundle => {
        if(! this.getClass.isAssignableFrom(that.getClass)) SpinalError("Bundles must have the same final class to be assigned. Else use assignByName or assignSomeByName at \n" + ScalaLocated.getScalaTrace)
        for ((name, element) <- elements) {
          val other = that.find(name)
          if (other == null) SpinalError("Bundle assignement is not complet at " + ScalaLocated.getScalaTrace)
          element := other
        }
      }
      case _ => throw new Exception("Undefined assignement")
    }
  }

  private var elementsCache: ArrayBuffer[(String, Data)] = null


  def elements = {
    if (elementsCache == null) {
      elementsCache = ArrayBuffer[(String, Data)]()
      Misc.reflect(this, (name, obj) => {
        obj match {
          case data: Data => {
            if (data.parentData == this) { //To avoid bundle argument
              elementsCache += Tuple2(name, data)
            }
          }
          case _ =>
        }
      })
      elementsCache = elementsCache.sortWith(_._2.instanceCounter < _._2.instanceCounter)
    }
    elementsCache
  }
}
