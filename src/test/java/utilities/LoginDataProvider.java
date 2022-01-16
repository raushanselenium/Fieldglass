package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import base.Base;

public class LoginDataProvider extends Base {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		//String inputPath=rb.getString("path_input");
		String inputPath=".//test-Data//Fieldglass-InputFile.xlsx";
		XLUtility xlutil=new XLUtility(inputPath);
		
		int totalRows=xlutil.getRowCount("sheet1");
		int totalCols=xlutil.getCellCount("sheet1",0);
		
		String loginData[][]=new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalCols;j++)
			{
				loginData[i-1][j]=xlutil.getCellData("sheet1", i, j);
			}
		}
		
		return loginData;
	}

}
