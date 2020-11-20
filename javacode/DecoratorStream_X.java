package javacode;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DecoratorStream_X extends OutputStream
{
    private OutputStream stream;
    private String prefix;

    public DecoratorStream_X(OutputStream stream, String prefix) {
        super();
        this.stream = stream;
        this.prefix = prefix;
    }

    @Override
    public void write(int b) throws IOException {
        byte[] result = new byte[4];

        result[0] = (byte) (b >> 24);
        result[1] = (byte) (b >> 16);
        result[2] = (byte) (b >> 8);
        result[3] = (byte) (b);

        write(result, 0, 4);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
    	if (prefix != null) {
    		stream.write(prefix.getBytes(StandardCharsets.UTF_8));
    		prefix = null;
    	}
    	stream.write(b, off, len);
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }
    
    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }
    
    public static void main(String[] args) throws IOException {
        byte[] message = new byte[]{0x48, 0x65, 0x6c, 0x6c, 0x6f, 0x2c, 0x20, 0x77, 0x6f, 0x72, 0x6c, 0x64, 0x21};
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DecoratorStream_X decoratorStream = new DecoratorStream_X(baos, "First line: ");
            decoratorStream.write(message);
            
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), "UTF-8"))) {
                System.out.println(reader.readLine());  //should print "First line: Hello, world!"
            }
        }
    }
}