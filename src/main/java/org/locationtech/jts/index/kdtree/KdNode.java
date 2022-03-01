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

package org.locationtech.jts.index.kdtree;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;

/**
 * A node of a {@link KdTree}, which represents one or more points in the same location.
 * 
 * @author dskea
 */
public class KdNode {

    private Coordinate p = null;
    private Object     data;
    private KdNode     left;
    private KdNode     right;
    private int        count;

    /**
     * Creates a new KdNode.
     * 
     * @param _x coordinate of point
     * @param _y coordinate of point
     * @param data a data objects to associate with this node
     */
    public KdNode(double _x, double _y, Object data) {
        p = new Coordinate(_x, _y);
        left = null;
        right = null;
        count = 1;
        this.data = data;
    }

    /**
     * Creates a new KdNode.
     * 
     * @param p point location of new node
     * @param data a data objects to associate with this node
     */
    public KdNode(Coordinate p, Object data) {
        this.p = new Coordinate(p);
        left = null;
        right = null;
        count = 1;
        this.data = data;
    }

    /**
     * Returns the X coordinate of the node
     * 
     * @return X coordinate of the node
     */
    public double getX() {
        return p.x;
    }

    /**
     * Returns the Y coordinate of the node
     * 
     * @return Y coordinate of the node
     */
    public double getY() {
        return p.y;
    }

    /**
     * Gets the split value at a node, depending on 
     * whether the node splits on X or Y.
     * The X (or Y) ordinates of all points in the left subtree
     * are less than the split value, and those
     * in the right subtree are greater than or equal to the split value.
     * 
     * @param isSplitOnX whether the node splits on X or Y
     * @return the splitting value
     */
    public double splitValue(boolean isSplitOnX) {
      if (isSplitOnX) {
        return p.getX();
      }
      return p.getY();
    }
    
    /**
     * Returns the location of this node
     * 
     * @return p location of this node
     */
    public Coordinate getCoordinate() {
        return p;
    }

    /**
     * Gets the user data object associated with this node.
     * @return user data
     */
    public Object getData() {
        return data;
    }

    /**
     * Returns the left node of the tree
     * 
     * @return left node
     */
    public KdNode getLeft() {
        return left;
    }

    /**
     * Returns the right node of the tree
     * 
     * @return right node
     */
    public KdNode getRight() {
        return right;
    }

    // Increments counts of points at this location
    void increment() {
        count = count + 1;
    }

    /**
     * Returns the number of inserted points that are coincident at this location.
     * 
     * @return number of inserted points that this node represents
     */
    public int getCount() {
        return count;
    }

    /**
     * Tests whether more than one point with this value have been inserted (up to the tolerance)
     * 
     * @return true if more than one point have been inserted with this value
     */
    public boolean isRepeated() {
        return count > 1;
    }

    // Sets left node value
    void setLeft(KdNode _left) {
        left = _left;
    }

    // Sets right node value
    void setRight(KdNode _right) {
        right = _right;
    }
    
    /**
     * Tests whether the node's left subtree may contain values
     * in a given range envelope.
     * 
     * @param isSplitOnX whether the node splits on  X or Y
     * @param env the range envelope
     * @return true if the left subtree is in range
     */
    boolean isRangeOverLeft(boolean isSplitOnX, Envelope env) {
      double envMin;
      if ( isSplitOnX ) {
        envMin = env.getMinX();
      } else {
        envMin = env.getMinY();
      }
      double splitValue = splitValue(isSplitOnX);
      boolean isInRange = envMin < splitValue;
      return isInRange;
    }
    
    /**
     * Tests whether the node's right subtree may contain values
     * in a given range envelope.
     * 
     * @param isSplitOnX whether the node splits on  X or Y
     * @param env the range envelope
     * @return true if the right subtree is in range
     */
   boolean isRangeOverRight(boolean isSplitOnX, Envelope env) {
      double envMax;
       if ( isSplitOnX ) {
        envMax = env.getMaxX();
       } else {
        envMax = env.getMaxY();
      }
      double splitValue = splitValue(isSplitOnX);
      boolean isInRange = splitValue <= envMax;
      return isInRange;
    }

   /**
    * Tests whether a point is strictly to the left 
    * of the splitting plane for this node.
    * If so it may be in the left subtree of this node,
    * Otherwise, the point may be in the right subtree. 
    * The point is to the left if its X (or Y) ordinate
    * is less than the split value.
    * 
    * @param isSplitOnX whether the node splits on  X or Y
    * @param pt the query point
    * @return true if the point is strictly to the left.
    * 
    * @see #splitValue(boolean)
    */
   boolean isPointOnLeft(boolean isSplitOnX, Coordinate pt) {
      double ptOrdinate;     
      if (isSplitOnX) {
          ptOrdinate = pt.x;
      }
      else {
          ptOrdinate = pt.y;
      }
      double splitValue = splitValue(isSplitOnX);
      boolean isInRange = (ptOrdinate < splitValue);
      return isInRange;
    }
    
}
