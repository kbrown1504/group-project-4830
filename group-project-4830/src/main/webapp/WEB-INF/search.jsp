

<!DOCTYPE html PUBLIC>
<html>

<head>
	<style>
		.appbar {
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
		.search {
			width:60%;
		}
		.dropdown {
			margin-left:10px;
		}
		.forms {
			display:vertical;
		}
		.nav-button{
			height:100%;
			display:table-cell;
			text-align:right;
			vertical-align:middle;
			padding-right:20px;
			
		}
		.window {
			position:relative;
			margin-top: 120px;
			margin-left: auto;
			margin-right:auto;
			width:60%;
		}
	</style>
</head>

<body>
	<nav class="appbar">
		<h1 style="color:#ffffff; margin:10px;">BookWorms - ${requestScope["pageTitle"]}</h1>
		<div class="forms">
			<form action="search" method="POST">
			<select class="dropdown" name="categories" id="categories">
				<option value="TITLE" ${requestScope["titleSelected"]}>Title</option>
				<option value="AUTHOR" ${requestScope["authorSelected"]}>Author</option>
				<option value="ISBN" ${requestScope["isbnSelected"]}>ISBN</option>
			</select>
			<input class=search type="text" name="search" placeholder="Search for a book by category..." value="${requestScope["search"]}">
			<input type=submit value=Search name=submit>
			<input type="submit" value="Advanced Search" formaction="advancedSearch" formmethod="GET">
			</form>
			
		</div>
		<div class=nav-button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button onclick="window.location.href='User'" style="height:50px;">Account</button>
			<button onclick="window.location.href='Cart'" style="height:50px;">Cart</button>
		</div>
	</nav>
	<div class="window">
		<h2>Search Results</h2>
		<hr>
		${requestScope["searchResults"]}
	</div>
</body>

</html>