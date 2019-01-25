package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogFaixaUop;

/**
 * Classe de conversão de layout do arquivo de LOG_FAIXA_UOP de CEP.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:03:00
 */
public class LogFaixaUopConverter implements Converter<LogFaixaUop, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_FAIXA_UOP";
    private static final String columns = "UOP_NU, FNC_INICIAL, FNC_FINAL, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogFaixaUop convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogFaixaUop logFaixaUop = new LogFaixaUop();

        logFaixaUop.setUopNu(getNumber(splited, 0));
        logFaixaUop.setFncInicial(getNumber(splited, 1));
        logFaixaUop.setFncFinal(getNumber(splited, 2));

        return logFaixaUop;
    }

    @Override
    public StringBuilder generateSQL(LogFaixaUop logFaixaUop) {

        StringBuilder sql = new StringBuilder();

        if (contador == 0) SqlGenerateUtil.deleteAllAndCommit(sql, esquema, table, "");

        contador++;

        if (logFaixaUop.getUopNu() != 0) {
            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logFaixaUop.getUopNu()).append(", ");
            sql.append(logFaixaUop.getFncInicial()).append(", ");
            sql.append(logFaixaUop.getFncFinal()).append(", ");
            sql.append("USER, ");
            sql.append("current_timestamp");

            SqlGenerateUtil.insertFinish(sql);
        }

        if ((contador % numCommits) == 0) SqlGenerateUtil.commit(sql);

        return sql;
    }

    @Override
    public void countRegistros(StringBuilder stringBuilder) {
        SqlGenerateUtil.countRegistrosInseridos(stringBuilder, esquema, table, "");
    }
}
