/*
 * This file is part of HE_Mesh, a library for creating and manipulating meshes.
 * It is dedicated to the public domain. To the extent possible under law,
 * I , Frederik Vanhoutte, have waived all copyright and related or neighboring
 * rights.
 * 
 * This work is published from Belgium. (http://creativecommons.org/publicdomain/zero/1.0/)
 * 
 */
package wblut.hemesh;

import java.util.Iterator;

/**
 * 
 */
public class HE_PathFaceInnerIterator implements Iterator<HE_Face> {
    
    /**
     * 
     */
    private final HE_PathHalfedge _start;
    
    /**
     * 
     */
    private HE_PathHalfedge _current;

    /**
     * 
     *
     * @param path 
     */
    public HE_PathFaceInnerIterator(final HE_Path path) {
	_start = path.getPathHalfedge();
	_current = null;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
	if (_start == null) {
	    return false;
	}
	return (_current == null)
		|| ((_current.getNextInPath() != _start) && (_current
			.getNextInPath() != null));
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    @Override
    public HE_Face next() {
	if (_current == null) {
	    _current = _start;
	} else {
	    _current = _current.getNextInPath();
	}
	return _current.getHalfedge().getFace();
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {
	throw new UnsupportedOperationException();
    }
}