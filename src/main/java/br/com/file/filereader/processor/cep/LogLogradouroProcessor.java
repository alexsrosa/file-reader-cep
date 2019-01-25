package br.com.file.filereader.processor.cep;

import br.com.file.filereader.converter.cep.LogLogradouroConverter;
import br.com.file.filereader.helper.FileUtil;
import br.com.file.filereader.processor.Processor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Classe processadora da leitura e conversão da tabela LOG_LOCALIDADE.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:02:00
 */
public class LogLogradouroProcessor implements Processor {

    private final static String fileIn = "LOG_LOGRADOURO_@.TXT";
    private final static String fileOut = "LOG_LOGRADOURO_@.sql";
    private final static List<String> Ufs = Arrays.asList("AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG",
            "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO");
    private Processor processor;

    @Override
    public void process() {
        FileUtil fileUtil = new FileUtil();

        Ufs.forEach(uf ->
                fileUtil.readAndWhite(
                        fileIn.replace("@", uf),
                        fileOut.replace("@", uf),
                        new LogLogradouroConverter())
        );

        if (Objects.nonNull(processor)) processor.process();
    }

    @Override
    public void setNext(Processor processor) {
        this.processor = processor;
    }
}
