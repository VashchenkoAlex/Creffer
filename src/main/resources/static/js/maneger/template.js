/**
 * 1) Add data-field="field_name" attribute for each element which needs to be handled. Unique names is not required,
 *      but all elements with same names will be processed identically.
 *
 * 2) Call fields() method on parent element containing all elements marked with data-field attribute
 *      ex: $("div.form").fields({...}) or $("body").fields({...})
 *
 * 3) The one argument passed to fields() method is a configuration object. The structure is:
 *      { field_name_1: {options}, field_name_2: {options}, ... }
 *      where field_name is a name you gave to an element with data-field attribute.
 *
 *
 * Properties list:
 * show - calls show() if value is true or hide() if not. Can be used to show one element in group and hide others, see example.
 * hide - calls hide() if value is true or show() if not.
 * *** if element hides by show/hide properties, then all other configuration properties are not processed.
 * when - skips the whole configuration if value is equal to false.
 * child - applies configurations for child elements by selector, see examples.
 * init - callback function will be applied to element before processing. Equal to specifying the function as config.
 * widget - applies custom widget from jQuery widget factory with options. See example for usage.
 *
 * Following properties are mapped to jQuery methods with same name:
 * text - This is default property and can be omitted, if no other properties needed (see examples).
 * html
 * attr
 * css
 * addClass
 * removeClass
 * on
 * off
 *
 * Examples:
 * var data = {
 *      first_name: "John",
 *      last_name: "Doe"
 *      age: 25,
 *      profile_link: "http://...",
 *      registered_date: "2015-10-09",
 *      sex: "M"
 * };
 * $("div#my").fields({
 *      // apply to element's text:
 *      name: data.first_name + " " + data.last_name,
 *      // or
 *      name: {
 *          text: data.first_name + " " + data.last_name
 *      },
 *
 *      // apply as HTML:
 *      age: {
 *          html: "Age is <b>" + data.age + "</b>"
 *      },
 *
 *      // add 'when' property to any configuration to skip it, if 'when' is false:
 *      age: {
 *          html: "Age is <b>" + data.age + "</b>",
 *          when: data.age > 18
 *      },
 *
 *      // apply attributes:
 *      profile: {
 *          attr: {
 *              href: data.profile_link,
 *              title: "User's profile"
 *          }
 *      },
 *
 *      // apply css styles:
 *      field: {
 *          css: {
 *              color: "red",
 *              backgroundColor: "white"
 *          }
 *      },
 *
 *      // adding/removing css classes:
 *      field: {
 *          addClass: data.warning ? "text-danger" : null,
 *          removeClass: data.warning ? "text-bold" : null
 *      },
 *
 *      // show/hide element depending on condition:
 *      registered: {
 *          show: data.registered_date != null,
 *          text: data.registered_date
 *      },
 *
 *      // apply configurations for child elements
 *      field: {
 *          child: {
 *              "span.address": {
 *                  text: ...,
 *                  show: ...
 *                  ...
 *              },
 *              "div#myChildNode": {
 *                  attr: {
 *                      ....
 *                  }
 *              }
 *          }
 *      },
 *
 *      // show one element in group and hide others give same data-field name for all of these and
 *      // put different names to data-field-option attribute:
 *      // <span data-field="sex" data-field-option="male">Male</span>
 *      // <span data-field="sex" data-field-option="female">Female</span>
 *      sex: {
 *          show: {
 *              male: data.sex == "M",
 *              female: data.sex == "F"
 *          }
 *      },
 *
 *      // when you need to do something unusual, pass function as value ('this' will be a current element):
 *      warning: function() {
 *          if(!data.first_name || !data.last_name) {
 *              this.html("User profile is not filled properly!");
 *          }
 *      }
 *
 *      // same as before, but if you want to use initialization together with using of other properties
 *      warning: {
 *          init: function() {
 *              $this.html(...)
 *          }
 *      },
 *
 *      // apply widget
 *      field: {
 *          widget: {
 *              name: "checkbox",
 *              options: {
 *                  ...
 *              }
 *          }
 *      }
 *
 * });
 */

(function ($) {

	$.fn.fields = function (options, bind) {

		var mappedProps = [
			"text",
			"html",
			"attr",
			"css",
			"addClass",
			"removeClass"
		];

		function processField($field, config) {
			if ($.isFunction(config)) {
				$.proxy(config, $field)();
			} else if ($.type(config) == "string") {
				$field.text(config);
			} else if ($.isPlainObject(config)) {

				if (config.hasOwnProperty("when")) {
					if (!config.when) {
						return;
					}
				}

				if (config.hasOwnProperty("show")) {
					if ($.isPlainObject(config.show)) {
						$.each($field, function (i, e) {
							var $e = $(e), optionName = $e.attr("data-field-option");
							if (config.show.hasOwnProperty(optionName)) {
								if (config.show[optionName]) {
									$e.show();
								} else {
									$e.hide();
								}
							}
						});
					} else if (config.show) {
						$field.show();
					} else {
						$field.hide();
						return;
					}
				}

				if(config.hasOwnProperty("hide")) {
					if(config.hide) {
						$field.hide();
						return;
					} else {
						$field.show();
					}
				}

				$.each(mappedProps, function (i, v) {
					if(config[v]) {
						$field[v](config[v]);
					}
				});

				if(config.on) {
					$.each(config.on, function (events, callback) {
						$field.off(events);
						$field.on(events, callback);
					});
				}

				if(config.off) {
					$.each(config.off, function (events, callback) {
						$field.off(events, callback);
					});
				}

				if(config.child) {
					$.each(config.child, function (selector, fields) {
						processField($(selector, $field), fields);
					});
				}

				if(config.init) {
					$.proxy(config.init, $field)();
				}

				if(config.widget) {
					if($.isFunction($field[config.widget.name])) {
						$field[config.widget.name](config.widget.options);
					} else {
						throw new Error("Unable to find widget \"" + config.widget.name + "\"");
					}
				}
			}
		}


		function processAll(context, options)
		{
			var $this = $(context);
			$("[data-field-option]", $this).hide();

			$.each(options, function (name, config) {
				var $field = $("[data-field='" + name + "']", $this);

				if ($field.length) {
					processField($field, config);
				}
			});
		}


		var self = this;

		if(arguments.length == 1 && $.isPlainObject(arguments[0])) {
			// simple mode
			return this.each(function () {
				processAll(self, options);
			});
		} else if(arguments.length == 2 && $.isPlainObject(arguments[0]) && $.isFunction(arguments[1])) {
			// bind mode init
			console.assert($.isPlainObject(options), "First parameter 'model' should be an object");
			console.assert($.isFunction(bind), "Second parameter 'bind' should be a function");

			model = options;
			options = bind(model);

			return this.each(function () {
				processAll(self, options);
				self.update = function (model) {
					options = bind(model);
					processAll(self, options);
				};
			});
		}

		throw new Error('Bad arguments');
	};
})(jQuery);