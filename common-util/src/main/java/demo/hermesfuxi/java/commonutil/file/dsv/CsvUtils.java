package demo.hermesfuxi.java.commonutil.file.dsv;

import java.lang.reflect.Field;
import java.util.*;

public class CsvUtils {
    //    Csv导入导出
//    public void export(List dataList, String fileName, HttpServletResponse response) {
//        File tempFile = null;
//        try {
//            tempFile = File.createTempFile(UUID.randomUUID().toString().replaceAll("-", ""), FILE_SUFFIX);
//            // UTF-8使正确读取分隔符","
//            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), CHARSET_NAME), 1024);
//            String outputStrByDataList = getOutputStrByDataList(dataList);
//            csvFileOutputStream.write(outputStrByDataList);
//            csvFileOutputStream.flush();
//            //写入csv结束，写出流
//            response.reset();
//            response.setContentType("text/csv;charset=" + CHARSET_NAME);
//            response.setHeader("Content-Disposition", "attachment;  filename=" + URLEncoder.encode(fileName + FILE_SUFFIX, "UTF-8"));
//            out = response.getOutputStream();
//            File fileLoad = new File(tempFile.getCanonicalPath());
//            in = new FileInputStream(fileLoad);
//            int n;
//            byte[] b = new byte[10240];
//            while ((n = in.read(b)) != -1) {
//                out.write(b, 0, n); // 每次写入out1024字节
//            }
//            out.flush();
//        } catch () {
//        } finally {
//        }
//    }
//
//    public void importCsv(){
//    }
//
//    /**
//     * 将数据转化为输出的字符串
//     */
//    private static String getOutputStrByDataList(List dataList) {
//        StringBuffer stringBuffer = new StringBuffer();
//        if (dataList != null && dataList.size() > 0) {
//            // Arraylist<实体类>填充实体类的基本信息
//            Class clazz = dataList.get(0).getClass();
//            CsvField csvField = new CsvField(clazz);
//            List<Field> csvFieldList = csvField.getCsvFieldList();
//            if (csvFieldList != null && csvFieldList.size() > 0) {
//                // 写入文件头部
//                List<String> fieldTitleNames = csvField.getFieldTitleNames();
//                stringBuffer.append(String.join(SPILT_CHAR, fieldTitleNames));
//                stringBuffer.append("\r\n");
//                // 写入文件内容
//                for (int j = 0; j < dataList.size(); j++) {
//                    for (int i = 0; i < csvFieldList.size(); i++) {
//                        Object obj = null;
//                        try {
//                            Field field = csvFieldList.get(i);
//                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
//                            Method method = propertyDescriptor.getReadMethod();
//                            method.setAccessible(true);
//                            obj = method.invoke(dataList.get(j));
//                        } catch (Exception e) {
//                            throw new ApplicationException(ErrorCode.SYSTEM_ERROR, e.getMessage());
//                        }
//                        String str = String.valueOf(obj);
//                        if (str == null || "null".equals(str)) {
//                            str = "";
//                        }
//                        // 将生成的单元格添加到工作表中
//                        stringBuffer.append(str);
//                        stringBuffer.append(SPILT_CHAR);
//                    }
//                    stringBuffer.append("\r\n");
//                }
//            }
//        }
//        return stringBuffer.toString();
//    }
}

class CsvField{
    // set/get方法省略
    List<Field> csvFieldList;
    List<String> fieldTitleNames;
    List<String> fieldTitleTableInfo;

//    CsvField(Class clazz) {
//        Field[] fields = clazz.getDeclaredFields();
//        Map titleMap = new TreeMap(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return Integer.valueOf(o1) - Integer.valueOf(o2);
//            }
//        });
//        for (int i = 0; fields != null && i < fields.length; i++) {
//            Csv annotation = fields[i].getAnnotation(Csv.class);
//            if (annotation != null) {
//                titleMap.put(annotation.orderNum(), fields[i]);
//            }
//        }
//
//        List<Field> fieldList = new ArrayList<Field>();
//        List<String> fieldTitleNames = new ArrayList<String>();
//        List<String> fieldTitleTableInfo = new ArrayList<String>();
//        for (Iterator propertyIterator = titleMap.entrySet().iterator(); propertyIterator.hasNext(); ) {
//            Map.Entry propertyEntry = (Map.Entry) propertyIterator.next();
//            Field field = (Field) propertyEntry.getValue();
//            fieldList.add(field);
//            Csv annotation = field.getAnnotation(Csv.class);
//            String csvName = annotation.name();
//            if (StringUtils.isNotEmpty(csvName)) {
//                fieldTitleNames.add(csvName);
//                fieldTitleTableInfo.add(String.format("%s:%s", field.getName(), csvName));
//            } else {
//                fieldTitleNames.add("");
//            }
//        }
//        this.setCsvFieldList(fieldList);
//        this.setFieldTitleNames(fieldTitleNames);
//        this.setFieldTitleTableInfo(fieldTitleTableInfo);
//    }

}
