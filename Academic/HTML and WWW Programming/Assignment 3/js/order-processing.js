function parseURLParams(url) {
    var queryStart = url.indexOf("?") + 1,
        queryEnd   = url.indexOf("#") + 1 || url.length + 1,
        query = url.slice(queryStart, queryEnd - 1),
        pairs = query.replace(/\+/g, " ").split("&"),
        parms = {}, i, n, v, nv;
    if (query === url || query === "") {
        return;
    }
    for (i = 0; i < pairs.length; i++) {
        nv = pairs[i].split("=");
        n = decodeURIComponent(nv[0]);
        v = decodeURIComponent(nv[1]);
        if (!parms.hasOwnProperty(n)) {
            parms[n] = [];
        }
        parms[n].push(nv.length === 2 ? v : null);
    }
    return parms;
}

var urlString = window.location.href;
var urlParams = parseURLParams(urlString);
var totalprice = 0;
var orders = document.getElementById('orders');
for(var i=1; i <= urlParams.diners; i++){
	order = document.createElement('li');
	order.setAttribute('id',"order-" + i);
	appetizer = document.createElement('span');
	appetizer.setAttribute('id',"appetizer-" + i);
	order.appendChild(appetizer);
	order.appendChild(document.createTextNode(", "));
	entree = document.createElement('span');
	entree.setAttribute('id',"entree-" + i);
	order.appendChild(entree);
	order.appendChild(document.createTextNode(", "));
	dessert = document.createElement('span');
	dessert.setAttribute('id',"dessert-" + i);
	order.appendChild(dessert);
	orders.appendChild(order);
	totalprice = totalprice + 14.95;
}
orders.parentNode.appendChild(document.createTextNode("Your Order Total: $" + totalprice.toFixed(2)));
var menu = [
	{"index":"cheese","value":"Cheese Platter"}, 
	{"index":"sliders","value":"Mini Sliders"},
	{"index":"bruschetta","value":"Bruschetta"},
	{"index":"calamari","value":"Calamari"},
	{"index":"steak","value":"Sirloin Steak"}, 
	{"index":"chicken","value":"Chicken Fajitas"},
	{"index":"salad","value":"House Salad"},
	{"index":"eggplantparm","value":"Eggplant Parmesan"},
	{"index":"tiramisu","value":"Tiramisu"}, 
	{"index":"bostoncream","value":"Boston Cream Pie"},
	{"index":"cheesecake","value":"New York Cheesecake"},
	{"index":"keylime","value":"Key Lime Pie"}
];

for (var key in urlParams) {
	if (urlParams.hasOwnProperty(key)) {
		for(var i= 0;i< menu.length; i++){
			if(urlParams[key][0] === menu[i].index){
				urlParams[key] = menu[i].value;
			}
		}
		document.getElementById(key).textContent += urlParams[key];
	}
}

console.log(urlParams);


