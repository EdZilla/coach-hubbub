// Place your Spring DSL code here
beans = {
  xmlns jee:"http://www.springframework.org/schema/jee"
  jee.'jndi-lookup'(id:"coachHubbubDBDataSource", 'jndi-name':"java:comp/env/jdbc/coach")
}