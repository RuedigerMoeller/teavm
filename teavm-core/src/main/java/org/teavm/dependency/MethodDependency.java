/*
 *  Copyright 2012 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.dependency;

import java.util.Arrays;
import org.teavm.model.MethodReader;
import org.teavm.model.MethodReference;

/**
 *
 * @author Alexey Andreev
 */
public class MethodDependency implements MethodDependencyInfo {
    private DependencyChecker dependencyChecker;
    private DependencyNode[] variableNodes;
    private int parameterCount;
    private DependencyNode resultNode;
    private DependencyNode thrown;
    private DependencyStack stack;
    private MethodReader method;
    private MethodReference reference;
    private boolean used;

    MethodDependency(DependencyChecker dependencyChecker, DependencyNode[] variableNodes, int parameterCount,
            DependencyNode resultNode, DependencyNode thrown, DependencyStack stack, MethodReader method,
            MethodReference reference) {
        this.dependencyChecker = dependencyChecker;
        this.variableNodes = Arrays.copyOf(variableNodes, variableNodes.length);
        this.parameterCount = parameterCount;
        this.thrown = thrown;
        this.resultNode = resultNode;
        this.stack = stack;
        this.method = method;
        this.reference = reference;
    }

    public DependencyAgent getDependencyAgent() {
        return dependencyChecker;
    }

    @Override
    public DependencyNode[] getVariables() {
        return Arrays.copyOf(variableNodes, variableNodes.length);
    }

    @Override
    public int getVariableCount() {
        return variableNodes.length;
    }

    @Override
    public DependencyNode getVariable(int index) {
        return variableNodes[index];
    }

    @Override
    public int getParameterCount() {
        return parameterCount;
    }

    @Override
    public DependencyNode getResult() {
        return resultNode;
    }

    @Override
    public DependencyNode getThrown() {
        return thrown;
    }

    @Override
    public DependencyStack getStack() {
        return stack;
    }

    @Override
    public MethodReference getReference() {
        return reference;
    }

    public MethodReader getMethod() {
        return method;
    }

    @Override
    public boolean isMissing() {
        return method == null;
    }

    @Override
    public boolean isUsed() {
        return used;
    }

    public void use() {
        if (!used) {
            used = true;
            if (!isMissing()) {
                dependencyChecker.scheduleMethodAnalysis(this);
            }
        }
    }
}
