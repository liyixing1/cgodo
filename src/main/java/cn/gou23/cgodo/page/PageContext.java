package cn.gou23.cgodo.page;

/**
 * 
 * 
 * 描述:分页上下文，通过线程变量ThreadLocal，保存不同线程的数据，并且在处理完毕后，要主动清理
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年7月30日 下午5:48:29
 */
public class PageContext {
	/** 线程变量,用于存放Page */
	private static final ThreadLocal<Page<?>> PAGE_THREAD_LOCAL = new ThreadLocal<Page<?>>();

	public static Page<?> get() {
		Page<?> page = PAGE_THREAD_LOCAL.get();

		return page;
	}

	public static void set(Page<?> page) {
		PAGE_THREAD_LOCAL.set(page);
	}

	public static void clear() {
		PAGE_THREAD_LOCAL.remove();
	}
}
