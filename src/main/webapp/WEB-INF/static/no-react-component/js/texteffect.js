(function($) {
	"use strict";
		var isOn = 0, sets, fx, toAnimate = "#effect", settings = {
			animation:1,
			animationType: "in",
			backwards: false,
			easing: "easeOutQuint",
			speed: 1000,
			sequenceDelay: 100,
			startDelay: 0,
			offsetX: 100,
			offsetY: 50,
			onComplete: fireThis,
			restoreHTML: true
		};

		jQuery(document).ready(function() {
			fx = jQuery("#effect");
			jQuery.cjTextFx(settings);
			jQuery.cjTextFx.animate(toAnimate);
		});
			
		function fireThis() {
						sets = {animation: 8,animationType: "out", restoreHTML: false};
				
		}

})(jQuery);