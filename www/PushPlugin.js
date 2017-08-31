
var PushPlugin = function() {

};

/*
*	success - success callback this is wrong
*	fail - error callback
*	options
*		.account -  bindaccount name
*/
PushPlugin.prototype.init = function(options,success, fail) {
	if (!options) {
		options = {account:''};
	}
	var params = {
		account: options.account,
	};

	return cordova.exec(success, fail, "PushPlugin", "init", [params]);
};

window.pushPlugin = new PushPlugin();