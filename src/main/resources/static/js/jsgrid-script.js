$(function() {
	$("#jsGrid").jsGrid({
		height : "auto",
		width : "100%",

		sorting : true,
		paging : false,
		autoload : true,

		controller : {
			loadData : function() {
				var d = $.Deferred();

				$.ajax({
					url : "/arbitrage/",
					dataType : "json"
				}).done(function(response) {
					console.log(response);
					d.resolve(response);
				});

				return d.promise();
			}
		},

		fields : [ {
			name : "buyAt",
			type : "text",
			title : "Buy At"
		}, {
			name : "sellAt",
			type : "textarea",
			title : "Sell At"
		}, {
			name : "buyAtPrice",
			type : "number",
			title : "Buy At Price"
		}, {
			name : "sellAtPrice",
			type : "number",
			title : "Sell At Price"
		}, {
			name : "differencePercentage",
			type : "number",
			title : "Difference In Percentages"
		} ]
	});
});
