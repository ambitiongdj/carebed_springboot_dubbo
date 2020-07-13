package com.carebed.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImportExcelUtil {

	public static List<Map<String,Object>> readExcel(MultipartFile file, String[] cellValueEn) throws IOException {
		String fileName = file.getOriginalFilename();
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("xls".equals(extension)) {
			return read2003Excel(file, cellValueEn);
		} else if ("xlsx".equals(extension)) {
			return read2007Excel(file, cellValueEn);
		} else {
			throw new IOException("不支持的文件类型");
		}
	}

	/**
	 * 读取Office 2007 excel
	 */
	private static List<Map<String,Object>> read2007Excel(MultipartFile file, String[] cellValueEn) throws IOException {
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(file.getInputStream());
		// 读取第一章表格内容
		XSSFSheet sheet = xwb.getSheetAt(0);
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		// sheet.getPhysicalNumberOfRows() 得到excel总行数
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			Map<String,Object> data = new HashMap<String,Object>();
			row = sheet.getRow(i);
			if (row == null) {
				list.add(data);
				continue;
			}
			for (int j = 0; j <= cellValueEn.length - 1; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					data.put(cellValueEn[j],cell);
					continue;
				}
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					if("".equals(value.toString().trim())){
						value = null;
					}
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("0");// 格式化 number String
					// 字符
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
					
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("0".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("0.E+00".equals(cell.getCellStyle().getDataFormatString())) {
						value = cell.getNumericCellValue();
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()))+"";
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = null;
					break;
				default:
					value = cell.toString();
				}
				data.put(cellValueEn[j],value);
			}
			list.add(data);
		}
		return list;
	}

	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static List<Map<String,Object>> read2003Excel(MultipartFile file, String[] cellValueEn) throws IOException {
		List<Map<String,Object>> list = new LinkedList<Map<String,Object>>();
		HSSFWorkbook hwb = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = hwb.getSheetAt(0);
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
			Map<String,Object> data = new HashMap<String,Object>();
			row = sheet.getRow(i);
			if (row == null) {
				list.add(data);
				continue;
			}
			for (int j = 0; j <= cellValueEn.length - 1; j++) {
				cell = row.getCell(j);
				if (cell == null) {
					data.put(cellValueEn[j], cell);
					continue;
				}
				
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					if("".equals(value.toString().trim())){
						value = null;
					}
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					DecimalFormat df = new DecimalFormat("0");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if ("@".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("0".equals(cell.getCellStyle().getDataFormatString())) {
						value = df.format(cell.getNumericCellValue());
					} else if ("0.E+00".equals(cell.getCellStyle().getDataFormatString())) {
						value = cell.getNumericCellValue();
					} else {
						value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) + "";
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					value = null;
					break;
				default:
					value = cell.toString();
				}
				data.put(cellValueEn[j], value);
			}
			list.add(data);
		}
		return list;
	}

	/**
	 * 批量插入工具
	 * 
	 * @param list
	 * @param len
	 * @return
	 */
	public static List<List<?>> splitList(List<?> list, int len) {
		if (list == null || list.size() == 0 || len < 1) {
			return null;
		}
		List<List<?>> result = new ArrayList<List<?>>();
		int size = list.size();
		int count = (size + len - 1) / len;
		for (int i = 0; i < count; i++) {
			List<?> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
			result.add(subList);
		}
		return result;
	}
	
}
