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

package terbilang;

import yadieet.Application;
import yadieet.MiscUtility;
import yadieet.Terbilang;

import java.math.BigInteger;

import static yadieet.MiscUtility.random;

/**
 * Created by yadieet on 04/06/16.
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

    private static String getRandomNumberString( int length )
    {
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = random.nextInt(10);
        stringBuilder.append(i == 0 ? 1 : i);
        for (int n = 1; n < length; n++)
            stringBuilder.append(random.nextInt(10));
        return stringBuilder.toString();
    }

    @Override
    protected void init()
    {
        int maxDigit = Terbilang.MAX_VALUE.toString().length();
        BigInteger bigInteger;
        for (int n = 0; n < 10; n++)
        {
            bigInteger = new BigInteger(getRandomNumberString(random.nextInt(maxDigit + 1)));
            System.out.println(bigInteger);
            System.out.println(Terbilang.getText(bigInteger, true, true));
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
