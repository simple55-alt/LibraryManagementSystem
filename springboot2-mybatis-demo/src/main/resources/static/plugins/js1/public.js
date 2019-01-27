
$(function(){

    //表单验证
    $.extend($.fn.validatebox.defaults.rules, {
        NotEmpty : { // 非空字符串验证。 easyui 原装required 不能验证空格
            validator : function(value, param) {
                return $.trim(value).length>0;
            },
            message : '该输入项为必输项'
        },
        CHS: {
            validator: function (value, param) {
                return /^[\u0391-\uFFE5]+$/.test(value);
            },
            message: '请输入汉字'
        },
        english : {// 验证英语
            validator : function(value) {
                return /^[A-Za-z]+$/i.test(value);
            },
            message : '请输入英文'
        },
        ip : {// 验证IP地址
            validator : function(value) {
                return /\d+\.\d+\.\d+\.\d+/.test(value);
            },
            message : 'IP地址格式不正确'
        },
        ZIP: {
            validator: function (value, param) {
                return /^[0-9]\d{5}$/.test(value);
            },
            message: '邮政编码不存在'
        },
        QQ: {
            validator: function (value, param) {
                return /^[1-9]\d{4,10}$/.test(value);
            },
            message: 'QQ号码不正确'
        },
        mobile: {
            validator: function (value, param) {
                return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
            },
            message: '手机号码不正确'
        },
        tel:{
            validator:function(value,param){
                return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?(-\d{1,6})?$/.test(value);
            },
            message:'电话号码不正确'
        },
        mobileAndTel: {
            validator: function (value, param) {
                return /(^([0\+]\d{2,3})\d{3,4}\-\d{3,8}$)|(^([0\+]\d{2,3})\d{3,4}\d{3,8}$)|(^([0\+]\d{2,3}){0,1}13\d{9}$)|(^\d{3,4}\d{3,8}$)|(^\d{3,4}\-\d{3,8}$)/.test(value);
            },
            message: '请正确输入电话号码'
        },
        number: {
            validator: function (value, param) {
                return /^[0-9]+.?[0-9]*$/.test(value);
            },
            message: '请输入数字'
        },
        money:{
            validator: function (value, param) {
                return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
            },
            message:'请输入正确的金额'

        },
        mone:{
            validator: function (value, param) {
                return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
            },
            message:'请输入整数或小数'

        },
        integer:{
            validator:function(value,param){
                return /^[+]?[1-9]\d*$/.test(value);
            },
            message: '请输入最小为1的整数'
        },
        integ:{
            validator:function(value,param){
                return /^[+]?[0-9]\d*$/.test(value);
            },
            message: '请输入整数'
        },
        range:{
            validator:function(value,param){
                if(/^[1-9]\d*$/.test(value)){
                    return value >= param[0] && value <= param[1]
                }else{
                    return false;
                }
            },
            message:'输入的数字在{0}到{1}之间'
        },
        minLength:{
            validator:function(value,param){
                return value.length >=param[0]
            },
            message:'至少输入{0}个字'
        },
        maxLength:{
            validator:function(value,param){
                return value.length<=param[0]
            },
            message:'最多{0}个字'
        },
        //select即选择框的验证
        selectValid:{
            validator:function(value,param){
                if(value == param[0]){
                    return false;
                }else{
                    return true ;
                }
            },
            message:'请选择'
        },
        idCode:{
            validator:function(value,param){
                return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value);
            },
            message: '请输入正确的身份证号'
        },
        loginName: {
            validator: function (value, param) {
                //数字、字母、下划线，6-18长度
                var t = /^[a-zA-Z0-9_\u4e00-\u9fa5]{6,18}$/;
                //var t = /^[a-zA-Z0-9_\u4e00-\u9fa5]+$/;只能含有汉字、数字、字母、下划线  长度并没有限制
                return t.test(value);
            },
            message: '登录名称只允许汉字、英文字母、数字及下划线,并且长度为6-18位!'
        },
        equalTo: {
            validator: function (value, param) {
                return value == $(param[0]).val();
            },
            message: '两次输入的字符不一至'
        },
        englishOrNum : {// 只能输入英文和数字
            validator : function(value) {
                return /^[a-zA-Z0-9_]{6,12}$/.test(value);
            },
            message : '请输入英文、数字或下划线,长度为6-12位'
        },
        xiaoshu:{
            validator : function(value){
                return /^(([1-9]+)|([0-9]+\.[0-9]{1,2}))$/.test(value);
            },
            message : '最多保留两位小数！'
        },
        ddPrice:{
            validator:function(value,param){
                if(/^[1-9]\d*$/.test(value)){
                    return value >= param[0] && value <= param[1];
                }else{
                    return false;
                }
            },
            message:'请输入1到100之间正整数'
        },
        jretailUpperLimit:{
            validator:function(value,param){
                if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                    return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                }else{
                    return false;
                }
            },
            message:'请输入0到100之间的最多俩位小数的数字'
        },
        rateCheck:{
            validator:function(value,param){
                if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){
                    return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);
                }else{
                    return false;
                }
            },
            message:'请输入0到1000之间的最多俩位小数的数字'
        },
        equaldDate: {
            validator: function(value, param){
                var d1 = $(param[0]).datetimebox('getValue');  //获取开始时间
                return value >=d1;  //有效范围为大于开始时间的日期
            },
            message: '结束日期不能早于开始日期!'
        }

    });


    setBeginToEndDate();


	//左侧导航菜单效果
	$('.side-menu li dt').click(function(){
		$(this).parents('li').addClass('open');
		$(this).parents('.side-menu').find('.InitialPage').removeClass('active');
		$(this).parents('li').siblings().removeClass('open');
		
	});
	$('.side-menu dd a').click(function(){
		$(this).parents('li').addClass('open');
		$(this).parents('li').siblings().removeClass('open')
	});
	$('.side-menu li').each(function(){
		//判断链接相当（当前页面导航样式）
		if($(this).find('a').attr('href') == window.location.pathname){
			$(this).addClass('open');
			$(this).siblings().find('.InitialPage').removeClass('active');
			$('.InitialPage').removeClass('active');
		}else if($('.side-menu h2 a').attr('href') == window.location.pathname){
			$('.InitialPage').addClass('active');
		}   
	});
    //Tab选项卡.
    $('.tab-nav li').click(function(){
    	var liIndex = $('.tab-nav li').index(this);
    	$(this).addClass('active').siblings().removeClass('active');
    	$('.tab-cont').eq(liIndex).fadeIn().siblings('.tab-cont').hide();
    });
    //Button下拉菜单
    $('.btn-drop-group .btn').click(function(e){
    	$(this).siblings('.drop-list').show();
    	$(this).parent().siblings().find('.drop-list').hide();
    	$(document).one('click', function(){
	        $('.btn-drop-group .drop-list').hide();
	    });
	    e.stopPropagation();
    });
    	//点击list将当前值复制于button
	    $('.btn-drop-group .drop-list li').click(function(){
	    	$(this).parent().parent().hide();
	    });
	//左侧菜单收缩
	$('.top-hd .hd-lt').click(function(){
		$('.side-nav').toggleClass('hide');
		$('.top-hd,.main-cont,.btm-ft').toggleClass('switchMenu');
		$('.top-hd .hd-lt a').toggleClass('icon-exchange');
		localStorage.setItem('setLayOut1','hide');
		localStorage.setItem('setLayOut2','switchMenu');
		localStorage.setItem('setLayOut3','icon-exchange');
		if(!$('.side-nav').hasClass('hide')){
			localStorage.removeItem('setLayOut1');
			localStorage.removeItem('setLayOut2');
			localStorage.removeItem('setLayOut3');
		}
	});
	$('.side-nav').addClass(localStorage.getItem('setLayOut1'));
	$('.top-hd,.main-cont,.btm-ft').addClass(localStorage.getItem('setLayOut2'));
	$('.top-hd .hd-lt a').addClass(localStorage.getItem('setLayOut3'));
	

	//弹出层基础效果（确认/取消/关闭）
	$('.JyesBtn').click(function(){
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display','block')){
			$('.mask').hide();
		}
	});
	$('.JnoBtn,.JclosePanel').click(function(){
		$(this).parents('.dialog').hide();
		if($('.mask').attr('display','block')){
			$('.mask').hide();
		}
	});
	//基础弹出窗
	$('.JopenPanel').click(function(){
		$('.dialog').show();
		$('.dialog').css('box-shadow','0 0 30px #d2d2d2');
	});
	//带遮罩
	$('.JopenMaskPanel').click(function(){
		$('.dialog,.mask').show();
		$('.dialog').css('box-shadow','none');
	});
	$('.mask').click(function(){
		$(this).hide();
		$('.dialog').hide();
	});
	//自动关闭消息窗口
	$('.Jmsg').click(function(){
		$('dialog').show().delay(5000).hide(0);
	});	
	//安全退出
	$('#JsSignOut').click(function(){
		layer.confirm('确定登出管理中心？', {
		  title:'系统提示',
		  btn: ['确定','取消']
		}, function(){
		  location.href = 'login.html';
		});
	});


});

//捐赠
function reciprocate(){
	layer.open({
	  type: 1,
	  skin: 'layui-layer-demo',
	  closeBtn:1,
	  anim: 2,
	  shadeClose: false,
	  title:'喝杯咖啡O(∩_∩)O',
	  content: '<div class="pl-20 pr-20">'
		  +'<table class="table table-bordered table-striped mt-10">'
		  	+'<tr>'
		  		+'<td><img src="images/wechat_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>'
		  		+'<td><img src="images/alipay_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>'
		  	+'</tr>'
		  	+'<tr class="cen">'
		  		+'<td class="text-primary">微信打赏</td>'
		  		+'<td class="text-primary">支付宝打赏</td>'
		  	+'</tr>'
		  +'</table>'
	  +'</div>'
	});
}

Date.prototype.format = function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "H+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1 ? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}


/*时间格式化*/
function formatDatebox(value,row) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}

/*时间格式化*/
function formatDateTimebox(value,row) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd HH:mm:ss"); //扩展的Date的format方法(上述插件实现)
}

/*获取表单数据，并序列化*/
function serializeObject(form){
    var o={};
    $.each(form.serializeArray(),function(index){
        if(o[this['name'] ]){
            o[this['name'] ] = o[this['name'] ] + "," + this['value'];
        }else{
            o[this['name'] ]=this['value'];
        }
    })
    return o;
}


function setBeginToEndDate() {
    var buttons = $.extend([], $.fn.datebox.defaults.buttons);
    buttons.splice(1, 0, {
        text: '清空',
        handler : function(target) {
            $(target).combo("setValue", "").combo("setText", ""); // 设置空值
            $(target).combo("hidePanel"); // 点击清空按钮之后关闭日期选择面板
        }
    });
    $.fn.datebox.defaults.buttons = buttons;
    //起止日期控制
    $("#beginTime").datebox({
        buttons: buttons,//增加清除按钮
        onSelect : function(beginDate){
            $("#endTime").datebox('enable');	//启用结束日期控件
            $("#endTime").datebox('reset');	//重置结束日期的值
            $("#endTime").datebox({required:true});//设置必填
        },
        onChange : function (beginDate) {
            if(beginDate == ''){
                $("#endTime").datebox('reset');	//重置结束日期的值
                $("#endTime").datebox({required:false});//设置不必填
                $("#endTime").datebox({disabled: true});	//不启用结束日期控件
            }
        }
    });
}

