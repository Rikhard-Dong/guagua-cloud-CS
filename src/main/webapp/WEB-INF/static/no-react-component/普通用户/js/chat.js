$('.conLeft li').on('click',function(){
		$(this).addClass('bg').siblings().removeClass('bg');
		var intername=$(this).children('.liRight').children('.intername').text();
		$('.headName').text(intername);
		$('.newsList').html('');
	})
	// $('.sendBtn').on('click',function(){
	// 	var news=$('#dope').val();
	// 	if(news==''){
	// 		alert('不能为空');
	// 	}else{
	// 		$('#dope').val('');
	// 	var str='';
	// 	str+='<li>'+
	// 			'<div class="nesHead"><img src="img/6.jpg"/></div>'+
	// 			'<div class="news"><img class="jiao" src="img/20170926103645_03_02.jpg">'+news+'</div>'+
	// 		'</li>';
	// 	$('.newsList').append(str);
	// 	setTimeout(answers,1000); 
	// 	$('.conLeft').find('li.bg').children('.liRight').children('.infor').text(news);
	// 	$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
	// }
	
	// })

	$('.ExP').on('mouseenter',function(){
		$('.emjon').show();
	})
	$('.emjon').on('mouseleave',function(){
		$('.emjon').hide();
	})
	$('.emjon li').on('click',function(){
		var imgSrc=$(this).children('img').attr('src');
		var str="";
		str+='<li>'+
				'<div class="nesHead"><img src="img/6.jpg"/></div>'+
				'<div class="news"><img class="jiao" src="img/20170926103645_03_02.jpg"><img class="Expr" src="'+imgSrc+'"></div>'+
			'</li>';
		$('.newsList').append(str);
		$('.emjon').hide();
		$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight );
	})


	$(function () {
        //评分
        
        $('.photo span').on('mouseenter',function () {
            var index = $(this).index()+1;
            $(this).prevAll().find('.high').css('z-index',1)
            $(this).find('.high').css('z-index',1)
            $(this).nextAll().find('.high').css('z-index',0)
            $('.starNum').html((index*2)+'分')
        })
        $('.photo').on('mouseleave',function () {
            $(this).find('.high').css('z-index',0)
            var count = starRating / 2
            if(count == 5) {
                $('.photo span').find('.high').css('z-index',1);
            } else {
                $('.photo span').eq(count).prevAll().find('.high').css('z-index',1);
            }
            $('.starNum').html(starRating+'分')
        })
        $('.photo span').on('click',function () {
            var index = $(this).index()+1;
            $(this).prevAll().find('.high').css('z-index',1)
            $(this).find('.high').css('z-index',1)
            starRating = index*2;
            $('.starNum').html(starRating.toFixed(1)+'分');
            alert('评分：'+(starRating+'分'))
        })
        //取消评分
        $('.cancleStar').on('click',function () {
            starRating = 0;
            $('.photo span').find('.high').css('z-index',0);
            $('.starNum').html(starRating+'分');
        })
   
    })