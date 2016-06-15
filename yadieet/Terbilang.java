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

    private static final BigInteger[] valuelevel = {
        new BigInteger("20"),
        new BigInteger("10"),
        new BigInteger("100"),
        new BigInteger("1000"),
        new BigInteger("1000000"),
        new BigInteger("1000000000"),
        new BigInteger("1000000000000"),
        new BigInteger("1000000000000000"),
        new BigInteger("1000000000000000000"),
        new BigInteger("1000000000000000000000"),
        new BigInteger("1000000000000000000000000"),
        new BigInteger("1000000000000000000000000000"),
        new BigInteger("1000000000000000000000000000000"),
        new BigInteger("1000000000000000000000000000000000"),
        //... (dst)
    };

    private static final String[] textuallevel = {
        null,
        "puluh ",
        "ratus ",
        "ribu, ",
        "juta, ",
        "milyar, ",
        "triliun, ",
        "quadtriliun, ",
        "quintiliun, ",
        "sektiliun, ",
        "septiliun, ",
        "oktiliun, ",
        "noniliun, ",
        "desiliun, ",
        //... (dst)
    };

    private static final String[] textualnumber = {
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

    public static String getText( BigInteger value, boolean rupiah, boolean period )
    {
        return new Terbilang().toText(value, rupiah, period);
    }

    public static String getText( BigInteger value )
    {
        return getText(value, false, false);
    }

    public static String getText( long value, boolean rupiah, boolean period )
    {
        return getText(BigInteger.valueOf(value), rupiah, period);
    }

    public static String getText( long value )
    {
        return getText(value, false, false);
    }

    public static String getText( int value, boolean rupiah, boolean period )
    {
        return getText(BigInteger.valueOf(value), rupiah, period);
    }

    public static String getText( int value )
    {
        return getText(value, false, false);
    }

    private String toText( BigInteger value, boolean rupiah, boolean period )
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

        if (rupiah)
            result.append("rupiah");
        else
            result.deleteCharAt(result.length() - 1);

        if (period)
            result.append('.');

        result.setCharAt(0, Character.toUpperCase(result.charAt(0)));

        return result.toString();
    }

    private void letsGo( BigInteger value )
    {
        if (value.compareTo(valuelevel[0]) == -1)
        {
            result.append(textualnumber[value.intValue()]);
            return;
        }

        /////////////////////////////////////////////////

        int i = valuelevel.length;
        while (value.compareTo(valuelevel[--i]) < 0);

        BigInteger[] temp = value.divideAndRemainder(valuelevel[i]);

        /////////////////////////////////////////////////

        if (temp[0].compareTo(valuelevel[0]) == -1)
        {
            int x = temp[0].intValue();
            if (x == 1 && (i == 2 || i == 3)) //No comment!
                result.append("se");
            else
                result.append(textualnumber[x]);
        }
        else
            letsGo(temp[0]);

        /////////////////////////////////////////////////

        result.append(textuallevel[i]);

        /////////////////////////////////////////////////

        if (temp[1].compareTo(BigInteger.ZERO) == 0)
            return;

        letsGo(temp[1]);
    }

}
