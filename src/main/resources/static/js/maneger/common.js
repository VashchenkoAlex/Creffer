$(function () {
	WinMove();

	// sidebar minicharts
	$(".minibar-traffic").peity("bar", {
		fill: ["#1ab394"],
		width: 69,
		height: 20
	});
	$(".minibar-conversions").peity("bar", {
		fill: ["#EC4758"],
		width: 69,
		height: 20
	});
	$(".minibar-revenue").peity("bar", {
		fill: ["#F8AC59"],
		width: 69,
		height: 20
	});

	// Clock
	(function () {
		var serverTime = window.serverTime;
		var localTime = +Date.now();
		var timeDiff = serverTime - localTime;
		var el = $('#server-time');

		var td = function (value) {
			return value < 10 ? '0' + value : value;
		};
		var ut = function () {
			var date = new Date(+Date.now() + timeDiff);
			el.html(
				date.getHours() + ':' +
				td(date.getMinutes()) + ':' +
				td(date.getSeconds())
			);
		};
		ut();
		setInterval(ut, 1000);
	})();


	// New messages alert
	/*
	 setTimeout(function () {
	 $.gritter.add({
	 title: 'You have new messages',
	 text: '<a href="index.php?page=notifications" style="color:#F8AC59">Click here to read.</a>',
	 time: 12000
	 });
	 }, 2000);
	 */


	// Remote content loading for ajax modal
	$('body').on("click", '[data-toggle="ajaxModal"]', function (e) {
		if (!$(e.target).hasClass("skip-modal")) {
			$("#ajaxModal").remove();
			e.preventDefault();
			var $this = $(this), $remote = $this.data('remote') || $this.attr('href'),
				$modal = $('<div class="modal fade" id="ajaxModal"><div class="modal-body"></div></div>');
			$('body').append($modal);
			$modal.modal({backdrop: true, keyboard: true});
			$modal.load($remote);
		}
	});

	window.bsConfirm = function (options, p1) {
		if ($.isFunction(options)) {
			options = {
				onConfirm: options
			};
		} else if ($.type(options) == "string") {
			options = {
				message: options
			};
			if ($.isFunction(p1)) {
				options.onConfirm = p1;
			}
		}

		options.title = options.title || "Confirm your action";
		options.message = options.message || "Are you sure?";
		options.successText = options.successText || "OK";
		options.cancelText = options.cancelText || "Cancel";
		options.onConfirm = options.onConfirm || $.noop;

		var $header = $('<h4 class="modal-header">');
		var $title = $('<h4 class="modal-title">').append(options.title);
		var $body = $('<div class="modal-body">');
		var $footer = $('<div class="modal-footer">');
		var $content = $('<div class="modal-content">');

		$header.append('<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
		$header.append($title);
		$content.append($header);

		$body.append('<h3><i class="fa fa-question-circle fa-4x fa-pull-left fa-fw hidden-xs"></i>'
		+ "<p style='display:inline-block;vertical-align:top;margin-top:5px;margin-left:20px;font-weight:normal; width:75%'>"
		+ options.message + "</p></h3>");
		$content.append($body);

		var $cancelBtn = $('<button type="button" class="btn btn-white" data-dismiss="modal">').append(options.cancelText);
		var $okBtn = $('<button type="button" class="btn btn-primary">').append(options.successText);

		$footer.append($cancelBtn);
		$footer.append($okBtn);
		$content.append($footer);

		var $modal = $("<div class='modal fade'>")
			.append($('<div class="modal-dialog">')
				.append($content));

		$okBtn.click(function (e) {
			options.onConfirm(e);
			if (!e.isDefaultPrevented()) {
				$modal.modal('hide');
			}
		});

		$('body').append($modal);
		$modal.modal({backdrop: true, keyboard: true});
	};

	// Animation FX for related offers
	$('.related-offer').each(function () {
		animationHover(this, 'pulse');
	});

	$(document).on('focus', 'input[type=text].select-on-focus', function () {
		$(this).select();
	});

	window.applyPagination = function () {
		$(".auto-pagination").each(function () {
			var self = $(this);
			var count = parseInt(self.attr('data-count'));
			var size = parseInt(self.attr('data-size'));
			var current = parseInt(self.attr('data-current'));

			var total_pages = Math.ceil(count / size);

			if(!total_pages) {
				self.hide();
				return;
			} else {
				self.show();
			}

			var pages = [];
			var f = Math.max(current - 2, 1);
			var l = f + 4;
			for (var p = f; p <= l; p++) {
				if (p <= total_pages) {
					pages.push(p);
				}
			}

			if (pages[pages.length - 1] < total_pages) {
				if (total_pages - pages[pages.length - 1] > 1) {
					pages.push('...');
				}
				pages.push(total_pages);
			}

			if (pages[0] > 1) {
				if (pages[0] > 2) {
					pages.unshift('...');
				}
				pages.unshift(1);
			}

			var params = [];
			$.each(self.parents('form').serializeArray(), function () {
				params.push(this.name + '=' + (this.name == 'page' ? '{page}' : encodeURIComponent(this.value)));
			});
			var url = '?' + params.join('&');

			var button = self.find('.page-button').remove().first().removeClass("disabled active");
			$.each(pages, function (k, v) {
				var b = button.clone(true);
				b.find('a').text(v).attr('href', url.replace('{page}', v));
				if (v == current) {
					b.addClass('active');
				}
				if (v == '...') {
					b.addClass('disabled');
					b.find('a').attr('href', '');
				}
				b.insertBefore(self.find('.next-page'));
			});


			if (current == 1) {
				self.find('.prev-page').addClass('disabled');
			} else {
				self.find('.prev-page').removeClass('disabled');
			}

			if (current == total_pages) {
				self.find('.next-page').addClass('disabled');
			} else {
				self.find('.next-page').removeClass('disabled');
			}

			self.find('.prev-page a').attr('href', url.replace('{page}', Math.max(current - 1, 1)));
			self.find('.next-page a').attr('href', url.replace('{page}', Math.min(current + 1, total_pages)));

		});

		var size = $(".auto-pagination").attr('data-size');
		var params = [];
		$.each($(".size-selector").parents('form').serializeArray(), function () {
			if (this.name == "page") this.value = 1;
			params.push(this.name + '=' + (this.name == 'size' ? '{size}' : encodeURIComponent(this.value)));
		});
		var url = '?' + params.join('&');

		$(".size-selector a").each(function () {
			if ($(this).data("size") == size) {
				$("i", this).show();
			}

			$(this).attr("href", url.replace("{size}", $(this).data("size")));
		});
	};

	window.applyPagination();
	//$(".auto-pagination").fixOnScreen();

	jQuery.fn.attent = function (options) {
		options = $.extend({
			keep: false,
			endColor: "inherit"
		}, options);

		var e = $(this);

		if (options.keep) {
			options.endColor = "#EBFFEF";
		}

		e.css({backgroundColor: "#93FFA4"}).stop().animate({backgroundColor: options.endColor}, 2000, options.keep ? $.noop : function () {
			e.css({backgroundColor: ""})
		});
	};

	jQuery.fn.tristate = function (v) {
		$(this).each(function () {

			var i = $(this).find("i");
			var input = $(this).find("input[type=hidden]");

			if (v === undefined) {
				var h = function () {
					var i = $(this).find("i");
					var input = $(this).find("input[type=hidden]");

					i.removeClass(i.data("unchecked-class"));
					i.removeClass(i.data("tristate-class"));
					i.removeClass(i.data("checked-class"));
					$(this).removeClass("tristate-unchecked");
					$(this).removeClass("tristate-checked");
					$(this).removeClass("tristate-tristate");

					if (input.data("checked-value") == input.val()) {
						input.val(input.data("unchecked-value"));
						i.addClass(i.data("unchecked-class"));
						$(this).addClass("tristate-unchecked");
					} else if (input.data("unchecked-value") == input.val()) {
						input.val(input.data("tristate-value"));
						i.addClass(i.data("tristate-class"));
						$(this).addClass("tristate-tristate");
					} else {
						input.val(input.data("checked-value"));
						i.addClass(i.data("checked-class"));
						$(this).addClass("tristate-checked");
					}
				};
				$(this).on("click", h);
			}
			else {
				input.val(v);
			}

			i.removeClass(i.data("unchecked-class"));
			i.removeClass(i.data("tristate-class"));
			i.removeClass(i.data("checked-class"));
			$(this).removeClass("tristate-unchecked");
			$(this).removeClass("tristate-checked");
			$(this).removeClass("tristate-tristate");

			if (input.data("checked-value") == input.val()) {
				input.val(input.data("checked-value"));
				i.addClass(i.data("checked-class"));
				$(this).addClass("tristate-checked");
			} else if (input.data("unchecked-value") == input.val()) {
				input.val(input.data("unchecked-value"));
				i.addClass(i.data("unchecked-class"));
				$(this).addClass("tristate-unchecked");
			} else {
				input.val(input.data("tristate-value"));
				i.addClass(i.data("tristate-class"));
				$(this).addClass("tristate-tristate");
			}
		});
	};

	$(".tristate-switch").tristate();

	$(".tristate-switch2").each(function () {
		var h = function () {
			var i = $(this).find("i");
			var input = $(this).find("input[type=hidden]");

			i.removeClass(i.data("unchecked-class"));
			i.removeClass(i.data("tristate-class"));
			i.removeClass(i.data("checked-class"));
			$(this).removeClass("tristate-unchecked");
			$(this).removeClass("tristate-checked");
			$(this).removeClass("tristate-tristate");

			if (input.data("checked-value") == input.val()) {
				input.val(input.data("unchecked-value"));
				i.addClass(i.data("unchecked-class"));
				$(this).addClass("tristate-unchecked");
			} else if (input.data("unchecked-value") == input.val()) {
				input.val(input.data("tristate-value"));
				i.addClass(i.data("tristate-class"));
				$(this).addClass("tristate-tristate");
			} else {
				input.val(input.data("checked-value"));
				i.addClass(i.data("checked-class"));
				$(this).addClass("tristate-checked");
			}
		};
		$(this).on("click", h);

		var i = $(this).find("i");
		var input = $(this).find("input[type=hidden]");
		if (input.data("checked-value") == input.val()) {
			input.val(input.data("checked-value"));
			i.addClass(i.data("checked-class"));
			$(this).addClass("tristate-checked");
		} else if (input.data("unchecked-value") == input.val()) {
			input.val(input.data("unchecked-value"));
			i.addClass(i.data("unchecked-class"));
			$(this).addClass("tristate-unchecked");
		} else {
			input.val(input.data("tristate-value"));
			i.addClass(i.data("tristate-class"));
			$(this).addClass("tristate-tristate");
		}
	});

	$(".datepicker").datepicker({
		format: 'yyyy-mm-dd'
	});

	// detect browser scroll bar width
	var scrollDiv = $('<div class="scrollbar-measure"></div>')
			.appendTo(document.body)[0],
		scrollBarWidth = scrollDiv.offsetWidth - scrollDiv.clientWidth;

	$(document)
		.on('hidden.bs.modal', '.modal', function (evt) {
			// use margin-right 0 for IE8
			$(document.body).css('margin-right', '');
		})
		.on('show.bs.modal', '.modal', function () {
			// When modal is shown, scrollbar on body disappears.  In order not
			// to experience a "shifting" effect, replace the scrollbar width
			// with a right-margin on the body.
			if ($(window).height() < $(document).height()) {
				$(document.body).css('margin-right', scrollBarWidth + 'px');
			}
		});
});
