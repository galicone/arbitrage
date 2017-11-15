$(document).ready(function() {
	$('select').material_select();

	// JsGrid initialization
	$(function() {
		$("#jsGrid").jsGrid({
			height : "auto",
			width : "950px",

			sorting : true,
			paging : false,
			selecting : false,
			autoload : true,

			controller : {
				loadData : function() {
					var d = $.Deferred();

					$.ajax({
						url : loadDataUrl,
						dataType : "json"
					}).done(function(response) {
						console.log(response);
						d.resolve(response);
					});

					return d.promise();
				}
			},

			fields : [ {
				name : "type",
				type : "text",
				title : "Currency Pair",
				align : "center"
			}, {
				name : "buyAt",
				type : "text",
				title : "Buy At",
				align : "center"
			}, {
				name : "sellAt",
				type : "textarea",
				title : "Sell At",
				align : "center"
			}, {
				name : "buyAtPrice",
				type : "number",
				title : "Buy At Price",
				align : "center"
			}, {
				name : "sellAtPrice",
				type : "number",
				title : "Sell At Price",
				align : "center"
			}, {
				name : "differencePercentage",
				type : "number",
				title : "Difference In Percentages",
				align : "center"
			} ]
		});
	});

	// Reloading grid after button click
	loadDataUrl = "/arbitrage/";

	$("#btnApplyExchanges").on("click", function(e) {
		if ($('select').val() != "") {
			loadDataUrl = "/arbitrage/selectedExchanges=" + $('select').val();
		}
		$("#jsGrid").jsGrid("loadData");
	});

});