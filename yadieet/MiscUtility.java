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

import java.io.PrintStream;
import java.util.Random;

/**
 * Created by yadieet on 31/05/16.
 */
public enum MiscUtility
{
    ;

    public final static Random random = new Random(System.nanoTime());

    MiscUtility()
    {
    }


    public static void printStackTrace( PrintStream printStream, Thread thread, Throwable throwable )
    {
        StringBuilder stringBuilder = new StringBuilder(1500);
        stringBuilder.append('[').append(thread.getName()).append(" thread] exception : ").append(throwable).append(System.lineSeparator());
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        for (StackTraceElement element : stackTraceElements)
            stringBuilder.append("    at ").append(element).append(System.lineSeparator());

        Throwable cause;
        while ((cause = throwable.getCause()) != null)
        {
            stringBuilder.append("Caused by: ").append(cause).append(System.lineSeparator());
            stackTraceElements = cause.getStackTrace();
            for (StackTraceElement element : stackTraceElements)
                stringBuilder.append("    at ").append(element).append(System.lineSeparator());

            throwable = cause;
        }

        printStream.println(stringBuilder);
    }

    public static void printStackTrace( Throwable throwable )
    {
        printStackTrace(System.err, Thread.currentThread(), throwable);
    }

    public static void printStackTrace( Thread thread, Throwable throwable )
    {
        printStackTrace(System.err, thread, throwable);
    }

    public static String getRandomNumber( int length )
    {
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = random.nextInt(10);
        stringBuilder.append(i == 0 ? 1 : i);
        for (int n = 1; n < length; n++)
            stringBuilder.append(random.nextInt(10));
        return stringBuilder.toString();
    }

}
