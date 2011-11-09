/*
 * The MIT License
 *
 * Copyright (C) 2011 by Anthony Robinson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkins_ci.plugins.run_condition.logic;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.model.Hudson;
import org.jenkins_ci.plugins.run_condition.RunCondition;
import org.kohsuke.stapler.DataBoundConstructor;

import java.util.List;

public class ConditionContainer implements Describable<ConditionContainer> {

    private final RunCondition condition;

    @DataBoundConstructor
    public ConditionContainer(final RunCondition condition) {
        this.condition = condition;
    }

    public RunCondition getCondition() {
        return condition;
    }

    public ConditionContainerDescriptor getDescriptor() {
        return Hudson.getInstance().getDescriptorByType(ConditionContainerDescriptor.class);
    }

    public boolean runPrebuild(final AbstractBuild<?, ?> build, final BuildListener listener) throws Exception {
        return condition.runPrebuild(build, listener);
    }

    public boolean runPerform(final AbstractBuild<?, ?> build, final BuildListener listener) throws Exception {
        return condition.runPerform(build, listener);
    }

    @Extension
    public static class ConditionContainerDescriptor extends Descriptor<ConditionContainer> {

        @Override
        public String getDisplayName() {
            return "Should not appear anywhere";
        }

        public List<? extends Descriptor<? extends RunCondition>> getRunConditions() {
            return RunCondition.all();
        }

    }

}
