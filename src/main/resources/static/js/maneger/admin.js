Checker = {
	getCheckLink: function (options) {
		options = $.extend({
			checkerServiceUrl: null,
			// todo: pass networkID to check URL
			networkId: null,
			offerId: null,
			checkUrl: null,
			trackingUrl: null,
			platforms: [],
			countries: []
		}, options);

		if(!options.platforms.length) {
			options.platforms = ["2"];
		}

		if(!options.countries.length) {
			options.countries = ["US"];
		}

		if (options.checkUrl && options.platforms.length && options.countries.length) {
			var check_url = options.checkUrl
				.replace("{id}", options.offerId)
				.replace("{tracking}", options.trackingUrl);

			var platform;
			switch (options.platforms[0].toString()) {
				case "2":
					platform = "iphone";
					break;
				case "3":
					platform = "ipad";
					break;
				default:
					platform = "android";
					break;
			}

			return options.checkerServiceUrl + "?url=" + encodeURIComponent(check_url) +
			"&device=" + encodeURIComponent(platform) +
			"&country=" + encodeURIComponent(options.countries[0]);
		} else {
			return null;
		}
	}
};