package br.com.file.filereader.processor.cep;

import br.com.file.filereader.converter.cep.LogLocalidadeConverter;
import br.com.file.filereader.helper.FileUtil;
import br.com.file.filereader.processor.Processor;

import java.util.Objects;

/**
 * Classe processadora da leitura e conversão da tabela LOG_LOCALIDADE.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:02:00
 */
public class LogLocalidadeProcessor implements Processor {

    private final static String fileIn = "LOG_LOCALIDADE.txt";
    private final static String fileOut = "LOG_LOCALIDADE.sql";
    private Processor processor;

    @Override
    public void process() {
        FileUtil fileUtil = new FileUtil();
        fileUtil.readAndWhite(fileIn, fileOut, new LogLocalidadeConverter());

        if(Objects.nonNull(processor)) processor.process();
    }

    @Override
    public void setNext(Processor processor) {
        this.processor = processor;
    }
}
