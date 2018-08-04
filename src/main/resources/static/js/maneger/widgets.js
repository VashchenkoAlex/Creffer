$.widget("ui.terms", {
	options: {
		external_id: null,
		offer_id: null
	},

	_create: function () {
		var $this = this.element;
		$this.addClass("label label-info");

		$this.attr({
			title: "Offer has terms to accept"
		});

		$this.css({
			padding: "0 4px",
			cursor: "pointer"
		});

		$this.attr("data-toggle", "ajaxModal");
		$this.attr("data-remote", "/offers/external/terms?id=" + this.options.external_id);
	}
});

$.widget("ui.checkbox", {
	options: {
		id: null,
		name: null,
		value: null,
		attrs: {},
		on: {}
	},

	_create: function () {
		var $this = this.element;
		var $el = $('<div><label><input type="checkbox"/></label></div>');
		var $input = $("input", $el);

		// copy attributes from template to element
		$($this[0].attributes).each(function () {
			$el.attr(this.nodeName, this.nodeValue);
		});

		$el.addClass("checkbox i-checks");

		$input.attr(this.options.attrs);
		$input.attr({
			id: this.options.id,
			name: this.options.name,
			value: this.options.value
		});


		var $check = $el.iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green'
		});

		$.each(this.options.on, function (event, handler) {
			$check.on(event, handler);
		});

		$this.replaceWith($el);
	}
});


$.widget("ui.countryList", {
	options: {
		codes: [],
		all: {},
		limit: 3,
		on: {}
	},

	_create: function () {
		var $this = this.element;
		var options = this.options;

		$.each(options.codes, function (i, code) {
			var lbl = $("<span class='label'><a></a></span>");
			if (i >= options.limit) {
				lbl.hide();
				lbl.addClass("geocut");
			}
			lbl.attr("title", options.all[code]);
			var $a = lbl.find("a");
			$a.text(code);
			$.each(options.on, function (event, handler) {
				$a.on(event, handler);
			});
			$this.append(lbl).append(" ");
		});

		if (options.codes.length > options.limit) {
			var lbl = $("<span class='label label-success'></span>").text("+ " + (options.codes.length - options.limit) + " more");
			var a = $("<a>").append(lbl);
			$this.append(a).append(" ");
			a.click(function () {
				if (!$(this).data("toggle")) {
					$this.find(".geocut").fadeIn();
					lbl.text("- " + (options.codes.length - options.limit) + " more");
					$(this).data("toggle", "1");
				} else {
					$this.find(".geocut").fadeOut();
					lbl.text("+ " + (options.codes.length - options.limit) + " more");
					$(this).removeData("toggle");
				}
			});
		}
	}
});


$.widget("ui.platformIcon", {
	options: {
		platforms: [],
		mode: "icon" // icon/image
	},

	data: {
		2: {
			id: "iphone",
			title: "iPhone",
			group: "ios",
			image: "/_assets/img/apple_logo.png",
			imageClass: "os-logo os-logo-ios",
			iconClass: "fa fa-apple os-logo os-logo-ios m-r-xs"
		},
		3: {
			id: "ipad",
			title: "iPad",
			group: "ios",
			image: "/_assets/img/apple_logo.png",
			imageClass: "os-logo os-logo-ios",
			iconClass: "fa fa-apple os-logo os-logo-ios m-r-xs"
		},
		4: {
			id: "android",
			title: "Android",
			group: "android",
			image: "/_assets/img/android_logo.png",
			imageClass: "os-logo os-logo-android",
			iconClass: "fa fa-android os-logo os-logo-android m-r-xs"
		},
		5: {
			id: "winphone",
			title: "Windows Phone",
			group: "winphone",
			image: "",
			imageClass: "os-logo os-logo-winphone",
			iconClass: "fa fa-windows os-logo os-logo-winphone m-r-xs"
		},
		9: {
			id: "other_mobile",
			title: "Other mobile",
			group: "other_mobile",
			image: "",
			imageClass: "os-logo os-logo-other-mobile",
			iconClass: "fa fa-mobile os-logo os-logo-other_mobile m-r-xs"
		}
	},

	_create: function () {
		var $this = this.element;
		var self = this;
		var options = this.options;

		var items = {};
		$(options.platforms).each(function (i, v) {
			if (!self.data[v]) {
				console.log("Platform is not specified: " + v);
				return;
			}

			if (items[self.data[v].group]) {
				items[self.data[v].group].title += " + " + self.data[v].title
			} else {
				items[self.data[v].group] = $.extend({}, self.data[v]);
			}
		});

		var icons = [];
		$.each(items, function (group, item) {
			var $el;
			if (options.mode == "image") {
				$el = $("<img>");
				$el.attr("title", item.title);
				$el.attr("src", item.image);
				$el.attr("class", item.imageClass);
			} else if (options.mode == "icon") {
				$el = $("<i>");
				$el.attr("title", item.title);
				$el.attr("class", item.iconClass);
			}
			icons.push($el);
		});

		$this.append(icons);
	}
});

$.widget("ui.popoverWindow", {
	options: {
		trigger: "click", // click/focus/etc.., any valid event name
		align: "left", // left/right/top/bottom
		width: null,
		height: null,
		title: "",
		content: null,
		init: $.noop,
		arrow: true
	},

	show: function () {
		var $popover = $('<div class="popover" style="box-shadow: 0 0 10px rgba(0,0,0,0.5)"></div>');
		$popover.addClass(this.options.align);
		if(this.options.width) {
			$popover.css("width", this.options.width);
		}
		if(this.options.height) {
			$popover.css("height", this.options.height);
		}
        if(this.options.arrow === true) {
            $('<div class="arrow"></div>').appendTo($popover);
        }
		if(this.options.title) {
			$('<h3 class="popover-title">' + this.options.title + '</h3>').appendTo($popover);
		}
		$('<div class="popover-content">').append(this.options.content).appendTo($popover);
		$('body').append($popover);

		var self = this;
		var top, left, options = this.options;
		var h = $popover.height();
		var w = $popover.width();
		var etop = this.element.offset().top + parseInt(this.element.css('padding-top'));
		var eleft = this.element.offset().left + parseInt(this.element.css('padding-left'));

		switch(options.align) {
			case "left":
				top = etop + this.element.height() / 2 - h / (this.options.arrow ? 2 : 20);
				left = eleft - w - 15;
				break;
			case "right": // not tested
				top = etop + this.element.height() / 2 - h / 2;
				left = eleft + w + 5;
				break;
			case "top":
				top = etop - h - 5;
				left = eleft + this.element.width() / 2 - w / 2;
				break;
			case "bottom": // not tested
				top = etop + this.element.height();
				left = eleft + this.element.width() / 2 - w / 2;
				break;
			default:
				return;
		}

		$popover.css("top", top);
		$popover.css("left", left);
		$popover.fadeIn("fast");

		function bodyHandler(e)
		{
			if($(e.target).parents(".popover").length > 0) {
				e.stopPropagation();
				e.preventDefault();
				return;
			}

			$("body").off("click", bodyHandler);

			$popover.fadeOut('fast', function () {
				$popover.remove();
			});
		}

		setTimeout(function () {
			var isMobile = /Android|iPhone|iPad|iPod/i.test(navigator.userAgent);

			$.proxy(options.init, self, $popover)();
			$("body").click(bodyHandler);
			if(!isMobile) {
				$(window).one("resize", bodyHandler);
			} else {
				$(window).one("orientationchange", bodyHandler);
			}
		}, 0);
	},

	_create: function () {
		var $this = this.element;
		var self = this;
		var options = this.options;

		$this.on(options.trigger, function (e) {
			$(".popover").fadeOut("fast");
			self.show();
			e.stopPropagation();
		});
	}
});

$.widget("ui.dropdownMenu", {
	options: {
		items: {},
		trigger: "click", // click/focus/etc.., any valid event name
		align: "auto", // left/right/auto
		verticalAlign: "auto", // left/right/auto
		position: "inside", // inside/outside
		tooltipAt: "left", // left/right
		isSub: false
	},

	item: {
		text: null,
		icon: null,
		href: null,
		newTab: false,
		title: null,
		disabled: false,
		disabledTitle: null,
		hidden: false,
		separator: false,
		header: null,
		init: null,
		linkAttr: {},
		items: {},
		on: {}
	},

	show: function () {
		var options = this.options;
		var $menu = $('<ul class="dropdown-menu">');
		var self = this;

		if(options.isSub) {
			$menu.addClass("dropdown-submenu");
		}

		$.each(options.items, function (id, _item) {
			var item = {};
			$.extend(item, self.item, _item);

			if (item.hidden) {
				return;
			}

			var $li = $('<li><a href="#"><i class="fa fa-fw"></i><span></span></a></li>');
			var $a = $("a", $li);
			var $i = $("i", $a);

			if(item.items && !$.isEmptyObject(item.items)) {
				$a.prepend("<i class='fa fa-caret-right pull-right m-t-xs'></i>");
				$li.dropdownMenu({
					items: item.items,
					isSub: true
				});
			}

			if (item.separator) {
				$li
					.attr("role", "separator")
					.addClass("divider")
					.html("")
				;
			} else if (item.header) {
				$li.addClass("dropdown-header").text(item.text);
			} else {
				$a.attr({
					title: item.title,
					href: item.href,
					"data-placement": options.tooltipAt
				});

				$a.attr(item.linkAttr);

				$("span", $a).text(item.text);

				if (item.icon) {
					$i.addClass(item.icon);
				} else {
					$i.remove();
				}

				if (item.newTab) {
					$a.attr("target", "_blank");
				}

				if (item.disabled) {
					$li.addClass("disabled");
					$a.css({
						opacity: 0.5
					});

					if (item.disabledTitle) {
						$a.attr("title", item.disabledTitle);
					}

					$a.on("click", function (e) {
						e.preventDefault();
						e.stopPropagation();
					});
				} else {
					if (item.on) {
						$.each(item.on, function (event, handler) {
							$a.on(event, function (e) {
								e.preventDefault();
								setTimeout(function () {
									handler(e);
								}, 0);
							});
						});
					}
				}
			}

			if (item.init) {
				$.proxy(item.init, $li)($menu);
			}

			$menu.append($li);
		});

		$("body").append($menu);

		var h = $menu.height();
		var w = $menu.width();
		var top, left;

		if(!options.isSub) {
			top = this.element.offset().top + this.element.height() + parseInt(this.element.css('padding-top')) * 2;
			left = this.element.offset()['left'] + 2;

			// process horizontal position
			if(options.align == "right" || (options.align == "auto" && left + w > $(window).scrollLeft() + $(window).width())) {
				$menu.css({
					left: "auto",
					right: $(window).width() - (this.element.offset().left + this.element.outerWidth())
				});
			} else {
				$menu.css("left", this.element.offset()['left'] + 2);
			}

			// process vertical position
			if(options.verticalAlign == "top" ||
				(options.verticalAlign == "auto" && top + h > $(window).scrollTop() + $(window).height()))
			{
				top = this.element.offset().top - h
			}
		} else {
			// sub menu
			top = this.element.offset().top + parseInt(this.element.css('padding-top')) * 2;
			left = this.element.offset()['left'] + this.element.width() + 2;

			// process horizontal position
			if(left + w > $(window).scrollLeft() + $(window).width()) {
				$menu.css({
					left: "auto",
					right: $(window).width() - this.element.offset().left
				});
			} else {
				$menu.css("left", left);
			}

			// process vertical position
			if(options.verticalAlign == "top" ||
				(options.verticalAlign == "auto" && top + h > $(window).scrollTop() + $(window).height()))
			{
				top = this.element.offset().top - h
			}
		}

		$menu.css("top", top);
		$menu.fadeIn("fast");

		setTimeout(function () {
			$("body").one("click", function () {
				$menu.fadeOut('fast');
			});
			$(window).one("resize", function () {
				$menu.fadeOut('fast');
			});
		}, 0);
	},

	_create: function () {
		var $this = this.element;
		var self = this;
		var options = this.options;

		$this.on(options.trigger, function (e) {
			if(!options.isSub) {
				$(".dropdown-menu").fadeOut("fast");
			} else {
				$(".dropdown-submenu").fadeOut("fast");
			}
			self.show();
			e.stopPropagation();
		});
	}
});

$.widget("ui.approvalIndicator", {
	options: {
		required: null,
		supported: null,
		requested_at: null,
		processing: null
	},

	_spinner: $('<img src="/_assets/spinner1.svg" style="width: 14px; height: 14px">'),

	update: function () {
		var $this = this.element;

		$this.empty();

		var $i;

		if (!this.options.required) {
			return;
		} else if (this.options.processing) {
			$i = this._spinner;
			$this.attr("title", "Requesting approval...");
		} else if (!this.options.supported) {
			$i = $('<span class="fa-stack"><i class="fa fa-gavel fa-stack-1x"></i><i class="fa fa-ban fa-stack-2x text-danger" style="font-size: 23px"></i></span>');
			$this.attr("title", "Automatic approval requests are not supported by advertiser");
		} else if (this.options.requested_at) {
			$i = $('<i class="fa fa-gavel text-navy">');
			$this.attr("title", "Pending approval");
		} else {
			$i = $('<i class="fa fa-gavel text-danger">');
			$this.attr("title", "Approval required");
		}

		$this.append($i);
	},

	_init: function () {
		var $this = this.element;
		var self = this;
		var options = this.options;

		this.update();
	}
});

$.widget("ui.fixOnScreen", {
	options: {},

	_create: function () {
		var $this = this.element;
		var self = this;
		var options = this.options;


		function fix() {
			var parent = $this.parent();
			$(parent).one("resize", function () {
				//unfix();
				//fix();
			});

			var pos = $this.offset();
			var h = $this.height();
			var winHeight = $(window).height();
			var top = winHeight - h;


			//console.log(top)
			//console.log(pos)
			$this.attr("data-orig-margin-top", $this.css("margin-top"));


			$this.css({
				zIndex: 1000000,
				marginTop: 0,
				position: "fixed",
				top: top + h,
				left: pos["left"]
			});

			$this.animate({
				top: top
			})
		}

		function unfix() {
			$this.css({
				marginTop: $this.attr("data-orig-margin-top"),
				position: $this.attr("data-orig-position")
			});
		}

		setTimeout(function () {
			var origTop = $this.offset()["top"];
			var winHeight = $(window).height();
			var viewBottom = $(window).scrollTop() + winHeight;
			$this.attr("data-orig-position", $this.css("position"));

			$(window).on("resize", function () {
				winHeight = $(window).height();
			});

			function activate() {
				viewBottom = $(window).scrollTop() + winHeight;
				//console.log(origTop)
				//console.log(viewBottom)
				if (origTop > viewBottom) {
					if (!$this.attr("data-fixed")) {
						$this.attr("data-fixed", "1");
						fix();
					}
				} else {
					if ($this.attr("data-fixed")) {
						$this.removeAttr("data-fixed");
						unfix();
					}
				}
			}

			$(window).on("scroll", function () {
				activate();
			});

			activate();
		});
	}
});


var ApiAction = new function () {

	var defaultParams = {
		url: null,
		params: {},
		check: function (response) {
			return !!response.success;
		},
		getErrorMessage: function (response) {
			return response.error || "Unknown server error";
		},
		init: $.noop,
		success: $.noop,
		fail: $.noop,
		always: $.noop
	};

	var defaultParamConfig = {
		optional: true,
		type: "string"
	};

	var actions = {};

	this.add = function (name, options) {
		actions[name] = $.extend({}, defaultParams, options);
	};

	this.invoke = function (name, options) {
		if (!actions[name]) {
			throw new Error("Undefined action: " + name);
		}

		var action = $.extend({}, actions[name], options);
		var params = {};
		$.each(action.params, function (n, _config) {
			var value = options.data[n];
			var config = $.extend({}, defaultParamConfig, _config);
			if (value==="undefined" && config.optional==="undefined") {
				throw new Error("Parameter '" + n + "' is required in action '" + name + "'");
			}

			var array = config.type.slice(-2) == "[]";
			var type = array ? config.type.substr(0, config.type.length - 1) : config.type;

			if (array && !$.isArray(value)) {
				throw new Error("Parameter '" + n + "' in action '" + name + "' should be an array");
			}

			switch (type) {
				case "bool":
					if (array) {
						$.each(value, function (i, v) {
							value[i] = v ? 1 : 0;
						});
					} else {
						value = value ? 1 : 0;
					}
					break;

				case "int":
					if (array) {
						$.each(value, function (i, v) {
							if (!$.isNumeric(v)) {
								throw new Error("Parameter '" + n + "' in action '" + name + "' contains non numeric values in array");
							}
							value[i] = parseInt(v);
						});
					} else {
						value = parseInt(value);
					}
					break;

				case "float":
					if (array) {
						if (!$.isNumeric(value)) {
							throw new Error("Parameter '" + n + "' in action '" + name + "' should be a numeric value");
						}
						$.each(value, function (i, v) {
							value[i] = parseFloat(v);
						});
					} else {
						value = parseFloat(value);
					}
					break;

				case "string":
				default:
					if (array) {
						$.each(value, function (i, v) {
							value[i] = v.toString();
							if(config.trim) {
								value[i] = value[i].trim();
							}
						});
					} else {
						value = value.toString();
						if(config.trim) {
							value = value.trim();
						}
					}
					break;
			}

			params[n] = value;
		});

		if ($.proxy(action.init, action)() === false) {
			return;
		}

		params._ts = Date.now();

		$.getJSON(action.url, params)
			.done(function (response) {
				if (!action.check(response)) {
					var errMsg = action.getErrorMessage(response);
					if ($.proxy(action.fail, action)(response) !== false) {
						alert("Error: " + errMsg);
					}
				} else {
					$.proxy(action.success, action)(response);
				}
			})
			.fail(function (request, status, errorThrown) {
				console.log(request);
				if ($.proxy(action.fail, action)({}) !== false) {
					alert("Something gone wrong with this request: " + errorThrown);
				}
			})
			.always(function () {
				$.proxy(action.always, action)();
			});
	};
};

ApiAction.add("requestApproval", {
	url: "/offers/requestapproval",
	params: {
		adv_id: {},
		real_id: {}
	},
	check: function (response) {
		return response.status == "ok";
	}
});

ApiAction.add("schedule", {
	url: "/offers/external/add",
	params: {
		sel: {
			type: "int[]"
		},
		request: {
			type: "bool",
			optional: true
		}
	}
});

ApiAction.add("unschedule", {
	url: "/offers/external/unschedule",
	params: {
		sel: {
			type: "int[]"
		}
	}
});

ApiAction.add("getDescription", {
	url: "/offers/external/desc",
	params: {
		id: {
			type: "int"
		}
	}
});

ApiAction.add("setOfferStatus", {
	url: "/offers/status",
	params: {
		id: {
			type: "int"
		},
		status: {
			type: "string"
		}
	}
});

ApiAction.add("setRequestStatus", {
	url: "/offers/approvals/status",
	params: {
		id: {
			optional: false,
			type: "int"
		},
		status: {
			optional: false,
			type: "int"
		},
		"delete": {
			optional: true,
			type: "int"
		},
		daily_cap: {
			optional: true,
			type: "int"
		},
		pay_percent: {
			optional: true,
			type: "float"
		}
	}
});

ApiAction.add("parseCreatives", {
	url: "/offers/parse_creatives",
	params: {
		offer_id: {
			type: "int"
		}
	},
	check: function (response) {
		return response.status == "ok";
	}
});

ApiAction.add("requestStatus", {
	url: "/offers/external/request_status",
	params: {
		external_id: {
			type: "int"
		},
		status: {
			type: "string"
		}
	}
});