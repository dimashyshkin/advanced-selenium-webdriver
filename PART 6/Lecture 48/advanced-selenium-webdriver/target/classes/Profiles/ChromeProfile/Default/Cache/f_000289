
/*! Agility
*/


if (typeof (window.Agility) == "undefined") {
	window.Agility = new Object();	
}
(function(Agility) {

	//handle ctrl and shift
	Agility.isCtrl = false; 
	Agility.isAlt = false; 
	Agility.isShift = false; 

	$(document).keyup(function (e) { 
		switch (e.which) {
			case 16:
				Agility.isShift=false; 
				break;
			case 17:
				Agility.isCtrl=false; 
				break;
			case  18:
				Agility.isAlt=false; 	
				break;
		}
		
	}).keydown(function (e) { 
		switch (e.which) {
			case 16:
				Agility.isShift=true; 
				break;
			case 17:
				Agility.isCtrl=true; 
				break;
			case 18:
				Agility.isAlt=true; 	
				break;
		}
		
	});
	

	Agility.RegisterNamespace = function(space) {
		/// <summary>
		/// Register a javascript namespace.
		/// </summary>
		/// <param name="space" type="String">The namespace (dot-separated) to register.</param>
		/// <returns type="object">Object representing the created namespace.</returns>

		var spaces = space.split("."),
			root = window;

		for (var i = 0; i < spaces.length; i++) {
			if (typeof (root[spaces[i]]) == "undefined") {
				root = root[spaces[i]] = {};
			} else {
				root = root[spaces[i]];
			}
		}
		
		return root;
	}
	
	var _sessionLevelCacheKey = null;
	Agility.SessionLevelCacheKey = function() {
		/// <summary>
		/// gets a string that is based on the date and time that this page was loaded.  Use this for Client Templating with urls.
		/// </summary>
		
		if (_sessionLevelCacheKey == null) {
			_sessionLevelCacheKey = (new Date()).toString("yyyyMMddHHmmss");
		}
		return _sessionLevelCacheKey;
		
	};

	Agility.ResolveUrl = function(url) {
		/// <summary>
		/// Resolve "~/" to the application's base url. *Requires that global var "Edentity_BaseUrl" has been set.
		/// </summary>
		/// <param name="url" type="String">The URL whose path to resolve.</param>
		/// <returns type="string">String representing the resolved URL.</returns>

		var baseUrl = window.Agility_BaseUrl || "/";
		return url.replace(/^~\//, baseUrl);
	}
	
	
	var _unique = {};
	var _uniqueIndex = 0;
	Agility.UniqueID = function(prePend){
		/// <summary>
		/// Build a Unique ID (unique to this instance).
		/// </summary>
		/// <param name="prePend" type="String">The value to prepend to the Unique portion.</param>
						
		if (prePend == undefined || prePend == null || prePend == "") {
			return _uniqueIndex++;
		} else {
			if (_unique[prePend] == undefined) _unique[prePend] = 0;
			return prePend + _unique[prePend]++;
		}				
	}
	
	Agility.CloneObject = function (what, serializationType) {
		/// <summary>
		/// Clones an object to preserve the original value.
		/// </summary>
		/// <param name="what" type="object">The object to clone.</param>
		
		if (serializationType != undefined && serializationType != null && serializationType != "") {
			this.__type = serializationType;	
		}
		
		for (i in what) {
			if (what[i] == null) {
				this[i] = null;
			} else {
			 
				if (typeof what[i] == 'object') {
					this[i] = new Agility.CloneObject(what[i]);
				} else {
					var val = what[i];				
					this[i] = val;				
				}
			}
		}
	}


	
	Agility.QueryString = function(name, url) {
		/// <summary>
		/// Gets a variable from the query string.  
		/// </summary>
		/// <param name="name" type="String">QueryString variable to retrieve.</param>
		/// <param name="url" type="String">(Optional) the url to take the querystring from.  If this is not present, the current url is used.</param>
		
		if (url == undefined || url == null || url == "") {
			url = location.href;
		}
		
		var index = url.indexOf("?");
		if (index < 1 && index == url.length - 2) return null;
		if (url.indexOf("#") != -1) {
			url = url.substring(0, url.indexOf("#"));
		}
		
		var qstr = url.substring(index + 1, url.length);
		
		var ary1 = qstr.split("&");
		var retValue = null;
		
		jQuery.each(ary1, function(index, q) {
			var ary2 = q.split("=");
			if (ary2.length == 2) {
				if (unescape(ary2[0]).toLowerCase() == name.toLowerCase()) {
				
					retValue = ary2[1];					
					retValue = retValue.replace(/\+/g, "%20");
					retValue = unescape(retValue);
					return false;
				}
			}
		});
		
		return retValue;
		
	}
	
	Agility.ToJSONDate = function(dateObj) {
		/// <summary>
		/// Converts a Date object to a JSON string, and it will account for the timezone offset (\/Date(000000000000)\/). 
		/// </summary>
		/// <param name="dateObj" type="Date">Date object to convert.</param>
				
		dateObj.setMinutes(dateObj.getMinutes() + dateObj.getTimezoneOffset());
				
		var dtStr = dateObj.getFullYear() + "-" + Agility.PadLeft(dateObj.getMonth() + 1, "0") + "-" + Agility.PadLeft(dateObj.getDate() + 1, "0") + " " + Agility.PadLeft(dateObj.getHours(), "0") + ":" + Agility.PadLeft(dateObj.getMinutes(), "0") + ":" + Agility.PadLeft(dateObj.getSeconds(), dateObj.getMilliseconds(), "0");
		
		return "\/Date("+ dtStr +")\/";	
	}
	
	Agility.PadLeft = function(str, padChar) {
		
		str = str + "";
		while (str.length < padChar) {
			str = padChar + str;
		}
		
		return str;
	}
	
	Agility.GetFlashVersion = function() {
		
	   var flashVersion = 0;
	   
		if (window.ActiveXObject) {   
			//ie
			var control = null;   
			try {   
				control = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');   
				if (control) {					
					var version = control.GetVariable('$version').substring(4);   
					version = version.split(',');   
					flashVersion = parseInt(version[0]);
				}   
			} catch (e) {  				
			}   
			
		} else { 
	   
			//non ie
			if (navigator.plugins != null) {
				if (navigator.plugins.length > 0) {
					var flashPlugin = navigator.plugins['Shockwave Flash']; 

						if (typeof flashPlugin == 'object') {
						if (flashPlugin.version) {
							var vstr = flashPlugin.version;
							if (vstr) flashVersion = parseInt(vstr.split(".")[0]);						
						} else {						
							var desc = flashPlugin.description;
							if (desc) flashVersion = parseInt(desc.replace("Shockwave Flash ", "").split(".")[0]);										
						}
					}
				}
			}
		}
		
		return flashVersion;
	}

	
	
	Agility.SetCookie = function(name, value, expires, path, domain, secure ) {
	/// <summary>Sets a cookie based on the name and value provided.</summary>
	/// <param name="name" type="String">The name of the cookie to set the value of.</param>
	/// <param name="value" type="String">The value of the cookie to set.</param>
	/// <param name="expires" type="Date">(Optional) The date that the cookie should expire.</param>
	/// <param name="path" type="String">(Optional) The path of the cookie.</param>
	/// <param name="domain" type="String">(Optional) The domain of the cookie.</param>
	/// <param name="secure" type="Boolean">(Optional) Whether the cookie is secure or not.</param>

		var expires_date = expires;

		document.cookie = name + "=" +escape( value ) +
		( ( expires ) ? ";expires=" + expires_date.toGMTString() : "" ) +
		( ( path ) ? ";path=" + path : "" ) +
		( ( domain ) ? ";domain=" + domain : "" ) +
		( ( secure ) ? ";secure" : "" );
	}

	
	
	Agility.GetCookie = function ( cookieName ) {
	/// <summary>Gets the value of the given cookieName from the current cookies collection.</summary>
	/// <param name="cookieName" type="String">The name of the cookie to return.</param>

		// first we'll split this cookie up into name/value pairs
		// note: document.cookie only returns name=value, not the other components
		var a_all_cookies = document.cookie.split( ';' );
		var a_temp_cookie = '';
		var cookie_name = '';
		var cookie_value = '';
		var b_cookie_found = false; // set boolean t/f default f

		for ( i = 0; i < a_all_cookies.length; i++ )
		{
			// now we'll split apart each name=value pair
			a_temp_cookie = a_all_cookies[i].split( '=' );


			// and trim left/right whitespace while we're at it
			cookie_name = a_temp_cookie[0].replace(/^\s+|\s+$/g, '');

			// if the extracted name matches passed cookieName
			if ( cookie_name == cookieName )
			{
				b_cookie_found = true;
				// we need to handle case where cookie has no value but exists (no = sign, that is):
				if ( a_temp_cookie.length > 1 )
				{
					cookie_value = unescape( a_temp_cookie[1].replace(/^\s+|\s+$/g, '') );
				}
				// note that in cases where cookie is initialized but no value, null is returned
				return cookie_value;
				break;
			}
			a_temp_cookie = null;
			cookie_name = '';
		}
		if ( !b_cookie_found )
		{
			return null;
		}
	}
	
    Agility.DeleteCookie = function(name, path, domain, secure) {
    /// <summary>Delete a cookie based on the name provided.</summary>
	/// <param name="name" type="String">The name of the cookie to set the value of.</param>
	/// <param name="path" type="String">(Optional) The path of the cookie.</param>
	/// <param name="domain" type="String">(Optional) The domain of the cookie.</param>
	/// <param name="secure" type="Boolean">(Optional) Whether the cookie is secure or not.</param>

        if (Agility.GetCookie(name)) {
            document.cookie = name + "=" + (path ? ";path=" + path : "") +
                (domain ? ";domain=" + domain : "" ) + (secure ? ";secure" : "") +
                ";expires=Thu, 01-Jan-1970 00:00:01 GMT";
        }
    };


    Agility.JSON = new function () {

        /* Section: Methods - Public */

        /*
        Method: decode
        decodes a valid JSON encoded string.
        
        Arguments:
        [String / Function] - Optional JSON string to decode or a filter function if method is a String prototype.
        [Function] - Optional filter function if first argument is a JSON string and this method is not a String prototype.
        
        Returns:
        Object - Generic JavaScript variable or undefined
        
        Example [Basic]:
        >var	arr = JSON.decode('[1,2,3]');
        >alert(arr);	// 1,2,3
        >
        >arr = JSON.decode('[1,2,3]', function(key, value){return key * value});
        >alert(arr);	// 0,2,6
        
        Example [Prototype]:
        >String.prototype.parseJSON = JSON.decode;
        >
        >alert('[1,2,3]'.parseJSON());	// 1,2,3
        >
        >try {
        >	alert('[1,2,3]'.parseJSON(function(key, value){return key * value}));
        >	// 0,2,6
        >}
        >catch(e) {
        >	alert(e.message);
        >}
        
        Note:
        Internet Explorer 5 and other old browsers should use a different regular expression to check if a JSON string is valid or not.
        This old browsers dedicated RegExp is not safe as native version is but it required for compatibility.
        */
        this.decode = function () {
            var filter, result, self, tmp;
            if ($$("toString")) {
                switch (arguments.length) {
                    case 2:
                        self = arguments[0];
                        filter = arguments[1];
                        break;
                    case 1:
                        if ($[typeof arguments[0]](arguments[0]) === Function) {
                            self = this;
                            filter = arguments[0];
                        }
                        else
                            self = arguments[0];
                        break;
                    default:
                        self = this;
                        break;
                };
                if (rc.test(self)) {
                    try {
                        result = e("(".concat(self, ")"));
                        if (filter && result !== null && (tmp = $[typeof result](result)) && (tmp === Array || tmp === Object)) {
                            for (self in result)
                                result[self] = v(self, result) ? filter(self, result[self]) : result[self];
                        }
                    }
                    catch (z) { }
                }
                else {
                    throw new JSONError("bad data");
                }
            };
            return result;
        };

        /*
        Method: encode
        encode a generic JavaScript variable into a valid JSON string.
        
        Arguments:
        [Object] - Optional generic JavaScript variable to encode if method is not an Object prototype.
        
        Returns:
        String - Valid JSON string or undefined
        
        Example [Basic]:
        >var	s = Agility.JSON.encode([1,2,3]);
        >alert(s);	// [1,2,3]
        
        Example [Prototype]:
        >Object.prototype.toJSONString = Agility.JSON.encode;
        >
        >alert([1,2,3].toJSONString());	// [1,2,3]
        */
        this.encode = function () {
            var self = arguments.length ? arguments[0] : this,
                result, tmp;
            if (self === null)
                result = "null";
            else if (self !== undefined && (tmp = $[typeof self](self))) {
                switch (tmp) {
                    case Array:
                        result = [];
                        for (var i = 0, j = 0, k = self.length; j < k; j++) {
                            if (self[j] !== undefined && (tmp = Agility.JSON.encode(self[j])))
                                result[i++] = tmp;
                        };
                        result = "[".concat(result.join(","), "]");
                        break;
                    case Boolean:
                        result = String(self);
                        break;
                    case Date:
                        result = '"'.concat(self.getFullYear(), '-', d(self.getMonth() + 1), '-', d(self.getDate()), 'T', d(self.getHours()), ':', d(self.getMinutes()), ':', d(self.getSeconds()), '"');
                        break;
                    case Function:
                        break;
                    case Number:
                        result = isFinite(self) ? String(self) : "null";
                        break;
                    case String:
                        result = '"'.concat(self.replace(rs, s).replace(ru, u), '"');
                        break;
                    default:
                        var i = 0, key;
                        result = [];
                        for (key in self) {
                            if (self[key] !== undefined && (tmp = Agility.JSON.encode(self[key])))
                                result[i++] = '"'.concat(key.replace(rs, s).replace(ru, u), '":', tmp);
                        };
                        result = "{".concat(result.join(","), "}");
                        break;
                }
            };
            return result;
        };

        /*
        Method: toDate
        transforms a JSON encoded Date string into a native Date object.
        
        Arguments:
        [String/Number] - Optional JSON Date string or server time if this method is not a String prototype. Server time should be an integer, based on seconds since 1970/01/01 or milliseconds / 1000 since 1970/01/01.
        
        Returns:
        Date - Date object or undefined if string is not a valid Date
        
        Example [Basic]:
        >var	serverDate = JSON.toDate("2007-04-05T08:36:46");
        >alert(serverDate.getMonth());	// 3 (months start from 0)
        
        Example [Prototype]:
        >String.prototype.parseDate = JSON.toDate;
        >
        >alert("2007-04-05T08:36:46".parseDate().getDate());	// 5
        
        Example [Server Time]:
        >var	phpServerDate = JSON.toDate(<?php echo time(); ?>);
        >var	csServerDate = JSON.toDate(<%=(DateTime.Now.Ticks/10000-62135596800000)%>/1000);
        
        Example [Server Time Prototype]:
        >Number.prototype.parseDate = JSON.toDate;
        >var	phpServerDate = (<?php echo time(); ?>).parseDate();
        >var	csServerDate = (<%=(DateTime.Now.Ticks/10000-62135596800000)%>/1000).parseDate();
        
        Note:
        This method accepts an integer or numeric string too to mantain compatibility with generic server side time() function.
        You can convert quickly mtime, ctime, time and other time based values.
        With languages that supports milliseconds you can send total milliseconds / 1000 (time is set as time * 1000)
        */
        this.toDate = function () {
            var self = arguments.length ? arguments[0] : this,
                result;
            if (rd.test(self)) {
                result = new Date;
                result.setHours(i(self, 11, 2));
                result.setMinutes(i(self, 14, 2));
                result.setSeconds(i(self, 17, 2));
                result.setMonth(i(self, 5, 2) - 1);
                result.setDate(i(self, 8, 2));
                result.setFullYear(i(self, 0, 4));
            }
            else if (rt.test(self))
                result = new Date(self * 1000);
            return result;
        };

        /* Section: Properties - Private */

        /*
        Property: Private
        
        List:
        Object - 'c' - a dictionary with useful keys / values for fast encode convertion
        Function - 'd' - returns decimal string rappresentation of a number ("14", "03", etc)
        Function - 'e' - safe and native code evaulation
        Function - 'i' - returns integer from string ("01" => 1, "15" => 15, etc)
        Array - 'p' - a list with different "0" strings for fast special chars escape convertion
        RegExp - 'rc' - regular expression to check JSON strings (different for IE5 or old browsers and new one)
        RegExp - 'rd' - regular expression to check a JSON Date string
        RegExp - 'rs' - regular expression to check string chars to modify using c (char) values
        RegExp - 'rt' - regular expression to check integer numeric string (for toDate time version evaluation)
        RegExp - 'ru' - regular expression to check string chars to escape using "\u" prefix
        Function - 's' - returns escaped string adding "\\" char as prefix ("\\" => "\\\\", etc.)
        Function - 'u' - returns escaped string, modifyng special chars using "\uNNNN" notation
        Function - 'v' - returns boolean value to skip object methods or prototyped parameters (length, others), used for optional decode filter function
        Function - '$' - returns object constructor if it was not cracked (someVar = {}; someVar.constructor = String <= ignore them)
        Function - '$$' - returns boolean value to check native Array and Object constructors before convertion
        */
        var c = { "\b": "b", "\t": "t", "\n": "n", "\f": "f", "\r": "r", '"': '"', "\\": "\\", "/": "/" },
            d = function (n) { return n < 10 ? "0".concat(n) : n },
            e = function (c, f, e) { e = eval; delete eval; if (typeof eval === "undefined") eval = e; f = eval("" + c); eval = e; return f },
            i = function (e, p, l) { return 1 * e.substr(p, l) },
            p = ["", "000", "00", "0", ""],
            rc = null,
            rd = /^[0-9]{4}\-[0-9]{2}\-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$/,
            rs = /(\x5c|\x2F|\x22|[\x0c-\x0d]|[\x08-\x0a])/g,
            rt = /^([0-9]+|[0-9]+[,\.][0-9]{1,3})$/,
            ru = /([\x00-\x07]|\x0b|[\x0e-\x1f])/g,
            s = function (i, d) { return "\\".concat(c[d]) },
            u = function (i, d) {
                var n = d.charCodeAt(0).toString(16);
                return "\\u".concat(p[n.length], n)
            },
            v = function (k, v) { return $[typeof result](result) !== Function && (v.hasOwnProperty ? v.hasOwnProperty(k) : v.constructor.prototype[k] !== v[k]) },
            $ = {
                "boolean": function () { return Boolean },
                "function": function () { return Function },
                "number": function () { return Number },
                "object": function (o) { return o instanceof o.constructor ? o.constructor : null },
                "string": function () { return String },
                "undefined": function () { return null }
            },
            $$ = function (m) {
                function $(c, t) { t = c[m]; delete c[m]; try { e(c) } catch (z) { c[m] = t; return 1 } };
                return $(Array) && $(Object)
            };
        try { rc = new RegExp('^("(\\\\.|[^"\\\\\\n\\r])*?"|[,:{}\\[\\]0-9.\\-+Eaeflnr-u \\n\\r\\t])+?$') }
        catch (z) { rc = /^(true|false|null|\[.*\]|\{.*\}|".*"|\d+|\d+\.\d+)$/ }
    };
	

})(Agility);


//include datejs here
var tryParseDate = Date.parse;
Date.CultureInfo={name:"en-US",englishName:"English (United States)",nativeName:"English (United States)",dayNames:["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"],abbreviatedDayNames:["Sun","Mon","Tue","Wed","Thu","Fri","Sat"],shortestDayNames:["Su","Mo","Tu","We","Th","Fr","Sa"],firstLetterDayNames:["S","M","T","W","T","F","S"],monthNames:["January","February","March","April","May","June","July","August","September","October","November","December"],abbreviatedMonthNames:["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],amDesignator:"AM",pmDesignator:"PM",firstDayOfWeek:0,twoDigitYearMax:2029,dateElementOrder:"mdy",formatPatterns:{shortDate:"M/d/yyyy",longDate:"dddd, MMMM dd, yyyy",shortTime:"h:mm tt",longTime:"h:mm:ss tt",fullDateTime:"dddd, MMMM dd, yyyy h:mm:ss tt",sortableDateTime:"yyyy-MM-ddTHH:mm:ss",universalSortableDateTime:"yyyy-MM-dd HH:mm:ssZ",rfc1123:"ddd, dd MMM yyyy HH:mm:ss GMT",monthDay:"MMMM dd",yearMonth:"MMMM, yyyy"},regexPatterns:{jan:/^jan(uary)?/i,feb:/^feb(ruary)?/i,mar:/^mar(ch)?/i,apr:/^apr(il)?/i,may:/^may/i,jun:/^jun(e)?/i,jul:/^jul(y)?/i,aug:/^aug(ust)?/i,sep:/^sep(t(ember)?)?/i,oct:/^oct(ober)?/i,nov:/^nov(ember)?/i,dec:/^dec(ember)?/i,sun:/^su(n(day)?)?/i,mon:/^mo(n(day)?)?/i,tue:/^tu(e(s(day)?)?)?/i,wed:/^we(d(nesday)?)?/i,thu:/^th(u(r(s(day)?)?)?)?/i,fri:/^fr(i(day)?)?/i,sat:/^sa(t(urday)?)?/i,future:/^next/i,past:/^last|past|prev(ious)?/i,add:/^(\+|aft(er)?|from|hence)/i,subtract:/^(\-|bef(ore)?|ago)/i,yesterday:/^yes(terday)?/i,today:/^t(od(ay)?)?/i,tomorrow:/^tom(orrow)?/i,now:/^n(ow)?/i,millisecond:/^ms|milli(second)?s?/i,second:/^sec(ond)?s?/i,minute:/^mn|min(ute)?s?/i,hour:/^h(our)?s?/i,week:/^w(eek)?s?/i,month:/^m(onth)?s?/i,day:/^d(ay)?s?/i,year:/^y(ear)?s?/i,shortMeridian:/^(a|p)/i,longMeridian:/^(a\.?m?\.?|p\.?m?\.?)/i,timezone:/^((e(s|d)t|c(s|d)t|m(s|d)t|p(s|d)t)|((gmt)?\s*(\+|\-)\s*\d\d\d\d?)|gmt|utc)/i,ordinalSuffix:/^\s*(st|nd|rd|th)/i,timeContext:/^\s*(\:|a(?!u|p)|p)/i},timezones:[{name:"UTC",offset:"-000"},{name:"GMT",offset:"-000"},{name:"EST",offset:"-0500"},{name:"EDT",offset:"-0400"},{name:"CST",offset:"-0600"},{name:"CDT",offset:"-0500"},{name:"MST",offset:"-0700"},{name:"MDT",offset:"-0600"},{name:"PST",offset:"-0800"},{name:"PDT",offset:"-0700"}]};(function(){var $D=Date,$P=$D.prototype,$C=$D.CultureInfo,p=function(s,l){if(!l){l=2;}return("000"+s).slice(l*-1);};$P.clearTime=function(){this.setHours(0);this.setMinutes(0);this.setSeconds(0);this.setMilliseconds(0);return this;};$P.setTimeToNow=function(){var n=new Date();this.setHours(n.getHours());this.setMinutes(n.getMinutes());this.setSeconds(n.getSeconds());this.setMilliseconds(n.getMilliseconds());return this;};$D.today=function(){return new Date().clearTime();};$D.compare=function(date1,date2){if(isNaN(date1)||isNaN(date2)){throw new Error(date1+" - "+date2);}else{if(date1 instanceof Date&&date2 instanceof Date){return(date1<date2)?-1:(date1>date2)?1:0;}else{throw new TypeError(date1+" - "+date2);}}};$D.equals=function(date1,date2){return(date1.compareTo(date2)===0);};$D.getDayNumberFromName=function(name){var n=$C.dayNames,m=$C.abbreviatedDayNames,o=$C.shortestDayNames,s=name.toLowerCase();for(var i=0;i<n.length;i++){if(n[i].toLowerCase()==s||m[i].toLowerCase()==s||o[i].toLowerCase()==s){return i;}}return -1;};$D.getMonthNumberFromName=function(name){var n=$C.monthNames,m=$C.abbreviatedMonthNames,s=name.toLowerCase();for(var i=0;i<n.length;i++){if(n[i].toLowerCase()==s||m[i].toLowerCase()==s){return i;}}return -1;};$D.isLeapYear=function(year){return((year%4===0&&year%100!==0)||year%400===0);};$D.getDaysInMonth=function(year,month){return[31,($D.isLeapYear(year)?29:28),31,30,31,30,31,31,30,31,30,31][month];};$D.getTimezoneAbbreviation=function(offset){var z=$C.timezones,p;for(var i=0;i<z.length;i++){if(z[i].offset===offset){return z[i].name;}}return null;};$D.getTimezoneOffset=function(name){var z=$C.timezones,p;for(var i=0;i<z.length;i++){if(z[i].name===name.toUpperCase()){return z[i].offset;}}return null;};$P.clone=function(){return new Date(this.getTime());};$P.compareTo=function(date){return Date.compare(this,date);};$P.equals=function(date){return Date.equals(this,date||new Date());};$P.between=function(start,end){return this.getTime()>=start.getTime()&&this.getTime()<=end.getTime();};$P.isAfter=function(date){return this.compareTo(date||new Date())===1;};$P.isBefore=function(date){return(this.compareTo(date||new Date())===-1);};$P.isToday=$P.isSameDay=function(date){return this.clone().clearTime().equals((date||new Date()).clone().clearTime());};$P.addMilliseconds=function(value){this.setMilliseconds(this.getMilliseconds()+value*1);return this;};$P.addSeconds=function(value){return this.addMilliseconds(value*1000);};$P.addMinutes=function(value){return this.addMilliseconds(value*60000);};$P.addHours=function(value){return this.addMilliseconds(value*3600000);};$P.addDays=function(value){this.setDate(this.getDate()+value*1);return this;};$P.addWeeks=function(value){return this.addDays(value*7);};$P.addMonths=function(value){var n=this.getDate();this.setDate(1);this.setMonth(this.getMonth()+value*1);this.setDate(Math.min(n,$D.getDaysInMonth(this.getFullYear(),this.getMonth())));return this;};$P.addYears=function(value){return this.addMonths(value*12);};$P.add=function(config){if(typeof config=="number"){this._orient=config;return this;}var x=config;if(x.milliseconds){this.addMilliseconds(x.milliseconds);}if(x.seconds){this.addSeconds(x.seconds);}if(x.minutes){this.addMinutes(x.minutes);}if(x.hours){this.addHours(x.hours);}if(x.weeks){this.addWeeks(x.weeks);}if(x.months){this.addMonths(x.months);}if(x.years){this.addYears(x.years);}if(x.days){this.addDays(x.days);}return this;};var $y,$m,$d;$P.getWeek=function(){var a,b,c,d,e,f,g,n,s,w;$y=(!$y)?this.getFullYear():$y;$m=(!$m)?this.getMonth()+1:$m;$d=(!$d)?this.getDate():$d;if($m<=2){a=$y-1;b=(a/4|0)-(a/100|0)+(a/400|0);c=((a-1)/4|0)-((a-1)/100|0)+((a-1)/400|0);s=b-c;e=0;f=$d-1+(31*($m-1));}else{a=$y;b=(a/4|0)-(a/100|0)+(a/400|0);c=((a-1)/4|0)-((a-1)/100|0)+((a-1)/400|0);s=b-c;e=s+1;f=$d+((153*($m-3)+2)/5)+58+s;}g=(a+b)%7;d=(f+g-e)%7;n=(f+3-d)|0;if(n<0){w=53-((g-s)/5|0);}else{if(n>364+s){w=1;}else{w=(n/7|0)+1;}}$y=$m=$d=null;return w;};$P.getISOWeek=function(){$y=this.getUTCFullYear();$m=this.getUTCMonth()+1;$d=this.getUTCDate();return p(this.getWeek());};$P.setWeek=function(n){return this.moveToDayOfWeek(1).addWeeks(n-this.getWeek());};var validate=function(n,min,max,name){if(typeof n=="undefined"){return false;}else{if(typeof n!="number"){throw new TypeError(n+" is not a Number.");}else{if(n<min||n>max){throw new RangeError(n+" is not a valid value for "+name+".");}}}return true;};$D.validateMillisecond=function(value){return validate(value,0,999,"millisecond");};$D.validateSecond=function(value){return validate(value,0,59,"second");};$D.validateMinute=function(value){return validate(value,0,59,"minute");};$D.validateHour=function(value){return validate(value,0,23,"hour");};$D.validateDay=function(value,year,month){return validate(value,1,$D.getDaysInMonth(year,month),"day");};$D.validateMonth=function(value){return validate(value,0,11,"month");};$D.validateYear=function(value){return validate(value,0,9999,"year");};$P.set=function(config){if($D.validateMillisecond(config.millisecond)){this.addMilliseconds(config.millisecond-this.getMilliseconds());}if($D.validateSecond(config.second)){this.addSeconds(config.second-this.getSeconds());}if($D.validateMinute(config.minute)){this.addMinutes(config.minute-this.getMinutes());}if($D.validateHour(config.hour)){this.addHours(config.hour-this.getHours());}if($D.validateMonth(config.month)){this.addMonths(config.month-this.getMonth());}if($D.validateYear(config.year)){this.addYears(config.year-this.getFullYear());}if($D.validateDay(config.day,this.getFullYear(),this.getMonth())){this.addDays(config.day-this.getDate());}if(config.timezone){this.setTimezone(config.timezone);}if(config.timezoneOffset){this.setTimezoneOffset(config.timezoneOffset);}if(config.week&&validate(config.week,0,53,"week")){this.setWeek(config.week);}return this;};$P.moveToFirstDayOfMonth=function(){return this.set({day:1});};$P.moveToLastDayOfMonth=function(){return this.set({day:$D.getDaysInMonth(this.getFullYear(),this.getMonth())});};$P.moveToNthOccurrence=function(dayOfWeek,occurrence){var shift=0;if(occurrence>0){shift=occurrence-1;}else{if(occurrence===-1){this.moveToLastDayOfMonth();if(this.getDay()!==dayOfWeek){this.moveToDayOfWeek(dayOfWeek,-1);}return this;}}return this.moveToFirstDayOfMonth().addDays(-1).moveToDayOfWeek(dayOfWeek,+1).addWeeks(shift);};$P.moveToDayOfWeek=function(dayOfWeek,orient){var diff=(dayOfWeek-this.getDay()+7*(orient||+1))%7;return this.addDays((diff===0)?diff+=7*(orient||+1):diff);};$P.moveToMonth=function(month,orient){var diff=(month-this.getMonth()+12*(orient||+1))%12;return this.addMonths((diff===0)?diff+=12*(orient||+1):diff);};$P.getOrdinalNumber=function(){return Math.ceil((this.clone().clearTime()-new Date(this.getFullYear(),0,1))/86400000)+1;};$P.getTimezone=function(){return $D.getTimezoneAbbreviation(this.getUTCOffset());};$P.setTimezoneOffset=function(offset){var here=this.getTimezoneOffset(),there=Number(offset)*-6/10;return this.addMinutes(there-here);};$P.setTimezone=function(offset){return this.setTimezoneOffset($D.getTimezoneOffset(offset));};$P.hasDaylightSavingTime=function(){return(Date.today().set({month:0,day:1}).getTimezoneOffset()!==Date.today().set({month:6,day:1}).getTimezoneOffset());};$P.isDaylightSavingTime=function(){return Date.today().set({month:0,day:1}).getTimezoneOffset()!=this.getTimezoneOffset();};$P.getUTCOffset=function(){var n=this.getTimezoneOffset()*-10/6,r;if(n<0){r=(n-10000).toString();return r.charAt(0)+r.substr(2);}else{r=(n+10000).toString();return"+"+r.substr(1);}};$P.getElapsed=function(date){return(date||new Date())-this;};if(!$P.toISOString){$P.toISOString=function(){function f(n){return n<10?"0"+n:n;}return'"'+this.getUTCFullYear()+"-"+f(this.getUTCMonth()+1)+"-"+f(this.getUTCDate())+"T"+f(this.getUTCHours())+":"+f(this.getUTCMinutes())+":"+f(this.getUTCSeconds())+'Z"';};}$P._toString=$P.toString;$P.toString=function(format){var x=this;if(format&&format.length==1){var c=$C.formatPatterns;x.t=x.toString;switch(format){case"d":return x.t(c.shortDate);case"D":return x.t(c.longDate);case"F":return x.t(c.fullDateTime);case"m":return x.t(c.monthDay);case"r":return x.t(c.rfc1123);case"s":return x.t(c.sortableDateTime);case"t":return x.t(c.shortTime);case"T":return x.t(c.longTime);case"u":return x.t(c.universalSortableDateTime);case"y":return x.t(c.yearMonth);}}var ord=function(n){switch(n*1){case 1:case 21:case 31:return"st";case 2:case 22:return"nd";case 3:case 23:return"rd";default:return"th";}};return format?format.replace(/(\\)?(dd?d?d?|MM?M?M?|yy?y?y?|hh?|HH?|mm?|ss?|tt?|S)/g,function(m){if(m.charAt(0)==="\\"){return m.replace("\\","");}x.h=x.getHours;switch(m){case"hh":return p(x.h()<13?(x.h()===0?12:x.h()):(x.h()-12));case"h":return x.h()<13?(x.h()===0?12:x.h()):(x.h()-12);case"HH":return p(x.h());case"H":return x.h();case"mm":return p(x.getMinutes());case"m":return x.getMinutes();case"ss":return p(x.getSeconds());case"s":return x.getSeconds();case"yyyy":return p(x.getFullYear(),4);case"yy":return p(x.getFullYear());case"dddd":return $C.dayNames[x.getDay()];case"ddd":return $C.abbreviatedDayNames[x.getDay()];case"dd":return p(x.getDate());case"d":return x.getDate();case"MMMM":return $C.monthNames[x.getMonth()];case"MMM":return $C.abbreviatedMonthNames[x.getMonth()];case"MM":return p((x.getMonth()+1));case"M":return x.getMonth()+1;case"t":return x.h()<12?$C.amDesignator.substring(0,1):$C.pmDesignator.substring(0,1);case"tt":return x.h()<12?$C.amDesignator:$C.pmDesignator;case"S":return ord(x.getDate());default:return m;}}):this._toString();};}());(function(){Date.Parsing={Exception:function(s){this.message="Parse error at '"+s.substring(0,10)+" ...'";}};var $P=Date.Parsing;var _=$P.Operators={rtoken:function(r){return function(s){var mx=s.match(r);if(mx){return([mx[0],s.substring(mx[0].length)]);}else{throw new $P.Exception(s);}};},token:function(s){return function(s){return _.rtoken(new RegExp("^s*"+s+"s*"))(s);};},stoken:function(s){return _.rtoken(new RegExp("^"+s));},until:function(p){return function(s){var qx=[],rx=null;while(s.length){try{rx=p.call(this,s);}catch(e){qx.push(rx[0]);s=rx[1];continue;}break;}return[qx,s];};},many:function(p){return function(s){var rx=[],r=null;while(s.length){try{r=p.call(this,s);}catch(e){return[rx,s];}rx.push(r[0]);s=r[1];}return[rx,s];};},optional:function(p){return function(s){var r=null;try{r=p.call(this,s);}catch(e){return[null,s];}return[r[0],r[1]];};},not:function(p){return function(s){try{p.call(this,s);}catch(e){return[null,s];}throw new $P.Exception(s);};},ignore:function(p){return p?function(s){var r=null;r=p.call(this,s);return[null,r[1]];}:null;},product:function(){var px=arguments[0],qx=Array.prototype.slice.call(arguments,1),rx=[];for(var i=0;i<px.length;i++){rx.push(_.each(px[i],qx));}return rx;},cache:function(rule){var cache={},r=null;return function(s){try{r=cache[s]=(cache[s]||rule.call(this,s));}catch(e){r=cache[s]=e;}if(r instanceof $P.Exception){throw r;}else{return r;}};},any:function(){var px=arguments;return function(s){var r=null;for(var i=0;i<px.length;i++){if(px[i]==null){continue;}try{r=(px[i].call(this,s));}catch(e){r=null;}if(r){return r;}}throw new $P.Exception(s);};},each:function(){var px=arguments;return function(s){var rx=[],r=null;for(var i=0;i<px.length;i++){if(px[i]==null){continue;}try{r=(px[i].call(this,s));}catch(e){throw new $P.Exception(s);}rx.push(r[0]);s=r[1];}return[rx,s];};},all:function(){var px=arguments,_=_;return _.each(_.optional(px));},sequence:function(px,d,c){d=d||_.rtoken(/^\s*/);c=c||null;if(px.length==1){return px[0];}return function(s){var r=null,q=null;var rx=[];for(var i=0;i<px.length;i++){try{r=px[i].call(this,s);}catch(e){break;}rx.push(r[0]);try{q=d.call(this,r[1]);}catch(ex){q=null;break;}s=q[1];}if(!r){throw new $P.Exception(s);}if(q){throw new $P.Exception(q[1]);}if(c){try{r=c.call(this,r[1]);}catch(ey){throw new $P.Exception(r[1]);}}return[rx,(r?r[1]:s)];};},between:function(d1,p,d2){d2=d2||d1;var _fn=_.each(_.ignore(d1),p,_.ignore(d2));return function(s){var rx=_fn.call(this,s);return[[rx[0][0],r[0][2]],rx[1]];};},list:function(p,d,c){d=d||_.rtoken(/^\s*/);c=c||null;return(p instanceof Array?_.each(_.product(p.slice(0,-1),_.ignore(d)),p.slice(-1),_.ignore(c)):_.each(_.many(_.each(p,_.ignore(d))),px,_.ignore(c)));},set:function(px,d,c){d=d||_.rtoken(/^\s*/);c=c||null;return function(s){var r=null,p=null,q=null,rx=null,best=[[],s],last=false;for(var i=0;i<px.length;i++){q=null;p=null;r=null;last=(px.length==1);try{r=px[i].call(this,s);}catch(e){continue;}rx=[[r[0]],r[1]];if(r[1].length>0&&!last){try{q=d.call(this,r[1]);}catch(ex){last=true;}}else{last=true;}if(!last&&q[1].length===0){last=true;}if(!last){var qx=[];for(var j=0;j<px.length;j++){if(i!=j){qx.push(px[j]);}}p=_.set(qx,d).call(this,q[1]);if(p[0].length>0){rx[0]=rx[0].concat(p[0]);rx[1]=p[1];}}if(rx[1].length<best[1].length){best=rx;}if(best[1].length===0){break;}}if(best[0].length===0){return best;}if(c){try{q=c.call(this,best[1]);}catch(ey){throw new $P.Exception(best[1]);}best[1]=q[1];}return best;};},forward:function(gr,fname){return function(s){return gr[fname].call(this,s);};},replace:function(rule,repl){return function(s){var r=rule.call(this,s);return[repl,r[1]];};},process:function(rule,fn){return function(s){var r=rule.call(this,s);return[fn.call(this,r[0]),r[1]];};},min:function(min,rule){return function(s){var rx=rule.call(this,s);if(rx[0].length<min){throw new $P.Exception(s);}return rx;};}};var _generator=function(op){return function(){var args=null,rx=[];if(arguments.length>1){args=Array.prototype.slice.call(arguments);}else{if(arguments[0] instanceof Array){args=arguments[0];}}if(args){for(var i=0,px=args.shift();i<px.length;i++){args.unshift(px[i]);rx.push(op.apply(null,args));args.shift();return rx;}}else{return op.apply(null,arguments);}};};var gx="optional not ignore cache".split(/\s/);for(var i=0;i<gx.length;i++){_[gx[i]]=_generator(_[gx[i]]);}var _vector=function(op){return function(){if(arguments[0] instanceof Array){return op.apply(null,arguments[0]);}else{return op.apply(null,arguments);}};};var vx="each any all".split(/\s/);for(var j=0;j<vx.length;j++){_[vx[j]]=_vector(_[vx[j]]);}}());(function(){var $D=Date,$P=$D.prototype,$C=$D.CultureInfo;var flattenAndCompact=function(ax){var rx=[];for(var i=0;i<ax.length;i++){if(ax[i] instanceof Array){rx=rx.concat(flattenAndCompact(ax[i]));}else{if(ax[i]){rx.push(ax[i]);}}}return rx;};$D.Grammar={};$D.Translator={hour:function(s){return function(){this.hour=Number(s);};},minute:function(s){return function(){this.minute=Number(s);};},second:function(s){return function(){this.second=Number(s);};},meridian:function(s){return function(){this.meridian=s.slice(0,1).toLowerCase();};},timezone:function(s){return function(){var n=s.replace(/[^\d\+\-]/g,"");if(n.length){this.timezoneOffset=Number(n);}else{this.timezone=s.toLowerCase();}};},day:function(x){var s=x[0];return function(){this.day=Number(s.match(/\d+/)[0]);};},month:function(s){return function(){this.month=(s.length==3)?"jan feb mar apr may jun jul aug sep oct nov dec".indexOf(s)/4:Number(s)-1;};},year:function(s){return function(){var n=Number(s);this.year=((s.length>2)?n:(n+(((n+2000)<$C.twoDigitYearMax)?2000:1900)));};},rday:function(s){return function(){switch(s){case"yesterday":this.days=-1;break;case"tomorrow":this.days=1;break;case"today":this.days=0;break;case"now":this.days=0;this.now=true;break;}};},finishExact:function(x){x=(x instanceof Array)?x:[x];for(var i=0;i<x.length;i++){if(x[i]){x[i].call(this);}}var now=new Date();if((this.hour||this.minute)&&(!this.month&&!this.year&&!this.day)){this.day=now.getDate();}if(!this.year){this.year=now.getFullYear();}if(!this.month&&this.month!==0){this.month=now.getMonth();}if(!this.day){this.day=1;}if(!this.hour){this.hour=0;}if(!this.minute){this.minute=0;}if(!this.second){this.second=0;}if(this.meridian&&this.hour){if(this.meridian=="p"&&this.hour<12){this.hour=this.hour+12;}else{if(this.meridian=="a"&&this.hour==12){this.hour=0;}}}if(this.day>$D.getDaysInMonth(this.year,this.month)){throw new RangeError(this.day+" is not a valid value for days.");}var r=new Date(this.year,this.month,this.day,this.hour,this.minute,this.second);if(this.timezone){r.set({timezone:this.timezone});}else{if(this.timezoneOffset){r.set({timezoneOffset:this.timezoneOffset});}}return r;},finish:function(x){x=(x instanceof Array)?flattenAndCompact(x):[x];if(x.length===0){return null;}for(var i=0;i<x.length;i++){if(typeof x[i]=="function"){x[i].call(this);}}var today=$D.today();if(this.now&&!this.unit&&!this.operator){return new Date();}else{if(this.now){today=new Date();}}var expression=!!(this.days&&this.days!==null||this.orient||this.operator);var gap,mod,orient;orient=((this.orient=="past"||this.operator=="subtract")?-1:1);if(!this.now&&"hour minute second".indexOf(this.unit)!=-1){today.setTimeToNow();}if(this.month||this.month===0){if("year day hour minute second".indexOf(this.unit)!=-1){this.value=this.month+1;this.month=null;expression=true;}}if(!expression&&this.weekday&&!this.day&&!this.days){var temp=Date[this.weekday]();this.day=temp.getDate();if(!this.month){this.month=temp.getMonth();}this.year=temp.getFullYear();}if(expression&&this.weekday&&this.unit!="month"){this.unit="day";gap=($D.getDayNumberFromName(this.weekday)-today.getDay());mod=7;this.days=gap?((gap+(orient*mod))%mod):(orient*mod);}if(this.month&&this.unit=="day"&&this.operator){this.value=(this.month+1);this.month=null;}if(this.value!=null&&this.month!=null&&this.year!=null){this.day=this.value*1;}if(this.month&&!this.day&&this.value){today.set({day:this.value*1});if(!expression){this.day=this.value*1;}}if(!this.month&&this.value&&this.unit=="month"&&!this.now){this.month=this.value;expression=true;}if(expression&&(this.month||this.month===0)&&this.unit!="year"){this.unit="month";gap=(this.month-today.getMonth());mod=12;this.months=gap?((gap+(orient*mod))%mod):(orient*mod);this.month=null;}if(!this.unit){this.unit="day";}if(!this.value&&this.operator&&this.operator!==null&&this[this.unit+"s"]&&this[this.unit+"s"]!==null){this[this.unit+"s"]=this[this.unit+"s"]+((this.operator=="add")?1:-1)+(this.value||0)*orient;}else{if(this[this.unit+"s"]==null||this.operator!=null){if(!this.value){this.value=1;}this[this.unit+"s"]=this.value*orient;}}if(this.meridian&&this.hour){if(this.meridian=="p"&&this.hour<12){this.hour=this.hour+12;}else{if(this.meridian=="a"&&this.hour==12){this.hour=0;}}}if(this.weekday&&!this.day&&!this.days){var temp=Date[this.weekday]();this.day=temp.getDate();if(temp.getMonth()!==today.getMonth()){this.month=temp.getMonth();}}if((this.month||this.month===0)&&!this.day){this.day=1;}if(!this.orient&&!this.operator&&this.unit=="week"&&this.value&&!this.day&&!this.month){return Date.today().setWeek(this.value);}if(expression&&this.timezone&&this.day&&this.days){this.day=this.days;}return(expression)?today.add(this):today.set(this);}};var _=$D.Parsing.Operators,g=$D.Grammar,t=$D.Translator,_fn;g.datePartDelimiter=_.rtoken(/^([\s\-\.\,\/\x27]+)/);g.timePartDelimiter=_.stoken(":");g.whiteSpace=_.rtoken(/^\s*/);g.generalDelimiter=_.rtoken(/^(([\s\,]|at|@|on)+)/);var _C={};g.ctoken=function(keys){var fn=_C[keys];if(!fn){var c=$C.regexPatterns;var kx=keys.split(/\s+/),px=[];for(var i=0;i<kx.length;i++){px.push(_.replace(_.rtoken(c[kx[i]]),kx[i]));}fn=_C[keys]=_.any.apply(null,px);}return fn;};g.ctoken2=function(key){return _.rtoken($C.regexPatterns[key]);};g.h=_.cache(_.process(_.rtoken(/^(0[0-9]|1[0-2]|[1-9])/),t.hour));g.hh=_.cache(_.process(_.rtoken(/^(0[0-9]|1[0-2])/),t.hour));g.H=_.cache(_.process(_.rtoken(/^([0-1][0-9]|2[0-3]|[0-9])/),t.hour));g.HH=_.cache(_.process(_.rtoken(/^([0-1][0-9]|2[0-3])/),t.hour));g.m=_.cache(_.process(_.rtoken(/^([0-5][0-9]|[0-9])/),t.minute));g.mm=_.cache(_.process(_.rtoken(/^[0-5][0-9]/),t.minute));g.s=_.cache(_.process(_.rtoken(/^([0-5][0-9]|[0-9])/),t.second));g.ss=_.cache(_.process(_.rtoken(/^[0-5][0-9]/),t.second));g.hms=_.cache(_.sequence([g.H,g.m,g.s],g.timePartDelimiter));g.t=_.cache(_.process(g.ctoken2("shortMeridian"),t.meridian));g.tt=_.cache(_.process(g.ctoken2("longMeridian"),t.meridian));g.z=_.cache(_.process(_.rtoken(/^((\+|\-)\s*\d\d\d\d)|((\+|\-)\d\d\:?\d\d)/),t.timezone));g.zz=_.cache(_.process(_.rtoken(/^((\+|\-)\s*\d\d\d\d)|((\+|\-)\d\d\:?\d\d)/),t.timezone));g.zzz=_.cache(_.process(g.ctoken2("timezone"),t.timezone));g.timeSuffix=_.each(_.ignore(g.whiteSpace),_.set([g.tt,g.zzz]));g.time=_.each(_.optional(_.ignore(_.stoken("T"))),g.hms,g.timeSuffix);g.d=_.cache(_.process(_.each(_.rtoken(/^([0-2]\d|3[0-1]|\d)/),_.optional(g.ctoken2("ordinalSuffix"))),t.day));g.dd=_.cache(_.process(_.each(_.rtoken(/^([0-2]\d|3[0-1])/),_.optional(g.ctoken2("ordinalSuffix"))),t.day));g.ddd=g.dddd=_.cache(_.process(g.ctoken("sun mon tue wed thu fri sat"),function(s){return function(){this.weekday=s;};}));g.M=_.cache(_.process(_.rtoken(/^(1[0-2]|0\d|\d)/),t.month));g.MM=_.cache(_.process(_.rtoken(/^(1[0-2]|0\d)/),t.month));g.MMM=g.MMMM=_.cache(_.process(g.ctoken("jan feb mar apr may jun jul aug sep oct nov dec"),t.month));g.y=_.cache(_.process(_.rtoken(/^(\d\d?)/),t.year));g.yy=_.cache(_.process(_.rtoken(/^(\d\d)/),t.year));g.yyy=_.cache(_.process(_.rtoken(/^(\d\d?\d?\d?)/),t.year));g.yyyy=_.cache(_.process(_.rtoken(/^(\d\d\d\d)/),t.year));_fn=function(){return _.each(_.any.apply(null,arguments),_.not(g.ctoken2("timeContext")));};g.day=_fn(g.d,g.dd);g.month=_fn(g.M,g.MMM);g.year=_fn(g.yyyy,g.yy);g.orientation=_.process(g.ctoken("past future"),function(s){return function(){this.orient=s;};});g.operator=_.process(g.ctoken("add subtract"),function(s){return function(){this.operator=s;};});g.rday=_.process(g.ctoken("yesterday tomorrow today now"),t.rday);g.unit=_.process(g.ctoken("second minute hour day week month year"),function(s){return function(){this.unit=s;};});g.value=_.process(_.rtoken(/^\d\d?(st|nd|rd|th)?/),function(s){return function(){this.value=s.replace(/\D/g,"");};});g.expression=_.set([g.rday,g.operator,g.value,g.unit,g.orientation,g.ddd,g.MMM]);_fn=function(){return _.set(arguments,g.datePartDelimiter);};g.mdy=_fn(g.ddd,g.month,g.day,g.year);g.ymd=_fn(g.ddd,g.year,g.month,g.day);g.dmy=_fn(g.ddd,g.day,g.month,g.year);g.date=function(s){return((g[$C.dateElementOrder]||g.mdy).call(this,s));};g.format=_.process(_.many(_.any(_.process(_.rtoken(/^(dd?d?d?|MM?M?M?|yy?y?y?|hh?|HH?|mm?|ss?|tt?|zz?z?)/),function(fmt){if(g[fmt]){return g[fmt];}else{throw $D.Parsing.Exception(fmt);}}),_.process(_.rtoken(/^[^dMyhHmstz]+/),function(s){return _.ignore(_.stoken(s));}))),function(rules){return _.process(_.each.apply(null,rules),t.finishExact);});var _F={};var _get=function(f){return _F[f]=(_F[f]||g.format(f)[0]);};g.formats=function(fx){if(fx instanceof Array){var rx=[];for(var i=0;i<fx.length;i++){rx.push(_get(fx[i]));}return _.any.apply(null,rx);}else{return _get(fx);}};g._formats=g.formats(['"yyyy-MM-ddTHH:mm:ssZ"',"yyyy-MM-ddTHH:mm:ssZ","yyyy-MM-ddTHH:mm:ssz","yyyy-MM-ddTHH:mm:ss","yyyy-MM-ddTHH:mmZ","yyyy-MM-ddTHH:mmz","yyyy-MM-ddTHH:mm","ddd, MMM dd, yyyy H:mm:ss tt","ddd MMM d yyyy HH:mm:ss zzz","MMddyyyy","ddMMyyyy","Mddyyyy","ddMyyyy","Mdyyyy","dMyyyy","yyyy","Mdyy","dMyy","d"]);g._start=_.process(_.set([g.date,g.time,g.expression],g.generalDelimiter,g.whiteSpace),t.finish);g.start=function(s){try{var r=g._formats.call({},s);if(r[1].length===0){return r;}}catch(e){}return g._start.call({},s);};$D._parse=$D.parse;$D.parse=function(s){var r=null;if(!s){return null;}if(s instanceof Date){return s;}try{r=$D.Grammar.start.call({},s.replace(/^\s*(\S*(\s+\S+)*)\s*$/,"$1"));}catch(e){return null;}try { return((r[1].length===0)?r[0]:Date._parse(s)); } catch (er1) {return tryParseDate(s);};};$D.getParseFunction=function(fx){var fn=$D.Grammar.formats(fx);return function(s){var r=null;try{r=fn.call({},s);}catch(e){return null;}return((r[1].length===0)?r[0]:null);};};$D.parseExact=function(s,fx){return $D.getParseFunction(fx)(s);};}());(function(){var $D=Date,$P=$D.prototype,$C=$D.CultureInfo,$N=Number.prototype;$P._orient=+1;$P._nth=null;$P._is=false;$P._same=false;$P._isSecond=false;$N._dateElement="day";$P.next=function(){this._orient=+1;return this;};$D.next=function(){return $D.today().next();};$P.last=$P.prev=$P.previous=function(){this._orient=-1;return this;};$D.last=$D.prev=$D.previous=function(){return $D.today().last();};$P.is=function(){this._is=true;return this;};$P.same=function(){this._same=true;this._isSecond=false;return this;};$P.today=function(){return this.same().day();};$P.weekday=function(){if(this._is){this._is=false;return(!this.is().sat()&&!this.is().sun());}return false;};$P.at=function(time){return(typeof time==="string")?$D.parse(this.toString("d")+" "+time):this.set(time);};$N.fromNow=$N.after=function(date){var c={};c[this._dateElement]=this;return((!date)?new Date():date.clone()).add(c);};$N.ago=$N.before=function(date){var c={};c[this._dateElement]=this*-1;return((!date)?new Date():date.clone()).add(c);};var dx=("sunday monday tuesday wednesday thursday friday saturday").split(/\s/),mx=("january february march april may june july august september october november december").split(/\s/),px=("Millisecond Second Minute Hour Day Week Month Year").split(/\s/),pxf=("Milliseconds Seconds Minutes Hours Date Week Month FullYear").split(/\s/),nth=("final first second third fourth fifth").split(/\s/),de;$P.toObject=function(){var o={};for(var i=0;i<px.length;i++){o[px[i].toLowerCase()]=this["get"+pxf[i]]();}return o;};$D.fromObject=function(config){config.week=null;return Date.today().set(config);};var df=function(n){return function(){if(this._is){this._is=false;return this.getDay()==n;}if(this._nth!==null){if(this._isSecond){this.addSeconds(this._orient*-1);}this._isSecond=false;var ntemp=this._nth;this._nth=null;var temp=this.clone().moveToLastDayOfMonth();this.moveToNthOccurrence(n,ntemp);if(this>temp){throw new RangeError($D.getDayName(n)+" does not occur "+ntemp+" times in the month of "+$D.getMonthName(temp.getMonth())+" "+temp.getFullYear()+".");}return this;}return this.moveToDayOfWeek(n,this._orient);};};var sdf=function(n){return function(){var t=$D.today(),shift=n-t.getDay();if(n===0&&$C.firstDayOfWeek===1&&t.getDay()!==0){shift=shift+7;}return t.addDays(shift);};};for(var i=0;i<dx.length;i++){$D[dx[i].toUpperCase()]=$D[dx[i].toUpperCase().substring(0,3)]=i;$D[dx[i]]=$D[dx[i].substring(0,3)]=sdf(i);$P[dx[i]]=$P[dx[i].substring(0,3)]=df(i);}var mf=function(n){return function(){if(this._is){this._is=false;return this.getMonth()===n;}return this.moveToMonth(n,this._orient);};};var smf=function(n){return function(){return $D.today().set({month:n,day:1});};};for(var j=0;j<mx.length;j++){$D[mx[j].toUpperCase()]=$D[mx[j].toUpperCase().substring(0,3)]=j;$D[mx[j]]=$D[mx[j].substring(0,3)]=smf(j);$P[mx[j]]=$P[mx[j].substring(0,3)]=mf(j);}var ef=function(j){return function(){if(this._isSecond){this._isSecond=false;return this;}if(this._same){this._same=this._is=false;var o1=this.toObject(),o2=(arguments[0]||new Date()).toObject(),v="",k=j.toLowerCase();for(var m=(px.length-1);m>-1;m--){v=px[m].toLowerCase();if(o1[v]!=o2[v]){return false;}if(k==v){break;}}return true;}if(j.substring(j.length-1)!="s"){j+="s";}return this["add"+j](this._orient);};};var nf=function(n){return function(){this._dateElement=n;return this;};};for(var k=0;k<px.length;k++){de=px[k].toLowerCase();$P[de]=$P[de+"s"]=ef(px[k]);$N[de]=$N[de+"s"]=nf(de);}$P._ss=ef("Second");var nthfn=function(n){return function(dayOfWeek){if(this._same){return this._ss(arguments[0]);}if(dayOfWeek||dayOfWeek===0){return this.moveToNthOccurrence(dayOfWeek,n);}this._nth=n;if(n===2&&(dayOfWeek===undefined||dayOfWeek===null)){this._isSecond=true;return this.addSeconds(this._orient);}return this;};};for(var l=0;l<nth.length;l++){$P[nth[l]]=(l===0)?nthfn(-1):nthfn(l);}}());var TimeSpan=function(days,hours,minutes,seconds,milliseconds){var attrs="days hours minutes seconds milliseconds".split(/\s+/);var gFn=function(attr){return function(){return this[attr];};};var sFn=function(attr){return function(val){this[attr]=val;return this;};};for(var i=0;i<attrs.length;i++){var $a=attrs[i],$b=$a.slice(0,1).toUpperCase()+$a.slice(1);TimeSpan.prototype[$a]=0;TimeSpan.prototype["get"+$b]=gFn($a);TimeSpan.prototype["set"+$b]=sFn($a);}if(arguments.length==4){this.setDays(days);this.setHours(hours);this.setMinutes(minutes);this.setSeconds(seconds);}else{if(arguments.length==5){this.setDays(days);this.setHours(hours);this.setMinutes(minutes);this.setSeconds(seconds);this.setMilliseconds(milliseconds);}else{if(arguments.length==1&&typeof days=="number"){var orient=(days<0)?-1:+1;this.setMilliseconds(Math.abs(days));this.setDays(Math.floor(this.getMilliseconds()/86400000)*orient);this.setMilliseconds(this.getMilliseconds()%86400000);this.setHours(Math.floor(this.getMilliseconds()/3600000)*orient);this.setMilliseconds(this.getMilliseconds()%3600000);this.setMinutes(Math.floor(this.getMilliseconds()/60000)*orient);this.setMilliseconds(this.getMilliseconds()%60000);this.setSeconds(Math.floor(this.getMilliseconds()/1000)*orient);this.setMilliseconds(this.getMilliseconds()%1000);this.setMilliseconds(this.getMilliseconds()*orient);}}}this.getTotalMilliseconds=function(){return(this.getDays()*86400000)+(this.getHours()*3600000)+(this.getMinutes()*60000)+(this.getSeconds()*1000);};this.compareTo=function(time){var t1=new Date(1970,1,1,this.getHours(),this.getMinutes(),this.getSeconds()),t2;if(time===null){t2=new Date(1970,1,1,0,0,0);}else{t2=new Date(1970,1,1,time.getHours(),time.getMinutes(),time.getSeconds());}return(t1<t2)?-1:(t1>t2)?1:0;};this.equals=function(time){return(this.compareTo(time)===0);};this.add=function(time){return(time===null)?this:this.addSeconds(time.getTotalMilliseconds()/1000);};this.subtract=function(time){return(time===null)?this:this.addSeconds(-time.getTotalMilliseconds()/1000);};this.addDays=function(n){return new TimeSpan(this.getTotalMilliseconds()+(n*86400000));};this.addHours=function(n){return new TimeSpan(this.getTotalMilliseconds()+(n*3600000));};this.addMinutes=function(n){return new TimeSpan(this.getTotalMilliseconds()+(n*60000));};this.addSeconds=function(n){return new TimeSpan(this.getTotalMilliseconds()+(n*1000));};this.addMilliseconds=function(n){return new TimeSpan(this.getTotalMilliseconds()+n);};this.get12HourHour=function(){return(this.getHours()>12)?this.getHours()-12:(this.getHours()===0)?12:this.getHours();};this.getDesignator=function(){return(this.getHours()<12)?Date.CultureInfo.amDesignator:Date.CultureInfo.pmDesignator;};this.toString=function(format){this._toString=function(){if(this.getDays()!==null&&this.getDays()>0){return this.getDays()+"."+this.getHours()+":"+this.p(this.getMinutes())+":"+this.p(this.getSeconds());}else{return this.getHours()+":"+this.p(this.getMinutes())+":"+this.p(this.getSeconds());}};this.p=function(s){return(s.toString().length<2)?"0"+s:s;};var me=this;return format?format.replace(/dd?|HH?|hh?|mm?|ss?|tt?/g,function(format){switch(format){case"d":return me.getDays();case"dd":return me.p(me.getDays());case"H":return me.getHours();case"HH":return me.p(me.getHours());case"h":return me.get12HourHour();case"hh":return me.p(me.get12HourHour());case"m":return me.getMinutes();case"mm":return me.p(me.getMinutes());case"s":return me.getSeconds();case"ss":return me.p(me.getSeconds());case"t":return((me.getHours()<12)?Date.CultureInfo.amDesignator:Date.CultureInfo.pmDesignator).substring(0,1);case"tt":return(me.getHours()<12)?Date.CultureInfo.amDesignator:Date.CultureInfo.pmDesignator;}}):this._toString();};return this;};Date.prototype.getTimeOfDay=function(){return new TimeSpan(0,this.getHours(),this.getMinutes(),this.getSeconds(),this.getMilliseconds());};var TimePeriod=function(years,months,days,hours,minutes,seconds,milliseconds){var attrs="years months days hours minutes seconds milliseconds".split(/\s+/);var gFn=function(attr){return function(){return this[attr];};};var sFn=function(attr){return function(val){this[attr]=val;return this;};};for(var i=0;i<attrs.length;i++){var $a=attrs[i],$b=$a.slice(0,1).toUpperCase()+$a.slice(1);TimePeriod.prototype[$a]=0;TimePeriod.prototype["get"+$b]=gFn($a);TimePeriod.prototype["set"+$b]=sFn($a);}if(arguments.length==7){this.years=years;this.months=months;this.setDays(days);this.setHours(hours);this.setMinutes(minutes);this.setSeconds(seconds);this.setMilliseconds(milliseconds);}else{if(arguments.length==2&&arguments[0] instanceof Date&&arguments[1] instanceof Date){var d1=years.clone();var d2=months.clone();var temp=d1.clone();var orient=(d1>d2)?-1:+1;this.years=d2.getFullYear()-d1.getFullYear();temp.addYears(this.years);if(orient==+1){if(temp>d2){if(this.years!==0){this.years--;}}}else{if(temp<d2){if(this.years!==0){this.years++;}}}d1.addYears(this.years);if(orient==+1){while(d1<d2&&d1.clone().addDays(Date.getDaysInMonth(d1.getYear(),d1.getMonth()))<d2){d1.addMonths(1);this.months++;}}else{while(d1>d2&&d1.clone().addDays(-d1.getDaysInMonth())>d2){d1.addMonths(-1);this.months--;}}var diff=d2-d1;if(diff!==0){var ts=new TimeSpan(diff);this.setDays(ts.getDays());this.setHours(ts.getHours());this.setMinutes(ts.getMinutes());this.setSeconds(ts.getSeconds());this.setMilliseconds(ts.getMilliseconds());}}}return this;};

