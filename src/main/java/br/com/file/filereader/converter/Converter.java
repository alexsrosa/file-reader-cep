package br.com.file.filereader.converter;

import br.com.file.filereader.helper.SqlGenerateUtil;

import java.util.List;
import java.util.Objects;

/**
 * Interface que server para padronizar a conversao de informaçoes
 *
 * @param <E>
 * @param <Long>
 */
public interface Converter<E, Long> {

    /**
     * Metodo com o objetivo de converter uma linha String em um objeto.
     *
     * @param line
     * @return
     */
    E convertLineToObject(String line);

    /**
     * Metodo com o objetivo de gerar um SQL.
     *
     * @param object
     */
    StringBuilder generateSQL(E object);

    /**
     * Contador de registros porcessados
     *
     * @param stringBuilder
     * @return
     */
    void countRegistros(StringBuilder stringBuilder);

    /**
     * Metodo default para tratamento de strings
     *
     * @param o
     * @param i
     * @return String
     */
    default String getString(String[] o, int i) {

        if(i < o.length)
            return Objects.isNull(o[i]) ? "" : o[i];
        return "";
    }

    /**
     * Metodo default para tratamento de numeros.
     *
     * @param o
     * @param i
     * @return long
     */
    default long getNumber(String[] o, int i) {

        if(i < o.length)
            return Objects.isNull(o[i]) || o[i].equals("") ? 0 : java.lang.Long.valueOf(o[i]);
        return 0;
    }


    /**
     * Metodo default para tratamento de caracteres especiais.
     *
     * @param value
     * @return String
     */
    default String escapeSpecial(String value) {
        return value.replace("'", "''")
                    .replace("&","'||CHR(38)||'");
    }

    /**
     * Metodo para conversao de todos as linhas em SQL.
     *
     * @param data          Lista de linhas para transformaçao
     * @param spoolName     Nome do arquivo de spool
     * @return StringBuilder com o SQL gerado a partir das linhas
     *
     * TODO refatorar e generalizar este metodo
     */
    default StringBuilder convertAllToSql(List<String> data, String spoolName) {

        StringBuilder stringBuilder = new StringBuilder();

        int registros = data.size() - 1;

        stringBuilder.append("-- Total de registros: ").append(registros).append("\n\n");

        SqlGenerateUtil.startSpool(stringBuilder, spoolName);

        for (String s:data) {
            stringBuilder.append(generateSQL(convertLineToObject(s)));
        }

        SqlGenerateUtil.commit(stringBuilder);

        stringBuilder.append("prompt 'Numero de registro a processar: ").append(registros).append("'\n");

        stringBuilder.append("prompt 'Total de registros processados:'\n");
        countRegistros(stringBuilder);

        SqlGenerateUtil.finishSpool(stringBuilder);

        return stringBuilder;
    }
}
