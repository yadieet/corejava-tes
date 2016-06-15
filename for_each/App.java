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

package forEach;

import yadieet.Application;
import yadieet.MiscUtility;

import java.util.*;

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

    private static void test( String... strs )
    {
        //for-each on varargs

        for (String str : strs)
            System.out.print(str + " ");
        System.out.println();

        System.out.println("------------------------------------------------");

        //for-each on array

        String[] strs2 = strs.clone();
        for (String str : strs2)
            System.out.print(str + " ");
        System.out.println();

        System.out.println("------------------------------------------------");

        //for-each on some java-collections objects

        ArrayList<String> strArraylist = new ArrayList<>();
        HashSet<String> strHashset = new HashSet<>();
        ArrayDeque<String> strArraydeque = new ArrayDeque<>();
        HashMap<String, Object> strHashmap = new HashMap<>();

        for (int i = 0; i < strs.length; i++)
        {
            strHashmap.put(strs[i].substring(0, 2), strs[i]);
            strArraylist.add(strs[i]);
            strHashset.add(strs[i]);
            strArraydeque.add(strs[i]);
        }

        System.out.println(strArraylist instanceof Iterable);
        for (String str : strArraylist)
            System.out.print(str + " ");

        System.out.println(System.lineSeparator());

        System.out.println(strHashset instanceof Iterable);
        for (String str : strHashset)
            System.out.print(str + " ");

        System.out.println(System.lineSeparator());

        System.out.println(strArraydeque instanceof Iterable);
        for (String str : strArraydeque)
            System.out.print(str + " ");

        System.out.println(System.lineSeparator());

        Set<String> strHashmapKeyset = strHashmap.keySet();
        System.out.println(strHashmapKeyset instanceof Iterable);
        for (String key : strHashmapKeyset)
            System.out.print(strHashmap.get(key) + " ");

        System.out.println();

        System.out.println("------------------------------------------------");

        //for-each on an object of an "iterable-class"

        Foo<String> foo = new Foo<>(strs);

        System.out.println(foo instanceof Iterable);
        for (String str : foo)
            System.out.print(str + " ");
        System.out.println();
    }

    @Override
    protected void init()
    {
        test("orange", "papaya", "banana", "mango", "apple", "melon", "guava", "grape");
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
