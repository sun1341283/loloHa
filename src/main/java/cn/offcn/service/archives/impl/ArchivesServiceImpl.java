package cn.offcn.service.archives.impl;

import cn.offcn.entity.Archives;
import cn.offcn.entity.Employee;
import cn.offcn.mapper.ArchivesMapper;
import cn.offcn.mapper.EmployeeMapper;
import cn.offcn.service.archives.ArchivesService;
import cn.offcn.utils.OAResult;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ArchivesMapper archivesMapper;

    public Employee getEmployeeArchivesByEid(Integer eid){

        return employeeMapper.getEmployeeArchivesByEid(eid);
    }

    public OAResult getAndSaveArchives(MultipartFile files){

        try{
            //创建一个集合存每行的数据
            List<Archives> archivesList=new ArrayList<Archives>();
            if(files!=null){
                //获取上传文件的输入流
                InputStream inputStream=files.getInputStream();
                //创建一个Excel文档
                HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
                //循环工作薄
                for(int numSheet=0;numSheet<workbook.getNumberOfSheets();numSheet++){
                    //根据下标获取指定的工作薄
                    HSSFSheet hssfSheet=workbook.getSheetAt(numSheet);
                    if(hssfSheet==null){
                        continue;
                    }
                    //循环当前工作薄中的每一行
                    //numRow从1开始，下标为0的行是标题
                    for(int numRow=1;numRow<=hssfSheet.getLastRowNum();numRow++){
                        //获取指定的行
                        HSSFRow row = hssfSheet.getRow(numRow);

                        HSSFCell dnum = row.getCell(0);
                        HSSFCell landline = row.getCell(1);
                        HSSFCell school = row.getCell(2);
                        HSSFCell zhuanye = row.getCell(3);
                        HSSFCell sosperson = row.getCell(4);
                        HSSFCell biyedate = row.getCell(5);
                        HSSFCell zzmm = row.getCell(6);
                        HSSFCell minzu = row.getCell(7);
                        HSSFCell xueli = row.getCell(8);
                        HSSFCell email = row.getCell(9);
                        HSSFCell empFk = row.getCell(10);
                        //创建一个档案类对象
                        Archives archives=new Archives();
                        archives.setDnum(dnum.getStringCellValue());
                        archives.setLandline(landline.getStringCellValue());
                        archives.setSchool(school.getStringCellValue());
                        archives.setZhuanye(zhuanye.getStringCellValue());
                        archives.setSosperson(sosperson.getStringCellValue());
                        archives.setBiyedate(biyedate.getDateCellValue());
                        archives.setZzmm(zzmm.getStringCellValue());
                        archives.setMinzu(minzu.getStringCellValue());
                        archives.setXueli(xueli.getStringCellValue());
                        archives.setEmail(email.getStringCellValue());
                        archives.setEmpFk((int)empFk.getNumericCellValue());
                        //每一行对应的archives添加到集合
                        archivesList.add(archives);
                    }
                }
            }
            //把集合中的每个Archives对应保存到档表中
            //批量添加
            int rows=archivesMapper.addBatchArchives(archivesList);
            if(rows>0){
                return OAResult.ok(200,"档案数据导入成功");
            }
        }catch(Exception e){
             e.printStackTrace();
        }
        return OAResult.ok(400,"档案数据导入失败");
    }
}
