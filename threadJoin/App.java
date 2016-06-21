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

package threadJoin;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{
    private int i = 1;

    private App()
    {
        super(App.class.getCanonicalName());
    }


    public static void main( String... args )
    {
        new App().start();
    }

    private void blah()
    {
        for (int a = 0; a++ < 5; i++)
        {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            MiscUtility.threadSleep(1000L);
        }
    }

    @Override
    protected void init()
    {
        Thread thread1 = new Thread(this::blah);

        Thread thread2 = new Thread(() ->
        {
            thread1.start();
            MiscUtility.threadJoin(thread1);
            blah();
        });

        thread2.start();
        MiscUtility.threadJoin(thread2);
        blah();
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
