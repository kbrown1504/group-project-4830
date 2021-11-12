<!DOCTYPE html PUBLIC>
<html>

<head>
	<style>
		.appbar 
		{
			background:#00004e;
			display:table;
			height: 90px;
			width:100%;
			position:absolute;
			top:0;
			left:0;
			box-shadow: 0px 5px 3px #aaaaaa;
			padding:0px;
			
		}
		.searchText 
		{
			width:230px;
			margin-left:5px;
		}
		.searchNum
		{
			width:100px;
			margin-left:5px
		}
		.nav-button
		{
			height:100%;
			display:table-cell;
			text-align:right;
			vertical-align:middle;
			padding-right:20px;
			
		}
		.window 
		{
			position:relative;
			margin-top: 15px;
			margin-left: auto;
			margin-right:auto;
			width:70%;
		}
		.filterPane
		{
			position:relative;
			margin-top: 110px;
			margin-left:auto;
			margin-right:auto;
			width:70%;
		}
	</style>
</head>

<body>
	<nav class="appbar">
		<h1 style="color:#ffffff; margin:10px;">BookWorms - ${requestScope["pageTitle"]}</h1>
		<form>
			<input class=searchText type="text" name="search" placeholder="Search for a book...">
			<input type=submit value=Search name=submit>
		</form>
		<div class=nav-button>
			<button onclick="window.location.href='advancedSearch.jsp'" style="height:50px;">Adv.Search</button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button style="height:50px;">Profile</button>
		</div>
	</nav>
	
	<div class="filterPane">
		<h2>Filtering Options</h2>
		<form action="AdvancedSearch" method="POST">
			<pre>Title: <input class="searchText" type="text" name="title"> Author: <input class="searchText" type="text" name="author"></pre> <br />
			<pre>ISBN:  <input class="searchText" type="text" name="isbn"> Seller: <input class="searchText" type="text" name="seller"></pre> <br />
			<pre>Price: <input class="searchNum" type="text" name="priceMin" placeholder="minimum"> to <input class="searchNum" type="text" name="priceMax" placeholder="maximum"></pre> <br />
			<pre>Condition: <input class="searchNum" type="text" name="conditionMin" placeholder="minimum"> to <input class="searchNum" type="text" name="conditionMax" placeholder="maximum"></pre> <br />
			<input type="submit" value="Search"/>
		</form>
		<hr>
	</div>
	<div class="window">
		<h2>Search Results</h2>
		${requestScope["books"]}
	</div>
</body>

</html>