package com.cgodo.verifycode.model;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.cgodo.util.UtilMisc;

/**
 * 
 * 
 * 描述:验证码
 *
 * @author liyixing
 * @version 1.0
 * @since 2016年12月11日 下午2:25:22
 */
public class VerifyCodeModel {
	/** 常用汉字 */
	public static final String CHINA_VERIFY_CODES = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc";
	/**
	 * 常用成语
	 */
	public static final List<String> CODE_IDIOM = UtilMisc.toList("水到渠成",
			"司空见惯", "顺理成章", "顺其自然", "按部就班", "墨守成规", "运筹帷幄", "日新月异", "瞬息万变",
			"望尘莫及", "措手不及", "轻重缓急", "大相径庭", "截然相反", "不谋而合", "如出一辙", "心灵手巧",
			"别出心裁", "巧夺天工", "任劳任怨", "鲜为人知", "闻名遐迩", "门庭冷落", "人迹罕至", "欲欲跃试",
			"摩拳擦掌", "踌躇满志", "莫衷一是", "见仁见智", "烟花一现", "过眼云烟", "无源之水", "天花乱坠",
			"层出不穷", "眼花缭乱", "变幻莫测", "毫不留情", "是非功过", "事过境迁", "尘埃落定", "痛定思痛",
			"物是人非", "寥寥无几", "聊若星辰", "屈指可数", "入乡随俗", "历历在目", "记忆犹新", "不寒而栗",
			"心有余悸", "事必躬亲", "明察秋毫", "明辨是非", "高瞻远瞩", "敷衍塞责", "脱颖而出", "洛阳纸贵",
			"目无全牛", "罪不容诛", "洞若观火", "对薄公堂", "耳濡目染", "耳熟能详", "耳提面命", "罚不当罪",
			"翻云覆雨", "繁文缛节", "方兴未艾", "粉墨登场", "风驰电掣", "否极泰来", "浮光掠影", "高谈阔论",
			"隔靴搔痒", "各行其是", "耿耿于怀", "功败垂成", "孤注一掷", "瓜田李下", "刮目相看", "管窥蠡测",
			"光天化日", "鬼斧神工", "含英咀华", "汗牛充栋", "厚积薄发", "虎视眈眈", "怙恶不悛", "涣然冰释",
			"荒诞不经", "黄粱一梦", "讳莫如深", "祸起萧墙", "积重难返", "计日程功", "济济一堂", "间不容发",
			"见仁见智", "见贤思齐", "矫揉造作", "金科玉律", "噤若寒蝉", "开卷有益", "空穴来风", "苦心孤诣",
			"良莠不齐", "临渊羡鱼", "令人发指", "另眼相看", "门可罗雀", "门庭若市", "面目全非", "暮鼓晨钟",
			"南辕北辙", "泥沙俱下", "抛砖引玉", "蓬荜生辉", "萍水相逢", "杞人忧天", "黔驴技穷", "忍俊不禁",
			"如坐春风", "茹毛饮血", "塞翁失马", "闪烁其词", "身体力行", "尸位素餐", "石破天惊", "拾人牙慧",
			"始作俑者", "受宠若惊", "数典忘祖", "水落石出", "夙兴夜寐", "素未平生", "叹为观止", "韬光养晦",
			"痛心疾首", "万马齐喑", "妄自菲薄", "欣喜若狂", "心悦诚服", "喜笑颜开", "喜怒无常", "喜出望外",
			"心花怒放", "喜眉笑眼", "慈眉善目", "喜不自胜", "谈笑风生", "手舞足蹈", "受宠若惊", "乐极生悲",
			"眉开眼笑", "哭丧着脸", "酒酣耳热", "惊喜欲狂", "皆大欢喜", "欢欣鼓舞", "含笑九泉", "欢天喜地",
			"欢呼雀跃", "得意忘形", "大喜过望", "眉欢眼笑", "眉飞色舞", "惊喜交加", "旭日初升", "鲜卑活跳",
			"没弄大眼", "苟且偷生", "心平气和", "喜怒无常", "生龙活虎", "拂袖而去", "肝胆欲碎", "愤愤不平",
			"别开生面", "活蹦乱跳", "百花争妍", "平易近人", "宽宏大度", "冰清玉洁", "持之以恒", "锲而不舍",
			"废寝忘食", "大义凛然", "临危不俱", "光明磊落", "不屈不挠", "鞠躬尽瘁", "死而后已", "料事如神",
			"足智多谋", "融会贯通", "学贯中西", "博古通今", "才华横溢", "出类拔萃", "博大精深", "集思广益",
			"举一反三", "悠然自得", "眉飞色舞", "喜笑颜开", "神采奕奕", "欣喜若狂", "呆若木鸡", "喜出望外",
			"垂头丧气", "无动于衷", "勃然大怒");

	/**
	 * 数字
	 */
	public static final Map<Integer, String> NUM = UtilMisc.toMap(0, "零", 1,
			"一", 2, "二", 3, "三", 4, "四", 5, "五", 6, "六", 7, "七", 8, "八", 9,
			"九", 10, "十");
	/** 运算符 */
	public static final Map<Integer, String> OPERATOR = UtilMisc.toMap(0, "加",
			1, "减", 2, "乘");

	/**
	 * 
	 * 
	 * 描述:验证码类型
	 *
	 * @author liyixing
	 * @version 1.0
	 * @since 2016年12月11日 下午2:25:39
	 */
	public enum Type {
		数学题, 汉字, 成语
	}

	public VerifyCodeModel() {
		super();

		// 随机
		int type = random.nextInt(3);

		switch (type) {
		case 0:
			generateMathVerifyCode();
			break;
		case 1:
			generateChinaVerifyCode(4);
		default:
			generateIdiomVerifyCode();
		}
	}

	/**
	 * 随机运算
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @return
	 */
	public void generateMathVerifyCode() {
		type = Type.数学题;
		int left = random.nextInt(11);
		int right = random.nextInt(11);
		int operator = random.nextInt(3);
		int result = 0;

		switch (operator) {
		case 0:
			result = left + right;
			break;
		case 1:
			result = left - right;
			break;
		case 2:
			result = left * right;
			break;
		default:
			throw new RuntimeException("无效的操作符");
		}

		code = NUM.get(left) + OPERATOR.get(operator) + NUM.get(right) + "等于";
		value = result;
	}

	/**
	 * 随机汉字
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @return
	 */
	public void generateChinaVerifyCode(int verifySize) {
		StringBuilder verifyCode = new StringBuilder(verifySize);

		for (int i = 0; i < verifySize; i++) {
			verifyCode.append(CHINA_VERIFY_CODES.charAt(random
					.nextInt(CHINA_VERIFY_CODES.length())));
		}

		type = Type.汉字;
		code = verifyCode.toString();
		value = verifyCode.toString();
	}

	/**
	 * 随机成语
	 * 
	 * @param verifySize
	 *            验证码长度
	 * @return
	 */
	public void generateIdiomVerifyCode() {
		String code = CODE_IDIOM.get(random.nextInt(CODE_IDIOM.size()));

		type = Type.成语;
		this.code = code;
		value = code;
	}

	/**
	 * 
	 * 描述:对比验证码
	 * 
	 * @param code
	 * @return
	 * @author liyixing 2016年12月11日 下午3:11:26
	 */
	public boolean equals(String code) {
		if (StringUtils.isBlank(code)) {
			return false;
		}

		if (type == Type.数学题) {
			try {
				return Integer.valueOf(code.trim()).equals(value);
			} catch (Exception e) {
				return false;
			}
		}

		if (type == Type.汉字) {
			return code.trim().equals(value);
		}

		if (type == Type.成语) {
			return code.trim().equals(value);
		}

		return false;

	}

	private Random random = new Random();
	/**
	 * 类型
	 */
	private Type type;
	/**
	 * 验证码字符串
	 */
	private String code;
	/**
	 * 验证码最终值
	 */
	private Object value;

	/**
	 * 类型
	 */
	public Type getType() {
		return type;
	}

	/**
	 * 类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 验证码字符串
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 验证码字符串
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 验证码最终值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 验证码最终值
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
