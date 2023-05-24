package tech.realcpf.pong.dsl;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class FileTextWriteFunc implements TheFunc{
    @Override
    public Object run(Map<String, Object> params) {
        if (params != null && params.size() == 3){
            String path = (String) params.get("path");
            String content = (String) params.get("content");
            try {
                Path filePath = Path.of(path);
                Files.write(filePath,content.getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,StandardOpenOption.WRITE);
                return String.format("ok with %s",path);
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
        throw new IllegalArgumentException("need 3 param");
    }
}
