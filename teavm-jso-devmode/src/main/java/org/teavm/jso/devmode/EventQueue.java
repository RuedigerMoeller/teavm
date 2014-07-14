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
package org.teavm.jso.devmode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Alexey Andreev <konsoletyper@gmail.com>
 */
public class EventQueue {
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private volatile boolean stopped = false;

    public void put(Runnable task) {
        queue.offer(task);
    }

    public void exec() {
        stopped = false;
        while (!stopped) {
            try {
                Runnable task = queue.poll(1, TimeUnit.SECONDS);
                if (task != null) {
                    task.run();
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void stop() {
        stopped = true;
        if (queue.isEmpty()) {
            queue.offer(new Runnable() {
                @Override public void run() { }
            });
        }
    }
}