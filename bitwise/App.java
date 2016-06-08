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

package bitwise;

import yadieet.Application;
import yadieet.MiscUtility;

import java.math.BigInteger;

/**
 * Created by yadieet on 31/05/16.
 */
final class App extends Application
{
    private final static String binStr = " bin : ";
    private final static String unsignedStr = " (unsigned)";
    private final static String sp = ' ' + new String(Character.toChars(0x1f60a)) + ' ';
    private final static String topicSepStr = "===============================================================================";
    private final static String horLine = System.lineSeparator() + " - - - - - - - - - - - - - - - - - - -" + System.lineSeparator();
    private final static String aisStr = "  a : ";
    private final static String bisStr = "  b : ";
    private final static String aisStr2 = "a : ";
    private final static String bisStr2 = "b : ";
    private final static String orStr = ", or ";
    private final static String emptyStr = "";
    private final static String cisStr = "c : ";
    private final static int intNbits = 32;
    private final static int longNbits = 64;

    private App()
    {
        super(App.class.getCanonicalName());
    }


    public static void main( String... args )
    {
        new App().start();
    }

    private static String getBinaryStr( int i )
    {
        StringBuilder binaryStr = new StringBuilder(intNbits);
        binaryStr.append(Integer.toBinaryString(i));

        int length = binaryStr.length();
        if (length < intNbits)
        {
            for (int n = intNbits - length; n-- > 0; )
                binaryStr.insert(0, '0');
        }
        return binaryStr.toString();
    }

    private static String getBinaryStr( long l )
    {
        StringBuilder binaryStr = new StringBuilder(longNbits);
        binaryStr.append(Long.toBinaryString(l));

        int length = binaryStr.length();
        if (length < longNbits)
        {
            for (int n = longNbits - length; n-- > 0; )
                binaryStr.insert(0, '0');
        }
        return binaryStr.toString();
    }

    private static int getRandomInt()
    {
        byte[] bytes = new byte[4];
        MiscUtility.random.nextBytes(bytes);

        for (int i = 0; i < bytes.length - 1; i++)
        {
            if (MiscUtility.random.nextBoolean())
                bytes[i] = 0;
            else
                break;
        }

        return new BigInteger(bytes).intValue();
    }

    private static long getRandomLong()
    {
        byte[] bytes = new byte[8];
        MiscUtility.random.nextBytes(bytes);

        for (int i = 0; i < bytes.length - 1; i++)
        {
            if (MiscUtility.random.nextBoolean())
                bytes[i] = 0;
            else
                break;
        }

        return new BigInteger(bytes).longValue();
    }

    private static int rotateLeftShift( int i, int n )
    {
        return (i << n) | (i >>> (intNbits - n));
    }

    private static int rotateRightShift( int i, int n )
    {
        return (i >>> n) | (i << (intNbits - n));
    }

    private static long rotateLeftShift( long i, int n )
    {
        return (i << n) | (i >>> (longNbits - n));
    }

    private static long rotateRightShift( long i, int n )
    {
        return (i >>> n) | (i << (longNbits - n));
    }
    
    private static void testBitwiseShift()
    {
        System.out.println(topicSepStr);
        System.out.println("<< BITWISE SHIFT >> >>>" + System.lineSeparator());

        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            while (a != 0)
            {
                a <<= 1; // a = a << 1;
                System.out.print("  a <<= 1" + sp);
                System.out.print(binStr + getBinaryStr(a));
                System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            //while (a != 0 && a != -1)

            if (a < 0)
                while (a != -1)
                {
                    a >>= 1; // a = a >> 1;
                    System.out.print("  a >>= 1" + sp);
                    System.out.print(binStr + getBinaryStr(a));
                    System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
                }
            else
                while (a != 0)
                {
                    a >>= 1; // a = a >> 1;
                    System.out.print("  a >>= 1" + sp);
                    System.out.print(binStr + getBinaryStr(a));
                    System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
                }
        }
        System.out.println(horLine);
        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            while (a != 0)
            {
                a >>>= 1; // a = a >>> 1;
                System.out.print("  a >>>= 1" + sp);
                System.out.print(binStr + getBinaryStr(a));
                System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            long b = getRandomLong();
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            while (b != 0L)
            {
                b <<= 1L; // b = b << 1;
                System.out.print("  b <<= 1" + sp);
                System.out.print(binStr + getBinaryStr(b));
                System.out.println(sp + bisStr2 + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            long b = getRandomLong();
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            //while ( b != 0 && b != -1L)

            if (b < 0L)
                while (b != -1L)
                {
                    b >>= 1L; // b = b >> 1;
                    System.out.print("  b >>= 1" + sp);
                    System.out.print(binStr + getBinaryStr(b));
                    System.out.println(sp + bisStr2 + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
                }
            else
                while (b != 0L)
                {
                    b >>= 1L; // b = b >> 1;
                    System.out.print("  b >>= 1" + sp);
                    System.out.print(binStr + getBinaryStr(b));
                    System.out.println(sp + bisStr2 + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
                }
        }
        System.out.println(horLine);
        {
            long b = getRandomLong();
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            while (b != 0L)
            {
                b >>>= 1L; // b = b >>> 1;
                System.out.print("  b >>>= 1" + sp);
                System.out.print(binStr + getBinaryStr(b));
                System.out.println(sp + bisStr2 + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            }
        }
        System.out.println();
    }

    private static void testBitwiseComplement()
    {
        System.out.println(topicSepStr);
        System.out.println("~ BITWISE COMPLEMENT ~" + System.lineSeparator());

        {
            int a = 0;
            System.out.println(aisStr + a);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            a = ~a;
            System.out.println("  a = ~a");
            System.out.println(binStr + getBinaryStr(a));
            System.out.println(aisStr + a);
            System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
        }
        System.out.println(horLine);
        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            a = ~a;
            System.out.println("  a = ~a");
            System.out.println(binStr + getBinaryStr(a));
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
        }
        System.out.println(horLine);
        {
            int a = ~Integer.MAX_VALUE; //~2147483longNbits7
            System.out.println("  a = ~Integer.MAX_VALUE");
            System.out.println(aisStr + a);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println("(a == Integer.MIN_VALUE) : " + (a == Integer.MIN_VALUE));
            System.out.println();

            a = ~Integer.MIN_VALUE; //~-2147483longNbits8
            System.out.println("  a = ~Integer.MIN_VALUE");
            System.out.println(aisStr + a);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println("(a == Integer.MAX_VALUE) : " + (a == Integer.MAX_VALUE));
        }
        System.out.println(horLine);
        {
            long b = 0L;
            System.out.println(bisStr + b);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            b = ~b;
            System.out.println("  b = ~b");
            System.out.println(binStr + getBinaryStr(b));
            System.out.println(bisStr + b);
            System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
        }
        System.out.println(horLine);
        {
            long b = getRandomLong();
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            b = ~b;
            System.out.println("  b = ~b");
            System.out.println(binStr + getBinaryStr(b));
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
        }
        System.out.println(horLine);
        {
            long b = ~Long.MAX_VALUE; //~9223372036854775807
            System.out.println("  b = ~Long.MAX_VALUE");
            System.out.println(bisStr + b);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println("(b == Long.MIN_VALUE) : " + (b == Long.MIN_VALUE));
            System.out.println();

            b = ~Long.MIN_VALUE; //~-9223372036854775808
            System.out.println("  b = ~Long.MIN_VALUE");
            System.out.println(bisStr + b);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println("(b == Long.MAX_VALUE) : " + (b == Long.MAX_VALUE));
        }
        System.out.println();
    }

    private static void testBitwiseOr()
    {
        System.out.println(topicSepStr);
        System.out.println("| BITWISE OR |" + System.lineSeparator());

        for (int n = 0; n++ < 2; )
        {
            int a = getRandomInt();
            System.out.println(aisStr2 + getBinaryStr(a) + sp + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            int b = getRandomInt();
            System.out.println(bisStr2 + getBinaryStr(b) + sp + b + (b < 0 ? orStr + Integer.toUnsignedString(b) + unsignedStr : emptyStr));
            int c = a | b;
            System.out.println(cisStr + getBinaryStr(c) + sp + c + (c < 0 ? orStr + Integer.toUnsignedString(c) + unsignedStr : emptyStr));

            System.out.println(horLine);
        }

        for (int n = 0; n++ < 2; )
        {
            long a = getRandomLong();
            System.out.println(aisStr2 + getBinaryStr(a) + sp + a + (a < 0L ? orStr + Long.toUnsignedString(a) + unsignedStr : emptyStr));
            long b = getRandomLong();
            System.out.println(bisStr2 + getBinaryStr(b) + sp + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            long c = a | b;
            System.out.println(cisStr + getBinaryStr(c) + sp + c + (c < 0L ? orStr + Long.toUnsignedString(c) + unsignedStr : emptyStr));

            System.out.println(horLine);
        }
    }

    private static void testBitwiseXor()
    {
        System.out.println(topicSepStr);
        System.out.println("^ BITWISE XOR ^" + System.lineSeparator());

        for (int n = 0; n++ < 2; )
        {
            int a = getRandomInt();
            System.out.println(aisStr2 + getBinaryStr(a) + sp + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            int b = getRandomInt();
            System.out.println(bisStr2 + getBinaryStr(b) + sp + b + (b < 0 ? orStr + Integer.toUnsignedString(b) + unsignedStr : emptyStr));
            int c = a ^ b;
            System.out.println(cisStr + getBinaryStr(c) + sp + c + (c < 0 ? orStr + Integer.toUnsignedString(c) + unsignedStr : emptyStr));

            System.out.println(horLine);
        }

        for (int n = 0; n++ < 2; )
        {
            long a = getRandomLong();
            System.out.println(aisStr2 + getBinaryStr(a) + sp + a + (a < 0L ? orStr + Long.toUnsignedString(a) + unsignedStr : emptyStr));
            long b = getRandomLong();
            System.out.println(bisStr2 + getBinaryStr(b) + sp + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            long c = a ^ b;
            System.out.println(cisStr + getBinaryStr(c) + sp + c + (c < 0L ? orStr + Long.toUnsignedString(c) + unsignedStr : emptyStr));

            System.out.println(horLine);
        }
    }

    private static void testBitwiseAnd()
    {
        System.out.println(topicSepStr);
        System.out.println("& BITWISE AND &" + System.lineSeparator());

        for (int n = 0; n++ < 2; )
        {
            int a = getRandomInt();
            System.out.println(aisStr2 + getBinaryStr(a) + sp + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            int b = getRandomInt();
            System.out.println(bisStr2 + getBinaryStr(b) + sp + b + (b < 0 ? orStr + Integer.toUnsignedString(b) + unsignedStr : emptyStr));
            int c = a & b;
            System.out.println(cisStr + getBinaryStr(c) + sp + c + (c < 0 ? orStr + Integer.toUnsignedString(c) + unsignedStr : emptyStr));

            System.out.println(horLine);
        }

        for (int n = 0; n++ < 2; )
        {
            long a = getRandomLong();
            System.out.println(aisStr2 + getBinaryStr(a) + sp + a + (a < 0L ? orStr + Long.toUnsignedString(a) + unsignedStr : emptyStr));
            long b = getRandomLong();
            System.out.println(bisStr2 + getBinaryStr(b) + sp + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            long c = a & b;
            System.out.println(cisStr + getBinaryStr(c) + sp + c + (c < 0L ? orStr + Long.toUnsignedString(c) + unsignedStr : emptyStr));

            System.out.println(horLine);
        }
    }

    private static void testBitwiseRotateShift()
    {
        System.out.println(topicSepStr);
        System.out.println(sp + "ROTATE SHIFT" + sp + System.lineSeparator());

        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            for (int n = 1; n <= intNbits; n++)
            {
                a = rotateLeftShift(a, 1);
                System.out.print(getBinaryStr(a));
                System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            for (int n = 1; n <= intNbits; n++)
            {
                a = rotateRightShift(a, 1);
                System.out.print(getBinaryStr(a));
                System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            long b = getRandomLong();
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            for (int n = 1; n <= longNbits; n++)
            {
                b = rotateLeftShift(b, 1);
                System.out.print(getBinaryStr(b));
                System.out.println(sp + bisStr2 + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            long b = getRandomLong();
            System.out.println(bisStr + b);
            if (b < 0L)
                System.out.println(bisStr + Long.toUnsignedString(b) + unsignedStr);
            System.out.println(binStr + getBinaryStr(b));
            System.out.println();

            for (int n = 1; n <= longNbits; n++)
            {
                b = rotateRightShift(b, 1);
                System.out.print(getBinaryStr(b));
                System.out.println(sp + bisStr2 + b + (b < 0L ? orStr + Long.toUnsignedString(b) + unsignedStr : emptyStr));
            }
        }
    }

    private static void testMisc()
    {
        System.out.println(topicSepStr);
        System.out.println(sp + "M I S C" + sp + System.lineSeparator());

        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            while (a != 0)
            {
                a &= a - 1;
                System.out.print("  a &= a-1" + sp);
                System.out.print(binStr + getBinaryStr(a));
                System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            }
        }
        System.out.println(horLine);
        {
            int a = getRandomInt();
            System.out.println(aisStr + a);
            if (a < 0)
                System.out.println(aisStr + Integer.toUnsignedString(a) + unsignedStr);
            System.out.println(binStr + getBinaryStr(a));
            System.out.println();

            while (a != -1)
            {
                a |= a + 1;
                System.out.print("  a |= a+1" + sp);
                System.out.print(binStr + getBinaryStr(a));
                System.out.println(sp + aisStr2 + a + (a < 0 ? orStr + Integer.toUnsignedString(a) + unsignedStr : emptyStr));
            }
        }

        System.out.println();
    }

    @Override
    protected void init()
    {
        testBitwiseComplement();

        testBitwiseOr();

        testBitwiseXor();

        testBitwiseAnd();

        testBitwiseShift();

        testBitwiseRotateShift();

        testMisc();

        System.out.println(topicSepStr);
        System.out.println("For more about bitwise, bit hacks, bit manipulation, bit twiddling/bashing;");
        System.out.println("you can read books :");
        System.out.println("- Addison Wesley : Hacker's Delight (First &/ Second Edition)");
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
