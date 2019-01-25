package br.com.file.filereader.processor.cep;

import br.com.file.filereader.converter.cep.LogGrandeUsuarioConverter;
import br.com.file.filereader.helper.FileUtil;
import br.com.file.filereader.processor.Processor;

import java.util.Objects;

/**
 * Classe processadora da leitura e conversão da tabela LOG_LOCALIDADE.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:02:00
 */
public class LogGrandeUsuarioProcessor implements Processor {

    private final static String fileIn = "LOG_GRANDE_USUARIO.txt";
    private final static String fileOut = "LOG_GRANDE_USUARIO.sql";
    private Processor processor;

    @Override
    public void process() {
        FileUtil fileUtil = new FileUtil();
        fileUtil.readAndWhite(fileIn, fileOut, new LogGrandeUsuarioConverter());

        if(Objects.nonNull(processor)) processor.process();
    }

    @Override
    public void setNext(Processor processor) {
        this.processor = processor;
    }
}
