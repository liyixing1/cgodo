package com.cgodo.component.model;

/**
 * 
 * 
 * 描述:微信发送或者接收到的数据
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月8日 下午7:46:53
 */
public class WechatModel {
	/**
	 * appid
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 设备号
	 */
	private String device_info;

	/**
	 * 随机字符
	 */
	private String nonce_str;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 签名类型
	 */
	private String sign_type;

	/**
	 * 商品描述
	 */
	private String body;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 附加数据
	 */
	private String attach;

	/**
	 * 商户订单号
	 */
	private String out_trade_no;

	/**
	 * 标价币种<br>
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
	 */
	private String fee_type;

	/**
	 * 标价币种<br>
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
	 */
	private Integer total_fee;

	/**
	 * 终端IP
	 */
	private String spbill_create_ip;

	/**
	 * 交易起始时间
	 */
	private String time_start;

	/**
	 * 交易结束时间
	 */
	private String time_expire;

	/**
	 * 通知地址
	 */
	private String notify_url;

	/**
	 * 交易类型<br>
	 * 取值如下：JSAPI，NATIVE，APP等
	 */
	private String trade_type;

	/**
	 * 返回状态码<br>
	 * SUCCESS/FAIL此字段是通信标识，非交易标识
	 */
	private String return_code;

	/**
	 * 返回信息
	 */
	private String return_msg;

	/**
	 * 业务结果，SUCCESS/FAIL
	 */
	private String result_code;

	/**
	 * 错误代码描述
	 */
	private String err_code;

	/**
	 * 错误代码描述.结果信息描述
	 */
	private String err_code_des;

	/**
	 * 预支付交易会话标识<br>
	 * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
	 */
	private String prepay_id;

	/**
	 * 二维码链接,trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
	 */
	private String code_url;

	/**
	 * 微信订单号
	 */
	private String transaction_id;
	/**
	 * 用户标识
	 */
	private String openid;
	/**
	 * 是否关注公众账号
	 */
	private String is_subscribe;

	/**
	 * 交易状态<br>
	 * SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭 REVOKED—已撤销（刷卡支付）
	 * USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
	 */
	private String trade_state;
	
	/**
	 * token
	 */
	private String access_token;

	/**
	 * 过期时间
	 */
	private Integer expires_in;
	
	/**
	 * 票据
	 */
	private String ticket;
	
	/**
	 * 错误码
	 */
	private Integer errcode;
	/**
	 * 错误消息
	 */
	private String errmsg;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
