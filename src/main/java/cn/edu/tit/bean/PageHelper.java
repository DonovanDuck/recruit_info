/**
 * 
 */
package cn.edu.tit.bean;

/**
 * @author LiMing
 * 前端分页
 */
public class PageHelper {
	private String previousPage; //前一页
	private String nextPage; //后一页
	private String currentPage; //当前页
	private String allPage; //总共页
	
	public String getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getAllPage() {
		return allPage;
	}
	public void setAllPage(String allPage) {
		this.allPage = allPage;
	}
	@Override
	public String toString() {
		return "PageHelper [previousPage=" + previousPage + ", nextPage=" + nextPage + ", currentPage=" + currentPage
				+ ", allPage=" + allPage + "]";
	}
	public PageHelper(String previousPage, String nextPage, String currentPage, String allPage) {
		super();
		this.previousPage = previousPage;
		this.nextPage = nextPage;
		this.currentPage = currentPage;
		this.allPage = allPage;
	}
	public PageHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
