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

package synchronizedBlock;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{
    private final Object anObject = new Object();

    private App()
    {
        super(App.class.getCanonicalName());
    }


    public static void main( String... args )
    {
        new App().start();
    }

    private void foo()
    {
        System.out.println(Thread.currentThread().getName() + " : before entering synchronized block.");
        synchronized( anObject )
        {
            System.out.println(Thread.currentThread().getName() + " : entered synchronized block.");

            for (int i = 0; i++ < 5; )
            {
                System.out.println(Thread.currentThread().getName() + " : ..");
                MiscUtility.threadSleep(1000L);
            }

            System.out.println(Thread.currentThread().getName() + " : before exitting synchronized block.");
        }
        System.out.println(Thread.currentThread().getName() + " : exitted synchronized block.");
    }

    @Override
    protected void init()
    {
        //a stateless runnable object
        Runnable runnable = this::foo;

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Thread(runnable);

        for (Thread thread : threads)
            thread.start();
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
