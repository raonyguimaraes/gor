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

package org.gorpipe.gor.cli.render;

import org.gorpipe.gor.cli.FormattingOptions;
import picocli.CommandLine;

@SuppressWarnings("squid:S106")
@CommandLine.Command(name = "render",
        aliases = {"r"},
        description="Renders reports and gor scripts to its runnable form.",
        header="Render reports and scripts",
        subcommands = {ReportCommand.class, ScriptCommand.class})
public class RenderCommand extends FormattingOptions implements Runnable{

    @Override
    public void run() { CommandLine.usage(this, System.err); }
}
