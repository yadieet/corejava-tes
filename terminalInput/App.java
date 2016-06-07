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

package terminalInput;

import yadieet.Application;
import yadieet.MiscUtility;

import javax.swing.*;
import java.awt.*;
import java.io.Console;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static terminalInput.ConsoleDataInputUtility.*;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{
    private final Console console;

    private App()
    {
        super(App.class.getCanonicalName());
        console = System.console();

        if (console == null)
        {
            if (!GraphicsEnvironment.isHeadless())
                JOptionPane.showMessageDialog(null,
                    "Please run this application from terminal or terminal emulator !"
                        + System.lineSeparator()
                        + " Or don't pipe stdin/stdout from/to another inputstream/pipe/program/file."
                    , getName(), JOptionPane.WARNING_MESSAGE);
            
            cancelInit();
            return;
        }

        console.flush();
    }

    
    public static void main( String... args )
    {
        new App().start();
    }
    
    @Override
    protected void init()
    {
        //No need to use 'try-with-resources' !!
        PrintWriter writer = console.writer();

        {
            writer.println("Please input an [ALPHABETIC CHAR] !");
            char value = getAlphabeticCharacter(console);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [Y/n] ?", true, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input a digit [0-9] !");
            int value = getDigit(console);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input an [INTEGER][MIN:" + Integer.MIN_VALUE + ",MAX:" + Integer.MAX_VALUE + "] ! ");
            int value = getInteger(console, null);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/n] ?", 'y', 'n'))
            return;

        writer.println();
        {
            int minValue = -1000;
            int maxValue = 1000;
            writer.println("Please input an [INTEGER][MIN:" + minValue + ",MAX:" + maxValue + "] ! ");
            int value = getInteger(console, minValue, maxValue);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input a [LONG][MIN:" + Long.MIN_VALUE + ",MAX:" + Long.MAX_VALUE + "] ! ");
            long value = getLong(console, null);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input a [FLOATINGPOINT] !");
            BigDecimal value = getFloatingPoint(console, null);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            BigDecimal minValue = new BigDecimal("-1000.0");
            BigDecimal maxValue = new BigDecimal("1000.0");
            writer.println("Please input a [FLOATINGPOINT][MIN:" + minValue + ",MAX:" + maxValue + "] !");
            BigDecimal value = getFloatingPoint(console, minValue, maxValue);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input a [BIGINTEGER] !");
            BigInteger value = getBigInteger(console, null);
            writer.println("Value : " + value);
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input any [STRING] !");
            writer.println("Value : " + getString(console, null));
        }
        writer.println();

        if (!getBooleanAnswer(console, "Do you want to continue [y/N] ?", false, 'y', 'n'))
            return;

        writer.println();
        {
            writer.println("Please input a [DATE:YYYY/MM/DD] !");
            LocalDate localDate = getDate(console, null);
            DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy");
            writer.println("Value : " + datetimeFormatter.format(localDate));
        }
        writer.println();
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
