package com.springbootweb.common.utils;

import java.util.List;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * @ClassName: ResponsePage 
 * @Description: 分页类返回结果
 *
 * @param <T>
 */
public class ResponsePage<T> {

	/** page: 当前页 */
	private int page = 1;

	/** size: 每页容量 */
	private int size;

	/** content: 当前页数据列表 */
	private List<T> content;

	/** total: 总数据个数 */
	private long total;

	/** totalPages: 总页数 */
	private int totalPages;

	public ResponsePage(List<T> list) {
		setContent(list);
		Pagination page = PageHelper.getPagination();
		setSize(page.getSize());
		setPage(page.getCurrent());
		setTotalPages(page.getPages());
		setTotal(page.getTotal());
		PageHelper.remove();
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

}
