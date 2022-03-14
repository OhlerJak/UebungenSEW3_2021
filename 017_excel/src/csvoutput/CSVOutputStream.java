package csvoutput;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;

public class CSVOutputStream extends FilterOutputStream {

    private static char DEFAULTTRENNZEICHEN = ';';
    private char trennzeichen;


    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public CSVOutputStream(OutputStream out, char trennzeichen) {
        super(out);

        setTrennzeichen(trennzeichen);

    }
    public CSVOutputStream(OutputStream out){
        this(out, DEFAULTTRENNZEICHEN);
    }


    public char getTrennzeichen() {
        return trennzeichen;
    }

    public void setTrennzeichen(char trennzeichen) {
        this.trennzeichen = trennzeichen;
    }




    public void writeCell(String data) throws IOException {
        //Zelle anfüllen
        data +=trennzeichen;
        super.write(data.getBytes(StandardCharsets.UTF_8));


        System.out.print(data);
    }

    public void writeCell(int data) throws IOException {
        writeCell(Integer.toString(data));
    }

    public void writeCell(double data) throws IOException {
        writeCell(DecimalFormat.getNumberInstance().format(data));
    }

    public void writeCell(char data) throws IOException {
        writeCell(Character.toString(data));
    }


    public void writeRow() throws IOException {
        //neue Reihe

        super.write((System.lineSeparator()).getBytes(StandardCharsets.UTF_8));

        System.out.println();
    }
}
