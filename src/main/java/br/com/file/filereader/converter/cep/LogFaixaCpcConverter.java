package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogFaixaCpc;


/**
 * Classe de conversão de layout do arquivo de LOG_FAIXA_CPC de CEP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:00:00
 */
public class LogFaixaCpcConverter implements Converter<LogFaixaCpc, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_FAIXA_CPC";
    private static final String columns = "CPC_NU, CPC_INICIAL, CPC_FINAL, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogFaixaCpc convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogFaixaCpc logFaixaCpc = new LogFaixaCpc();

        logFaixaCpc.setCpcNu(getNumber(splited, 0));
        logFaixaCpc.setCpcInicial(getString(splited, 1));
        logFaixaCpc.setCpcFinal(getString(splited, 2));

        return logFaixaCpc;
    }

    @Override
    public StringBuilder generateSQL(LogFaixaCpc logFaixaCpc) {

        StringBuilder sql = new StringBuilder();

        if (contador == 0) SqlGenerateUtil.deleteAllAndCommit(sql, esquema, table, "");

        contador++;

        if (logFaixaCpc.getCpcNu() != 0) {

            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logFaixaCpc.getCpcNu()).append(", ");
            sql.append("'").append(escapeSpecial(logFaixaCpc.getCpcInicial())).append("', ");
            sql.append("'").append(escapeSpecial(logFaixaCpc.getCpcFinal())).append("', ");
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
