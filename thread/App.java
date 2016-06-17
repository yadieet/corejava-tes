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

package thread;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{
    private final HelloWorldRunnable hwRunnable1;
    private boolean stopNow = false;

    private App()
    {
        super(App.class.getCanonicalName());

        hwRunnable1 = new HelloWorldRunnable(() -> stopNow);
    }


    public static void main( String... args )
    {
        new App().start();
    }

    @Override
    protected void init()
    {
        Thread hfThread = new Thread(() -> System.out.println(Thread.currentThread() + " : Hello Friends !"));
        hfThread.setDaemon(true);
        hfThread.start();

        Thread hwThread = new Thread(hwRunnable1);
        hwThread.setDaemon(false);
        hwThread.start();

        MiscUtility.threadSleep(2000L);
        stopNow = true;
    }

    @Override
    protected void deinit()
    {
        stopNow = true;
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
