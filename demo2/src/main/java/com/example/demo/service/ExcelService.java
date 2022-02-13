package com.example.demo.service;

import com.example.demo.model.Cat;
import com.example.demo.model.Person;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Component
public class ExcelService {

    private final PersonServiceImpl personService;

    public ExcelService(PersonServiceImpl personService) {
        this.personService = personService;
    }
@Transactional
    Person getPerson(Long id) {
        Person person = personService.readOne(id);
        return person;
    }

    String getCat(Long person_id) {
        List<Cat> cats = new ArrayList<>();
        cats = getPerson(person_id).getCats();
       /* List<String> nameCats = cats.stream()
                .map(cat -> cat.getName())
                .collect(Collectors.toList());
*/
        StringBuilder out = new StringBuilder();
        for (Cat cat : cats)
        {
            out.append(cat.getName());
            out.append("  ");
        }
        return out.toString();

      /*  List<String> nam = new ArrayList<>();
for(Cat cat:cats){
  nam.add(cat.getName());
}
        String name = nam.toString();
        return name;*/
    }

    @SneakyThrows
    public XSSFWorkbook buildExcelDocument(Long id) {


        String name = getPerson(id).getName();
        Integer age = getPerson(id).getAge();
        String ageString = age.toString();
        String surname = getPerson(id).getSurName();
        //String catname = getPerson(id).getCats().toString();
        String catname = getCat(id);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(" Student Data ");
        XSSFRow row;

        Map<String, Object[]> studentData = new TreeMap<String, Object[]>();
        studentData.put("1", new Object[]{"AGE", "NAME", "SURNAME", "NameCat"});
        studentData.put("2", new Object[]{ageString, name, surname,catname});
        Set<String> keyid = studentData.keySet();

        int rowid = 0;

        for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = studentData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
     /*   FileOutputStream out = new FileOutputStream(
               // new File ("first.xlsx"));
               // new File("/home/pavel/first.xlsx"));
new File ("d:/first.xlsx"));
        workbook.write(out);
        out.close();
*/
return workbook;
    }

    public ByteArrayResource export(Long id) throws IOException {

        XSSFWorkbook workbook = buildExcelDocument(id);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        byte[] xls = baos.toByteArray();
        ByteArrayResource byteArrayResource = new ByteArrayResource(xls) {

            @Override
            public String getFilename() {
                return "excel";
            }
        };
        return byteArrayResource;
    }
}
