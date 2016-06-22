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
final class Bar
{

    static synchronized void a()
    {
        for (int i = 0; i++ < 5; )
        {
            System.out.println(Thread.currentThread().getName() + " : Bar.a() : " + i);
            MiscUtility.threadSleep(600L);
        }
    }

    static void x()
    {
        System.out.println(Thread.currentThread().getName() + " : Bar.x()");
    }

    static synchronized void b()
    {
        for (int i = 0; i++ < 5; )
        {
            System.out.println(Thread.currentThread().getName() + " : Bar.b() : " + i);
            MiscUtility.threadSleep(600L);
        }
    }

    static void y()
    {
        System.out.println(Thread.currentThread().getName() + " : Bar.y()");
    }

    static synchronized void c()
    {
        for (int i = 0; i++ < 5; )
        {
            System.out.println(Thread.currentThread().getName() + " : Bar.c() : " + i);
            MiscUtility.threadSleep(600L);
        }
    }

    static void d()
    {
        synchronized( Bar.class )
        {
            for (int i = 0; i++ < 5; )
            {
                System.out.println(Thread.currentThread().getName() + " : Bar.d() : " + i);
                MiscUtility.threadSleep(600L);
            }
        }
    }
}
