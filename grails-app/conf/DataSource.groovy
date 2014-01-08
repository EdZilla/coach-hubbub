dataSource {
//    pooled = true
//    driverClassName = "org.h2.Driver"
//    username = "sa"
//    password = ""
	//logSql = true
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	
	format_sql = true
	use_sql_comments = true
}

// environment specific settings
environments {
    development {
        dataSource {
//			jndiName = 'java:comp/env/jdbc/coachHubbubDBDataSource'
//			pooled = false			
//			driverClassName = "com.mysql.jdbc.Driver"			
//			dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			//url: "jdbc:mysql://localhost:3306/coach?autoReconnect=true"
			//username: "coach"
			//password: "coach"
            //url = "jdbc:h2:mem:/opt/data/db/coachg2/devDb"
			//url = "jdbc:h2:mem:/opt/data/db/coachg2/devDb"
			
			//url = "jdbc:mysql://localhost:3306/coach?autoReconnect=true"
			jndiName = 'java:comp/env/jdbc/coachDBDataSource'						
			
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:coachTestDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
            //dbCreate = "update"
			pooled = false
			dbCreate = "update"			
			driverClassName = "com.mysql.jdbc.Driver"
			dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'
			jndiName = 'java:comp/env/jdbc/coachDBDataSource'
			
			//jndiName = 'java:comp/env/jdbc/coachDBDataSource'
			//jndiName = 'java:comp/env/jdbc/coachDBDataSource'
        }
    }
}
