/* MANAGE AUTO FILL FOR BILLING INFORMATION */
var reusecheckbox = document.getElementById('reuse');
reusecheckbox.onclick = function(){
	if(reusecheckbox.checked === true){
		var sFirstName = document.getElementById('sFirstName').value;
		document.getElementById('bFirstName').value = sFirstName;
		var sLastName = document.getElementById('sLastName').value;
		document.getElementById('bLastName').value = sLastName;
		var sAddrLn1 = document.getElementById('sAddrLn1').value;
		document.getElementById('bAddrLn1').value = sAddrLn1;
		var sAddrLn2 = document.getElementById('sAddrLn2').value;
		document.getElementById('bAddrLn2').value = sAddrLn2;
		var sCity = document.getElementById('sCity').value;
		document.getElementById('bCity').value = sCity;
		var sState = document.getElementById('sState').value;
		document.getElementById('bState').value = sState;
		var sZip = document.getElementById('sZip').value;
		document.getElementById('bZip').value = sZip;	
	}
	else{
		var billing = document.getElementById('billing-information');
		var billingTextBoxes = billing.getElementsByTagName("input");
		for(var i=0; i< billingTextBoxes.length; i++){
			if(billingTextBoxes.type="text")
				billingTextBoxes[i].value = "";
		}
	}
}
/* MANAGE NUMBER OF DINERS */
var diners = document.getElementById('diners');
var menu = document.getElementById("menu");
var currentDiners = 1;
diners.onchange = function(){
	var newDiners = diners.value - currentDiners;
	if(newDiners > 0){
		//ADD DINERS
		var firstDiner = document.getElementById("diner-1");
		for(var i=currentDiners; i< diners.value; i++){
			var temp = firstDiner.cloneNode(true);
			temp.setAttribute("id","diner-" + (parseInt(i)+1));
			temp.getElementsByTagName("legend")[0].textContent = "Diner:" + (parseInt(i)+1);
			// ADD APPETIZERS //
			var appetizers = temp.getElementsByClassName("appetizer");
			for(var j=0;j<appetizers.length; j++){
				appetizers[j].name = appetizers[j].name.slice(0,-1) + (parseInt(i)+1);
				appetizers[j].checked = false;
				var newid = appetizers[j].id.slice(0,-1) + (parseInt(i)+1);
				var labels = temp.getElementsByTagName('label');
				for(var k=0; k<labels.length; k++){
					if(labels[k].htmlFor === appetizers[j].id)
						labels[k].htmlFor = newid;
				}
				appetizers[j].id = newid;
			}
			// ADD ENTREES //
			var entrees = temp.getElementsByClassName("entree");
			for(var j=0;j<entrees.length; j++){
				entrees[j].name = entrees[j].name.slice(0,-1) + (parseInt(i)+1);
				entrees[j].checked = false;
				var newid = entrees[j].id.slice(0,-1) + (parseInt(i)+1);
				var labels = temp.getElementsByTagName('label');
				for(var k=0; k<labels.length; k++){
					if(labels[k].htmlFor === entrees[j].id)
						labels[k].htmlFor = newid;
				}
				entrees[j].id = newid;
			}
			// ADD DESSERT //
			var desserts = temp.getElementsByClassName("dessert");
			for(var j=0;j<desserts.length; j++){
				desserts[j].name = desserts[j].name.slice(0,-1) + (parseInt(i)+1);
				desserts[j].checked = false;
				var newid = desserts[j].value.slice(0,-1) + (parseInt(i)+1);
				var labels = temp.getElementsByTagName('label');
				for(var k=0; k<labels.length; k++){
					if(labels[k].htmlFor === desserts[j].id)
						labels[k].htmlFor = newid;
				}
				desserts[j].id = newid;
			}
			menu.appendChild(temp);
		}
	}
	else if (newDiners < 0){
		//REMOVE DINERS
		allDiners = document.getElementsByClassName('diner');
		for(var i=currentDiners-1; i >= diners.value; i--){
			menu.removeChild(allDiners[i]);
		}
	}
	currentDiners = diners.value;
	changePrice();
}

function changePrice (){
	var inputs = document.getElementsByTagName("input");
	for(var i=inputs.length-1; i >0 ;i--){
		if(inputs[i].type === "submit"){
			inputs[i].value = "Submit Order - $" + (diners.value*14.95).toFixed(2);
			break;
		}
	}
}
changePrice ();
