# HE_Mesh
=========

HE_Mesh, a Java library for creating and manipulating polygonal meshes. Aimed primarily at [Processing](http://processing.org/).

## Building HE_Mesh from source.

The first thing you need to do is download or fork this repository and import the code in Eclipse.
You need to add some other external *.jar files to the properties of your Eclipse project to compile HE_Mesh.

* `core.jar`: This is the core Processing API for Processing 3. Download the latest version of Processing here: http://processing.org/
* `javolution-6.1.0.jar`: Javolution is used to make HE_Mesh faster. You'll need version 6.1.0, download here: http://www.wblut.com/hemesh/javolution-6.1.0.zip (http://javolution.org/)
* `jts.jar`: The JTS Topology Suite is an API of spatial predicates and functions for processing planar geometry. You need version 1.13. Download here: http://www.wblut.com/hemesh/jts.zip (http://tsusiatsoftware.net/jts/main.html)
* `trove-3.1a1.jar`: A collection of high speed primitive based collections. Download here: http://www.wblut.com/hemesh/trove-3.1a1.zip
  (http://trove.starlight-systems.com/)
* `hemesh-external-2_2_0.jar`: HE_Mesh contains source code from other authors that was converted to use my geometry classes for convenience. To protect the rights of the original authors whose work is not in the public domain, the source code is only available on request. The required JAR can be downloaded here: http://www.wblut.com/hemesh/hemesh-external.zip
* `hemesh-data-2_2_0.jar`: Data for HE_Mesh. The required JAR can be downloaded here: http://www.wblut.com/hemesh/hemesh-data.zip
* `exp4j.jar`: A simple expression evaluator for Java: https://github.com/fasseg/exp4j. Download a built version here:http://www.wblut.com/hemesh/exp4j.zip
* `objparser.jar`: A simple OBJ file parser for Java: https://github.com/momchil-atanasov/java-data-front  Download a built version here:http://www.wblut.com/hemesh/objparser.zip

## Build-of-the-day HE_Mesh (2016/03/25)

Download a recent built here: http://www.wblut.com/hemesh/hemesh20160325.zip.

## License

HE_Mesh, with the below exceptions, is dedicated to the public domain. 
To the extent possible under law, I, Frederik Vanhoutte, have waived all copyright and related or neighboring rights to HE_Mesh. This work is published from Belgium.
(http://creativecommons.org/publicdomain/zero/1.0/)

The following classes are subject to the license agreement of their original authors, included in the source file:

* wblut.geom.WB_ShapeReader
* wblut.math.WB_MTRandom
* wblut.math.WB_OSNoise
* wblut.math.WB_PNoise
* wblut.math.WB_SNoise

The following packages are part of hemesh-external.jar and are subject to the license agreement of their original authors:

* wblut.external.Delaunay https://github.com/visad/visad 
* wblut.external.ProGAL http://www.diku.dk/~rfonseca/ProGAL/
* wblut.external.straightskeleton https://code.google.com/p/campskeleton/
* wblut.external.QuickHull3D https://www.cs.ubc.ca/~lloyd/java/quickhull3d.html

The modified code is available on request.
