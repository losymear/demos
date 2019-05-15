import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: pdfreplacetext
 * @description: 替换word里的文本
 * @author: losymear
 * @create: 2019-05-15 14:17
 * @see {https://itextpdf.com/en/resources/examples/itext-5-legacy/replacing-pdf-objects}
 * word转成的pdf无法识别，原因未知
 */


public class PdfFileWordReplace {

    public static void main(String[] args) throws Exception {

        manipulatePdf("hello.pdf", "output.pdf");

    }

    public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        if (object instanceof PRStream) {
            PRStream stream = (PRStream) object;
            byte[] data = PdfReader.getStreamBytes(stream);


//            String eredeti = "öűóá";
//            final String s = new String(eredeti.getBytes());

            String str = new String(data);
            System.out.println(str);
            stream.setData(str
//                    .replace("today", s)
//                    .replace("时间", "test")
                    .replace("Hello", "Rs")
                    .getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();

        Paragraph preface = new Paragraph();
        preface.setAlignment(Element.ALIGN_CENTER);

        reader.close();
    }


}
