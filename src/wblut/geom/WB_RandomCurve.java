
/*
 * This file is part of HE_Mesh, a library for creating and manipulating meshes.
 * It is dedicated to the public domain. To the extent possible under law,
 * I , Frederik Vanhoutte, have waived all copyright and related or neighboring
 * rights.
 * 
 * This work is published from Belgium. (http://creativecommons.org/publicdomain/zero/1.0/)
 * 
 */
package wblut.geom;

import wblut.math.WB_MTRandom;

/**
 *
 * Random generator for points on a halfopen curve. The distribution is only
 * uniform in the curve parameter.
 *
 * @author Frederik Vanhoutte, W:Blut
 *
 */
public class WB_RandomCurve implements WB_RandomPoint {
	private final WB_MTRandom randomGen;
	private WB_Curve curve;
	private double start, end;
	private WB_Vector offset;

	/**
	 * 
	 *
	 * @param curve 
	 * @param start 
	 * @param end 
	 */
	public WB_RandomCurve(WB_Curve curve, final double start, final double end) {
		this.start = start;
		this.end = end;
		this.curve = curve;
		randomGen = new WB_MTRandom();
		offset = new WB_Vector();
	}

	/**
	 * 
	 *
	 * @param curve 
	 * @param start 
	 * @param end 
	 * @param seed 
	 */
	public WB_RandomCurve(WB_Curve curve, final double start, final double end, final long seed) {
		this.start = start;
		this.end = end;
		this.curve = curve;
		randomGen = new WB_MTRandom(seed);
		offset = new WB_Vector();
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#setSeed(long)
	 */
	@Override
	public WB_RandomCurve setSeed(final long seed) {
		randomGen.setSeed(seed);
		return this;
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#nextPoint()
	 */
	@Override
	public WB_Point nextPoint() {
		final double d = start + (end - start) * randomGen.nextDouble();
		return curve.curvePoint(d).addSelf(offset);
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#nextVector()
	 */
	@Override
	public WB_Vector nextVector() {
		final double d = start + (end - start) * randomGen.nextDouble();
		return curve.curvePoint(d).addSelf(offset);
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#reset()
	 */
	@Override
	public void reset() {
		randomGen.reset();
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#setOffset(wblut.geom.WB_Coord)
	 */
	@Override
	public WB_RandomPoint setOffset(WB_Coord offset) {
		this.offset.set(offset);
		return this;
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#setOffset(double, double)
	 */
	@Override
	public WB_RandomPoint setOffset(double x, double y) {
		this.offset.set(x, y, 0);
		return this;
	}

	/* (non-Javadoc)
	 * @see wblut.geom.WB_RandomPoint#setOffset(double, double, double)
	 */
	@Override
	public WB_RandomPoint setOffset(double x, double y, double z) {
		this.offset.set(x, y, z);
		return this;
	}
}
