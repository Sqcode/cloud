package sqc.goods.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * org.apache.poi 导出excel。基类
 */
@Slf4j
public class BaseExcel {
	// 表格
	private HSSFWorkbook excel;
	// 样式
	private HSSFCellStyle style;
	
	/**
	 * 输出表格对象
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean export(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean success = true;
		
		String dir = null;
		try {
			dir = create();
			File file = new File(dir + File.separator + String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".xls");
			HSSFWorkbook excel = getExcel();
			excel.write(file);
			export(file.getCanonicalPath(), fileName, request, response);
		}

		finally {
			delete(dir);
		}

		return success;
	}
	public boolean export(String fileName) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		return export(fileName, request, response);
	}
	
	/**
	 * 远程输出文件
	 * @param path
	 * @param name
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void export(String path, String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		InputStream is = null;
		try {
			response.setContentType("application/vnd.ms-excel");
//			response.addHeader("Content-Disposition",
//				"attachment;filename="+ encode(name, request));
			//attachment为以附件方式下载
			response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(
					name + ".xls",
				"utf-8"));

			os = response.getOutputStream();
			is = new BufferedInputStream(new FileInputStream(path));
			
			// 输出
			byte[] buf = new byte[1024 * 10];
			for (int len = 0; (len = is.read(buf)) > 0;) {
				os.write(buf, 0, len);
			}
		}
		finally {
			close(is);
			close(os);
		}
	}
	
	/**
	 * 创建文件夹
	 * @return
	 */
	private String create() {
		String name = String.valueOf(Calendar.getInstance().getTimeInMillis());
//		String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		ApplicationHome ah = new ApplicationHome(getClass());
		File file1 = ah.getSource();

		// 创建文件夹权限不足，通过已存在的logs去操作
		String path = file1.getParentFile().toString() + File.separator + "logs";

		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		path = path + File.separator + name;
//			path = FileUtil.getJarPath() + File.separator + name;
		file = new File(path);
		boolean success = file.mkdir();
		if (success) {
			return path;
		}
		return null;
	}
	
	/**
	 * 删除文件夹
	 * @param path
	 */
	private void delete(String path) {
		if (path == null) {
			return;
		}
		
		File file = new File(path);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				f.delete();
			}
		}

		file.delete();
	}
	
	/**
	 * 关闭流
	 * @param stream
	 */
	private void close(Closeable stream) {
		if (stream == null) {
			return;
		}
		
		try {
			stream.close();
		} 
		catch (Exception ex) {
			
		}
	}
	
//	/**
//	 * 编码文件名
//	 * @param name
//	 * @param request
//	 * @return
//	 * @throws IOException
//	 */
//	private String encode(String name, HttpServletRequest request) throws IOException {
//		String agent = request.getHeader("USER-AGENT");
//
//		if (agent != null) {
//			if(agent.indexOf("Mozilla") != -1) {
//				return MimeUtility.encodeText(name, "UTF-8", "B");
//			}
//
//			if(agent.indexOf("MSIE") != -1) {
//				String newFileName = URLEncoder.encode(name, "UTF-8");
//				newFileName = StringUtils.replace(newFileName, "+", "%20");
//				if (newFileName.length() > 150) {
//					newFileName = new String(name.getBytes("GB2312"), "ISO8859-1");
//					newFileName = StringUtils.replace(newFileName, " ", "%20");
//				}
//				return newFileName;
//			}
//		}
//
//		return name;
//	}
	
	/**
	 * 创建表格
	 * @return
	 */
	private HSSFWorkbook makeExcel() {
		// 创建表格对象
		excel = new HSSFWorkbook();
		HSSFSheet sheet = excel.createSheet();
		
		// 获取表格内容
		List<List<Object>> table = getContent();
		
		// 设置列宽
		for(int col=0; col<table.get(0).size(); col++) {
			setWidth(col, sheet);
		}
		
		// 创建表格单元
		for(int row=0; row<table.size(); row++) {
			HSSFRow hssfRow = sheet.createRow(row);
			for(int col=0; col<table.get(row).size(); col++) {
				setCell(row, col, hssfRow, table.get(row).get(col));
			}
		}

		// 同时冻结第一行 和 冻结第一列
//		sheet.createFreezePane( 1, 1, 1, 1 );
		// 只冻结第一行
		sheet.createFreezePane( 0, 1, 0, 1 );
		// 只冻结第一列
//		sheet.createFreezePane( 1, 0, 1, 0 );

		// A1至C1 筛选
		CellRangeAddress fliter = CellRangeAddress.valueOf("A1:C1");
		sheet.setAutoFilter(fliter);

		return excel;
	}
	
	/**
	 * 设置单元列宽度
	 * @param col
	 * @param sheet
	 */
	protected void setWidth(int col, HSSFSheet sheet) {
		sheet.setColumnWidth(col, 5000);
	}
	
	/**
	 * 设置单元格
	 * @param row
	 * @param col
	 * @param value
	 */
	protected void setCell(int row, int col, HSSFRow line, Object value) {
		value = (value == null ? "" : value);
		HSSFCell cell = line.createCell(col);
		cell.setCellValue(value.toString());
		cell.setCellStyle(getStyle(row, col));
	}
	
	/**
	 * 获取单元格样式
	 * @param row
	 * @param col
	 * @return
	 */
	protected HSSFCellStyle getStyle(int row, int col) {
		if(style != null) {
			return style;
		}
		
		style = getExcel().createCellStyle();
		style.setWrapText(true);
		
		HSSFFont font = excel.createFont();
		font.setFontName("微软雅黑");
		font.setFontHeightInPoints((short)11);
		style.setFont(font);
			
		return style;
	}
	
	/**
	 * 获取内容
	 * @return
	 */
	protected List<List<Object>> getContent() {
		List<List<Object>> table = new ArrayList<List<Object>>();
		return table;
	}
	
	/**
	 * 获取表格
	 * @return
	 */
	protected HSSFWorkbook getExcel() {
		if (excel == null) {
			makeExcel();
		}
		return excel;
	}
}
