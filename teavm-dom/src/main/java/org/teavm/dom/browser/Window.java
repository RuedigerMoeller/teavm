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
package org.teavm.dom.browser;

import org.teavm.dom.ajax.XMLHttpRequest;
import org.teavm.dom.html.HTMLDocument;
import org.teavm.dom.typedarrays.ArrayBuffer;
import org.teavm.dom.typedarrays.Int8Array;
import org.teavm.jso.JSConstructor;
import org.teavm.jso.JSGlobal;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 *
 * @author Alexey Andreev
 */
public interface Window extends JSGlobal {
    @JSProperty
    HTMLDocument getDocument();

    void alert(JSObject message);

    void alert(String message);

    int setTimeout(TimerHandler handler, int delay);

    void clearTimeout(int timeoutId);

    int setInterval(TimerHandler handler, int delay);

    void clearInterval(int timeoutId);

    @JSConstructor("XMLHttpRequest")
    XMLHttpRequest createXMLHttpRequest();

    @JSConstructor("ArrayBuffer")
    ArrayBuffer createArrayBuffer(int length);

    @JSConstructor("Int8Array")
    Int8Array createInt8Array(int length);

    @JSConstructor("Int8Array")
    Int8Array createInt8Array(ArrayBuffer buffer);

    @JSConstructor("Int8Array")
    Int8Array createInt8Array(ArrayBuffer buffer, int offset, int length);

    @JSConstructor("Uint8ClampedArray")
    Int8Array createUint8ClampedArray(int length);

    @JSConstructor("Uint8ClampedArray")
    Int8Array createUint8ClampedArray(ArrayBuffer buffer);

    @JSConstructor("Uint8ClampedArray")
    Int8Array createUintClamped8Array(ArrayBuffer buffer, int offset, int length);
}
