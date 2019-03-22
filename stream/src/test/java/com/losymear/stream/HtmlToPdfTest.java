package com.losymear.stream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @program: stream
 * @description:
 * @author: losymear
 * @create: 2019-03-22 16:20
 */

@RunWith(JUnit4.class)
public class HtmlToPdfTest {
    @Test
    @Ignore
    public void html2pdf() throws Exception {
        // 无法使用

//        String initialString = "<html>";
//        InputStream stream = new ByteArrayInputStream(initialString.getBytes());


//        HtmlConverter.convertToPdf("<html><body><p> hh </p> </body></html>", new FileOutputStream("src/dest.pdf"));
//        Document document = new Document();
//        PdfWriter writer = PdfWriter.getInstance(document,
//                new FileOutputStream("src/html.pdf"));
//        document.open();
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document,  new FileInputStream("src/test.html"));
//        document.close();

    }


    @Test
    public void 测试FlyingSaucer() throws Exception {

        OutputStream os = new FileOutputStream("src/out.pdf");
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocument("src/test.html");
        renderer.layout();
        renderer.createPDF(os);

        os.flush();
        os.close();

    }

}

