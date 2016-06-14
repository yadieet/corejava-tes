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

package indonesiaLocaleAndTimezone;

import yadieet.Application;
import yadieet.MiscUtility;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

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
        Locale idLocale = new Locale("in", "ID");
        TimeZone idTimezone = TimeZone.getTimeZone(ZoneId.of("Asia/Jakarta"));
        //Locale.setDefault(idLocale);
        //TimeZone.setDefault(idTimezone);


        System.out.println("Locale     : " + idLocale);
        //System.out.println("- Language : " + idLocale.getDisplayLanguage());
        //System.out.println("- Country  : " + idLocale.getDisplayCountry());
        System.out.println("- Language : " + idLocale.getDisplayLanguage(idLocale));
        System.out.println("- Country  : " + idLocale.getDisplayCountry(idLocale));
        System.out.println("TimeZone   : " + idTimezone);
        System.out.println("- ID       : " + idTimezone.getID());
        //System.out.println("- Display  : " + idTimezone.getDisplayName());
        System.out.println("- Display  : " + idTimezone.getDisplayName(false, TimeZone.SHORT, idLocale));
        System.out.println("- Offset   : " + idTimezone.getRawOffset() + " ms");
        System.out.println();


        Locale.setDefault(idLocale);
        TimeZone.setDefault(idTimezone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM YYYY - HH:mm:ss z (VV)");
        System.out.println(ZonedDateTime.now().format(formatter));
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
