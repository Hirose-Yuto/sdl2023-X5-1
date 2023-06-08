package main.java;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine;

public final class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", description = "Source file")
    Path sourceFile;

    @CommandLine.Parameters(index = "1", description = "Target file")
    Path targetFile;

    public static void main(String[] args) {
        final App app = new App();
        final CommandLine cmdline = new CommandLine(app);
        cmdline.setExpandAtFiles(false);
        final int status = cmdline.execute(args);
        if (status != 0) {
            System.exit(status);
        }
    }

    @Override
    public Integer call() throws Exception {
        final String source = Files.readString(sourceFile);
        final String target = Files.readString(targetFile);

        List<String> sourceTokenList = Splitter.split(source);
        List<String> targetTokenList = Splitter.split(target);

        Analyzer differencer = new Analyzer();
        AnalogyInfo info = differencer.computeAnalogy(sourceTokenList, targetTokenList);
        show(info, sourceTokenList);
        return null;
    }

    public void show(AnalogyInfo info, List<String> source) {
        System.out.print("following analogies found!");
        while(!info.analogies.isEmpty()) {
            AnalogyInfo.Pair pair = info.analogies.pop();

            if(pair.end - pair.start < 10) continue;
            System.out.println("\n==============================");

            StringBuilder s = new StringBuilder();
            for(int i = pair.start; i <= pair.end; i ++) {
                s.append(source.get(i));
            }
            System.out.print(s);
        }
        System.out.println("\n==============================");
    }
}