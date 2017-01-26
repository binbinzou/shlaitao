/**
 * @fileName validate.js
 * @description 一些验证条件
 * @author zbb
 * @dataTime 2015年8月29日 上午11:29:29
 * @company 上海北斗卫星导航平台有限公司
 * @version V1.0
 */
/**
 * @title: validate
 * @description 验证参数是否为空
 * @param:
 * @param params
 *            需要验证的参数
 * @param:
 * @returns {Boolean} 返回boolean类型
 * @return: Boolean
 * @dateTime 2015年8月18日 下午1:08:23
 * @author zbb
 */
function validate(params) {

	if (params == "") {
		return true;
	}
	return false;
}
/**
 * @title: isOverLength
 * @description 长度判断，是否超过num
 * @param:
 * @param str
 *            需要判断的字符串
 * @param:
 * @param num
 *            限长
 * @param:
 * @returns true or false
 * @return: any
 * @dateTime 2015年8月18日 下午1:11:25
 * @author zbb
 */
function isOverLength(str, num) {

	var trimStr = $.trim(str);
	if (trimStr == null)
		return 0;
	if (typeof trimStr != "string") {
		trimStr += "";
	}
	if (trimStr.replace(/[^x00-xff]/g, "01").length > num) {
		return true;
	}
	return false;
}
/**
 * @param Str
 *            需要验证的字符
 * @param num
 *            最大字节数
 * @returns {Number}
 */
function isOverChar(Str, num) {

	var i, sum;
	sum = 0;
	for (i = 0; i < Str.length; i++) {
		/**
		 * @author zbb
		 * @description 判断中文字或者英文符号等。字节数在相加，应为数据库中是utf-8所以中文三字节相加
		 */
		if ((Str.charCodeAt(i) >= 0) && (Str.charCodeAt(i) <= 255)) {
			sum = sum + 1;
		} else {
			//sum = sum + 3;//oracle utf-8 中文三字符
			sum = sum + 1;//mysql utf-8中文还是一支付
		}
	}
	if (sum > num) {
		return true;
	} else {
		return false;
	}
}
/**
 * @title: isValiFigures
 * @description 验证是否为10位整数以及2位小数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author zbb
 */
function isValiFigures10int2decimal(str) {

	var myreg = /^(([1-9]\d{0,9})|(0))(\.\d{1,2})?$/;
	return !myreg.test(str);
}
/**
 * @title: isValiFigures
 * @description 验证是否为8位整数以及2位小数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author zbb
 */
function isValiFigures8int2decimal(str) {

	var myreg = /^(([1-9]\d{0,7})|(0))(\.\d{1,2})?$/;
	return !myreg.test(str);
}
/**
 * @title: isValiFigures
 * @description 验证是否为4位整数以及1位小数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author zbb
 */
function isValiFigures6int2decimal(str) {

	var myreg = /^(([1-9]\d{0,5})|(0))(\.\d{1,2})?$/;
	return !myreg.test(str);
}
/**
 * @title: isValiFigures
 * @description 验证是否为4位整数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author zbb
 */
function isValiFigures4int(str) {

	var myreg = /^(([0-9]\d{0,3}))?$/;
	return !myreg.test(str);
}

/**
 * @title: isValiFigures
 * @description 验证是否为5位整数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author yfl
 */
function isValiFigures5int(str) {

	var myreg = /^(([0-9]\d{0,4}))?$/;
	return !myreg.test(str);
}

/**
 * @title: isValiFigures
 * @description 验证是否为10位整数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author yfl
 */
function isValiFigures10int(str) {

	var myreg = /^(([0-9]\d{0,9}))?$/;
	return !myreg.test(str);
}


/**
 * @title: isValiFigures
 * @description 验证是否为11位整数以内
 * @param:
 * @param str
 *            需要验证的子串
 * @param:
 * @returns
 * @return: any
 * @dateTime 2015年8月18日 下午2:18:54
 * @author yfl
 */
function isValiFigures11int(str) {

	var myreg = /^(([0-9]\d{0,10}))?$/;
	return !myreg.test(str);
}

/**
 * @method callFunction
 * @description 调用
 * @param e
 * @param functionName
 * @returns {Boolean}
 * @author zbb
 * @dateTime 2015年10月20日上午9:09:21
 */
function callFunction(e,functionName) {
	try {
		functionName = eval(functionName)
     } catch(e) {
         return false;
     }
     if (typeof functionName === 'function'){
    	//回车键调用查询方法
    	if (e.keyCode == 13) {
    		functionName.call(this); 
 			return false;
 		}
     } else {
    	 return false;
     }  
}
