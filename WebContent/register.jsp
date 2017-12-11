<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
                position: fixed;
                top: 0;
                width: 100%;
            }
            
            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }
            
            li a:hover:not(.active) {
                background-color: #111;
            } 
</style>
</head>

<body oncontextmenu="return false">
    <ul>
   <li><img src = images/tstree.jpg width="120px" style="float:left; margin-top:3px" ></li>
           <li><a href="About.jsp" style="float:right">About us</a></li>
        </ul>
	<div class="page-container">
		<h1>Register</h1>
		<form action="register" method="post" >
			<div class="registerForm" style="display: block">
				<div>
					<input type="text" name="firstName" placeholder="firstName" />
				</div>

				<div>
					<input type="text" name="lastName" placeholder="lastName" />
				</div>

				<div>
					<input type="text" name="email" placeholder="email" />
				</div>

				<div>
					<input type="password" name="pwd" placeholder="password">
				</div>

				<div>
					<input type="password" name="pwd1"
						placeholder="confirm your password">
				</div>

				<button id="next1" type="button">next</button>
			</div>
			<div class="form1" style="display: none">
				<h2>关于你...</h2>

				
				<div>
					<input type="text" name="headline" placeholder="headline：e.g.花果山美猴王">
				</div>

				<!-- <div>
					<input type="text" name="location" placeholder="Haerbin, Heilongjiang, China">
				</div> -->
                <select name="country">
                    <option value="China">中国</option>
                </select>
				<select name="province" onChange="set_city(this, this.form.city);" style="font-color:#333">
					<option style="color:#333" value="0">选择省</option>
					<option style="color:#333" value="Xinjiang">新疆</option>
					<option style="color:#333" value="Yunnan">云南</option>
					<option style="color:#333" value="Shanxi">山西</option>
					<option style="color:#333" value="Shandong">山东</option>
					<option style="color:#333" value="Shaanxi">陕西</option>
					<option style="color:#333" value="Qinghai">青海</option>
					<option style="color:#333" value="Ningxia">宁夏</option>
					<option style="color:#333" value="Liaoning">辽宁</option>
					<option style="color:#333" value="Jilin">吉林</option>
					<option style="color:#333" value="Jiangxi">江西</option>
					<option style="color:#333" value="Henan">河南</option>
					<option style="color:#333" value="Heilongjiang">黑龙江</option>
					<option style="color:#333" value="Hunan">湖南</option>
					<option style="color:#333" value="Hubei">湖北</option>
					<option style="color:#333" value="Hebei">河北</option>
					<option style="color:#333" value="Sichuan">四川</option>
					<option style="color:#333" value="Inner Mongolia">内蒙古</option>
					<option style="color:#333" value="Guizhou">贵州</option>
					<option style="color:#333" value="Hainan">海南</option>
					<option style="color:#333" value="Guangxi">广西</option>
					<option style="color:#333" value="Gansu">甘肃</option>
					<option style="color:#333" value="Jiangsu">江苏</option>
					<option style="color:#333" value="Zhejiang">浙江</option>
					<option style="color:#333" value="Guangdong">广东</option>
					<option style="color:#333" value="Anhui">安徽</option>
					<option style="color:#333" value="Fujian">福建</option>
					<option style="color:#333" value="Beijing City">北京市区</option>
					<option style="color:#333" value="Shanghai City">上海市区</option>
					<option style="color:#333" value="Chongqing City">重庆市区</option>
					<option style="color:#333" value="Tianjin City">天津市区</option>
				</select> <select name="city" id="citys">
					<option style="color:#333" value="0">选择城市</option>
				</select>

				<div>
					<input type="text" name="industry" placeholder="industry e.g.Computer Science">
				</div>

				<div>
					<input type="text" name="profile_url"
						placeholder="LinkedIn host page">
				</div>

				<button id="submit" type="submit">Sign in</button>
			</div>
		</form>

	</div>

	<div class="alert" style="display: none">
		<h2>消息</h2>
		<div class="alert_con">
			<p id="ts"></p>
			<p style="line-height: 70px">
				<a class="btn">确定</a>
			</p>
		</div>
	</div>


	<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"
		type="text/javascript" type="text/javascript"></script>
	<script src="supersized.3.2.7.min.js"></script>
	<script src="supersized-init.js"></script>
	<script>
		$(".btn").click(function() {
			is_hide();
		})
		var u = $("input[name=pwd]");
		var p = $("input[name=pwd1]");
		var r = $("input[name=email]");
		var reg =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		$("#next1").live('click', function() {
			if (u.val() == '' || p.val() == '') {
				$("#ts").html("密码不能为空~");
				is_show();
				return false;
			} else {
				if (u.val() != p.val()) {
					$("#ts").html("两次输入密码不一致");
					is_show();
					return false;}
				else if(!reg.exec(r.val())){ //正则验证不通过，格式不对
			                $("#ts").html("请输入合法邮箱");
			                is_show();
			                return false;
			            }
				
					else {
						$(".registerForm").hide({
			                "right" : "500px"
			            }, 600)
			            $(".form1").show({
			                "right" : "500px"
			            }, 600)
					}
			}
				}
			
			);
		
		var q = $("input[name=location]");
		$("#submit").live('click', function() {
            if (q.val() == '') {
                $("#ts").html("地区不能为空~");
                is_show();
                return false;
            }
            }
		);
		
		function is_hide() {
			$(".alert").animate({
				"top" : "-40%"
			}, 300)
		}
		function is_show() {
			$(".alert").show().animate({
				"top" : "45%"
			}, 300)
		}
		
		cities = new Object();
		values = new Object();
		cities['Shanxi']=new Array('太原', '临汾', '运城');
		values['Shanxi']=new Array('Taiyuan', 'Linfen', 'Yuncheng');
		cities['Xinjiang']=new Array('乌鲁木齐', '伊犁', '喀什');
		values['Xinjiang']=new Array('Urumqi', 'Ili Prefecture', 'Kashgar Prefecture');
		cities['Yunnan']=new Array('昆明', '曲靖', '昭通', '丽江');
		values['Yunnan']=new Array('Kunming', 'Qujing', 'Zhaotong, Yunnan', 'Lijiang');
		cities['Shandong']=new Array('济南', '青岛', '潍坊', '威海', '烟台', '济宁', '聊城', '临沂', '荷泽');
		values['Shandong']=new Array('Jinan', 'Qingdao', 'Weihai', 'Jining', 'Liaocheng', 'Linyi', 'Heze');
		cities['Shaanxi']=new Array('西安', '宝鸡', '渭南', '咸阳');
		values['Shaanxi']=new Array('Xi\u2018an', 'Baoji', 'Weinan', 'Xianyang')
		cities['Qinghai']=new Array('西宁');
		values['Qinghai']=new Array('Xining');
		cities['Ningxia']=new Array('银川');
		values['Ningxia']=new Array('Yinchuan');
		cities['Liaoning']=new Array('沈阳', '大连', '鞍山');
		values['Liaoning']=new Array('Shenyang', 'Dalian', 'Anshan');
		cities['Jilin']=new Array('长春', '吉林', '四平');
		values['Jilin']=new Array('Changchun', 'Jilin', 'Siping');
		cities['Jiangxi']=new Array('南昌', '赣州', '上饶', '宜春');
		values['Jiangxi']=new Array('Nanchang', 'Ganzhou', 'Shangrao', 'Yichun');
		cities['Henan']=new Array('郑州', '安阳', '开封', '洛阳', '南阳', '商丘', '周口', '驻马店', '平顶山');
		values['Henan']=new Array('Zhengzhou', 'Anyang', 'Kaifeng', 'Luoyang', 'Nanyang', 'Shangqiu' ,'Zhoukou', 'Zhumadian', 'Pingdingshan');
		cities['Heilongjiang']=new Array('哈尔滨', '齐齐哈尔', '绥化', '大庆');
		values['Heilongjiang']=new Array('Haerbin', 'Qiqihaer', 'Suihua', 'Daqing');
		cities['Hunan']=new Array('长沙', '常德', '郴州', '衡阳', '怀化', '邵阳', '益阳', '永州', '岳阳');
		values['Hunan']=new Array('Changsha', 'Changde', 'Chenzhou', 'Hengyang', 'Huaihua', 'Shaoyang', 'Yiyang', 'Yongzhou', 'Yueyang');
		cities['Hubei']=new Array('武汉', '黄冈', '荆州', '孝感', '宜昌');
		values['Hubei']=new Array('Wuhan', 'Huanggang', 'Jingzhou', 'Xiaogan', 'Yichang');
		cities['Hebei']=new Array('石家庄', '保定', '邯郸', '衡水', '廊坊', '唐山', '邢台', '张家口', '秦皇岛', '承德');
		values['Hebei']=new Array('Shijiazhuang', 'Baoding', 'Handan', 'Hengshui', 'Langfang', 'Tangshan', 'Xingtai', 'Zhangjiakou', 'Qinhuangdao', 'Chengde');
		cities['Sichuan']=new Array('成都', '绵阳', '南充', '德阳', '泸州', '宜宾');
		values['Sichuan']=new Array('Chengdu', 'Nanchong', 'Deyang', 'Luzhou', 'Yibin');
		cities['Inner Mongolia']=new Array('包头', '呼和浩特', '赤峰');
		values['Inner Mongolia']=new Array('Baotou', 'Huhehaote', 'Chifeng');
		cities['Guizhou']=new Array('贵阳', '遵义');
		values['Guizhou']=new Array('Guiyang', 'Zunyi');
		cities['Hainan']=new Array('海口', '三亚');
		values['Hainan']=new Array('Haikou', 'Sanya');
		cities['Guangxi']=new Array('南宁', '桂林', '玉林');
		values['Guangxi']=new Array('Nanning', 'Guilin', 'Yulin');
		cities['Gansu']=new Array('兰州');
		values['Gansu']=new Array('Lanzhou');
		cities['Jiangsu']=new Array('南京', '常州', '淮安', '连云港', '南通', '苏州', '无锡', '徐州', '盐城', '扬州');
		values['Jiangsu']=new Array('Nanjing' ,'Changzhou', 'Huaian', 'Lianyungang', 'Nantong', 'Suzhou', 'Wuxi', 'Xuzhou', 'Yangzhou');
		cities['Zhejiang']=new Array('杭州', '宁波', '嘉兴', '湖州', '金华', '绍兴', '台州', '温州');
		values['Zhejiang']=new Array('Hangzhou', 'Ningbo', 'Jiaxing', 'Huzhou', 'Jinhua', 'Shaoxing', 'Taizhou', 'Wenzhou');
		cities['Guangdong']=new Array('深圳', '广州', '佛山', '东莞', '惠州', '揭阳', '汕头', '湛江', '茂名', '珠海');
		values['Guangdong']=new Array('Shenzhen', 'Guangzhou', 'Foshan', 'Dongguan', 'Huizhou', 'Jieyang', 'Shantou', 'Zhanjiang', 'Maoming', 'Zhuhai');
		cities['Anhui']=new Array('合肥', '芜湖', '蚌埠');
		values['Anhui']=new Array('Hefei', 'Wuhu', 'Bengbu');
		cities['Fujian']=new Array('福州', '厦门', '南平', '泉州', '龙岩', '漳州', '莆田');
		values['Fujian']=new Array('Fuzhou', 'Xiamen', 'Nanping', 'Quanzhou', 'Longyan', 'Zhangzhou', 'Putian');
		cities['Tianjin City']=new Array();
		values['Tianjin City']=new Array();
		cities['Chongqing City']=new Array();
		values['Chongqing City']=new Array();
		cities['Shanghai City']=new Array();
		values['Shanghai City']=new Array();
		cities['Beijing City']=new Array();
		values['Beijing City']=new Array();

		function set_city(province, city)
		{
		    var pv, cv;
		    var i, ii;

		    pv=province.value;
		    cv=city.value;

		    city.length=1;

		    if(pv=='0') return;
		    if(typeof(cities[pv])=='undefined') return;

		    for(i=0; i<cities[pv].length; i++)
		    {
		       ii = i+1;
		       city.options[ii] = new Option();
		       city.options[ii].text = cities[pv][i];
		       city.options[ii].value = values[pv][i];
		       city.options[ii].style = "color:#333" ;
		    }
		    

		    function GetQueryString(name)
		    {
		         var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		         var r = window.location.search.substr(1).match(reg);
		         if(r!=null)return  unescape(r[2]); return null;
		    }
		     
		    
		    if (GetQueryString("email")=="red"){
		    	alert(GetQueryString("该邮箱已经被注册"));
		    }

		}
	</script>
</body>
</html>

