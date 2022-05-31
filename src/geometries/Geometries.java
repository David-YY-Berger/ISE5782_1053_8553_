package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * implements the "Composite" design pattern; this object can iterate thru a list of geometries
 *
 */
public class Geometries extends Intersectable {

    public List<Intersectable> intersectableList; //should NOT be final; we plan to add to this..

    //default CTOR
    public Geometries() {
        /*
        WE HAVE CHOSEN TO USE A LINKEDLIST INSTEAD OF AN ARRAYLIST, BECAUSE:
        (1) we will never need to access a particular element; rather we will always iterate thru them...
        (2) we need to add many shapes to our list, and a linkedList is faster in that regard..
         */
        intersectableList = new LinkedList<>();
    }
    //CTOR with parameters
    public Geometries(Intersectable ... geometries){

        intersectableList = new LinkedList<>(); //intialize...
        intersectableList.addAll(List.of(geometries));
    }

//    public List<Intersectable> getIntersectableList() {
//        return intersectableList;
//    }
    public void add(Intersectable ... geoList) {

        intersectableList.addAll(List.of(geoList));
    }

        @Override
        public List<GeoPoint> findGeoIntersectionsHelper (Ray ray){

            List<GeoPoint> res = null;

            for (Intersectable shape : intersectableList) {

                List<GeoPoint> thisShapeIntersections = shape.findGeoIntersections(ray);
                if (thisShapeIntersections != null)      //returns null if there are no intersections....
                {
                    if(shape instanceof BoundingBox)
                    {
                        res = ((BoundingBox) shape).boxShapeList.findGeoIntersections(ray);
                    }
                    else {
                        if (res == null) { //for first time that we add a list...
                            res = new LinkedList<>();
                        }
                        res.addAll(thisShapeIntersections);
                    }

                }
            }

            return res;
        }

    }

