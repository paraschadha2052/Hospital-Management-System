function test2()
{
	str = document.getElementById("error2").innerHTML;
	console.log(str.indexOf("Date"));
	if(str.indexOf("Date") !== -1)
		{
		document.getElementById('register-form-link').click();
		}

}



