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

package gorsat.Monitors

import gorsat.Commands.Analysis
import gorsat.gorsatGorIterator.MemoryMonitorUtil
import org.gorpipe.exceptions.custom.GorLowMemoryException
import org.gorpipe.gor.model.Row

case class MemoryMonitor(logname: String,
                         minFreeMemMB: Int = MemoryMonitorUtil.memoryMonitorMinFreeMemMB,
                         minFreeMemRatio: Float = MemoryMonitorUtil.memoryMonitorMinFreeMemRatio) extends Analysis {
  val mmu: MemoryMonitorUtil = new MemoryMonitorUtil((actualFreeMem: Long, args: List[_]) => {
    val msg = "MemoryMonitor: Out of memory executing " + logname + "(line " + mmu.lineNum + ").  Free mem down to " + actualFreeMem / mmu.bytesInMB + " MB.\n" + args.head
    throw new GorLowMemoryException(msg)
  }, minFreeMemMB, minFreeMemRatio)

  override def process(r: Row) {
    mmu.check(r)
    super.process(r)
  }
}