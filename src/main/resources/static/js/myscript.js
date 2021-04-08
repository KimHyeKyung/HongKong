var imgs=[
   "url(/images/MainVisual/culture-banner-16-91.jpg)",
   "url(/images/MainVisual/nightlife-header-16-91.jpg)",
   "url(/images/MainVisual/cruise_1920x640_noparallax.jpg)"
];

var first;
var last;

/*------------------------------------------------------------------------*/
var img_lists;

$(function(){
   
   
   img_lists=$("#MainVisualImg ul li");
   imgload();
   
   
   $("#prev_btn").click(prev_btn);//이전 이미지 표시
   
   $("#next_btn").click(next_btn);//다음 이미지 표시

/*$(this).scroll(function(){
	if($(this).scrollTop()>50){
	$("#header .header-wrap").css("position","fixed");
	}else{
		$("#header .header-wrap").css("position","sticky");
	} 

});*/
});

/*------------------------------------------------------------------------*/

function imgload(){
   for(var i=0;i<imgs.length;i++){//배열의 처음부터 끝까지 반복
      img_lists.eq(i).css("background-image", imgs[i]);
   }
}



function prev_btn(){
   var imgs=$("#MainVisualImg ul");
   first=$("#MainVisualImg ul li").first();
   last=$("#MainVisualImg ul li").last();
   
   first.before(last);
   imgs.css("left",-100+'%');
   imgs.animate({left: 0},500);
   
}


function next_btn(){
      var imgs=$("#MainVisualImg ul");
      first=$("#MainVisualImg ul li").first();
      last=$("#MainVisualImg ul li").last();
      imgs.animate({left: -100+'%'},500,function(){
         last.after(first);
         imgs.css("left",0);
      });
   }