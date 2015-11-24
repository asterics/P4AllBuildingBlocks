echo deploying jars, creating installer
#javapackager -createjar -v -srcdir build/merged/bin/ARE -outdir build/merged/bin/ARE -outfile org.eclipse.osgi_3.6.0.v20100517.jar -appclass org.eclipse.core.runtime.adaptor.EclipseStarter -paramfile build/merged/bin/ARE/profile/appproperties.ini

javapackager -deploy -srcdir build/merged/bin/ARE -outdir build/deploy -outfile XFacetrackerLK -appclass org.eclipse.core.runtime.adaptor.EclipseStarter -paramfile build/merged/bin/ARE/profile/appproperties.ini -name XFacetrackerLK -title "Head-controlled camera mouse" -vendor "UAS Technikum Wien - AsTeRICS" -v -native deb

