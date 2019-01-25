package br.com.file.filereader.processor;

/**
 * Interface para patronizar um processamento
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 14:48:00
 */
public interface Processor {

    int sequence = 0;

    /**
     * Método que deve possuir conteudo a ser processado.
     *
     */
    void process();

    /**
     * Método responsável por chamar o proximo processo da cadeia de processos.
     *
     * @param processor
     */
    void setNext(Processor processor);
}
