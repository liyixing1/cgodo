package cn.gou23.cgodo.mybatis.dialect;

/**
 * MYSQL方言
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-10-9 下午11:45:37
 */
public class MySQLDialect implements IDialect {
	/**
	 * 
	 * @see com.cgodo.mybatis.dialect.IDialect#getPageString(java.lang.String,
	 *      int, int)
	 */
	public String getPageString(String sql, int offset, int limit) {
		return new StringBuffer(sql).append(" limit ").append(offset)
				.append(", ").append(limit).append(" ").toString();
	}
}
