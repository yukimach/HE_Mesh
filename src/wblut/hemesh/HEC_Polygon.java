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

import wblut.geom.WB_Coord;
import wblut.geom.WB_Point;
import wblut.geom.WB_Polygon;
import wblut.geom.WB_Vector;
import wblut.math.WB_Epsilon;

/**
 *
 */
public class HEC_Polygon extends HEC_Creator {

	/**
	 * 
	 */
	private WB_Polygon polygon;

	/**
	 * 
	 */
	private double thickness;

	/**
	 * 
	 */
	public HEC_Polygon() {
		super();
		override = true;
	}

	/**
	 * 
	 *
	 * @param poly
	 * @param d
	 */
	public HEC_Polygon(final WB_Polygon poly, final double d) {
		this();
		override = true;
		polygon = poly;
		thickness = d;
	}

	/**
	 * 
	 *
	 * @param poly
	 * @return
	 */
	public HEC_Polygon setPolygon(final WB_Polygon poly) {
		polygon = poly;
		return this;
	}

	/**
	 * 
	 *
	 * @param d
	 * @return
	 */
	public HEC_Polygon setThickness(final double d) {
		thickness = d;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wblut.hemesh.creators.HEC_Creator#createBase()
	 */
	@Override
	protected HE_Mesh createBase() {
		if (polygon == null) {
			return null;
		}
		final WB_Vector norm = polygon.getPlane().getNormal();
		final int n = polygon.getNumberOfPoints();
		final boolean surf = WB_Epsilon.isZero(thickness);
		final WB_Coord[] points = new WB_Coord[surf ? n : 2 * n];
		for (int i = 0; i < n; i++) {
			points[i] = polygon.getPoint(i);
		}
		if (!surf) {
			for (int i = 0; i < n; i++) {
				points[n + i] = new WB_Point(points[i]).addMulSelf(thickness, norm);
			}
		}
		int[][] faces;
		if (surf) {
			faces = new int[1][n];
			for (int i = 0; i < n; i++) {
				faces[0][i] = i;
			}
		} else {
			faces = new int[n + 2][];
			faces[n] = new int[n];
			faces[n + 1] = new int[n];
			for (int i = 0; i < n; i++) {
				faces[n][i] = i;
				faces[n + 1][i] = 2 * n - 1 - i;
				faces[i] = new int[4];
				faces[i][0] = i;
				faces[i][3] = (i + 1) % n;
				faces[i][2] = n + (i + 1) % n;
				faces[i][1] = n + i;
			}
		}
		final HEC_FromFacelist fl = new HEC_FromFacelist();
		fl.setVertices(points).setFaces(faces).setDuplicate(false);
		return HET_MeshOp.flipFaces(fl.createBase());
	}
}
