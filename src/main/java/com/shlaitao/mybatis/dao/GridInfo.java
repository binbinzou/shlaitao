package com.shlaitao.mybatis.dao;

public class GridInfo {
	 
		//请求的页码参数
		public String page;
		//请求行数
		public String rows;
		//请求排序列
		public String sidx;
		//请求排序标识
		public String sord;
		
	    //页码总数
		public String total;
		//记录总数
		public String records;
		

		public String nd;
		public String search;
		public String getNd() {
			return nd;
		}



		public void setNd(String nd) {
			this.nd = nd;
		}



		public String getSearch() {
			return search;
		}



		public void setSearch(String search) {
			this.search = search;
		}



		public String getPage() {
			return page;
		}



		public void setPage(String page) {
			this.page = page;
		}



		public String getRows() {
			return rows;
		}



		public void setRows(String rows) {
			this.rows = rows;
		}







		public String getTotal() {
			return total;
		}



		public void setTotal(String total) {
			this.total = total;
		}



		public String getRecords() {
			return records;
		}



		public void setRecords(String records) {
			this.records = records;
		}



		public String getSidx() {
			return sidx;
		}



		public void setSidx(String sidx) {
			this.sidx = sidx;
		}



		public String getSord() {
			return sord;
		}



		public void setSord(String sord) {
			this.sord = sord;
		}
}
