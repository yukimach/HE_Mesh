/*
 *
 */
package wblut.hemesh;

import wblut.geom.WB_GeometryFactory;

/**
 *
 */
public abstract class HE_Element {
    /**
     *
     */
    protected static long _currentKey;
    /**
     *
     */
    protected final long _key;
    /**
     *
     */
    private int _internalLabel;
    /**
     *
     */
    private int _label;
    /**
     *
     */
    protected final static WB_GeometryFactory geometryfactory = WB_GeometryFactory
	    .instance();
    protected boolean visited;

    /**
     *
     */
    public HE_Element() {
	_key = _currentKey;
	_currentKey++;
	_label = -1;
	_internalLabel = -1;
	visited = false;
    }

    /**
     *
     *
     * @param label
     */
    public final void setInternalLabel(final int label) {
	_internalLabel = label;
    }

    /**
     *
     *
     * @param label
     */
    public final void setLabel(final int label) {
	_label = label;
    }

    public void clearVisited() {
	visited = false;
    }

    public void setVisited() {
	visited = true;
    }

    public boolean isVisited() {
	return visited;
    }

    /**
     *
     *
     * @return
     */
    public final long getKey() {
	return _key;
    }

    /**
     *
     *
     * @return
     */
    public final int getInternalLabel() {
	return _internalLabel;
    }

    /**
     *
     *
     * @return
     */
    public final int getLabel() {
	return _label;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return (int) (_key ^ (_key >>> 32));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
	if (other == null) {
	    return false;
	}
	if (other == this) {
	    return true;
	}
	if (!(other instanceof HE_Element)) {
	    return false;
	}
	return ((HE_Element) other).getKey() == _key;
    }

    /**
     *
     *
     * @param el
     */
    public void copyProperties(final HE_Element el) {
	_label = el.getLabel();
	_internalLabel = el.getInternalLabel();
	visited = el.visited;
    }

    /**
     *
     */
    public abstract void clear();
}