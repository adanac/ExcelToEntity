package com.adanac.tool.e2e.Excel2Entity;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.adanac.tool.e2e.ExcelHelper;
import com.adanac.tool.e2e.exception.ExcelContentInvalidException;
import com.adanac.tool.e2e.exception.ExcelParseException;
import com.adanac.tool.e2e.exception.ExcelRegexpValidFailedException;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * 功能说明： 单元测试 参数说明：
 *
 */
public class AppTest extends TestCase {
	public AppTest() throws ExcelParseException {
		ExcelHelper.registerNewType(MyDataType.class);
		ExcelHelper.registerNewType(MyDataType2.class);
	}

	public void testApp() throws InvalidFormatException, IOException {
		ExcelHelper eh = ExcelHelper.readExcel("test_excel.xls");

		String[] headers = eh.getHeaders();
		String[][] datas = eh.getDatas();

		List<Demo> entitys = null;
		try {
			entitys = eh.toEntitys(Demo.class);
			for (Demo d : entitys) {
				System.out.println(d.toString());
			}
		} catch (ExcelParseException e) {
			System.out.println(e.getMessage());
		} catch (ExcelContentInvalidException e) {
			System.out.println(e.getMessage());
		} catch (ExcelRegexpValidFailedException e) {
			System.out.println(e.getMessage());
		}

		Assert.assertEquals(5, headers.length);
		Assert.assertEquals(3, datas.length);
		// Assert.assertNotNull(entitys);
	}

}
