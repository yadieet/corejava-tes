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

package varargs;

import yadieet.Application;
import yadieet.MiscUtility;

/**
 * Created by yadieet on 31/05/14.
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

    private static void foo( int num, String... fruits ) //varargs parameter must be the last in the list
    {
        System.out.print(String.format("%2d", num) + ". foo : ");

        if (fruits == null)
        {
            System.out.println("Wrong coding, varargs is null.");
            return;
        }

        if (fruits.length == 0)
        {
            System.out.println("No fruit to eat.");
            return;
        }

        for (String fruit : fruits)
            System.out.print(fruit + ' ');
        System.out.println();

        System.out.println("          " + fruits.getClass().getCanonicalName());
    }

    private static void bar( int num, Qfruit... fruits ) //varargs parameter must be the last in the list
    {
        System.out.print(String.format("%2d", num) + ". bar : ");

        if (fruits == null)
        {
            System.out.println("Wrong coding, varargs is null.");
            return;
        }

        if (fruits.length == 0)
        {
            System.out.println("No fruit to eat.");
            return;
        }

        for (Qfruit fruit : fruits)
            System.out.print(fruit + " ");
        System.out.println();

        System.out.println("          " + fruits.getClass().getCanonicalName());
    }

    @Override
    protected void init()
    {
        {
            foo(1, null);

            String[] fruits = null;
            foo(2, fruits);
            fruits = new String[] {"ORANGE", "PAPAYA", "BANANA"};
            foo(3, fruits);

            foo(4);
            foo(5, "MANGO");
            foo(6, "BANANA", "MANGO", "APPLE");
        }

        System.out.println("-------------------------------------------------");

        {
            bar(7, null);

            Qfruit[] fruits = null;
            bar(8, fruits);
            fruits = new Qfruit[] {Qfruit.ORANGE, Qfruit.PAPAYA, Qfruit.BANANA};
            bar(9, fruits);

            bar(10);
            bar(11, Qfruit.MANGO);
            bar(12, Qfruit.BANANA, Qfruit.MANGO, Qfruit.APPLE);
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
