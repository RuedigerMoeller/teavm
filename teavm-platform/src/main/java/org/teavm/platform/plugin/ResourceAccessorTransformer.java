/*
 *  Copyright 2014 Alexey Andreev.
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
package org.teavm.platform.plugin;

import org.teavm.model.ClassHolder;
import org.teavm.model.ClassHolderTransformer;
import org.teavm.model.ClassReaderSource;
import org.teavm.model.MethodHolder;
import org.teavm.vm.spi.TeaVMHost;

/**
 *
 * @author Alexey Andreev
 */
class ResourceAccessorTransformer implements ClassHolderTransformer {
    private TeaVMHost vm;

    public ResourceAccessorTransformer(TeaVMHost vm) {
        this.vm = vm;
    }

    @Override
    public void transformClass(ClassHolder cls, ClassReaderSource innerSource) {
        if (cls.getName().equals(ResourceAccessor.class.getName())) {
            ResourceAccessorGenerator generator = new ResourceAccessorGenerator();
            for (MethodHolder method : cls.getMethods()) {
                vm.add(method.getReference(), generator);
            }
        }
    }
}
