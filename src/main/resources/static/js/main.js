$(document).ready(
		function() {
			$('select').material_select();

			// JsGrid initialization
			$(function() {
				$("#jsGrid").jsGrid({
					width : "950px",
					filtering: true,
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
						loadData : function(filter) {
							var d = $.Deferred();

							$.ajax({
								url : loadDataUrl,
								dataType : "json"
							}).done(function(response) {
								
								var response = $.grep(response, function(item, idx) {
				                    for (var key in filter) {
				                        var value = filter[key];
				                        if (value.length > 0) {
				                            if (item[key].indexOf(value) == -1)
				                                return false;
				                        }
				                    }
				                    return true;
				                });
								
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
						var profitPercentageDropdown = $('select[name="profitPercentageDropdown"]').val();
						
						if (profitPercentageDropdown == null) {
							profitPercentageDropdown = 0;
						}
						
						loadDataUrl = "/arbitrage?exchanges="
								+ $('select[name="exchangesDropdown"]').val()
								+ "&profitPercentage="
								+ profitPercentageDropdown;
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