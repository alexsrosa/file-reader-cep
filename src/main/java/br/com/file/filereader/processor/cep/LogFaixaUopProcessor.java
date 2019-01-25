package br.com.file.filereader.processor.cep;

import br.com.file.filereader.converter.cep.LogFaixaUopConverter;
import br.com.file.filereader.helper.FileUtil;
import br.com.file.filereader.processor.Processor;

import java.util.Objects;

/**
 * Classe processadora da leitura e conversão da tabela LOG_FAIXA_UOP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:02:00
 */
public class LogFaixaUopProcessor implements Processor {

    private final static String fileIn = "LOG_FAIXA_UOP.txt";
    private final static String fileOut = "LOG_FAIXA_UOP.sql";
    private Processor processor;

    @Override
    public void process() {
        FileUtil fileUtil = new FileUtil();
        fileUtil.readAndWhite(fileIn, fileOut, new LogFaixaUopConverter());

        if(Objects.nonNull(processor)) processor.process();
    }

    @Override
    public void setNext(Processor processor) {
        this.processor = processor;
    }
}
