package csv;

import java.io.*;

import csvoutput.CSVOutputStream;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CSVOutputStreamTest {

  private static File csvFile;


  public CSVOutputStreamTest() {
  }


  @BeforeClass
  public static void setUpClass() {
    // Ausgabedatei anlegen
    csvFile = new File("unitTest.csv");
    //csvFile.delete();
  }


  @AfterClass
  public static void tearDownClass() {
  }


  @Before
  public void setUp() {
  }


  @Test
  public void testWriteCell_Double() {
    try {
      CSVOutputStream csv =
              new CSVOutputStream(new BufferedOutputStream(new FileOutputStream(
              csvFile)),
                                  ';');
      csv.writeCell(3.45);
      csv.flush();

      char[] cb = new char[256];
      FileReader fr = new FileReader(csvFile);
      int nc = fr.read(cb);

      assertEquals(5, nc);

      assertEquals('3', cb[0]);
      assertEquals(',', cb[1]);
      assertEquals('4', cb[2]);
      assertEquals('5', cb[3]);
      assertEquals(';', cb[4]);
    }
    catch (Exception ex) {
      fail("Unexpected Exception: " + ex.getMessage());
    }
  }


  @Test
  public void testWriteCell_Integer() {
    try {
      CSVOutputStream csv =
              new CSVOutputStream(new BufferedOutputStream(new FileOutputStream(
              csvFile)),
                                  ';');
      csv.writeCell(21);
      csv.flush();

      char[] cb = new char[256];
      FileReader fr = new FileReader(csvFile);
      int nc = fr.read(cb);

      assertEquals(3, nc);

      assertEquals('2', cb[0]);
      assertEquals('1', cb[1]);
      assertEquals(';', cb[2]);
    }
    catch (Exception ex) {
      fail("Unexpected Exception: " + ex.getMessage());
    }
  }


  @Test
  public void testWriteCell_char() {
    try {
      CSVOutputStream csv =
              new CSVOutputStream(new BufferedOutputStream(new FileOutputStream(
              csvFile)),
                                  ';');
      csv.writeCell('a');
      csv.flush();

      char[] cb = new char[256];
      FileReader fr = new FileReader(csvFile);
      int nc = fr.read(cb);

      assertEquals(2, nc);

      assertEquals('a', cb[0]);
      assertEquals(';', cb[1]);
    }
    catch (Exception ex) {
      fail("Unexpected Exception: " + ex.getMessage());
    }
  }


  @Test
  public void testWriteCell_String() {
    try {
      CSVOutputStream csv =
              new CSVOutputStream(new BufferedOutputStream(new FileOutputStream(
              csvFile)),
                                  ';');
      csv.writeCell("Java");
      csv.flush();

      char[] cb = new char[256];
      FileReader fr = new FileReader(csvFile);
      int nc = fr.read(cb);

      assertEquals(5, nc);

      assertEquals('J', cb[0]);
      assertEquals('a', cb[1]);
      assertEquals('v', cb[2]);
      assertEquals('a', cb[3]);
      assertEquals(';', cb[4]);
    }
    catch (Exception ex) {
      fail("Unexpected Exception: " + ex.getMessage());
    }
  }


  @Test
  public void testWriteRow() {
    try {
      CSVOutputStream csv =
              new CSVOutputStream(new BufferedOutputStream(new FileOutputStream(
              csvFile)),
                                  ';');
      csv.writeCell("Java");
      csv.writeRow();
      csv.flush();

      char[] cb = new char[256];
      FileReader fr = new FileReader(csvFile);
      int nc = fr.read(cb);

      assertEquals('J', cb[0]);
      assertEquals('a', cb[1]);
      assertEquals('v', cb[2]);
      assertEquals('a', cb[3]);
      assertEquals(';', cb[4]);
      assertEquals(System.getProperty("line.separator").getBytes()[0], cb[5]);
      if (System.getProperty("line.separator").getBytes().length > 1) {
        assertEquals(System.getProperty("line.separator").getBytes()[1], cb[6]);
      }
    }
    catch (Exception ex) {
      fail("Unexpected Exception: " + ex.getMessage());
    }
  }
}
