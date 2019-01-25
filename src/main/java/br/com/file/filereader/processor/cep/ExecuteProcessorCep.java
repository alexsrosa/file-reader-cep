package br.com.file.filereader.processor.cep;

import br.com.file.filereader.processor.Processor;

/**
 * Classe de execução da leitura e escrita da cadeia de arquivos
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 08:56:00
 */
public class ExecuteProcessorCep {

    /**
     * Metodo principal para chamar toda a cadeia de processamento.
     *
     * @param args
     */
    public static void main(String[] args) {

        Processor logLocalidadeProcessor = new LogLocalidadeProcessor();
        Processor logBairroProcessor = new LogBairroProcessor();
        Processor logCpcProcessor = new LogCpcProcessor();
        Processor logFaixaBaiProcessor = new LogFaixaBaiProcessor();
        Processor logFaixaCpcProcessor = new LogFaixaCpcProcessor();
        Processor logFaixaUfProcessor = new LogFaixaUfProcessor();
        Processor logFaixaLocalidadeProcessor = new LogFaixaLocalidadeProcessor();
        Processor logFaixaUopProcessorProcessor = new LogFaixaUopProcessor();
        Processor logLogradouroProcessor = new LogLogradouroProcessor();
        Processor logGrandeUsuarioProcessor = new LogGrandeUsuarioProcessor();
        Processor logNumSecProcessor = new LogNumSecProcessor();
        Processor logUnidOperProcessor = new LogUnidOperProcessor();

        // Definir order de execução
        logNumSecProcessor.setNext(logUnidOperProcessor);
        logGrandeUsuarioProcessor.setNext(logNumSecProcessor);
        logLogradouroProcessor.setNext(logGrandeUsuarioProcessor);
        logFaixaUopProcessorProcessor.setNext(logLogradouroProcessor);
        logFaixaUfProcessor.setNext(logFaixaUopProcessorProcessor);
        logFaixaLocalidadeProcessor.setNext(logFaixaUfProcessor);
        logFaixaCpcProcessor.setNext(logFaixaLocalidadeProcessor);
        logFaixaBaiProcessor.setNext(logFaixaCpcProcessor);
        logCpcProcessor.setNext(logFaixaBaiProcessor);
        logBairroProcessor.setNext(logCpcProcessor);
        logLocalidadeProcessor.setNext(logBairroProcessor);
        logLocalidadeProcessor.process();
    }
}
