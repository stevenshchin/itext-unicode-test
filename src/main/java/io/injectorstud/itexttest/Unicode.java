package io.injectorstud.itexttest;

import com.google.common.collect.ImmutableList;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Unicode {
  private static final String DEST = "results/" + Unicode.class.getSimpleName() + ".pdf";
  private static final String FONT_DIR = "./src/main/resources/font/";
  private static final List<String> FONTS = ImmutableList.of("NotoSansCJKsc-Regular.otf", "arialuni.ttf");

  private String _contentTraditionalChinese;
  private String _contentSimplifiedChinese;
  private String _contentJapanese;
  private String _contentKorean;

  public static void main(String[] args) throws Exception {
    File file = new File(DEST);

    file.getParentFile().mkdirs();

    new Unicode().manipulatePdf();
  }

  private void manipulatePdf() throws Exception {
    loadStrings();

    PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
    Document doc = new Document(pdfDoc);

    for (String font : FONTS) {
      addTestParagraphs(doc, PdfFontFactory.createFont(FONT_DIR + font, PdfEncodings.IDENTITY_H, true));
    }

    doc.close();
  }

  private void loadStrings() throws IOException {
    _contentTraditionalChinese =
        "Traditional Chinese: " + IOUtils.toString(Unicode.class.getClassLoader().getResourceAsStream("text/tc.txt"));
    _contentSimplifiedChinese =
        "Simplified Chinese: " + IOUtils.toString(Unicode.class.getClassLoader().getResourceAsStream("text/sc.txt"));
    _contentJapanese =
        "Japanese: " + IOUtils.toString(Unicode.class.getClassLoader().getResourceAsStream("text/jp.txt"));
    _contentKorean = "Korean: " + IOUtils.toString(Unicode.class.getClassLoader().getResourceAsStream("text/kr.txt"));
  }

  private void addTestParagraphs(Document doc, PdfFont font) {
    try {
      doc.add(new Paragraph("Font: " + font.toString()).setFont(font).setFontSize(16.0f));
      doc.add(new Paragraph(_contentTraditionalChinese).setFont(font));
      doc.add(new Paragraph(_contentSimplifiedChinese).setFont(font));
      doc.add(new Paragraph(_contentJapanese).setFont(font));
      doc.add(new Paragraph(_contentKorean).setFont(font));
    } catch (Exception e) {
      doc.add(new Paragraph("dump").setFontSize(0.1f));
      doc.add(new Paragraph("Error").setFont(font).setFontColor(Color.RED).setMarginTop(-80.0f));
    }
  }
}
