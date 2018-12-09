package com.shangwu.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel 解析工具，支持2003与2007
 * @author yuzhongming
 *
 */
public class PoiExcelUtil {

	private FileInputStream input;
	/** 2003 Excel对象 */
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	/** 2007 Excel对象 */
	private XSSFWorkbook xwb;
	private XSSFSheet xsheet;
	private XSSFRow xrow;
	
	/**
	 * 读取表第一行数据
	 * @param excelPath		文件路径
	 * @return              第一行数据
	 */
	public Map<String, String> readExcelFirstRow(String excelPath){
		String tableName = "";
		String tableCode = "";
		try{
			if(null!=excelPath && excelPath.length()>0){
				String suffix = excelPath.substring(excelPath.lastIndexOf(".")+1, excelPath.length());
				if(suffix.equals("xls")){
					this.input = new FileInputStream(new File(excelPath));
					this.fs = new POIFSFileSystem(this.input);
					this.wb = new HSSFWorkbook(this.fs);
					this.sheet = this.wb.getSheetAt(0);
					this.row = this.sheet.getRow(0);
					if (this.row.getCell(Integer.valueOf(1)) != null) {
						tableName = getStringCellValue(this.row.getCell(Integer.valueOf(1))).trim();
					}
					if (this.row.getCell(Integer.valueOf(5)) != null) {
						tableCode = getStringCellValue(this.row.getCell(Integer.valueOf(5))).trim();
					}
				}else if(suffix.equals("xlsx")){
					this.input = new FileInputStream(new File(excelPath));
					this.xwb = new XSSFWorkbook(this.input);
					this.xsheet = this.xwb.getSheetAt(0);
					this.xrow = this.xsheet.getRow(0);
					if (this.xrow.getCell(Integer.valueOf(1)) != null) {
						tableName = getStringCellValue(this.xrow.getCell(Integer.valueOf(1))).trim();
					}
					if (this.xrow.getCell(Integer.valueOf(5)) != null) {
						tableCode = getStringCellValue(this.xrow.getCell(Integer.valueOf(5))).trim();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", tableName);
		map.put("table_code", tableCode);
		return map;
	}
	
	/**
	 * 通过后缀判断excel是2003or2007，并读取内容
	 * @param excelPath
	 * @return
	 */
	public Map<Integer, String> readExcelContent(String excelPath){
		Map<Integer, String> map = new HashMap<Integer, String>();
		try{
			if(null!=excelPath && excelPath.length()>0){
				String suffix = excelPath.substring(excelPath.lastIndexOf(".")+1, excelPath.length());
				if(suffix.equals("xls")){
					map = this.read2003ExcelContent(excelPath);
				}else if(suffix.equals("xlsx")){
					map = this.read2007ExcelContent(excelPath);
				}
				//完成读取操作后，删除上传的文件
				File file = new File(excelPath);
				if(file.exists()){
					file.delete();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 读取2003excel的内容
	 * @param excelPath		文件路径
	 * @return				文件内容
	 */
	public Map<Integer, String> read2003ExcelContent(String excelPath) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String excelStr = "";
		try {
			this.input = new FileInputStream(new File(excelPath));
			this.fs = new POIFSFileSystem(this.input);
			this.wb = new HSSFWorkbook(this.fs);
			this.sheet = this.wb.getSheetAt(0);
			int rowNum = this.sheet.getLastRowNum();
			rowNum += 1;
			System.out.print("2003Excel总行数为" + rowNum);
			this.row = this.sheet.getRow(0);
			int colNum = this.row.getPhysicalNumberOfCells();
			System.out.println("总列数为" + colNum);
			
			for (int i = 0; i < rowNum; i++) {
				this.row = this.sheet.getRow(i);
				int j = 0;
				while (j < colNum) {
					if (this.row.getCell(Integer.valueOf(j)) == null) {
						excelStr += CommonConstants.DEFAULTV;
					} else {
						excelStr += getStringCellValue(this.row.getCell(Integer.valueOf(j))).trim();
					}
					if(++j<colNum){
						excelStr += CommonConstants.SPLITER;
					}
				}
				content.put(i, excelStr);
				excelStr = "";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.input != null)
					this.input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	
	/**
	 * 读取2007excel的内容
	 * @param excelPath		文件路径
	 * @return				文件内容
	 */
	public Map<Integer, String> read2007ExcelContent(String excelPath) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String excelStr = "";
		try {
			this.input = new FileInputStream(new File(excelPath));
			this.xwb = new XSSFWorkbook(this.input);
			this.xsheet = this.xwb.getSheetAt(0);
			int rowNum = this.xsheet.getLastRowNum();
			rowNum += 1;
			this.xrow = this.xsheet.getRow(0);
			int colNum = this.xrow.getPhysicalNumberOfCells();
			
			for (int i = 0; i < rowNum; i++) {
				this.xrow = this.xsheet.getRow(i);
				int j = 0;
				while (j < colNum) {
					if (this.xrow.getCell(Integer.valueOf(j)) == null) {
						excelStr += CommonConstants.DEFAULTV;
					} else {
						excelStr += getStringCellValue(this.xrow.getCell(Integer.valueOf(j))).trim();
					}
					if(++j<colNum){
						excelStr += CommonConstants.SPLITER;
					}
				}
				content.put(Integer.valueOf(i), excelStr);
				//System.out.println("第"+i+"行："+excelStr);
				excelStr = "";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (this.input != null)
					this.input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}
	
	/**
	 * 通过后缀判断excel是2003or2007，并读取内容
	 * @param excelPath
	 * @return
	 */
	public List<Map<Integer, String>> readExcelContent(InputStream input, String filename){
		List<Map<Integer, String>> map = new ArrayList<Map<Integer, String>>();
		try{
			if(null!=filename && filename.length()>0){
				String suffix = filename.substring(filename.lastIndexOf(".")+1, filename.length());
				if(suffix.equals("xls")){
					map = this.read2003ExcelContent(input);
				}else if(suffix.equals("xlsx")){
					map = this.read2007ExcelContent(input);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
     * 通过后缀判断excel是2003or2007，并根据指定的目标读取内容
     * @param excelPath
     * @param number 指定要读取的表单
     * @param row 指定读取的开始行
     * @return
     */
    public List<Map<Integer, String>> readExcelContentes(InputStream input, String filename,int number,int row){
        List<Map<Integer, String>> map = new ArrayList<Map<Integer, String>>();
        try{
            if(null!=filename && filename.length()>0){
                String suffix = filename.substring(filename.lastIndexOf(".")+1, filename.length());
                if(suffix.equals("xls")){
                    map = this.read2003ExcelContent(input);
                }else if(suffix.equals("xlsx")){
                    map = this.read2007ExcelContentes(input,number,row);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }
	
	/**
	 * 读取2003excel的内容
	 * @param excelPath		文件路径
	 * @return				文件内容
	 */
	public List<Map<Integer, String>> read2003ExcelContent(InputStream input) {
		List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();

		try {
			this.fs = new POIFSFileSystem(input);
			this.wb = new HSSFWorkbook(this.fs);
			this.sheet = this.wb.getSheetAt(0);
			int rowNum = this.sheet.getLastRowNum();
			rowNum += 1;
			//System.out.print("2003Excel总行数为" + rowNum);
			this.row = this.sheet.getRow(0);
			int colNum = this.row.getPhysicalNumberOfCells();
			//System.out.println("总列数为" + colNum);
			
			for (int i = 1; i < rowNum; i++) {
				Map<Integer, String> map = new HashMap<Integer, String>();
				this.row = this.sheet.getRow(i);
				int j = 0;
				while (j < colNum) {
				    //System.out.println("i = " + i + " &&　ｊ＝　" + j);
				    
					String cellv = this.getStringCellValue(this.row.getCell(Integer.valueOf(j)));
					map.put(j, cellv);
					++j;
				}
				result.add(map);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
     * 读取2003excel的内容
     * @param excelPath     文件路径
     * @param number 指定要读取的表单
     * @param row 指定读取的开始行
     * @return              文件内容
     */
    public List<Map<Integer, String>> read2003ExcelContentes(InputStream input,int number,int row) {
        List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        try {
            this.fs = new POIFSFileSystem(input);
            this.wb = new HSSFWorkbook(this.fs);
            this.sheet = this.wb.getSheetAt(number);
            int rowNum = this.sheet.getLastRowNum();
            rowNum += 1;
            //System.out.print("2003Excel总行数为" + rowNum);
            this.row = this.sheet.getRow(0);
            int colNum = this.row.getPhysicalNumberOfCells();
            //System.out.println("总列数为" + colNum);
            
            for (int i = row; i < rowNum; i++) {
                this.row = this.sheet.getRow(i);
                int j = 0;
                while (j < colNum) {
                    //System.out.println("i = " + i + " &&　ｊ＝　" + j);
                    
                    String cellv = this.getStringCellValue(this.xrow.getCell(Integer.valueOf(j)));
                    map.put(j, cellv);
                    ++j;
                }
                result.add(map);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	
	/**
	 * 读取2007excel的内容
	 * @param excelPath		文件路径
	 * @return				文件内容
	 */
	public List<Map<Integer, String>> read2007ExcelContent(InputStream input) {
		List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
		Map<Integer, String> map = null;
		try {
			this.xwb = new XSSFWorkbook(input);
			this.xsheet = this.xwb.getSheetAt(0);
			int rowNum = this.xsheet.getLastRowNum();
			rowNum += 1;
			//System.out.print("2007Excel总行数为" + rowNum);
			this.xrow = this.xsheet.getRow(0);
			int colNum = this.xrow.getPhysicalNumberOfCells();
			//System.out.println("总列数为" + colNum);
			
			for (int i = 1; i < rowNum; i++) {
				map = new HashMap<Integer, String>();
				this.xrow = this.xsheet.getRow(i);
				int j = 0;
				while (j < colNum) {
					String cellv = this.getStringCellValue(this.xrow.getCell(Integer.valueOf(j)));
					map.put(j, cellv);
					++j;
				}
				result.add(map);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
     * 按照指定的目标读取2007excel的内容
     * @param excelPath     文件路径
     * @param number 指定要读取的表单
     * @param row 指定读取的开始行
     * @return              文件内容
     */
    public List<Map<Integer, String>> read2007ExcelContentes(InputStream input,int number,int row) {
        List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
        Map<Integer, String> map = null;
        try {
            this.xwb = new XSSFWorkbook(input);
            this.xsheet = this.xwb.getSheetAt(number);
            int rowNum = this.xsheet.getLastRowNum();
            rowNum += 1;
            //System.out.print("2007Excel总行数为" + rowNum);
            this.xrow = this.xsheet.getRow(0);
            int colNum = this.xrow.getPhysicalNumberOfCells();
            //System.out.println("总列数为" + colNum);
            boolean flag = true;
            for (int i = row; i < rowNum; i++) {
                if(flag){
                    map = new HashMap<Integer, String>();
                    this.xrow = this.xsheet.getRow(i);
                    int j = 0;
                    while (j < colNum) {
                        String cellv = this.getStringCellValue(this.xrow.getCell(Integer.valueOf(j)));
                        if("".equals(cellv) || cellv == null){
                            flag = false;
                            break;
                        }
                        map.put(j, cellv);
                        ++j;
                    }
                    result.add(map);
                }else{
                    result = null;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	
    /**
     * 读取txt的内容
     * @param Input     输入流
     * @return          文件内容
     */
    public List<Map<Integer, String>> readTxtContent(InputStream input) {
        List<Map<Integer, String>> result = new ArrayList<Map<Integer, String>>();
        String encoding = "UTF-8";//设置解码方法
        BufferedReader reader = null;
        try {
            InputStreamReader inputreader = new InputStreamReader(input,encoding);
            //将解读后的信息转化为IO可以识别的数据
            reader = new BufferedReader(inputreader);
            String txtLine = null;
            String[] arrays = null;
            int rows = 0;//读取数据行数
            while((txtLine = reader.readLine()) != null){
                Map<Integer, String> map = new HashMap<Integer, String>();
                arrays = txtLine.split(",");
                for(int i = 0;i < arrays.length;i++){
                    map.put(i, arrays[i]);
                }
                result.add(map);
                rows ++;
            }
            //System.out.println(">>>>>>>>>>>>>Txt文件数据总共有"+rows+"行,"+arrays.length+"列");
            reader.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    
	/** 读取单元格内容  */
	public  static String getStringCellValue(Cell cell) {
		String strCell = "";
		if (cell == null) {
			strCell = "";
		}else {
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:  //0
					if (HSSFDateUtil.isCellDateFormatted(cell)) {//如果是日期
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date d = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						strCell = sdf.format(d);
					} else {
						DecimalFormat df = new DecimalFormat("#.##"); //保留两位小数
						strCell = df.format(cell.getNumericCellValue());
					}
					break;
				case Cell.CELL_TYPE_STRING: //1
					strCell = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN: //4
					strCell = String.valueOf(cell.getBooleanCellValue());
					break;
				case 2:
				case 3:
				default:
					strCell = null;
			}
		}
		return strCell;
	}
	
	/**
	 * 导出数据
	 * @param headers
	 * @param data
	 * @param dataList
	 * @return
	 */
	public HSSFWorkbook createExcel(String[] headers, List<Map<String, Object>> dataList, int maxCount){
		HSSFWorkbook workbook = null;
		if(null != headers && null != dataList){
			workbook = new HSSFWorkbook(); //产生工作簿对象
			HSSFSheet sheet = null; 
			int listlen = dataList.size();
			int num = (listlen % maxCount) > 0 ? (listlen / maxCount) + 1 : (listlen / maxCount);
			List<Map<String, Object>> sublist = new ArrayList<Map<String,Object>>();
			for(int n=0; n<num; n++){
				sheet = workbook.createSheet(); //产生工作表对象
				sheet.setDefaultColumnWidth(20);
				workbook.setSheetName(n,"sheet"+n);
				
				if(maxCount*(n+1) < listlen){
					sublist = dataList.subList(n*maxCount, maxCount*(n+1));
				}else{
					sublist = dataList.subList(n*maxCount, listlen);
				}
				
				int rowCount = 0;
				HSSFRow row= sheet.createRow(rowCount++);
				HSSFCell cell;
				for(int i=0; i< headers.length; i++){
					cell = row.createCell(i);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(headers[i]);
					
					CellStyle style = workbook.createCellStyle();
					Font font = workbook.createFont();
					font.setBoldweight(Font.BOLDWEIGHT_BOLD);
					
					style.setAlignment(CellStyle.ALIGN_CENTER);//居中
					style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);//设置背景色
					style.setFillPattern(CellStyle.SOLID_FOREGROUND);
					
					//style.setFillBackgroundColor(HSSFColor.BLUE.index);
					
					//font.setColor(HSSFColor.RED.index);//设置字体颜色
					style.setFont(font);//设置粗休
					cell.setCellStyle(style);
				}
				for(int i=0; i< sublist.size(); i++){
					row= sheet.createRow(rowCount++);
					Map<String, Object> map = sublist.get(i);
					int cellCount = 0;
					if(null!=map && map.size()>0){
						Set<String> set = map.keySet();
						for (Iterator ite = set.iterator(); ite.hasNext();) {
							String key = (String) ite.next();
							cell = row.createCell(cellCount++);
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellValue(map.get(key)!=null ? map.get(key).toString() : "");
						}
					}
				}
			}
		}
		return workbook;
	}
	
}
