
/*  --------------------------------------------
* @package Push.
* @author Lead Developer by Regi Ellis ( rellis@pushhere.com )
* @author Web Developer by Matt Ma. ( mma@pushhere.com )
* http://www.pushhere.com
    --------------------------------------------  */
    
/*  Object: Detection
    --------------------------------------------  */
    var Detection = function() {

      Detection.prototype.init = function() {
        this.name = 'Core Library';
        this.version = '0.0.1';


        this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent)
          || this.searchVersion(navigator.appVersion)
          || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS";
        this.scriptable();
      };

    this.scriptable = function(other) {
     if ( other === false
        || !document.createTextNode
        || !Array.prototype.push
        || !Object.hasOwnProperty
        || !document.createElement
        || !document.getElementsByTagName
        ) {
            return false;
        }
        
        // HACKY, Pushing classes into an array for display
        var classes = [];
        classes.push(document.body.parentNode.className + ' ' +
        this.browser + ' ' + this.version + ' ' + this.OS + ' scriptable');
        document.body.parentNode.className = classes;
      };

      this.searchString = function (data) {
        for (var i=0;i<data.length;i++)  {
          var dataString = data[i].string;
          var dataProp = data[i].prop;
          this.versionSearchString = data[i].versionSearch || data[i].identity;
          if (dataString) {
            if (dataString.indexOf(data[i].subString) != -1)
              return data[i].identity;
          }
          else if (dataProp)
            return data[i].identity;
        }
      };

      this.searchVersion = function (dataString) {
        var index = dataString.indexOf(this.versionSearchString);
        if (index == -1) return;
        return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
      };
      this.dataBrowser = [
        {
          string: navigator.userAgent,
          subString: "Chrome",
          identity: "Chrome"
        },
        {
          string: navigator.vendor,
          subString: "Apple",
          identity: "Safari",
          versionSearch: "Version"
        },
        {
          prop: window.opera,
          identity: "Opera"
        },
        {
          string: navigator.userAgent,
          subString: "Firefox",
          identity: "Firefox"
        }
      ];
      this.dataOS = [
        {
          string: navigator.platform,
          subString: "Win",
          identity: "Windows"
        },
        {
          string: navigator.platform,
          subString: "Mac",
          identity: "Mac"
        },
        {
          string: navigator.userAgent,
          subString: "iPhone",
          identity: "iPhone/iPod"
          },
        {
          string: navigator.userAgent,
          subString: "iPad",
          identity: "iPad"
          },
        {
          string: navigator.platform,
          subString: "Linux",
          identity: "Linux"
        }
      ]
    };
    var detection = new Detection()


/*  Ready()
    -----------------------------------------------  */
    window.onload = function() {
      detection.init();
    };
	


