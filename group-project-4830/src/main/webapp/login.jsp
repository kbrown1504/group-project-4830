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
	<script type="text/javascript">
    function validate_required(field, alerttxt) {
        with (field) {
            if (value == null || value == "") {
                alert(alerttxt);
                return false;
            }
            else {
                return true;
            }
        }
    }

    function validate_Loginform(thisform) {
        with (thisform) {
            if (validate_required(username, "Please enter the username") == false)
            {
                username.focus();
                return false;
            }

            if (validate_required(password, "Please enter the password") == false)
            {
                password.focus();
                return false;
            }
            return true;
        }
    }
</script>
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
			<input type="submit" value="Advanced Search" formaction="advancedSearch">
			</form>
			
		</div>
		<div class=nav-button>
			<button onclick="window.location.href='home'" style="height:50px;">Home</button>
			<button style="height:50px;">Profile</button>
		</div>
	</nav>
	<%
	String msg = "";
 	String uname = request.getParameter("username");
 	String password = request.getParameter("password");
 	if(uname != null && password != null && uname.length() > 0 && password.length() > 0)
 	{
  		Login obj = new Login();
  		boolean flag = obj.validateUserLogin(uname, password);
  		if(flag){
   			request.getRequestDispatcher("account.jsp").forward(request, response);
  		}
  		else{
   			msg = "Invalid username or password";
  		}
 	}
	%>

 <form action="login.jsp" method="post" onsubmit="return validate_Loginform(this)">
  <table width="40%" border="1" align="center">
   <tr>
    <th colspan="2" align="center" valign="top">Please enter login details</th>
   </tr>
   <tr height="50">
    <td valign="middle" align="right">User Name</td>
    <td align="left"><input name="username" size="20" value=""  type="text">
    </td>
   </tr>
   <tr>
    <td valign="middle" align="right">Password</td>
    <td align="left"><input name="password" size="20" value=""  type="password">
    </td>
   </tr>
   <tr height="40">
    <td colspan="2" align="center"><input value="Login" name="B1" type="submit"></td>
   </tr>
  </table>
</body>

</html>