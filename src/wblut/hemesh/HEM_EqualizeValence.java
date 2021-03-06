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

import java.util.Collections;
import java.util.List;

/**
 * Bend a mesh. Determined by a ground plane, a bend axis and an angle factor.
 *
 * @author Frederik Vanhoutte (W:Blut)
 *
 */
public class HEM_EqualizeValence extends HEM_Modifier {
	/** threshold. */
	private double threshold;

	/**
	 * Instantiates a new HEM_Bend.
	 */
	public HEM_EqualizeValence() {
		super();
		threshold = -Math.PI;
	}

	/**
	 * Set threshold angle.
	 *
	 * @param a
	 *            threshold angle in radians
	 * @return self
	 */
	public HEM_EqualizeValence setThreshold(final double a) {
		threshold = a;
		return this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wblut.hemesh.modifiers.HEB_Modifier#modify(wblut.hemesh.HE_Mesh)
	 */
	@Override
	public HE_Mesh apply(final HE_Mesh mesh) {
		mesh.triangulate();
		HE_Vertex a, b, c, d;
		int devpre = 0, devpost = 0;
		for (int r = 0; r < 2; r++) {

			List<HE_Halfedge> edges = mesh.getEdges();
			Collections.shuffle(edges);
			for (HE_Halfedge e : edges) {
				if (!e.isInnerBoundary() && e.getEdgeDihedralAngle() > threshold) {

					a = e.getVertex();
					b = e.getPair().getVertex();
					c = e.getNextInFace().getEndVertex();
					d = e.getPair().getNextInFace().getEndVertex();
					devpre = Math.abs((a.isBoundary() ? 4 : 6) - a.getVertexOrder());
					devpre += Math.abs((b.isBoundary() ? 4 : 6) - b.getVertexOrder());
					devpre += Math.abs((c.isBoundary() ? 4 : 6) - c.getVertexOrder());
					devpre += Math.abs((d.isBoundary() ? 4 : 6) - d.getVertexOrder());
					if (HET_MeshOp.flipEdge(mesh, e)) {

						devpost = Math.abs((a.isBoundary() ? 4 : 6) - a.getVertexOrder());
						devpost += Math.abs((b.isBoundary() ? 4 : 6) - b.getVertexOrder());
						devpost += Math.abs((b.isBoundary() ? 4 : 6) - c.getVertexOrder());
						devpost += Math.abs((b.isBoundary() ? 4 : 6) - d.getVertexOrder());
						if (devpre <= devpost) {
							HET_MeshOp.flipEdge(mesh, e);
						}
					}
				}

			}

		}
		return mesh;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wblut.hemesh.modifiers.HEB_Modifier#modifySelected(wblut.hemesh.HE_Mesh)
	 */
	@Override
	public HE_Mesh apply(final HE_Selection selection) {
		selection.parent.triangulate(selection);
		final List<HE_Halfedge> edges = selection.getInnerEdges();
		Collections.shuffle(edges);
		HE_Vertex a, b, c, d;
		int devpre, devpost;
		for (int r = 0; r < 2; r++) {
			for (final HE_Halfedge e : edges) {
				if (e.getEdgeDihedralAngle() > threshold) {

					a = e.getVertex();
					b = e.getPair().getVertex();
					c = e.getNextInFace().getEndVertex();
					d = e.getPair().getNextInFace().getEndVertex();
					devpre = Math.abs((a.isBoundary() ? 4 : 6) - a.getVertexOrder());
					devpre += Math.abs((b.isBoundary() ? 4 : 6) - b.getVertexOrder());
					devpre += Math.abs((c.isBoundary() ? 4 : 6) - c.getVertexOrder());
					devpre += Math.abs((d.isBoundary() ? 4 : 6) - d.getVertexOrder());
					if (HET_MeshOp.flipEdge(selection.parent, e)) {
						devpost = Math.abs((a.isBoundary() ? 4 : 6) - a.getVertexOrder());
						devpost += Math.abs((b.isBoundary() ? 4 : 6) - b.getVertexOrder());
						devpost += Math.abs((b.isBoundary() ? 4 : 6) - c.getVertexOrder());
						devpost += Math.abs((b.isBoundary() ? 4 : 6) - d.getVertexOrder());
						if (devpre <= devpost) {
							HET_MeshOp.flipEdge(selection.parent, e);
						}
					}
				}
			}
		}
		return selection.parent;
	}
}
