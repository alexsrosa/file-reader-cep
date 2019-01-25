package br.com.file.filereader.helper;

import br.com.file.filereader.converter.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Classe Util para leitura e escrita de arquivos.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 08:51:00
 */
public class FileUtil {

    //TODO flexibilizar os caminhos de path
    private Path pathOutput = Paths.get("src", "main", "resources", "generated", "cep");
    //TODO flexibilizar os caminhos de path
    private Path pathInput = Paths.get("/files", "cep");

    private static final Logger logger = LogManager.getLogger(FileUtil.class);

    /**
     * Metodo para leitura de um arquivo.
     *
     * @param nomeFile nome do arquivo
     * @return Uma string com o conteudo do arquivo.
     */
    public String read(String nomeFile) {

        StringBuilder result = new StringBuilder();
        String pathInputFile = pathInput + "/" + nomeFile;
        InputStream in = getClass().getResourceAsStream(pathInputFile);

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(in))) {
            String line;

            while ((line = buffer.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("[LEITURA] O arquivo " + pathInputFile + " foi lido com sucesso;");

        return result.toString();
    }

    /**
     * Metodo para escrita de infomaçoes em um determinado arquivo.
     *
     * @param string   StringBuilder com conteudo a ser escrito.
     * @param fileName Nome do arquivo de escrita.
     * @throws IOException
     */
    public void write(StringBuilder string, String fileName) throws IOException {

        String pathOutputFile = pathOutput + "/" + fileName;

        File file = new File(pathOutputFile);
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file));
        BufferedWriter writer = new BufferedWriter(out);

        try {
            writer.write(string.toString());
            writer.close();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        logger.info("[GERACAO] O arquivo " + pathOutputFile + " foi gerado com sucesso.");
    }

    /**
     * Metodo que efetua a leitura do arquivo e escrita de uma novo arquivo a partir do seu converter.
     *
     * @param fileIn    Nome do arquivo de leitura
     * @param fileOut   Nome do arquivo de escrita
     * @param converter Classe de conversao
     */
    public void readAndWhite(String fileIn, String fileOut, Converter converter) {
        try {
            // Reader file
            List<String> data = Arrays.asList(read(fileIn).split("\\r\\n|\\r|\\n", -1));

            // writer sql
            // TODO generalizar esta chamada
            write(converter.convertAllToSql(data, fileOut.split("\\.")[0]), fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
