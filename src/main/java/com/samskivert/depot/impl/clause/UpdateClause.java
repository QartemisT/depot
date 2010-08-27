//
// $Id$
//
// Depot library - a Java relational persistence library
// Copyright (C) 2006-2008 Michael Bayne and Pär Winzell
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

package com.samskivert.depot.impl.clause;

import java.util.Collection;

import com.samskivert.depot.PersistentRecord;
import com.samskivert.depot.clause.QueryClause;
import com.samskivert.depot.clause.WhereClause;
import com.samskivert.depot.expression.ColumnExp;
import com.samskivert.depot.expression.SQLExpression;

import com.samskivert.depot.impl.FragmentVisitor;

/**
 * Builds actual SQL given a main persistent type and some {@link QueryClause} objects.
 */
public class UpdateClause
    implements QueryClause
{
    public UpdateClause (Class<? extends PersistentRecord> pClass, WhereClause where,
                         ColumnExp[] fields, PersistentRecord pojo)
    {
        _pClass = pClass;
        _where = where;
        _fields = fields;
        _values = null;
        _pojo = pojo;
    }

    public UpdateClause (Class<? extends PersistentRecord> pClass, WhereClause where,
                         ColumnExp[] fields, SQLExpression[] values)
    {
        _pClass = pClass;
        _fields = fields;
        _where = where;
        _values = values;
        _pojo = null;
    }

    public WhereClause getWhereClause ()
    {
        return _where;
    }

    public ColumnExp[] getFields ()
    {
        return _fields;
    }

    public SQLExpression[] getValues ()
    {
        return _values;
    }

    public PersistentRecord getPojo ()
    {
        return _pojo;
    }

    public Class<? extends PersistentRecord> getPersistentClass ()
    {
        return _pClass;
    }

    // from SQLFragment
    public void addClasses (Collection<Class<? extends PersistentRecord>> classSet)
    {
        classSet.add(_pClass);
        if (_where != null) {
            _where.addClasses(classSet);
        }
        if (_values != null) {
            for (int ii = 0; ii < _values.length; ii ++) {
                _values[ii].addClasses(classSet);
            }
        }
    }

    // from SQLFragment
    public Object accept (FragmentVisitor<?> builder)
    {
        return builder.visit(this);
    }

    /** The class we're updating. */
    protected Class<? extends PersistentRecord> _pClass;

    /** The where clause. */
    protected WhereClause _where;

    /** The persistent fields to update. */
    protected ColumnExp[] _fields;

    /** The field values, or null. */
    protected SQLExpression[] _values;

    /** The object from which to fetch values, or null. */
    protected PersistentRecord _pojo;
}