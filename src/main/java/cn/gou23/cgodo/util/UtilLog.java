package cn.gou23.cgodo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j工具类
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2012-7-18 上午11:40:15
 */
public final class UtilLog {
	private static final int LOG_LEVEL_DEBUG = 2;
	private static final int LOG_LEVEL_INFO = 3;
	private static final int LOG_LEVEL_WARN = 4;
	private static final int LOG_LEVEL_ERROR = 5;
	private static final String CLASS_NAME = UtilLog.class.getName();

	/**
	 * 
	 * 描述:调试模式是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:42:18
	 */
	public static final boolean isDebugEnabled() {
		return isDebugEnabled(getLog(getStatckTraceElement()));
	}

	/**
	 * 
	 * 描述:调试模式是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:42:18
	 */
	public static final boolean isDebugEnabled(Logger log) {
		return log.isDebugEnabled();
	}

	/**
	 * 
	 * 描述:info级别是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:45:34
	 */
	public static final boolean isInfoEnabled() {
		return isInfoEnabled(getLog(getStatckTraceElement()));
	}

	/**
	 * 
	 * 描述:info级别是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:45:34
	 */
	public static final boolean isInfoEnabled(Logger log) {
		return log.isInfoEnabled();
	}

	/**
	 * 
	 * 描述:警告级别是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:45:42
	 */
	public static final boolean isWarnEnabled() {
		return isWarnEnabled(getLog(getStatckTraceElement()));
	}

	/**
	 * 
	 * 描述:警告级别是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:45:42
	 */
	public static final boolean isWarnEnabled(Logger log) {
		return log.isWarnEnabled();
	}

	/**
	 * 
	 * 描述:错误级别是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:45:51
	 */
	public static final boolean isErrorEnabled() {
		return isErrorEnabled(getLog(getStatckTraceElement()));
	}

	/**
	 * 
	 * 描述:错误级别是否开启
	 * 
	 * @return
	 * @author liyixing 2012-7-18 上午11:45:51
	 */
	public static final boolean isErrorEnabled(Logger log) {
		return log.isErrorEnabled();
	}

	/**
	 * 
	 * 描述：输出debug信息
	 * 
	 * @param message
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void debug(String format, Object... args) {
		recordLog(LOG_LEVEL_DEBUG, format, args);
	}

	/**
	 * 
	 * 描述：输出debug信息
	 * 
	 * @param message
	 * @param throwable
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void debug(String format, Throwable throwable,
			Object... args) {
		recordLog(LOG_LEVEL_DEBUG, format, throwable, args);
	}

	/**
	 * 
	 * 描述：输出info信息
	 * 
	 * @param message
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void info(String format, Object... args) {
		recordLog(LOG_LEVEL_INFO, format, args);
	}

	/**
	 * 
	 * 描述：输出info信息
	 * 
	 * @param message
	 * @param throwable
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void info(String format, Throwable throwable,
			Object... args) {
		recordLog(LOG_LEVEL_INFO, format, throwable, args);
	}

	/**
	 * 
	 * 描述：输出warn信息
	 * 
	 * @param message
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void warn(String format, Object... args) {
		recordLog(LOG_LEVEL_WARN, format, args);
	}

	/**
	 * 
	 * 描述：输出warn信息
	 * 
	 * @param message
	 * @param throwable
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void warn(String format, Throwable throwable,
			Object... args) {
		recordLog(LOG_LEVEL_WARN, format, throwable, args);
	}

	/**
	 * 
	 * 描述：输出error信息
	 * 
	 * @param message
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void error(String format, Object... args) {
		recordLog(LOG_LEVEL_ERROR, format, args);
	}

	/**
	 * 
	 * 描述：输出error信息
	 * 
	 * @param message
	 * @param throwable
	 * @author liyixing 2012-7-18 上午11:46:00
	 */
	public static final void error(String format, Throwable throwable,
			Object... args) {
		recordLog(LOG_LEVEL_ERROR, format, throwable, args);
	}

	/**
	 * 
	 * 描述:获取日志对象
	 * 
	 * @param stackTraceElement
	 * @return
	 * @author liyixing 2012-11-25 下午8:02:49
	 */
	private static final Logger getLog(StackTraceElement stackTraceElement) {
		return LoggerFactory.getLogger(stackTraceElement.getClassName());
	}

	/**
	 * 
	 * 描述:记录日志
	 * 
	 * @param level
	 *            要记录的级别
	 * @param format
	 *            可以是消息，或者格式化串
	 * @param throwable
	 *            如果有异常，则传入
	 * @param
	 * @author liyixing 2012-11-25 下午8:03:06
	 */
	private static final void recordLog(int level, String format,
			Object... args) {
		StackTraceElement stackTraceElement = getStatckTraceElement();
		Logger log = getLog(stackTraceElement);

		if (LOG_LEVEL_DEBUG == level && isDebugEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.debug(logMessagePrefix + msg);
		} else if (LOG_LEVEL_INFO == level && isInfoEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.info(logMessagePrefix + msg);
		} else if (LOG_LEVEL_WARN == level && isWarnEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.warn(logMessagePrefix + msg);
		} else if (LOG_LEVEL_ERROR == level && isErrorEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.error(logMessagePrefix + msg);
		}
	}

	/**
	 * 
	 * 描述:记录日志
	 * 
	 * @param level
	 *            要记录的级别
	 * @param format
	 *            可以是消息，或者格式化串
	 * @param throwable
	 *            如果有异常，则传入
	 * @param
	 * @author liyixing 2012-11-25 下午8:03:06
	 */
	private static final void recordLog(int level, String format,
			Throwable throwable, Object... args) {
		StackTraceElement stackTraceElement = getStatckTraceElement();
		Logger log = getLog(stackTraceElement);

		if (LOG_LEVEL_DEBUG == level && isDebugEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.debug(logMessagePrefix + msg, throwable);
		} else if (LOG_LEVEL_INFO == level && isInfoEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.info(logMessagePrefix + msg, throwable);
		} else if (LOG_LEVEL_WARN == level && isWarnEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.warn(logMessagePrefix + msg, throwable);
		} else if (LOG_LEVEL_ERROR == level && isErrorEnabled(log)) {
			String msg = String.format(format.replace("{}", "%s"), args);
			String logMessagePrefix = getPrefix(stackTraceElement);

			log.error(logMessagePrefix + msg, throwable);
		}
	}

	/**
	 * 描述:消息前缀，计算出类，方法，行
	 * 
	 * @param stackTraceElement
	 * @return
	 * @author liyixing 2015年7月30日 下午6:22:49
	 */
	private static String getPrefix(StackTraceElement stackTraceElement) {
		String logMessagePrefix = "\r\n" + stackTraceElement.getClassName()
				+ "." + stackTraceElement.getMethodName() + "() \r\n" + "line："
				+ stackTraceElement.getLineNumber() + "\r\n";// 添加前缀
		return logMessagePrefix;
	}

	/**
	 * 
	 * 描述:获取上层堆栈
	 * 
	 * @return
	 * @author liyixing 2012-11-25 下午7:52:24
	 */
	private static final StackTraceElement getStatckTraceElement() {
		Throwable throwableTemp = new Throwable(); // 创建一个临时异常，用于生成堆栈
		StackTraceElement stacks[] = throwableTemp.getStackTrace(); // 堆栈信息
		StackTraceElement stackTraceElement = null;

		for (StackTraceElement stackTraceElementTemp : stacks) {
			stackTraceElement = stackTraceElementTemp;
			// 找出第一个不是本类的堆栈信息
			if (!stackTraceElement.getClassName().equals(CLASS_NAME)) {
				break;
			}
		}

		return stackTraceElement;
	}
}
