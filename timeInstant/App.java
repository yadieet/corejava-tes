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

package timeInstant;

import yadieet.Application;
import yadieet.MiscUtility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
        Instant anInstant = Instant.now();

        //ZoneId.getAvailableZoneIds().stream().filter(s->s.contains("Asia")).sorted().forEach(System.out::println);
        ZonedDateTime inUtcTime = anInstant.atZone(ZoneId.of("UTC"));
        ZonedDateTime inJakartaTime = anInstant.atZone(ZoneId.of("Asia/Jakarta"));
        ZonedDateTime inParisTime = anInstant.atZone(ZoneId.of("Europe/Paris"));
        ZonedDateTime inTokyoTime = anInstant.atZone(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime inNewYorkTime = anInstant.atZone(ZoneId.of("America/New_York"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM YYYY - HH:mm:ss z (VV)");

        System.out.println(anInstant.toEpochMilli() + " millisecond since Epoch, is :");
        System.out.println("~ " + inUtcTime.format(formatter));
        System.out.println("~ " + inTokyoTime.format(formatter));
        System.out.println("~ " + inJakartaTime.format(formatter));
        System.out.println("~ " + inParisTime.format(formatter));
        System.out.println("~ " + inNewYorkTime.format(formatter));
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
