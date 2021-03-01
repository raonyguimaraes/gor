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

package gorsat;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by sigmar on 12/06/2017.
 */
public class UTestBug {
    @Test
    public void testBug() {
        String[] args = new String[]{"gor ../tests/data/gor/genes.gorz | bug process:0.1 | rownum | join -segseg ../tests/data/gor/genes.gorz | bug process:0.1 | group chrom -count"};
        boolean error = false;

        try {
            TestUtils.runGorPipeCount(args);
        } catch (Exception e) {
            error = true;
        }

        Assert.assertTrue("No bug", error);
    }
}
