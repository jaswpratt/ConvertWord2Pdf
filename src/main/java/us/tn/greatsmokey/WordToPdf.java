package us.tn.greatsmokey;

import java.io.File;
import java.io.FileOutputStream;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 * Provides functionality for converting Microsoft Word DOCX documents
 * to PDF documents using the docx4j library and its XSL-FO PDF converter.
 * <p>
 * The conversion process loads a DOCX document into a
 * {@link WordprocessingMLPackage} and uses docx4j to convert the
 * document to PDF.
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

        if (convert("documents/The Crimson Worm.docx", "documents/The Crimson Worm.pdf")) {
            System.out.println("Successfully created a PDF!!!");
        } else {
            System.out.println("Failed to create a PDF!!!");
        }
    }

    /**
     * Converts a Microsoft Word DOCX document to a PDF document.
     *
     * @param inputDocx path to the source DOCX file
     * @param outputPdf path where the resulting PDF file will be created
     *
     * @return {@code true} if the PDF conversion completes successfully;
     *         {@code false} if an error occurs while creating or writing
     *         the PDF
     *
     * @throws Exception if the input DOCX cannot be loaded
     */
    public static boolean convert(String inputDocx, String outputPdf) throws Exception {

        // Load the DOCX document.
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(inputDocx));

        // Convert the DOCX directly to PDF.
        try (FileOutputStream outputStream = new FileOutputStream(outputPdf)) {
            Docx4J.toPDF(wordMLPackage, outputStream);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}