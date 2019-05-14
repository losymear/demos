import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

            XWPFDocument doc = new XWPFDocument(OPCPackage.open("text.docx"));
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains("{{textField}}")) {
                            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            text = text.replace("{{textField}}", LocalDate.now().format(df));
                            r.setText(text, 0);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e);

        }

    }

}
