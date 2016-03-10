# P4AllBuildingBlocks
This repository contains 'sub-repositories' with configuration and build files for deploying dedicated AsTeRICS Building Blocks as runnable stand-alone demos. Additionally, the building block demos can be bundled together into one installer. A dedicated deployment tool ([AsTeRICS Packaging Environment, APE](https://github.com/asterics/P4AllBuildingBlocks/wiki/AsTeRICS-Packaging-Environment-(APE))) has been developed for the [Prosperity for All (P4All)](http://www.prosperity4all.eu/
) project and is used to extract only the needed resources of a full AsTERICS installation to run a building block demo. For more information about customization of Building Blocks and deployments as well as licensing options please have a look a the [Wiki](https://github.com/asterics/P4AllBuildingBlocks/wiki)

A short overview of the features of the building blocks can be found in the [P4All Developer Space listing](http://portal.teco.edu/dspace/?q=en/)

## Demos
Go to the AsTeRICS [demo page](http://asterics.github.io/AsTeRICS/demos.html) to see the building blocks in action.
The [demo release](https://github.com/asterics/P4AllBuildingBlocks/releases/tag/asterics-prosperity4all-bb-demos) of the AsTeRICS system includes runnable versions of all these Building blocks and demo applications of their combination.

## Building the project
This repository is organized in thematic subfolders ('sub-repositories') like ```CameraInput``` containing building blocks using a camera (e.g. for facetracking or eyetracking) or demonstrate use cases for a Smart Home environment.

The building blocks can be built in two steps and used in two ways depending on your requirements:

* Step 1: Create a downstripped/extracted version of AsTeRICS depending on the given model files using [APE-copy](https://github.com/asterics/AsTeRICS/tree/Licensing_Packaging_Asterics_2_8_Prep/bin/APE#ape-copy).You can use the resulting folder and **bundle and deploy it together with your software**.

* Step 2: Create a native installer and native launcher for your platform of choice - currently Windows (.msi, .exe) and Linux (.deb)

The behaviour of APE-copy and the native installer creation can be configured in the file ```APE.properties```. There you can define model files, copy modes, application name and version and optionally embed a JRE (Java Runtime Environment) into your installer.   

### Install and build instructions of prerequisites 

1. Clone the AsTeRICS repository to a parallel folder of this one.  
  ```
cd ..

git clone -b Licensing_Packaging_Asterics_2_8_Prep https://github.com/asterics/AsTeRICS.git
  ```
2. Install the [**Java Development Kit 8 (32-bit)**] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  * Ensure to set “JAVA_HOME” to the folder where you installed the Java JDK and add the JDK bin path to the Environment Variable “Path”
3. Install the [**apache ant build framework (version >= 1.9.1)**] (http://ant.apache.org/bindownload.cgi)
  * Ensure to set “ANT_HOME” to the folder where you installed ant and add the ant bin path to the Environment Variable “Path”
4. Open a terminal and go to the ```ARE``` subfolder
5. Compile the ARE by calling
  ```
  ant
  ```
6. Edit/Check the ```<ARE.baseURI>``` property value in the file [P4AllBuildingBlocks/imported.xml](https://github.com/asterics/P4AllBuildingBlocks/blob/master/imported.xml). 
It must point to the ```bin/ARE``` folder of the installed AsTeRICS framework.   
7. __Only for Step 2__: Install installer-specific toolkits like [InnoSetup >= 5] (http://www.jrsoftware.org/isdl.php)(for .exe installer), [WiX toolset >= 3.0](http://wixtoolset.org/) or [debian packaging tools] (https://wiki.debian.org/PackageManagement) depending on the required target platform. For more details, read the [JavaFX packaging tutorial] (https://docs.oracle.com/javase/8/docs/technotes/guides/deploy/self-contained-packaging.html#A1324980) 

### Creating bundled demo with APE-copy

* Open a terminal window in the root folder of the P4AllBuildingBlocks repository and call  
```ant APE-copy```
* To test the result, go to the ```build\merged\bin\ARE``` subfolder and start the ARE.  
```
cd build\merged\bin\ARE
start.bat
```  

### Creating bundled demo installer (.exe)
Please ensure to install [InnoSetup >= 5] (http://www.jrsoftware.org/isdl.php) to be able to create .exe installer files.

* Open a terminal window in the root folder of the P4AllBuildingBlocks repository and call  
```ant deploy```
* To test the result, go to the ```build\deploy\bundles``` subfolder and execute the demo installer  
```
cd build\deploy\bundles
asterics-prosperity4all-bb-demos-0.1.exe
```

The demo will be installed to the user home directory and started automatically.

### Creating single camera mouse demo with APE-copy

* To build the demo of a single building block, go to the respective subfolder - for the windows-only camera mouse, this is ```CameraInput\FacetrackerLK-windows``` and call ```ant APE-copy```.
```
cd CameraInput\FacetrackerLK-windows
ant APE-copy
```
* To test the result, go to the ```build\merged\bin\ARE``` subfolder and start the ARE.
```
cd build\merged\bin\ARE
start.bat
```  

### Creating single camera mouse demo installer (.exe)

* To build the demo of a single building block, go to the respective subfolder - for the windows-only camera mouse, this is ```CameraInput\FacetrackerLK-windows``` and call ```ant deploy```.
```
cd CameraInput\FacetrackerLK-windows
ant deploy
```
* To test the result, go to the ```build\deploy\bundles``` subfolder and execute the demo installer  
```
cd build\deploy\bundles
cameramouse-facetrackerlk-p4all-bb-demo-0.1.exe
```

The demo will be installed to the user home directory and started automatically.
