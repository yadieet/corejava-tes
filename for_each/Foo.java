/*
 * Copyright (C) 2016 Yadieet SA <yadieet@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package forEach;

import java.util.Iterator;

/**
 * Created by Yadieet SA on 15/06/16.
 */
final class Foo<E> implements Iterable<E> //for testing-purpose only
{
    private final E[] elements;

    Foo( E[] elements )
    {
        this.elements = elements;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new Iterator<E>()
        {
            private int i = elements.length;

            @Override
            public boolean hasNext()
            {
                return i > 0;
            }

            @Override
            public E next()
            {
                return elements[--i];
            }
        };
    }

}
