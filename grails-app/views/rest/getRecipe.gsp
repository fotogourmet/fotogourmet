<html>
	<body>
<html>
		<head>
		<style>
		body{
		background-color:#000000;
		color:#DCDCDC;
		font-family:"Calibri";
		}
		h1{
		color:#FF8C00;
		text-align:center;
		font-family:"Calibri";
		font-size:250%;
		}
		h2{
		text-align:center;
		}
		h3{
		font-family:"Calibri";
		font-size:110%;
		text-align:left;
		color:#DCDCDC;
		}
		h4{
		font-family:"Calibri";
		font-size:110%;
		text-align:left;
		color:#DCDCDC;
		}
		</style>
		</head>
		
		<body>
	
		<h1>${jsRecipe.nombre}</h1>

		<h2><img src=" ${jsRecipe.imagenPpal} "/></h2>

		<h3><strong>Ingredientes:</strong> ${jsRecipe.ingredientes}</h3> ${jsRecipe.procedimiento} </body>
		</html>
	</body>
</html>