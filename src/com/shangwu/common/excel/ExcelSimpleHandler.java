package com.shangwu.common.excel;

import com.shangwu.common.utils.DatePubUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Excel简单操作类
 * 
 * @author Mr.Wei
 * 
 */
public class ExcelSimpleHandler extends AbstractExcelHandler {

	public ExcelSimpleHandler() {
	}

	public ExcelSimpleHandler(ExcelModel excelModel) {
		super();
	}

	/**
	 * 导出excel
	 * 
	 *            excel名称
	 *            参数
	 * @param lists
	 *            列表
	 * @throws IOException
	 */
	public void export(OutputStream out, List<Map<String,String>> columns,
			@SuppressWarnings("unchecked") List<Map<String, Object>>... lists)
			throws IOException {
		if (lists == null || lists.length == 0) {
			return;
		}
		// 创建工作簿
		Workbook workbook = this.createWorkbook();
		for (int sheetIndex = 0; sheetIndex < lists.length; sheetIndex++) {
			// 创建工作表
			Sheet sheet = workbook.createSheet("sheet" + (sheetIndex + 1));
			int size = columns.size();
			// 样式
			CellStyle cs = workbook.createCellStyle();
			cs.setBorderBottom(CellStyle.BORDER_THIN);
			cs.setBorderTop(CellStyle.BORDER_THIN);
			cs.setBorderLeft(CellStyle.BORDER_THIN);
			cs.setBorderRight(CellStyle.BORDER_THIN);
			cs.setAlignment(CellStyle.ALIGN_CENTER);
			cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			// 列头行
			Row third = null;
			// 标题行
				third = sheet.createRow(0);
			// 创建列头
			for (int i = 0; i < size; i++) {
				third.setHeightInPoints(18);
				Map<String, String> column = columns.get(i);
				String header = column.get("header");
				Cell cell = third.createCell(i);
				CellStyle headerCs = workbook.createCellStyle();
				headerCs.cloneStyleFrom(cs);
				cell.setCellStyle(headerCs);
				cell.setCellValue(header);
			}
			// 循环列表
			List<Map<String, Object>> list = lists[sheetIndex];
			int rowIndex = 1;
			for (Map<String, Object> map : list) {
				// 创建数据行
				Row row = sheet.createRow(third.getRowNum() + rowIndex);
				// 循环标题头
				for (int i = 0; i < size; i++) {
					Map<String,String> column = columns.get(i);
					String dataIndex = column.get("dataIndex");
					Integer width = Integer.parseInt(column.get("width"));
					if (width == null) {
						width = 100;
					}
					Cell cell = row.createCell(i);
					// 设置样式
					cell.setCellStyle(cs);
					sheet.setColumnWidth(i, pixel2PoiWidth(width));
					// 赋值
					this.setCellValue(cell, map.get(dataIndex));
				}
				rowIndex++;
			}
		}
		// 写入输出流
		workbook.write(out);
		// 关闭输出流
		out.flush();
		out.close();
	}
	/**
	 * 导出excel
	 * 
	 * @param excelName
	 *            excel名称
	 * @param values
	 *            参数
	 * @param lists
	 *            列表
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public void exportLists(OutputStream out, List<Map<String,String>> columns,List lists)
			throws IOException {
		if (lists == null || lists.size() == 0) {
			return;
		}
		// 创建工作簿
		Workbook workbook = this.createWorkbook();
		for (int sheetIndex = 0; sheetIndex < lists.size(); sheetIndex++) {
			// 创建工作表
			Sheet sheet = workbook.createSheet("sheet" + (sheetIndex + 1));
			int size = columns.size();
			// 样式
			CellStyle cs = workbook.createCellStyle();
			cs.setBorderBottom(CellStyle.BORDER_THIN);
			cs.setBorderTop(CellStyle.BORDER_THIN);
			cs.setBorderLeft(CellStyle.BORDER_THIN);
			cs.setBorderRight(CellStyle.BORDER_THIN);
			cs.setAlignment(CellStyle.ALIGN_CENTER);
			cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			// 列头行
			Row third = null;
			// 标题行
			third = sheet.createRow(0);
			// 创建列头
			for (int i = 0; i < size; i++) {
				third.setHeightInPoints(18);
				Map<String, String> column = columns.get(i);
				String header = column.get("header");
				Cell cell = third.createCell(i);
				CellStyle headerCs = workbook.createCellStyle();
				headerCs.cloneStyleFrom(cs);
				cell.setCellStyle(headerCs);
				cell.setCellValue(header);
			}
			// 循环列表
			List<Map<String, Object>> list = (List<Map<String, Object>>) lists.get(sheetIndex);
			int rowIndex = 1;
			for (Map<String, Object> map : list) {
				// 创建数据行
				Row row = sheet.createRow(third.getRowNum() + rowIndex);
				// 循环标题头
				for (int i = 0; i < size; i++) {
					Map<String,String> column = columns.get(i);
					String dataIndex = column.get("dataIndex");
					Integer width = Integer.parseInt(column.get("width"));
					if (width == null) {
						width = 100;
					}
					Cell cell = row.createCell(i);
					// 设置样式
					cell.setCellStyle(cs);
					sheet.setColumnWidth(i, pixel2PoiWidth(width));
					// 赋值
					this.setCellValue(cell, map.get(dataIndex));
				}
				rowIndex++;
			}
		}
		// 写入输出流
		workbook.write(out);
		// 关闭输出流
		out.flush();
		out.close();
	}
	/**
	 * 像素转poi列宽
	 * 
	 * @param pixel
	 * @return
	 */
	private int pixel2PoiWidth(int pixel) {
		return (int) (pixel * 37.5);
	}

	/**
	 * 赋值
	 * 
	 * @param cell
	 * @param value
	 */
	private void setCellValue(Cell cell, Object value) {
		// 根据不同的数据类型赋值
		if (value == null) {
			cell.setCellValue("");
		} else if (value instanceof Date) {// 日期
			cell.setCellValue( DatePubUtils.format((Date) value, "yyyy-MM-dd"));
		} else if (value instanceof Boolean) {// Boolean
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Integer) {// 整型
			cell.setCellValue((Integer) value);
		} else if (value instanceof Double) {// 浮点
			cell.setCellValue((Double) value);
		}else {// 其他类型统一转换为字符串
			cell.setCellValue(String.valueOf(value));
		}
	}

}
