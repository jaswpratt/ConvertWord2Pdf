# ConvertWord2Pdf
Java Demo - To Convert a Word Document

Certainly. Here is a complete README.md you can copy directly into the repository. I based it on the current repository structure, pom.xml, and WordToPdf.java. 
G
GitHub
+2

# ConvertWord2Pdf

A simple Java demonstration project for converting Microsoft Word
DOCX documents to PDF using [docx4j](https://www.docx4java.org/).

## Overview

`ConvertWord2Pdf` demonstrates how to programmatically convert a Microsoft
Word `.docx` document to a `.pdf` document using Java.

The project uses the docx4j XSL-FO conversion process:

```text
Microsoft Word DOCX
        |
        v
    docx4j
        |
        v
      XSL-FO
        |
        v
   Apache FOP
        |
        v
       PDF


Microsoft Word does not need to be installed on the computer performing
the conversion.

Technologies
Java 11
Maven
docx4j 17.0.3
docx4j Export FO 17.0.3
Jakarta JAXB
Apache FOP
Project Structure
ConvertWord2Pdf/
├── .settings/
│
├── documents/
│   └── DOCX and PDF documents
│
├── lib/
│   └── JAR files for offline use
│
├── src/
│   └── main/
│       └── java/
│           └── us/
│               └── tn/
│                   └── greatsmokey/
│                       └── WordToPdf.java
│
├── .classpath
├── .gitignore
├── .project
├── pom.xml
└── README.md

Requirements
Java 11 or later
Eclipse, IntelliJ IDEA, or another Java IDE
Maven, when dependencies need to be downloaded from a Maven repository

Microsoft Word is not required.

Maven Dependencies

The project uses Maven to manage the docx4j dependency tree.

The two primary dependencies are:

<dependency>
    <groupId>org.docx4j</groupId>
    <artifactId>docx4j-JAXB-ReferenceImpl</artifactId>
    <version>17.0.3</version>
</dependency>

<dependency>
    <groupId>org.docx4j</groupId>
    <artifactId>docx4j-export-fo</artifactId>
    <version>17.0.3</version>
</dependency>


The complete dependency tree is resolved automatically by Maven.

Important: Keep docx4j Versions Consistent

All docx4j components should use the same version.

This project uses:

docx4j 17.0.3


Do not mix versions such as:

docx4j-core-17.0.3.jar
docx4j-export-fo-11.5.6.jar


Mixing different docx4j versions can cause runtime errors including:

NoClassDefFoundError
NoSuchMethodError
ClassNotFoundException


The same principle applies to the JAXB dependencies.

Running the Application
Using Eclipse

Import the project into Eclipse as an existing Maven project.

Allow Eclipse/Maven to resolve the project dependencies.

Open:

src/main/java/us/tn/greatsmokey/WordToPdf.java


Right-click WordToPdf.java.

Select:

Run As → Java Application

The application currently converts:

documents/2025 Performance & Career Conversation_jwp.docx


to:

documents/2025 Performance & Career Conversation_jwp.pdf


A successful conversion displays:

Successfully created a PDF!!!


If the conversion fails:

Failed to create a PDF!!!

Using the Converter

The actual conversion functionality is provided by the static convert()
method in WordToPdf.

Example:

boolean success = WordToPdf.convert(
    "documents/input.docx",
    "documents/output.pdf"
);

Parameters
Parameter	Description
inputDocx	Path to the source DOCX file
outputPdf	Path where the generated PDF will be written
Return Value

The method returns:

true


when the conversion succeeds.

It returns:

false


when an error occurs while creating or writing the PDF.

Offline Use

One of the purposes of this project is to support environments where access
to a public Maven repository is unavailable.

For example, a corporate or government environment may restrict Internet
access from development machines.

When Internet access is available, Maven can be used to download the
complete dependency tree.

Download Dependencies

From the project directory, run:

mvn dependency:copy-dependencies -DoutputDirectory=lib


This downloads the project's dependencies into the lib directory.

The resulting JAR files can then be copied with the project to a machine
that does not have access to a Maven repository.

Eclipse Offline Configuration

When Maven is not available on the target machine, the JAR files in the
lib directory can be added manually to the Eclipse Java Build Path.

In Eclipse:

Right-click the project.
Select Properties.
Select Java Build Path.
Select the Libraries tab.
Select Classpath.
Select Add JARs... or Add External JARs....
Add the required JAR files from the lib directory.
Apply the changes.
Do Not Mix Maven and Manually Added Dependencies

When Maven is being used to manage dependencies, the preferred approach is
to let Maven manage the complete dependency tree.

Avoid having both:

Manually added JAR files


and:

Maven Dependencies


containing different versions of the same libraries.

This can result in runtime errors such as:

NoSuchMethodError
NoClassDefFoundError
ClassNotFoundException


For an offline installation, either use Maven's locally resolved
dependencies or use a complete, consistent set of manually supplied JAR
files.

Generating the Offline Dependency Set

The recommended process for preparing an offline copy is:

On a computer with Internet access

Run:

mvn clean
mvn dependency:copy-dependencies -DoutputDirectory=lib


This creates a local collection of the dependency JARs.

The project can then be copied to the offline environment, including:

ConvertWord2Pdf/
├── documents/
├── lib/
├── src/
├── pom.xml
└── README.md

On the offline computer

Add the JAR files from lib to the Java Build Path.

No connection to Maven Central is required once all required dependencies
have been downloaded.

Conversion Process

The application performs the following operations:

Loads the DOCX file using WordprocessingMLPackage.
Creates an XSL-FO PDF conversion.
Creates and configures PdfSettings.
Passes the Word document to the PDF converter.
Writes the resulting PDF to the specified output file.

The important part of the conversion is:

WordprocessingMLPackage wordMLPackage =
    WordprocessingMLPackage.load(new File(inputDocx));

PdfConversion conversion =
    new Conversion(wordMLPackage);

PdfSettings pdfSettings =
    new PdfSettings();

pdfSettings.setWmlPackage(wordMLPackage);

conversion.output(outputStream, pdfSettings);

Example

A complete example of calling the converter is:

public static void main(String[] args) throws Exception {

    if (WordToPdf.convert(
            "documents/input.docx",
            "documents/output.pdf")) {

        System.out.println("Successfully created a PDF!!!");

    } else {

        System.out.println("Failed to create a PDF!!!");
    }
}

File Paths

The application currently uses paths relative to the project's working
directory.

For example:

documents/input.docx


refers to a file in the project's documents directory.

The output PDF is also written relative to the project's working directory:

documents/output.pdf


If running the application from Eclipse, verify the configured working
directory if files cannot be found.

Error Handling

The convert() method returns a boolean indicating whether the PDF was
successfully created.

For example:

boolean success = WordToPdf.convert(input, output);

if (success) {
    // Conversion succeeded
} else {
    // Conversion failed
}


The current implementation does not expose the underlying exception when
conversion fails.

For a production application, more detailed exception handling and logging
would be recommended.

SLF4J Warning

Depending on the dependency configuration, the application may display a
message similar to:

SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation


This is a logging configuration warning and does not necessarily indicate
that PDF conversion has failed.

If logging is desired, an appropriate SLF4J logging provider can be added
to the project's dependency configuration.

Limitations

This project is intended as a demonstration and starting point rather than
a complete document conversion application.

The current implementation:

Converts DOCX documents to PDF.
Uses fixed input and output paths in main().
Does not provide a graphical user interface.
Does not currently accept command-line arguments.
Does not provide batch conversion.
Provides limited error reporting.
Does not guarantee perfect PDF layout fidelity for every Word document.

The resulting PDF can vary depending on the features used in the source
Word document, including fonts, images, tables, styles, and other Word
formatting features.

Fonts

PDF conversion may depend on the fonts available to the Java runtime and
the conversion environment.

For the most consistent results, make sure that required fonts are
available on the machine performing the conversion.

A document containing fonts that are unavailable on the conversion machine
may not render identically to the original Word document.

Development

The Maven project is configured for Java 11:

<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.target>11</maven.compiler.target>


The project can be built with:

mvn clean package


Dependencies are normally obtained from the configured Maven repositories.

For offline builds, the required dependencies must already be available
locally.

Cleaning the Project

To perform a clean Maven build:

mvn clean
mvn package


In Eclipse, the equivalent operation can be performed with:

Project → Clean

followed by:

Maven → Update Project

when Maven dependency information needs to be refreshed.

Troubleshooting
NoClassDefFoundError

Example:

java.lang.NoClassDefFoundError


This generally indicates that a required dependency is missing from the
runtime classpath.

Verify that all required Maven dependencies or offline JAR files are
available.

NoSuchMethodError

Example:

java.lang.NoSuchMethodError


This usually indicates that incompatible versions of libraries are being
loaded.

Check for multiple versions of:

docx4j
JAXB
Apache FOP
related XML libraries

Do not mix docx4j versions.

DOCX File Not Found

If the application reports that the DOCX cannot be found, verify that the
file exists relative to the application's working directory.

For example:

documents/input.docx

PDF Is Not Created

Check:

The input DOCX exists.
The output directory exists.
The application has permission to write the output file.
All docx4j dependencies are available.
The dependency versions are consistent.
The required fonts are available.
Dependencies and Licensing

This project uses third-party open-source libraries, including docx4j,
Jakarta JAXB, Apache FOP, and their transitive dependencies.

Each third-party library is distributed under its own license.

Before redistributing this project or its lib directory, review the
licenses and notices associated with all included dependencies.

The inclusion of third-party JAR files does not imply that those libraries
are part of this project's source code.

Repository

Source code and project files are available at:

https://github.com/jaswpratt/ConvertWord2Pdf

Author

J. Pratt

License

This project is provided as a demonstration/example project.

Unless otherwise specified, users should review the repository contents and
the licenses of all included third-party dependencies before using or
redistributing the project.


One small point: I deliberately **didn't put the 50-JAR inventory into the README**. Since your `pom.xml` now has only the two top-level docx4j dependencies, Maven is the authoritative source for the transitive dependency set. Your repository currently has a `lib` directory as well, so the README's offline procedure explains how to generate that directory from Maven rather than maintaining a potentially stale hand-written list. 


Also, your current `WordToPdf.java` still doesn't close its `FileOutputStream`; I'd fix that before your next commit. 
