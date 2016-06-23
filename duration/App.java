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

package duration;

import yadieet.Application;
import yadieet.MiscUtility;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

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

    private static String getDurationString( Duration duration )
    {
        StringBuilder stringBuilder = new StringBuilder(40);

        long l = duration.toDays();
        duration = duration.minusDays(l);
        stringBuilder.append(l).append(" day ");

        l = duration.toHours();
        duration = duration.minusHours(l);
        stringBuilder.append(l).append(" hour ");

        l = duration.toMinutes();
        duration = duration.minusMinutes(l);
        stringBuilder.append(l).append(" min ");

        l = duration.getSeconds();
        stringBuilder.append(l).append(" sec");

        return stringBuilder.toString();
    }

    private static String getDurationString( long seconds )
    {
        return getDurationString(Duration.ofSeconds(Math.abs(seconds)));
    }

    private static void nextTest()
    {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("y/M/d");
        DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern("y/M/d HH:mm:ss");
        DateTimeFormatter zonedDatetimeFormatter = DateTimeFormatter.ofPattern("y/M/d HH:mm:ss z (VV)");

        System.out.println("---------------------------------------------");
        {
            LocalDate localDate1 = LocalDate.of(2015, 4, 22);
            LocalDate localDate2 = LocalDate.of(2016, 6, 23);

            //java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Seconds
            //Duration duration = Duration.between(localDate2, localDate1);

            Period period = localDate1.until(localDate2); //or via Period.between(localDate1, localDate2);
            period.normalized();

            printOut(dateFormatter.format(localDate1), dateFormatter.format(localDate2)
                , period.getYears() + " year " + period.getMonths() + " month " + period.getDays() + " day. (based on calendar)");

            long nDay = localDate1.until(localDate2, ChronoUnit.DAYS);
            printOut(dateFormatter.format(localDate1), dateFormatter.format(localDate2)
                , nDay + " days. (based on calendar)");

        }
        System.out.println("---------------------------------------------");
        {
            LocalDateTime localDatetime1 = LocalDateTime.of(2015, 4, 22, 15, 30, 10);
            LocalDateTime localDatetime2 = LocalDateTime.of(2016, 6, 23, 17, 32, 7);

            Duration duration = Duration.between(localDatetime1, localDatetime2);
            printOut(datetimeFormatter.format(localDatetime1), datetimeFormatter.format(localDatetime2)
                , getDurationString(duration));

            long nSecond = localDatetime1.until(localDatetime2, ChronoUnit.SECONDS);
            printOut(datetimeFormatter.format(localDatetime1), datetimeFormatter.format(localDatetime2)
                , getDurationString(nSecond));


            long nDay = localDatetime1.until(localDatetime2, ChronoUnit.DAYS);
            printOut(datetimeFormatter.format(localDatetime1), datetimeFormatter.format(localDatetime2)
                , nDay + " days.");

        }
        System.out.println("---------------------------------------------");
        {
            ZoneId jakartaZoneId = ZoneId.of("Asia/Jakarta");
            ZoneId tokyoZoneId = ZoneId.of("Asia/Tokyo");

            ZonedDateTime zonedDatetime1 = LocalDateTime.of(2015, 4, 22, 15, 30, 10).atZone(jakartaZoneId);
            ZonedDateTime zonedDatetime2 = LocalDateTime.of(2016, 6, 23, 17, 32, 7).atZone(tokyoZoneId);

            System.out.println("                                                    "
                + zonedDatetimeFormatter.format(zonedDatetime2.withZoneSameInstant(jakartaZoneId)));

            Duration duration = Duration.between(zonedDatetime1, zonedDatetime2);
            printOut(zonedDatetimeFormatter.format(zonedDatetime1), zonedDatetimeFormatter.format(zonedDatetime2)
                , getDurationString(duration));

            long nSecond = zonedDatetime1.until(zonedDatetime2, ChronoUnit.SECONDS);
            printOut(zonedDatetimeFormatter.format(zonedDatetime1), zonedDatetimeFormatter.format(zonedDatetime2)
                , getDurationString(nSecond));


            long nDay = zonedDatetime1.until(zonedDatetime2, ChronoUnit.DAYS);
            printOut(zonedDatetimeFormatter.format(zonedDatetime1), zonedDatetimeFormatter.format(zonedDatetime2)
                , nDay + " days.");

        }
    }

    private static void printOut( String a, String b, String c )
    {
        System.out.println("Between " + a + " until " + b + " : " + c);
    }

    @Override
    protected void init()
    {
        Random random = MiscUtility.random;

        int seconds;
        for (int i = 0; i++ < 5; )
        {
            seconds = random.nextInt(864001); //random-value max: 10 day
            System.out.println(seconds + " sec : " + getDurationString(seconds));
        }

        nextTest();
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
