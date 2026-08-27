package us.tn.greatsmokey;

import java.io.File;
import java.io.FileOutputStream;

import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 * Provides functionality for converting Microsoft Word DOCX documents
 * to PDF documents using the docx4j library and its XSL-FO PDF converter.
 * <p>
 * The conversion process loads a DOCX document into a
 * {@link WordprocessingMLPackage}, configures the docx4j PDF conversion
 * settings, and writes the resulting PDF to the specified output file.
 * </p>
 *
 * @author 
 * @version 1.0
 */
public class WordToPdf {

    /**
     * Application entry point.
     * <p>
     * Converts the specified Word document to a PDF document and reports
     * whether the conversion was successful.
     * </p>
     *
     * @param args command-line arguments; not currently used
     * @throws Exception if the input Word document cannot be loaded or
     *                   an error occurs while preparing the conversion
     */
    public static void main(String[] args) throws Exception {
       
       if (convert("documents/2025 Performance & Career Conversation_jwp.docx", "documents/2025 Performance & Career Conversation_jwp.pdf")) {
         System.out.println("Successfully created a PDF!!!");   
       } else {
          System.out.println("Failed to create a PDF!!!");
       }
    }
    
    /**
     * Converts a Microsoft Word DOCX document to a PDF document.
     * <p>
     * The input DOCX file is loaded using docx4j's
     * {@link WordprocessingMLPackage}. A {@link PdfConversion} is then
     * created using the XSL-FO conversion implementation. The converted
     * PDF content is written to the specified output file.
     * </p>
     *
     * @param inputDocx  path to the source DOCX file
     * @param outputPdf  path where the resulting PDF file will be created
     * @return {@code true} if the PDF conversion completes successfully;
     *         {@code false} if an error occurs while creating or writing
     *         the PDF
     * @throws Exception if the input DOCX cannot be loaded or an error
     *                   occurs while initializing the conversion
     */
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
