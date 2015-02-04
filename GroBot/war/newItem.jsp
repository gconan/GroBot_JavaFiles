
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Longhorn Bazaar</title>
<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">
	function getCookie(cname) {
	    var name = cname + "=";
	    var ca = document.cookie.split(';');
	    for(var i=0; i<ca.length; i++) {
	        var c = ca[i];
	        while (c.charAt(0)==' ') c = c.substring(1);
	        if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
	    }
	    return "";
	}
	
	function checkCookie() {
	    var email=getCookie("email");
	    if (email=="") {
	        window.location.assign("/index.html")
	    }else{
	        
	    }
	}

	function checkInput(ob) {
  		var invalidChars = /[^0-9.]/gi
  		if(invalidChars.test(ob.value)) {
            ob.value = ob.value.replace(invalicChars,"");
      	}
	}

</script>
</head>
<body>
<script>
	checkCookie();
</script>
<div id="wrapper">
	<div id="wrapper2">
		<div id="header" class="container">
			<div id="logo">
			<a href="index.html">
				<h1>Longhorn Bazaar</h1><br />
				<p>Helping Longhorns trade</p>
			</a>
			</div>
			<div id="menu">
				<ul>
					<li><a href="buy.jsp">Buy</a></li>
					<li><a href="sell.jsp">Sell</a></li>
					<li><a href="activities.jsp">Activities</a></li>
					<li><a href="/logout">Logout</a></li>
					
				</ul>
			</div>
		</div>
		<div id="page">
		  <div id="content">
				<div class="post">
                <center>
					<h2 class="title"><span style="font-size: 24pt">Please tell us about your item for sale below:</span></h2></center>
				</div>
				
				
				<div style=clear: both; color: "#000000">&nbsp;
				  	<%BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();%>	 
                  <div class="contentbox" id="contentbox">
                    <p>
                     
                     <form action="<%= blobstoreService.createUploadUrl("/addItem") %>" method="post" enctype="multipart/form-data">
                        <div><center>
					    Title:<br><textarea name="name" rows="1" cols="128" maxlength="1024" placeholder="Put a title here!" ></textarea><br>
                        Description:<br><textarea name="description" rows="6" cols="128" maxlength="650" placeholder="Put a short description of your item here. For more tips visit our help page." ></textarea><br>
						Asking Price:<br><textarea name="price" onkeyup="checkInput(this)" rows="1" cols="128" maxlength="10" placeholder="How much are you asking for this item?" ></textarea><br>
						Upload a Picture: <input type="file" name="myFile"/> <br>
        						<input type="submit" value="Submit"/>
						</center></div>
					 </form>
					</p>
                  </div>
                  
            </div>
			</div>
		</div>
	   
</div>
     
<div id="footer-content" valign="bottom">
	<div id="footer">
		<p>This space can be used for more links or whatever we want. </p>
	</div>
</div>
</div>
</body>
</html>

