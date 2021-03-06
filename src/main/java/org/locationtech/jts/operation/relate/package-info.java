/*
 * Copyright (c) 2016 Vivid Solutions.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

/**
 * Contains classes to implement the computation of the spatial relationships of <CODE>Geometry</CODE>s.
 * <P>
 * The <code>relate</code> algorithm computes the <code>IntersectionMatrix</code> describing the
 * relationship of two <code>Geometry</code>s.  The algorithm for computing <code>relate</code>
 * uses the intersection operations supported by topology graphs.  Although the <code>relate</code>
 * result depends on the resultant graph formed by the computed intersections, there is
 * no need to explicitly compute the entire graph.
 * It is sufficient to compute the local structure of the graph
 * at each intersection node.
 * <P>
 * The algorithm to compute <code>relate</code> has the following steps:
 * <UL>
 * <LI>Build topology graphs of the two input geometries. For each geometry
 * all self-intersection nodes are computed and added to the graph.
 * <LI>Compute nodes for all intersections between edges and nodes of the graphs.
 * <LI>Compute the labeling for the computed nodes by merging the labels from the input graphs.
 * <LI>Compute the labeling for isolated components of the graph (see below)
 * <LI>Compute the <code>IntersectionMatrix</code> from the labels on the nodes and edges.
 * </UL>
 * <H3>Labeling isolated components</H3>
 * Isolated components are components (edges or nodes) of an input <code>Geometry</code> which
 * do not contain any intersections with the other input <code>Geometry</code>.  The
 * topological relationship of these components to the other input <code>Geometry</code>
 * must be computed in order to determine the complete labeling of the component.  This can
 * be done by testing whether the component lies in the interior or exterior of the other
 * <code>Geometry</code>.  If the other <code>Geometry</code> is 1-dimensional, the isolated
 * component must lie in the exterior (since otherwise it would have an intersection with an
 * edge of the <code>Geometry</code>).  If the other <code>Geometry</code> is 2-dimensional,
 * a Point-In-Polygon test can be used to determine whether the isolated component is in the
 * interior or exterior.
 * <h2>Package Specification</h2>
 * <ul>
 * <li>Java Topology Suite Technical Specifications
 * <li><A HREF="http://www.opengis.org/techno/specs.htm">
 * OpenGIS Simple Features Specification for SQL</A>
 * </ul>
 */
package org.locationtech.jts.operation.relate;