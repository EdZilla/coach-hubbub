<!doctype html>
<html>
<head>
  <title>SummitCoach &raquo; <g:layoutTitle default="Welcome" /></title>
  <g:external dir="css" file="hubbub.css"/>
  <g:external dir="css" file="main.css"/>
  <g:layoutHead />
  <r:layoutResources/>
</head>
<body>
  <div>
    <div id="hd">
      <g:link uri="/">
        <g:img id="logo" uri="/images/summitbid_logo.gif" alt="hubbub logo"/>
      </g:link>
    </div>
    <!-- platform-core nav -->
    <nav:primary id="primary-nav"> 	</nav:primary>
    
    <div id="bd"><!-- start body -->
    
    <!-- platform-core nav -->
<%--        <nav:secondary/>--%>
    
        <g:layoutBody/>
        
    </div>  <!-- end body -->
    <div id="ft">
      <div id="footerText">SummitCoach - Social Workout Planning on Grails</div>
      <div id="appVersion">App Version <g:meta name="app.version"/></div>
      <div id="grailsVersion">on Grails Version <g:meta name="app.grails.version"/></div>
    </div>
  </div>
  <r:layoutResources/>
</body>
</html>
