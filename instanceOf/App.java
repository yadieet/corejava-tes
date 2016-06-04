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

package instanceOf;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 03/06/16.
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
        Object o = new A();

        System.out.println("o instanceof Object == " + (o instanceof Object));
        System.out.println("o instanceof A == " + (o instanceof A));
        System.out.println("o instanceof B == " + (o instanceof B));
        System.out.println("o instanceof C == " + (o instanceof C));
        System.out.println("o instanceof D == " + (o instanceof D));
        System.out.println("o instanceof E == " + (o instanceof E));
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
