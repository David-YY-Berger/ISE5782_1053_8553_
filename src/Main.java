import geometries.Sphere;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.io.Console;

import static java.lang.System.out;
import static primitives.Util.*;

public final class Main {

    public static void main(String[] args) {

        Scene myScene = new Scene.Builder("davidsTestScene")
                .setAmbientLight(new Color(255, 255, 0), new Double3(.1, .1, .1))
                .setBackground(new Color(100, 100, 100))
                .addGeometry(new Sphere(2, new Point(6, 0, 0)))
                .addGeometry(new Sphere(2, new Point(6, 3, 0)))
                .build();

        Camera cam = new Camera(Point.ZERO, new Vector(1, 0, 0), new Vector(0, 1, 0))
                .setVPDistance(1).
                setVPSize(100, 100)
                .setImageWriter(new ImageWriter("davidsTestImage", 100, 100))
                .setRayTracerBase(new RayTracerBasic(myScene));

        cam.writeToImage();


      System.out.println("no bugs BH!");



    }
}
