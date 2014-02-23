if(window.Leetop){
	window.Leetop = undefined;
}
window.Leetop = {
	
};

Leetop.Bootstrap = {
	
	SHORTCUTMARK : "<link rel='shortcut icon' href='{url}' />",
	
	CSSMARK : "<link rel='stylesheet' id='{id}' type='text/css' href='{url}' />",
	
	JAVASCRIPTMARK : "<script type='text/javascript' src='{url}' onload='Leetop.Bootstrap.onScriptLoad()'></script>",
	
	pendFiles : 0,
	
	onScriptLoad : function(){
		Leetop.Bootstrap.pendFiles--;
		if(Leetop.Bootstrap.pendFiles == 0){
			Leetop.Bootstrap.loadCallback.call(this);
		}
	},
	
	resTypes : {
		CSS : 'css',
		JAVASCRIPT : 'javascript',
		SHORTCUT : 'shortcut'
	},
	
	loadRequires : function(res){
		for(var i = 0;i < res.length;i++){
			var resource = res[i];
			if(resource.type == Leetop.Bootstrap.resTypes.CSS){
				Leetop.Bootstrap.loadCSS(resource);
			}else if(resource.type == Leetop.Bootstrap.resTypes.JAVASCRIPT){
				Leetop.Bootstrap.loadJavaScript(resource);
				
			}else if(resource.type == Leetop.Bootstrap.resTypes.SHORTCUT){
				Leetop.Bootstrap.loadShortcut(resource);
			}
		}
	},
	
	loadCSS : function(css){
		document.write(Leetop.Bootstrap.CSSMARK.replace('{id}',css.id).replace('{url}',css.url));
	},
	
	loadJavaScript : function(script){
		Leetop.Bootstrap.pendFiles ++ ;
		var js = document.createElement('script');
		js.src = script.url;
		js.onload = Leetop.Bootstrap.onScriptLoad;
		js.onreadystatechange = function(){
			if(js.readyState == 'loaded'){
				Leetop.Bootstrap.onScriptLoad();
			}
		};
		document.getElementsByTagName('head')[0].appendChild(js);
		//document.write(Leetop.Bootstrap.JAVASCRIPTMARK.replace('{url}',script.url));
	},
	
	loadShortcut : function(shorcut){
		document.write(Leetop.Bootstrap.SHORTCUTMARK.replace('{url}',shorcut.url));
	}
};
