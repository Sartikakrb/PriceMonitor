$(document).ready(function() {
    fillProductIdentity();
    fillProductPriceChart();

});

function fillProductIdentity() {
    //get id product
    var urlPath = window.location.href;
    var idProduct = urlPath.split('=')[1];

    $.get("/api/product/detail",{id:idProduct}, function(data){
            if(data.status==600){
              document.getElementById("name").textContent=data.data.name;document.getElementById("current_price").textContent=data.data.current_price;
              document.getElementById("url").textContent=data.data.url;
              $('#description').html(data.data.description);
            } else {
               document.getElementById("name").textContent='-';
               document.getElementById("description").textContent='-';
               document.getElementById("current_price").textContent='-';
               document.getElementById("url").textContent='-';
               console.log('Sorry. The API of this system seems in trouble.');
            }
    });
}

function fillProductPriceChart() {
    var options1 = {
    	animationEnabled: true,
    	title: {
    		text: "Spline Chart using jQuery Plugin"
    	},
    	axisX: {
    		labelFontSize: 14
    	},
    	axisY: {
    		labelFontSize: 14
    	},
    	data: [{
    		type: "spline", //change it to line, area, bar, pie, etc
    		dataPoints: [
    			{ y: 10 },
    			{ y: 6 },
    			{ y: 14 },
    			{ y: 12 },
    			{ y: 19 },
    			{ y: 14 },
    			{ y: 26 },
    			{ y: 10 },
    			{ y: 22 }
    		]
    	}]
    };

    $("#chartContainer1").CanvasJSChart(options1);
}