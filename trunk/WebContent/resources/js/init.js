/* =================== Javascript Info =================== */
/*
    Javascript for ProjectName

    Version 1.0 modified on DateTimeYear

    Copyright©2011 CompanyName

    Lead Developer by Regi Ellis ( rellis@pushhere.com )
   
    Web Developer by Matt Ma. ( mma@pushhere.com )
    
    http://www.pushhere.com
*/
/* ================= End Javascript Info ================= */

/* ================= Javascript Start Now ================ */
/*  Ready
    ---------------------------------------------  */
 $(document).ready(function(){
        // Keep the lightbox cover the whole document
        var pageHeight;
        
        calculateLightboxHeight();
        
        function calculateLightboxHeight() {
            pageHeight = $(document).height()+"px";
            $('.cover').height(pageHeight);
        }
        
        $(window).resize(function(){
            calculateLightboxHeight();
        });
        
        // For the slideshows and give them the specific width
        // Checking Page has slideshow feature or not

        var slideshowOnPage = $('body').find('.slideshow').length;
        if ( slideshowOnPage > 0 ) {
           $('.slideshow').DDSlider({
            trans      : 'barTop',
            selector   : '.slider_selector',
            arrowNext: null, arrowPrev: null
            });
            var dots_count = $('.slider_selector li').size();
            $('.slider_selector').css( 'width', 25*dots_count + "px");
        }
        var dots_count = $('.slider_selector li').size();
        if ( dots_count === 0 ) {
            $('.sub-content-header, .controls').addClass('disabled');
            $('.controls').siblings('article').addClass('borderRemove');
            $('.cover').height( ( $(document).height() - 216 ) + "px");
        }
        
        // var hasContent = $('.slideshow li.current p').html().length;
        // if( hasContent === 0 ) {
        //     $('.slideshow li.current p').css("display", "none");
        //     console.log ("hjellow");
        // } else {
        //     $('.slideshow li.current p').css("display", "block");
        // }
   
        // Checking Page is home or not
        /*
        if ( $('body').hasClass('home') ) {
            $('#shell').animate({ backgroundPositionX: '-1392px'  }, 3000, 'linear' );	
        	$('#magnify-glass').animate( {'left': '437px'}, 3000, 'linear');

        	$('#magnify-glass').mousedown(function(){
        	    $('#magnify-glass:animated').stop(true, true);
                $('#shell:animated').stop(true, true);
        	});
        }
        */
 });


(function() {

         var navlinks = $('#menu-primary li:not(:last-child) a, #menu-primary li:not(:nth-child(4)) a');
         var $nonaology = $('#nonaology');
         //var $body = $('body');
         var openDropdown = false;
         var $dw = $('#nav-dropdown');
         var $slider = $('#window-slide');
         var speed = 300;
         
         function slideDropdown(value) {
            $slider.animate({ top: value }, speed);
         }

         navlinks.mouseenter(
             function(e){
                 if(!openDropdown) {
                         $('.cover').addClass('coverBox');
                         $dw.show("slide", { direction: "up" }, 400);
                         $nonaology.stop().delay(100).animate({ top: '45px' }, 400);
                         $dw.removeClass('closed').addClass('open');
                         openDropdown = true;
                 }
                 
                 var target = '#dropdown-'+ $(e.target).text().toLowerCase().replace(/ /ig, "-");
                 
                 switch(target) {
                     case '#dropdown-community' :
                            slideDropdown('0');
                     break;
                     case '#dropdown-live' :
                            slideDropdown('-260px');
                     break;
                     case '#dropdown-commerce' :
                            slideDropdown('-520px');
                     break;
                     // case '#dropdown-learn' :
                     //        slideDropdown('-780px');
                     // break;
                     case '#dropdown-medical-city' :
                            slideDropdown('-780px');
                     break;
                 }
             }
         );
         $('#menu-primary li:nth-child(4) a').mouseenter(
             function(e){
                 if(openDropdown) {
                     $('.cover').removeClass('coverBox');
                      if ($dw.hasClass('open')) {
                         $dw.hide("slide", { direction: "up" }, 400);
                         $nonaology.stop().delay(100).animate({ top: '-215px' }, 300);
                         $dw.removeClass('open').addClass('closed');   
                      }
                      openDropdown = false;
                 }
             }
         );
         $('#header').mouseleave(
            function(e){
                if(openDropdown) {
                    $('.cover').removeClass('coverBox');
                     if ($dw.hasClass('open')) {
                        $dw.hide("slide", { direction: "up" }, 400);
                        $nonaology.stop().delay(100).animate({ top: '-215px' }, 300);
                        $dw.removeClass('open').addClass('closed');   
                     }
                     openDropdown = false;
                }
            }
         );
         
         $nonaology.mouseenter(
             function(e){
                 $(this).stop().animate({ top: '10px' }, 300);
             }
          );
          
          $nonaology.mouseleave(
             function(e){
                 $(this).stop().animate({ top: '-215px' }, 200);
             }
          );
         
         $nonaology.find('span').draggable({
            axis: 'y',
            containment: "parent",
            drag: function(event, ui) {
              var currentY = ui.position.top;
              var height = 249;
              console.log(currentY);
            }
         });
})();

(function() {
    // only fire the draggable event when it is at homepage
    if ( $('body').hasClass('home') ) {
            var shelfWidth = 876;
            var centerGridPoint = 1200;
            var windowWidth, currentShelfOffsetX, circlrURL, clickCircleShow = true;
                
            // Creating the 7 Snap Points
            var $snapPointContainer = $('<div id="snapContainer">').css({'width': '995px', 'height' : '10px', 'position' : 'absolute' , 'top' : 0,  'left' : '70px', 'background' : 'transparent', "z-index" : 1 });
            $snapPointContainer.appendTo($('#shelf'));
            var snapPoints = [ -3, 145, 290, 435, 580, 725, 870 ];
            for (var i = 0; i < snapPoints.length; i ++ ) {
                $point = $('<span>').addClass('snap').css({'width': "122px", 'height' : '121px', 'display': 'block', 'position': 'absolute', 'top' : "-60px", 'left': snapPoints[i], 'background': 'transparent' });
                $point.appendTo($snapPointContainer);
            }
           
            windowWidth = $(window).width();
            
            // When Page refresh, it will position the layer in the correct initial order
            function imageBgPos() {
               var half =  $(window).width() / 2;
               if ( half > centerGridPoint ) {
                   currentShelfOffsetX = half - centerGridPoint;
               } else if ( half < centerGridPoint ) {
                   currentShelfOffsetX = - ( centerGridPoint - half );
               } else if ( half === centerGridPoint ) {
                   currentShelfOffsetX = 0;
               } 
               return currentShelfOffsetX;
            }
            
            var centerPos = imageBgPos() -3600;
            
            // Return the URL address
            function getAbsolutePath() {
                var loc = window.location;
                var pathName = loc.pathname.substring(0, loc.pathname.indexOf('/'));
                return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
            }
            
            function updateCircleURL(pos) {
                if( pos >= 0 && pos < 145 ) {
                    circlrURL = baseURL + '/community/healthy-living/';
                } else if ( pos > 147 && pos < 290 ) {
                    circlrURL = baseURL + "/community/education/";
                } else if ( pos > 292 && pos < 435 ) {
                    circlrURL = baseURL + "/live/";
                } else if ( pos > 437 && pos < 580 ) {
                    circlrURL = baseURL + "/community/";
                } else if ( pos > 582 && pos < 725 ) {
                    circlrURL = baseURL + "/medical-city/";
                } else if ( pos > 727 && pos < 870 ) {
                    circlrURL = baseURL + "/commerce/";
                } else if ( pos > 870) {
                    circlrURL = baseURL + "/community/sustainability/";
                }
                return circlrURL;
            }
            
            function updateImagePosition(e, ui) {
                // Hide the circle when it is dragging
                if( clickCircleShow ) {
                    $('#clickCircle').css('display', 'none');
                    clickCircleShow = false;
                }
                
                var currentShelfX = ui.position.left;
                var ratio = currentShelfX / shelfWidth;

                //var currentImageMove = currentShelfOffsetX1 - ( (imageWidth - currentShelfOffsetX1 - halfPanelHalfCirclr ) * ratio ) - extendedImage ;
                var currentImageMove = -( ( 7200  * ratio ) - currentShelfOffsetX );
                $('#slides')  .stop() .animate({ backgroundPositionX: currentImageMove  }, 3200,  'easeOutCirc' );
                $('#grass')   .stop() .animate({ backgroundPositionX: currentImageMove  }, 2400, 'easeOutCirc' );
                $('#circles') .stop() .animate({ backgroundPositionX: currentImageMove  }, 1600, 'easeOutCirc' );
                $('#objects') .stop() .animate({ backgroundPositionX: currentImageMove }, 800, 'easeOutCirc' );
            
            }
            
            function updateImagePositionWithArrow(magPos) {
                // Hide the circle when it is dragging
                if( clickCircleShow ) {
                    $('#clickCircle').css('display', 'none');
                    clickCircleShow = false;
                }
                
                var currentShelfX = magPos;
                var ratio = currentShelfX / shelfWidth;
                
                //var currentImageMove = currentShelfOffsetX1 - ( (imageWidth - currentShelfOffsetX1 - halfPanelHalfCirclr ) * ratio ) - extendedImage ;
                var currentImageMove = -( ( 7200  * ratio ) - currentShelfOffsetX );
                $('#slides')  .stop() .animate({ backgroundPositionX: currentImageMove  }, 3200,  'easeOutCirc' );
                $('#grass')   .stop() .animate({ backgroundPositionX: currentImageMove  }, 2400, 'easeOutCirc' );
                $('#circles') .stop() .animate({ backgroundPositionX: currentImageMove  }, 1600, 'easeOutCirc' );
                $('#objects') .stop() .animate({ backgroundPositionX: currentImageMove }, 800, 'easeOutCirc' );
            
            }
            
            // Creating the clickable circle on the homepage circle
            var baseURL = getAbsolutePath();
            var initialURL = baseURL + '/community/';
            var clickCircleLeft = $(window).width() / 2 - 150;
            var $clickCircle = $('<div id="clickCircle">').css({'width': '300px', 'height' : '300px', 'position' : 'absolute' , 'top' : '120px',  'left' : clickCircleLeft, 'background' : 'transparent', "z-index" : 40 });
            var IE7 = (navigator.appVersion.indexOf("MSIE 7.")==-1) ? false : true;
            var $clickCircleValue = $('<a>').attr('href', initialURL).css({'width': '300px', 'height' : '300px', 'display': 'block', 'background' : 'transparent'}).appendTo($clickCircle);
            if (jQuery.browser.msie ) {
               $clickCircle.appendTo($('#shell'));
            }else{
               $clickCircle.appendTo($('#slides')); 
            }
            
            $(window).resize(function(){        
                if( $('#magnify-glass').length > 0 ) {
                    var magPos = $('#magnify-glass').position().left;
                    var newCenterPos = imageBgPos() -3600;
                    imagesOffset( '438px',  newCenterPos );

                    var clickCircleLeft = $(window).width() / 2 - 150;
                    $clickCircle.css('left', clickCircleLeft);
                    $('#clickCircle a').attr('href', updateCircleURL(magPos) );
                }      
            });
           
            function imagesOffset( magPos, bgImagePos  ) {
                $('#magnify-glass').css( {'left': magPos });
                $('#slides') .css({ backgroundPositionX: bgImagePos  });
                $('#objects').css({ backgroundPositionX: bgImagePos  });
                $('#circles').css({ backgroundPositionX: bgImagePos  });
                $('#grass')  .css({ backgroundPositionX: bgImagePos  });
            }
            
            //center load the slideshow animation
            //imagesOffset( '438px',  centerPos );
            imagesOffset( '0',  imageBgPos() );
            
            var waitingInit =  setInterval(function(){
                var slidesVisible = $("#slides").css('opacity');
                if(slidesVisible === '1'){
                    init_animation();
                }
            }, 500);
            
            //Initalization animation when the page loads
            function init_animation(){
                clearInterval(waitingInit);
                $clickCircle.attr('href', initialURL);
                $('#slides')  .stop() .animate({ backgroundPositionX: centerPos  }, 5600,  'easeOutCirc' );
                $('#grass')   .stop() .animate({ backgroundPositionX: centerPos  }, 4800, 'easeOutCirc' );
                $('#circles') .stop() .animate({ backgroundPositionX: centerPos  }, 3600, 'easeOutCirc' );
                $('#objects') .stop() .animate({ backgroundPositionX: centerPos },  2400, 'easeOutCirc' );
                $('#magnify-glass') .animate({ "left" : "438px" }, 2400, 'easeOutCirc');
            }
            
            //#prev-arrow, #next-arrow var snapPoints = [ -3, 145, 290, 435, 580, 725, 870 ];  145
            //if( !clickCircleShow && magnifyGlassPos > 0 || magnifyGlassPos < 20 || magnifyGlassPos > 292  || magnifyGlassPos < 312  || magnifyGlassPos > 437 || magnifyGlassPos < 457 || magnifyGlassPos > 582 || magnifyGlassPos < 602 || magnifyGlassPos > 727 || magnifyGlassPos < 747 || magnifyGlassPos > 870 ) {
            $('#prev-arrow').click(function(event) {
                var magPos = parseInt ( $('#magnify-glass').css('left') );
                if ( magPos <= 438 && magPos > 293 ) { // Live Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "293px" }, 1800, 'easeOutCirc');
                    updateImagePositionWithArrow(293);
                } else if( magPos <= 293 && magPos > 148 ) { // Learn Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "148px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(148);
                } else if( magPos <= 148 && magPos > 0 ) { // Healthy Living Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "0px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(0);
                } else if( magPos <= 583 && magPos > 438 ) { // Medical City Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "438px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(438);
                } else if( magPos <= 728 && magPos > 583 ) { // Commerce Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "583px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(583);
                } else if( magPos <= 873 && magPos > 728 ) { // Sustainablity Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "728px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(728);
                }
            });
            
            $('#next-arrow').click(function(event) {
                var magPos = parseInt ( $('#magnify-glass').css('left') );
                if ( magPos < 438 && magPos >= 293 ) { // Live Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "438px" }, 1800, 'easeOutCirc');
                    updateImagePositionWithArrow(438);
                } else if( magPos < 293 && magPos >= 148 ) { // Learn Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "293px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(293);
                } else if( magPos < 148 && magPos >= 0 ) { // Healthy Living Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "148px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(148);
                } else if( magPos < 583 && magPos >= 438 ) { // Medical City Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "583px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(583);
                } else if( magPos < 728 && magPos >= 583 ) { // Medical City Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "728px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(728);
                } else if( magPos < 873 && magPos >= 728 ) { // Medical City Section
                    $('#magnify-glass') .stop(true, true) .animate({ "left" : "873px" }, 1800, 'easeOutCirc'); 
                    updateImagePositionWithArrow(873);
                }
            });
           
            $('#magnify-glass').draggable({ 
                axis: "x", 
                containment: "parent",
                snap: '.snap',
                snapMode : 'inner',
                snapTolerance : 30,
                cursor: 'move',
                
                drag: function(event, ui) {
                    updateImagePosition(event, ui);
                }
            });
            
            $('.snap').droppable({ 
                drop: function(event, ui) {
                    var magnifyGlassPos = ui.position.left;
                    var currentURL = updateCircleURL(magnifyGlassPos);
                    $('#clickCircle a').attr('href', currentURL);
                    if( !clickCircleShow && magnifyGlassPos > 0 || magnifyGlassPos < 20 || magnifyGlassPos > 292  || magnifyGlassPos < 312  || magnifyGlassPos > 437 || magnifyGlassPos < 457 || magnifyGlassPos > 582 || magnifyGlassPos < 602 || magnifyGlassPos > 727 || magnifyGlassPos < 747 || magnifyGlassPos > 870 ) {
                        $('#clickCircle').css('display', 'block');
                        clickCircleShow = true;
                    }
                }
            });

    }
})();

(function(){
  
    var Options = {
       wrapper : $('#timeline ul'),
       article : $('#timeline li'),
     leftClick : $('#timeline-left'),
    rightClick : $('#timeline-right')
    };
    
    var length = Options.wrapper.find('li').size();
    var width = Options.article.width();
    var totalWidth = width * length;
    var slideLength = Math.ceil( length / 4 ); // Total number of the slides
    var self = Options.article.clone();
    var slideWidth = width * 4;
    var isLast = false,
        isFirst = true,
        count = 1,
        slow = 'slow',
        fast = 'fast',
        slideSpeed = 600;
        
    var originalWidth = Options.wrapper.css('width', totalWidth + 'px');

    Options.leftClick.click(function(event){
             event.preventDefault();
             event.stopPropagation();
                 
             var move = '+=' + slideWidth.toString() + 'px';
                 
             if( !isLast ) {
               if( count > 1 ) {
                   $('#timeline ul:not(:animated)').animate({ left : move}, slideSpeed, 'swing', function(){
                        count--;
                    });
               }
             } 
             else {
               $('#timeline ul:not(:animated)').animate({ left : move }, slideSpeed, 'swing',function(){
                   $('#timeline-right').fadeIn(slow);
                   isLast = false;
                   count--;
                   if( count === 1 ) {
                      $('#timeline-left').fadeOut(slow);
                      isFirst = true;
                   }
               });
             }
    });

    Options.rightClick.click(function(event){
            event.preventDefault();
            event.stopPropagation();
    
            var move = '-=' + slideWidth.toString() + 'px';
              
            if( slideLength > count ) {
                $('#timeline ul:not(:animated)').animate({ left : move }, slideSpeed,  'swing',function(){
                   if( isFirst ) {
                     $('#timeline-left').css('display', 'block').fadeIn(slow);
                     isFirst = false;
                   }
                   if( slideLength - 1 === count ) {
                      //isLast = true;
                      //Options.wrapper.css('width', totalWidth * 2 + 'px').append(self);
                      var mods = 0;
                      var timeline = []
                      var modules = Options.wrapper.children();
                      while (mods<=3) {
                         // timeline.push(modules.eq(mods));

                         mods++
                      }

                   }
                   count++;
                });
            }
     });
})();

// (function() {
//     
//     var sociallinks = $('.social-media');
//     sociallinks.attr('target', 'blank');
//         
// })();
