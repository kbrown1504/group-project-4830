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
		.reviewInputs {
			vertical-align:top;
			display:block;
			margin-bottom: 10px;
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
			<button onclick="window.location.href='Cart'" style="height:50px;">Cart</button>
		</div>
	</nav>
	<div class="window">
	<h2 style="color:red;">${requestScope["message"]}</h2>
	<h2>Leave a Review for ${requestScope["sellerName"]}</h2>
	<hr>
		<form method="POST">
			<input hidden name="sellerID" value=${requestScope["sellerID"]}>
			<label for="rating">Rating:</label>
			<select class="reviewInputs" name="rating" id="rating">
				<option value = "1">1 star</option>
				<option value = "2">2 stars</option>
				<option value = "3">3 stars</option>
				<option value = "4">4 stars</option>
				<option value = "5">5 stars</option>
			</select>
			<label for="reviewText">Review:</label>
			<textarea class="reviewInputs" name="reviewText" rows="8" cols="80" placeholder="Enter Review..."></textarea>
			<input class="reviewInputs" type="submit" value="Post Review">
		</form>
	</div>
</body>

</html>