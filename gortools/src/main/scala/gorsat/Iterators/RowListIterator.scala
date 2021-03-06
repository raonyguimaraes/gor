/*
 *  BEGIN_COPYRIGHT
 *
 *  Copyright (C) 2011-2013 deCODE genetics Inc.
 *  Copyright (C) 2013-2021 WuXi NextCode Inc.
 *  All Rights Reserved.
 *
 *  GORpipe is free software: you can redistribute it and/or modify
 *  it under the terms of the AFFERO GNU General Public License as published by
 *  the Free Software Foundation.
 *
 *  GORpipe is distributed "AS-IS" AND WITHOUT ANY WARRANTY OF ANY KIND,
 *  INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 *  NON-INFRINGEMENT, OR FITNESS FOR A PARTICULAR PURPOSE. See
 *  the AFFERO GNU General Public License for the complete license terms.
 *
 *  You should have received a copy of the AFFERO GNU General Public License
 *  along with GORpipe.  If not, see <http://www.gnu.org/licenses/agpl-3.0.html>
 *
 *  END_COPYRIGHT
 */

package gorsat.Iterators

import org.gorpipe.gor.driver.providers.stream.datatypes.gor.GorHeader
import org.gorpipe.gor.model.Row
import org.gorpipe.model.gor.iterators.RowSource

case class RowListIterator(lineList: List[Row]) extends RowSource {
  private var gorHeader: GorHeader = _
  var l: List[Row] = lineList

  override def hasNext: Boolean = l.nonEmpty

  override def next(): Row = {
    val r = l.head; l = l.tail; r
  }

  override def setPosition(seekChr: String, seekPos: Int) {}

  def close() {}

  override def getGorHeader: GorHeader = gorHeader
  def setGorHeader(gh: GorHeader): Unit = gorHeader = gh
}
