<!-
//https://stackoverflow.com/questions/38239554/java-web-servlet-writing-plain-text-on-an-existing-html-template-file 
!->

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
				<option value="TITLE">Title</option>
				<option value="AUTHOR">Author</option>
				<option value="ISBN">ISBN</option>
			</select>
			<input class=search type="text" name="search" placeholder="Search for a book by category...">
			<input type=submit value=Search name=submit>
			<input type="submit" value="Advanced Search" formaction="advancedSearch" formmethod="GET">
			</form>
			
		</div>
		<div class=nav-button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button onclick="window.location.href='User'" style="height:50px;">Account</button>
			<button onclick="window.location.href='createListing'" style="height:50px;">Create a Listing</button>
			<button onclick="window.location.href='Cart'" style="height:50px;">Cart</button>
		</div>
	</nav>
	<div class="window">
		<h2>Welcome to BookWorms!</h2>
		<p>
			BookWorms is a web-based textbook sale platform designed for students by students.
			For many college students, textbooks can be a large expense. Especially when those
			books are only used for a single semester. BookWorms seeks to connect students
			who want to sell old textbooks with students looking for a cheaper alternative to buying textbooks
			from a retailer. To get started, search by title, author, or ISBN in the search bar
			above!
		</p>
		<hr>
		<h2>New Listings</h2>
		${requestScope["books"]}
	</div>
</body>

</html>