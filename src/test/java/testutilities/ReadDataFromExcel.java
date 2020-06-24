package testutilities;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReadDataFromExcel
{
    public FileInputStream fis;
    public static XSSFWorkbook workbook = null;
    public static XSSFSheet sheet = null;
    public static XSSFRow row = null;
    public static XSSFCell cell = null;

    public ReadDataFromExcel(String xlFilePath) throws Exception
    {

        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public String getCellData(String sheetName, String colName, int rowNum)
    {
        int colNum = 0;
        try
        {
            colNum = -1;

            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++)
            {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    colNum = i;
            }

            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(colNum);

            if (cell.getCellType() == CellType.STRING)
            {
                return cell.getStringCellValue();
            }
            else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA)
            {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }
            else if (cell.getCellType() == CellType.BLANK)
            {
                return "";
            }
            else
            {
                return String.valueOf(cell.getBooleanCellValue());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in Excel";
        }
    }


////This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
//    public static void setExcelFile(String Path,String SheetName) throws Exception
//    {
//        try
//        {
//            // Open the Excel file
//            FileInputStream ExcelFile = new FileInputStream(Path);
//            // Access the required test data sheet
//            workbook = new XSSFWorkbook(ExcelFile);
//            sheet = workbook.getSheet(SheetName);
//        }
//        catch (Exception e)
//        {
//            throw (e);
//        }
//
//    }
//
//    public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow)    throws Exception
//    {
//        String[][] tabArray = null;
//        try{
//            int startCol = 1;
//            int ci=0,cj=0;
//            int totalRows = 1;
//            int totalCols = 2;
//
//            tabArray=new String[totalRows][totalCols];
//            for (int j=startCol;j<=totalCols;j++, cj++)
//            {
//                tabArray[ci][cj]=getCellData(iTestCaseRow,j);
//                System.out.println(tabArray[ci][cj]);
//            }
//
//        }
//        catch (IOException e)
//        {
//            System.out.println("Could not read the Excel sheet");
//            e.printStackTrace();
//        }
//        return(tabArray);
//    }
//
//
//    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
//    public static String getCellData(int RowNum, int ColNum) throws Exception
//    {
//        try
//        {
//            XSSFCell Cell = null;
//            XSSFSheet sheet = null;
//            Cell = sheet.getRow(RowNum).getCell(ColNum);
//            return Cell.getStringCellValue();
//        }
//        catch (Exception e)
//        {
//            return"";
//        }
//    }
//
//
//    public static String getTestCaseName(String sTestCase)throws Exception
//    {
//        String value = sTestCase;
//        try
//        {
//            int posi = value.indexOf("@");
//            value = value.substring(0, posi);
//            posi = value.lastIndexOf(".");
//            value = value.substring(posi + 1);
//            return value;
//        }
//        catch (Exception e)
//        {
//            throw (e);
//        }
//    }
//
//
//    public static int getRowContains(String sTestCaseName, int colNum) throws Exception
//    {
//        int i;
//        try
//        {
//            int rowCount = getRowUsed();
//            for ( i=0 ; i<rowCount; i++){
//                if  (getCellData(i,colNum).equalsIgnoreCase(sTestCaseName))
//                {
//                    break;
//                }
//            }
//            return i;
//        }
//        catch (Exception e)
//        {
//            throw(e);
//        }
//    }
//
//
//    public static int getRowUsed() throws Exception
//    {
//        try
//        {
//            int RowCount = sheet.getLastRowNum();
//            return RowCount;
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            throw (e);
//        }
//    }




}
