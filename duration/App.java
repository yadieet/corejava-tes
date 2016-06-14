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

import java.time.Duration;
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

    private static String getDurationString( long seconds )
    {
        seconds = Math.abs(seconds);

        Duration duration = Duration.ofSeconds(seconds);
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
