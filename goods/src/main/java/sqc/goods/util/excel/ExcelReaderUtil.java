package sqc.goods.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import sqc.goods.enums.ManageExceptionEnum;
import sqc.goods.exception.ManageException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析excel 文件
 * @author Administrator
 *
 */
public class ExcelReaderUtil {
	// 2003版excel 后缀名
	private static final String xls = "xls";
	// 2007版excel 后缀名
	private static final String xlsx = "xlsx";

	/**
	 * 解析excel文件
	 * @param file 后台接受到的MultipartFile类型对象
	 */
	public static List<String[]> getExcelData(MultipartFile file) throws IOException {
		// 检查文件格式
		checkFile(file);
		// 获取Workbook工作簿对象
		Workbook workbook = getWorkbook(file);

		return getExcelInnerData(workbook);
	}

	/**
	 * 解析excel
	 * @param workbook
	 * @return 内容数组
	 */
	private static List<String[]> getExcelInnerData(Workbook workbook) {
		//创建返回对象，每行都当作一个数组,所有行作为一个集合返回
		List<String[]> list = new ArrayList<>();

		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum ++){
				// 获取当前Sheet工作表
				Sheet sheet = workbook.getSheetAt(sheetNum);
				// 如果当前sheet表不存在 进入下次循环
				if (sheet == null) {
					continue;
				}

				// 获得当前sheet 的开始行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得当前sheet 的结束行
				int lastRowNum = sheet.getLastRowNum();

				// 循环除了第一行的所有行
				for (int rowNum = firstRowNum+1; rowNum < lastRowNum; rowNum++) {
					// 获得当前行
					Row row = sheet.getRow(rowNum);
					// 如果当前行为空 就进入下次循环
					if (row == null) {
						continue;
					}

					// 获得当前行的开始列
					int firstCellNum = row.getFirstCellNum();
					// 获取当前行的总列数
					int lastCellNum = row.getPhysicalNumberOfCells();
					String[] cells = new String[row.getPhysicalNumberOfCells()];

					// 循环当前行中所有的列
					for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
						Cell cell = row.getCell(cellNum);
						// 解析单元格内容 并存放到数组中
						cells[cellNum] = getCellValue(cell);
					}
					list.add(cells);
				}
			}
		}
		return list;
	}

	/**
	 * 验证文件合法性
	 * @param file 传入的excel文件
	 * @throws 1002 excel解析异常
	 */
	private static void checkFile (MultipartFile file) {
		if (file == null) {
			throw new ManageException(ManageExceptionEnum.FILE_PARSING_ERROR);
		}

		// 获取文件全名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是合法类型
		if (!(fileName.endsWith(xls) || fileName.endsWith(xlsx))) {
			throw new ManageException(ManageExceptionEnum.FILE_PARSING_ERROR);
		}
	}

	private static Workbook getWorkbook(MultipartFile file) throws IOException {
		// 获取文件全名
		String fileName = file.getOriginalFilename();
		// 创建excel文件对象
		Workbook workbook = null;
		InputStream is = null;
		try {
			// 获取文件输出流
			is = file.getInputStream();
			// 根据不同的后缀名 乘胜不同的 excel 对象
			if (fileName.endsWith(xls) || fileName.endsWith(xlsx)) {
				// 生成2007版的excel对象
				workbook = new XSSFWorkbook(is);
				// 是在XSSF基础上，POI3.8版本开始提供的支持低内存占用的操作方式
//				SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(100);
			} else {
				throw new ManageException(ManageExceptionEnum.FILE_PARSING_ERROR);
			}
		} catch (IOException e) {
			throw new ManageException(ManageExceptionEnum.FILE_PARSING_ERROR);
		} finally {
			if (is != null) {
				is.close();
			}
		}

		return workbook;
	}

	/**
	 * 解析单元格对象
	 * @param cell 单元格对象
	 * @return string 解析结果
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		String result = cell.getStringCellValue();
		// 如果excel字段格式是 包含  .0000... 就去掉   (默认解析出来的 数据 末尾带 .0)
		String reg = "\\.[0]*";
		if (result.matches(reg)) {
			result = result.replaceAll(reg, "");
		}
		return result;
	}


//	public static void main(String[] args) throws Exception {
//		InputStream is = new URL("file:C:/Users/dyjx/Desktop/人员列表20210607154124.xls").openStream();
//
//		Workbook workbook = new XSSFWorkbook(is);
//
//		List<String[]> list = getExcelInnerData(workbook);
//		for (String[] strings : list) {
//			System.out.println(Arrays.toString(strings));
//		}
//
//		is.close();
//	}
}
