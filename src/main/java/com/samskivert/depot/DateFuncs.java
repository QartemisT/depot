//
// $Id$
//
// Depot library - a Java relational persistence library
// Copyright (C) 2006-2009 Michael Bayne and Pär Winzell
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package com.samskivert.depot;

import java.sql.Date;
import java.sql.Timestamp;

import com.samskivert.depot.expression.FluentExp;
import com.samskivert.depot.expression.SQLExpression;
import com.samskivert.depot.impl.expression.DateFun.*;

/**
 * Provides static methods for date-related function construction.
 */
public class DateFuncs
{
    /**
     * Creates an expression that extracts the date from the given timestamp expression.
     */
    public static FluentExp<Date> date (SQLExpression<Timestamp> exp)
    {
        return new DateTruncate(exp, DateTruncate.Truncation.DAY);
    }

    /**
     * Creates an expression to extract the day-of-week from the the supplied timestamp expression.
     */
    public static FluentExp<Number> dayOfWeek (SQLExpression<Timestamp> exp)
    {
        return new DatePart(exp, DatePart.Part.DAY_OF_WEEK);
    }

    /**
     * Creates an expression to extract the day-of-week from the the supplied date expression.
     */
    public static FluentExp<Number> dayOfWeekFromDate (SQLExpression<Date> exp)
    {
        return new DatePart(exp, DatePart.Part.DAY_OF_WEEK);
    }

    /**
     * Creates an expression to extract the day-of-month from the the supplied timestamp expression.
     */
    public static FluentExp<Number> dayOfMonth (SQLExpression<Timestamp> exp)
    {
        return new DatePart(exp, DatePart.Part.DAY_OF_MONTH);
    }

    /**
     * Creates an expression to extract the day-of-month from the the supplied date expression.
     */
    public static FluentExp<Number> dayOfMonthFromDate (SQLExpression<Date> exp)
    {
        return new DatePart(exp, DatePart.Part.DAY_OF_MONTH);
    }

    /**
     * Creates an expression to extract the day-of-year from the the supplied timestamp expression.
     */
    public static FluentExp<Number> dayOfYear (SQLExpression<Timestamp> exp)
    {
        return new DatePart(exp, DatePart.Part.DAY_OF_YEAR);
    }

    /**
     * Creates an expression to extract the day-of-year from the the supplied data expression.
     */
    public static FluentExp<Number> dayOfYearFromDate (SQLExpression<Date> exp)
    {
        return new DatePart(exp, DatePart.Part.DAY_OF_YEAR);
    }

    /**
     * Creates an expression to extract the hour of the the supplied timestamp expression.
     */
    public static FluentExp<Number> hour (SQLExpression<Timestamp> exp)
    {
        return new DatePart(exp, DatePart.Part.HOUR);
    }

    /**
     * Creates an expression to extract the minute of the the supplied timestamp expression.
     */
    public static FluentExp<Number> minute (SQLExpression<Timestamp> exp)
    {
        return new DatePart(exp, DatePart.Part.MINUTE);
    }

    /**
     * Creates an expression to extract the second of the the supplied timestamp expression.
     */
    public static FluentExp<Number> second (SQLExpression<Timestamp> exp)
    {
        return new DatePart(exp, DatePart.Part.SECOND);
    }

    // TODO: the below should work on java.sql.Date and java.sql.Timestamp, but annoyingly the only
    // supertype shared by those types is java.util.Date

    /**
     * Creates an expression to extract the week of the the supplied timestamp expression.
     */
    public static FluentExp<Number> week (SQLExpression<? extends java.util.Date> exp)
    {
        return new DatePart(exp, DatePart.Part.WEEK);
    }

    /**
     * Creates an expression to extract the month of the the supplied timestamp expression.
     */
    public static FluentExp<Number> month (SQLExpression<? extends java.util.Date> exp)
    {
        return new DatePart(exp, DatePart.Part.MONTH);
    }

    /**
     * Creates an expression to extract the year of the the supplied timestamp expression.
     */
    public static FluentExp<Number> year (SQLExpression<? extends java.util.Date> exp)
    {
        return new DatePart(exp, DatePart.Part.YEAR);
    }

    /**
     * Creates an expression to extract the epoch (aka unix timestamp, aka seconds passed since
     * 1970-01-01) of the the supplied timestamp expression.
     */
    public static FluentExp<Number> epoch (SQLExpression<? extends java.util.Date> exp)
    {
        return new DatePart(exp, DatePart.Part.EPOCH);
    }

    /**
     * Creates an expression for the current timestamp.
     */
    public static FluentExp<Timestamp> now ()
    {
        return new Now();
    }
}
