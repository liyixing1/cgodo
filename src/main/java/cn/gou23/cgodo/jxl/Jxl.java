package cn.gou23.cgodo.jxl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * jxl操作工具
 * 
 * 通过变量，来隔离每次调用
 * 
 * @author liyixing liyixing1@yahoo.com.cn
 * @version 1.0
 * @since 2011-12-19 下午02:20:54
 */
public final class Jxl {
	/**
	 * 工作簿 只读 的工作薄
	 */
	private Workbook workBook;
	/**
	 * 可写的工作簿
	 */
	private WritableWorkbook writableWorkBook;

	/**
	 * 工作页
	 */
	private Sheet sheet;
	/**
	 * 工作页，可写
	 */
	private WritableSheet writableSheet;
	/**
	 * 列名及列名所在序号
	 */
	private Map<String, Integer> columnNamesIndex = new HashMap<String, Integer>();
	/**
	 * 当前正在处理的行
	 */
	private int currentRow = 0;
	/**
	 * 当前正在写入的行
	 */
	private int currentWriteRow = 0;

	/**
	 * 
	 * 描述:获取当前的工作簿
	 * 
	 * @return
	 * @author liyixing 2012-12-3 上午10:04:47
	 */
	public Workbook getWorkbook() {
		return workBook;
	}

	/**
	 * 
	 * 描述:获取当前的工作簿
	 * 
	 * @return
	 * @author liyixing 2012-12-3 上午10:04:47
	 */
	public WritableWorkbook getWritableWorkBook() {
		return writableWorkBook;
	}

	/**
	 * 
	 * 描述:初始化一个工作薄和工作目录
	 * 
	 * @param inputStream
	 *            流
	 * @author liyixing 2011-12-19 下午02:37:05
	 * @throws BiffException
	 * @throws IOException
	 */
	public void initWorkBook(InputStream inputStream) throws BiffException,
			IOException {
		initWorkBook(inputStream, null);
	}

	/**
	 * 
	 * 描述:初始化一个工作薄和工作目录
	 * 
	 * @param inputStream
	 * 
	 *            流
	 * @param sheetName
	 *            表名
	 * @author liyixing 2011-12-19 下午02:37:05
	 * @throws BiffException
	 * @throws IOException
	 */
	public void initWorkBook(InputStream inputStream, String sheetName)
			throws BiffException, IOException {
		// 创建book
		workBook = Workbook.getWorkbook(inputStream);
		getSheet(sheetName);
	}

	/**
	 * 
	 * 描述:初始化一个工作薄和工作目录
	 * 
	 * @param outputStream
	 *            流
	 * @param sheetName
	 *            表名
	 * @author liyixing 2011-12-19 下午02:37:05
	 * @throws IOException
	 */
	public void initWorkBook(OutputStream outputStream, String sheetName)
			throws IOException {
		initWorkBook(outputStream, sheetName, new WorkbookSettings());
	}

	/**
	 * 
	 * 描述:初始化一个工作薄和工作目录
	 * 
	 * @param outputStream
	 *            流
	 * @param sheetName
	 *            表名
	 * @param 工作薄设置
	 * @author liyixing 2011-12-19 下午02:37:05
	 * @throws IOException
	 */
	public void initWorkBook(OutputStream outputStream, String sheetName,
			WorkbookSettings workbookSettings) throws IOException {
		// 创建book
		writableWorkBook = Workbook.createWorkbook(outputStream,
				workbookSettings);
		addSheet(sheetName);
	}

	/**
	 * 
	 * 描述:获取当前的工作页
	 * 
	 * @return
	 * @author liyixing 2012-12-3 上午10:04:47
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * 
	 * 描述:获取当前可写的工作页
	 * 
	 * @return
	 * @author liyixing 2015年9月16日 下午3:50:56
	 */
	public WritableSheet getWritableSheet() {
		return writableSheet;
	}

	/**
	 * 
	 * 描述:下一行，如果返回false，则表示没有下一行
	 * 
	 * @return
	 * @author liyixing 2015年9月9日 上午10:36:32
	 */
	public boolean next() {
		int allRows = sheet.getRows();

		if (currentRow < allRows - 1) {
			// 下一行
			currentRow++;

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 描述:获取当前的列
	 * 
	 * @return
	 * @author liyixing 2012-12-3 上午10:04:47
	 */
	public Map<String, Integer> getColumnNames() {
		return columnNamesIndex;
	}

	/**
	 * 
	 * 描述:读取工作页
	 * 
	 * 调用后,会自动将当前表格定位到该表
	 * 
	 * @param sheetName
	 *            表名 不能为blank
	 * @author liyixing 2012-12-3 上午10:05:17
	 */
	public Sheet setSheet(Sheet sheet) {
		if (sheet == null) {
			sheet = workBook.getSheets()[0];
		}

		this.sheet = sheet;

		// 读取列名,第一行作为列名存在
		Cell[] cells = sheet.getRow(0);

		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];

			columnNamesIndex.put(cell.getContents(), i);
		}

		currentRow = 0;

		return sheet;
	}

	/**
	 * 
	 * 描述:读取工作页
	 * 
	 * 调用后,会自动将当前表格定位到该表
	 * 
	 * @param sheetName
	 *            表名 不能为blank
	 * @author liyixing 2012-12-3 上午10:05:17
	 */
	public Sheet getSheet(String sheetName) {
		Workbook book = getWorkbook();
		sheet = null;

		if (sheetName == null) {
			sheet = book.getSheets()[0];
		} else {
			sheet = book.getSheet(sheetName);
		}

		// 读取列名,第一行作为列名存在
		Cell[] cells = sheet.getRow(0);

		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];

			columnNamesIndex.put(cell.getContents(), i);
		}

		currentRow = 0;

		return sheet;
	}

	/**
	 * 
	 * 描述:读取列
	 * 
	 * 
	 * @param r
	 *            行数，从0开始，r不能超过65545 <br>
	 *            但是由于第一行（0）是作为列名行存在，所以r实际是从1开始
	 * @author liyixing 2011-12-19 下午02:42:21
	 * @throws Exception
	 */
	public String readCell(String colunm) {
		Sheet sheet = getSheet();

		// 检查列是否存在
		if (!columnNamesIndex.containsKey(colunm)) {
			throw new IllegalArgumentException("不存在的列：" + colunm);
		}

		int c = columnNamesIndex.get(colunm);

		return sheet.getCell(c, currentRow).getContents();
	}

	/**
	 * 
	 * 描述:读取所有行数据
	 * 
	 * @author liyixing 2011-12-19 下午02:42:21
	 * @throws Exception
	 */
	public String[][] readDatas() {
		Sheet sheet = getSheet();
		int allRows = sheet.getRows();
		int allColumns = sheet.getColumns();
		String[][] results = new String[allRows][allColumns];

		for (int currentRow = 1; currentRow < allRows; currentRow++) {
			for (int currentColumn = 0; currentColumn < allColumns; currentColumn++) {
				results[currentRow][currentColumn] = sheet.getCell(
						currentColumn, currentRow).getContents();
			}
		}

		return results;
	}

	/**
	 * 
	 * 描述:增加工作页
	 * 
	 * @param sheetName
	 *            表名 不能为blank
	 * @author liyixing 2012-12-3 上午10:05:17
	 * @throws IOException
	 */
	public void addSheet(String sheetName) throws IOException {
		if (sheetName == null) {
			return;
		}

		currentWriteRow = 0;

		writableSheet = writableWorkBook.createSheet(sheetName,
				writableWorkBook.getSheets().length);
	}

	/**
	 * 
	 * 描述:增加列
	 * 
	 * 会自动往指定的行后面增加列
	 * 
	 * 列的类型是Label
	 * 
	 * @param text
	 *            文字
	 * @param r
	 *            行数，从0开始，r不能超过65545
	 * @author liyixing 2011-12-19 下午02:42:21
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public void addCell(String text) throws RowsExceededException,
			WriteException {
		addCell(text, currentWriteRow);
	}

	/**
	 * 
	 * 描述:增加列
	 * 
	 * 会自动往指定的行后面增加列
	 * 
	 * 列的类型是Label
	 * 
	 * @param text
	 *            文字
	 * @param r
	 *            行数，从0开始，r不能超过65545
	 * @author liyixing 2011-12-19 下午02:42:21
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public void addCell(String text, int r) throws RowsExceededException,
			WriteException {
		Cell[] cells = writableSheet.getRow(r);
		writableSheet.addCell(new Label(cells.length, r, text));
	}

	// 进入下一行写入
	public void nextWrite() {
		currentWriteRow++;
	}

	/**
	 * 
	 * 描述:关闭
	 * 
	 * 是否自动关闭输入流，由WorkbookSettings来设置
	 * 
	 * @author liyixing 2011-12-19 下午02:42:29
	 * @throws IOException
	 * @throws WriteException
	 */
	public void close() throws WriteException, IOException {
		if (workBook != null) {
			workBook.close();
		}

		if (writableWorkBook != null) {
			writableWorkBook.write();
			writableWorkBook.close();
		}
	}
}
