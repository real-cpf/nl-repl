package tech.realcpf.pong.dsl;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FunctionExporter {
    public static void fileTextWrite(String path,String content) {
        try {
            Path filePath = Path.of(path);
            Files.writeString(filePath,content,StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
