package com.cgodo.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cgodo.util.UtilLog;
import com.cgodo.util.UtilModel;
import com.cgodo.util.UtilString;

/**
 * 
 * 
 * 描述:JDBC连接器
 *
 * @author liyixing
 * @version 1.0
 * @since 2017年5月22日 下午3:17:23
 */
public class Jdbc {
	public Jdbc() {

	}

	public Jdbc(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public void init() {
		if (init) {
			return;
		}

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			init = true;
		} catch (Exception e) {
			UtilLog.error("初始数据库链接失败", e);
		}
	}

	/**
	 * 
	 * 描述:链接jdbc
	 * 
	 * @return
	 * @author liyixing 2017年5月22日 下午3:19:01
	 * @throws SQLException
	 */
	public Connection connection() throws SQLException {
		init();

		getDatabaseInfo();
		return con;
	}

	// 其他数据库不需要这个方法 oracle和db2需要
	// public static String getSchema(Connection conn) throws Exception {
	// String schema;
	// schema = conn.getMetaData().getUserName();
	// if ((schema == null) || (schema.length() == 0)) {
	// throw new Exception("ORACLE数据库模式不允许为空");
	// }
	// return schema.toUpperCase().toString();
	//
	// }

	/**
	 * 
			* 描述:show status来获取表注释
			* @return
			* @throws Exception
			* @author liyixing 2017年5月23日 下午4:03:36
	 * @throws SQLException 
	 */
	public String getCommentByTableName(String table) throws SQLException{
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery("SHOW TABLE STATUS WHERE Name = '"+table+"'");
		
		if(resultSet.next()) {
			return resultSet.getString("Comment");
		}
		
		return null;
	}

	/**
	 * 
	 * 描述:获取数据库信息
	 * 
	 * @throws SQLException
	 * @author liyixing 2017年5月22日 下午3:31:39
	 * @return
	 */
	public Map<TableModel, List<ColumnModel>> getDatabaseInfo()
			throws SQLException {
		init();
		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet resultSet = dbmd.getTables(null, "%", "%",
				new String[] { "TABLE" });
		Map<TableModel, List<ColumnModel>> database = new HashMap<TableModel, List<ColumnModel>>();

		while (resultSet.next()) {
			TableModel tableModel = new TableModel();
			String tableName = resultSet.getString("TABLE_NAME");
			String tableRemark = resultSet.getString("REMARKS");

			tableModel.setTableName(tableName);

			if (StringUtils.isBlank(tableRemark)) {
				tableRemark = getCommentByTableName(tableName);
			}
			
			if (StringUtils.isBlank(tableRemark)) {
				tableRemark = tableName;
			}

			tableModel.setTableName(tableName);
			tableModel.setRemark(tableRemark);
			UtilLog.debug("表名{},{}", tableName, tableRemark);
			ResultSet rs = con.getMetaData().getColumns(null, null,
					tableName.toLowerCase(), "%");
			List<ColumnModel> columnModels = new ArrayList<Jdbc.ColumnModel>();
			database.put(tableModel, columnModels);

			while (rs.next()) {
				// System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));
				ColumnModel columnModel = new ColumnModel();
				String colName = rs.getString("COLUMN_NAME");
				columnModel.setName(colName);
				String remarks = rs.getString("REMARKS");
				if (remarks == null || remarks.equals("")) {
					remarks = colName;
				}
				columnModel.setRemark(remarks);

				String dbType = rs.getString("TYPE_NAME");
				columnModel.setTypeName(dbType);
				columnModels.add(columnModel);

				UtilLog.debug("数据库字段{},{},{}", columnModel.getName(),
						columnModel.getRemark(), columnModel.getTypeName());
			}
		}

		return database;
	}

	public void close() throws SQLException {
		con.close();
	}

	/**
	 * 驱动
	 */
	public String driver = "com.mysql.jdbc.Driver";
	/**
	 * URL
	 */
	public String url = "jdbc:mysql://localhost:3306/test";
	/**
	 * 用户名
	 */
	public String user = "root";
	/**
	 * 密码
	 */
	public String password = "";

	private boolean init = false;

	private Connection con;

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * 表信息
	 */
	public class TableModel {
		private String tableName;
		private String remark;

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
	}

	/**
	 * 
	 * 
	 * 描述：列信息
	 *
	 * @author liyixing
	 * @version 1.0
	 * @since 2017年5月22日 下午3:33:59
	 */
	public class ColumnModel {
		/**
		 * 名称
		 */
		private String name;
		/**
		 * 注释
		 */
		private String remark;
		/**
		 * 
		 * 描述:
		 * 
		 * @return
		 * @author liyixing 2017年5月22日 下午3:45:44
		 */
		private String typeName;
		/**
		 * 
		 * 描述:
		 * 
		 * @return
		 * @author liyixing 2017年5月22日 下午3:46:22
		 */
		private String valueType;

		public String getName() {
			return name;
		}

		/**
		 * 
		 * 描述:获取改字段在方法中首字符大写
		 * 
		 * @return
		 * @author liyixing 2017年5月22日 下午5:17:44
		 */
		public String getNameMethod() {
			return UtilString.firstToUpperCase(getProperty());
		}

		/**
		 * 
		 * 描述:获取改字段在方法中首字符大写
		 * 
		 * @return
		 * @author liyixing 2017年5月22日 下午5:17:44
		 */
		public String getProperty() {
			return UtilModel.dbFieldNameToProperty(name);
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;

			if (StringUtils.equals(typeName, "DATETIME")) {
				valueType = Date.class.getName();
			} else if (StringUtils.equals(typeName, "BIGINT")) {
				valueType = Long.class.getName();
			} else if (StringUtils.equals(typeName, "TEXT")) {
				valueType = String.class.getName();
			} else if (StringUtils.equals(typeName, "INT")) {
				valueType = Integer.class.getName();
			} else if (StringUtils.equals(typeName, "DECIMAL")) {
				valueType = BigDecimal.class.getName();
			} else if (StringUtils.contains(typeName, "CHAR")) {
				valueType = String.class.getName();
			}
		}

		public String getSimplTypeName() {
			return valueType.substring(valueType.lastIndexOf(".") + 1);
		}

		public String getValueType() {
			return valueType;
		}

		public void setValueType(String valueType) {
			this.valueType = valueType;
		}

		public String toString() {
			return name + "，" + remark + "，" + typeName;
		}
	}

	public static void main(String[] args) throws SQLException {
		Object o = new Jdbc(
				"net.sf.log4jdbc.DriverSpy",
				"jdbc:log4jdbc:mysql://127.0.0.1:3306/ticket?useUnicode=false&autoReconnect=true&characterEncoding=utf-8",
				"root", "2722261").getDatabaseInfo();

		System.out.println(o);
	}
}
