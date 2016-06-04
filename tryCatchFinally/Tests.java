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

package tryCatchFinally;

import java.io.IOException;

/**
 * Created by yadieet on 02/06/16.
 */
enum Tests
{
    ;

    static void test0()
    {
        System.out.println("Tests.test0..");

        try
        {
            System.out.println("Tests.test0 : try..");
        }
        finally
        {
            System.out.println("Tests.test0 : finally..");
        }

        System.out.println("Tests.test0 : OK.");
    }

    static void test1()
    {
        System.out.println("Tests.test1..");
        try
        {
            System.out.println("Tests.test1 : try..");
            do
            {
                break;
            }
            while (true);
        }
        finally
        {
            System.out.println("Tests.test1 : finally..");
        }

        System.out.println("Tests.test1 : OK.");
    }

    static void test2()
    {
        System.out.println("Tests.test2..");
        try
        {
            System.out.println("Tests.test2 : try..");
            Foo.doSomething1();
        }
        finally
        {
            System.out.println("Tests.test2 : finally..");
        }

        System.out.println("Tests.test2 : OK.");
    }

    static void test3() throws IOException
    {
        System.out.println("Tests.test3..");
        try
        {
            System.out.println("Tests.test3 : try..");
            Foo.doSomething2();
        }
        finally
        {
            System.out.println("Tests.test3 : finally..");
        }

        System.out.println("Tests.test3 : OK.");
    }

    static boolean test4()
    {
        System.out.println("Tests.test4..");
        try
        {
            System.out.println("Tests.test4 : try..");
            Foo.doSomething2();
        }
        catch( IOException e )
        {
            System.out.println("Tests.test4 : caught " + e);
            return false;
        }
        finally
        {
            System.out.println("Tests.test4 : finally..");
        }

        System.out.println("Tests.test4 : OK.");
        return true;
    }

    static boolean test5()
    {
        System.out.println("Tests.test5..");
        try
        {
            System.out.println("Tests.test5 : try..");
            Foo.doSomething2();
            System.out.println("Tests.test5 : OK.");
            return true;
        }
        catch( IOException e )
        {
            System.out.println("Tests.test5 : caught " + e);
            return false;
        }
        finally
        {
            System.out.println("Tests.test5 : finally..");
        }

        //Nothing to do here
    }

    static void test6() throws AlohaException
    {
        System.out.println("Tests.test6..");
        try
        {
            System.out.println("Tests.test6 : try..");
            Foo.doSomething2();
        }
        catch( IOException e )
        {
            System.out.println("Tests.test6 : caught " + e);
            throw new AlohaException(e);
        }
        finally
        {
            System.out.println("Tests.test6 : finally..");
        }

        System.out.println("Tests.test6 : OK.");
    }

    static boolean test7()
    {
        System.out.println("Tests.test7..");
        try
        {
            System.out.println("Tests.test7 : try..");
            Foo.doSomething3();
        }
        catch( IOException | AlohaException e )
        {
            System.out.println("Tests.test7 : caught " + e);
            return false;
        }
        finally
        {
            System.out.println("Tests.test7 : finally..");
        }

        System.out.println("Tests.test7 : OK.");
        return true;
    }

    static int test8()
    {
        System.out.println("Tests.test8..");
        try
        {
            System.out.println("Tests.test8 : try..");
            Foo.doSomething3();
        }
        catch( IOException ioe )
        {
            System.out.println("Tests.test8 : caught " + ioe);
            return -1;
        }
        catch( AlohaException be )
        {
            System.out.println("Tests.test8 : caught " + be);
            return -2;
        }
        finally
        {
            System.out.println("Tests.test8 : finally..");
        }

        System.out.println("Tests.test8 : OK.");
        return 0;
    }

    static int test9()
    {
        System.out.println("Tests.test9..");
        try
        {
            System.out.println("Tests.test9 : try..");
            Foo.doSomething4();
        }
        catch( OlalaException oe )  // OlalaException is a subclass of IOException,
        // so the catch block must be placed before IOException
        {
            System.out.println("Tests.test9 : caught " + oe);
            return -3;
        }
        catch( IOException be )
        {
            System.out.println("Tests.test9 : caught " + be);
            return -1;
        }
        finally
        {
            System.out.println("Tests.test9 : finally..");
        }

        System.out.println("Tests.test9 : OK.");
        return 0;
    }

    static int test10()
    {
        System.out.println("Tests.test10..");
        try
        {
            System.out.println("Tests.test10: try..");
            Foo.doSomething4();
        }
        catch( IOException ioe )
        {
            System.out.println("Tests.test10: caught " + ioe);
            if (ioe instanceof OlalaException)
                return -3;
            //else
            return -1;
        }
        finally
        {
            System.out.println("Tests.test10: finally..");
        }

        System.out.println("Tests.test10: OK.");
        return 0;
    }

    static void test11()
    {
        System.out.println("Tests.test11..");
        try
        {
            System.out.println("Tests.test11: try..");
            if (System.nanoTime() % 2L == 0L)
                return;
        }
        finally
        {
            System.out.println("Tests.test11: finally..");
        }

        System.out.println("Tests.test11: OK.");
    }

}
