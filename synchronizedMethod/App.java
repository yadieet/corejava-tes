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

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{
    private final Foo foo;

    private App()
    {
        super(App.class.getCanonicalName());

        foo = new Foo();
    }


    public static void main( String... args )
    {
        new App().start();
    }

    @Override
    protected void init()
    {
        {
            //stateless runnables
            Runnable foo_a = foo::a;
            Runnable foo_b = foo::b;
            Runnable foo_c = foo::c;
            Runnable foo_d = foo::d;
            Runnable foo_x = foo::x;
            Runnable foo_y = foo::y;

            Thread[] threads =
                {
                    new Thread(foo_a),
                    new Thread(foo_x),
                    new Thread(foo_x),
                    new Thread(foo_b),
                    new Thread(foo_y),
                    new Thread(foo_y),
                    new Thread(foo_c),
                    new Thread(foo_d),
                    new Thread(foo_a),
                    new Thread(foo_x),
                    new Thread(foo_x),
                    new Thread(foo_b),
                    new Thread(foo_y),
                    new Thread(foo_y),
                    new Thread(foo_c),
                    new Thread(foo_d)
                };

            for (Thread thread : threads)
                thread.start();

            for (Thread thread : threads)
                MiscUtility.threadJoin(thread);
        }
        System.out.println("------------------------------------------------");
        {
            //stateless runnables
            Runnable bar_a = Bar::a;
            Runnable bar_b = Bar::b;
            Runnable bar_c = Bar::c;
            Runnable bar_d = Bar::d;
            Runnable bar_x = Bar::x;
            Runnable bar_y = Bar::y;

            Thread[] threads =
                {
                    new Thread(bar_a),
                    new Thread(bar_x),
                    new Thread(bar_x),
                    new Thread(bar_b),
                    new Thread(bar_y),
                    new Thread(bar_y),
                    new Thread(bar_c),
                    new Thread(bar_d),
                    new Thread(bar_a),
                    new Thread(bar_x),
                    new Thread(bar_x),
                    new Thread(bar_b),
                    new Thread(bar_y),
                    new Thread(bar_y),
                    new Thread(bar_c),
                    new Thread(bar_d)
                };

            for (Thread thread : threads)
                thread.start();
        }
    }

    @Override
    protected void deinit()
    {
    }

    @Override
    protected void shutdown()
    {
    }

    @Override
    public void uncaughtException( Thread t, Throwable e )
    {
        MiscUtility.printStackTrace(t, e);
        forceExit(-1);
    }

}
