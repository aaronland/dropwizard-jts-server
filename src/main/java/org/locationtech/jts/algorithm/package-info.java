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
 * Contains classes and interfaces implementing fundamental computational geometry algorithms.
 * <H3>Robustness</H3>
 * Geometrical algorithms involve a combination of combinatorial and numerical computation.  As with
 * all numerical computation using finite-precision numbers, the algorithms chosen are susceptible to
 * problems of robustness.  A robustness problem occurs when a numerical calculation produces an
 * incorrect answer for some inputs due to round-off errors.  Robustness problems are especially
 * serious in geometric computation, since they can result in errors during topology building.
 * <P>
 * There are many approaches to dealing with the problem of robustness in geometrical computation.
 * Not surprisingly, most robust algorithms are substantially more complex and less performant than
 * the non-robust versions.  Fortunately, JTS is sensitive to robustness problems in only a few key
 * functions (such as line intersection and the point-in-polygon test).  There are efficient robust
 * algorithms available for these functions, and these algorithms are implemented in JTS.
 * <H3>Computational Performance</H3>
 * Runtime performance is an important consideration for a production-quality implementation of
 * geometric algorithms.  The most computationally intensive algorithm used in JTS is intersection
 * detection.  JTS methods need to determine both all intersection between the line segments in a
 * single Geometry (self-intersection) and all intersections between the line segments of two different
 * Geometries.
 * <P>
 * The obvious naive algorithm for intersection detection (comparing every segment with every other)
 * has unacceptably slow performance.  There is a large literature of faster algorithms for intersection
 * detection.  Unfortunately, many of them involve substantial code complexity.  JTS tries to balance code
 * simplicity with performance gains.  It uses some simple techniques to produce substantial performance
 * gains for common types of input data.
 * <h2>Package Specification</h2>
 * <ul>
 * <li>Java Topology Suite Technical Specifications
 * <li><A HREF="http://www.opengis.org/techno/specs.htm">
 * OpenGIS Simple Features Specification for SQL</A>
 * </ul>
 */
package org.locationtech.jts.algorithm;