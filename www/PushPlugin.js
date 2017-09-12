
var PushPlugin = function() {};

PushPlugin.prototype.init = function(options,success, fail) {
	if (!options) {
		options = {account:''};
	}
	var params = {
		account: options.account,
	};

	return cordova.exec(success, fail, "PushPlugin", "init", [params]);
};

PushPlugin.prototype.unbind = function(success, fail) {
	return cordova.exec(success, fail, "PushPlugin", "unbind", [{}]);
};

PushPlugin.prototype.initstate = function(success, fail) {
	return cordova.exec(success, fail, "PushPlugin", "initstate", [{}]);
};

PushPlugin.prototype.registerNotify = function(success, fail) {
	return cordova.exec(success, fail, "PushPlugin", "registerNotify", [{}]);
};

window.pushPlugin = new PushPlugin();