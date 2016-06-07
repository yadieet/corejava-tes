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

package terminalInput;

import java.io.Console;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * Created by yadieet on 31/05/16.
 */
enum ConsoleDataInputUtility
{
    ;

    private static final Pattern datePattern = Pattern.compile("^\\d{1,4}/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])$");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("y/M/d");

    static char getCharacter( Console console, Function<Character, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String answer;
        char c;

        do
        {
            answer = readLine(console);
        }
        while (answer.length() != 1 || !validator.apply(c = answer.charAt(0)));

        return c;
    }

    static char getAlphabeticCharacter( Console console )
    {
        return getCharacter(console, Character::isAlphabetic);
    }

    static boolean getBoolean( Console console, boolean defaultTrue, char trueChar, char falseChar )
    {
        String answer = readLine(console).toLowerCase();

        if (defaultTrue)
            return !answer.equals(String.valueOf(falseChar));

        return answer.equals(String.valueOf(trueChar));
    }

    static boolean getBoolean( Console console, char trueChar, char falseChar )
    {
        String answer;
        char c;

        do
        {
            answer = readLine(console);
        }
        while (answer.length() != 1 || (c = Character.toLowerCase(answer.charAt(0))) != trueChar && c != falseChar);

        return c == trueChar;
    }

    static int getDigit( Console console )
    {
        return Character.digit(getCharacter(console, Character::isDigit), 10);
    }

    static int getInteger( Console console, Function<Integer, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String answer;
        int value;

        do
        {
            answer = readLine(console);
            try
            {
                value = Integer.parseInt(answer);
                if (validator.apply(value))
                    break;
            }
            catch( NumberFormatException ignored )
            {
            }
        }
        while (true);

        return value;
    }

    static int getInteger( Console console, int minValue, int maxValue )
    {
        if (minValue - maxValue > 0)
            throw new IllegalArgumentException("Invalid min-max value.");

        return getInteger(console, value -> value >= minValue && value <= maxValue);
    }

    static long getLong( Console console, Function<Long, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String answer;
        long value;

        do
        {
            answer = readLine(console);
            try
            {
                value = Long.parseLong(answer);
                if (validator.apply(value))
                    break;
            }
            catch( NumberFormatException ignored )
            {
            }
        }
        while (true);

        return value;
    }

    static long getLong( Console console, long minValue, long maxValue )
    {
        if (minValue - maxValue > 0L)
            throw new IllegalArgumentException("Invalid min-max value.");

        return getLong(console, value -> value >= minValue && value <= maxValue);
    }

    static BigDecimal getFloatingPoint( Console console, Function<BigDecimal, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String answer;
        BigDecimal value;

        do
        {
            answer = readLine(console);
            try
            {
                value = stripAndOrRescale(new BigDecimal(answer));
                if (validator.apply(value))
                    break;
            }
            catch( NumberFormatException ignored )
            {
            }
        }
        while (true);

        return value;
    }

    static BigDecimal getFloatingPoint( Console console, BigDecimal minValue, BigDecimal maxValue )
    {
        if (minValue.compareTo(maxValue) > 0)
            throw new IllegalArgumentException("Invalid min-max value.");

        return getFloatingPoint(console, value -> value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0);
    }

    private static BigDecimal stripAndOrRescale( BigDecimal value )
    {
        value = value.stripTrailingZeros();
        try
        {
            value.toBigIntegerExact();
            value = value.setScale(1, RoundingMode.UNNECESSARY);
        }
        catch( ArithmeticException ignored )
        {
        }
        return value;
    }

    static BigInteger getBigInteger( Console console, Function<BigInteger, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String answer;
        BigInteger value;

        do
        {
            answer = readLine(console);
            try
            {
                value = new BigInteger(answer);
                if (validator.apply(value))
                    break;
            }
            catch( NumberFormatException ignored )
            {
            }
        }
        while (true);

        return value;
    }

    static BigInteger getBigInteger( Console console, BigInteger minValue, BigInteger maxValue )
    {
        if (minValue.compareTo(maxValue) > 0)
            throw new IllegalArgumentException("Invalid min-max value.");

        return getBigInteger(console, value -> value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0);
    }

    static String getString( Console console, Function<String, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String value;

        do
        {
            value = readLine(console);
        }
        while (!validator.apply(value));

        return value;
    }

    static LocalDate getDate( Console console, Function<LocalDate, Boolean> validator )
    {
        if (validator == null)
            validator = value -> true;

        String dateStr;
        LocalDate localDate;

        do
        {
            dateStr = getString(console, value -> datePattern.matcher(value).matches());
            try
            {
                localDate = LocalDate.parse(dateStr, dateFormatter);
                if (validator.apply(localDate))
                    break;
            }
            catch( DateTimeParseException ignored )
            {
            }
        }
        while (true);

        return localDate;
    }
    
    private static String readLine( Console console )
    {
        console.printf("> ");
        return console.readLine().trim();
    }

    static boolean getBooleanAnswer( Console console, String questionStr, boolean defaultTrue, char trueChar, char falseChar )
    {
        console.printf(questionStr + System.lineSeparator());
        return getBoolean(console, defaultTrue, trueChar, falseChar);
    }

    static boolean getBooleanAnswer( Console console, String questionStr, char trueChar, char falseChar )
    {
        console.printf(questionStr + System.lineSeparator());
        return getBoolean(console, trueChar, falseChar);
    }

}
