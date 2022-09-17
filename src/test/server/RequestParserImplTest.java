package test.server;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import server.session.RequestParser;

@SuppressWarnings({ "java:S5960" })
public class RequestParserImplTest {

  private static final String METHOD = "POST";
  private static final String PATH = "/";
  private static final Map<String, String> PARAMS = Map.of(
      "file-name", "file.txt",
      "description", "But I must explain to you how all this mistaken idea...");
  private static final byte[] FILE_CONTENT = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

  private static RequestParser parser;

  @BeforeClass
  public static void beforeAll() throws IOException {
    try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(buffer);) {

      output.writeUTF(METHOD + " " + PATH);

      for (var param : PARAMS.entrySet())
        output.writeUTF(param.getKey() + "=" + param.getValue());

      output.writeUTF("file-size=" + FILE_CONTENT.length);
      output.writeUTF("");

      output.write(FILE_CONTENT, 0, FILE_CONTENT.length);

      try (DataInputStream input = new DataInputStream(new ByteArrayInputStream(buffer.toByteArray()))) {
        parser = new RequestParser(input);
        parser.receiveInput();
      }
    }
  }

  @Test
  public void testGetMethod() {
    assertEquals(parser.getMethod(), METHOD);
  }

  @Test
  public void testGetPath() {
    assertEquals(parser.getPath(), PATH);
  }

  @Test
  public void testGetParams() {
    for (var entry : PARAMS.entrySet()) {
      var parserParams = parser.getParams();
      assertEquals(parserParams.get(entry.getKey()), entry.getValue());
    }
  }

  @Test
  public void testGetTempFile() throws IOException {
    try (FileInputStream input = new FileInputStream(parser.getTempFile())) {
      assertArrayEquals(input.readAllBytes(), FILE_CONTENT);
    }
  }

}
