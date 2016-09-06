package cn.gou23.cgodo.mybatis.dialect;

/**
 * 数据库方言
 * 
 * @author liyixing
 * @version 1.0
 * @created 2011-6-16 下午04:06:55
 */
public interface IDialect {
	/**
	 * 生成分页SQL
	 * 
	 * @param sql
	 * @param hasOffset
	 *            是否为取前N条
	 * @param offset
	 *            开始
	 * @param limit
	 *            结束
	 * @return
	 */
	public String getPageString(String sql, int offset, int limit);
}