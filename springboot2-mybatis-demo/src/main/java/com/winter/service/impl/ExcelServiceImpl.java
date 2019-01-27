package com.winter.service.impl;


import com.winter.dao.BookDao;
import com.winter.model.SysBook;
import com.winter.service.ExcelService;
import com.winter.util.MyException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelServiceImpl
 * @Description TODO
 * @Author 张振镇
 * @Date 2019/1/27 17:24
 * @Version 1.0
 */
@Service
@Transactional
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void importExcel(String fileName, MultipartFile file) {

        boolean notNull = false;
        List<SysBook> sysBookList = new ArrayList<>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return;
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Workbook wb = null;
        try {
            if (isExcel2003) {
                    wb = new HSSFWorkbook(is);
            } else {
//                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        for (int r = 2; r <= sheet.getLastRowNum(); r++) {//r = 2 表示从第三行开始循环 如果你的第三行开始是数据
            Row row = sheet.getRow(r);//通过sheet表单对象得到 行对象
            if (row == null){
                continue;
            }

            //sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException

            SysBook  sysBook = new SysBook();

            if( row.getCell(0).getCellType() !=1){//循环时，得到每一行的单元格进行判断
                throw new MyException("导入失败(第"+(r+1)+"行,用户名请设为文本格式)");
            }

            String sysBookname = row.getCell(0).getStringCellValue();//得到每一行第一个单元格的值




            if(sysBookname == null || sysBookname.isEmpty()){//判断是否为空
                throw new MyException("导入失败(第"+(r+1)+"行,用户名未填写)");
            }


            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);//得到每一行的 第二个单元格的值
            String password = row.getCell(1).getStringCellValue();


            if(password==null || password.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,密码未填写)");
            }


            //完整的循环一次 就组成了一个对象
            sysBook.setBookName(sysBookname);
            sysBook.setBookAuthor(password);
            sysBookList.add(sysBook);
        }
        for (SysBook sysBookResord : sysBookList) {
            String name = sysBookResord.getBookName();
            Map<String,Object> param = new HashMap<>();
            param.put("bookName",name);
            SysBook cnt = bookDao.selectOneByKey(param);
            if (cnt == null) {
                bookDao.insert(sysBookResord);
                System.out.println(" 插入 "+sysBookResord);
            } else {
                bookDao.updateOneByKey(sysBookResord);
                System.out.println(" 更新 "+sysBookResord);
            }
        }
        return ;
    }



    @Override
    public void exportExcel(String fileName) {

        SysBook sysBook = new SysBook(); //数据库表对应的实体
        List<SysBook> list = null;//mysysBookService.find();//从数据库读出数据到集合中
        Integer rowNumber = list.size(); //集合里面数据的个数
        //当然上面的数据源也可以根据个人需要自己换


        // 创建Excel文件
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            // HSSFSheet sheet = workbook.createSheet("XX");

            HSSFSheet sheet = workbook.createSheet();

            // 定义行
            HSSFRow row;

            // 定义单元格
            HSSFCell cell, cell1;

            // 定义单元格为字符串类型
            // cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 在单元格中输入一些内容语句如下：
            // cell.setCellValue("内容");
            // cell1.setCellValue("内容");

            // 创建第一行
            row = sheet.createRow(0);
            // 创建单元格 ，2个单元格
            cell = row.createCell(0);
            cell1 = row.createCell(1);

            //给第一行的单元格赋值
            cell.setCellValue("书名");
            cell1.setCellValue("作者");

            //循环给每行及每个单元格赋值
            for (int i = 1; i <= rowNumber; i++) {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell1 = row.createCell(1);

                cell.setCellValue(list.get(i - 1).getBookName());
                cell1.setCellValue(list.get(i - 1).getBookAuthor());
            }

            //检查当前要导出的excel文件是否存在 ，存在就删除。
            File file = new File("d:\\test.xls");
            if (file.exists()) {
                file.delete();
            }

            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream("d:\\test.xls");

            // 把相应的Excel 工作簿
            workbook.write(fOut);

            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();

        } catch (Exception e) {
            System.out.println("Exception：" + e);
        }
        return ;
    }

}
