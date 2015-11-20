echo deploying jars, creating installer
#javafxpackager -createjar -v -srcdir build/merged/bin/ARE -destdir build/merged/bin/ARE -outfile org.eclipse.osgi_3.6.0.v20100517 -appclass org.eclipse.core.runtime.adaptor.EclipseStarter -paramfile build/merged/bin/ARE/profile/appproperties.ini

javafxpackager -deploy -allpermissions -srcdir build/merged/bin/ARE -outdir build/deploy -outfile XFacetrackerLK -width 400 -height 400 -appclass org.eclipse.core.runtime.adaptor.EclipseStarter -paramfile build/merged/bin/ARE/profile/appproperties.ini -name XFacetrackerLK -version "1.0.0-AsTeRICS-2.7" -title "Head-controlled camera mouse" -vendor "UAS Technikum Wien - AsTeRICS" -v -native installer

