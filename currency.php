<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<title>Currency</title>
</head>
<body>
	<form method="POST" >
		<select name="from" id="from">
			<option value=""selected>From</option>
			<option value="USD">USD</option>
			<option value="BDT">BDT</option>
		</select><br><br>
		<select name="to" id="to">
			<option value="" selected>TO</option>
			<option value="USD">USD</option>
			<option value="BDT">BDT</option>
		</select><br><br>

		<input type="text" id="amount" name="amount" required>

		<!-- <input type="submit" name="Submit"> -->
		<input type="submit" name="Submit" id="myBtn" >
		<!-- <p class="greeting-id">The ID is </p><br>
		<p class="greeting-id2">The ID is </p><br> -->
	 </form>

  
<!-- <?php
	if($_SERVER['REQUEST_METHOD']==="POST") {
		$from=$_POST['from'];
		$to=$_POST['to'];
		

		$amount=$_POST['amount'];
		$json =file_get_contents('http://localhost:8080/5_Tuhin_Git_war_exploded/HelloController/currency/rate/from/'.$from.'/to/'.$to);
        $data = json_decode($json);
        echo "BD Taka : ".($data->rate*$amount);
        echo "\n";
}
?> -->
 <script>
 	$(document).ready(function() {
 		$.ajax({
    	type: 'POST',
        url: "http://localhost:8080/5_Tuhin_Git_war_exploded/HelloController/currency/rate/from/USD/to/BDT"
         //url: "http://localhost:8080/5_Tuhin_Git_war_exploded/HelloController/currency/rate"
        }).then(function(data) {
        	$("#myBtn").click(function(){
        		let rate=data.rate;
                var amount = $("#amount").val();
                alert(rate*amount);
                //document.getElementById("demo").innerHTML=y;
               // $('.greeting-id').append(y);
               // $('.greeting-id2').append(str);
            });
        });
    });

</script>  
</body>
</html>

