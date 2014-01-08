
Summitbid coach-hubbub App

Developed by following the graina2 MEAP and then adding coach functionality

https://github.com/EdZilla/coach-hubbub

https://grandcentral.cloudbees.com/

Platform core plugin
http://grails.org/plugin/platform-core


Dumbster plugin to test email
http://grails.org/plugin/dumbster

Searchable plugin doesn't seem to work. Can't even install it
tried 
//compile ':searchable:0.6.6'
compile ':searchable:0.6.5'
//compile ':searchable:0.6.4'

0.6.4 (the one in the MEAP) failed with 
Error Compilation error: startup failed:
Compile error during compilation with javac.
/Users/eyoung2297k/git/coach-hubbub/target/work/plugins/searchable-0.6.4/src/java/grails/plugin/searchable/internal/util/GrailsDomainClassUtils.java:38: error: non-static method getMapping(Class<?>) cannot be referenced from a static context
        Mapping mapping = GrailsDomainBinder.getMapping(domainClassProperty.getDomainClass().getClazz());
        
0.6.5 (supposed to work with grails 2.3.x) seems to work ok. (Update: does not work with any version)
0.6.6 does not work. Fails with : 
Error Resolve error obtaining dependencies: Could not find artifact org.compass-project:compass:jar:2.2.1 in grailsCentral (http://repo.grails.org/grails/plugins) (Use --stacktrace to see the full trace)


Needed to change permissions on .grails/project directory to eyoung2297k. 
        
Seems to work with 0.6.5 and only the User.groovy domain changes. Post.groovy changes fail. Need to revisit this because I can't get it to work. 

Lucene search toolbox/control panel
http://www.getopt.org/luke/   

"is not bound in this Context. Unable to find [jdbc]"
http://grails.1312388.n4.nabble.com/Repost-Upgrade-to-2-3-and-jndiName-breaks-td4650947.html
http://jira.grails.org/browse/GRAILS-10703     
        

