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
		.createText 
		{
			width:230px;
			margin-left:5px;
		}
		.createNum
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
		.createPane
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
		<h1 style="color:#ffffff; margin:10px;">BookWorms - Create a Book Listing</h1>
		<div class=nav-button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button onclick="window.location.href='User'" style="height:50px;">Account</button>
			<button onclick="window.location.href='Cart'" style="height:50px;">Cart</button>
		</div>
	</nav>
	
	<div class="createPane">
		<h2>Enter the Following Information About Your Book</h2>
		<form action="createListing" method="POST">
			<pre>Title: <input class="createText" type="text" name="title"></pre> <br />
			<pre>Author: <input class="createText" type="text" name="author"></pre> <br />
			<pre>ISBN:  <input class="createText" type="text" name="isbn"></pre> <br />
			<pre>Price: <input class="createNum" type="text" name="price"> </pre> <br />
			<pre>Condition: <select name="condition" id="condition">
								<option value="5">Like New</option>
								<option value ="4">Very Good</option>
								<option value ="3">Good</option>
								<option value ="2">Average</option>
								<option value ="1">Poor</option>
							</select></pre> <br />
			<pre>Additional Information: <input class="createText" type="text" name="addinfo"></pre> <br />
			<input type="submit" value="Submit"/>
		</form>
		<hr>
		<br>${message}
	</div>
	
</body>

</html>