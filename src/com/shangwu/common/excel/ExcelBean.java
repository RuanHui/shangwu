package com.shangwu.common.excel;

public class ExcelBean {

	private String header;//表头中文名称
	private int  dataIndex;//显示顺序
	private int width;//列表宽度
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public int getDataIndex() {
		return dataIndex;
	}
	public void setDataIndex(int dataIndex) {
		this.dataIndex = dataIndex;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
