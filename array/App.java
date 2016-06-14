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

package array;

import yadieet.Application;
import yadieet.MiscUtility;

import java.io.IOError;
import java.io.IOException;
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

    private static void arrayTest1()
    {
        {
            //'final' for array variable only affect its length, or 'null array'
            final int[] ints = new int[5]; //final int[] ints = null;

            ints[0] = 3;
            ints[1] = 6;
            ints[2] = 4;
            ints[3] = 7;
            ints[4] = 8;

            for (int x : ints)
                System.out.print(x + " ");

            System.out.println();
            System.out.println();

            for (int i = 0; i < ints.length; i++)
                System.out.print("[" + i + "]:" + ints[i] + ' ');

            System.out.println();

            for (int i = -1; ++i < ints.length; )
                System.out.print("[" + i + "]:" + ints[i] + ' ');

            System.out.println();

            for (int i = ints.length - 1; i > -1; i--)
                System.out.print("[" + i + "]:" + ints[i] + ' ');

            System.out.println();

            for (int i = ints.length; i-- > 0; )
                System.out.print("[" + i + "]:" + ints[i] + ' ');

            System.out.println();
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            int[] ints = {3, 6, 4, 7, 8};

            ints[2] = 5;

            for (int i = -1; ++i < ints.length; )
                System.out.print("[" + i + "]:" + ints[i] + ' ');

            System.out.println();
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            String str1 = null;
            String str2 = null;
            String str3 = "agdjyenv";

            String[] strs = {str3, str1, "khjtub", str2, "uij"};
            for (String s : strs)
                System.out.println("- " + s);
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            Object[] objects = {null, null, "asdf", null, 1.0, true, MiscUtility.random};

            for (Object o : objects)
            {
                if (o == null)
                    continue;

                System.out.println("- " + o + " (" + o.getClass().getCanonicalName() + ')');
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            Throwable[] throwables = new Throwable[15];

            throwables[7] = new IllegalArgumentException("sfgdfg");
            throwables[2] = new IOException("dgdfgdfg");
            throwables[6] = new Error("lkjouihlk");
            throwables[5] = new NumberFormatException("ugiugkj");
            throwables[8] = new IOError(new IOException("hguilio"));
            throwables[12] = new Throwable("hosrgdfg");
            throwables[0] = new Exception("oiyugkj");

            for (Throwable t : throwables)
            {
                if (t != null)
                    System.out.println("- " + t);
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            Exception[] exceptions = new Exception[10];

            exceptions[3] = new IOException("poioiyoi");
            exceptions[7] = new NumberFormatException("lkhhh");
            exceptions[2] = new Exception("hgfyfuu");

            for (Exception e : exceptions)
            {
                if (e != null)
                    System.out.println("- " + e);
                else
                    System.out.println("- [NULL]");
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
    }

    private static void arrayTest2()
    {
        {
            int[][] intss = new int[2][3];

            intss[0][0] = 1;
            intss[0][1] = 2;
            intss[0][2] = 3;

            intss[1][0] = 4;
            intss[1][1] = 5;
            intss[1][2] = 6;

            for (int i = 0; i < 2; i++)
            {
                for (int ii = 0; ii < 3; ii++)
                    System.out.print(intss[i][ii] + " ");

                System.out.println();
            }

            System.out.println();

            intss[0][0] = 1;
            intss[0][1] = 3;
            intss[0][2] = 5;

            intss[1][0] = 2;
            intss[1][1] = 4;
            intss[1][2] = 6;

            for (int i = 0; i < 3; i++)
            {
                for (int ii = 0; ii < 2; ii++)
                    System.out.print(intss[ii][i] + " ");

                System.out.println();
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            int[][] intss = new int[2][];

            intss[0] = new int[] {1, 2, 3}; //intss[0] = {1, 2, 3};
            intss[1] = new int[] {4, 5, 6, 7, 8};

            for (int i = 0; i < intss.length; i++)
            {
                for (int ii = 0; ii < intss[i].length; ii++)
                    System.out.print(intss[i][ii] + " ");

                System.out.println();
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            int[][] intss = new int[2][];

            intss[0] = new int[3];
            intss[0][0] = 1;
            intss[0][1] = 2;
            intss[0][2] = 3;

            intss[1] = new int[5];
            intss[1][0] = 4;
            intss[1][1] = 5;
            intss[1][2] = 6;
            intss[1][3] = 7;
            intss[1][4] = 8;

            for (int i = 0; i < intss.length; i++)
            {
                for (int ii = 0; ii < intss[i].length; ii++)
                    System.out.print(intss[i][ii] + " ");

                System.out.println();
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            int[][] intss = new int[7][];

            intss[0] = new int[3];
            intss[0][0] = 1;
            intss[0][1] = 2;
            intss[0][2] = 3;

            intss[3] = new int[5];
            intss[3][0] = 4;
            intss[3][1] = 5;
            intss[3][2] = 6;
            intss[3][3] = 7;
            intss[3][4] = 8;

            for (int i = 0; i < intss.length; i++)
            {
                if (intss[i] == null)
                {
                    System.out.println("null");
                    continue;
                }

                for (int ii = 0; ii < intss[i].length; ii++)
                    System.out.print(intss[i][ii] + " ");

                System.out.println();
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            int[][] intss = new int[8][];

            int val = 0;
            for (int i = 0; i < intss.length; i++)
            {
                intss[i] = new int[i * 2 + 1];

                for (int ii = 0; ii < intss[i].length; ii++)
                    intss[i][ii] = ++val;
            }

            for (int i = 0; i < intss.length; i++)
            {
                for (int n = 0; n < intss.length - i; n++)
                    System.out.print("   ");

                for (int ii = 0; ii < intss[i].length; ii++)
                    System.out.print(String.format("%02d ", intss[i][ii]));

                System.out.println();
            }
        }
        System.out.println(System.lineSeparator() + "================================================" + System.lineSeparator());
        {
            Random random = MiscUtility.random;
            int randLength;

            int[][] intss = new int[10][];

            for (int i = 0; i < intss.length; i++)
            {
                randLength = random.nextInt(11);
                intss[i] = new int[randLength == 0 ? 1 : randLength];
            }

            for (int i = 0; i < intss.length; i++)
                System.out.println("intss[" + i + "].length:" + intss[i].length);

        }
    }

    @Override
    protected void init()
    {
        arrayTest1();

        arrayTest2();
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
