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

package synchronizedMethod;

import yadieet.MiscUtility;

/**
 * Created by Yadieet SA on 23/06/16.
 */
final class Foo
{

    synchronized void a()
    {
        for (int i = 0; i++ < 5; )
        {
            System.out.println(Thread.currentThread().getName() + " : foo.a() : " + i);
            MiscUtility.threadSleep(600L);
        }
    }

    void x()
    {
        System.out.println(Thread.currentThread().getName() + " : foo.x()");
    }

    synchronized void b()
    {
        for (int i = 0; i++ < 5; )
        {
            System.out.println(Thread.currentThread().getName() + " : foo.b() : " + i);
            MiscUtility.threadSleep(600L);
        }
    }

    void y()
    {
        System.out.println(Thread.currentThread().getName() + " : foo.y()");
    }

    synchronized void c()
    {
        for (int i = 0; i++ < 5; )
        {
            System.out.println(Thread.currentThread().getName() + " : foo.c() : " + i);
            MiscUtility.threadSleep(600L);
        }
    }

    void d()
    {
        synchronized( Foo.this )
        {
            for (int i = 0; i++ < 5; )
            {
                System.out.println(Thread.currentThread().getName() + " : foo.d() : " + i);
                MiscUtility.threadSleep(600L);
            }
        }
    }
}
