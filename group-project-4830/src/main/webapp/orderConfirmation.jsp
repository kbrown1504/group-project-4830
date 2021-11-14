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
		<h1 style="color:#ffffff; margin:10px;">BookWorms - Order Confirmation</h1>
		<form>
			<input class=searchText type="text" name="search" placeholder="Search for a book...">
			<input type=submit value=Search name=submit>
		</form>
		<div class=nav-button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button style="height:50px;">Profile</button>
		</div>
	</nav>
	
	<div class="filterPane">
		<form action="OrderConfirmation" method="POST">
			<h2>Shipping/Billing Address</h2>
			<pre>Full Name: <input class="searchText" type="text" name="fullName"></pre> <br />
			<pre>Street Address: <input class="searchText" type="text" name="streetAddress"> City: <input class="searchText" type="text" name="city"></pre> <br />
			<pre>State: <input class="searchText" type="text" name="state"> Zip Code: <input class="searchText" type="text" name="zipcode"></pre> <br />
			<hr>
			<h2>Payment Info</h2>
			<pre>Card Number: <input class="searchText" type="text" name="cardNumber"></pre> <br />
			<pre>Name on Card: <input class="searchText" type="text" name="nameOnCard"></pre> <br />
			<pre>Expiration Date: <input class="searchText" type="text" name="expirationDate" placeholder="MM/YYYY"></pre> <br />
			<input type="submit" value="Submit"/>
		</form>
		<hr>
	</div>
</body>

</html>