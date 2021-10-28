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
			width:100%;
			position:absolute;
			top:0;
			left:0;
			box-shadow: 0px 5px 3px #aaaaaa;
			padding-left:10px;
			
		}
		.search {
			width:60%;
		}
		.nav-button{
			height:100%;
			display:table-cell;
			text-align:right;
			vertical-align:middle;
			padding-right:20px;
			
		}
	</style>
</head>

<body>
	<div class="appbar">
		<h1 style="color:#ffffff">BookWorms - ${requestScope["pageTitle"]}</h1>
		<form>
			<input class=search type="text" name="search" placeholder="Search for a book...">
			<input type=submit value=Search name=submit>
		</form>
		<div class=nav-button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button style="height:50px;">Profile</button>
		</div>
	</div>
</body>

</html>