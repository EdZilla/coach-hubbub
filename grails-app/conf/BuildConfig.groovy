grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
		// next two only for spring security RC. Once they go GA remove 
		mavenRepo 'http://repo.spring.io/milestone'
		mavenRepo "http://download.java.net/maven/2/"
		
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    def gebVersion = "0.9.2"
    def seleniumVersion = "2.32.0"

    dependencies {
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        test "org.gebish:geb-spock:$gebVersion"

        test "org.seleniumhq.selenium:selenium-support:$seleniumVersion"
//        test "org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion", {
//            exclude "xml-apis"
//        }
//        test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.47"

        // plugins for the compile step
        //compile ":scaffolding:2.0.1"
		compile ":scaffolding:2.0.1"
        compile ':cache:1.1.1'
		// from graina2 chapter 10. Don't need both cache and cache-ehcache so choose one. cache-ehcache is much more sophistocated. 
		//compile ":cache-ehcache:1.0.0"
		compile ":platform-core:1.0.RC6"

        // plugins needed at runtime but not for compilation
        runtime ":hibernate:3.6.10.4" // or ":hibernate4:4.1.11.4"
        runtime ":database-migration:1.3.8"
        runtime ":jquery:1.10.2"
		runtime ":jquery-ui:1.10.3", ":famfamfam:1.0.1"
        runtime ":resources:1.2.1"
		
		test("org.grails.plugins:geb:$gebVersion")
        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0.1"
        //runtime ":cached-resources:1.1"
        //runtime ":yui-minify-resources:0.1.5"
		
		// Apparently this is not necessary with Grails 2.3.x
//		test(":spock:0.7") {
//			exclude "spock-grails-support"
//		}
		
		// from Chapter 10 in Graina2
		compile ':mail:1.0.1'
		
		//compile ':searchable:0.6.6'
		//compile ':searchable:0.6.5'
		// try this
		//compile ':searchable:0.6.4'
		
		// to test emails
		test ':dumbster:0.1'
		
		compile ':spring-security-core:2.0-RC2'
		compile ":spring-security-ui:1.0-RC1"
    }
}
