import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @program: apachepoi
 * @description: 测试文本替换
 * @author: losymear
 * @create: 2019-05-14 16:37
 */


public class DocxFileWordReplace {


    public static void main(String[] args) {
        try {
            InputStream is = DocxFileWordReplace.class.getResourceAsStream("/test.docx");
            DocxFileWordReplace docxFileWordReplace = new DocxFileWordReplace();
            XWPFDocument doc = new XWPFDocument(OPCPackage.open(is));
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        // 替换模板的textField
                        if (text != null && text.contains("{{textField}}")) {
                            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            text = text.replace("{{textField}}", LocalDate.now().format(df));
                            r.setText(text, 0);
                        }
                        if (text != null && text.contains("口人力资源")) {
                            text = text.replace("口人力资源", "☑人力资源");
                            r.setText(text, 0);
                        }

                        if (text != null && text.contains("{{textField}}")) {
                            text = text.replace("{{textField}}", "这是一段长本\np1\np2\np3");
                            r.setText(text, 0);
                        }
                    }

                }
            }
            File newFile = new File("src/main/resources/output.docx");
            doc.write(new FileOutputStream(newFile));
        } catch (Exception e) {
            System.err.println(e);

        }

    }

}
