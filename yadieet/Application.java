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

package yadieet;

/**
 * Created by yadieet on 31/05/16.
 */
public abstract class Application extends ThreadGroup
{
    private boolean initCancelled;
    private boolean initialized;
    private boolean deinitialized;


    protected Application( String name )
    {
        super(name);
    }


    protected static void forceExit( int statusCode )
    {
        System.exit(statusCode);
    }

    protected void cancelInit()
    {
        initCancelled = true;
    }

    protected boolean isInitialized()
    {
        return initialized;
    }

    protected final void start()
    {
        if (initCancelled)
            return;

        Runtime runtime = Runtime.getRuntime();
        Thread shutdownThread = new Thread(this, () ->
        {
            if (!deinitialized)
            {
                try
                {
                    exit();
                }
                catch( Throwable ignored )
                {
                }
            }

            try
            {
                shutdown();
            }
            catch( Throwable ignored )
            {
            }

            runtime.runFinalization();
        });
        shutdownThread.setDaemon(false);
        runtime.addShutdownHook(shutdownThread);

        Thread initializerThread = new Thread(this, () ->
        {
            init();
            initialized = true;
        });
        initializerThread.setDaemon(false);
        initializerThread.start();
    }

    protected final void exit()
    {
        deinit();
        deinitialized = true;
    }

    protected abstract void init();

    protected abstract void deinit();

    protected abstract void shutdown();

    @Override
    public abstract void uncaughtException( Thread t, Throwable e );

}
