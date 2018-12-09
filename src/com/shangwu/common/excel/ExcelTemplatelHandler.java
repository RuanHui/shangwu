package com.shangwu.common.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Excel操作类
 * 
 * @author W.G
 * 
 */
public class ExcelTemplatelHandler extends AbstractExcelHandler {

	public ExcelTemplatelHandler() {
	}

	public ExcelTemplatelHandler(ExcelModel excelModel) {
		super();
	}

	/**
	 * 根据模板导出Excel
	 * 
	 * @param out
	 *            输出流
	 * @param in
	 *            模板输入流
	 * @param start
	 *            数据行
	 * @param record
	 *            外部替换数据
	 * @param lists
	 *            数据列表
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void export(OutputStream out, InputStream in, int start,
			Map<String, Object> values,
			@SuppressWarnings("unchecked") List<Map<String, Object>>... lists)
			throws IOException, InvalidFormatException {
		if (lists == null || lists.length == 0) {
			return;
		}
		// 获取模板工作薄
		Workbook tplWorkbook = WorkbookFactory.create(in);// createWorkbook(ExcelModel.Excel2007,
															// tplPath);
		// 创建工作薄
		Workbook workbook = createWorkbook();
		// 循环Lists以获取工作薄数量并创建工作表
		for (int sheetIndex = 0; sheetIndex < lists.length; sheetIndex++) {
			// 获取模板工作表
			Sheet tplSheet = tplWorkbook.getSheetAt(sheetIndex);
			// 如果模板工作表不存在则退出循环
			if (tplSheet == null) {
				break;
			}
			List<Map<String, Object>> list = lists[sheetIndex];
			// 创建工作表
			Sheet sheet = workbook.createSheet(tplSheet.getSheetName());
			// 创建表头
			createHeader(tplSheet, workbook, sheet, start, values);
			// 创建表体
			createBody(tplSheet, workbook, sheet, start, list);
			// 创建表格底部
			createFooter(tplSheet, workbook, sheet, list.size(), start, values);
		}
		// 写入输出流
		workbook.write(out);
		// 关闭输出流
		out.flush();
		out.close();
	}

	/**
	 * 根据模板导出Excel
	 * 
	 * @param out
	 *            输出流
	 * @param tplPath
	 *            模板路径
	 * @param start
	 *            数据行
	 * @param record
	 *            外部替换数据
	 * @param lists
	 *            数据列表
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public void export(OutputStream out, String tplPath, int start,
			Map<String, Object> values,
			@SuppressWarnings("unchecked") List<Map<String, Object>>... lists)
			throws IOException, InvalidFormatException {
		FileInputStream in = new FileInputStream(tplPath);
		export(out, in, start, values, lists);
	}

	/**
	 * 创建模板工作薄
	 * 
	 * @param excelModel
	 * @param tplPath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Workbook createTplWorkbook(String tplPath)
			throws FileNotFoundException, IOException {
		Workbook workbook;
		switch (this.excelModel) {
		case XLSX:
		case XLSX_BIGDATA:
			workbook = new XSSFWorkbook(new FileInputStream(tplPath));
			break;
		case XLS:
		default:
			workbook = new HSSFWorkbook(new FileInputStream(tplPath));
			break;
		}
		return workbook;
	}

	/**
	 * 创建表头
	 * 
	 * @param tplSheet
	 *            模板工作表
	 * @param workbook
	 *            工作薄
	 * @param sheet
	 *            工作表
	 * @param start
	 *            数据开始行索引
	 * @param record
	 *            数据行（用于替换模板内容）
	 */
	private void createHeader(Sheet tplSheet, Workbook workbook, Sheet sheet,
			int start, Map<String, Object> values) {
		// 如果开头有合并的单元格，直接合并
		int regions = tplSheet.getNumMergedRegions();
		if (regions > 0) {
			for (int j = 0; j < regions; j++) {
				CellRangeAddress cra = tplSheet.getMergedRegion(j);
				if (cra.getFirstRow() < start) {
					sheet.addMergedRegion(cra);
				}
			}
		}
		// 循环模板表头并创建表头
		for (Row tplRow : tplSheet) {
			int rowNum = tplRow.getRowNum();
			if (rowNum < start) {
				Row row = sheet.createRow(rowNum);
				// 设置行高
				row.setHeight(tplRow.getHeight());
				for (Cell tplCell : tplRow) {
					int columnNum = tplCell.getColumnIndex();
					Cell cell = row.createCell(columnNum);
					// 创建样式
					CellStyle cellStyle = createCellStyle(tplCell, workbook);
					cell.setCellStyle(cellStyle);
					// 设置值
					setCellValue(tplCell, cell, values);
				}
			}
		}
	}

	/**
	 * 创建表体
	 * 
	 * @param tplSheet
	 *            模板工作表
	 * @param workbook
	 *            工作薄
	 * @param sheet
	 *            工作表
	 * @param start
	 *            数据开始行索引
	 * @param list
	 *            数据列表
	 */
	private void createBody(Sheet tplSheet, Workbook workbook, Sheet sheet,
			int start, List<Map<String, Object>> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		// 模板行
		Row tplRow = tplSheet.getRow(start);
		// 获取并保存数据行每列样式
		HashMap<Integer, CellStyle> cellStyles = new HashMap<Integer, CellStyle>();
		for (Cell tplCell : tplRow) {
			// 创建样式
			CellStyle cellStyle = createCellStyle(tplCell, workbook);
			cellStyles.put(tplCell.getColumnIndex(), cellStyle);
		}
		// 数据行数
		int size = list.size();
		for (int k = 0; k < size; k++) {
			Map<String, Object> record = list.get(k);
			int rowNum = start + k;
			// 创建行
			Row row = sheet.createRow(rowNum);
			// 设置行高
			row.setHeight(tplRow.getHeight());
			// 创建列
			for (Cell tplCell : tplRow) {
				int columnIndex = tplCell.getColumnIndex();
				// 创建单元格
				Cell cell = row.createCell(columnIndex);
				// 设置样式
				cell.setCellStyle(cellStyles.get(columnIndex));
				// 设置列宽
				int columnWidth = tplSheet.getColumnWidth(columnIndex);
				sheet.setColumnWidth(columnIndex, columnWidth);
				// 赋值
				setCellValue(tplCell, cell, record);
			}
		}
	}

	/**
	 * 创建表格底部
	 * 
	 * @param tplSheet
	 *            模板工作表
	 * @param workbook
	 *            工作薄
	 * @param sheet
	 *            工作表
	 * @param dataRowSize
	 *            数据行长度
	 * @param start
	 *            数据行开始索引
	 * @param record
	 *            数据行（用于替换模板内容）
	 */
	private void createFooter(Sheet tplSheet, Workbook workbook, Sheet sheet,
			int dataRowSize, int start, Map<String, Object> values) {
		// 如果底部有合并的单元格，直接合并
		int regions = tplSheet.getNumMergedRegions();
		if (regions > 0) {
			for (int j = 0; j < regions; j++) {
				CellRangeAddress tplCra = tplSheet.getMergedRegion(j);
				int firstRow = tplCra.getFirstRow();
				// 合并起始行大于数据行则为底部
				if (firstRow > start) {
					firstRow = firstRow + dataRowSize - 1;
					int lastRow = tplCra.getLastRow() + dataRowSize - 1;
					CellRangeAddress cra = new CellRangeAddress(firstRow,
							lastRow, tplCra.getFirstColumn(),
							tplCra.getLastColumn());
					sheet.addMergedRegion(cra);
				}
			}
		}
		// 循环模板底部并创建底部
		for (Row tplRow : tplSheet) {
			int rowNum = tplRow.getRowNum();
			if (rowNum > start) {
				rowNum = rowNum + dataRowSize - 1;
				Row row = sheet.createRow(rowNum);
				// 设置行高
				row.setHeight(tplRow.getHeight());
				for (Cell tplCell : tplRow) {
					int columnNum = tplCell.getColumnIndex();
					Cell cell = row.createCell(columnNum);
					// 创建样式
					CellStyle cellStyle = createCellStyle(tplCell, workbook);
					cell.setCellStyle(cellStyle);
					// 设置值
					setCellValue(tplCell, cell, values);
				}
			}
		}
	}

	/**
	 * 创建单元格样式
	 * 
	 * @param tplCellStyle
	 *            模板源样式
	 * @param workbook
	 *            工作薄
	 * @return
	 */
	private CellStyle createCellStyle(Cell tplCell, Workbook workbook) {
		CellStyle tplCellStyle = tplCell.getCellStyle();
		CellStyle cellStyle = workbook.createCellStyle();
		switch (this.excelModel) {
		case XLSX:
		case XLSX_BIGDATA:
			// 由于2007以上版本的excel克隆样式时边框会报错，导致样式不正常，故将边框重新设置
			cellStyle.cloneStyleFrom(tplCellStyle);
			cellStyle.setBorderBottom(tplCellStyle.getBorderBottom());
			cellStyle.setBorderTop(tplCellStyle.getBorderTop());
			cellStyle.setBorderLeft(tplCellStyle.getBorderLeft());
			cellStyle.setBorderRight(tplCellStyle.getBorderRight());
			cellStyle.setBottomBorderColor(tplCellStyle.getBottomBorderColor());
			break;
		case XLS:
		default:
			cellStyle.cloneStyleFrom(tplCellStyle);
			break;
		}
		return cellStyle;
	}

	/**
	 * 给单元格赋值
	 * 
	 * @param tplCell
	 *            模板单元格
	 * @param cell
	 *            单元格
	 * @param values
	 *            一条数据
	 */
	private void setCellValue(Cell tplCell, Cell cell,
			Map<String, Object> values) {
		// 判断单元格的类型获取对应类型的数据
		switch (tplCell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:// boolean
			cell.setCellValue(tplCell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:// 数字
			if (DateUtil.isCellDateFormatted(tplCell)) {// 判断是否日期
				cell.setCellValue(tplCell.getDateCellValue());
			} else {// 数字
				cell.setCellValue(tplCell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_FORMULA:// 格式
			cell.setCellFormula(tplCell.getCellFormula());
			break;
		default:// 字符串
			String cellValue = tplCell.getStringCellValue();
			if (StringUtils.isBlank(cellValue)) {
				break;
			}
			Matcher m = Pattern.compile("@\\{(.*?)\\}").matcher(cellValue);
			if (m.find()) {
				if (values == null || values.size() == 0) {
					return;
				}
				String key = m.group(1);
				Object value = values.get(key);
				// 根据不同的数据类型赋值
				if (value == null) {
					cell.setCellValue("");
				} else if (value instanceof Date) {// 日期
					// String dateValue = Utils.date2String(
					// "yyyy-MM-dd HH:mm", (Date) value);
					cell.setCellValue((Date) value);
				} else if (value instanceof Boolean) {// Boolean
					cell.setCellValue((Boolean) value);
				} else if (value instanceof Integer) {// 整型
					cell.setCellValue((Integer) value);
				} else if (value instanceof Double) {// 浮点
					cell.setCellValue((Double) value);
				} else {// 其他类型统一转换为字符串
					String v = cellValue.replaceAll(m.group(0),
							String.valueOf(value));
					cell.setCellValue(v);
				}
			} else {
				cell.setCellValue(cellValue);
			}
			break;
		}
	}
}
