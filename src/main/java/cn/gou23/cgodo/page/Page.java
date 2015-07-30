package cn.gou23.cgodo.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;

/**
 * 分页对象<br>
 * 分页原理，则通过一个分页处理器(暂时处理器只有web的)负责解析出分页对象，并存放在线程对象上下文PageContext中。 <br>
 * 对于需要分页的地方，通过PageContextHolder.getPage获取<br>
 * <b> 如果要自己编写Page处理器，那么在生成Page对象后，需要把page通过PageContext.set，将page对象放入上下文中 <br>
 * 结束后，调用clear方法，清理，否则，线程之间的数据会串联 </b>
 * 
 * @author 李义星
 */
public class Page<T> extends RowBounds implements Serializable {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 无分页对象
	 */
	public static final Page<Object> NOT_PAGE = new Page<Object>(0,
			Integer.MAX_VALUE);

	/**
	 * 
	 * 描述: 获取一个只有一条数据的Page
	 * 
	 * @return
	 * @author liyixing 2015年7月30日 下午5:51:49
	 */
	public static final <T> Page<T> getPageSizeIsOne() {
		Page<T> page = new Page<T>();

		page.setPageSize(1);

		return page;
	}

	private int pageSize = 20; // 页大小
	private int pageNo = 1; // 页码，从1开始
	private int totalCount = 0; // 总记录数
	private List<T> results; // list存放的结果

	public Page() {
		super();
	}

	public Page(int offset, int limit) {
		super(offset, limit);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			pageSize = 10;
		}

		this.pageSize = pageSize;
	}

	public void setPageSize(String pageSize) {
		if (StringUtils.isBlank(pageSize)) {
			this.pageSize = 10;
		}

		try {
			setPageSize(Integer.valueOf(pageSize));
		} catch (NumberFormatException e) {
			this.pageSize = 10;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			pageNo = 1;
		}

		this.pageNo = pageNo;
	}

	public void setPageNo(String pageNo) {
		if (StringUtils.isBlank(pageNo)) {
			this.pageNo = 0;
		}

		try {
			setPageNo(Integer.valueOf(pageNo));
		} catch (NumberFormatException e) {
			this.pageNo = 0;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			totalCount = 0;
		}

		this.totalCount = totalCount;
	}

	public void setTotalCount(Long totalCount) {
		if (totalCount == null || totalCount < 0) {
			totalCount = 0l;
		}

		this.totalCount = totalCount.intValue();
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;

		if (totalCount % pageSize != 0) {
			totalPage += 1;
		}

		return totalPage;
	}

	public List<T> getResults() {
		if (results == null) {
			results = new ArrayList<T>();
		}

		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

	public int getLimit() {
		return pageSize;
	}
}
