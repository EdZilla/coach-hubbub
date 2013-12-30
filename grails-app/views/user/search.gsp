<html>
<head>
    <title>Search Coach</title>
    <meta name="layout" content="main"/>
</head>
<body>
		<nav:secondary/>
        
        <div id="user-nav">
           <nav:menu scope="user"/>
        </div> 

    <formset>
        <legend>Search for Friends</legend>
        <g:form action="results">
            <label for="query">User Id</label>
            <g:textField name="query" />
            <g:submitButton name="search" value="Search"/>
        </g:form>
    </formset>
</body>
</html>
