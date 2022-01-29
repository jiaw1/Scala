package o1.items

// This class is introduced in Chapter 7.3.

import scala.collection.mutable.Buffer


class Container(name: String) extends Item(name) {

  private val contentBuffer = Buffer[Item]()

  def addContent(newContent: Item): Unit = {
    this.contentBuffer += newContent
  }

  def contents = this.contentBuffer.toVector

  override def toString = super.toString + " containing " + this.contents.size + " item(s)"

}