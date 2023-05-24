package pong;


import tech.realcpf.pong.dsl.FunctionExporter;
import tech.realcpf.pong.run.GroovyRun;

public class TestDSL {
    public static void main(String[] args) {
        /**
         *
         *

         import tech.realcpf.pong.dsl.FunctionExporter;

         def main(){
             FunctionExporter.fileTextWrite("/tmp/abc.txt","hello nl-repl");
             return "ok";
         }


         */

        String s =  " def main(){\n" +
                "             $.fileTextWrite(\"/tmp/abc.txt\",\"hello nl-repl\");\n" +
                "             return \"ok\";\n" +
                "         }";
        GroovyRun.get().doOnce(s);

    }
}
