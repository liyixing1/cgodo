package cn.gou23.cgodo.jdbc;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.xml.DOMConfigurator;

import cn.gou23.cgodo.util.UtilLog;
import cn.gou23.cgodo.util.UtilUrl;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 
 * 
 * 描述:mysqlssh驱动
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年9月17日 上午11:02:23
 */
public class MySqlSsh implements Driver {
	private static final String PRIEX = "jdbc:mysqlssh:";
	/**
	 * 代理端口
	 */
	private static final int PROXY_PORT = 13307;
	private Proxy proxy;
	/**
	 * 最后一个匹配到的驱动
	 */
	private Driver lastUnderlyingDriverRequested;

	private static final Set<String> SUBDRIVERS_SET = new TreeSet<String>();

	static {
		// 需要对接的驱动
		SUBDRIVERS_SET.add("net.sf.log4jdbc.DriverSpy");
		SUBDRIVERS_SET.add("com.mysql.jdbc.Driver");

		try {
			DriverManager.registerDriver(new MySqlSsh());

			for (Iterator<String> i = SUBDRIVERS_SET.iterator(); i.hasNext();) {
				String driverClass = (String) i.next();
				try {
					Class.forName(driverClass);
				} catch (Throwable c) {
					i.remove();
				}
			}
		} catch (SQLException s) {
			throw (RuntimeException) new RuntimeException("无法注册驱动!")
					.initCause(s);
		}
	}

	/**
	 * 
	 * 描述:计算出实际的驱动
	 * 
	 * @param url
	 * @return
	 * @throws SQLException
	 * @author liyixing 2015年9月17日 上午11:05:40
	 */
	private Driver getUnderlyingDriver(String url) throws SQLException {
		if (url.startsWith(PRIEX)) {
			url = url.substring(PRIEX.length());

			Enumeration<Driver> e = DriverManager.getDrivers();

			Driver d;

			while (e.hasMoreElements()) {
				d = (Driver) e.nextElement();

				if (d.acceptsURL(url)) {
					lastUnderlyingDriverRequested = d;
					return d;
				}
			}
		}
		return null;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public synchronized Connection connect(String url, Properties info)
			throws SQLException {
		// 查找实际驱动
		Driver d = getUnderlyingDriver(url);

		if (d == null) {
			return null;
		}

		url = url.substring(PRIEX.length());

		// 解析出实际的MYSQLIP和端口
		Pattern pattern = Pattern
				.compile("mysql://(\\d{1,}\\.\\d{1,}\\.\\d{1,}\\.\\d{1,}):+(\\d{1,})/");
		Matcher matcher = pattern.matcher(url);

		matcher.find();

		try {
			if (proxy == null) {
				proxy = new Proxy();

				// 真实的mysql地址
				String mysqlIp = matcher.group(1);
				// 真实的MYSQL端口
				String mysqlPort = matcher.group(2);
				Map<String, String> params = UtilUrl.urlToMap(url, "utf-8");
				String sshIp = params.get("sshIp");
				String sshUser = params.get("sshUser");
				String sshPassword = params.get("sshPassword");
				String sshPort = params.get("sshPort");

				proxy.setMysqlIp(mysqlIp);
				proxy.setMysqlPort(Integer.valueOf(mysqlPort));
				proxy.setSshIp(sshIp);
				proxy.setSshUser(sshUser);
				proxy.setSshPassword(sshPassword);
				proxy.setSshPort(Integer.valueOf(sshPort));
				proxy.doPorxy();
			}

			// sshIp = params
			url = url
					.replaceFirst(
							"mysql://(\\d{1,}\\.\\d{1,}\\.\\d{1,}\\.\\d{1,}):+(\\d{1,})/",
							"mysql://127.0.0.1:" + PROXY_PORT + "/");
			Connection c = d.connect(url, info);

			if (c == null) {
				throw new SQLException("无效的URL: " + url);
			}

			return c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("url解析出错", e);
		}
	}

	/**
	 * 是否自己需要处理的链接
	 * 
	 * @see java.sql.Driver#acceptsURL(java.lang.String)
	 */
	@Override
	public boolean acceptsURL(String url) throws SQLException {
		Driver d = getUnderlyingDriver(url);

		if (d != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		Driver d = getUnderlyingDriver(url);
		if (d == null) {
			return new DriverPropertyInfo[0];
		}

		return d.getPropertyInfo(url, info);
	}

	@Override
	public int getMajorVersion() {
		if (lastUnderlyingDriverRequested == null) {
			return 1;
		} else {
			return lastUnderlyingDriverRequested.getMajorVersion();
		}
	}

	@Override
	public int getMinorVersion() {
		if (lastUnderlyingDriverRequested == null) {
			return 0;
		} else {
			return lastUnderlyingDriverRequested.getMinorVersion();
		}
	}

	@Override
	public boolean jdbcCompliant() {
		return lastUnderlyingDriverRequested != null
				&& lastUnderlyingDriverRequested.jdbcCompliant();
	}

	/**
	 * 
	 * 描述:代理ssh
	 * 
	 * @author liyixing 2015年9月17日 上午11:18:03
	 */
	public static class Proxy {
		public String sshUser;// SSH连接用户名
		public String sshPassword;// SSH连接密码
		public String sshIp;// SSH服务器
		public int sshPort;// SSH访问端口
		public String mysqlIp;
		public int mysqlPort;

		public String getMysqlIp() {
			return mysqlIp;
		}

		public void setMysqlIp(String mysqlIp) {
			this.mysqlIp = mysqlIp;
		}

		public int getMysqlPort() {
			return mysqlPort;
		}

		public void setMysqlPort(int mysqlPort) {
			this.mysqlPort = mysqlPort;
		}

		public String getSshUser() {
			return sshUser;
		}

		public void setSshUser(String sshUser) {
			this.sshUser = sshUser;
		}

		public String getSshPassword() {
			return sshPassword;
		}

		public void setSshPassword(String sshPassword) {
			this.sshPassword = sshPassword;
		}

		public String getSshIp() {
			return sshIp;
		}

		public void setSshIp(String sshIp) {
			this.sshIp = sshIp;
		}

		public int getSshPort() {
			return sshPort;
		}

		public void setSshPort(int sshPort) {
			this.sshPort = sshPort;
		}

		public void doPorxy() {
			try {
				JSch jsch = new JSch();
				// 先链接到ssh，建立通道
				Session session = jsch.getSession(sshUser, sshIp, sshPort);
				session.setPassword(sshPassword);
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();
				UtilLog.debug("ssh版本信息：{}", session.getServerVersion());// 这里打印SSH服务器版本信息
				/**
				 * Registers the local port forwarding for loop-back interface.
				 * If lport is 0, the tcp port will be allocated.
				 * 
				 * 总共有三个参数，第一个参数，jsch会在本地机器开启一个端口，并把该端口，并告知上面session建立的通道，
				 * 该通道是用来做数据转发的，<br>
				 * 实际的参数需要发送给目标IP和端口 <br>
				 * 本地机器看成C，目标SSH服务器看成B，目标数据库看成A <br>
				 * 那么就是驱动链接到C，C通过通道B，发送给A
				 */
				int assinged_port = session.setPortForwardingL(PROXY_PORT,
						mysqlIp, mysqlPort);
				UtilLog.debug("assinged", assinged_port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		DOMConfigurator.configure(MySqlSsh.class
				.getResource("/cn/gou23/cgodo/resource/log4j/log4j.xml"));

		if (args.length < 6) {
			UtilLog.debug("无效的参数 需要：ssh ip port user password, mysql ip port");

			return;
		}
		UtilLog.debug("开始启动代理，本地代理端口{}", MySqlSsh.PROXY_PORT);
		Proxy proxy = new Proxy();

		// ssh
		proxy.setSshIp(args[0]);
		proxy.setSshPort(Integer.valueOf(args[1]));
		proxy.setSshUser(args[2]);
		proxy.setSshPassword(args[3]);
		UtilLog.debug("ssh ip:{} port:{} user:{} password:{}", args[0],
				args[1], args[2], args[3]);

		proxy.setMysqlIp(args[4]);
		proxy.setMysqlPort(Integer.valueOf(args[5]));
		UtilLog.debug("target mysql ip:{} port:{}", args[4], args[5]);

		proxy.doPorxy();
	}
}
