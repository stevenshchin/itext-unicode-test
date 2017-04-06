itext-unicode-test
==================

A tiny project to test Unicode fonts support in [iText](http://developers.itextpdf.com/), a Java PDF library.

Install test fonts
------------------

Check [font README](src/main/resources/font/README.md)

Generate test PDF
-----------------

1. `mvn compile`
2. `mvn exec:java -Dexec.mainClass="io.injectorstud.itexttest.Unicode"`
3. Check `Unicode.pdf` under `results/`
