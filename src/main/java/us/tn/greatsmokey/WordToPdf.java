package us.tn.greatsmokey;

import java.io.File;
import java.io.FileOutputStream;

import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class WordToPdf {

    public static void main(String[] args) throws Exception {
       
       if (convert("documents/2025 Performance & Career Conversation_jwp.docx", "documents/2025 Performance & Career Conversation_jwp.pdf")) {
    	  System.out.println("Successfully created a PDF!!!");   
       } else {
    	   System.out.println("Failed to create a PDF!!!");
       }
    }

    public static boolean convert(String inputDocx, String outputPdf) throws Exception {
        boolean success = false;
        // Load the DOCX
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(inputDocx));

        // Create PDF conversion
        PdfConversion conversion = new Conversion(wordMLPackage);

        // Create PDF settings
        PdfSettings pdfSettings = new PdfSettings();

        // IMPORTANT: tell docx4j which document to convert
        pdfSettings.setWmlPackage(wordMLPackage);

        // Write PDF
        try {
        	FileOutputStream outputStream = new FileOutputStream(outputPdf); 
            conversion.output(outputStream, pdfSettings);
            success = true;
        } catch (Exception e) {
        	return false;
        }
        
        return success;
    }
}
