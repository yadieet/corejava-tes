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

import java.math.BigInteger;

/**
 * Number-to-words or number-to-text converter utility for Bahasa Indonesia or Indonesian Rupiah (IDR) currency.
 * Created by Yadieet SA on 2013.
 */

//@ThreadSafe
public final class Terbilang
{
    public static final BigInteger MAX_VALUE = new BigInteger("999999999999999999999999999999999999");

    private static final BigInteger[] VALUE_LEVEL = {
        BigInteger.valueOf(20L),
        BigInteger.valueOf(10L),
        BigInteger.valueOf(100L),
        BigInteger.valueOf(1000L),
        BigInteger.valueOf(1000000L),
        BigInteger.valueOf(1000000000L),
        BigInteger.valueOf(1000000000000L),
        BigInteger.valueOf(1000000000000000L),
        BigInteger.valueOf(1000000000000000000L),
           new BigInteger("1000000000000000000000"),
           new BigInteger("1000000000000000000000000"),
           new BigInteger("1000000000000000000000000000"),
           new BigInteger("1000000000000000000000000000000"),
           new BigInteger("1000000000000000000000000000000000"),
        //... (dst)
    };

    private static final String[] VALUE_LEVEL_STRING = {
        null,
        "puluh ",
        "ratus ",
        "ribu, ",
        "juta, ",
        "milyar, ",
        "triliun, ",
        "quadriliun, ",
        "quintiliun, ",
        "sektiliun, ",
        "septiliun, ",
        "oktiliun, ",
        "noniliun, ",
        "desiliun, ",
        //... (dst)
    };

    private static final String[] UNDER_TWENTY_STRING = {
        null,
        "satu ",
        "dua ",
        "tiga ",
        "empat ",
        "lima ",
        "enam ",
        "tujuh ",
        "delapan ",
        "sembilan ",
        "sepuluh ",
        "sebelas ",
        "dua belas ",
        "tiga belas ",
        "empat belas ",
        "lima belas ",
        "enam belas ",
        "tujuh belas ",
        "delapan belas ",
        "sembilan belas "
    };

    private final StringBuilder result = new StringBuilder(600);

    private Terbilang()
    {
    }

    public static String getText( BigInteger value, boolean rupiah )//, boolean period )
    {
        return new Terbilang().toText(value, rupiah);//, period);
    }

    public static String getText( BigInteger value )
    {
        return getText(value, false);//, false);
    }

    public static String getText( long value, boolean rupiah )//, boolean period )
    {
        return getText(BigInteger.valueOf(value), rupiah);//, period);
    }

    public static String getText( long value )
    {
        return getText(value, false);//, false);
    }

    public static String getText( int value, boolean rupiah )//, boolean period )
    {
        return getText(BigInteger.valueOf(value), rupiah);//, period);
    }

    public static String getText( int value )
    {
        return getText(value, false);//, false);
    }

    private String toText( BigInteger value, boolean rupiah )//, boolean period )
    {
        if (value == null)
            throw new NullPointerException("value");
        if (value.signum() == -1)
            throw new IllegalArgumentException("negative value");
        if (value.compareTo(MAX_VALUE) == 1)
            throw new IllegalArgumentException("value > max value");

        if (value.compareTo(BigInteger.ZERO) == 0)
            result.append("nol ");
        else
            letsGo(value);

        if (result.charAt(result.length() - 2) == ',')
            result.deleteCharAt(result.length() - 2);

        if (rupiah)
            result.append("rupiah");
        else
            result.deleteCharAt(result.length() - 1);

        //if (period)
        result.append('.');

        result.setCharAt(0, Character.toUpperCase(result.charAt(0)));

        return result.toString();
    }

    private void letsGo( BigInteger value )
    {
        if (value.compareTo(VALUE_LEVEL[0]) == -1)
        {
            result.append(UNDER_TWENTY_STRING[value.intValue()]);
            return;
        }

        /////////////////////////////////////////////////

        int i = VALUE_LEVEL.length;
        while (value.compareTo(VALUE_LEVEL[--i]) < 0)
        {
            ;
        }

        BigInteger[] temp = value.divideAndRemainder(VALUE_LEVEL[i]);

        /////////////////////////////////////////////////

        if (temp[0].compareTo(VALUE_LEVEL[0]) == -1)
        {
            int x = temp[0].intValue();
            if (x == 1 && (i == 2 || i == 3)) //No comment!
                result.append("se");
            else
                result.append(UNDER_TWENTY_STRING[x]);
        }
        else
            letsGo(temp[0]);

        /////////////////////////////////////////////////

        result.append(VALUE_LEVEL_STRING[i]);

        /////////////////////////////////////////////////

        if (temp[1].compareTo(BigInteger.ZERO) == 0)
            return;

        letsGo(temp[1]);
    }

}
