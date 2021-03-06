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
		.priceCard{
			float: right;
			padding: 10px;
			border-radius:20px;
			box-shadow: 5px 5px 3px #aaaaaa;
			border: 1px solid #aaaaaa;
		}
		.listings{
           	overflow:hidden;
        }
        .orders{
        	overflow.hidden;
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
                <img src="https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png" height=200 style="float:left;margin-right:10px;">
                
		<h1 style="font-size:60px;">${requestScope["userName"]}</h1>
		<hr>
		<form method="POST">
			<input type=submit style="float:right;height:4em;" value="Logout">
		</form>
		<h1>Username: ${requestScope["username"]}</h1>
        <h1>Email: ${requestScope["userEmail"]}</h1>
        <br><br><br><br><br>
		<hr>
		<div class="orders">
		    <h2>My Orders</h2>
		    <div>
		    	${requestScope["orders"]}
		    </div>
		</div>
		<hr>
		<div class="listings">
			<h2>My Listings</h2>
			${requestScope["myListings"]}
		</div>
	</div>
</body>
</html>