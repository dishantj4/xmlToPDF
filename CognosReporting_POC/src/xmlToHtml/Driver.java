package xmlToHtml;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;


public class Driver {

    public static void main(String[] args)
    {
        try
        {
      
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("src/xmlToHtml/catalog.xsl");
            Source xmlDoc = new StreamSource("src/xmlToHtml/catalog.xml");

            String outputFileName = "src/xmlToHtml/catalog.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
            
            
      
            Document document = new Document();
         
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/xmlToHtml/Report.pdf"));
      
            document.open();
           
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    new FileInputStream("src/xmlToHtml/catalog.html")); 
            //step 5
             document.close();
     
            System.out.println( "PDF Created!" );
           
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}