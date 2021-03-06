/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.utilities.collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Immortius
 */
public class TypeListMultimap<T> extends TypeMultimap<T> {

    private ListMultimap<Class<? extends T>, T> inner;

    TypeListMultimap(ListMultimap<Class<? extends T>, T> inner) {
        super(inner);
        this.inner = inner;
    }

    public TypeListMultimap<T> create() {
        return new TypeListMultimap<>(ArrayListMultimap.<Class<? extends T>, T>create());
    }

    public TypeListMultimap<T> createFrom(ListMultimap<Class<? extends T>, T> from) {
        return new TypeListMultimap<>(from);
    }

    public <U extends T> List<U> get(Class<U> key) {
        return convertList(key, inner.get(key));
    }

    public <U extends T> List<U> removeAll(Class<U> key) {
        return convertList(key, inner.removeAll(key));
    }

    public <U extends T> List<U> replaceValues(Class<U> key, Iterable<? extends U> values) {
        return convertList(key, inner.replaceValues(key, values));
    }

    public Collection<Map.Entry<Class<? extends T>, T>> entries() {
        return inner.entries();
    }

    private <U extends T> List<U> convertList(Class<U> type, Collection<T> values) {
        List<U> results = Lists.newArrayListWithCapacity(values.size());
        for (T value : values) {
            results.add(type.cast(value));
        }
        return results;
    }

}
