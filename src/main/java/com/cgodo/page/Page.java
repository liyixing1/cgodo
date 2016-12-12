package com.cgodo.page;

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
public class Page extends RowBounds implements Serializable {
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 无分页对象
	 */
	public static final Page NOT_PAGE = new Page(0,
			Integer.MAX_VALUE);
	
	/**
	 * 1行数据
	 */
	public static final Page ONE_PAGE = new Page(0,
			1);

	/**
	 * 
	 * 描述: 获取一个只有一条数据的Page
	 * 
	 * @return
	 * @author liyixing 2015年7月30日 下午5:51:49
	 */
	public static final Page getPageSizeIsOne() {
		Page page = new Page();

		page.setPageSize(1);

		return page;
	}

	/**
	 * 
	 * 描述:下一页,如果返回true，表示有下一页
	 * 
	 * @return
	 * @author liyixing 2015年8月2日 下午4:12:33
	 */
	public boolean next() {
		if (getTotalPage() <= pageNo) {
			// 已经是最后一页了
			pageNo = getTotalPage();
			return false;
		}

		pageNo++;

		return true;
	}

	private int pageSize = 15; // 页大小
	private int pageNo = 1; // 页码，从1开始
	private int totalCount = 0; // 总记录数
	private List<?> results; // list存放的结果

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
		this.pageSize = pageSize;
	}

	public void setPageSize(String pageSize) {
		if (StringUtils.isBlank(pageSize)) {
			return;
		}
		
		try {
			setPageSize(Integer.valueOf(pageSize));
		} catch (NumberFormatException e) {
			return;
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
			return;
		}

		try {
			setPageNo(Integer.valueOf(pageNo));
		} catch (NumberFormatException e) {
			return;
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

	@SuppressWarnings("unchecked")
	public <T> List<T> getResults() {
		if (results == null) {
			results = new ArrayList<T>();
		}

		return (List<T>) results;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList() {
		if (results == null) {
			results = new ArrayList<T>();
		}

		return (List<T>) results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

	public int getLimit() {
		return pageSize;
	}
	
	public boolean isFirst() {
		return pageNo == 1;
	}
	
	public boolean isLast() {
		return pageNo == getTotalPage();
	}
	
	public boolean isLastPage() {
		return pageNo == getTotalPage();
	}
}
