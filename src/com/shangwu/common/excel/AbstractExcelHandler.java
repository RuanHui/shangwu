package com.shangwu.common.excel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AbstractExcelHandler {
	/**
	 * Excel类型
	 */
	public ExcelModel excelModel = ExcelModel.XLS;

	public void setExcelModel(ExcelModel excelModel) {
		this.excelModel = excelModel;
	}

	public AbstractExcelHandler() {
	}

	public AbstractExcelHandler(ExcelModel excelModel) {
		this.excelModel = excelModel;
	}

	public Workbook createWorkbook() {
		Workbook workbook;
		switch (this.excelModel) {
		case XLS:
		default:
			workbook = new HSSFWorkbook();
			break;
		case XLSX:
			workbook = new XSSFWorkbook();
			break;
		case XLSX_BIGDATA:
			workbook = new SXSSFWorkbook();
			break;
		}
		return workbook;
	}
}
