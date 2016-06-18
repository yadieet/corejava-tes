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

package threadInterrupt;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{

    private App()
    {
        super(App.class.getCanonicalName());
    }


    public static void main( String... args )
    {
        new App().start();
    }

    //for delay purpose only
    private static void delay( long millis )
    {
        MiscUtility.threadSleep(millis);
    }

    @Override
    protected void init()
    {
        Thread aFooThread = new Thread(new FooRunnable());
        aFooThread.setDaemon(false);
        aFooThread.start();
        delay(1L);
        aFooThread.interrupt();

        delay(100L);
        System.out.println("---------------------------------------------------------------");


        Thread aBarThread = new Thread(new BarRunnable());
        aBarThread.setDaemon(false);
        aBarThread.start();
        delay(10L);
        aBarThread.interrupt();
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
