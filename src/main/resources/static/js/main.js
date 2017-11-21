$(document).ready(
		function() {
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
					onDataLoaded : function(args) {
						colorProfit();
					},
					onItemUpdated : function(args) {
						colorProfit();
					},
					onOptionChanged : function(args) {
						colorProfit();
					},
					onPageChanged : function(args) {
						colorProfit();
					},
					onRefreshed : function(args) {
						colorProfit();
					},
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

			$("#btnApplyExchanges").on(
					"click",
					function(e) {
						loadDataUrl = "/arbitrage?exchanges="
								+ $('select[name="exchangesDropdown"]').val()
								+ "&profitPercentage="
								+ $('select[name="profitPercentageDropdown"]')
										.val();
						$("#jsGrid").jsGrid("loadData");
					});

			// Change color of profit column
			function colorProfit() {
				$('.jsgrid-table tr td:last-child').each(function() {
					if ($(this).text() > 2 && $(this).text() < 5)
						$(this).css('background-color', '#dbffe2');
					if ($(this).text() > 5)
						$(this).css('background-color', '#88f79d');
				});
			}

		});