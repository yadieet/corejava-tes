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

package integralTypesAssignment;

import yadieet.Application;
import yadieet.MiscUtility;

import java.math.BigInteger;

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

    @Override
    protected void init()
    {
        {
            byte a = (byte) 0b11111111; //assignment using binary literal
            byte b = (byte) 0377; //assignment using octal literal
            byte c = -1; //assignment using decimal literal
            byte d = (byte) 255; //assignment using decimal literal (unsigned 8bit)
            byte e = (byte) 0xff; //assignment using hexadecimal literal

            System.out.println(a == b);
            System.out.println(a == c);
            System.out.println(a == d);
            System.out.println(a == e);
        }
        System.out.println("--------------------------------------------");
        {
            short a = (short) 0b11111111_11111111; //assignment using binary literal
            short b = (short) 0177777; //assignment using octal literal
            short c = -1; //assignment using decimal literal
            short d = (short) 65535; //assignment using decimal literal (unsigned 16bit)
            short e = (short) 0xff_ff; //assignment using hexadecimal literal

            System.out.println(a == b);
            System.out.println(a == c);
            System.out.println(a == d);
            System.out.println(a == e);
        }
        System.out.println("--------------------------------------------");
        {
            int a = 0b11111111_11111111_11111111_11111111; //assignment using binary literal
            int b = 037777777777; //assignment using octal literal
            int c = -1; //assignment using decimal literal
            int d = (int) 4294967295L; //assignment using decimal literal (unsigned 32bit)
            int e = 0xff_ff_ff_ff; //assignment using hexadecimal literal

            System.out.println(a == b);
            System.out.println(a == c);
            System.out.println(a == d);
            System.out.println(a == e);
        }
        System.out.println("--------------------------------------------");
        {
            long a = 0b11111111_11111111_11111111_11111111_11111111_11111111_11111111_11111111L; //assignment using binary literal
            long b = 01777777777777777777777L; //assignment using octal literal
            long c = -1L; //assignment using decimal literal

            //compile error: integer number too large.
            //long d = 18446744073709551615L; //assignment using decimal literal (unsigned 64bit)

            long d1 = Long.parseUnsignedLong("18446744073709551615");
            long d2 = new BigInteger("18446744073709551615", 10).longValue();
            long e = 0xff_ff_ff_ff_ff_ff_ff_ffL; //assignment using hexadecimal literal


            System.out.println(a == b);
            System.out.println(a == c);
            System.out.println(a == d1);
            System.out.println(a == d2);
            System.out.println(a == e);
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
