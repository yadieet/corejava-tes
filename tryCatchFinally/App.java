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

package tryCatchFinally;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 02/06/16.
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

    @Override
    protected void init()
    {
        try
        {
            Tests.test0();
        }
        catch( Throwable e )    // catch any exception, error. (Note1)
        {
            System.out.println("App.init    : caught " + e);
        }

        // Note1 :
        // Generally,overly broad catch is not recommended;
        // The reason is to make difference between 'known errors/exceptions' and 'BUGS'.
        // (Even the checked VS unchecked exception already become a debate topic in the java community.)
        // But, for this Tests purpose we needed it (overly broad catch), so that the thrown exception doesn't go to
        // UncaughtExceptionHandler, so that the application doesn't exit and still continue.

        System.out.println("===============================================================================");

        try
        {
            Tests.test1();
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            Tests.test2();
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            Tests.test3();
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            System.out.println("App.init    : retVal == " + Tests.test4());
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            System.out.println("App.init    : retVal == " + Tests.test5());
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            Tests.test6();
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            System.out.println("App.init    : retVal == " + Tests.test7());
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            System.out.println("App.init    : retVal == " + Tests.test8());
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            System.out.println("App.init    : retVal == " + Tests.test9());
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            System.out.println("App.init    : retVal == " + Tests.test10());
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

        System.out.println("===============================================================================");

        try
        {
            Tests.test11();
        }
        catch( Throwable e )
        {
            System.out.println("App.init    : caught " + e);
        }

    }

    @Override
    protected void deinit()
    {
    }

    @Override
    protected void shutdown()
    {
        System.out.println();
        System.out.println("Application will now exit.");
    }

    @Override
    public void uncaughtException( Thread t, Throwable e )
    {
        System.out.println("App.uncaughtException : " + e);
        MiscUtility.printStackTrace(t, e);
        forceExit(-1);
    }

}
