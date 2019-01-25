package br.com.file.filereader.processor;

import br.com.file.filereader.processor.cep.LogLocalidadeProcessor;
import org.junit.Test;

import java.io.IOException;

public class ProcessorTest {

    @Test
    public void process() throws IOException {
        Processor LogLocalidadeProcessor = new LogLocalidadeProcessor();
        LogLocalidadeProcessor.process();
    }
}
